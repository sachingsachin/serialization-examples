package examples_group.serialization;

import java.io.IOException;
import java.util.Map;

import org.apache.avro.io.Decoder;
import org.apache.avro.io.Encoder;
import org.apache.avro.reflect.CustomEncoding;

public class CustomMapEncoding extends CustomEncoding<Map>
{
    @Override
    protected void write(Object datum, Encoder out) throws IOException {
        //out.write
    }

    @Override
    protected Map read(Object reuse, Decoder in) throws IOException {
        return null;
    }
}
