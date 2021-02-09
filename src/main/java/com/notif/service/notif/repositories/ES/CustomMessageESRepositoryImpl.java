package com.notif.service.notif.repositories.ES;

import com.notif.service.notif.models.MessageESModel;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CustomMessageESRepositoryImpl implements CustomMessageESRepository{
    Logger logger = LoggerFactory.getLogger(CustomMessageESRepository.class);
    @Autowired
    ElasticsearchOperations elasticsearchOps;

    @Override
    public SearchHits<MessageESModel> customRandomFunction(Date startDate, Date endDate) {
        logger.info("customRandomFunction method invoked");
//        Pageable firstPageWithFiveElements = PageRequest.of(0, 5);
        //    @Query(" \"query\": {\"range\": {\"createdAt\": {\"gte\": ?,\"lt\": ?} }}")
        RangeQueryBuilder queryBuilder = QueryBuilders.rangeQuery("createdAt").from(startDate).to(endDate);
       Query searchQuery = new NativeSearchQueryBuilder()
               .withQuery(queryBuilder).build();
        return  elasticsearchOps.search(searchQuery,MessageESModel.class);
    }
}
