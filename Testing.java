import java.util.ArrayList;

public class Testing {
    public static void main( String[] args ){
        CashRegister cashregister = new CashRegister();

        System.out.println("Money Before addDenomination : "+ cashregister.getTotalCash());

        cashregister.addDenomination( 10, 1 );
        cashregister.addDenomination( 9, 1 );
        System.out.println("Money After addDenomination : "+ cashregister.getTotalCash());
        System.out.println("UserMoney : "+ cashregister.getUserMoney());
        System.out.println( "User Has Enough Change: "+cashregister.changeCheck(100) );

        System.out.println( "one Coins: "+cashregister.getOneCoin() );
        System.out.println( "five Coins: "+cashregister.getFiveCoin() );
        System.out.println( "ten Coins: "+cashregister.getTenCoin() );
        System.out.println( "twenty Coins: "+cashregister.getTwentyCoin() );
        System.out.println( "twenty Bill: "+cashregister.getTwentyBill() );
        System.out.println( "fifty Bill: "+cashregister.getFiftyBill() );
        System.out.println( "hundred Bill: "+cashregister.getHundredBill() );
        System.out.println( "two hundred Bill: "+cashregister.getTwoHundredBill() );
        System.out.println( "five hundred Bill: "+cashregister.getFiveHundredBill() );
        System.out.println( "thousand Bill: "+cashregister.getThousandBill() );

        cashregister.giveChange();
        System.out.println("Money After Change is given : "+ cashregister.getTotalCash());


        ArrayList<Integer> newList = new ArrayList<>();


        System.out.println( newList.get(0) == null );
    }
}
