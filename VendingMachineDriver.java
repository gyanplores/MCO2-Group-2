import java.util.Scanner;

public class VendingMachineDriver {
    public static void main(String[] args){
        int snumber, inumber, menu, feature, vendingMachineChoice, testingMachine, choice, enterMoneyChoice, buyMenu, pass, maintenanceMenu, restockselect, stockselect, changeprice;
        float price, calories;
        boolean enterMoney, key;
        String name;
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
                            printSlotRequest();
                            snumber = scan.nextInt();

                            Slot[] slots = new Slot[snumber];
                            one = new VendingMachine(slots);

                            printItemRequest();
                            inumber = scan.nextInt();
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

                            for( int i = 0 ; i < snumber ; i++ ){
                                //slots[i] = new Slot( items[i], inumber );
                                slots[i].addItem(items[i] , inumber);
                            }
                            choice = 0;

                            do{
                                printCustomizeRequest();
                                choice = scan.nextInt();
                                switch(choice){
                                    case 1:
                                        one.printProducts();
                                        one.stockProductsMenu();
                                        stockselect = scan.nextInt();
                                        one.stockProductsConfirm(stockselect - 1);
                                        name = scan.next();
                                        price = scan.nextFloat();
                                        calories = scan.nextFloat();
                                        one.stockProductsFinal(stockselect - 1, name, price, calories);
                                    case 2:
                                        break;
                                    default:
                                        System.out.println("Invalid input, please try again.");
                                }
                            }while(choice!=2);

                            one.printProducts();
                            System.out.println("Vending machine successfully created!");
                            break;
                        case 2:
                            System.out.println("This is a work in progress. Sorry for your inconvenience.");
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
                    testingMachine = scan.nextInt();
                    switch (testingMachine) {
                        case 1:
                            do {
                                one.printFeatureMenu();
                                feature = scan.nextInt();
                                switch (feature) {
                                    case 1:
                                        one.printProducts();
                                        break;
                                    case 2:
                                        one.printBuyScreen();
                                        choice = scan.nextInt();
                                        if (one.printConfirmation(choice - 1)) {
                                            buyMenu = scan.nextInt();
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
                                        break;
                                    case 3:
                                        do {
                                            enterMoney = true;
                                            one.enterMoneyMenu();
                                            enterMoneyChoice = scan.nextInt();
                                            if (enterMoneyChoice == 1) {
                                                one.enterBillsMenu();
                                                choice = scan.nextInt();
                                                one.enterBills(choice);
                                            } else {
                                                enterMoney = one.enterCoins(enterMoneyChoice);
                                            }
                                        } while (enterMoney);
                                        break;
                                    case 4:
                                        one.giveChangeConfirm();
                                        if( scan.nextInt() == 1 ){
                                            one.giveChange();
                                        }
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
                                        one.restockProductsMenu();
                                        restockselect = one.restockProductsConfirm(scan.nextInt() - 1);
                                        if (restockselect < 8 && restockselect > -1) {
                                            if (one.restockProductsFinal(restockselect, scan.nextInt())) {
                                                System.out.println("Product has been restocked");
                                            } else {
                                                System.out.println("Invalid Amount - Reached Max Capacity");
                                            }
                                        } else {
                                            System.out.println("Invalid Selection");
                                        }
                                        break;
                                    case 2:
                                        one.stockProductsMenu();
                                        stockselect = scan.nextInt();
                                        one.stockProductsConfirm(stockselect - 1);
                                        name = scan.next();
                                        price = scan.nextFloat();
                                        calories = scan.nextFloat();
                                        one.stockProductsFinal(stockselect - 1, name, price, calories);
                                        break;
                                    case 3:
                                        one.changePriceMenu();
                                        changeprice = scan.nextInt();
                                        one.changePriceMenuConfirm(changeprice - 1);
                                        if (one.getSlots()[changeprice - 1].getItem() != null) {
                                            one.changePriceMenuFinal(changeprice - 1, scan.nextFloat());
                                        } else {
                                            System.out.println("Item does not Exist!");
                                        }
                                        break;
                                    case 4:
                                            one.collectPayment();
                                        break;
                                    case 5:
                                        do{
                                        one.restockMoneyMenu();
                                        restockselect = scan.nextInt();
                                        if( restockselect == 1 || restockselect == 2 || restockselect ==3 || restockselect ==4 ){
                                            one.restockMoneyDenomination(restockselect);
                                            one.restockMoneyFinal(restockselect, scan.nextInt());
                                            }
                                        }while( restockselect != 0 );
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
                }while(testingMachine!=3);
            case 3:
                break;
            }
        } while( menu != 3);
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
