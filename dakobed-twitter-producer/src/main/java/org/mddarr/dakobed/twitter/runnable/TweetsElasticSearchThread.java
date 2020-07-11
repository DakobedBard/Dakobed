package org.mddarr.dakobed.twitter.runnable;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongSerializer;
import org.joda.time.DateTime;
import org.mddarr.avro.tweets.Tweet;
import org.mddarr.dakobed.twitter.AppConfig;
import org.mddarr.dakobed.twitter.locationparser.LocationParser;
import twitter4j.Status;

import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;

public class TweetsElasticSearchThread {
    LocationParser locationParser;
    private final Log log = LogFactory.getLog(getClass());
    private final AppConfig appConfig;

    private final CountDownLatch latch;

    private int recordCount;

    private final ArrayBlockingQueue<Status> statusQueue;

    public TweetsElasticSearchThread(AppConfig appConfig,
                                    ArrayBlockingQueue<Status> statusQueue,
                                    CountDownLatch latch){
        this.locationParser = new LocationParser();
        this.statusQueue = statusQueue;
        this.appConfig = appConfig;
        this.latch = latch;


        this.recordCount +=1;
    }



    public void run() {

        int tweetCount = 0;

        while(latch.getCount() >0 ) {
            try {
                if(statusQueue.size()>0){
                    Status status = statusQueue.poll();
                    tweetCount +=1;
                    Tweet tweet = statusToTweet(status, tweetCount);

                }else{
                    Thread.sleep(200);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        close();
    }


    public void close(){
        log.info("Closing Producer");
        latch.countDown();
    }

    public Tweet statusToTweet(Status status, int id){

        Tweet.Builder tweetBuilder = Tweet.newBuilder();
        tweetBuilder.setScreename(status.getUser().getScreenName());
        tweetBuilder.setName(status.getUser().getName());
        tweetBuilder.setLocation(status.getUser().getLocation());

        tweetBuilder.setTweetContent(status.getText());
        tweetBuilder.setTweetTime(new DateTime(status.getCreatedAt()));
        tweetBuilder.setId(id);
        tweetBuilder.setLng(-12.0);
        tweetBuilder.setLat(12.0);

//        ArrayList<Double> coords = locationParser.parseLocation(status.getUser().getLocation());
//        tweetBuilder.setLat(coords.get(0));
//        tweetBuilder.setLng(coords.get(1));

        return tweetBuilder.build();
    }
}
