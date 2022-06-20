package interviews;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class E41_NextGreaterElementII {

    public static int[] nextGreaterElements(int[] nums) {
        Stack<Integer> stack=new Stack<>();
        int n=nums.length;
        int dp[]=new int[n];
        Arrays.fill(dp, -1);
        boolean isCycle=false;

        if(n>=1){
            stack.push(0);
        }
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

    public int[] nextGreaterElementsOptimized(int[] nums) {
        int[] ans = new int[nums.length];
        Stack<Integer> st = new Stack<>();

        for (int i = nums.length - 1; i >= 0; i--) {
            st.push(nums[i]);
        }

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

    public static void main(String[] args) {
        int nums[]=new int[]{1,2,3,4,3};
//        int nums[]=new int[]{1,2,1};
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
        System.out.println("");
    }
}
