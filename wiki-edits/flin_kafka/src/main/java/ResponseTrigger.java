import org.apache.flink.streaming.api.windowing.triggers.Trigger;
import org.apache.flink.streaming.api.windowing.triggers.TriggerResult;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;

import java.util.HashMap;
import java.util.Map;

public class ResponseTrigger extends Trigger<Response, TimeWindow> {
    private Map<String, Long> map = new HashMap<>();
    @Override
    public TriggerResult onElement(Response element, long timestamp, TimeWindow window, TriggerContext ctx) throws Exception {
        String id = element.id;
        if(!map.containsKey(id)){
            map.put(id, 1L);
            return TriggerResult.CONTINUE;
        }
        else if(map.get(id) == 7){
            map.remove(id);
            return TriggerResult.FIRE;
        }
        else {
            map.put(id, map.get(id) + 1);
            return TriggerResult.CONTINUE;
        }
    }

    @Override
    public TriggerResult onProcessingTime(long time, TimeWindow window, TriggerContext ctx) throws Exception {
        return TriggerResult.CONTINUE;
    }

    @Override
    public TriggerResult onEventTime(long time, TimeWindow window, TriggerContext ctx) throws Exception {
        return TriggerResult.CONTINUE;
    }

    @Override
    public void clear(TimeWindow window, TriggerContext ctx) throws Exception {

    }
}
