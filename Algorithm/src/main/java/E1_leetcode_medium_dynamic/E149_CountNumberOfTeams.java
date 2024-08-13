package E1_leetcode_medium_dynamic;

import java.util.Arrays;

public class E149_CountNumberOfTeams {

    public static int numTeamsWrong(int[] rating) {
        int n = rating.length;
        int[] lessCount = new int[n];
        int[] greaterCount = new int[n];

        for (int i = 0; i < n; i++) {
            int curRsLess = 0;
            int countLess = 0;
            int curRsGreater = 0;
            int countGreater = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (rating[i] > rating[j]) {
                    curRsLess = Math.max(lessCount[j] + countLess, curRsLess);
                    countLess++;
                }
                if (rating[i] < rating[j]) {
                    curRsGreater = Math.max(greaterCount[j] + countGreater, curRsGreater);
                    countGreater++;
                }
            }
            lessCount[i] = curRsLess;
            greaterCount[i] = curRsGreater;
        }
        int rs = 0;

        for (int i = 0; i < n; i++) {
            rs = Math.max(rs, lessCount[i] + greaterCount[i]);
        }
        return rs;
    }

    public static int numTeams(int[] rating) {
        int n = rating.length;
        int[] lessCount = new int[n];
        int[] greaterCount = new int[n];

        for (int i = 0; i < n; i++) {
            int countLess = 0;
            int countGreater = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (rating[i] > rating[j]) {
                    countLess++;
                }
                if (rating[i] < rating[j]) {
                    countGreater++;
                }
            }
            lessCount[i] = countLess;
            greaterCount[i] = countGreater;
        }
//        Arrays.stream(lessCount).forEach(System.out::println);
//        Arrays.stream(greaterCount).forEach(System.out::println);
        int rs = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (rating[i] > rating[j]) {
                    rs += lessCount[j];
                }
                if (rating[i] < rating[j]) {
                    rs += greaterCount[j];
                }
            }
        }
        return rs;
    }

    public static int numTeamsOptimization(int[] rating) {
        int n = rating.length;
        int[] lessCount = new int[n];
        int[] greaterCount = new int[n];

        for (int i = 0; i < n; i++) {
            int countLess = 0;
            int countGreater = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (rating[i] > rating[j]) {
                    countLess++;
                }
                if (rating[i] < rating[j]) {
                    countGreater++;
                }
            }
            lessCount[i] = countLess;
            greaterCount[i] = countGreater;
        }
