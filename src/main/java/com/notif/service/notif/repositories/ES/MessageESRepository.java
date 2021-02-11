package com.notif.service.notif.repositories.ES;

import com.notif.service.notif.models.MessageESModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface MessageESRepository extends ElasticsearchRepository<MessageESModel,String>,
        CustomMessageESRepository {
//    @Query(" \"query\": {\"range\": {\"createdAt\": {\"gte\": 10,\"lt\": } }}")

//    epoch
    Page<MessageESModel> findAllByCreatedAtBetween(long start, long end, Pageable pageable);
    Optional<MessageESModel> findById(String id);
    Page<MessageESModel> findByMessageContaining(String text,Pageable pageable);
    Page<MessageESModel> findByMessageExists(String text,Pageable pageable);
}
