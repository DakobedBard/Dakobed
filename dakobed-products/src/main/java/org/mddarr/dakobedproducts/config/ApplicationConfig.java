package org.mddarr.dakobedproducts.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Configuration
public class ApplicationConfig {

    @Value("${spring.application.name}")
    private String appName;

    @Bean
    public AmazonS3 amazonS3Client(AWSCredentialsProvider credentialsProvider) {
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(credentialsProvider)
                .withRegion("us-west-1")
                .build();
    }

    @Bean
    public AmazonSNS amazonSNSClient(AWSCredentialsProvider credentialsProvider) {
        return AmazonSNSClient.builder()
                .standard()
                .withRegion("us-west-2")
                .withCredentials(credentialsProvider)
                .build();
    }
}
