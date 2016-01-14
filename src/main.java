import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mizhang on 1/14/16.
 */

public class main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Enter webpage url to crawl: ");
            String url = sc.next();
            if(url.indexOf("www")==0){
                url = "http://"+url;
            }

            Parser p = new Parser(url);
            if(url!=null && url.trim().length()>0 && url.matches("[http].+[^(pdf|rar|zip)]")) {
                HashMap<String,Integer> map = p.parse();
//                for(String key: map.keySet()){
//                    System.out.println(key);
//                    System.out.println(map.get(key));
//                }
                Ranker ranker = new Ranker(map);
                ranker.rank();
            }

        }
    }
}
