package examples_group.serialization;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;

import org.apache.avro.Schema;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.reflect.ReflectData;
import org.apache.avro.reflect.ReflectDatumWriter;
import org.junit.Test;

/**
 * Check circular references
 */
public class CircularRefsTest
{
    @Test
    public void testSerialization() throws Exception
    {
        // Create a circular linked-list
        ListNode first = new ListNode("first");
        ListNode second = new ListNode("second");
        second.next = first;
        first.next = second;

        ReflectData rdata = ReflectData.AllowNull.get();

        // Print Schema
        Schema schema = rdata.getSchema(ListNode.class);
        System.out.println(schema);

        // Serialize
        DatumWriter<ListNode> datumWriter = new ReflectDatumWriter<ListNode>(ListNode.class);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Encoder encoder = EncoderFactory.get().binaryEncoder(byteArrayOutputStream, null);
        datumWriter.write(first, encoder);
        encoder.flush();
        byte[] bytes = byteArrayOutputStream.toByteArray();

        assertTrue( bytes != null );
    }
}

class ListNode
{
    String data;
    ListNode next;

    public ListNode() {
    }
    public ListNode(String data) {
        this.data = data;
    }
}
