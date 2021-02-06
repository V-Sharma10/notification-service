package com.notif.service.notif.services;

import com.notif.service.notif.exception.InvalidRequestException;
import com.notif.service.notif.exception.NotFoundException;
import com.notif.service.notif.exception.ServiceUnavailableException;
import com.notif.service.notif.models.MessageDtoModel;
import com.notif.service.notif.models.request.MessageRequestModel;
import com.notif.service.notif.repositories.MessageDBRepository;
import com.notif.service.notif.utils.ErrorCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.notif.service.notif.utils.Validator.isValidIndianMobileNumber;
import static com.notif.service.notif.utils.Validator.isValidMessage;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageDBRepository messageDBRepository;

    MessageDtoModel msgDto  = new MessageDtoModel();

    @Override
    public String sendMsg(MessageRequestModel message)
            throws InvalidRequestException, NotFoundException, ServiceUnavailableException {

            if(!isValidIndianMobileNumber(message.getPhoneNumber()) ){
                throw new InvalidRequestException("Invalid Phone Number", ErrorCodes.BAD_REQUEST_ERROR);
            }
            if(!isValidMessage(message.getMessage())){
                throw new InvalidRequestException("Invalid Message. Must be greater than 5 characters", ErrorCodes.BAD_REQUEST_ERROR);
            }
            String id = UUID.randomUUID().toString();
            msgDto.setId(id);msgDto.setStatus("queued");
            msgDto.setPhoneNumber(message.getPhoneNumber());
            msgDto.setMessage(message.getMessage());
            messageDBRepository.save(msgDto);
            return id;
    }
}
