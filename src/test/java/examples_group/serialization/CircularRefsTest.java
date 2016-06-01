package examples_group.serialization;

import static org.junit.Assert.assertTrue;

import org.apache.avro.Schema;
import org.apache.avro.reflect.ReflectData;
import org.junit.Test;

/**
 * Check circular references
 */
public class CircularRefsTest
{
    @Test
    public void testCircularRefs() throws Exception
    {
        // Create a circular linked-list
        ListNode first = new ListNode("first");
        ListNode second = new ListNode("second");
        second.next = first;
        first.next = second;

        ReflectData rdata = ReflectData.AllowNull.get();

        // Print Schema
        Schema schema = rdata.getSchema(ListNode.class);
        Utils.printSchema(schema);

        // Serialize
        byte[] bytes = Utils.serialize("circular-refs", first);

        assertTrue( bytes != null );
    }
}

class ListNode
{
    String data;
    ListNode next;

    public ListNode()
    {
    }
    public ListNode(String data)
    {
        this.data = data;
    }
}
