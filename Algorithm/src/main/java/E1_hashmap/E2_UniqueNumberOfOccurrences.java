package E1_hashmap;

import java.util.HashMap;
import java.util.HashSet;

public class E2_UniqueNumberOfOccurrences {

    public static boolean uniqueOccurrences(int[] arr) {
        HashMap<Integer, Integer> mapCount=new HashMap<>();

        for(Integer num: arr){
            mapCount.put(num, mapCount.getOrDefault(num, 0)+1);
        }
        HashSet<Integer> occurance=new HashSet<>();

        for(Integer key: mapCount.keySet()){
            int numOccurance=mapCount.get(key);

            if(occurance.contains(numOccurance)){
                return false;
            }
            occurance.add(numOccurance);
        }
        return true;
    }
    public static void main(String[] args) {
        //#Reference:
        //789. Escape The Ghosts
        //518. Coin Change II
        //2289. Steps to Make Array Non-decreasing
    }
}
