package org.mddarr.dakobed.twitter;

import com.typesafe.config.Config;

public class AppConfig {

    private final String bootstrapServers;
    private final String schemaRegistryUrl;
    private final String sourceTopicName;
    private final String elasticSearchURL;
    private final String tweetTopicName;
    private final String applicationId;
    private final String tweetTopic;
    public AppConfig(Config config, String[] arguments) {

        this.tweetTopic = arguments[0];

        // this.bootstrapServers = "http://kafka:9092";
//        this.schemaRegistryUrl = "http://schema-registry:8081";
        this.elasticSearchURL = "http://elasticsearch7:29200";
        this.bootstrapServers = "http://" + arguments[1]; // localhost:9092";
        this.schemaRegistryUrl = "http://" + arguments[2]; // arlocalhost:8081";

        this.sourceTopicName = "kafka.source.tweet."+arguments[0];
        this.tweetTopicName = arguments[0];

        this.applicationId = "my-app-v1.0.0"; //config.getString("kafka.streams.application.id");
    }

    public int getQueuCapacity(){return 100;}

    public String getBootstrapServers() {
        return bootstrapServers;
    }

    public String getSchemaRegistryUrl() {
        return schemaRegistryUrl;
    }

    public String getSourceTopicName() {
        return sourceTopicName;
    }


    public String getTweetTopicName() {
        return tweetTopicName;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public String getTweetKeyword(){return tweetTopic;}


}
