package interviews;

public class E106_MinimizedMaximumOfProductsDistributedToAnyStore {

    public static int minimizedMaximum(int n, int[] quantities) {
        int low=0;
        int high=0;
        int length=quantities.length;
        long sum=0;

        for(int i=0;i<length;i++){
            high=Math.max(high, quantities[i]);
            sum+=quantities[i];
        }
        low=(int)(sum/n);

        while (low<=high){
            int mid=low + (high-low)/2;


        }

        return 1;
    }

    public static void main(String[] args) {
        int[] quantities=new int[]{15,10,10};
    }
}
