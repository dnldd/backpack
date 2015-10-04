package dnldd.backpack.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import dnldd.backpack.utils.LogUtils;

public class Article {
    protected String title;
    protected Element body;
    protected Document document;
    protected ArrayList<Tag> tags;


    public String getTitle(){ return title; }
    public Element getBody(){ return body; }
    public Document getDocument(){ return document; }

    public void parseDocument(String data, boolean isURL) {
        if (isURL) {
            try {
                this.document = Jsoup.connect(data).get();
                this.title = document.title();
                this.body = document.body();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            this.document = Jsoup.parse(data);
            this.title = document.title();
            this.body = document.body();
        }
    }

    public void generateTags(){
        Elements elements = this.document.getAllElements();
        int order = 1;

        for (int index = 0; index < elements.size(); index++) {
            Element element = elements.get(index);

            if (!element.hasText()) {
                continue;
            } else {

                if (element.tagName().equalsIgnoreCase(Tag.H1)) {
                    tags.add(new Tag(order, Tag.H1_TYPE, element));
                    order += 1;

                } else if (element.tagName().equalsIgnoreCase(Tag.H2)) {
                    tags.add(new Tag(order, Tag.H2_TYPE, element));
                    order += 1;
                } else if (element.tagName().equalsIgnoreCase(Tag.H3)) {
                    tags.add(new Tag(order, Tag.H3_TYPE, element));
                    order += 1;
                } else if (element.tagName().equalsIgnoreCase(Tag.H4)) {
                    tags.add(new Tag(order, Tag.H4_TYPE, element));
                    order += 1;
                } else if (element.tagName().equalsIgnoreCase(Tag.H5)) {
                    tags.add(new Tag(order, Tag.H5_TYPE, element));
                    order += 1;
                } else if (element.tagName().equalsIgnoreCase(Tag.H6)) {
                    tags.add(new Tag(order, Tag.H6_TYPE, element));
                    order += 1;
                } else if (element.tagName().equalsIgnoreCase(Tag.UNORDERED_LIST)) {
                    tags.add(new Tag(order, Tag.UNORDERED_LIST_TYPE, element));
                    order += 1;

                } else if (element.tagName().equalsIgnoreCase(Tag.ORDERED_LIST)) {
                    tags.add(new Tag(order, Tag.ORDERED_LIST_TYPE, element));
                    order += 1;
                } else if (element.tagName().equalsIgnoreCase(Tag.DESCRIPTION_LIST)) {
                    tags.add(new Tag(order, Tag.DESCRIPTION_LIST_TYPE, element));
                    order += 1;
                } else if (element.tagName().equalsIgnoreCase(Tag.PREFORMATTED_TEXT)) {
                    tags.add(new Tag(order, Tag.PREFORMATTED_TEXT_TYPE, element));
                    order += 1;
                } else if (element.tagName().equalsIgnoreCase(Tag.ADDRESS)) {
                    tags.add(new Tag(order, Tag.ADDRESS_TYPE, element));
                    order += 1;
                } else if (element.tagName().equalsIgnoreCase(Tag.IMAGE)) {
                    tags.add(new Tag(order, Tag.IMAGE_TYPE, element));
                    order += 1;
                } else if (element.tagName().equalsIgnoreCase(Tag.BLOCKQUOTE)) {
                    tags.add(new Tag(order, Tag.BLOCKQUOTE_TYPE, element));
                    order += 1;
                } else if (element.tagName().equalsIgnoreCase(Tag.PARAGRAPH)) {
                    tags.add(new Tag(order, Tag.PARAGRAPH_TYPE, element));
                    order += 1;
                }
            }
        }
    }

    public Article(String data, boolean isURL){
        this.tags = new ArrayList<>();
        parseDocument(data, isURL);
        generateTags();
    }
}