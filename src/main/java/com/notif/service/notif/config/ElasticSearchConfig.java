package com.notif.service.notif.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

@Configuration
public class ElasticSearchConfig {
    @Bean
    public RestHighLevelClient elasticsearchClient() {


        RestHighLevelClient client = new RestHighLevelClient(RestClient
                .builder(new HttpHost("localhost",9200,"http")));

        return client;
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchRestTemplate(elasticsearchClient());
    }
}
