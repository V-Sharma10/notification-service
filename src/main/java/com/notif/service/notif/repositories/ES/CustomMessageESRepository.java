package com.notif.service.notif.repositories.ES;

import com.notif.service.notif.models.MessageESModel;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.util.Date;

public interface CustomMessageESRepository {
     SearchHits<MessageESModel> customRandomFunction(Date startDate, Date endDate);
}
