package examples_group.serialization.data;

import java.util.Date;
import java.util.List;

public class House extends CommonBase
{
    private String name1;
    private String name2;
    private String name3;
    private HouseAddress address;
    private List<Person> residents;
    private Date completionDate;
    private Date houseWarmingDate;
    private String history1;
    private String history2;
    private BuildingType type;
    private Long longValue;
    private List<String> amenities;
    private List<Integer> maintenancePerYear;

    // Constructors
    public House(String name1, String name2, String name3,
            HouseAddress address, List<Person> residents,
            Date completionDate, Date houseWarmingDate,
            String history1, String history2,
            BuildingType type, Long longValue, List<String> amenities)
    {
        super();
        this.name1 = name1;
        this.name2 = name2;
        this.name3 = name3;
        this.address = address;
        this.residents = residents;
        this.completionDate = completionDate;
        this.houseWarmingDate = houseWarmingDate;
        this.history1 = history1;
        this.history2 = history2;
        this.type = type;
        this.longValue = longValue;
        this.amenities = amenities;
    }
    public House()
    {
        super();
    }
    public String getName1()
    {
        return name1;
    }
    public void setName1(String name1)
    {
        this.name1 = name1;
    }
    public HouseAddress getAddress()
    {
        return address;
    }
    public void setAddress(HouseAddress address)
    {
        this.address = address;
    }
    public List<Person> getResidents()
    {
        return residents;
    }
    public void setResidents(List<Person> residents)
    {
        this.residents = residents;
    }
    public Date getCompletionDate()
    {
        return completionDate;
    }
    public void setCompletionDate(Date completionDate)
    {
        this.completionDate = completionDate;
    }
    public Date getHouseWarmingDate()
    {
        return houseWarmingDate;
    }
    public void setHouseWarmingDate(Date houseWarmingDate)
    {
        this.houseWarmingDate = houseWarmingDate;
    }
    public String getName2()
    {
        return name2;
    }
    public void setName2(String name2)
    {
        this.name2 = name2;
    }
    public String getName3()
    {
        return name3;
    }
    public void setName3(String name3)
    {
        this.name3 = name3;
    }
    public String getHistory1()
    {
        return history1;
    }
    public void setHistory1(String history)
    {
        this.history1 = history;
    }
    public String getHistory2()
    {
        return history2;
    }
    public void setHistory2(String history2)
    {
        this.history2 = history2;
    }
    public BuildingType getType() {
        return type;
    }
    public void setType(BuildingType type) {
        this.type = type;
    }	
    public Long getLongValue() {
        return longValue;
    }
    public void setLongValue(Long longValue) {
        this.longValue = longValue;
    }	
    public List<String> getAmenities() {
        return amenities;
    }
    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }	
    public List<Integer> getMaintenancePerYear() {
        return maintenancePerYear;
    }
    public void setMaintenancePerYear(List<Integer> maintenancePerYear) {
        this.maintenancePerYear = maintenancePerYear;
    }
    @Override
    public String toString()
    {
        return "House \"" + name1 + "\"\n at " + address + "\n with residents=" + residents;
    }
}
