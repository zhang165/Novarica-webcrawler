import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by mizhang on 1/14/16.
 */
public class Parser {
    public String url;
    public Document doc;
    public Parser(String url){
        if(url!=null && url.trim().length()>0 && url.matches("[http].+[^(pdf|rar|zip)]")) {
            this.url = url;
            try {
                this.doc = Jsoup.connect(url).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // prints all links to a page
    public HashMap parse(){
        HashMap<String, Integer> map = new HashMap(); // contains all urls and times seen

        Queue<Document> queue = new LinkedList(); // queue that contains the next page to crawl
        HashSet<String> seen = new HashSet();
        queue.offer(doc);
        seen.add(url);

        System.out.println("Crawling: please wait");
        Document current;
        int count = 0;
        while(!queue.isEmpty() && count < 50){  // BFS
            current = queue.poll();
            org.jsoup.select.Elements links = current.select("a");
            for(Element e: links){
                String key = new String(e.attr("abs:href"));
                if(key!=null&& key.trim().length()>0 && key.matches("[http].+[^(pdf|rar|zip)]")) { // won't try invalid urls
//                    System.out.println("Trying key: " + key);
                    if (!map.containsKey(key)) {
                        map.put(key, 1);
                    } else {
                        map.put(key, map.get(key) + 1);
                    }
                    try {
                        if (!seen.contains(key)) {
                            Document newDoc = Jsoup.connect(key).get();
                            queue.offer(newDoc);
                            seen.add(key);
                        }
                    } catch (IOException exec) {
                        continue;
                    }
                }
            }
            count++;
        }
        return map;
    }
}
