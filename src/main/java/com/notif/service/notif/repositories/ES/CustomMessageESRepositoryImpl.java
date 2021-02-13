package com.notif.service.notif.repositories.ES;

import com.notif.service.notif.models.MessageESModel;
import com.notif.service.notif.models.request.SearchPhraseModel;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

@Service
public class CustomMessageESRepositoryImpl implements CustomMessageESRepository{

        Logger logger = LoggerFactory.getLogger(CustomMessageESRepository.class);


    @Autowired
    ElasticsearchOperations elasticsearchOps;

    @Override
    public Page<MessageESModel> getByPhrase(SearchPhraseModel phrase) {
        logger.isTraceEnabled();
//       System.out.println(phrase.getPage()+" "+ phrase.getSize()+" "+ phrase.getPhrase());
      String search = ".*"+phrase.getPhrase()+".*";
    logger.trace("searched for phrase: {} from {} page and {} size",phrase.getPhrase(),phrase.getPage(),phrase.getSize());
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.multiMatchQuery(search).field("message")
                        .type(MultiMatchQueryBuilder.DEFAULT_TYPE))
                .build().setPageable(PageRequest.of(phrase.getPage(), phrase.getSize()));

         SearchHits<MessageESModel> pageHits =  elasticsearchOps.search(searchQuery,MessageESModel.class);

         System.out.println(pageHits);


//
//         List<MessageESModel> pageList = new ArrayList<>();
//         int i=0;
//         while(i< phrase.getSize()){
//             pageList.add(pageHits.getSearchHit(i).getContent());
//             i++;
//         }
//         System.out.println(pageList);
         return null;
    }
}
