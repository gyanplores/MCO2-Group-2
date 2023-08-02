import java.util.Scanner;

public class VendingMachineDriver {
    public static void main(String[] args){
        int menu, feature, vendingMachineChoice, testMachine, pass, maintenanceMenu;
        boolean key;
        Item[] specitems = new Item[10];
        Slot[] specslots = new Slot[10];

        Scanner scan = new Scanner(System.in);

        VendingMachine one = null;

        printWelcome();
        scan.nextLine();
        do {
            printMenu();
            menu = scan.nextInt();
            switch (menu){
            case 1:
                do {
                    printCreate();
                    vendingMachineChoice = scan.nextInt();
                    switch (vendingMachineChoice) {
                        case 1:             //prototype, lacks conditions and condition loop
                            one = createRegularVendingMachine(scan, one);
                            break;
                        case 2:
                            VendingMachineController vendingMachineController;

                            VendingMachineView vendingMachineView = new VendingMachineView();
                            specitems[0] = new Item();
                            specitems[1] = new Item("Spaghetti Pasta", 40, 150);
                            specitems[2] = new Item("Ridged Pasta", 35, 130);
                            specitems[3] = new Item("Fusilli Pasta", 40, 160);
                            specitems[4] = new Item("Red Sauce", 20, 80);
                            specitems[5] = new Item("White Sauce", 30, 90);
                            specitems[6] = new Item("Pesto Sauce", 50, 100);
                            specitems[7] = new Item("Meat Balls", 60, 200);
                            specitems[8] = new Item("Beef Bits", 50, 180);
                            specitems[9] = new Item("Hot Dogs", 20, 160);

                            for(int j = 0; j < specslots.length; j++) {
                                specslots[j] = new Slot();
                                specslots[j].addItem(specitems[j],10);
                            }

                            SpecialVendingMachineModel specialVendingMachine = new SpecialVendingMachineModel(specslots);
                            vendingMachineController = new VendingMachineController(vendingMachineView, specialVendingMachine);
                            break;
                        case 3:
                            break;
                        default:
                            System.out.println("Invalid Command. Please try again.");
                            break;
                    }
                }while(vendingMachineChoice!=3);
                break;
            case 2:
                if(one==null){
                    System.out.println("Vending machine is not created. Please try again.");
                    break;
                }

                do {
                    printTest();
                    testMachine = scan.nextInt();
                    switch (testMachine) {
                        case 1:
                            do {
                                one.printFeatureMenu();
                                feature = scan.nextInt();
                                switch (feature) {
                                    case 1:
                                        one.printProducts();
                                        break;
                                    case 2:
                                        buyProducts(scan, one);
                                        break;
                                    case 3:
                                        enterMoney(scan, one);
                                        break;
                                    case 4:
                                        giveChange(scan, one);
                                        break;
                                    case 0:
                                        break;
                                    default:
                                        System.out.println("Invalid Command. Please try again.");
                                        break;
                                }
                            } while (feature != 0);
                            break;
                        case 2:
                            one.maintenanceLogin();
                            pass = scan.nextInt();
                            key = true;
                            if (pass != 8700) {
                                System.out.println("Invalid Password!");
                                break;
                            }

                            while (key) {
                                one.maintenanceMenu();
                                maintenanceMenu = scan.nextInt();
                                switch (maintenanceMenu) {
                                    case 1:
                                        restockProducts(scan, one);
                                        break;
                                    case 2:
                                        stockNewProducts(scan, one);
                                        break;
                                    case 3:
                                        changePrice(scan, one);
                                        break;
                                    case 4:
                                        one.collectPayment();
                                        break;
                                    case 5:
                                        restockMoney(scan, one);
                                        break;
                                    case 6:
                                        one.printTransactions();
                                        break;
                                    case 0:
                                        key = false;
                                        break;
                                    default:
                                        System.out.println("Invalid Selection");
                                        break;
                                }
                            }
                            break;
                        case 3:
                            break;
                        default:
                            System.out.println("Invalid Command. Please try again.");
                            break;
                    }
                }while(testMachine!=3);
            case 3:
                break;
            }
        } while( menu != 3);
    }




