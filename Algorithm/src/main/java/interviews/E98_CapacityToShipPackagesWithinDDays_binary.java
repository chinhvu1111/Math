package interviews;

public class E98_CapacityToShipPackagesWithinDDays_binary {

    public static int shipWithinDaysDynamic(int[] weights, int days) {
        int n=weights.length;
        int dp[][]=new int[n][days+1];
        int sum=0;

        for(int i=0;i<n;i++){
            sum+=weights[i];
            dp[i][1]=sum;
        }

        for(int i=2;i<=days;i++){
            for(int j=0;j<n;j++){
                int currentValue=Integer.MAX_VALUE;
                int s=weights[j];

                for(int h=j-1;h>=0;h--){
                    currentValue=Math.min(currentValue, Math.max(dp[h][i-1], s));
                    s+=weights[h];
                }
                dp[j][i]=currentValue;
            }
        }
        return dp[n-1][days];
    }

    public static int shipWithinDaysBinarySearch(int[] weights, int days) {
        int low=0;
        int high=0;
        int n=weights.length;

        for(int i=0;i<n;i++){
            low=Math.min(low, weights[i]);
            high+=weights[i];
        }
        while (low<high){
            int mid=low +(high-low)/2;

            if(isValid(mid, weights, days)){
                high=mid;
            }else{
                low=mid+1;
            }
        }

        return low;
    }

    public static boolean isValid(int value, int[] weights, int days){
        int n=weights.length;
        int count=0;
        int sum=0;

        for(int i=0;i<n;i++){
            if(sum+weights[i]>value){
                count++;
                sum=weights[i];
                if(sum>value){
                    return false;
                }
            }else{
                sum+=weights[i];
            }
        }
        if(sum!=0){
            count++;
        }
        //Nếu chia theo kiểu này sum quá nhở --> low=mid +1
        return count<=days;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{3,2,2,4,1,4};
//        int days=3;
        int arr[]=new int[]{1,2,3,4,5,6,7,8,9,10};
        int days=5;
//        int arr[]=new int[]{1,2,3,1,1};
//        int days=4;

        System.out.println(shipWithinDaysDynamic(arr, days));
        System.out.println(shipWithinDaysBinarySearch(arr, days));
    }
}
