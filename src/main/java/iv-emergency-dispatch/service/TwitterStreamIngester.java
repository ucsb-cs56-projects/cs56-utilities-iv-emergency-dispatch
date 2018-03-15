package ivemergencydispatch.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.social.twitter.api.*;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Service
public class TwitterStreamIngester implements StreamListener {

    @Inject
    private Twitter twitter;
    @Inject
    private EmergencyService emergencyService;
    @Inject
    private GeocodingService geocodingService;
    @Inject
    private ThreadPoolTaskExecutor taskExecutor;
    @Value("${twitterProcessing.enabled}")
    private boolean processingEnabled;

    private BlockingQueue<Tweet> queue = new ArrayBlockingQueue<>(20);

    public void run() {
        List<StreamListener> listeners = new ArrayList<>();
        listeners.add(this);
        FilterStreamParameters userParams = new FilterStreamParameters();
        // Follow @SBCFireDispatch by adding their twitter user id to userParams
        userParams.follow(973338933533720577l);
        twitter.streamingOperations().filter(userParams, listeners);
    }

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        if (processingEnabled) {
            for (int i = 0; i < taskExecutor.getMaxPoolSize(); i++) {
                taskExecutor.execute(new TweetProcessor(emergencyService, geocodingService, queue));
            }

            run();
        }
    }

    @Override
    public void onTweet(Tweet tweet) {
        queue.offer(tweet);
    }

    @Override
    public void onDelete(StreamDeleteEvent deleteEvent) {
    }

    @Override
    public void onLimit(int numberOfLimitedTweets) {
    }

    @Override
    public void onWarning(StreamWarningEvent warningEvent) {
    }
}