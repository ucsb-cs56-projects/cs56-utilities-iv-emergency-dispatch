package ivemergencydispatch.service;

import ivemergencydispatch.model.Emergency;
import ivemergencydispatch.repository.EmergencyRepository;
import org.springframework.social.twitter.api.Tweet;
import com.google.maps.model.LatLng;
import java.util.Date;
import java.util.concurrent.BlockingQueue;

public class TweetProcessor implements Runnable {
    private EmergencyService emergencyService;
    private GeocodingService geocodingService;

    private final BlockingQueue<Tweet> queue;

    public TweetProcessor(EmergencyService emergencyService, GeocodingService geocodingService, BlockingQueue<Tweet> queue) {
        this.emergencyService = emergencyService;
        this.geocodingService = geocodingService;
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
        String address = elements[0].substring(5).replace(" ,Isla Vista", "");
        
        // description is second element of split array
        String description = elements[1];
        
        // get tweet id and time it was tweeted
        String id = tweetEntity.getIdStr();
        Date time = tweetEntity.getCreatedAt();
        
        try {
          // get latlng coordinates of address using Google Maps API
          String latlng = geocodingService.getCoords(address);
          
          // create Emergency object and save to database
          Emergency newEmergency = new Emergency(time, address, description, latlng);
          emergencyService.createEmergency(newEmergency);
          System.out.println(newEmergency.toString());
        } catch( Exception e) {
          System.out.println(e);
        }        
    }
}