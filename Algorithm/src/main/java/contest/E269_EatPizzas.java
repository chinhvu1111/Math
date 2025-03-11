package contest;

import java.util.Arrays;
import java.util.Collections;

public class E269_EatPizzas {

    public static long maxWeight(int[] pizzas) {
        int n=pizzas.length;
        Arrays.sort(pizzas);
        long rs=0;
        int days = n/4;
        int index=n-1;

        //even
        for(int i=1;i<=days;i+=2){
            rs+=pizzas[index];
            index--;
        }
        //odd
        for(int i=2;i<=days;i+=2){
            rs+=pizzas[index-1];
//            System.out.println(pizzas[index-1]);
            index-=2;
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an integer array pizzas of size n, where pizzas[i] represents the weight of the ith pizza.
        // Every day, you eat exactly 4 pizzas. Due to your incredible metabolism,
        // when you eat pizzas of weights W, X, Y, and Z, where W <= X <= Y <= Z, you gain the weight of only 1 pizza!
        //  + On odd-numbered days (1-indexed), you gain a weight of Z.
        //  + On even-numbered days, you gain a weight of Y.
        //* Find the maximum total weight you can gain by eating all pizzas optimally.
        //* Note: It is guaranteed that n is a multiple of 4, and each pizza can be eaten only once.
        //
        //Example 1:
        //
        //Input: pizzas = [1,2,3,4,5,6,7,8]
        //Output: 14
        //Explanation:
        //
        //On day 1, you eat pizzas at indices [1, 2, 4, 7] = [2, 3, 5, 8]. You gain a weight of 8.
        //On day 2, you eat pizzas at indices [0, 3, 5, 6] = [1, 4, 6, 7]. You gain a weight of 6.
        //The total weight gained after eating all the pizzas is 8 + 6 = 14.
        //
        //* Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //4 <= n == pizzas.length <= 2 * 10^5
        //1 <= pizzas[i] <= 10^5
        //n is a multiple of 4.
        //  + Length<=10^5 ==> Time: O(n*k)
        //
        //- Brainstorm
        //- max days = n/4
        //- W, X, Y, and Z, where W <= X <= Y <= Z
        //  + Odd: Z
        //  + Even: Y
        //
        //- Sorted arr
        //Example 1:
        //
        //Input: pizzas = [1,2,3,4,5,6,7,8]
        //- rs = 3+8
        //- rs = 4+7
        //4+8 <=> 3+5
        //  ==> choose 4,8
        //4+9 <=> 3+13
        //  ==> choose 3,13
        //==> We can not check 1 pair to make decision
        //Ex:
        //Input: pizzas = [1,2,3,4,5,6,7,8]
        //- Odd: [2,3,4,5]
        //- Even: [1,6,7,8]
        //==> It is not optimal
        //Ex:
        //Input: pizzas = [1,2,3,4,5,6,7,8]
        //- Odd:
        //  + [4,5,6,7]
        //- Even:
        //  + [1,2,3,8]
        //
        //- a,[b],c,[d]
        //+ b+d <=> a+c
        //  + a<b
        //  + c<d
        //- Rules:
        //- [x,x,x,x....(a),c,(b)]
        //==> a+b
//        int[] pizzas = {2,1,1,1,1,1,1,1};
//        int[] pizzas = {1,2,3,4,6,7,8,9,11,15,16,19};
        //4
        //1,3
        int[] pizzas = {1,2,3,4,6,7,8,9,11,15,16,19,20,22,25,30};
        //19+15+11 = 45
//        int[] pizzas = {5,2,2,4,3,3,1,3,2,5,4,2};
        //Output =   13
        //Expected: = 14
        //[1, 2, 2, 2, 2, 3, 3, 3, [4], [4], 5, [5]]
        //5 + 4 + 4 = 13
        //- 1,2,2,5
        //- 2,3,4,5 ==> Không nhất thiết phải đưa vào odd ==> Để even hết
        //- days =3
        //  + 1,3
        //  + 2
        System.out.println(maxWeight(pizzas));
    }
}
