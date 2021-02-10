package com.notif.service.notif.repositories.ES;

import com.notif.service.notif.models.MessageESModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;
@Repository
public interface MessageESRepository extends ElasticsearchRepository<MessageESModel,String> {
//    @Query(" \"query\": {\"range\": {\"createdAt\": {\"gte\": 10,\"lt\": } }}")

//    epoch
    Page<MessageESModel> findAllByCreatedAtBetween(long start, long end, Pageable pageable);
    Optional<MessageESModel> findById(String id);
    Page<MessageESModel> findByMessageContaining(String text,Pageable pageable);
}
