package E1_String;

import java.util.ArrayList;
import java.util.List;

public class E15_OutputContestMatches {

    //0, 3 ==> mid=1
    public static String solution(int low, int high, int[] nums){
//        System.out.printf("%s %s\n", low, high);
        if(low==high-1){
            return "("+nums[low]+","+nums[high]+")";
        }
        int mid=low+(high-low)/2;
        return "("+solution(low, mid, nums)+","+solution(mid+1, high, nums)+")";
    }

    public static String findContestMatchWrong(int n) {
        int[] arr=new int[n];
        int low=1, high=n;
        int index=0;

        while(low<high){
//            System.out.printf("%s %s %s\n",index, low, high);
            arr[index]=low;
            arr[index+1]=high;
            low++;
            high--;
            index+=2;
        }
        for (int i = 0; i < n; i++) {
            System.out.printf("%s, ",arr[i]);
        }
        System.out.println();
        return solution(0, n-1, arr);
    }

    public static String findContestMatch(int n) {
        List<String> rs=new ArrayList<>();

        //Time : O(n)
        for(int i=1;i<=n;i++){
            rs.add(String.valueOf(i));
        }
        int numLayers= (int) (Math.log(n) / Math.log(2));
//        System.out.println(numLayers);

        //(1),2,3,4,5,6,7,(8)
        ////Time : O(log(n))
        for(int i=1;i<=numLayers;i++){
            int index=0;
            int size=rs.size();
            List<String> newList=new ArrayList<>();

            //Time : O(n/2+n/4+...)
            //Time : O(n)
            while(index<size/2){
                newList.add("("+rs.get(index)+","+rs.get(size-index-1)+")");
                index+=1;
            }
            rs=newList;
//            System.out.println(newList);
        }
        return rs.get(0);
    }

    public static String findContestMatchOptimization(int n) {
        List<String> rs=new ArrayList<>();

        //Time : O(n)
        for(int i=1;i<=n;i++){
            rs.add(String.valueOf(i));
        }

        int size=n/2+1;
        //(1),2,3,4,5,6,7,(8)
        ////Time : O(log(n))
        while(size>1){
            int index=0;
            size=rs.size();
            List<String> newList=new ArrayList<>();

            while(index<size/2){
                newList.add("("+rs.get(index)+","+rs.get(size-index-1)+")");
                index+=1;
            }
            size=newList.size();
//            System.out.printf("%s %s\n",size, newList);
            rs=newList;
        }
        return rs.get(0);
    }

    public static void main(String[] args) {
        //* Requirement
        //-
        //
        //** Idea
        //1.
        //1.0, Idea
        //- Constraint
        //n == 2x where x in in the range [1, 12].
        //
        //- Brainstorm
        //E.g:
        //n = 8
        //1,2,3,4,5,6,7,8
        //--> Sort
        //1,8,2,7,3,6,4,5
        //-->
        //((1,8),(4,5)),((2,7),(3,6))
        //--> Recursive this partition this into (2 parts) each time
        //
        //- (1,8),
        //
        //1.1, Optimization
        //- Replace the log function
        //
        //1.2, Complexity
        //- Space : O(n)
        //- Time : O(n*log(n) + n + log_function_complexity())
//        int n=4;
        int n=8;
//        int n=16;
        System.out.println(findContestMatch(n));
        System.out.println(findContestMatchOptimization(n));
        //#Reference:
        //2751. Robot Collisions
        //537. Complex Number Multiplication
        //290. Word Pattern
    }
}
