package interviews;

import java.util.*;

public class E66_CombinationSumII {

    public static boolean visited[];
    public static List<List<Integer>> rs;
    public static Set<String> setMap;

    public static void solution(int currentSum, int[] candidates, int index, List<Integer> list){
        if(currentSum<=0){
            if(currentSum==0){
                rs.add(new LinkedList<>(list));
            }
            return;
        }

        boolean currentVisit[]=new boolean[51];

        for(int i=index;i<candidates.length;i++){
            if(candidates[i]>currentSum){
                break;
            }
            if(!visited[i]&&!currentVisit[candidates[i]]){
                list.add(candidates[i]);
                visited[i]=true;
                solution(currentSum-candidates[i], candidates, i, list);
                list.remove(list.size()-1);
                visited[i]=false;
                currentVisit[candidates[i]]=true;
            }
        }
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int n=candidates.length;
        visited=new boolean[n];
        setMap=new HashSet<>();
        rs=new ArrayList<>();
        Arrays.sort(candidates);
        solution(target, candidates, 0, new LinkedList<>());
        return rs;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{10,1,2,7,6,1,5};
        //Bài này ta tư duy như sau:
        //
        int arr[]=new int[]{2,5,2,1,2};
//        int target=8;
        int target=5;
        combinationSum2(arr, target);
        //#Reference:
        //Subsets
        //Subsets II
        //Permutations
        //Permutations II
        //Combinations
        //Combination Sum
        //Combination Sum III
        //Palindrome Partition
    }
}
