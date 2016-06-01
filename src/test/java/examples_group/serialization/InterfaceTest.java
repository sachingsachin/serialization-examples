package examples_group.serialization;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.reflect.ReflectData;
import org.apache.avro.reflect.ReflectDatumWriter;
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
        List<GenericRecord> records = Utils.readGenericData(bytes);
        List<InterfaceWrapper> reflectRecords = Utils.readReflectData(bytes);

    }
}

class InterfaceWrapper
{
    Map<NumberWrapper, NumberWrapper> map;
}

class NumberWrapper
{
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