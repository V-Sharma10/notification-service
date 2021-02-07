package com.notif.service.notif.services;

import com.notif.service.notif.exception.InvalidRequestException;
import com.notif.service.notif.exception.NotFoundException;
import com.notif.service.notif.exception.ServiceUnavailableException;
import com.notif.service.notif.models.MessageDtoModel;
import com.notif.service.notif.models.request.MessageRequestModel;

import java.util.Optional;

public interface MessageService {
    String sendMsg(MessageRequestModel message) throws InvalidRequestException, NotFoundException, ServiceUnavailableException;
    Optional<MessageDtoModel> getDetailsById(String id);

}
