package E1_daily;

import java.util.Arrays;

public class E10_BoatsToSavePeople {

    public static int numRescueBoats(int[] people, int limit) {
        int n=people.length;
        Arrays.sort(people);

        int low=0, high=n-1;
        int rs=0;

        while(low<=high){
            if(people[low]+people[high]<=limit){
                people[low]=0;
                people[high]=0;
                low++;
                high--;
            }
//            else{
//                if(low>=high-1){
//                    rs+=high-low+1;
//                    break;
//                }
//                if(people[low+1]+people[high]>=people[low]+people[high-1]){
////                    people[high]=0;
//                    rs++;
//                    high--;
//                }else{
////                    people[low]=0;
//                    rs++;
//                    low++;
//                }
//            }
            else high--;
            rs++;
        }
//        for (int person : people) {
////            System.out.println(person);
//            if (person != 0) {
//                rs++;
//            }
//        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an array people where people[i] is the weight of (the ith person),
        // and (an infinite number of boats) where each boat can carry (a ("maximum") weight of limit).
        // + Each boat carries (at most two people) (at the same time),
        // + provided (the sum of the weight) of those people is (at most) (limit).
        //* Return the ("minimum") number of boats to carry every (given person).
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= people.length <= 5 * 10^4
        //1 <= people[i] <= limit <= 3 * 10^4
        //+ Limit >= all (peoples) ==> Mỗi boat có thể chở ít nhất 1 người
        //
        //- Brainstorm
        //- Để mà số thuyền MIN
        //  + Thuyền phải chia làm sao cho số lượng người trên mỗi thuyền là nhiều nhất
        //  + Các thuyền đối xứng nhau ==> 1 người không đi trước thì đi sau.
        //Ex:
        //Input: people = [3,2,2,1], limit = 3
        //Output: 3
        //Explanation: 3 boats (1, 2), (2) and (3)
        //Ex:
        //Input: people = [3,2,2,1], limit = 4
        //sorted -> 1,2,2,3
        //- Optimal solution = (1,3),(2,2)
        //==> Hướng đến việc đặt (min + max)
        //- Bài này hướng đến việc chọn:
        //  + Sao cho ghép đôi được nhiều nhất có thể
        //  ==> Bài toán (min left + max right) + 2 pointers.
        //
        //- Cases:
        //  + Nếu nums[low]+nums[high] > limit thì sao:
        //Ex:
        //1,2,4,10, limit =5
        //+ low=0, high=3
        //  + high--
        //  -> (1,4),(2),(10)
        //  + low++
        //  -> (1),(2),(4),(10)
        //- Ta phải tìm case mà (low++) --> tốt hơn (high--)
        //  + Tức là (low++) sẽ fit hơn là lấy (high--)
        //  ==> Không baoh có cases đó vì:
        //  + people[low]<people[low+1] ==> nếu people[low+1] ghép được thì people[low] ==> ghép được.
        //  ==> Tại sao ta lại bỏ qua people[low] khi mà nó còn ghép được.
        //  ==> giữa people[low] và people[high] ==> bỏ thằng to hơn đi (Vì nó chắc chắn không ghép được).
        //
//        int[] people = {3,2,2,1};
//        int limit = 3;
//        int[] people = {3,5,3,4};
//        int limit = 5;
//        int[] people = {1,2,4,10};
//        int limit = 5;
        int[] people = {3,8,4,9,2,2,7,1,6,10,6,7,1,7,7,6,4,4,10,1};
        int limit = 10;
        //#Reference:
        //786. K-th Smallest Prime Fraction
        //2702. Minimum Operations to Make Numbers Non-positive
        //2473. Minimum Cost to Buy Apples
        //321. Create Maximum Number
        //1471. The k Strongest Values in an Array
        //699. Falling Squares
        System.out.println(numRescueBoats(people, limit));
    }
}
