package com.notif.service.notif.services;

import com.notif.service.notif.exception.InvalidRequestException;
import com.notif.service.notif.exception.NotFoundException;
import com.notif.service.notif.exception.ServiceUnavailableException;
import com.notif.service.notif.models.request.MessageRequestModel;

public interface MessageService {
    String sendMsg(MessageRequestModel message) throws InvalidRequestException, NotFoundException, ServiceUnavailableException;
}
