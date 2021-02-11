package com.notif.service.notif.services.kafkaService;

import java.lang.reflect.InvocationTargetException;

public interface kafkaConsumerService {
     void listener(String message) throws InvocationTargetException, IllegalAccessException;
}
