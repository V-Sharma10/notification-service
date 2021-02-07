package com.notif.service.notif.services;

import com.notif.service.notif.exception.InvalidRequestException;
import com.notif.service.notif.exception.NotFoundException;
import com.notif.service.notif.exception.ServiceUnavailableException;
import com.notif.service.notif.models.MessageDtoModel;
import com.notif.service.notif.models.MessageESModel;
import com.notif.service.notif.models.request.MessageRequestModel;
import com.notif.service.notif.repositories.MessageDBRepository;
import com.notif.service.notif.repositories.MessageESRepository;
import com.notif.service.notif.services.redisService.RedisService;
import com.notif.service.notif.utils.ErrorCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.UUID;

import static com.notif.service.notif.utils.Validator.isValidIndianMobileNumber;
import static com.notif.service.notif.utils.Validator.isValidMessage;
import org.apache.commons.beanutils.BeanUtils;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageDBRepository messageDBRepository;

    @Autowired
    private MessageESRepository messageESRepository;

    @Autowired
    RedisService redisService;

    MessageDtoModel msgDto  = new MessageDtoModel();
    MessageESModel msgES = new MessageESModel();

    @Override
    public String sendMsg(MessageRequestModel message)
            throws InvalidRequestException, NotFoundException, ServiceUnavailableException {
             /** Checking if number is valid **/
            if(!isValidIndianMobileNumber(message.getPhoneNumber()) ){
                throw new InvalidRequestException("Invalid Phone Number", ErrorCodes.BAD_REQUEST_ERROR);
            }
            /** Checking if message is valid **/
            if(!isValidMessage(message.getMessage())){
                throw new InvalidRequestException("Invalid Message. Must be greater than 5 characters", ErrorCodes.BAD_REQUEST_ERROR);
            }
             /** Checking if number is blacklisted **/
            if(redisService.checkIfExist(message.getPhoneNumber())){
                throw new InvalidRequestException("Number is Blacklisted", ErrorCodes.BAD_REQUEST_ERROR);
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
            msgDto.setId(id);msgDto.setStatus("queued");

            /** Getting added to DB **/
            try{
                messageDBRepository.save(msgDto);
            } catch (Exception ex){
                throw new ServiceUnavailableException(ex.getMessage(), ErrorCodes.SERVICE_UNAVAILABLE_ERROR);
            }





            /**
             * Once the message is sent over to kafka it means, it has been successfully sent.
             *
             * **/





             /** copied MessageDtoModel(msgDto) to MessageESModel(msgES) **/
            try {
                BeanUtils.copyProperties(msgES, msgDto);
            } catch (IllegalAccessException e) {
                throw new ServiceUnavailableException(e.getMessage(),ErrorCodes.SERVICE_UNAVAILABLE_ERROR);
            } catch (InvocationTargetException e) {
                throw new ServiceUnavailableException(e.getMessage(),ErrorCodes.SERVICE_UNAVAILABLE_ERROR);
            }

            /** Getting added to ES **/
            try{
                messageESRepository.save(msgES);
            }catch (Exception ex){
                throw new ServiceUnavailableException(ex.getMessage(), ErrorCodes.SERVICE_UNAVAILABLE_ERROR);
            }

            return id;
    }

    @Override
    public Optional<MessageDtoModel> getDetailsById(String id) {
        return messageDBRepository.findById(id);
    }
}
