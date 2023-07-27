import java.util.*;
public class CashRegister {

    CashRegister(){
        oneCoin = new ArrayList<Coin>();
        fiveCoin = new ArrayList<Coin>();
        tenCoin = new ArrayList<Coin>();
        twentyCoin = new ArrayList<Coin>();
        twentyBill = new ArrayList<Bill>();
        fiftyBill = new ArrayList<Bill>();
        hundredBill = new ArrayList<Bill>();
        twohunBill = new ArrayList<Bill>();
        fivehunBill = new ArrayList<Bill>();
        thousandBill = new ArrayList<Bill>();
        totalCash = 0;
        revenue = 0;
        userMoney = 0;
    }

    public void addDenomination( int select, int amount ){
        for( int i = 0; i < amount; i++ ){
            switch (select) {
                case 1 -> {
                    oneCoin.add(new Coin(1));
                    totalCash = (amount) + totalCash;
                    userMoney = (amount) + userMoney;
                }
                case 2 -> {
                    fiveCoin.add(new Coin(5));
                    totalCash = (5 * amount) + totalCash;
                    userMoney = (5 * amount) + userMoney;
                }
                case 3 -> {
                    tenCoin.add(new Coin(10));
                    totalCash = (10 * amount) + totalCash;
                    userMoney = (10 * amount) + userMoney;
                }
                case 4 -> {
                    twentyCoin.add(new Coin(20));
                    totalCash = (20 * amount) + totalCash;
                    userMoney = (20 * amount) + userMoney;
                }
                case 5 -> {
                    twentyBill.add(new Bill(20));
                    totalCash = (20 * amount) + totalCash;
                    userMoney = (20 * amount) + userMoney;
                }
                case 6 -> {
                    fiftyBill.add(new Bill(50));
                    totalCash = (50 * amount) + totalCash;
                    userMoney = (50 * amount) + userMoney;
                }
                case 7 -> {
                    hundredBill.add(new Bill(100));
                    totalCash = (100 * amount) + totalCash;
                    userMoney = (100 * amount) + userMoney;
                }
                case 8 -> {
                    twohunBill.add(new Bill(200));
                    totalCash = (200 * amount) + totalCash;
                    userMoney = (200 * amount) + userMoney;
                }
                case 9 -> {
                    fivehunBill.add(new Bill(500));
                    totalCash = (500 * amount) + totalCash;
                    userMoney = (500 * amount) + userMoney;
                }
                case 10 -> {
                    thousandBill.add(new Bill(1000));
                    totalCash = (1000 * amount) + totalCash;
                    userMoney = (1000 * amount) + userMoney;
                }
            }
        }
        updateTotalCash();
    }

    public boolean buyCheck( double price , int sizeofItem){
        if( userMoney - price >= 0 ){
            return sizeofItem > 0;
        }else{
            return false;
        }
    }

    public void buyProduct( double price, int sizeofItem ){
        if( buyCheck( price, sizeofItem ) ){
            revenue = revenue + price;
            userMoney = userMoney - price;
        }else{
            System.out.println( "Unable to Buy Item: Not Enough Money OR Item not in Stock!" );
        }
    }

