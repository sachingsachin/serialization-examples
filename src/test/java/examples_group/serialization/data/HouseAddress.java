package examples_group.serialization.data;

import java.util.List;

public class HouseAddress extends CommonBase
{
    private String street;
    private Integer zipcode;
    private List<Restaurant> restaurant;

    // Constructors
    public HouseAddress()
    {
        super();
    }
    public HouseAddress(String street, Integer zipcode)
    {
        super();
        this.street = street;
        this.zipcode = zipcode;
    }
    public String getStreet()
    {
        return street;
    }
    public void setStreet(String street)
    {
        this.street = street;
    }
    public Integer getZipcode()
    {
        return zipcode;
    }
    public void setZipcode(Integer zipcode)
    {
        this.zipcode = zipcode;
    }    
    public List<Restaurant> getRestaurant() {
        return restaurant;
    }
    public void setRestaurant(List<Restaurant> restaurant) {
        this.restaurant = restaurant;
    }
    @Override
    public String toString()
    {
        return "Address: " + street + ", " + zipcode;
    }
}
