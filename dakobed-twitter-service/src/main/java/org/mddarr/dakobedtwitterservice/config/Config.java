package org.mddarr.dakobedtwitterservice.config;


import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.io.IOException;

@Configuration
@EnableElasticsearchRepositories(basePackages = "org.mddarr.tweetsservice.dao")
@ComponentScan(basePackages = { "org.mddarr.tweetsservice" })
public class Config {
    @Bean
    RestHighLevelClient client() throws IOException {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("https://search-dakobedes-o5fqopyonjvcuzkvpeyoezfgey.us-west-2.es.amazonaws.com",443, "https")));

        ClusterHealthRequest req = new ClusterHealthRequest();
        ClusterHealthResponse res =  client.cluster().health(req, RequestOptions.DEFAULT);
        System.out.println(res.toString());
        return client;

//
//        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
//                .connectedTo("https://search-dakobedes-o5fqopyonjvcuzkvpeyoezfgey.us-west-2.es.amazonaws.com:443" )
////                .withBasicAuth("master-user","1!Master-user-password") //, "localhost:9200")
//                .build();
//
//        return RestClients.create(clientConfiguration).rest();
    }

}