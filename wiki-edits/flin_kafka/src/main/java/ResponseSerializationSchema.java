import org.apache.flink.streaming.connectors.kafka.KafkaSerializationSchema;
import org.apache.kafka.clients.producer.ProducerRecord;

import javax.annotation.Nullable;

public class ResponseSerializationSchema implements KafkaSerializationSchema<Response> {
    @Override
    public ProducerRecord<byte[], byte[]> serialize(Response element, @Nullable Long timestamp) {
        long startTime = System.currentTimeMillis();
        byte[] key = element.id.getBytes();
        byte[] value = String.valueOf(element.count).getBytes();
        ProducerRecord record = new ProducerRecord("flink_response", key, value);
        long endTime = System.currentTimeMillis();
        System.out.println("2," + element.id + "," + startTime + "," + (endTime - startTime));
        return record;
    }
}
