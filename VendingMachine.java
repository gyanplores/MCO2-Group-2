/**
 * This class is responsible for the main features of the vending machine.
 *
 * @author Flores, Ranigo
 */
public class VendingMachine {
    //Constructor

    /**
     * This constructor initializes the main characteristics for a vending machine.
     * The constructor takes in a name and the created slots from the Slot class.
     * Other characteristics such as the bills, coins, and the change amount are initialized.
     *
     * @param name Name of the vending maching
     * @param slots Item slots composed of an item and its size
     */
    public VendingMachine( String name, Slot[] slots ){
        this.name = name;
        this.slots = slots;
        cashRegister = new CashRegister();
        twentyCoin = 5;
        tenCoin = 0;
        fiveCoin = 0;
        onesCoin = 0;
        bills100 = 0;
        bills50 = 0;
        userMoney = 0;
        currentSales = 0;
        changeAmount = (twentyCoin * 20) + (tenCoin * 10) + (fiveCoin * 5) + (onesCoin);
    }


    //Methods

    /**
     * Displays the feature options of a vending machine. It shows possible interactions such as showing products, buying, entering money, and getting change.
     *
     */
    public void printFeatureMenu(){
        System.out.println( "-------------------------------------------" );
        System.out.println( "-------------------------------------------"+"\n\n");
        System.out.println(  "Current Balance: "+userMoney);
        System.out.println(  "Vending Machine Balance: "+changeAmount+"\n");
        System.out.println( "(1) Show Products" );
        System.out.println( "(2) Buy" );
        System.out.println( "(3) Enter Money" );
        System.out.println( "(4) Give Change" );
        System.out.println( "(0) Exit" );
        System.out.println( "\n\n-------------------------------------------" );
        System.out.println( "-------------------------------------------\n\n" );
    }

