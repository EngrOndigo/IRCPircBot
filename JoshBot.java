package weatherapi;

import org.jibble.pircbot.PircBot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
	

import com.google.gson.Gson;
import java.io.IOException;
	

public class JoshBot extends PircBot {
	
     static String server = "irc.freenode.net";
     static String name = "youBot";
     static String channel = "#pircbot";
    
    public JoshBot(){
    setName(name);
    }
    
     @Override
    public void onMessage(String channel,String sender, String login, String hostname, String message){
    Gson gson = new Gson();
    
    String cityId = "";
	
    String json2 = readUrl("http://openweathermap.org/data/2.5/weather?id=" + cityId + "&appid=4ad7517e585b7461adb8bd4cc56d3ac5");
    String json = readUrl("http://www.javascriptkit.com/dhtmltutors/javascriptkit.json"); 
	
	Page page = gson.fromJson(json, Page.class);
	
        sendMessage(channel,page.title);
        page.items.forEach((item) -> {
            sendMessage(channel,"    " + item.title);
         });   
        
	Weather weather = gson.fromJson(json2, Weather.class);
       
		
        sendMessage(channel, weather.id);
        sendMessage(channel,weather.name);
     
    }

    public static void main(String[] args)  throws Exception{
        JoshBot bot = new JoshBot();
        
        bot.setVerbose(true);
    
        bot.connect(server);
        bot.sendMessage("Falied to connect to ", server);
        bot.joinChannel(channel);
        bot.sendMessage(channel, "My is chatBot is running!...");
        
        
        }
		
	public static String readUrl(String urlString) {
		String readText = "";
			try {
		BufferedReader reader = null;
		    try { 
		        URL url = new URL(urlString);
		        reader = new BufferedReader(new InputStreamReader(url.openStream()));
		        StringBuilder buffer = new StringBuilder();
		        int read;
		        char[] chars = new char[1024];
		        while ((read = reader.read(chars)) != -1)
		            buffer.append(chars, 0, read); 
		 
		        readText = buffer.toString();
		        
		    } finally { 
		        if (reader != null)
		            reader.close();
		    }
			} catch(IOException e) { System.out.println(e); }
			
			return readText;
		}
                
                public class Weather {
			String id;
			String name;
			String dt;
			String cod;
			List<Main> main1;
		}
        
		public class Main {
			String temp;
			String pressure;
			String humidity;
			String temp_min;
			String temp_max;
                        }
		public class Page { 
		    String title;
		    String link;
		    String description;
		    String language;
		    List<Item> items;
		}
		
		public class Item { 
		    String title;
		    String link;
		    String description;
		}	
		
	}