    public static VendingMachine createRegularVendingMachine(Scanner scan, VendingMachine one){
        printSlotRequest();
        int choice;
        int snumber = scan.nextInt();

        Slot[] slots = new Slot[snumber];
        one = new VendingMachine("hatdog", slots);

        printItemRequest();
        int inumber = scan.nextInt();
        Item[] items = new Item[snumber];

        do {
            printIItemDistribution();
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    items[0] = new Item("Spaghetti Pasta", 40, 150);
                    items[1] = new Item("Ridged Pasta", 35, 130);
                    items[2] = new Item("Fusilli Pasta", 40, 160);
                    items[3] = new Item("Red Sauce", 20, 80);
                    items[4] = new Item("White Sauce", 30, 90);
                    items[5] = new Item("Parmesian Cheese", 50, 100);
                    items[6] = new Item("Meat Balls", 60, 200);
                    items[7] = new Item("Beef Bits", 50, 180);
                case 2:
                    break;
                default:
                    System.out.println("Invalid input, please try again.");
            }
        }while(choice!=1 && choice!=2);

        for(int j = 0; j < 8; j++) {
            slots[j] = new Slot();
            slots[j].addItem(items[j],10);
        }
        choice = 0;

        do{
            printCustomizeRequest();
            choice = scan.nextInt();
            switch(choice){
                case 1: //////////////////////////////////Not working
                    one.stockProductsMenu();
                    int stockselect = scan.nextInt();
                    one.stockProductsConfirm(stockselect - 1);
                    String name = scan.next();
                    float price = scan.nextFloat();
                    float calories = scan.nextFloat();
                    items[stockselect-1] = new Item(name, price, calories);
                    one.stockProductsFinal(stockselect - 1, items[stockselect-1], 9);
                case 2:
                    break;
                default:
                    System.out.println("Invalid input, please try again.");
            }
        }while(choice!=2);

