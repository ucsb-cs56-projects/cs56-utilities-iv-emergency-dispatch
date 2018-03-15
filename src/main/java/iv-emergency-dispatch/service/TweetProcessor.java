package ivemergencydispatch.service;

import ivemergencydispatch.model.Emergency;
import ivemergencydispatch.repository.EmergencyRepository;
import org.springframework.social.twitter.api.Tweet;

import java.util.Date;
import java.util.concurrent.BlockingQueue;

public class TweetProcessor implements Runnable {
    private EmergencyService emergencyService;

    private final BlockingQueue<Tweet> queue;

    public TweetProcessor(EmergencyService emergencyService, BlockingQueue<Tweet> queue) {
        this.emergencyService = emergencyService;
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Tweet tweet = queue.take();
                processTweet(tweet);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void processTweet(Tweet tweetEntity) {
        String text = tweetEntity.getText();
        
        Long id = tweetEntity.getId();
        String category = tweetEntity.getText();
        String description = tweetEntity.getText();
        String address = tweetEntity.getText();
        Date time = tweetEntity.getCreatedAt();

        
        Emergency newEmergency = new Emergency(id, description, category, address, time);
        emergencyService.createEmergency(newEmergency);
        System.out.println(newEmergency.toString());
    }
}