/**
 * This class is responsible for the characteristics of the item.
 *
 * @author Group 2(Flores, Ranigo)
 */
public class Item {
    /**
     * This constructor initializes the basic attributes of a vending machine item.
     * This includes the name, prices, and calories of the item.
     * @param name
     * @param price
     * @param calories
     */
    public Item( String name, float price, float calories ){
        this.name = name;
        this.price = price;
        this.calories = calories;
        qtysold = 0;
    }

    /**
     * This constructor initializes the custom pasta
     */
    public Item(){
        this.name = "Custom Pasta";
        this.price = 0;
        this.calories = 0;
        qtysold = 0;
    }

    // Getter and Setters

    /**
     * This method return the name of the item.
     * @return Item name.
     */
    public String getName() { return name; }

    /**
     * This method returns the price of the item.
     * @return Item price.
     */
    public float getPrice(){ return price; }

    /**
     * This method returns the calories of the item.
     * @return Item calories.
     */
    public float getCalories(){ return calories; }

    /**
     * This method returns independence of the item.
     * @return Item independence.
     */
    public boolean getIndependence(){ return independence; }

    /**
     *This method returns the quantity sold for an item.
     * @return Quantity sold.
     */
    public int getQtySold(){return qtysold;}
    /**
     * This method accepts name and assigns it to the name object.
     * @param name The name of the item
     */
    public void setName( String name ){ this.name = name;}

    /**
     * This method accepts price and assigns it to the price object.
     * @param price The price of the item
     */
    public void setPrice( float price ){ this.price = price; }

    /**
     * This method accepts calories and assigns it to the price object.
     * @param calories The calories of the item.
     */
    public void setCalories( float calories ){ this.calories = calories;}

    /**
     * This method accepts calories and assigns it to the price object.
     * @param independence The independence of the item.
     */
    public void setIndependence( boolean independence){ this.independence = independence;}

    private String name;
    private float price;
    private float calories;
    private boolean independence;

    private int qtysold;


}