//        Arrays.stream(lessCount).forEach(System.out::println);
//        Arrays.stream(greaterCount).forEach(System.out::println);
        int rs = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (rating[i] > rating[j]) {
                    rs += lessCount[j];
                }
                if (rating[i] < rating[j]) {
                    rs += greaterCount[j];
                }
            }
        }
        return rs;
    }

    public static int numTeamsOptimization1(int[] rating) {
        int res = 0;
        for (int i = 1; i < rating.length - 1; ++i) {
            int less[] = new int[2], greater[] = new int[2];
            for (int j = 0; j < rating.length; ++j) {
                if (rating[i] < rating[j])
                    ++less[j > i ? 1 : 0];
                if (rating[i] > rating[j])
                    ++greater[j > i ? 1 : 0];
            }
            res += less[0] * greater[1] + greater[0] * less[1];
        }
        return res;
    }

    public static int getIncreaseVal(int index, int[] rating, int currentSize, int[][] increaseMemo, int size){
        int n=rating.length;

        if(index==n){
            return 0;
        }
        if(currentSize==size){
            return 1;
        }
        if(increaseMemo[index][currentSize]!=0){
            return increaseMemo[index][currentSize];
        }
        int rs=0;
        for(int i=index+1;i<n;i++){
            if(rating[i]>rating[index]){
                rs+=getIncreaseVal(i, rating, currentSize+1, increaseMemo, size);
            }
        }
        return increaseMemo[index][currentSize]=rs;
    }

    public static int getDecreaseVal(int index, int[] rating, int currentSize, int[][] decreaseMemo, int size){
        int n=rating.length;

        if(index==n){
            return 0;
        }
        if(currentSize==size){
            return 1;
        }
        if(decreaseMemo[index][currentSize]!=0){
            return decreaseMemo[index][currentSize];
        }
        int rs=0;
        for(int i=index+1;i<n;i++){
            if(rating[i]<rating[index]){
                rs+=getDecreaseVal(i, rating, currentSize+1, decreaseMemo, size);
            }
        }
        return decreaseMemo[index][currentSize]=rs;
    }

    public static int numTeamsOptimizationMemo(int[] rating) {
        int n=rating.length;
        int rs=0;
        int[][] increaseMemo=new int[n][3];
        int[][] decreaseMemo=new int[n][3];

        for(int start=0;start<n;start++){
            rs+=getIncreaseVal(start, rating, 1, increaseMemo, 3)
                    + getDecreaseVal(start, rating, 1, decreaseMemo, 3);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- There are (n soldiers) standing in a line. Each soldier is assigned (a unique rating value).
        //- You have to form a team of 3 soldiers amongst them under the following rules:
        //- Choose 3 soldiers with index (i, j, k) with rating (rating[i], rating[j], rating[k]).
        //  + A team is valid if:
        //      + (rating[i] < rating[j] < rating[k])
        //      or (rating[i] > rating[j] > rating[k]) where (0 <= i < j < k < n).
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
        //- Hoặc là có thể xét từng pair(i,j) lúc sau:
        //  + if rating[i] < rating[j]:
        //      + rs+=greaterCount[j]
        //  + if rating[i] > rating[j]:
        //      + rs+=lessCount[i]
        //
        //1.2, Complexity
        //- Space : O(n)
        //- Time : O(n^2)
        //
        //2.
        //2.0, Dynamic programming (Top down)
        //- Tìm số triple (i,j,k) unique thoả mãn điều kiện
        //- Bình thường nếu dừng bottom up --> Thì sẽ lưu array with one dimension
        //- Nếu dùng memo thì sao?
        //  + Nếu cố định start index ==> Ta sẽ loop để tìm các pair(i,j) còn lại.
        //      + Khi chọn start index thì có 2 cases:
        //          + Increase
        //          + Decrease
        //      ==> Sẽ loop 2 chiều
        //  + Trong mỗi method increase or decrease:
        //      + Mình sẽ đếm số cặp thoả mãn ==> return lại là được.
        //  + Vì là 3 số:
        //      + Ta sẽ đi 3 layers ==> nếu count==3 + thoả mãn condition:
        //          + Return 1
        //  + Caching ntn?
        //      + Vì có thể có nhiều layers ==> không phải là tripple (3) nữa
        //          ==> Có thể nhiều hơn nên ta cần caching ngay cả những thằng <3
        //      + Ta next new position dựa trên current index:
        //          + memo[currentIndex][layer]
        //
        //2.1, Optimization
        //2.2, Complexity
        //- Space: O(n+1)
        //- Time: O(n^2)
        //
        //3. Binary Indexed Tree (Fenwick Tree)
        //3.0,
        //- Range Sum Query - Mutable
        //- Count of Smaller Numbers After Self
        //
        //https://cs.stackexchange.com/questions/10538/bit-what-is-the-intuition-behind-a-binary-indexed-tree-and-how-was-it-thought-a
        //
        //
//        int[] rating = {2, 1, 3};
        int[] rating = {2,5,3,4,1};
        System.out.println(numTeamsWrong(rating));
        System.out.println(numTeams(rating));
        System.out.println(numTeamsOptimization(rating));
        System.out.println(numTeamsOptimization1(rating));
        System.out.println(numTeamsOptimizationMemo(rating));
    }
}
