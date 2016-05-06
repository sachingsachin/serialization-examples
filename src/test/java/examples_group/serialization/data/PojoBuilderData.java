package examples_group.serialization.data;

import java.util.Arrays;
import java.util.List;

public class PojoBuilderData
{
    public static final String houseNames [] =
    {
        "House of Elves", "Pack of Sharks", "Corner House", "Center House",
        "Jack The Giant Slayer", "Wolves in House", "Pack of Elves", "Sharks Paradise",
        "Elves Paradise", "Sharks of the ocean", "Hello Home", "Pack of Wolves",
    };
    public static final String streets [] =
    {
        "Baker Street", "Pacific Coast Highway", "Bourbon Street", "Michigan Avenue",
        "Hollywood Boulevard", "Wall Street", "Castro Street", "Baker Highway",
        "Pacific Coast Road", "Bourbon Road", "Castro Boulevard", "Hollywood Highway",
    };
    public static final Integer zipcodes [] =
    {
        900, 910, 920, 930,
        800, 810, 820, 830,
        700, 710, 720, 730,
    };
    public static final String personNames [] =
    {
        "Marie Curie", "John Malkovich", "Ray Kurzweil", "Michio Kaku",
        "John Kurzweil", "Ray Kaku", "Michio Curie", "Marie Malkovich",
        "Marie Kurzweil", "John Kaku", "Ray Curie", "Michio Malkovich",
    };
    public static final String restaurntNames [] =
    {
        "Bistro Taverna", "Cafe Coffee Day", "Starbucks",
        "Mc Donalds", "Subway", "Falafal Delight"
    };
    public static final String cuisines [] =
    {
        "Indian", "Chinese", "American",
        "Mediterranean", "Mongolian", "Fast Food"
    };
    public static final String histories [] =
    {
        "This wood house with metal doors was made by Dutch nationals and sold in 20th century",
        "This metal house was made by American nationals and sold in the 21st century",
        "This house was made of wood, cement and iron in later half of 20th century"
    };
    public static final BuildingType types[] = {
    	BuildingType.CAPE,
    	BuildingType.COLONIAL,
    	BuildingType.RANCH
    };
    public static final String[] amenities = 
    	{
    	"five-bath",
    	"nine bed",
    	"balcony",
    	"east-facing",
    	"fireplace",
    	"gym",
    	"close to shops"
    	};
    
    public static final Integer[] maintenancePerYear = 
    	{
    	100000,
    	150000,
    	200000,
    	110000
    	};
    
    public static <T> T getRandomData (T[] arr)
    {
        int i = (int)(Math.random()*arr.length);
        return arr[i];
    }
    
    public static <T> List<T> getRandomList (T[] arr)
    {
        int i = (int)(Math.random()*arr.length);
        int j = (int)(Math.random()*arr.length);
        
        if(i < j)
            return Arrays.asList((T[]) Arrays.copyOfRange(arr, i, j));
        else if(i!=j)	
            return Arrays.asList((T[]) Arrays.copyOfRange(arr, j, i));
        else 
            return Arrays.asList((T[]) Arrays.copyOfRange(arr, 0, 1));
    }

}
