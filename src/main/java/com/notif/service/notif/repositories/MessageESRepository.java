package com.notif.service.notif.repositories;

import com.notif.service.notif.models.MessageESModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface MessageESRepository extends ElasticsearchRepository<MessageESModel,String> {
}
