package interviews;

public class E106_MinimizedMaximumOfProductsDistributedToAnyStore {

    public static int minimizedMaximum(int n, int[] quantities) {
        int low=Integer.MAX_VALUE;
        int high=0;
        int length=quantities.length;

        if(n==1&&length==1){
            return quantities[0];
        }

        for(int i=0;i<length;i++){
            high=Math.max(high, quantities[i]);
            low=Math.min(low,quantities[i]);
        }
        int rs=0;

        while (low<=high){
            int mid=low + (high-low)/2;
            int count=0;

            for(int i=0;i<length;i++){
                count+=(quantities[i]+mid-1)/mid;
            }
            if(count>n){
                //1, Bài này nếu để mid trên đây sẽ bị sai
                //2, chỗ này nếu gán low=mid ==> Sẽ bị loop vô tận
                //==> Cần nhớ rõ tại sao
                low=mid+1;
            }else {
                rs=mid;
                high=mid-1;
            }
        }

        return rs;
    }

    public static void main(String[] args) {
//        int[] quantities=new int[]{15,10,10};
//        int n=7;
        int[] quantities=new int[]{11,6};
        int n=6;
//        int[] quantities=new int[]{100000};
//        int n=1;
//        int[] quantities=new int[]{5,7};
//        int n=2;
        System.out.println(minimizedMaximum(n, quantities));
    }
}
