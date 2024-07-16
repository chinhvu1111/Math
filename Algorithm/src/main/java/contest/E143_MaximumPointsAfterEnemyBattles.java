package contest;

import java.util.Arrays;

public class E143_MaximumPointsAfterEnemyBattles {

//    public static int findIndex(int[] enemyEnergies, int val, int n){
//        int low=0, high=n-1;
//        int rs=-1;
//        //0,1,4,7
//        //val = 3
//        //==>
//        while (low>=high){
//            int mid=low+(high-low)/2;
//            if(enemyEnergies[mid]>=val){
//                rs=
//                high=mid-1;
//            }
//        }
//    }

    public static long maximumPoints(int[] enemyEnergies, int currentEnergy) {
        int n=enemyEnergies.length;
        long rs=0;
        Arrays.sort(enemyEnergies);
        int right=n-1;
        long totalCurrentEnergy=currentEnergy;

        if(enemyEnergies[0]>totalCurrentEnergy){
            return 0L;
        }
        //1,2,5,9
        //val = 3
        //3-val[1]
        int left=0;

        //Input: enemyEnergies = [3,2,2], currentEnergy = 2
        //[2,2,3]
        //left=0: rs+=2/2=1
        //  + 2-2 = 0
        //
        //nums = [2,2,2,2,2]
        while (right>=0){
            rs+=(totalCurrentEnergy/enemyEnergies[left]);
            //val=3, x=2
            //==> val = 1
            totalCurrentEnergy=totalCurrentEnergy%enemyEnergies[left];
            totalCurrentEnergy+=enemyEnergies[right];
            right--;
        }
        return rs;
    }
    public static void main(String[] args) {
        //* Requirement
        //- You are given an integer array enemyEnergies denoting the energy values of various enemies.
        //- You are also given an integer currentEnergy denoting the amount of energy you have initially.
        //- You start with 0 points, and all the enemies are unmarked initially.
        //- You can perform either of the following operations zero or multiple times to gain points:
        //  - Choose an unmarked enemy, i, such that currentEnergy >= enemyEnergies[i]. By choosing this option:
        //      + You gain 1 point.
        //      + Your energy is reduced by the enemy's energy, i.e. currentEnergy = currentEnergy - enemyEnergies[i].
        //  - If you have at least 1 point, you can choose an unmarked enemy, i. By choosing this option:
        //      + Your energy increases by the enemy's energy, i.e. currentEnergy = currentEnergy + enemyEnergies[i].
        //      + The enemy i is marked.
        //* Return an integer denoting the maximum points you can get in the end by optimally performing operations.
        //- Kill enemies thoải mái ==> Get point
        //  + Energy giảm ==> (not mark)
        //- Có ít nhất 1 point:
        //  + Nhận thêm energy từ enemy ==> (Mark i)
        //
        //* Return max points
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= enemyEnergies.length <= 10^5
        //1 <= enemyEnergies[i] <= 10^9
        //0 <= currentEnergy <= 10^9
        //==> Time: O(n*(k~log(n)))
        //==> Có vẻ binary search
        //
        //- Brainstorm
        //- Max point == n
        //  + Cùng lắm là nhận hết enemies
        //
        //Example 1:
        //Input: enemyEnergies = [3,2,2], currentEnergy = 2
        //Output: 3
        //Explanation:
        //
        //The following operations can be performed to get 3 points, which is the maximum:
        //- First operation on enemy 1: points increases by 1, and currentEnergy decreases by 2. So, points = 1, and currentEnergy = 0.
        //- Second operation on enemy 0: currentEnergy increases by 3, and enemy 0 is marked. So, points = 1, currentEnergy = 3, and marked enemies = [0].
        //- First operation on enemy 2: points increases by 1, and currentEnergy decreases by 2. So, points = 2, currentEnergy = 1, and marked enemies = [0].
        //- Second operation on enemy 2: currentEnergy increases by 2, and enemy 2 is marked. So, points = 2, currentEnergy = 3, and marked enemies = [0, 2].
        //- First operation on enemy 1: points increases by 1, and currentEnergy decreases by 2. So, points = 3, currentEnergy = 1, and marked enemies = [0, 2].
        //
        //- Có 1 point rồi ==> energy thoải mái
        //- Có thể gain 1 enemy nhiều lần
        //  ==> K point = x/y
        //- Gain point --> Ưu tiên (small -> big) energy
        //
        //
//        int[]  enemyEnergies = {3,2,2};
//        int currentEnergy = 2;
        int[]  enemyEnergies = {2};
        int currentEnergy = 10;
        System.out.println(maximumPoints(enemyEnergies, currentEnergy));
    }
}
