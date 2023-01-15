package interviews.bytedance;

import java.util.List;

public class E17_tiktok_rating {

    public static long maximumSum(List<Integer> arr) {
        // Write your code here
        int sum=0;
        int n=arr.size();
        int rs=Integer.MIN_VALUE;

        if(n>=1){
            sum=arr.get(0);
            rs=sum;
        }

        for(int i=1;i<n;i++){
            if(sum+arr.get(i)<=arr.get(i)){
                sum=arr.get(i);
            }else if(sum+arr.get(i)>arr.get(i)){
                sum+=arr.get(i);
            }
            rs=Math.max(rs, sum);
        }
        return rs;
    }

    public static void main(String[] args) {

    }
}
