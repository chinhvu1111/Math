package E1_leetcode_medium_dynamic;

import java.util.*;

public class E125_Combinations {
    public static HashSet<Integer> checkUnique;
    public static List<List<Integer>> rs;
    public static void solution(
            int index, int k, int n,
            LinkedList<Integer> curList, int curVal, int curCollectionVal){
//        System.out.printf("%s %s\n", curList, curCollectionVal);
        if(index==k){
//            System.out.printf("%s %s\n", curList, curCollectionVal);
            if(checkUnique.contains(curCollectionVal)){
               return;
            }
            rs.add(new ArrayList<>(curList));
            checkUnique.add(curCollectionVal);
            return;
        }
        for(int i=curVal;i<=n;i++){
            //00010
            curList.add(i);
            solution(index+1, k, n, curList, i+1, curCollectionVal | (1<<i));
            curList.removeLast();
        }
    }

    public static List<List<Integer>> combine(int n, int k) {
        rs=new ArrayList<>();
        checkUnique=new HashSet<>();
        solution(0, k, n, new LinkedList<>(), 1, 0);
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- Given two integers n and k
        //* Return all possible (combinations of k numbers) chosen from the range [1, n].
        //* You may return the answer in any order.
        //Ex:
        //Input: n = 4, k = 2
        //Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
        //Ex:
        //Input: n = 4, k = 3
        //Output : [1,2,3]...
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //1 <= n <= 20
        //1 <= k <= n
        //
        //- Brainstorm
        //Ex:
        //Input: n = 4, k = 3
        //Output : [1,2,3], [1,2,4],[2,3,4]...
        //1,2,[1 -> 4] ==> Số sau khác các số trước.
        //- Ta có thể dùng bit để phân biệt các collection
        //
        //- Lỗi sai:
        //  + LinkedList --> Remove last chứ không phải poll
        //
        //- Phần này ta không cần phải check unique
        //  + Ta đang chọn theo greedy rồi.
        //
        //#Reference:
        //1655. Distribute Repeating Integers
        //2664. The Knight’s Tour
        //2397. Maximum Rows Covered by Columns
        //
        System.out.println(1<<21-1);
        System.out.println(1<<0);
        System.out.println(1<<3 | 1<<4);
        int n = 4, k = 2;
//        int n = 20, k = 16;
        System.out.println(combine(n, k));
        //
    }
}
