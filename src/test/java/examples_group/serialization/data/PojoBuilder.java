package examples_group.serialization.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PojoBuilder
{
    public static House buildHouse (boolean createCircularRefs)
    {
        return buildHouse(
                1 + (int) (Math.random() * 3),
                1 + (int) (Math.random() * 3),
                createCircularRefs
        );
    }

    public static House buildHouse (int numResidents, int numRestaurants,
            boolean createCircularRefs)
    {
        return buildHouse (
                PojoBuilderData.getRandomData(PojoBuilderData.houseNames),
                PojoBuilderData.getRandomData(PojoBuilderData.houseNames),
                PojoBuilderData.getRandomData(PojoBuilderData.houseNames),
                PojoBuilderData.getRandomData(PojoBuilderData.streets),
                PojoBuilderData.getRandomData(PojoBuilderData.zipcodes),
                PojoBuilderData.getRandomData(PojoBuilderData.histories),
                PojoBuilderData.getRandomData(PojoBuilderData.histories),
                PojoBuilderData.getRandomList(PojoBuilderData.amenities),
                numResidents,
                numRestaurants,
                createCircularRefs
        );
    }

    public static House buildHouse(String name1, String name2, String name3,
            String street, Integer zipcode,
            String history1, String history2,
            List<String> amenities,
            int numResidents, int numRestaurants,
            boolean createCircularRefs) {

        HouseAddress address = new HouseAddress(street, zipcode);
        List<Person> residents = new ArrayList<>();

        double rndm1 = 0.6 + 0.35 * Math.random();
        double rndm2 = 0.6 + 0.35 * Math.random();
        Date date1 = new Date((long) (rndm1 * System.currentTimeMillis()));
        Date date2 = new Date((long) (rndm2 * System.currentTimeMillis()));

        House house = new House(name1, name2, name3,
                address, residents, date1, date2,
                history1, history2,
                PojoBuilderData.getRandomData(PojoBuilderData.types), 
                System.currentTimeMillis(), amenities);

        house.setMaintenancePerYear(PojoBuilderData.getRandomList(PojoBuilderData.maintenancePerYear));

        house.setDbLockVersion(Long.valueOf(System.currentTimeMillis()));

        for (int i = 0; i < numResidents; i++) {
            Person resident = new Person(
                    PojoBuilderData.getRandomData(PojoBuilderData.personNames),
                    createCircularRefs?house:null);

            resident.getId();
            residents.add(resident);
            resident.setDbLockVersion(Long.valueOf(System.currentTimeMillis()));
        }

        List<Restaurant> restaurants = new ArrayList<>();
        house.getAddress().setRestaurant(restaurants);
        for (int i = 0; i < numRestaurants; i++) {
            Restaurant rest = new Restaurant(
                    PojoBuilderData.getRandomData(PojoBuilderData.restaurntNames),
                    PojoBuilderData.getRandomData(PojoBuilderData.cuisines),
                    (int)(5 + Math.random()*10));

            restaurants.add(rest);
            rest.setDbLockVersion(Long.valueOf(System.currentTimeMillis()));
        }
        return house;
    }
}
