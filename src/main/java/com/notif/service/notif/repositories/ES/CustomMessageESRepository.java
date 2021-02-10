package com.notif.service.notif.repositories.ES;

import com.notif.service.notif.models.MessageESModel;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Repository;

import java.util.Date;
@Repository
public interface CustomMessageESRepository {
     SearchHits<MessageESModel> customRandomFunction(Date startDate, Date endDate);
}
