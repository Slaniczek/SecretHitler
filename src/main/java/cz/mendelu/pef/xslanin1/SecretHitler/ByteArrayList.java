package cz.mendelu.pef.xslanin1.SecretHitler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ByteArrayList<T> {
    private List<T> items = new ArrayList<T>();

    public void addItem(T item){
        items.add(item);
    }

    public void removeItem(byte id){
        items.remove(id);
    }

    public T getItem(byte id){
        return items.get((int) id);
    }

    public List<T> getItems() {
        return items;
    }

    int getCount(){
        return items.size();
    }

    void shuffle(){
        Collections.shuffle(items);
    }
}
