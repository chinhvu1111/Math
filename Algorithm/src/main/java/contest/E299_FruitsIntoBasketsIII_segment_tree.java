package contest;

import java.util.Arrays;

public class E299_FruitsIntoBasketsIII_segment_tree {
    public static int searchIndex(int[] fruits, int key, boolean[] visited){
        int low = 0, high=fruits.length-1;
        int rs=-1;
        //3,5,8
        //4=> 5
        while(low<=high){
            int mid=low+(high-low)/2;
            if(fruits[mid]<=key){
                if(!visited[mid]){
                    rs=mid;
                }
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return rs;
    }

    public static class SegmentTree{
        public int[] tree;
        public SegmentTree(int n){
            tree=new int[4*n];
        }
        public void buildTree(int[] basket, int left, int right, int index){
            if(left==right){
                tree[index]=basket[left];
                return;
            }
            int mid=left+(right-left)/2;
            buildTree(basket, left, mid, 2*index+1);
            buildTree(basket, mid+1, right, 2*index+2);
            tree[index]=Math.max(tree[2*index+1], tree[2*index+2]);
        }
        public int queryRange(int left, int right, int index, int k){
            if(tree[index]>k){
                return -1;
            }
            if(left==right){
                tree[index]=-1;
                return left;
            }
            int mid=left+(right-left)/2;
            int pos;
            //Root is max
            //+ (left,mid)
            //+ (mid+1,right)
            //
            //* Main point:
            //- Nếu (left, mid) >=k
            //  ==> Thì ưu tiên lấy (lefmost)
            //  <> lấy right
            if(tree[2*mid+1]>=k){
                pos=queryRange(left, mid, 2*index+1, k);
            }else{
                pos=queryRange(mid+1, right, 2*index+2, k);
            }
            tree[index]=Math.max(tree[2*index+1], tree[2*index+2]);
            return pos;
        }

    }

    public static int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n=fruits.length;
        int rs=0;
        SegmentTree segmentTree=new SegmentTree(n);
        segmentTree.buildTree(baskets, 0, n-1, 0);

        for(int i=0;i<n;i++){
            int pos = segmentTree.queryRange(0, n-1, 0, fruits[i]);
            if(pos==-1){
                rs++;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given two arrays of integers, fruits and baskets, each of length n,
        // where fruits[i] represents the quantity of the ith type of fruit, and baskets[j] represents the capacity of the jth basket.
        //- From left to right, place the fruits according to these rules:
        //  + Each fruit type must be placed in the leftmost available basket with a capacity greater than or equal to the quantity of that fruit type.
        //  + Each basket can hold only one type of fruit.
        //  + If a fruit type cannot be placed in any basket, it remains unplaced.
        //* Return the number of fruit types that remain unplaced after all possible allocations are made.
        //
        // Idea
        //1
        //1.0,
        //- Method-1:
        //+ Constraints:
        //n == fruits.length == baskets.length
        //1 <= n <= 10^5
        //1 <= fruits[i], baskets[i] <= 10^9
        //  + n<=10^5 ==> Time: O(n)
        //  + val <=10^9 ==> Long + Time: O(n*log(n))
        //
        //- Brainstorm
        //- We need to find the leftmost elemen is greater than x
        //  + This element is not visited
        //- We should loop (baskets) rather than (fruits)
        //  + If we use this method:
        //  Ex: int[]  fruits = {4,2,5}, baskets = {3,5,4};
        //  Explanation:
        //      + fruits[0] = 4 is placed in baskets[1] = 5.
        //      + fruits[1] = 2 is placed in baskets[0] = 3.
        //      + fruits[2] = 5 cannot be placed in baskets[2] = 4.
        //- Could we use TreeMap?
        //Ex:
        //baskets = [5,3,(6)]
        //  + index=1 is visited
        //- fruits[i] = 2
        //  + index=0
        //- Sort:
        //  + We only have 1 way to (search)
        //  => Invalid
        //
        //- Example 1:
        //Input:
        //fruits = [4,2,5],
        //baskets = [3,5,4]
        //  + The arrow will be across
        //
        //- We can use the segment tree to solve that
        //Input:
        //fruits = [4,2,5],
        //baskets = [3,5,4]
        //- Rs:
        //  + For fruits[i] = 4 ==> Find the 5
        //  + For fruits[i] = 2 ==> Find the 3
        //  + For fruits[i] = 5 ==> Can not find any
        //- Leftmost characteristic:
        //- For each basket:
        //  + We find (the fruit[i]) is less than (the current basket) with (smallest index)
        //- Or we can find vice verses:
        //  + For (each fruit[i]) we find (the max left most) >= fruits[i]
        //      + If we visited the fruits[i] and then marking that as -1
        //
        //
        //1.1, Cases
        //
        //
        //1.2, Optimize
        //
        //
        //1.3, Complexity
        //
        //
        int[]  fruits = {4,2,5}, baskets = {3,5,4};
//        int[]  fruits = {63,40,1}, baskets = {59,93,20};
        System.out.println(numOfUnplacedFruits(fruits, baskets));
    }
}
