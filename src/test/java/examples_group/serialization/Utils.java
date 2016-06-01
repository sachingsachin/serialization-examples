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

    public static <T> byte[] serialize(String testType, T ... entityObjs) throws Exception {

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

        byte[] bytes = baos.toByteArray();
        return bytes;
    }

    public static <T> List<GenericRecord> readGenericData(byte[] bytes) throws IOException
    {
        GenericDatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord> ();
        SeekableByteArrayInput avroInputStream = new SeekableByteArrayInput(bytes);
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

    public static <T> List<T> readReflectData(byte[] bytes) throws IOException
    {
        ReflectDatumReader<T> datumReader = new ReflectDatumReader<T> ();
        SeekableByteArrayInput avroInputStream = new SeekableByteArrayInput(bytes);
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
}
