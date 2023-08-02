import java.util.ArrayList;

/**
 * This class is responsible for the slots of the vending machine. This is the object where items will be stored in.
 *
 * @author Group 2(Flores, Ranigo)
 */
public class Slot {
    //Constructors

    /**
     * The constructor initializes a new array list of items for the vending machine slot.
     */
    Slot(){
        items = new ArrayList<>();
    }

    //Methods

    /**
     * This method is responsible for adding items inside the slot.
     * @param item The type of item that will be stored.
     * @param amount The amount of items to be stored inside the vending machine.
     */
    public void addItem( Item item, int amount ){
        int i;

        for( i = 0 ; i < amount ; i++ ){
            items.add(item);
        }

    }

    public void restockItem(int amount){
        int i;

        for(i = 0 ; i < amount ; i++){
            items.add(getItem());
        }
    }

    /**
     * This method removes an item from the slot.
     */
    public void removeItem( ){
        items.remove(items.size()-1);
    }

    /**
     * This method is responsible for checking if the slot is empty.
     * @return True if slot is empty, false otherwise.
     */
    public boolean isSlotEmpty( ){
        return items.isEmpty();
    }

    //Getters

    /**
     * This method returns the item.
     * @return Item inside the slot, null if none.
     */
    public Item getItem(){
        if(items.size() > 0){
            return items.get(0);
        }
        else{
            return null;
        }
    }


    /**
     * This method returns the amount of items inside the slot.
     * @return Item amount in the slot
     */
    public int getItemAmount(){ return items.size(); }

    //Attributes
    private ArrayList<Item> items;
}
