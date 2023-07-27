/**
 * This class is responsible for the characteristics of the item.
 *
 * @author Flores
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

    public void setName( String name ){ this.name = name;}

    /**
     * This method accepts price and assigns it to the price object.
     * @param price
     */
    public void setPrice( float price ){ this.price = price; }

    public void setCalories( float calories ){ this.calories = calories;}

    private String name;
    private float price;
    private float calories;
}
