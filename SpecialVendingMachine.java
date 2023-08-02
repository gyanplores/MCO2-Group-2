public class SpecialVendingMachine extends VendingMachine {
    public SpecialVendingMachine(Slot[] slots){
        super(slots);
    }

    public double specialCustomizationCalories(int index, int quantity){
       int i, j;
       float calories = 0;
       for(i = 0; i < getSlots().length; i++){
            if(slots[index]==slots[i]){
               calories = calories + slots[i].getItem().getCalories() * quantity;
            }
       }
       return calories;
    }

    public boolean isIndependentItem(int index){
        return slots[index].getItem()//.getIndependent?
    }

    //dispense one item at a time
    public void dispenseSpecialItem(int choice){ //1-Carbonara, 2-Meatball Spaghetti, 3-Lasagna // Reduces the items involved
        /*Is beef bits beef meat? and where bacon */
        switch(choice){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
    private float specialCalories;
}