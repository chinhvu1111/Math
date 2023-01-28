package interviews.bytedance;

import javafx.util.Pair;

import java.util.*;

public class E28_TimeBasedKeyValueStore_BinarySearch {

    public HashMap<String, List<Pair<Integer, String>>> store;

    public E28_TimeBasedKeyValueStore_BinarySearch() {
        store=new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        List<Pair<Integer, String>> values = store.get(key);

        if(values==null){
            values=new ArrayList<>();
        }
        values.add(new Pair<>(timestamp, value));
        store.put(key, values);
    }

    public String get(String key, int timestamp) {
        List<Pair<Integer, String>> values = store.get(key);

        if(values==null){
            return "";
        }

        return getFloorKey(store.get(key), timestamp);
    }

    public String getFloorKey(List<Pair<Integer, String>> listValues, int timestamp){
        int high=listValues.size()-1;
        int low=0;
        int rs=-1;

        while (high>low){
            int mid=(high+low)/2;
            if(timestamp==listValues.get(mid).getKey()){
                return listValues.get(mid).getValue();
            }
            if(timestamp>listValues.get(mid).getKey()){
                rs=low;
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        if(rs==-1){
            return "";
        }
        return listValues.get(rs).getValue();
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
        //2. Implement binary search
        //2.1,
        //- Bài này là 1 dạng tìm value lớn nhất nhưng <= value có sẵn.
        //+ Ta sẽ dùng rs để gán vào 1 trong 2 predicates low/ high tuỳ thuộc vào việc ta muốn tìm =</> value.
        //- Chú ý nếu có dấu = thì cái ta cần là phải thêm high>=low vì:
        //+ Sẽ có case value == timestamp --> ta cần thêm case high>=low vì:
        //+ high==low ==> Đã bị break rồi ta không thể xét mid= (high+low)/2 được nữa.
        //
        //- Refactor 1 chút :
        //####Code
        //if(timestamp==listValues.get(mid).getKey()){
        //                return listValues.get(mid).getValue();
        //            }
        //==> Có thể bỏ đi bằng cách biến đổi.
        //####Code
        //(high>=low)
        //
        //if(timestamp>listValues.get(mid).getKey()){
        //                rs=low;
        //                low=mid+1;
        //            }
        //==> Thành
        //if(timestamp>=listValues.get(mid).getKey()){
        //                rs=mid;
        //                low=mid+1;
        //            }
        //- Vì cái ta cần là kết quả gần nhất mà <= timestamp + (sau đó sẽ timestamp< value mới / break loop (gía trị đó max rồi) )
        //
        //3, Complexity
        //3.1, Time complexity:
        //- set : O(1)
        //- get : O(log(k)), k là số phần tử hiện tại có trong list.
        E28_TimeBasedKeyValueStore_BinarySearch ins=new E28_TimeBasedKeyValueStore_BinarySearch();
        ins.set("love","high",10);
        ins.set("love","low",20);
        //Case 1: nếu không tìm ra index
        //- Cần return -1
//        System.out.println(ins.get("love",5));
        //Case 2: Nếu tìm ra nhưng nằm ở giữa
//        System.out.println(ins.get("love",15));
        //Case 3: Tìm ra nhưng đúng timestamp đó
        System.out.println(ins.get("love",20));
        //#Reference:
        //982. Triples with Bitwise AND Equal To Zero
        //2034. Stock Price Fluctuation
    }
}
