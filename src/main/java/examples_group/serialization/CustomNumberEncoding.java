package examples_group.serialization;

import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.Encoder;
import org.apache.avro.reflect.CustomEncoding;

public class CustomNumberEncoding extends CustomEncoding<Number> {

    @Override
    protected void write(Object datum, Encoder out) throws IOException {
        if (datum == null) {
            out.writeIndex(0);
            out.writeNull();
        } else if (datum instanceof Long) {
            out.writeIndex(1);
            out.writeLong((long) datum);
        } else if (datum instanceof Float) {
            out.writeIndex(2);
            out.writeFloat((float) datum);
        } else if (datum instanceof Short || datum instanceof Integer) {
            out.writeIndex(3);
            out.writeInt((int) datum);
        } else if (datum instanceof Double) {
            out.writeIndex(4);
            out.writeDouble((double) datum);
        }
    }

    @Override
    protected Number read(Object reuse, Decoder in) throws IOException {
        int index = in.readIndex();
        Number datum;
        switch (index) {
            case 0:
                in.readNull();
                return null;
            case 1:
                datum = in.readLong();
                return datum;
            case 2:
                datum = in.readFloat();
                return datum;
            case 3:
                datum = in.readInt();
                return datum;
            case 4:
                datum = in.readDouble();
                return datum;
        }
        return null;
    }

    protected Schema getSchema() {
        schema = Schema.createUnion(
                Schema.create(Schema.Type.NULL),
                Schema.create(Schema.Type.LONG),
                Schema.create(Schema.Type.FLOAT),
                Schema.create(Schema.Type.INT),
                Schema.create(Schema.Type.DOUBLE));
        return schema;
    }
}