    public boolean changeCheck( double change ){
        int val;

        val = (int)change / 1000;
        if( val >= 1 && thousandBill.size() > 0 && thousandBill.size() >= val ){
            change = change - 1000 * (val) ;
        }
        val = (int)change / 500;
        if( val >= 1 && fivehunBill.size() > 0 && fivehunBill.size() >= val ){
            change = change - 500 * (val) ;
        }
        val = (int)change / 200;
        if( val >= 1 && twohunBill.size() > 0 && twohunBill.size() >= val ){
            change = change - 200 * (val)  ;
        }
        val = (int)change / 100;
        if( val >= 1 && hundredBill.size() > 0 && hundredBill.size() >= val ){
            change = change - 100 * (val) ;
        }
        val = (int)change / 50;
        if( val >= 1 && fiftyBill.size() > 0 && fiftyBill.size() >= val ){
            change = change - 50 * (val) ;
        }
        val = (int)change / 20;
        if( val >= 1 && twentyBill.size() > 0 && twentyBill.size() >= val ){
            change =change - 20 * (val) ;
        }
        val = (int)change / 20;
        if( val >= 1 && twentyCoin.size() > 0 && twentyCoin.size() >= val ){
            change = change - 20 * (val) ;
        }
        val = (int)change / 10;
        if( val >= 1 && tenCoin.size() > 0 && tenCoin.size() >= val ){
            change = change - 10 * (val) ;
        }
        val = (int)change / 5;
        if( val >= 1 && fiveCoin.size() > 0 && fiveCoin.size() >= val ){
            change = change - 5 * (val) ;
        }
        val = (int) change;
        if( val >= 1 && oneCoin.size() > 0 && oneCoin.size() >= val ){
            change = change - (val);
        }

        return change == 0;
    }

    public void changePrint( int[] list, double value ){
        System.out.println( "-------------------------------------------" );
        System.out.println( "-------------------------------------------"+"\n\n" );
        System.out.println( "You have received: ");
        System.out.println( list[0]+" Amount of 1000 Peso Bills");
        System.out.println( list[1]+" Amount of 500 Peso Bills");
        System.out.println( list[2]+" Amount of 200 Peso Bills");
        System.out.println( list[3]+" Amount of 100 Peso Bills");
        System.out.println( list[4]+" Amount of 50 Peso Bills");
        System.out.println( list[5]+" Amount of 20 Peso Bills");
        System.out.println( list[6]+" Amount of 20 Peso Coins");
        System.out.println( list[7]+" Amount of 10 Peso Coins");
        System.out.println( list[8]+" Amount of 5 Peso Coins");
        System.out.println( list[9]+" Amount of 1 Peso Coins");
        System.out.println( "Total Change: "+value);
        System.out.println("\n\n-------------------------------------------" );
        System.out.println("-------------------------------------------\n\n" );
    }

