package ivemergencydispatch.service;

// import com.kaviddiss.keywords.domain.Profile;
import org.springframework.social.twitter.api.MentionEntity;
import org.springframework.social.twitter.api.Tweet;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* Created by david on 2014-12-19.
*/
public class TweetProcessor implements Runnable {
    private static final Pattern HASHTAG_PATTERN = Pattern.compile("#\\w+");

    private final BlockingQueue<Tweet> queue;

    public TweetProcessor(BlockingQueue<Tweet> queue) {
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
        String lang = tweetEntity.getLanguageCode();
        String text = tweetEntity.getText();
        // filter non-English tweets:
        if (!"en".equals(lang)) {
            return;
        }

        System.out.printf(text);
    }
}