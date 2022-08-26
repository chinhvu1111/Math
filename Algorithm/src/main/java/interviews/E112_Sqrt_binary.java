package interviews;

public class E112_Sqrt_binary {

    public static int mySqrt(int x) {
        if(x==1){
            return 1;
        }
        int low=0;
        int high= x/2;

        while (low<=high){
            int mid=low + (high-low)/2;

            //1, Thay vì cố tính mid * mid ==> Rất lớn --> Ta sẽ thực hiện phép chia (/)
            if(mid>x/mid){
                high= mid-1;
            }else if(mid<x/mid){
                low= mid+1;
            }else{
                return mid;
            }
        }
//        System.out.printf("%s %s\n", low, high);
        if(low>x/low){
            return low-1;
        }
        return low;
    }

    public static void main(String[] args) {
//        System.out.println(mySqrt(6));
//        System.out.println(mySqrt(2147395599));
        System.out.println(mySqrt(2147483647));
//        System.out.println(mySqrt(9));
//        System.out.println(mySqrt(10));
//        System.out.println(mySqrt(1));
//        System.out.println(mySqrt(0));
    }
}
