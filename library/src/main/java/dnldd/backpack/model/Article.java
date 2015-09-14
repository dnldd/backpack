package dnldd.backpack.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Article {
    protected String title;
    protected Element body;
    protected Document document;

    public String getTitle(){ return title; }
    public Element getBody(){ return body; }
    public Document getDocument(){ return document; }

    public void parseDocument(String data){
        this.document = Jsoup.parse(data);
        this.title = document.title();
        this.body = document.body();
    }
}