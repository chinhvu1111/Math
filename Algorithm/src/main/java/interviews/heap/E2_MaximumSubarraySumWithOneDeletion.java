package interviews.heap;

public class E2_MaximumSubarraySumWithOneDeletion {

    public static int maximumSumWrong(int[] arr) {
        int n=arr.length;
        int sum=arr[0];
        int tempMin=arr[0];
        int rs=arr[0];
        int numberElement=0;

        for(int i=1;i<n;i++){
            tempMin=Math.min(tempMin, arr[i]);
            //Reseting sum value
            if(sum-tempMin<0){
                sum=arr[i];
                rs=Math.max(rs, sum);
                tempMin=Integer.MAX_VALUE;
                numberElement=1;
            }else{
                //Adding more element to sum value
                numberElement++;
                sum+=arr[i];
                tempMin=Math.min(tempMin, arr[i]);
//                System.out.printf("%s %s %s\n", i, sum, tempMin);
                if(numberElement>1){
                    rs=Math.max(rs, sum-tempMin);
                }
            }
        }
        return rs;
    }

    public static int maximumSum(int[] arr) {
        int n=arr.length;
        int[]left=new int[n];
        int[]right=new int[n];
        int sum=0;
        int rs=arr[0];
        int totalSum=0;

        for(int i=0;i<n;i++){
            //result có thể nhận kết quả <0
            if(sum<0){
                sum=arr[i];
            }else{
                sum+=arr[i];
            }
            left[i]=sum;
            totalSum+=arr[i];
        }
        sum=0;
        for(int i=n-1;i>=0;i--){
            //result có thể nhận kết quả <0
            if(sum<0){
                sum=arr[i];
            }else{
                sum+=arr[i];
            }
            right[i]=sum;
        }
//        println(left);
//        println(right);
        for(int i=0;i<n;i++){
            int leftValue=0;
            int rightValue=0;
            boolean leftIsExist=i>=1;
            boolean rightIsExist=i<n-1;

            if(i>=1){
                leftValue=left[i-1];
            }
            if(i<n-1){
                rightValue=right[i+1];
            }
            //Với những case left/ right âm --> Không cần thêm vào bỏ hết
            if(rightValue>=0&&leftValue<0&&rightIsExist){
                leftValue=0;
            }else if(rightValue<0&&leftValue>=0&&leftIsExist){
               rightValue=0;
            }
            if(!leftIsExist&&!rightIsExist){
                continue;
            }
            rs=Math.max(rs, leftValue+rightValue);
        }
        return Math.max(rs, totalSum);
    }

    public static void println(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.printf("%s,", arr[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
//        int[] arr=new int[]{1,-2,0,3};
//        int[] arr=new int[]{1,-2,-2,3};
//        int[] arr=new int[]{-1,-1,-1,-1};
//        int[] arr=new int[]{-50};
//        int[] arr=new int[]{2,9,-4,7,-6,5,8,-5,-6,9};
//        int[] arr=new int[]{11,-10,-11,8,7,-6,9,4,11,6,5,0};
//        int[] arr=new int[]{-2, 3,4,5,-1};
//        int[] arr=new int[]{-2, 3,4,5};
//        int[] arr=new int[]{3,4,5,-2};
        int[] arr=new int[]{100,30,1,987,400,200,9};
        System.out.println(maximumSum(arr));

        //
        //** Đề bài:
        //
        //
        //** Bài này tư duy như sau:
        //
        //
        //# Reference
        //2343. Query Kth Smallest Trimmed Number
        //1092. Shortest Common Supersequence
        //1852. Distinct Numbers in Each Subarray
    }
}
