package interviews.bytedance;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class E28_TimeBasedKeyValueStore_TreeMap {

    public HashMap<String, TreeMap<Integer, String>> store;

    public E28_TimeBasedKeyValueStore_TreeMap() {
        store=new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        TreeMap<Integer, String> values=store.get(key);

        if(values==null){
            values=new TreeMap<>();
        }
        values.put(timestamp, value);
        store.put(key, values);
    }

    public String get(String key, int timestamp) {
        TreeMap<Integer, String> values=store.get(key);

        if(values==null){
            return "";
        }
        Map.Entry<Integer, String> val = values.floorEntry(timestamp);

        if(val==null){
            return "";
        }
        return values.floorEntry(timestamp).getValue();
    }
    public static void main(String[] args) {
        //** Đề bài:
        //
        //** Bài này tư duy như sau:
        //1.
        //1.1,
        //- key, value để có thể lấy được value ra.
        //- Mỗi key sẽ có nhiều timestamp --> Được sắp xếp tăng dần ==> Để có thể tìm nhanh nhất.
        //- Key --> List (value, timestamp) được sắp xếp Theo timestamp.
        //Hash<key, treeMap<Timestamp, value>>
        //1.2, Complexity:
        //- Time complexity:
        //
        //#Reference:
        //982. Triples with Bitwise AND Equal To Zero
        //2034. Stock Price Fluctuation
    }
}
