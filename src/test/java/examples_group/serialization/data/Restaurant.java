package examples_group.serialization.data;

public class Restaurant extends CommonBase {

    private String name;
    private String cuisine;
    private int yelpDollars;

    public Restaurant() {}
    public Restaurant(String name, String cuisine, int yelpDollars) {
        super();
        this.name = name;
        this.cuisine = cuisine;
        this.yelpDollars = yelpDollars;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCuisine() {
        return cuisine;
    }
    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }
    public int getYelpDollars() {
        return yelpDollars;
    }
    public void setYelpDollars(int yelpDollars) {
        this.yelpDollars = yelpDollars;
    }

    @Override
    public String toString() {
        return "Restaurant [name=" + name + "]";
    }	

}
