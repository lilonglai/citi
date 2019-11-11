import org.apache.flink.streaming.connectors.kafka.KafkaSerializationSchema;
import org.apache.kafka.clients.producer.ProducerRecord;

import javax.annotation.Nullable;

public class ResponseSerializationSchema implements KafkaSerializationSchema<Response> {
    @Override
    public ProducerRecord<byte[], byte[]> serialize(Response element, @Nullable Long timestamp) {
        ProducerRecord record = new ProducerRecord("flink_response", element.id, element.count);
        return record;
    }
}
