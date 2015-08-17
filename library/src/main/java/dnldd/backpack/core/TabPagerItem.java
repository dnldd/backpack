package dnldd.backpack.core;

public class TabPagerItem {
    protected final String title;
    protected final int indicatorColor;
    protected final int dividerColor;

    public TabPagerItem(String title, int indicatorColor, int dividerColor) {
        this.title = title;
        this.indicatorColor = indicatorColor;
        this.dividerColor = dividerColor;
    }

    public String getTitle() { return title; }
    public int getIndicatorColor() { return indicatorColor; }
    public int getDividerColor() { return dividerColor; }
}