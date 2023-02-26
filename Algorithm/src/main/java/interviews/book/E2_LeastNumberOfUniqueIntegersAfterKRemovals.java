package interviews.book;

import java.util.*;

public class E2_LeastNumberOfUniqueIntegersAfterKRemovals {

    public static class Pair{
        int key;
        int value;
        public Pair(int key, int value){
            this.key=key;
            this.value=value;
        }

        @Override
        public String toString() {
            return key+":"+value;
        }
    }

    public static int findLeastNumOfUniqueInts(int[] arr, int k) {
        HashMap<Integer, Integer> mapCount=new HashMap<>();
        PriorityQueue<Pair> countQueueNumber=new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.value-o2.value;
            }
        });

        for(Integer num:arr){
            Integer currentNum=mapCount.get(num);

            if(currentNum==null){
                currentNum=1;
            }else{
                currentNum++;
            }
            mapCount.put(num, currentNum);
        }
//        System.out.println(mapCount);
        Set<Map.Entry<Integer, Integer>> entries = mapCount.entrySet();

        for(Map.Entry<Integer, Integer> entry:entries){
            countQueueNumber.add(new Pair(entry.getKey(), entry.getValue()));
        }
//        System.out.println(countQueueNumber);
        while (!countQueueNumber.isEmpty()){
            if(k>=countQueueNumber.peek().value){
                k-=countQueueNumber.peek().value;
                mapCount.remove(countQueueNumber.peek().key);
                countQueueNumber.poll();
            }else{
                break;
            }
        }
        return mapCount.size();
    }

    public static void main(String[] args) {
        int[] arr={4,3,1,1,3,3,2};
        int k=3;
        System.out.println(findLeastNumOfUniqueInts(arr, k));
        //
        //#Reference
        //1482. Minimum Number of Days to Make m Bouquets
        //2511. Maximum Enemy Forts That Can Be Captured
        //1288. Remove Covered Intervals
        //387. First Unique Character in a String
    }
}
