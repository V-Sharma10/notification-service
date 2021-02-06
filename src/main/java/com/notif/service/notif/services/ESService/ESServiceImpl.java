package com.notif.service.notif.services.ESService;

import com.notif.service.notif.models.MessageESModel;
import com.notif.service.notif.repositories.MessageESRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ESServiceImpl implements ESService{
    @Autowired
    MessageESRepository messageESRepository;

    @Override
    public Iterable<MessageESModel> getAll() {
        return messageESRepository.findAll();
    }
}
