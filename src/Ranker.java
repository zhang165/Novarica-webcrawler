import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by mizhang on 1/14/16.
 */
public class Ranker {
    public HashMap<String, Integer> map;
    private SortedSet<WebPage> set;

    public Ranker(HashMap<String, Integer> map){
        this.map = map;
        set = new TreeSet();
    }
    public void rank(){
        for(String key: map.keySet()){
            set.add(new WebPage(key, map.get(key)));
        }

        for(WebPage w: set){
            System.out.println("Url: "+w.url);
            System.out.println("Count: "+w.rank);
        }
    }



}
