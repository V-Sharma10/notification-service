package com.notif.service.notif.services;

import com.notif.service.notif.controllers.MessageController;
import com.notif.service.notif.exception.InvalidRequestException;
import com.notif.service.notif.exception.NotFoundException;
import com.notif.service.notif.exception.ServiceUnavailableException;
import com.notif.service.notif.middleware.MessageRequestValidator;
import com.notif.service.notif.models.MessageDtoModel;
import com.notif.service.notif.models.request.MessageRequestModel;
import com.notif.service.notif.repositories.DB.MessageDBRepository;
import com.notif.service.notif.services.kafkaService.KafkaProducerServiceImpl;
import com.notif.service.notif.utils.enums.ErrorCodes;
import com.notif.service.notif.utils.enums.StatusEnums;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.UUID;

@Service
public class MessageServiceImpl implements MessageService{
    Logger logger = LoggerFactory.getLogger(MessageController.class);
    @Autowired
    private MessageDBRepository messageDBRepository;

    @Autowired
    private KafkaProducerServiceImpl kafkaProducerService;

    MessageDtoModel msgDto  = new MessageDtoModel();
    MessageRequestValidator validator = new MessageRequestValidator();
    @Override
    public String sendMsg(MessageRequestModel message)
            throws InvalidRequestException, NotFoundException, ServiceUnavailableException {

            if(!validator.main(message)){
                throw new InvalidRequestException("Invalid Request Body",ErrorCodes.BAD_REQUEST_ERROR);
            }

            /** copied MessageRequestModel(message) to MessageDtoModel(msgDto) **/
            try {
                BeanUtils.copyProperties(msgDto, message);
            } catch (IllegalAccessException e) {
                throw new ServiceUnavailableException(e.getMessage(),ErrorCodes.SERVICE_UNAVAILABLE_ERROR);
            } catch (InvocationTargetException e) {
                throw new ServiceUnavailableException(e.getMessage(),ErrorCodes.SERVICE_UNAVAILABLE_ERROR);
            }

             /** Updating Value before adding to DB **/
            String id = UUID.randomUUID().toString();
            msgDto.setId(id);msgDto.setStatus(StatusEnums.QUEUED.getCode());

            /** Getting added to DB **/
            logger.info("Getting added to DB");
            try{
                messageDBRepository.save(msgDto);
            } catch (Exception ex){
                throw new ServiceUnavailableException(ex.getMessage(), ErrorCodes.SERVICE_UNAVAILABLE_ERROR);
            }

            try{
                kafkaProducerService.sendMessage(id);
            } catch (Exception ex){
                throw new ServiceUnavailableException(ex.getMessage(), ErrorCodes.SERVICE_UNAVAILABLE_ERROR);
            }

        logger.info("Request Submitted");
            return id;
    }

    @Override
    public Optional<MessageDtoModel> getDetailsById(String id) {
        return messageDBRepository.findById(id);
    }
}
