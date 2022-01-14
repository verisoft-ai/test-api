package co.verisoft.fw.xray;

import co.verisoft.fw.utils.JsonObject;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Deprecated
public abstract class XrayJsonObject <T> implements JsonObject {

    private Map<Object,Object> dataSet;


    public XrayJsonObject(){
        dataSet = new HashMap<>();
    }

    public XrayJsonObject setValue(Object key, Object value){
        dataSet.put(key, value);
        return this;
    }

    public T getValue(Object key){
        return (T)dataSet.get(key);
    }


    public Map getMap(){
        return this.dataSet;
    }


    @Override
    public JSONObject asJsonObject() {
        JSONObject obj = new JSONObject();

        Iterator it = this.dataSet.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            obj.put(pair.getKey(), pair.getValue());
        }
        return obj;
    }


    @Override
    public String asString() {
        return this.asJsonObject().toJSONString();
    }
}
