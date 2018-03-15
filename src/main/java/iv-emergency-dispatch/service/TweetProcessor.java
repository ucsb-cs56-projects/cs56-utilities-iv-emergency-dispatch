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
        // tweets from SBCFireDispatch are in form:
        // Page 6477 El Colegio Rd ,Isla Vista *Medical Emergency *34416815 -119853131 *FSBC180003588*ME17,RA17
        String text = tweetEntity.getText();
        
        // only add process if tweet has address in IV
        if(!text.toLowerCase().contains("isla vista")){
          return;
        }
        
        // split text into array, separating text on *
        String[] elements = text.split(" \\*");
        
        // Address is first element of split array minus "Page" string
        String address = elements[0].substring(5);
        
        // description is second element of split array
        String description = elements[1];
        
        // get tweet id and time it was tweeted
        String id = tweetEntity.getIdStr();
        Date time = tweetEntity.getCreatedAt();
        
        // create Emergency object and save to database
        Emergency newEmergency = new Emergency(time, address, description);
        emergencyService.createEmergency(newEmergency);
        System.out.println(newEmergency.toString());
    }
}