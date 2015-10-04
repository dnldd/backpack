package dnldd.backpack.model;

import org.jsoup.nodes.Element;

import dnldd.backpack.core.BaseApplication;
import dnldd.backpack.viewholder.ViewHolder;

public class Tag extends Entity {
    /* tags */
    public static String H1 = "<h1>";
    public static String H2 = "<h2>";
    public static String H3 = "<h3>";
    public static String H4 = "<h4>";
    public static String H5 = "<h5>";
    public static String H6 = "<h6>";
    public static String UNORDERED_LIST = "<ul>";
    public static String ORDERED_LIST = "<ol>";
    public static String DESCRIPTION_LIST = "<dl>";
    public static String PREFORMATTED_TEXT = "<pre>";
    public static String ADDRESS = "<address>";
    public static String IMAGE = "<img>";
    public static String BLOCKQUOTE = "<blockquote>";
    public static String PARAGRAPH = "<p>";

    /* tag view types */
    public static final int H1_TYPE = 1;
    public static final int H2_TYPE = 2;
    public static final int H3_TYPE = 3;
    public static final int H4_TYPE = 4;
    public static final int H5_TYPE = 5;
    public static final int H6_TYPE = 6;
    public static final int UNORDERED_LIST_TYPE = 7;
    public static final int ORDERED_LIST_TYPE = 8;
    public static final int DESCRIPTION_LIST_TYPE = 9;
    public static final int PREFORMATTED_TEXT_TYPE = 10;
    public static final int ADDRESS_TYPE = 11;
    public static final int IMAGE_TYPE = 12;
    public static final int BLOCKQUOTE_TYPE = 13;
    public static final int PARAGRAPH_TYPE = 14;

    protected int order;
    protected int type;
    protected Element element;

    public Tag(int order, int type, Element element){
        this.order = order;
        this.type = type;
        this.element = element;
    }

    @Override
    public void bindItem(BaseApplication app, ViewHolder viewHolder) {
        /* bind all widgets for every tag type here */
        switch (type) {
            case H1_TYPE: {} break;
            case H2_TYPE: {} break;
            case H3_TYPE: {} break;
            case H4_TYPE: {} break;
            case H5_TYPE: {} break;
            case H6_TYPE: {} break;
            case UNORDERED_LIST_TYPE: {} break;
            case ORDERED_LIST_TYPE: {} break;
            case DESCRIPTION_LIST_TYPE: {} break;
            case PREFORMATTED_TEXT_TYPE: {} break;
            case ADDRESS_TYPE: {} break;
            case IMAGE_TYPE: {} break;
            case BLOCKQUOTE_TYPE: {} break;
            case PARAGRAPH_TYPE: {} break;
            default: {}
        }
    }
}
