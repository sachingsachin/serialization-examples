package examples_group.serialization;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.file.SeekableByteArrayInput;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.reflect.ReflectData;
import org.apache.avro.reflect.ReflectDatumReader;
import org.apache.avro.reflect.ReflectDatumWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Utils
{
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void printSchema (Schema s)
    {
        Map map = gson.fromJson(s.toString(), Map.class);
        System.out.println(gson.toJson(map));
    }

    public static <T> byte[] serializeWithFileWriter(String testType, T ... entityObjs) throws Exception {

        T entityObj1 = entityObjs[0];
        ReflectData rdata = ReflectData.AllowNull.get();

        Schema schema = rdata.getSchema(entityObj1.getClass());

        ReflectDatumWriter<T> datumWriter = new ReflectDatumWriter (entityObj1.getClass(), rdata);
        DataFileWriter<T> fileWriter = new DataFileWriter<T> (datumWriter);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        fileWriter.create(schema, baos);
        for (T entityObj : entityObjs)
        {
            fileWriter.append(entityObj);
        }
        fileWriter.close();

        byte[] binaryAvro = baos.toByteArray();
        return binaryAvro;
    }

    public static <T> byte[] serialize(String testType, T entityObj) throws Exception {

        ReflectData rdata = ReflectData.AllowNull.get();

        Schema schema = rdata.getSchema(entityObj.getClass());
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Encoder encoder = EncoderFactory.get().binaryEncoder(os, null);
        DatumWriter<T> writer = new ReflectDatumWriter<T>(schema, rdata);
        writer.write(entityObj, encoder);
        encoder.flush();
        byte[] binaryAvro = os.toByteArray();

        // ISO-8859-1 is the encoding which handles all bytes and not UTF-8
        // See https://issues.apache.org/jira/browse/AVRO-1650
        String binaryString = new String (binaryAvro, "ISO-8859-1");
        return binaryAvro;
    }

    public static <T> List<GenericRecord> readGenericDataWithFileWriter(byte[] binaryAvro) throws IOException
    {
        GenericDatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord> ();
        SeekableByteArrayInput avroInputStream = new SeekableByteArrayInput(binaryAvro);
        DataFileReader<GenericRecord> fileReader = new DataFileReader<GenericRecord>(avroInputStream, datumReader);

        Schema schema = fileReader.getSchema();
        GenericRecord record = null;
        List<GenericRecord> records = new ArrayList<GenericRecord> ();
        while (fileReader.hasNext())
        {
            records.add (fileReader.next(record));
        }
        fileReader.close();
        return records;
    }

    public static <T> List<T> readReflectDataWithFileWriter(byte[] binaryAvro) throws IOException
    {
        ReflectDatumReader<T> datumReader = new ReflectDatumReader<T> ();
        SeekableByteArrayInput avroInputStream = new SeekableByteArrayInput(binaryAvro);
        DataFileReader<T> fileReader = new DataFileReader<T>(avroInputStream, datumReader);

        Schema schema = fileReader.getSchema();
        T record = null;
        List<T> records = new ArrayList<T> ();
        while (fileReader.hasNext())
        {
            records.add (fileReader.next(record));
        }
        fileReader.close();
        return records;
    }

    public static GenericRecord readGenericData(byte[] binaryAvro, Schema schema) {

        BinaryDecoder decoder = DecoderFactory.get().binaryDecoder(binaryAvro, null);
        GenericDatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>(schema);

        GenericRecord record = null;
        try {
            record = datumReader.read(record, decoder);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return record;
    }

    public static <T> T readReflectData(byte[] binaryAvro, Schema schema) {

        ReflectData rdata = ReflectData.AllowNull.get();
        BinaryDecoder decoder = DecoderFactory.get().binaryDecoder(binaryAvro, null);
        ReflectDatumReader<T> datumReader = new ReflectDatumReader<T>(schema);

        T record = null;
        try {
            record = datumReader.read(record, decoder);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return record;
    }
}
