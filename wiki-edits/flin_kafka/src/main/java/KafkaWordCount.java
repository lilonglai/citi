import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.util.Collector;

import java.util.Properties;

public class KafkaWordCount {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        env.setBufferTimeout(200);
        // 获取数据源
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("group.id", "flink_consumer4");
        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<>("flink_request", new SimpleStringSchema(), properties);
        //consumer.setStartFromEarliest();
        consumer.setStartFromLatest();

        Properties produceProperties = new Properties();
        produceProperties.setProperty("bootstrap.servers", "localhost:9092");
        FlinkKafkaProducer<Response> producer = new FlinkKafkaProducer<>("flink_response",new ResponseSerializationSchema(), produceProperties, FlinkKafkaProducer.Semantic.EXACTLY_ONCE);

        env.addSource(consumer).map(( str)->{
            long startTime = System.currentTimeMillis();
            String message = str;
            int index = message.lastIndexOf(" ");

            Request request = new Request();
            request.id = message.substring(index+1);
            request.message =  message.substring(0, index);
            long endTime = System.currentTimeMillis();
            System.out.println("1," + request.id + "," + startTime + "," + (endTime - startTime));
            return request;
        }).map(request ->{
            return request;
        }).flatMap(new FlatMapFunction<Request, Request>() {
            @Override
            public void flatMap(Request value, Collector<Request> out) throws Exception {
                String message = value.message;
                String[] tokens = message.toLowerCase().split("\\W+");
                for (String token : tokens) {
                    if (token.length() > 0) {
                        Request result = new Request();
                        result.message = token;
                        result.id = value.id;
                        out.collect(result);
                    }
                }
            }
        }).map(new MapFunction<Request, Response>(){
            public Response map(Request request) throws Exception{
                Response response = new Response();
                response.id = request.id;
                response.message = request.message;
                response.count = 1;
                return response;
            }
        }).setParallelism(2).keyBy(r -> r.id).timeWindow(Time.milliseconds(400))
                .trigger(new ResponseTrigger())
                .aggregate(new AggregateFunction<Response, Response, Response>() {
                    @Override
                    public Response createAccumulator() {
                        return new Response();
                    }

                    @Override
                    public Response add(Response value, Response accumulator) {
                        Response res = new Response();
                        res.id = value.id;
                        res.count = accumulator.count + value.count;
                        res.message = accumulator.message + " " + value.message;
                        return res;
                    }

                    @Override
                    public Response getResult(Response accumulator) {
                        return accumulator;
                    }

                    @Override
                    public Response merge(Response a, Response b) {
                        if(a == null){
                            return b;
                        }

                        if(b  == null) {
                            return a;
                        }

                        Response res = new Response();
                        res.id = a.id;
                        res.count = a.count + b.count;
                        res.message = a.message + " " + b.message;
                        return null;
                    }
                }).addSink(producer);
        // 提交任务
        env.execute("Kafka Example");
    }


}
