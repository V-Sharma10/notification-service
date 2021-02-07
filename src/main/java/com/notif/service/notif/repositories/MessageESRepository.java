package com.notif.service.notif.repositories;

import com.notif.service.notif.models.MessageESModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.sql.Timestamp;
import java.util.Date;

public interface MessageESRepository extends ElasticsearchRepository<MessageESModel,String> {

    Page<MessageESModel> findAllByCreatedAtAfterAndCreatedAtBefore(String startDate,
                                                                   String endDate,
                                                                   Pageable pageable);

}
