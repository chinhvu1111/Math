package E1_daily;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class E269_FruitIntoBaskets {

    public static int totalFruit(int[] fruits) {
        int n=fruits.length;
        int start=0, i=0;
        HashMap<Integer, Integer> firstExists=new HashMap<>();
        HashSet<Integer> slide=new HashSet<>();
        //2,2,3,2,3,4,4,4
        //
        int rs=0;

        while (i<n){
            firstExists.put(fruits[i], i);
            while(slide.size()>=2&&start<i){
//                System.out.printf("%s %s\n",start, firstExists);
                if(firstExists.get(fruits[start])<=start){
                    slide.remove(fruits[start]);
//                    System.out.println(firstExists);
//                    System.out.printf("Remove: index=%s, val=%s, %s\n", start, fruits[start], slide);
                }
                start++;
//                System.out.printf("%s %s\n",start, slide);
            }
            slide.add(fruits[i]);
            while (i+1<n&&slide.size()==2&&slide.contains(fruits[i+1])){
                slide.add(fruits[i+1]);
                firstExists.put(fruits[i+1], i+1);
                i++;
            }
            if(slide.size()==2){
                rs=Math.max(rs, i-start+1);
            }
//            System.out.printf("%s %s\n", start, i);
//            System.out.println(slide);
            i++;
        }
        return rs;
    }

    public static int totalFruitRefer(int[] fruits) {
        // Hash map 'basket' to store the types of fruits.
        Map<Integer, Integer> basket = new HashMap<>();
        int left = 0, right;

        // Add fruit from right side (right) of the window.
        for (right = 0; right < fruits.length; ++right) {
            basket.put(fruits[right], basket.getOrDefault(fruits[right], 0) + 1);

            // If the current window has more than 2 types of fruit,
            // we remove one fruit from the left index (left) of the window.
            if (basket.size() > 2) {
                basket.put(fruits[left], basket.get(fruits[left]) - 1);
                if (basket.get(fruits[left]) == 0)
                    basket.remove(fruits[left]);
                left++;
            }
        }

        // Once we finish the iteration, the indexes left and right
        // stands for the longest valid subarray we encountered.
        return right - left;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are visiting a farm that has (a single row of fruit trees) arranged from (left to right).
        //- The trees are represented by an integer array fruits where fruits[i] is (the type of fruit) (the ith tree produces).
        //- You want to collect (as much fruit as) possible.
        //- However, the owner has (some strict rules) that you must follow:
        //  + You only have (two baskets), and (each basket) can only hold (a single type of fruit).
        //  There is (no limit) on the amount of fruit (each basket) can hold.
        //  + Starting from any tree of your choice, you must pick (exactly one fruit) from every tree (including the start tree)
        //  while moving to the right. The picked fruits must fit in one of your baskets.
        //  + Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
        //- Given the integer array fruits,
        //* Return (the maximum number of fruits) you can pick.
        //
        //Example 2:
        //
        //Input: fruits = [0,1,2,2]
        //Output: 3
        //Explanation: We can pick from trees [1,2,2].
        //If we had started at the first tree, we would only pick from trees [0,1].
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= fruits.length <= 10^5
        //0 <= fruits[i] < fruits.length
        //
        //* Brainstorm:
        //- Pick the window such that it contains (2 types of fruit)
        //==> Slide window
        //
        //1.1, Cases
        //
        //1.2, Optimization
        //- Use map count:
        //  + Remove when count == 0
        //  + If size == 2 ==> update rs.
        //- Mỗi lần go right:
        //  + Size>2 ==> Delete left (count)
        //- Mỗi lần thêm ==> Ta sẽ xoá nhiều nhất 1 lần
        //  ==> Đến cuối cùng sẽ là size <=2
        //- Nếu thêm 1 thằng gây size>2
        //  ===> Move slide window -> left 1 unit (Size không change)
        //  ==> Final state rs = end-start
        //
        //1.3, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        int[] fruits = {2,2,3,2,3,4,4,4};
//        int[] fruits = {1,2,3,2,2};
        System.out.println(totalFruit(fruits));
        System.out.println(totalFruitRefer(fruits));
    }
}
