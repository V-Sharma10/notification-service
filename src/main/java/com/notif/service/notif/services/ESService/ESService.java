package com.notif.service.notif.services.ESService;

import com.notif.service.notif.models.MessageESModel;

public interface ESService {
    Iterable<MessageESModel> getAll();
}
