/**
 * This class is responsible for the categorizing transaction money into coins.
 *
 * @author Group 2(Flores, Ranigo)
 */
public class Coin {

    /**
     * The constructor is responsible for assigning a value to a coin.
     * @param value The amount in terms of value.
     */
    Coin(double value){
        this.value = value;
    }

    /**
     * This method returns the value of the coin.
     * @return coin value.
     */
    public double getValue(){return value;}

    private double value;
}