    /**
     * Displays the characteristics of each item. It shows its name, price, calories, and quantity.
     */
    public void printProducts(){
        int i;
        System.out.println( "-------------------------------------------" );
        System.out.println( "-------------------------------------------" );
        for( i = 0 ; i < slots.length ; i++){
            System.out.println( "Slot #"+(i+1) );
            if( slots[i].getItem() != null ){
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
     * Displays what slot number is chosen for buying an item.
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
     * Checks if the user has enough money to buy the chosen product.
     *
     * @param index Index for a slot item.
     * @return True if user's money is greater, false otherwise.
     */
    public boolean priceCheck( int index ){
        return userMoney >= slots[index].getItem().getPrice();
    }

    /**
     * Checks if the item quantity is greater than 0.
     *
     * @param index Index for a slot item.
     * @return True if greater than 0, false otherwise.
     */
    public boolean quantityCheck( int index ){
        return slots[index].getItemAmount() > 0;
    }

    /**
     * Checks if the vending machine has enough money to give change to the user
     * @param index Index of the selected item.
     * @return True if process is successful, false otherwise.
     */
    public boolean changeCheck( int index ){
        int[] denom;
        int total;
        denom = coinConverter(Math.abs(userMoney - slots[index].getItem().getPrice()));
        total = (denom[0]*20) + (denom[1]*10) + (denom[2]*5) + (denom[3]);

        if(Math.abs(userMoney - slots[index].getItem().getPrice()) < changeAmount){
            if( total == Math.abs(userMoney - slots[index].getItem().getPrice()) ){
                return true;
            }
            else{
                return false;
            }
        }else{
            return false;
        }

    }

    /**
     * Checks if the item exists and displays the chosen item's characteristics for confirmation on buying.
     *
     * @param index Index for a slot item.
     * @return True if item does exist, false otherwise
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
            System.out.println("Current Balance: " + userMoney +"\n\n");
            System.out.println("Are you sure with your Purchase?");
            System.out.println("(1) Yes");
            System.out.println("(2) No");
            System.out.println("\n\n-------------------------------------------");
            System.out.println("-------------------------------------------\n\n");
            return true;
        }
    }

    /**
     * Displays previous transaction and the sales profit. Also displays the starting inventory and the current inventory.
     */
    public void printTransactions(){
        int i;
        System.out.println( "-------------------------------------------" );
        System.out.println( "Summary of Previous Transactions" );
        System.out.println( "-------------------------------------------" );
        System.out.println( "Sales-Sold-Inv -  Name   -    Price");

        for(i = 0; i < slots.length; i++){
            if(slots[i].getItem()==null){
                System.out.println( 0 +"    "+
                        0 +"    "+
                        0 +"    "+
                        "Item not stored" +" "+
                        "N/A");}
            else{System.out.println( itemSales(i) +"    "+
                    slots[i].getItem().getQtySold() +"    "+
                    slots[i].getItemAmount() +"    "+
                    slots[i].getItem().getName() +"    "+
                    slots[i].getItem().getPrice());}
        }
        System.out.println( "-------------------------------------------" );
        System.out.println( "Total Amount Collected: "+ currentSales );
        System.out.println( "-------------------------------------------" );
        System.out.println( "Current inventory" );
        System.out.println( "-------------------------------------------" );
        System.out.println( "Start-Cur - Name" );

        for(i = 0; i < slots.length; i++){
            if(slots[i].getItem()==null){
                System.out.println(
                        0 +"      "+ 0 +"    "+ "Item not stored");}
            else{System.out.println(
                    slots[i].getItemAmount()+slots[i].getItem().getQtySold() +"      "+
                            slots[i].getItemAmount() +"    "+
                            slots[i].getItem().getName());}
        }
    }

    /**
     * Computes for the sales by multiplying quantity sold and its price.
     * @param index Index for a slot item.
     * @return Money resulted from its sales
     */
    public float itemSales(int index){
        if(slots[index].getItem().getQtySold()<=0)
            return 0;
        return slots[index].getItem().getQtySold() * slots[index].getItem().getPrice();
    }

    /**
     * This method is connected with collecting payments. It counts how much 100 peso bills and 50 peso bills that can be collected.
     * @param amount Amount of money t be converted.
     * @return The converted money in bills.
     */
    public int[] billConverter( float amount ){
        int[] bills = new int[2];
        int[] values = new int[2];
        int[] placeholder = new int[2];
        int i;

        boolean key;

        values[0] = 100;
        values[1] = 50;
        placeholder[0] = bills100;
        placeholder[1] = bills50;

        for( i = 0; i < 2 ; i++ ){
            key = true;
            while( amount >= 1 && key){
                bills[i] = (int)amount / values[i];
                if (bills[i] > placeholder[i] || bills[i] < 1) {
                    bills[i] = 0;
                    key = false;
                } else {
                    amount = amount - bills[i] * values[i];
                    key = false;
                }
            }
        }

        return bills;
    }

    /**
     * This method is connected with collecting payments. It converts the remaining amount of money into its corresponding denominations.
     * @param amount Amount of money to be converted.
     * @return The converted money in denominations.
     */
    public int[] coinConverter( float amount ){
        int[] coins = new int[4];
        int[] values = new int[4];
        int[] placeholder = new int[4];
        int i;

        boolean key;

        values[0] = 20;
        values[1] = 10;
        values[2] = 5;
        values[3] = 1;
        placeholder[0] = twentyCoin;
        placeholder[1] = tenCoin;
        placeholder[2] = fiveCoin;
        placeholder[3] = onesCoin;


        for( i = 0 ; i < 4 ; i++ ){
            key = true;
            while( amount >= 1 && key) {
                coins[i] = (int)amount / values[i];
                if (coins[i] > placeholder[i] || coins[i] < 1) {
                    coins[i] = 0;
                    key = false;
                } else {
                    amount = amount - coins[i] * values[i];
                    key = false;
                }
            }
        }
        return coins;
    }

    /**
     * Subtracts the user's money and add total sales money as one buys an item.
     * @param index Index for a slot item.
     */
    public void buyItem( int index ){
        userMoney = userMoney - slots[index].getItem().getPrice();
        slots[index].removeItem();
        currentSales = currentSales + slots[index].getItem().getPrice();

    }

    /**
     * Displays the current balance of users and selections for inserting money.
     */
    public void enterMoneyMenu( ){
        System.out.println( "-------------------------------------------" );
        System.out.println( "-------------------------------------------"+"\n\n");
        System.out.println( "Current Amount: "+userMoney );
        System.out.println( "(1) Enter Bill " );
        System.out.println( "(2) Enter 20 Peso Coin " );
        System.out.println( "(3) Enter 10 Peso Coin " );
        System.out.println( "(4) Enter 5 Peso Coin " );
        System.out.println( "(5) Enter 1 Peso Coin " );
        System.out.println( "(0) Exit " );
        System.out.println("\n\n-------------------------------------------");
        System.out.println("-------------------------------------------\n\n");
    }

    /**
     * Displays the selection for inserting bills.
     */
    public void enterBillsMenu( ){
        System.out.println( "-------------------------------------------" );
        System.out.println( "-------------------------------------------"+"\n\n");
        System.out.println( "Enter Bill Amount:  "+"\n\n");
        System.out.println( "Please Note that the Vending Machine" );
        System.out.println( "Only Accepts 50 and 100 Peso Bills" );
        System.out.println("\n\n-------------------------------------------");
        System.out.println("-------------------------------------------\n\n");
    }

    /**
     * Increases the user's balance based from money received.
     * @param amount Amount of money(Bills).
     * @return True if process was successful, false otherwise.
     */
    public boolean enterBills( int amount ){
        if( amount > changeAmount ){
            System.out.println("Vending Machine does not have");
            System.out.println("enough change for this Amount");
            return false;
        }
        else if( amount == 100 ){
            userMoney = userMoney + amount;
            bills100++;
            return true;
        }else if( amount == 50){
            userMoney = userMoney + amount;
            bills50++;
            return true;
        }else{
            System.out.println("Invalid Amount!");
            return false;
        }
    }

    /**
     * Increases the user's balance and change money base on a selected choice(Coins).
     * @param select Option selected for entering coins.
     * @return True if process was successful, false otherwise.
     */
    public boolean enterCoins( int select ){
        switch( select ){
            case 2:
                twentyCoin++;
                userMoney = userMoney + 20;
                changeAmount = changeAmount + 20;
                return true;
            case 3:
                tenCoin++;
                userMoney = userMoney + 10;
                changeAmount = changeAmount + 10;
                return true;
            case 4:
                fiveCoin++;
                userMoney = userMoney + 5;
                changeAmount = changeAmount + 5;
                return true;
            case 5:
                onesCoin++;
                userMoney = userMoney + 1;
                changeAmount = changeAmount + 1;
                return true;
            case 0:
                return false;
            default:
                System.out.println("Invalid Choice");
                return true;
        }
    }

    /**
     * Displays confirmation on receiving change.
     */
    public void giveChangeConfirm(  ) {
        System.out.println("-------------------------------------------");
        System.out.println("-------------------------------------------" + "\n\n");
        System.out.println("Are you sure you wish to receive your change?");
        System.out.println("(1) Yes");
        System.out.println("(2) No");
        System.out.println("\n\n-------------------------------------------" );
        System.out.println("-------------------------------------------\n\n" );
    }

    /**
     * Involves dispensing change for the user.
     */
    public void giveChange( ){
        int[] denom;
        denom = coinConverter( userMoney );
        System.out.println( "-------------------------------------------" );
        System.out.println( "-------------------------------------------"+"\n\n" );
        System.out.println( "You have received: ");
        System.out.println( denom[0]+" Amount of 20 Peso Coins");
        System.out.println( denom[1]+" Amount of 10 Peso Coins");
        System.out.println( denom[2]+" Amount of 5 Peso Coins");
        System.out.println( denom[3]+" Amount of 1 Peso Coins");
        System.out.println( "Total Change: "+userMoney );
        System.out.println("\n\n-------------------------------------------" );
        System.out.println("-------------------------------------------\n\n" );
        userMoney = 0;
        twentyCoin = twentyCoin - denom[0];
        tenCoin = tenCoin - denom[1];
        fiveCoin = fiveCoin - denom[2];
        onesCoin = onesCoin - denom[3];
        changeAmount = changeAmount - ((denom[0]*20) + (denom[1]*10) + (denom[2]*5) + (denom[3]));
    }

    /**
     * Displays the login for maintenance.
     */
    public void maintenanceLogin( ){
        System.out.println( "-------------------------------------------" );
        System.out.println( "-------------------------------------------"+"\n\n");
        System.out.println("Please Enter Password: ");
        System.out.println("\n\n-------------------------------------------" );
        System.out.println("-------------------------------------------\n\n" );
    }

    /**
     * Displays the options for doing maintenance on the vending machine.
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
     * Displays the text for selecting what slot/item to restock.
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
     * Displays item characteristics for the restock option in maintenance.
     * @param index Index for a slot item.
     * @return The index of the slot item.
     */
    public int restockProductsConfirm( int index ){
        if(slots[index].isSlotEmpty()){
        System.out.println( "-------------------------------------------" );
        System.out.println( "-------------------------------------------"+"\n\n" );
        System.out.println( "Product Name: "+slots[index].getItem().getName() );
        System.out.println( "Quantity: "+slots[index].getItemAmount() );
        System.out.println( "\n\nEnter Quantity to be Added: " );
        System.out.println("\n\n-------------------------------------------" );
        System.out.println("-------------------------------------------\n\n" );
        return index;}
        else{
            return -1;
        }
    }

    /**
     * Restocks the quantity received to the selected slot.
     * @param index Index for a slot item.
     * @param quantity Quantities of the item that will be restocked.
     * @return True if process is successful, false otherwise
     */
    public boolean restockProductsFinal( int index,  int quantity ){
        if( slots[index].getItemAmount() <= 10 ){
            slots[index].restockItem(quantity);
            return true;
        }else{
            return false;
        }
    }

    /**
     * Displays the text on what slot will be stocked.
     */
    public void stockProductsMenu( ){
        System.out.println( "-------------------------------------------" );
        System.out.println( "-------------------------------------------"+"\n\n" );
        System.out.println( "Stock Items" );
        System.out.println( "Enter Slot Number: " );
        System.out.println( "Note that this option will replace" );
        System.out.println( "the selected Slot item" );
        System.out.println("\n\n-------------------------------------------" );
        System.out.println("-------------------------------------------\n\n" );
    }

    /**
     * Displays the text for entering the characteristics of the item. It will also display what item will be replaces.
     * @param index Index for a slot item.
     * @return The index of the slot item
     */
    public int stockProductsConfirm( int index ){
        String nameProd;
        if( slots[index].getItem() == null ){
            nameProd = "None";
        }else{
            nameProd = slots[index].getItem().getName();
        }
        System.out.println( "-------------------------------------------" );
        System.out.println( "-------------------------------------------"+"\n\n" );
        System.out.println( "Product to be Replaced: "+nameProd );
        System.out.println( "\n\nEnter Product Name of new Item: " );
        System.out.println( "Enter Price of new Item: " );
        System.out.println( "Enter Calories of new Item: " );
        System.out.println("\n\n-------------------------------------------" );
        System.out.println("-------------------------------------------\n\n" );
        return index;
    }

    /**
     * This method is responsible for stocking a new item in a slot.
     * @param index The index of the slot.
     * @param item The new item to be stored.
     * @param amount The amount to be stored.
     */
    public void stockProductsFinal( int index, Item item, int amount){
       int i;
        if(!slots[index].isSlotEmpty()){
           for(i = 0; i < slots[index].getItemAmount(); i++){
               slots[index].removeItem();
           }
       }
        slots[index].addItem( item, amount);
    }

    /**
     * Displays the text to enter on what slot to change its prices.
     */
    public void changePriceMenu( ){
        System.out.println( "-------------------------------------------" );
        System.out.println( "-------------------------------------------"+"\n\n" );
        System.out.println( "Change Price" );
        System.out.println( "Enter Slot Number: " );
        System.out.println("\n\n-------------------------------------------" );
        System.out.println("-------------------------------------------\n\n" );
    }

    /**
     * Displays the text to enter the new price and product to be modified.
     * @param index Index for a slot item.
     */
    public void changePriceMenuConfirm( int index ){
        System.out.println( "-------------------------------------------" );
        System.out.println( "-------------------------------------------"+"\n\n" );
        System.out.println( "Product to be Modified: "+slots[index].getItem().getName() );
        System.out.println( "Enter New Price: " );
        System.out.println("\n\n-------------------------------------------" );
        System.out.println("-------------------------------------------\n\n" );
    }

    /**
     * Process that will change the price of a selected slot.
     * @param index Index for a slot item.
     * @param price New price of the item.
     */
    public void changePriceMenuFinal( int index, float price ){
        slots[index].getItem().setPrice(price);
    }

    /**
     * Displays the amount of money collected from the vending machine.
     * @param bills Bills of a specific type(100 Peso, 50 Peso)
     * @param coins Coins of each type (20, 10, 5, 1 Peso coins)
     * @param amount Quantity of a specific money.
     */
    public void collectPaymentMenu( int[] bills , int[] coins, float amount){
        System.out.println( "-------------------------------------------" );
        System.out.println( "-------------------------------------------"+"\n\n" );
        System.out.println( "BILLS" );
        System.out.println( "Amount of 100 Peso Bills: "+bills[0] );
        System.out.println( "Amount of 50 Peso Bills: "+bills[1]+"\n\n" );
        System.out.println( "COINS" );
        System.out.println( "Amount of 20 Peso Coins: "+coins[0] );
        System.out.println( "Amount of 10 Peso Coins: "+coins[1] );
        System.out.println( "Amount of 5 Peso Coins: "+coins[2] );
        System.out.println( "Amount of 1 Peso Coins: "+coins[3]+"\n\n");
        System.out.println( "TOTAL PAYMENT COLLECTED: "+amount );
        System.out.println("\n\n-------------------------------------------" );
        System.out.println("-------------------------------------------\n\n" );
    }

    /**
     * Process for cellecting the money from the vending machine.
     */
    public void collectPayment(  ){
        int[] bills;
        int[] coins;
        float sales = currentSales;
        bills = billConverter( sales );
        sales = sales - ((bills[0]*100) + (bills[1]*50));
        coins = coinConverter(sales);
        sales = sales - ((coins[0]*20) + (coins[1]*10) + (coins[2]*5) + (coins[3]));
        collectPaymentMenu( bills, coins, currentSales );

        bills100 = bills100 - bills[0];
        bills50 = bills50 - bills[1];
        twentyCoin = twentyCoin - coins[0];
        tenCoin = tenCoin - coins[1];
        fiveCoin = fiveCoin - coins[2];
        onesCoin = onesCoin - coins[3];
        changeAmount = changeAmount - ((coins[0]*20) + (coins[1]*10) + (coins[2]*5) + (coins[3]));
    }

    /**
     * Displays the options for restocking money.
     */
    public void restockMoneyMenu( ){
        System.out.println( "-------------------------------------------" );
        System.out.println( "-------------------------------------------"+"\n\n" );
        System.out.println( "Replenish Vending Machine Change");
        System.out.println( "20 Peso Coin: "+twentyCoin);
        System.out.println( "10 Peso Coin: "+tenCoin);
        System.out.println( "5 Peso Coin: "+fiveCoin);
        System.out.println( "1 Peso Coin: "+onesCoin);
        System.out.println("Total Change: "+changeAmount);
        System.out.println( "\n\nSelect the Denomination: " );
        System.out.println( "(1) Twenty Peso Coin");
        System.out.println( "(2) Ten Peso Coin");
        System.out.println( "(3) Five Peso Coin");
        System.out.println( "(4) One Peso Coin");
        System.out.println( "(0) Ext Menu");
        System.out.println("\n\n-------------------------------------------" );
        System.out.println("-------------------------------------------\n\n" );
    }

    /**
     * Displays' for selecting what denomination to be restocked
     * @param select Option selected.
     */
    public void restockMoneyDenomination( int select ){
        System.out.println( "-------------------------------------------" );
        System.out.println( "-------------------------------------------"+"\n\n" );
        switch(select){
            case 1:
                System.out.println( "Enter Amount of Twenty Peso Coin to be Added: ");
                break;
            case 2:
                System.out.println( "Enter Amount of Ten Peso Coin to be Added: ");
                break;
            case 3:
                System.out.println( "Enter Amount of Five Peso Coin to be Added: ");
                break;
            case 4:
                System.out.println( "Enter Amount of One Peso Coin to be Added: ");
                break;
        }
        System.out.println("\n\n-------------------------------------------" );
        System.out.println("-------------------------------------------\n\n" );
    }

    /**
     * Process for restocking money in the vending machine.
     * @param select Option selected.
     * @param amount Quantity of the selected money(denomination)
     */
    public void restockMoneyFinal( int select, int amount ){
        switch(select){
            case 1:
                twentyCoin = twentyCoin + amount;
                changeAmount = changeAmount + (amount * 20);
                break;
            case 2:
                tenCoin = tenCoin + amount;
                changeAmount = changeAmount + (amount * 10);
                break;
            case 3:
                fiveCoin = fiveCoin + amount;
                changeAmount = changeAmount + (amount * 5);
                break;
            case 4:
                onesCoin = onesCoin + amount;
                changeAmount = changeAmount + amount;
                break;
        }
    }

    //Getters

    /**
     * The method returns the name of the vending machine.
     * @return Vending machine name.
     */
    public String getName() { return name; }

    /**
     * The methods returns the slots of the vending machine.
     * @return Vending machine slots.
     */
    public Slot[] getSlots() { return slots; }

    /**
     * The method returns the 20 coin.
     * @return 20 coin denomination
     */
    public int getTwentyCoin() { return twentyCoin; }

    /**
     * The method returns the 10 coin.
     * @return 10 coin denomination.
     */
    public int getTenCoin(){ return tenCoin;}

    /**
     * The method returns the 5 coin.
     * @return 5 coin denomination.
     */
    public int getFiveCoin(){ return fiveCoin; }

    /**
     * The method returns the 1 coin.
     * @return 1 coin denomination.
     */
    public int getOnesCoin(){ return onesCoin; }

    /**
     * The method returns 50 peso bills.
     * @return 50 peso bills.
     */
    public int getBills50(){ return bills50; }

    /**
     * The method returns 100 peso bills.
     * @return 100 peso bills.
     */
    public int getBills100(){ return bills100; }


    //Attributes
    private Slot[] slots;
    private String name;
    private int twentyCoin;
    private int tenCoin;
    private int fiveCoin;
    private int onesCoin;
    private int bills50;
    private int bills100;
    private float userMoney;
    private float currentSales;
    private float changeAmount;
    private CashRegister cashRegister;
}
