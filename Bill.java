/**
 * This class is responsible for the categorizing transaction money into bills.
 *
 * @author Group 2(Flores, Ranigo)
 */
public class Bill {

    /**
     * The constructor is responsible for assigning a value to a bill.
     *
     * @param value The amount in terms of value.
     */
    Bill(float value) {
        this.value = value;
    }

    /**
     * This method returns the value of the bill.
     *
     * @return Bill value.
     */
    public double getValue() {
        return value;
    }

    private double value;
}