    public void giveChange( ){
        int[] list = new int[10];
        int val;
        int i;
        double placeholder = userMoney;
        val = (int)userMoney / 1000;
        if( val >= 1 && thousandBill.size() > 0 && thousandBill.size() >= val ){
            userMoney = userMoney - 1000 * (val);
            list[0] = val;
            i = 0;
            while( i < val ){
                thousandBill.remove( thousandBill.size()-1 );
                i++;
            }
        }else{
            list[0] = 0;
        }
        val = (int)userMoney / 500;
        if( val >= 1 && fivehunBill.size() > 0 && fivehunBill.size() >= val ){
            userMoney = userMoney - 500 * (val);
            list[1] = val;
            i = 0;
            while( i < val ){
                fivehunBill.remove( fivehunBill.size()-1 );
                i++;
            }
        }else{
            list[1] = 0;
        }
        val = (int)userMoney / 200;
        if( val >= 1 && twohunBill.size() > 0 && twohunBill.size() >= val ){
            userMoney = userMoney - 200 * (val) ;
            list[2] = val;
            i = 0;
            while( i < val ){
                twohunBill.remove( twohunBill.size()-1 );
                i++;
            }
        }else{
            list[2] = 0;
        }
        val = (int)userMoney / 100;
        if( val >= 1 && hundredBill.size() > 0 && hundredBill.size() >= val ){
            userMoney = userMoney - 100 * (val) ;
            list[3] = val;
            i = 0;
            while( i < val ){
                hundredBill.remove( hundredBill.size()-1 );
                i++;
            }
        }else{
            list[3] = 0;
        }
        val = (int)userMoney / 50;
        if( val >= 1 && fiftyBill.size() > 0 && fiftyBill.size() >= val ){
            userMoney = userMoney - 50 * (val);
            list[4] = val;
            i = 0;
            while( i < val ){
                fiftyBill.remove( fiftyBill.size()-1 );
                i++;
            }
        }else{
            list[4] = 0;
        }
        val = (int)userMoney / 20;
        if( val >= 1 && twentyBill.size() > 0 && twentyBill.size() >= val ){
            userMoney = userMoney - 20 * (val) ;
            list[5] = val;
            i = 0;
            while( i < val ){
                twentyBill.remove( twentyBill.size()-1 );
                i++;
            }
        }else{
            list[5] = 0;
        }
        val = (int)userMoney / 20;
        if( val >= 1 && twentyCoin.size() > 0 && twentyCoin.size() >= val ){
            userMoney =  userMoney - 20 * (val);
            list[6] = val;
            i = 0;
            while( i < val ){
                twentyCoin.remove( twentyCoin.size()-1 );
                i++;
            }
        }else{
            list[6] = 0;
        }
        val = (int)userMoney / 10;
        if( val >= 1 && tenCoin.size() > 0 && tenCoin.size() >= val ){
            userMoney = userMoney - 10 * (val);
            list[7] = val;
            i = 0;
            while( i < val ){
                tenCoin.remove( tenCoin.size()-1 );
                i++;
            }
        }else{
            list[7] = 0;
        }
        val = (int)userMoney / 5;
        if( val >= 1 && fiveCoin.size() > 0 && fiveCoin.size() >= val ){
            userMoney = userMoney - 5 * (val);
            list[8] = val;
            i = 0;
            while( i < val ){
                fiveCoin.remove( fiveCoin.size()-1 );
                i++;
            }
        }else{
            list[8] = 0;
        }
        val = (int)userMoney;
        if( val >= 1 && oneCoin.size() > 0 && oneCoin.size() >= val ){
            userMoney = userMoney - val ;
            list[9] = val;
            i = 0;
            while( i < val ){
                oneCoin.remove( oneCoin.size()-1 );
                i++;
            }
        }else{
            list[9] = 0;
        }

        changePrint( list , placeholder);
        updateTotalCash();
    }

    public void updateTotalCash(){
        totalCash = getOneCoin();
        totalCash = getFiveCoin() * 5 + totalCash;
        totalCash = getTenCoin() * 10 + totalCash;
        totalCash = getTwentyCoin() * 20+ totalCash;
        totalCash = getTwentyBill() * 20 + totalCash;
        totalCash = getFiftyBill() * 50 + totalCash;
        totalCash = getHundredBill()* 100 + totalCash;
        totalCash = getTwoHundredBill() * 200 + totalCash;
        totalCash = getFiveHundredBill() * 500 + totalCash;
        totalCash = getThousandBill() * 1000 + totalCash;
    }

    public int getOneCoin(){return oneCoin.size();}
    public int getFiveCoin(){return fiveCoin.size();}
    public int getTenCoin(){return tenCoin.size();}
    public int getTwentyCoin(){return twentyCoin.size();}
    public int getTwentyBill(){return twentyBill.size();}
    public int getFiftyBill(){return fiftyBill.size();}
    public int getHundredBill(){return hundredBill.size();}
    public int getTwoHundredBill(){return twohunBill.size();}
    public int getFiveHundredBill(){return fivehunBill.size();}
    public int getThousandBill(){return thousandBill.size();}
    public double getTotalCash(){return totalCash;}
    public double getRevenue(){return revenue;}
    public double getUserMoney(){return userMoney;}


    private ArrayList<Coin> oneCoin;
    private ArrayList<Coin> fiveCoin;
    private ArrayList<Coin> tenCoin;
    private ArrayList<Coin> twentyCoin;
    private ArrayList<Bill> twentyBill;
    private ArrayList<Bill> fiftyBill;
    private ArrayList<Bill> hundredBill;
    private ArrayList<Bill> twohunBill;
    private ArrayList<Bill> fivehunBill;
    private ArrayList<Bill> thousandBill;
    private double totalCash;
    private double revenue;
    private double userMoney;

}