import java.net.URL;
import java.io.InputStreamReader;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.feed.synd.SyndEntry;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.Hashtable;
import java.util.Enumeration;

public class parserRome {

    public static void main(String[] args) {
        
        URL feedUrl = null;
        SyndFeed feed = null;
        //Diccionarios
        Hashtable<String, String> hash = new Hashtable<String, String>();
        hash.put("elp", "http://ep00.epimg.net/rss/tags/ultimas_noticias.xml");
        hash.put("bbc", "http://feeds.bbci.co.uk/news/rss.xml");
        hash.put("lav", "https://www.lavanguardia.com/mvc/feed/rss/home");
        hash.put("cnn", "http://rss.cnn.com/rss/edition_europe.rss");
        hash.put("abc", "https://sevilla.abc.es/rss/feeds/Sevilla_Sevilla.xml");
        hash.put("elm", "https://e00-elmundo.uecdn.es/elmundo/rss/espana.xml");

        Hashtable<String, String> hashT = new Hashtable<String, String>();
        hashT.put("elp", "El país");
        hashT.put("bbc", "BBC Headlines");
        hashT.put("lav", "La vanguardia");
        hashT.put("cnn", "CNN Headlines");
        hashT.put("abc", "ABC: Sevilla");
        hashT.put("elm", "El mundo");

        try {
            Enumeration e1 = hashT.elements();
            Object valor1;
           
            Enumeration e2 = hash.elements();
            Object valor2;
            while( e2.hasMoreElements() ){
                valor1 = e1.nextElement();
                System.out.println( valor1 );
                valor2 = e2.nextElement();
                String convertedToString = valor2.toString();
                feedUrl = new URL(convertedToString);
                SyndFeedInput input = new SyndFeedInput();
                feed = input.build(new InputStreamReader(feedUrl.openStream()));
                System.out.println(feed.getTitle());
                //Lista  que tiene el retorno de getEntries
                List<SyndEntry> synd = new ArrayList<SyndEntry>();
                synd = feed.getEntries();
                //Stream 
                Stream<SyndEntry> stream = synd.stream();
                stream
                .limit(5)
                .forEach(s -> System.out.println("\nTítulo de la noticia: \n" + s.getTitle() + "\n Link de la noticia: \n" + s.getLink() + "\n Descripción de la noticia: \n" + s.getDescription().getValue() + "\n"));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR: " + ex.getMessage());
        }


    }
}