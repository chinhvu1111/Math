package interviews;

import java.util.Stack;

public class E224_MaximumScoreOfAGoodSubarray_stack {

    public static int maximumScore(int[] nums, int k) {
        int n=nums.length;
        int[] left=new int[n];
        int[] right=new int[n];

        for(int i=0;i<n;i++){
            left[i]=i;
            right[i]=i;
        }
//        leftMin[0]=nums[0];
//        rightMin[0]=nums[n-1];

        Stack<Integer> stack=new Stack<>();
        stack.add(0);

        for(int i=1;i<n;i++){
            while (!stack.isEmpty()&&nums[stack.peek()]>=nums[i]){
                stack.pop();
            }
            if(!stack.isEmpty()){
                left[i]=stack.peek();
            }else{
                left[i]=-1;
            }
            stack.add(i);
        }
        stack=new Stack<>();
        stack.add(n-1);
        for(int i=n-2;i>=0;i--){
            while (!stack.isEmpty()&&nums[stack.peek()]>=nums[i]){
                stack.pop();
            }
            if(!stack.isEmpty()){
                right[i]=stack.peek();
            }else{
                right[i]=n;
            }
            stack.add(i);
//            System.out.printf("%s ", i);
//            System.out.println(stack);
        }
//        println(left);
//        println(right);
        int rs=0;

        for(int i=0;i<n;i++){
            int currentValue=nums[i];
            int indexMinLeft=left[i]+1;
            int indexMinRight=right[i]-1;

            if(left[i]==i){
                indexMinLeft=i;
            }
            if(right[i]==i){
                indexMinRight=i;
            }

            if(indexMinLeft<=k&&indexMinRight>=k){
                rs=Math.max(rs, currentValue*(indexMinRight-indexMinLeft+1));
//                System.out.printf("%s %s %s value %s, ", indexMinLeft, indexMinRight,
//                        currentValue, currentValue * (indexMinRight-indexMinLeft+1));
//                System.out.println();
            }

//            int j;
//            for(j=i-1;j>=0;j--){
//                if(nums[j]<currentValue){
//                    break;
//                }
//                indexMinLeft=j;
//            }
//            for(j=i+1;j<n;j++){
//                if(nums[j]<currentValue){
//                    break;
//                }
//                indexMinRight=j;
//            }
//            if(indexMinLeft<=k&&indexMinRight>=k){
//                rs=Math.max(rs, currentValue * (indexMinRight-indexMinLeft+1));
////                System.out.printf("%s %s %s value %s, ", indexMinLeft, indexMinRight,
////                        currentValue, currentValue * (indexMinRight-indexMinLeft+1));
////                System.out.println();
//            }
        }
        return rs;
    }

    public static int maximumScoreRefactor(int[] nums, int k) {
        int n=nums.length;
        int[] left=new int[n];
        int[] right=new int[n];

        for(int i=0;i<n;i++){
            left[i]=i;
            right[i]=i;
        }
//        leftMin[0]=nums[0];
//        rightMin[0]=nums[n-1];

        Stack<Integer> stack=new Stack<>();
        stack.add(0);

        for(int i=1;i<n;i++){
            while (!stack.isEmpty()&&nums[stack.peek()]>=nums[i]){
                stack.pop();
            }
            if(!stack.isEmpty()){
                left[i]=stack.peek();
            }else{
                left[i]=-1;
            }
            stack.add(i);
        }
        stack=new Stack<>();
        stack.add(n-1);
        int rs=0;
        for(int i=n-1;i>=0;i--){
            if(i!=n-1){
                while (!stack.isEmpty()&&nums[stack.peek()]>=nums[i]){
                    stack.pop();
                }
                if(!stack.isEmpty()){
                    right[i]=stack.peek();
                }else{
                    right[i]=n;
                }
                stack.add(i);
            }
            int currentValue=nums[i];
            int indexMinLeft=left[i]+1;
            int indexMinRight=right[i]-1;

            if(left[i]==i){
                indexMinLeft=i;
            }
            if(right[i]==i){
                indexMinRight=i;
            }

            if(indexMinLeft<=k&&indexMinRight>=k){
                rs=Math.max(rs, currentValue*(indexMinRight-indexMinLeft+1));
//                System.out.printf("%s %s %s value %s, ", indexMinLeft, indexMinRight,
//                        currentValue, currentValue * (indexMinRight-indexMinLeft+1));
//                System.out.println();
            }
//            System.out.printf("%s ", i);
//            System.out.println(stack);
        }
//        println(left);
//        println(right);
        return rs;
    }

    public static void println(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.printf("%s, ",arr[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
//        int[] arr=new int[]{1,4,3,7,4,5};
//        int k=3;
        int[] arr=new int[]{5,5,4,5,4,1,1,1};
        int k=0;
//        int[] arr=new int[]{1};
//        int k=0;
//        int[] arr=new int[]{};
//        int k=0;
        System.out.println(maximumScore(arr, k));
        System.out.println(maximumScoreRefactor(arr, k));
    }
}
