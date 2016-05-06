package examples_group.serialization.data;

public class Person extends CommonBase
{
    private String name;
    private House home;
    
    // Constructors
    public Person()
    {
        super();
    }
    public Person(String name, House home)
    {
        super();
        this.name = name;
        this.home = home;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public House getHome()
    {
        return home;
    }
    public void setHome(House home)
    {
        this.home = home;
    }
    @Override
    public String toString()
    {
        return name;
    }
}
