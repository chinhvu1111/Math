package E1_leetcode_medium_dynamic;

import java.util.Arrays;

public class E149_CountNumberOfTeams {

    public static int numTeamsWrong(int[] rating) {
        int n=rating.length;
        int[] lessCount=new int[n];
        int[] greaterCount=new int[n];

        for(int i=0;i<n;i++){
            int curRsLess=0;
            int countLess=0;
            int curRsGreater=0;
            int countGreater=0;
            for(int j=i-1;j>=0;j--){
                if(rating[i]>rating[j]){
                    curRsLess=Math.max(lessCount[j]+countLess, curRsLess);
                    countLess++;
                }
                if(rating[i]<rating[j]){
                    curRsGreater=Math.max(greaterCount[j]+countGreater, curRsGreater);
                    countGreater++;
                }
            }
            lessCount[i]=curRsLess;
            greaterCount[i]=curRsGreater;
        }
        int rs=0;

        for(int i=0;i<n;i++){
            rs=Math.max(rs, lessCount[i]+greaterCount[i]);
        }
        return rs;
    }

    public static int numTeams(int[] rating) {
        int n=rating.length;
        int[] lessCount=new int[n];
        int[] greaterCount=new int[n];

        for(int i=0;i<n;i++){
            int countLess=0;
            int countGreater=0;
            for(int j=i-1;j>=0;j--){
                if(rating[i]>rating[j]){
                    countLess++;
                }
                if(rating[i]<rating[j]){
                    countGreater++;
                }
            }
            lessCount[i]=countLess;
            greaterCount[i]=countGreater;
        }
//        Arrays.stream(lessCount).forEach(System.out::println);
//        Arrays.stream(greaterCount).forEach(System.out::println);
        int rs=0;
        for(int i=0;i<n;i++){
            for(int j=i-1;j>=0;j--){
                if(rating[i]>rating[j]){
                    rs+=lessCount[j];
                }
                if(rating[i]<rating[j]){
                    rs+=greaterCount[j];
                }
            }
        }
        return rs;
    }

    public static int numTeamsOptimization(int[] rating) {
        int n=rating.length;
        int[] lessCount=new int[n];
        int[] greaterCount=new int[n];

        for(int i=0;i<n;i++){
            int countLess=0;
            int countGreater=0;
            for(int j=i-1;j>=0;j--){
                if(rating[i]>rating[j]){
                    countLess++;
                }
                if(rating[i]<rating[j]){
                    countGreater++;
                }
            }
            lessCount[i]=countLess;
            greaterCount[i]=countGreater;
        }
//        Arrays.stream(lessCount).forEach(System.out::println);
//        Arrays.stream(greaterCount).forEach(System.out::println);
        int rs=0;
        for(int i=0;i<n;i++){
            for(int j=i-1;j>=0;j--){
                if(rating[i]>rating[j]){
                    rs+=lessCount[j];
                }
                if(rating[i]<rating[j]){
                    rs+=greaterCount[j];
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- There are n soldiers standing in a line. Each soldier is assigned (a unique rating value).
        //You have to form a team of 3 soldiers amongst them under the following rules:
        //- Choose 3 soldiers with index (i, j, k) with rating (rating[i], rating[j], rating[k]).
        //  + A team is valid if: (rating[i] < rating[j] < rating[k]) or (rating[i] > rating[j] > rating[k]) where (0 <= i < j < k < n).
        //* Return the number of teams you can form given the conditions. (soldiers can be part of multiple teams).
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //n == rating.length
        //3 <= n <= 1000
        //1 <= rating[i] <= 10^5
        //All the integers in rating are (unique).
        //- N không quá lớn
        //
        //- Brainstorm
        //Ex:
        // Input: rating = [2,5,3,4,1]
        //Output: 3
        //Explanation: We can form three teams given the conditions. (2,3,4), (5,4,1), (5,3,1).
        //==> Với dạng bài này dùng prefix max được.
        //- 2 prefix max là xong.
        //
        //- nums[i]<nums[j]<nums[k] (Giữa khoảng (j,k) có thể có nhiều phần tử >=< nums[j] ==> Đã từng làm sai đoạn này khi mất O(N^2) để tìm max)
        //+ Ta sẽ tính từng layer trước
        //Ex:
        //int[] rating = {2,5,3,4,1};
        //lessCount = {0,1,1,2,0} : Số lượng value nhỏ hơn nó ở đằng trước
        //lessCount2 = {0,0,0,1,0} : Sum số lượng lessCount mà thoả mãn (nums[i]>nums[j])
//        int[] rating = {2,5,3,4,1};
        //
        //1.1, Optimization
        //- Mình có thể shorten code đi
        //- Giảm space O(n) -> O(1)
        //- Ta có:
        //  + i < j < k
        //      + rating[i] < rating[j] < rating[k]
        //      + rating[i] > rating[j] > rating[k]
        //  ==> Ta sẽ cố định j và tìm ra 2 phía left, right:
        //  => rs tại (j) = less[j]*greater[j] + greater[j]*less[j]
        //
        //1.2, Complexity
        //- Space : O(n)
        //- Time : O(n^2)
        //
        int[] rating = {2,1,3};
        System.out.println(numTeamsWrong(rating));
        System.out.println(numTeams(rating));
        System.out.println(numTeamsOptimization(rating));
    }
}
