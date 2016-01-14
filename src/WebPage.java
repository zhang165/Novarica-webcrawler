/**
 * Created by mizhang on 1/14/16.
 */
public class WebPage implements Comparable<WebPage>{
    public String url;
    public int rank;

    public WebPage(String url, int rank){
        this.url = url;
        this.rank = rank;
    }

    @Override
    public int compareTo(WebPage w){
        return w.rank-rank;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WebPage)) return false;
        WebPage webPage = (WebPage) o;
        if (rank != webPage.rank) return false;
        return url != null ? url.equals(webPage.url) : webPage.url == null;

    }

    @Override
    public int hashCode() {
        return url != null ? url.hashCode() : 0;
    }
}
