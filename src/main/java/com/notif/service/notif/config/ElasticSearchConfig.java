package com.notif.service.notif.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

@Configuration
public class ElasticSearchConfig {
    Logger logger = LoggerFactory.getLogger(ElasticSearchConfig.class);
/**
 *  index builder bean to add
 **/

    @Bean
    public RestHighLevelClient elasticsearchClient() {


        RestHighLevelClient client = new RestHighLevelClient(RestClient
                .builder(new HttpHost("localhost",9200,"http")));

        return client;
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        logger.info("Elasticsearch connected.");
        return new ElasticsearchRestTemplate(elasticsearchClient());
    }
}
