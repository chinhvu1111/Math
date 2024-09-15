package E1_leetcode_medium_dynamic;

import java.util.Arrays;

public class E174_CountAllPossibleRoutes {

    public static long[][] memo;
    public static int mod = 1_000_000_007;

    public static long solution(int curLocation, int curFuel, int[] locations, int finish){
        long curRs=0;
//        if(curFuel<0){
//            return 0;
//        }
        if(memo[curLocation][curFuel]!=-1){
            return memo[curLocation][curFuel];
        }
        if(curLocation==finish){
//            System.out.println(curFuel);
            curRs=(curRs+1)%mod;
        }
        for(int i=0;i<locations.length;i++){
            if(i!=curLocation&&Math.abs(locations[curLocation]-locations[i])<=curFuel){
                curRs=(curRs+solution(i, curFuel-Math.abs(locations[curLocation]-locations[i]), locations, finish))%mod;
            }
        }
        return memo[curLocation][curFuel]=curRs;
    }

    public static int countRoutes(int[] locations, int start, int finish, int fuel) {
        int n=locations.length;
        memo=new long[n][fuel+1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        return (int) solution(start, fuel, locations, finish);
    }

    public static int countRoutesIterative(int[] locations, int start, int finish, int fuel) {
        return 1;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an array of (distinct positive integers locations) where locations[i] represents (the position of city i).
        //- You are also (given integers) (start, finish and fuel) representing the (starting city, ending city,
        // and the (initial amount) of fuel) you have, respectively.
        //- At each step, if you are at (city i), you can pick (any city j) such that
        //  + (j != i) and (0 <= j < locations.length) and (move to city j).
        //- Moving from (city i to city j)
        //  + reduces the amount of fuel you have by |locations[i] - locations[j]|.
        //- Please notice that |x| denotes (the absolute value of x).
        //* Notice that
        //  - fuel cannot become negative at any point in time, and that
        //  - you are (allowed) to visit any city (more than once) (including start and finish).
        //* Return (the count of all possible routes) from (start to finish).
        //  + Tính số lượng routes từ (start -> finish)
        //+ Since the answer may be too large, return it modulo (10^9 + 7).
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1: Dynamic programming
        //- Constraint:
        //2 <= locations.length <= 100
        //1 <= locations[i] <= 10^9
        //All integers in locations are distinct.
        //0 <= start, finish < locations.length
        //1 <= fuel <= 200
        //  + Số khá lớn
        //      + ==> Long
        //
        //- Brainstorm
        //Example 1:
        //
        //Input: locations = [2,3,6,8,4], start = 1, finish = 3, fuel = 5
        //Output: 4
        //Explanation: The following are all possible routes, each uses 5 units of fuel:
        //1 -> 3
        //1 -> 2 -> 3
        //1 -> 4 -> 3
        //1 -> 4 -> 2 -> 3
        //- Bài này có thể dùng top down
        //  + Memo
        //- fuel không quá lớn <=200:
        //  + Dùng memo[start][fuel] được.
        //- Nếu đến đích thì return 1
        //  + Sau đó cộng dần dần đi lên
//        int[] locations = {2,3,6,8,4};
//        int start = 1, finish = 3, fuel = 5;
//        int[] locations = {4,3,1};
//        int start = 1, finish = 0, fuel = 6;
        //
        //** Kinh nghiệm:
        //- Không nên để giá trị memo[i][j]=0
        //  + Vì đôi khi nó sẽ có thể xảy ra giá trị ==0:
        //      ==> Thành ra tính liên tục gây sai
        //  ==> Nên cân nhẵc giá trị chuẩn hoặc chọn (MAX, -1) sẽ tốt hơn
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n*fuel)
        //- Time: O(n^2*fuel)
        //
        int[] locations = {22,74,92,86,12,68,64,19,79,10,69,13,62,18,87,88,33,96,78,73,57,42,91,17,55,26,27,67,60,46,72,41};
        int start = 30, finish = 29, fuel = 47;
        System.out.println(countRoutes(locations, start, finish, fuel));
        System.out.println(countRoutesIterative(locations, start, finish, fuel));
    }
}
