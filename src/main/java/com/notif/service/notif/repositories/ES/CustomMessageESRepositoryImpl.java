package com.notif.service.notif.repositories.ES;

import com.notif.service.notif.models.MessageESModel;
import com.notif.service.notif.models.request.SearchPhraseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

@Service
public class CustomMessageESRepositoryImpl implements CustomMessageESRepository{
    Logger logger = LoggerFactory.getLogger(CustomMessageESRepository.class);
    @Autowired
    ElasticsearchOperations elasticsearchOps;

    @Override
    public Page<MessageESModel> getByPhrase(SearchPhraseModel phrase) {
       System.out.println(phrase.getPage()+" "+ phrase.getSize()+" "+ phrase.getPhrase());

        return null;
    }
}
