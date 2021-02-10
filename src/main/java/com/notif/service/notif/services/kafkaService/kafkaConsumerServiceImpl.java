package com.notif.service.notif.services.kafkaService;

import com.notif.service.notif.exception.InvalidRequestException;
import com.notif.service.notif.exception.NotFoundException;
import com.notif.service.notif.exception.ServiceUnavailableException;
import com.notif.service.notif.models.MessageDtoModel;
import com.notif.service.notif.models.MessageESModel;
import com.notif.service.notif.repositories.DB.MessageDBRepository;
import com.notif.service.notif.repositories.ES.MessageESRepository;
import com.notif.service.notif.services.redisService.RedisService;
import com.notif.service.notif.utils.enums.ErrorCodes;
import com.notif.service.notif.utils.externalAPIService.IMIMessagingConnect;
import com.notif.service.notif.utils.enums.StatusEnums;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

@ComponentScan("com.notif.service.notif.*")
@Service
public class kafkaConsumerServiceImpl implements kafkaConsumerService{

    Logger logger = LoggerFactory.getLogger(kafkaConsumerServiceImpl.class);

    @Autowired
    private MessageDBRepository messageDBRepository;

    @Autowired
    private MessageESRepository messageESRepository;

    @Autowired
    private RedisService redisService;

    @Autowired
    IMIMessagingConnect messagingConnect;


    MessageESModel msgES = new MessageESModel();
    @KafkaListener(topics = "${kafka.topic}",groupId = "${kafka.groupid}",autoStartup = "${kafka_autostart}")
    @Override
    public void listener(String message) throws InvalidRequestException,ServiceUnavailableException, NotFoundException {
        System.out.println("Received Message in group foo: " + message);
        MessageDtoModel msgDtoConsumer= messageDBRepository.findById(message).orElse(null);

        if(msgDtoConsumer==null){
            throw new ServiceUnavailableException("Cannot find the message in DB.",
                    ErrorCodes.SERVICE_UNAVAILABLE_ERROR);
        }

        if(redisService.checkIfExist(msgDtoConsumer.getPhoneNumber())){
            msgDtoConsumer.setUpdatedAt(new Date());
            msgDtoConsumer.setFailureCode(ErrorCodes.BAD_REQUEST_ERROR.getCode());
            msgDtoConsumer.setFailureComments("Blacklisted Number.");
            msgDtoConsumer.setStatus(StatusEnums.FAILED.getCode());
            messageDBRepository.save(msgDtoConsumer);
            throw new InvalidRequestException("Failed. Blacklisted Number.",ErrorCodes.BAD_REQUEST_ERROR);
        }

        /**
         * Instantiate External API Object.
         **/
        try{
            messagingConnect.thirdPartyCall(msgDtoConsumer.getId(),
                    msgDtoConsumer.getPhoneNumber(),
                    msgDtoConsumer.getMessage());

            msgDtoConsumer.setUpdatedAt(new Date());
            msgDtoConsumer.setStatus(StatusEnums.SUCCESS.getCode());
            messageDBRepository.save(msgDtoConsumer);
        }catch(Exception ex){
            throw new ServiceUnavailableException("3rd party API failed",ErrorCodes.SERVICE_UNAVAILABLE_ERROR);
        }

        /** Getting added to ES **/
        logger.info("Getting added to ES");

        /** copied MessageDtoModel(msgDto) to MessageESModel(msgES) **/
        try {
            BeanUtils.copyProperties(msgES, msgDtoConsumer);
        } catch (IllegalAccessException e) {
            throw new ServiceUnavailableException(e.getMessage(),ErrorCodes.SERVICE_UNAVAILABLE_ERROR);
        } catch (InvocationTargetException e) {
            throw new ServiceUnavailableException(e.getMessage(),ErrorCodes.SERVICE_UNAVAILABLE_ERROR);
        }

        try{
            messageESRepository.save(msgES);
        }catch (Exception ex){
            throw new ServiceUnavailableException(ex.getMessage(), ErrorCodes.SERVICE_UNAVAILABLE_ERROR);
        }
    }

}


