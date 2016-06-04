package examples_group.serialization;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.reflect.AvroEncode;
import org.apache.avro.reflect.ReflectData;
import org.junit.Test;

/**
 * Check circular references
 */
public class InterfaceTest
{
    @Test
    public void testInterfaces() throws Exception
    {
        InterfaceWrapper data = new InterfaceWrapper();
        data.map = new HashMap<>();
        data.map.put(new NumberWrapper(23), new NumberWrapper(45.23));

        ReflectData rdata = ReflectData.AllowNull.get();

        // Print Schema
        Schema schema = rdata.getSchema(InterfaceWrapper.class);
        Utils.printSchema(schema);

        // Serialize
        byte[] bytes = Utils.serialize("interface-test", data);
        GenericRecord record = Utils.readGenericData(bytes, schema);
        InterfaceWrapper reflectRecords = Utils.readReflectData(bytes, schema);
    }
}

class InterfaceWrapper
{
    //@AvroEncode(using = CustomMapEncoding.class)
    Map<NumberWrapper, NumberWrapper> map;
}

class NumberWrapper
{
    @AvroEncode(using = CustomNumberEncoding.class)
    Number num;

    public NumberWrapper()
    {
    }
    public NumberWrapper(Number num)
    {
        super();
        this.num = num;
    }
}