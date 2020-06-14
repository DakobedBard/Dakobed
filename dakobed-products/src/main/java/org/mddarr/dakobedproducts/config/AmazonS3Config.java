package org.mddarr.dakobedproducts.config;


import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class AmazonS3Config
{

    Map<String, String> env = System.getenv();

    private String awsKeyId;
    private String awsKeySecret;


    @Bean(name = "awsRegion")
    public Region getAWSPollyRegion() {
        String awsRegion = "us-west-2";
        return Region.getRegion(Regions.fromName(awsRegion));
    }

    @Bean(name = "awsKeyId")
    public String getAWSKeyId() {
        return env.get("AWS_ACCESS_KEY");
    }

    @Bean(name = "awsKeySecret")
    public String getAWSKeySecret() {
        return env.get("AWS_SECRET_ACCESS_KEY");
    }

    @Bean(name = "awsCredentialsProvider")
    public AWSCredentialsProvider getAWSCredentials() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(getAWSKeyId(), getAWSKeySecret());
        return new AWSStaticCredentialsProvider(awsCredentials);
    }

}