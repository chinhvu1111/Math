package E1_segment_tree;

public class E1_RangeSumQueryMutable {

    static class NumArray {

        public int[] tree;
        public int[] lazy;
        public int n;

        public NumArray(int[] nums) {
            int n=nums.length;
            this.n=n;
            tree=new int[4*n];
            lazy=new int[4*n];
            buildTree(nums, 0, nums.length-1, 0);
//            for (int i = 0; i < 4*n; i++) {
//                System.out.println(tree[i]);
//            }
        }

        public void buildTree(int[] nums, int left, int right, int index){
            if(left==right){
                tree[index] = nums[left];
                return;
            }
            int mid=left+(right-left)/2;
//            System.out.printf("left: %s, mid: %s\n", left, mid);
//            System.out.printf("mid+1: %s, right: %s\n", mid+1, right);
            buildTree(nums, left, mid, 2*index+1);
            buildTree(nums, mid+1, right, 2*index+2);
            tree[index]=tree[2*index+1]+tree[2*index+2];
        }

        public int query(int left, int right, int index, int qLeft, int qRight){
            //- Case:
            //+               (left, right)
            //(qLeft, qRight)
            //+ (left, right)
            //                (qLeft, qRight)
            //- Return 0
            if(qLeft>right || qRight<left){
                return 0;
            }
            //       (left---------right)
            //(qleft------------------------qRight
            //- Return immediately
            if(qLeft<=left&&right<=qRight){
                return tree[index];
            }
//            System.out.printf("left: %s, right: %s\n", left, right);
            int mid=left+(right-left)/2;
            int rsLeft = query(left, mid, 2*index+1, qLeft, qRight);
            int rsRight = query(mid+1, right, 2*index+2, qLeft, qRight);
            return rsLeft+rsRight;
        }
        public void updateQuery(int left, int right, int pos, int index, int val){
            if(pos < left || pos > right){
                return;
            }
            if(left==right){
                //Tree ==> correspond to index
                //==> Update: tree[index]=val
                tree[index]=val;
//                System.out.println("Update");
                return;
            }
            int mid = left+(right-left)/2;
//            System.out.printf("left: %s, mid: %s\n", left, mid);
//            System.out.printf("mid+1: %s, right: %s\n", mid+1, right);
            if(pos>mid){
                updateQuery(mid+1, right, pos, 2*index+2, val);
            }else{
                updateQuery(left, mid, pos, 2*index+1, val);
            }
            tree[index]=tree[2*index+1]+tree[2*index+2];
        }

        public void update(int index, int val) {
            updateQuery(0, n-1, index, 0, val);
        }

        public int sumRange(int left, int right) {
            return query(0, n-1,0, left, right);
        }
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given an integer array nums, handle multiple queries of the following types:
        //  + Update the value of an element in nums.
        //  + Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
        //- Implement the NumArray class:
        //- NumArray(int[] nums) Initializes the object with the integer array nums.
        //  + void update(int index, int val) Updates the value of nums[index] to be val.
        //  + int sumRange(int left, int right)
        //* Returns (the sum of the elements of nums)
        //  between indices (left and right) inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
        //
        //** Idea
        //1.
        //1.0, Segment tree
        //- Method-1:
        //+ Constraints:
        //1 <= nums.length <= 3 * 10^4
        //-100 <= nums[i] <= 100
        //0 <= index < nums.length
        //-100 <= val <= 100
        //0 <= left <= right < nums.length
        //At most 3 * 10^4 calls will be made to update and sumRange.
        //
        //* Brainstorm:
        //
        //Query cases:
        //- Return cases-1:
        //+               (left, right)
        //(qLeft, qRight)
        //+ (left, right)
        //                (qLeft, qRight)
        //- Return 0
        //- Return cases-2:
        //       (left---------right)
        //(qleft------------------------qRight
        //
        //- Note:
        //- Tree ==> correspond to index
        //==> Update: tree[index]=val
        //  + tree[index]=val;
        //
        //- Return immediately
        //
        //1.1, Special cases
        //
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Space: O(4*n)
        //- Time: O(log(n)/action + 4*n)
        //
        NumArray numArray = new NumArray(new int[]{1, 3, 5});
        System.out.println(numArray.sumRange(0, 2)); // return 1 + 3 + 5 = 9
        numArray.update(1, 2);   // nums = [1, 2, 5]
        for (int i = 0; i < numArray.tree.length; i++) {
            System.out.printf("%s, ", numArray.tree[i]);
        }
        System.out.println();
        System.out.println(numArray.sumRange(0, 2)); // return 1 + 2 + 5 = 8
    }
}
