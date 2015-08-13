package dnldd.backpack.utils;

public class NotificationExtra {
    protected String key;
    protected String value;

    public String getKey(){ return key; }
    public String getValue(){ return value; }

    public NotificationExtra(String key, String value){
        this.key = key;
        this.value = value;
    }
}
