package E1_leetcode_medium_dynamic;

import java.util.ArrayList;
import java.util.List;

public class E127_MaximumSumOfTwoNonOverlappingSubarrays {
    public static class Node{
        int val;
        int start;
        int end;
        public Node(int val, int start, int end){
            this.val=val;
            this.start=start;
            this.end=end;
        }

        @Override
        public String toString() {
            return val+" "+start+" "+end;
        }
    }

    public static int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int n=nums.length;
        List<Node> maxFirst=new ArrayList<>();
        List<Node> maxSecond=new ArrayList<>();
        int[] prefixSum = new int[n+1];

        for(int i=1;i<=n;i++){
            prefixSum[i]=prefixSum[i-1]+nums[i-1];
        }
        //slide window
        int sumFirst=0;
        int sumSecond=0;
        int curLengthFirst=1;
        int curLengthSecond=1;
        for(int i=1;i<=n;i++){
            //0,1,2
            //1,2,3
            if(i>=firstLen){
                maxFirst.add(new Node(prefixSum[i]-prefixSum[i-firstLen], i-firstLen+1, i));
            }
            if(i>=secondLen){
                maxSecond.add(new Node(prefixSum[i]-prefixSum[i-secondLen], i-secondLen+1, i));
            }
        }
        int k=maxFirst.size();
        int l=maxSecond.size();
        int rs=0;
        for(int i=0;i<k;i++){
            for(int j=0;j<l;j++){
                Node curFirst=maxFirst.get(i);
                Node curSecond=maxSecond.get(j);
                if(curFirst.end<curSecond.start||curSecond.end<curFirst.start){
                    rs=Math.max(rs, curFirst.val + curSecond.val);
//                    System.out.printf("%s : %s, val: %s\n", curFirst, curSecond, curFirst.val+curSecond.val);
                }
            }
        }
//        System.out.println(maxFirst);
//        System.out.println(maxSecond);
        return rs;
    }

    public static int maxSumTwoNoOverlapOptimization(int[] nums, int firstLen, int secondLen) {
        int n=nums.length;
        int[] prefixSumLeft = new int[n+1];
        int[] prefixSumRight = new int[n+1];

        for(int i=1;i<=n;i++){
            prefixSumLeft[i]=prefixSumLeft[i-1]+nums[i-1];
            prefixSumRight[n-i]=prefixSumRight[n-i+1]+nums[n-i];
        }
        //slide window
        int firstMaxLeft[]=new int[n+1];
        int firstMaxRight[]=new int[n+1];
        int secondMaxLeft[]=new int[n+1];
        int secondMaxRight[]=new int[n+1];
        for(int i=1;i<=n;i++){
            //0,1,2
            //1,2,3
            if(i>=firstLen){
                firstMaxLeft[i]=Math.max(firstMaxLeft[i-1], prefixSumLeft[i]-prefixSumLeft[i-firstLen]);
            }
            if(i>=secondLen){
                secondMaxLeft[i]=Math.max(secondMaxLeft[i-1], prefixSumLeft[i]-prefixSumLeft[i-secondLen]);
            }
        }
        for(int i=n;i>=1;i--){
            //0,1,2
            //1,2,3
            if(i+firstLen<=n){
                firstMaxRight[i-1]=Math.max(firstMaxRight[i], prefixSumRight[i]-prefixSumRight[i+firstLen]);
            }
            if(i+secondLen<=n){
                secondMaxRight[i]=Math.max(secondMaxRight[i+1], prefixSumRight[i]-prefixSumRight[i+secondLen]);
            }
        }
        //0,1,(2),3,4
        //4,3,(2),1,0
        int rs=0;
        for(int i=1;i<=n;i++){
            rs=Math.max(rs, firstMaxLeft[i]+secondMaxRight[i]);
            rs=Math.max(rs, secondMaxLeft[i]+firstMaxRight[i]);
            rs=Math.max(rs, firstMaxLeft[i-1]+secondMaxRight[i-1]);
            rs=Math.max(rs, secondMaxLeft[i-1]+firstMaxRight[i-1]);
//            if(i>=1){
//                // first left, second right
//                rs=Math.max(rs, firstMaxLeft[i-1]+secondMaxRight[i]);
//                //second left, first right
//                rs=Math.max(rs, secondMaxLeft[i-1]+firstMaxRight[i]);
//            }
//            //first left, second right
//            if(i>=2){
//                rs=Math.max(rs, firstMaxLeft[i-2]+secondMaxRight[i-1]);
//                rs=Math.max(rs, secondMaxLeft[i-2]+firstMaxRight[i-1]);
//            }
        }
//        System.out.println(maxFirst);
//        System.out.println(maxSecond);
        return rs;
    }

    public static int maxSumTwoNoOverlapOptimizationRefer(int[] nums, int firstLen, int secondLen) {
        int n=nums.length;
        int[]  prefixMax=new int[n];
        int i=0, j=0, sum=0;
        while(j<n){
            sum+=nums[j];
            if(j==firstLen-1){
                prefixMax[j]=sum;
            }else if(j>firstLen-1){
                sum=sum-nums[i];
                prefixMax[j]=Math.max(prefixMax[j-1], sum);
                i++;
            }
            j++;
        }
        int[] suffixMax=new int[n];
        i=j=n-1;
        sum=0;
        while(j>=0){
            sum+=nums[j];
            if(j==n-firstLen){
                suffixMax[j]=sum;
            }else if(j<n-firstLen){
                sum=sum-nums[i--];
                suffixMax[j]=Math.max(suffixMax[j+1], sum);
            }
            j--;
        }
        int rs=0;
        j=0;
        i=0;
        sum=0;

        while(j<n){
            sum+=nums[j];
            if (j==secondLen-1) {
                rs=Math.max(rs, sum + suffixMax[j+1]);
            }else if(j>secondLen-1){
                sum = sum - nums[i];
                int opt1 = sum + prefixMax[i];
                int opt2 = sum + (j!=n-1? suffixMax[j+1]: 0);
                rs=Math.max(rs, Math.max(opt1, opt2));
                i++;
            }
            j++;
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- Given an integer (array nums) and two integers (firstLen) and (secondLen),
        //* Return the maximum (sum of elements) in two non-overlapping subarrays with lengths (firstLen) and (secondLen).
        //- The array with length (firstLen) could occur before or after the array with length (secondLen), but they have to be (non-overlapping).
        //A subarray is a contiguous part of an array.
        //- Tìm max sum của các elements của 2 arrays với length là (firstLen) và (secondLen) + không overlap nhau.
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //1 <= firstLen, secondLen <= 1000
        //2 <= firstLen + secondLen <= 1000
        //firstLen + secondLen <= nums.length <= 1000
        //0 <= nums[i] <= 1000
        //
        //- Brainstorm
        //Ex:
        //nums = [3,8,1,3,2,1,8,9,0], firstLen = 3, secondLen = 2
        //+ Tìm max sum của 2 subarry với length=3/2
        //- Có nên tìm max sum với length = x, y riêng rẽ không.
        //- Check 2 arrays có overlap ntn
        //  + Lưu (start, end)
        //- Các case non overlap:
        //  + (x,y), (x1,y1)
        //
        //- Bài này ngoài giải bằng O(n^2) ta có thể xử lý bằng O(n):
        //- Bằng cách tìm:
        //  + Max nhất left/ right có chiều dài lần lượt là first/ second length tại (index=i)
        //- Với idea này ta sẽ tạo 4 arrays ==> Có vẻ hơi slow.
        //1.1, Optimization
        //- Tối ưu hơn 1 chút bằng cách tìm max nhất:
        //  + Xuất phát từ đầu : prefixMax
        //  + Xuất phát từ cuối : suffixMax
        //  ==> Với độ dài firstLen
        //- Sau đó không cần tìm tương tự với độ dài secondLen
        //  + Chỉ cần loop qua all elements, chia thành 2 cases:
        //      + prefixMax[i] (Độ dài bằng firstLen) + sum (Slide window với độ dài bằng secondLen)
        //      + sum (Slide window với độ dài bằng secondLen) + suffixMax[i] (Độ dài bằng firstLen)
        //
        //1.2, Complexity
        //- Space : O(n)
        //- Time : O(n)
        //
        //#Reference:
        //2779. Maximum Beauty of an Array After Applying Operation
        //3009. Maximum Number of Intersections on the Chart
        //1044. Longest Duplicate Substring
//        int[] nums= new int[]{0,6,5,2,2,5,1,9,4};
        int[] nums= new int[]{1,0,3};
        int firstLen=1;
        int secondLen=2;
        System.out.println(maxSumTwoNoOverlap(nums, 1,2));
//        System.out.println(maxSumTwoNoOverlapOptimization(nums, 1,2));
        System.out.println(maxSumTwoNoOverlapOptimization(nums, firstLen,secondLen));
        System.out.println(maxSumTwoNoOverlapOptimizationRefer(nums, firstLen,secondLen));
        //#Reference:
        //1413. Minimum Value to Get Positive Step by Step Sum
        //2244. Minimum Rounds to Complete All Tasks
        //2209. Minimum White Tiles After Covering With Carpets
    }
}
