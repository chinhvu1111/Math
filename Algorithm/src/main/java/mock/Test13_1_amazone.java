package mock;

import java.util.HashMap;
import java.util.TreeSet;

public class Test13_1_amazone {

    public static boolean find132pattern(int[] nums) {
        int n=nums.length;
        HashMap<Integer, Integer> indexMapSmallerIndex=new HashMap<>();
        if(n<3){
            return false;
        }
        int beforeMin=nums[0];
        int prevIndex=0;

        for(int i=1;i<n;i++){
            if(nums[i]>beforeMin){
                indexMapSmallerIndex.put(i, prevIndex);
            }else{
                beforeMin=nums[i];
                prevIndex=i;
            }
        }
        TreeSet<Integer> beforeNodes=new TreeSet<>();
        beforeNodes.add(nums[n-1]);

        for(int i=n-2;i>=0;i--){
            Integer iElement=indexMapSmallerIndex.get(i);
            if(iElement==null){
                continue;
            }
            Integer value=beforeNodes.ceiling(nums[iElement]+1);
            if(value!=null&&value<nums[i]){
                System.out.printf("%s %s %s", nums[iElement], nums[i], value);
                return true;
            }
            beforeNodes.add(nums[i]);
        }
        return false;
    }

    public static void main(String[] args) {
        //** Requirement
        //- 132 pattern là dãy dạng:
        //+ i < j < k
        //+ nums[i] < nums[k] < nums[j]
        //* Return xem nums array có tồn tại 132 patterns hay không
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //+ n == nums.length
        //+ 1 <= n <= 2 * 10^5
        //+ -10^9 <= nums[i] <= 10^9
        //--> Không thể xử lý trong O(N^2)
        //
        //- Brainstorm
        //- Ta sẽ tìm (i và j) trước sau đó tìm k (>i,j)
        //- Để có thể thoải mái tìm (k)
        //+ nums[i] < nums[k] < nums[j] ==> ta sẽ tìm MIN(nums[i])
        //
        //- Với (i<j) và (nums[i]<nums[j])
        //+ Ta sẽ dùng binary search để tìm:
        //VD:
        //3,1(i),4(j),2
        //==> Tìm được (i),(j) ==> hash(i)= j
        //==> Ta sẽ traverse all array để tìm ra k.
//        int[] nums = {1,2,3,4};
//        int[] nums = {3,1,4,2};
//        int[] nums = {-1,3,2,0};
//        int[] nums = {1,3,2};
        int[] nums = {1,3,2,4,5,6,7,8,9,10};
        //1, 3, 2
        //Expected result : true
        System.out.println(find132pattern(nums));
        //#Reference
        //948. Bag of Tokens
        //1653. Minimum Deletions to Make String Balanced
        //2702. Minimum Operations to Make Numbers Non-positive
    }
}
