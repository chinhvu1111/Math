package interviews;

import java.util.*;

public class E65_CombinationSum {

    public static char[]frequency;
    public static Set<String> setMap;
    public static List<List<Integer>> rs;
    public static Map<Integer, List<List<Integer>>> mapSum;

    public static void subCombinatioSum(int[] candidates, char frequency[], List<Integer> list, int currentSum){

        if(currentSum<=0){
            if(currentSum==0){
                String currentStr= Arrays.toString(frequency);

                if(!setMap.contains(currentStr)){
                    setMap.add(currentStr);
                    rs.add(new ArrayList<Integer>(list));
                }
            }
            return;
        }
        List<List<Integer>> lists=mapSum.get(currentSum);

        if(lists!=null){
            for(List<Integer> integers: lists){
                List<Integer> integerList=new LinkedList<>();
                integerList.addAll(list);
                integerList.addAll(integers);
                rs.add(integerList);
            }
            return;
        }

        for(int i=0;i<candidates.length;i++){
            if(candidates[i]>=150){
                continue;
            }
            frequency[candidates[i]]++;
            list.add(candidates[i]);
            subCombinatioSum(candidates, frequency, list,currentSum-candidates[i]);
            mapSum.put(currentSum, new LinkedList<>(rs));
            frequency[candidates[i]]--;
            list.remove(list.size()-1);
        }
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        frequency=new char[201];
        setMap=new HashSet<>();
        rs=new LinkedList<>();
        mapSum=new HashMap<>();

        subCombinatioSum(candidates, frequency, new LinkedList<>(), target);
        return rs;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{2,3,5};
        int arr[]=new int[]{100,200,4,12};
//        combinationSum(arr, 8);
        combinationSum(arr, 400);
        System.out.println("");
    }
}
