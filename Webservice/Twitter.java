 
import java.util.List;
import org.jibble.pircbot.PircBot;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author USER
 */
public class Twitter extends PircBot
{
    
    public static void main(String[] args) throws TwitterException
    {
        
        ConfigurationBuilder cb = new ConfigurationBuilder();
        
        cb.setDebugEnabled(true)
              .setOAuthConsumerKey("8CubXUyud9HFCRGseokOvxoMA")
              .setOAuthConsumerSecret("lRHPkh8bbH6zf3oWIly7ljESykhadyZjBun3EHV8J4sCNKtBqK") 
              .setOAuthAccessToken("1061960868-veC9rS2YMa601M25hWKbn1Bm11SirqbfhwGkQ2f")         
              .setOAuthAccessTokenSecret("yQGJrRg5zKZgs2ApB9SDa5H6TMvtzjm7dZqUif2kFcfa1");
        
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter4j.Twitter twitter = tf.getInstance();
        
        List<Status> status = twitter.getHomeTimeline();
        for(Status st : status)
        {
            System.out.println(st.getUser().getName() + "------" + st.getText());            
        }
    }
}
