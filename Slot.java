import java.util.ArrayList;

public class Slot {
    //Constructors
    Slot(){
        items = new ArrayList<>();
    }

    //Methods
    public void addItem( Item item, int amount ){
        int i;

        for( i = 0 ; i < amount ; i++ ){
            items.add(item);
        }

    }

    public boolean isSlotEmpty( ){
        return items.isEmpty();
    }

    //Getters
    public Item getItem(){ return items.get(0); }
    public int getItemAmount(){ return items.size(); }

    //Attributes
    private ArrayList<Item> items;
}
