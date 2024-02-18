package E1_leetcode_medium_dynamic;

import java.util.HashMap;
import java.util.HashSet;

public class E100_TwoSumIIIDataStructureDesign {

    HashMap<Integer, Integer> setNum;

    public E100_TwoSumIIIDataStructureDesign() {
        setNum=new HashMap<>();
    }

    public void add(int number) {
        setNum.put(number, setNum.getOrDefault(number, 0)+1);
    }

    public boolean find(int value) {
        for(int key: setNum.keySet()){
            int remainVal=value-key;

            if(remainVal==key&&setNum.get(key)>1){
                return true;
            }else if(remainVal!=key&&setNum.containsKey(remainVal)){
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        //#Reference:
        //288. Unique Word Abbreviation
    }
}
