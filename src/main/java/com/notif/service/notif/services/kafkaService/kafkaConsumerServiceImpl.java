package com.notif.service.notif.services.kafkaService;

import com.notif.service.notif.exception.InvalidRequestException;
import com.notif.service.notif.exception.NotFoundException;
import com.notif.service.notif.exception.ServiceUnavailableException;
import com.notif.service.notif.models.MessageDtoModel;
import com.notif.service.notif.models.MessageESModel;
import com.notif.service.notif.repositories.DB.MessageDBRepository;
import com.notif.service.notif.repositories.ES.MessageESRepository;
import com.notif.service.notif.services.HelperService;
import com.notif.service.notif.services.redisService.RedisService;
import com.notif.service.notif.utils.enums.ErrorCodes;
import com.notif.service.notif.utils.enums.FailureEnums;
import com.notif.service.notif.utils.externalSmsApi.IMIMessagingConnect;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;


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
    private IMIMessagingConnect messagingConnect;

    @Autowired
    private HelperService helperService;

    MessageESModel msgES = new MessageESModel();

    @KafkaListener(topics = "${kafka.topic}",groupId = "${kafka.groupid}",autoStartup = "${kafka_autostart}")
    @Override
    public void listener(String message) throws InvalidRequestException, ServiceUnavailableException, NotFoundException, InvocationTargetException, IllegalAccessException {
        System.out.println("Received Message in group foo: " + message);
        MessageDtoModel msgDtoConsumer= messageDBRepository.findById(message).orElse(null);

        if(msgDtoConsumer==null){
            throw new ServiceUnavailableException(FailureEnums.CANT_FIND.getMessage(), ErrorCodes.SERVICE_UNAVAILABLE_ERROR);
        }

        if(redisService.checkIfExist(msgDtoConsumer.getPhoneNumber())){
            helperService.sendingFailed(msgDtoConsumer);
            messageDBRepository.save(msgDtoConsumer);
            throw new InvalidRequestException(FailureEnums.BLACKLIST.getMessage(), ErrorCodes.BAD_REQUEST_ERROR);
        }

        try{
             String response = messagingConnect.thirdPartyCall(msgDtoConsumer.getId(),
                     msgDtoConsumer.getPhoneNumber(), msgDtoConsumer.getMessage());
             logger.info(response);
             helperService.sendingSuccess(msgDtoConsumer,response);
             messageDBRepository.save(msgDtoConsumer);
        }catch(Exception ex){
            throw new ServiceUnavailableException(ex.getMessage(), ErrorCodes.SERVICE_UNAVAILABLE_ERROR);
        }

        logger.info("Getting added to ES");

            BeanUtils.copyProperties(msgES, msgDtoConsumer);
         try{
            messageESRepository.save(msgES);
         }catch (Exception ex){
            throw new ServiceUnavailableException(ex.getMessage(), ErrorCodes.SERVICE_UNAVAILABLE_ERROR);
         }
    }

}