        one.printProducts();
        System.out.println("Vending machine successfully created!");
        return one;
    }

    public static void buyProducts(Scanner scan, VendingMachine one){
        one.printBuyScreen();
        int choice = scan.nextInt();
        if (one.printConfirmation(choice - 1)) {
            int buyMenu = scan.nextInt();
            if (buyMenu == 1 && one.priceCheck(choice - 1) && one.quantityCheck(choice - 1) && one.changeCheck(choice - 1)) {
                System.out.println("Successfully Purchased: " + one.getSlots()[choice - 1].getItem().getName());
                one.buyItem(choice - 1);
            } else if (!one.changeCheck(choice - 1)) {
                System.out.println("Vending Machine Does Not Have Enough Change!");
                System.out.println("Please Contact Maintenance Assistant!");
            } else if (!one.priceCheck(choice - 1)) {
                System.out.println("Insufficient Amount!");
                System.out.println("Please Insert more to Proceed");
            } else {
                System.out.println("Item is not in stock!");
                System.out.println("Please Try Again Later");
            }
        }
    }

    public static void enterMoney(Scanner scan, VendingMachine one){
        boolean enterMoney;
        do {
            enterMoney = true;
            one.enterMoneyMenu();
            int enterMoneyChoice = scan.nextInt();
            if (enterMoneyChoice == 1) {
                one.enterBillsMenu();
                int choice = scan.nextInt();
                one.enterBills(choice);
            } else {
                enterMoney = one.enterCoins(enterMoneyChoice);
            }
        } while (enterMoney);
    }

    public static void giveChange(Scanner scan, VendingMachine one){
        one.giveChangeConfirm();
        if( scan.nextInt() == 1 ){
            one.giveChange();
        }
    }

    public static void restockProducts(Scanner scan, VendingMachine one){
        one.restockProductsMenu();
        int restockselect = one.restockProductsConfirm(scan.nextInt() - 1);
        if (restockselect < 8 && restockselect > -1) {
            if (one.restockProductsFinal(restockselect, scan.nextInt())) {
                System.out.println("Product has been restocked");
            } else {
                System.out.println("Invalid Amount - Reached Max Capacity");
            }
        } else {
            System.out.println("Invalid Selection/Item slot is empty. Please try again");
        }
    }

    public static void stockNewProducts(Scanner scan, VendingMachine one){ //not working
    /*    one.stockProductsMenu();
        int stockselect = scan.nextInt();
        one.stockProductsConfirm(stockselect - 1);
        String name = scan.next();
        float price = scan.nextFloat();
        float calories = scan.nextFloat();
        Item[] items[ stockselect] = new Item(name, price, calories);
        one.stockProductsFinal(stockselect - 1, items[stockselect-1], 9);*/
    }

    public static void changePrice(Scanner scan, VendingMachine one){
        one.changePriceMenu();
        int changeprice = scan.nextInt();
        one.changePriceMenuConfirm(changeprice - 1);
        if (one.getSlots()[changeprice - 1].getItem() != null) {
            one.changePriceMenuFinal(changeprice - 1, scan.nextFloat());
        } else {
            System.out.println("Item does not Exist!");
        }
    }

    public static void restockMoney(Scanner scan, VendingMachine one){
        int restockselect;
        do{
            one.restockMoneyMenu();
            restockselect = scan.nextInt();
            if( restockselect == 1 || restockselect == 2 || restockselect ==3 || restockselect ==4 ){
                one.restockMoneyDenomination(restockselect);
                one.restockMoneyFinal(restockselect, scan.nextInt());
            }
        }while( restockselect != 0 );
    }
    public static void printSlotRequest(){
        System.out.println( "-------------------------------------------" );
        System.out.println( "-------------------------------------------"+"\n\n");
        System.out.println(  "How many slots do you want your vending machine to have?");
        System.out.println( "Input at least eight(8) slots" );
        System.out.println( "\n\n-------------------------------------------" );
        System.out.println( "-------------------------------------------\n\n" );
    }

    public static void printItemRequest(){
        System.out.println( "-------------------------------------------" );
        System.out.println( "-------------------------------------------"+"\n\n");
        System.out.println(  "How many items do you want your vending machine to have for each slot?");
        System.out.println( "Input at least ten(10) items" );
        System.out.println( "\n\n-------------------------------------------" );
        System.out.println( "-------------------------------------------\n\n" );
    }

    public static void printIItemDistribution(){
        System.out.println( "-------------------------------------------" );
        System.out.println( "-------------------------------------------"+"\n\n");
        System.out.println(  "How would you like to stock the items?");
        System.out.println( "(11 Default Preset" );
        System.out.println( "(21 From the scratch" );
        System.out.println( "\n\n-------------------------------------------" );
        System.out.println( "-------------------------------------------\n\n" );
    }

    public static void printCustomizeRequest(){
        System.out.println( "-------------------------------------------" );
        System.out.println( "-------------------------------------------"+"\n\n");
        System.out.println(  "How would you like to customize the items?");
        System.out.println( "(11 Yes" );
        System.out.println( "(21 No" );
        System.out.println( "\n\n-------------------------------------------" );
        System.out.println( "-------------------------------------------\n\n" );
    }

    public static void printWelcome(){
        System.out.println( "-------------------------------------------" );
        System.out.println( "-------------------------------------------"+"\n\n");
        System.out.println( "Welcome to the Simulation Vending Machine Program\n\n" );
        System.out.println( "Press Enter to Continue" );
        System.out.println( "\n\n-------------------------------------------" );
        System.out.println( "-------------------------------------------\n\n" );
    }

    public static void printMenu(){
        System.out.println( "-------------------------------------------" );
        System.out.println( "-------------------------------------------"+"\n\n");
        System.out.println( "Program Menu");
        System.out.println( "(1) Create Vending Machine" );
        System.out.println( "(2) Test the Vending Machine" );
        System.out.println( "(3) Exit Program" );
        System.out.println( "\n\n-------------------------------------------" );
        System.out.println( "-------------------------------------------\n\n" );
    }

    public static void printCreate(){
        System.out.println( "-------------------------------------------" );
        System.out.println( "-------------------------------------------"+"\n\n");
        System.out.println( "Create a Vending Machine");
        System.out.println( "(1) Regular Vending Machine" );
        System.out.println( "(2) Special Vending Machine" );
        System.out.println( "(3) Exit Menu" );
        System.out.println( "\n\n-------------------------------------------" );
        System.out.println( "-------------------------------------------\n\n" );
    }

    public static void printTest(){
        System.out.println( "-------------------------------------------" );
        System.out.println( "-------------------------------------------"+"\n\n");
        System.out.println( "Testing the Vending Machine");
        System.out.println( "(1) Vending Features" );
        System.out.println( "(2) Maintenance Features" );
        System.out.println( "(3) Exit Menu" );
        System.out.println( "\n\n-------------------------------------------" );
        System.out.println( "-------------------------------------------\n\n" );
    }
}
