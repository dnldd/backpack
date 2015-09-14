package dnldd.backpack.model;

import dnldd.backpack.core.BaseApplication;
import dnldd.backpack.viewholder.ViewHolder;

public class Tag extends Entity {
    protected int order;
    protected int type;
    protected String content;

    public Tag(int order, int type, String content){
        this.order = order;
        this.type = type;
        this.content = content;
    }

    @Override
    public void bindItem(BaseApplication app, ViewHolder viewHolder) {
        /* bind all widgets for every tag type here */
    }
}
