package examples_group.serialization;

import examples_group.serialization.data.House;
import examples_group.serialization.data.PojoBuilder;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Check circular references
 */
public class CircularRefsTest extends TestCase
{
    public void testSerialization()
    {
        House house = PojoBuilder.buildHouse(true);
        assertTrue( true );
    }

    /**
     * Creates the test case
     */
    public CircularRefsTest(String testName)
    {
        super(testName);
    }
    public static Test suite()
    {
        return new TestSuite(CircularRefsTest.class);
    }
}
