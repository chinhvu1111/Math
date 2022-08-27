package interviews;

public class E117_Sqrt_binary {

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

    public static int mySqrtOptimize(int x) {
        long r=x;

        while (r*r>x){
            System.out.printf("%s %s\n", r, x/r);
            r=(r+x/r)/2;
        }
        return (int)r;
    }

    public static void main(String[] args) {
//        System.out.println(mySqrt(6));
//        System.out.println(mySqrt(2147395599));
//        System.out.println(mySqrt(9));
//        System.out.println(mySqrt(10));
//        System.out.println(mySqrt(1));
//        System.out.println(mySqrt(0));
        System.out.println(mySqrt(2147483647));
        System.out.println(mySqrtOptimize(8));
        //Bài này tư duy như sau:
        //Cách 1, Làm theo kiểu binary search truyền thống
        //1.1, Sử dụng low, high để tìm thông tin trong khoảng (low, high)
        //1.2, Check dần dần so sánh (mid <x/mid)
        //1.3 Không dùng mid*mid --> Vì có thể > MAX_INTEGER.
        //1.4, Tìm đến khi low gần với sqrt(x) nhất
        //===> Vòng cuối sẽ check xem (low*low>x) --> thỏa mãn return low-1 <> low
        //
        //Cách 2: Làm binary search tối giản
        //2,
        //2.1, Ta sẽ tìm số gần với việc (x*x>r) nhất
        //- Bằng cách update x liên tục
        //x=(x+r/x)/2
        //x1*x2 --> x2 ==> cũng sẽ được update liên tục dựa vào giá trị của x1
        //==> Coi như đích đến là (r), ta sẽ hướng đến update cả 2.
        //VD:
        //8 1
        //4 2
        //3 2
        //
    }
}
