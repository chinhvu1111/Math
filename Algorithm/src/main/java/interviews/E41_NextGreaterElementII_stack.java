package interviews;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class E41_NextGreaterElementII_stack {

    public static int[] nextGreaterElements(int[] nums) {
        Stack<Integer> stack=new Stack<>();
        int n=nums.length;
        int dp[]=new int[n];
        Arrays.fill(dp, -1);
        boolean isCycle=false;

        if(n>=1){
            stack.push(0);
        }
        //2,5,3
        //5,-1,5
        for(int i=1;i<n;i++){
            while (!stack.isEmpty()&&nums[stack.peek()]<nums[i]){
                if(dp[stack.peek()]==-1){
                    dp[stack.peek()]=nums[i];
                }
                stack.pop();
            }
            stack.add(i);
            if(i==n-1&&!isCycle){
                i=-1;
                isCycle=true;
            }
        }
//        while(!stack.isEmpty()){
//            int top=stack.pop();
//            if(!stack.isEmpty()&&nums[stack.firstElement()]!=nums[top]){
//                dp[top]=nums[stack.firstElement()];
//            }
//        }
        return dp;
    }

    public static int[] nextGreaterElementsOptimized(int[] nums) {
        int[] ans = new int[nums.length];
        Stack<Integer> st = new Stack<>();

        for (int i = nums.length - 1; i >= 0; i--) {
            st.push(nums[i]);
        }

        //2,5,3
        //5,-1,5
        //2,5,(3)
        //steps:
        //- Traverse : n-1 --> 0
        //- stack : (3),5,2
        //3 : 1: add(3),5,2
        //5 : add(5)
        //2 : 5
        for (int i = nums.length - 1; i >= 0; i--) {
            int val = nums[i];

            while (st.size() > 0 && st.peek() <= val) {
                st.pop();
            }

            if (st.size() > 0) {
                ans[i] = st.peek();
            } else {
                ans[i] = -1;
            }

            st.push(val);
        }
        return ans;
    }

    public static void println(int[] arr){
        for(int i=0;i< arr.length;i++){
            System.out.printf("%s, ", arr[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
//        int nums[]=new int[]{1,2,3,4,3};
//        int nums[]=new int[]{1,2,1};
        int nums[]=new int[]{2,5,3};
        //Case 1: Các giá trị giảm dần stack full values ==> Cần tình tử tế lại
//        int nums[]=new int[]{5,4,3,2,1};
        //Expected : [-1,5,5,5,5]
        //Case 2: các giá trị bằng nhau:
//        int nums[]=new int[]{1,1,1,1,1};
//        int nums[]=new int[]{1,2,3,2,1};
        //Output :   [2,3,-1,3,3]
        //Expected : [2,3,-1,3,2]
//        int nums[]=new int[]{1,2,3,4,5,6,5,4,5,1,2,3};
        int dp[]=nextGreaterElements(nums);
        println(dp);
        int dp1[]=nextGreaterElementsOptimized(nums);
        println(dp1);
        System.out.println("");
        //
        //** Đề bài:
        //- Bài này mục đích là để in ra giá trị lớn hơn value tại (i) thảo mãn:
        //+ nums[j]> nums[i]
        //+ j gần i nhất
        //
        //** Bài này tư duy như sau:
        //Cách 1:
        //1,
        //1.1,
        //- Vì array dạng circle nên ta sẽ lựa chọn chạy theo circle như sau:
        //+ stack sẽ lưu index
        //+ Vì chỉ chạy 1 vòng --> i==n-1 ==> Ta sẽ đánh dấy isCycle tức là đánh dấu đã run cycle rồi
        //===> Nếu đến i==n-1 tiếp thì không chạy nữa break ra.
        //
        //1.2, Refactor
        //VD:
        //2,5,3
        //5,-1,5
        //2,5,(3)
        //steps:
        //- Traverse : n-1 --> 0
        //- stack : (3),5,2
        //3 : 1: add(3),5,2
        //5 : add(5)
        //2 : 5
        //Quy luật:
        //- Khi add 3 từ cuối --> Ta sẽ pop những phần tử top stack (0 --> right)
        //--> Sau khi tìm xong value của 3 --> Phần tử phía sau 3 (5) cần 3 --> right elements ==> Ta cần add 3 vào
        //--> Bài toán trở thành giống bài toàn duplicate array
        //VD:
        //3,5,2 ==> 3,5,(2), 3,5,2
        //==> Khi đó sẽ trở thành bài toán next greater bình thường
    }
}
