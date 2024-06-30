package E1_daily;

import java.util.Arrays;

public class E35_MagneticForceBetweenTwoBalls {

    public static int search(int[] position, int n, int index, int val){
        int low=0, high=n-1;
        int rs=-1;

        while(low<=high){
            int mid=low+(high-low)/2;
            if(position[mid]>=val){
                rs=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return rs;
    }

    public static boolean isValid(int[] position, int minDist, int n, int m){
        int i=0;
        int count=0;

        //Time: O(n/m)
        while(i!=-1&&i<n){
            int curVal=position[i];
            //Time : O(log(n))
            int nextIndex=search(position, n, i+1, curVal+minDist);
            if(nextIndex!=-1){
                count++;
            }
            i=nextIndex;
        }
        return count+1>=m;
    }

    public static int maxDistance(int[] position, int m) {
        int n= position.length;
        //Time : O(n*log(n))
        Arrays.sort(position);
        int low=1, high=position[n-1];
        int rs=-1;

        //Time : O(log(max))
        while(low<=high){
            int mid=low+(high-low)/2;
            if(isValid(position, mid, n, m)){
//                System.out.println(mid);
                rs=mid;
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- In the universe Earth C-137, Rick discovered (a special form of magnetic force) between two balls if they are put in his (new invented basket).
        //- Rick has (n empty baskets), the (ith basket) is at position[i],
        //- Morty has (m balls) and needs to distribute the balls into the baskets such that the (minimum magnetic force) between any two balls is (maximum).
        //- Rick stated that magnetic force between two different balls at (positions x and y) is |x - y|.
        //Given the integer array position and the integer m.
        //* Return the required force.
        //Ex:
        //Input: position = [1,2,3,4,7], m = 3
        //Output: 3
        //Explanation: Distributing the 3 balls into baskets 1, 4 and 7 will make the magnetic force between ball pairs [3, 3, 6].
        //The minimum magnetic force is 3. We cannot achieve a larger minimum magnetic force than 3.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint:
        //n == position.length
        //2 <= n <= 10^5
        //1 <= position[i] <= 10^9
        //All integers in position are distinct.
        //2 <= m <= position.length
        //+ N khá lớn ==> Time: O(n), O(n*log(n))
        //+ Position lớn <=10^9
        //+ m cũng khá lớn
        //
        //- Brainstorm
        //- Max distance ==> min nhất trong 1 case
        //- Sort position incrementally
        //- Nếu ta chọn min --> sát quá ==> Kết quả sau sẽ nhỏ không phải là case tốt
        //
        //- m>=n : return 1
        //
        //Ex:
        //Input: position = [1,2,3,4,7], m = 3
        //Output: 3
        //- Bài này nếu làm dynamic thì khá tệ => dp[n][n]
        //==> Position lớn : Nên ta sẽ search kết quả thay vì tìm nó trực tiếp
        //
        //- Kết quả min của all distances:
        //+ 1<=rs<=10^9
        //- Check xem current rs ==> Thoả mãn hay không
        //  + MIN RANGE = rs ==> Có cho width = m hay không
        //
        //- Dùng min distance để chia position
        //Ex:
        //1,2,3,4,7
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(log(n))
        //- Time: O(log(n)*log(max)*n/m)
        //
        //#Reference:
        //2126. Destroying Asteroids
        //2333. Minimum Sum of Squared Difference
        //2602. Minimum Operations to Make All Array Elements Equal
        int[] position = {1,2,3,4,7};
        int m = 3;
        System.out.println(maxDistance(position, m));
    }
}
