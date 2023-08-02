/**
 * This class is responsible for functioning as the vessel for a vending machine.
 *
 * @author Group 2(Flores, Ranigo)
 */
public class VendingMachine {
    //Constructors

    /**
     * This construction initializes the vending machine slots and creates a new case register.
     * @param slots Slots for the vending machine.
     */
    VendingMachine( Slot[] slots ){
        this.slots = slots;
        cashregister = new CashRegister();
    }

    //Methods

    /**
     * This method is responsible for printing the feature menu and the balance of the user and the vending machine.
     */
    public void printFeatureMenu(){
        System.out.println( "-------------------------------------------" );
        System.out.println( "-------------------------------------------"+"\n\n");
        System.out.println(  "Current Balance: "+cashregister.getUserMoney());
        System.out.println(  "Vending Machine Balance: "+cashregister.getUserMoney()+"\n");
        System.out.println( "(1) Show Products" );
        System.out.println( "(2) Buy" );
        System.out.println( "(3) Enter Money" );
        System.out.println( "(4) Give Change" );
        System.out.println( "(0) Exit" );
        System.out.println( "\n\n-------------------------------------------" );
        System.out.println( "-------------------------------------------\n\n" );
    }

    /**
     * This method is responsible for printing the current products and its details in the vending machine.
     */
    public void printProducts(){
        int i;
        System.out.println( "-------------------------------------------" );
        System.out.println( "-------------------------------------------" );
        for( i = 0 ; i < slots.length ; i++){
            System.out.println( "Slot #"+(i+1) );
            if( !slots[i].isSlotEmpty() ){
                System.out.println( "Name: "+slots[i].getItem().getName() );
                System.out.println( "Price: "+slots[i].getItem().getPrice() );
                System.out.println( "Calories: "+slots[i].getItem().getCalories() );
                System.out.println( "Quantity: "+slots[i].getItemAmount() );
                System.out.println( "-------------------------------------------" );
            }
            else{
                System.out.println( "Slot is Empty!");
                System.out.println( "-------------------------------------------" );
            }
        }
        System.out.println( "-------------------------------------------\n\n" );
    }

    /**
     * This method is responsible for printing the buying of a item from a slot.
     */
    public void printBuyScreen(){
        System.out.println( "-------------------------------------------" );
        System.out.println( "-------------------------------------------"+"\n\n");
        System.out.println( "Please Enter the Slot Number" );
        System.out.println( "of the Item You Wish to Purchase" );
        System.out.println( "\n\n-------------------------------------------" );
        System.out.println( "-------------------------------------------\n\n" );
    }

    /**
     * This method prints the attributes of the item selected and the confirmation for buying that item.
     * @param index The slot number selected.
     * @return True if item exits, false otherwise.
     */
    public boolean printConfirmation(int index) {
        if (slots[index].getItem() == null) {
            System.out.println("Item Does not Exist!");
            return false;
        }
        else {
            System.out.println("-------------------------------------------");
            System.out.println("-------------------------------------------");
            System.out.println("Name: " + slots[index].getItem().getName());
            System.out.println("Price: " + slots[index].getItem().getPrice());
            System.out.println("Calories: " + slots[index].getItem().getCalories());
            System.out.println("Quantity: " + slots[index].getItemAmount() + "\n\n");
            System.out.println("Current Balance: " + cashregister.getUserMoney() +"\n\n");
            System.out.println("Are you sure with your Purchase?");
            System.out.println("(1) Yes");
            System.out.println("(2) No");
            System.out.println("\n\n-------------------------------------------");
            System.out.println("-------------------------------------------\n\n");
            return true;
        }
    }

    /**
     * This method prints the types of denominations to be inserted.
     */
    public void enterMoneyMenu( ){
        System.out.println( "-------------------------------------------" );
        System.out.println( "-------------------------------------------"+"\n\n");
        System.out.println( "Current Amount: "+cashregister.getUserMoney() );
        System.out.println( "(1) Enter 1 Peso Coin " );
        System.out.println( "(2) Enter 5 Peso Coin " );
        System.out.println( "(3) Enter 10 Peso Coin " );
        System.out.println( "(4) Enter 20 Peso Coin " );
        System.out.println( "(5) Enter 20 Peso Bill " );
        System.out.println( "(6) Enter 50 Peso Bill " );
        System.out.println( "(7) Enter 100 Peso Bill " );
        System.out.println( "(8) Enter 200 Peso Bill " );
        System.out.println( "(9) Enter 500 Peso Bill " );
        System.out.println( "(10) Enter 1000 Peso Bill " );
        System.out.println( "(0) Exit " );
        System.out.println("\n\n-------------------------------------------");
        System.out.println("-------------------------------------------\n\n");
    }
//////////////////////Will this method be used?
    public void enterAmountMenu( ){
        System.out.println( "-------------------------------------------" );
        System.out.println( "-------------------------------------------"+"\n\n");
        System.out.println( "Enter Amount:  "+"\n\n");
        System.out.println("\n\n-------------------------------------------");
        System.out.println("-------------------------------------------\n\n");
    }


    //Maintenance Menu Methods

    /**
     * This method prints the prompt for logging in to maintenance.
     */
    public void maintenanceLogin( ){
        System.out.println( "-------------------------------------------" );
        System.out.println( "-------------------------------------------"+"\n\n");
        System.out.println("Please Enter Password: ");
        System.out.println("\n\n-------------------------------------------" );
        System.out.println("-------------------------------------------\n\n" );
    }

    /**
     * This method prints the menu for the maintenance features.
     */
    public void maintenanceMenu(  ){
        System.out.println( "-------------------------------------------" );
        System.out.println( "-------------------------------------------"+"\n\n");
        System.out.println("Welcome to Maintenance Menu");
        System.out.println("(1) Restock Products");
        System.out.println("(2) Stock Products");
        System.out.println("(3) Set Price");
        System.out.println("(4) Collect Payment");
        System.out.println("(5) Restock Money");
        System.out.println("(6) Print Transactions");
        System.out.println("(0) Exit Menu");
        System.out.println("\n\n-------------------------------------------" );
        System.out.println("-------------------------------------------\n\n" );
    }

    /**
     * This method prints the prompt for restocking items in a slot number.
     */
    public void restockProductsMenu( ){
        System.out.println( "-------------------------------------------" );
        System.out.println( "-------------------------------------------"+"\n\n" );
        System.out.println( "Restock Items" );
        System.out.println( "Enter Slot Number: " );
        System.out.println("\n\n-------------------------------------------" );
        System.out.println("-------------------------------------------\n\n" );
    }

    /**
     * This method prints the name and quantity of the current item in the slot and the quantity to be added.
     * @param index The index number of the slot.
     * @return The index number as well.
     */
    public int restockProductsConfirm( int index ){
        System.out.println( "-------------------------------------------" );
        System.out.println( "-------------------------------------------"+"\n\n" );
        System.out.println( "Product Name: "+slots[index].getItem().getName() );
        System.out.println( "Quantity: "+slots[index].getItemAmount() );
        System.out.println( "\n\nEnter Quantity to be Added: " );
        System.out.println("\n\n-------------------------------------------" );
        System.out.println("-------------------------------------------\n\n" );
        return index;
    }


    //Getters

    /**
     * This method returns the slots of the vending machine.
     * @return Vending machine slots.
     */
    public Slot[] getSlots(){return slots;}

    /**
     * This method returns the cash register of the vending machine.
     * @return Vending machine cash register.
     */
    public CashRegister getCashregister(){return cashregister;}

    protected Slot[] slots;
    private CashRegister cashregister;
}
