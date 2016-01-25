package dnldd.backpack.model;


import java.util.ArrayList;
import java.util.List;

import dnldd.backpack.core.BaseApplication;
import dnldd.backpack.viewholder.ViewHolder;

public class Contact extends Entity {
    protected String name;
    protected List<String> numbers;
    protected String thumbnailURI;

    public Contact(String name, String number, String thumbnailURI) {
        this.name = name;
        this.numbers = new ArrayList<String>();
        this.thumbnailURI = thumbnailURI;
        this.numbers.add(number);
    }

    public String getName(){ return name; }
    public List<String> getNumbers() { return numbers; }
    public String getFirstNumber() { return numbers.get(0); }
    public String getThumbnailURI() { return thumbnailURI; }
    public void setThumbnailURI(String thumbnailURI){
        this.thumbnailURI = thumbnailURI;
    }

    public void addNumber(String number){
        String sanitizedNumber = number.replaceAll("\\s+","");
        Boolean found = false;

        for (String entry : numbers) {
            if(entry.equals(sanitizedNumber)){
                found = true;
                break;
            }
        }

        if(!found){ numbers.add(sanitizedNumber); }
    }

    @Override
    public void bindItem(BaseApplication app, ViewHolder viewHolder) {}
}
