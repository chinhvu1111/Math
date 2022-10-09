package interviews;

import java.util.Arrays;

public class E70_FirstBadVersion {

    public static boolean arr[];

    public static boolean isBadVersion(int version){ return false; }

    public static int firstBadVersion(int n) {
        return search1(1, n);
    }

    public static long search(long low, long high){
        long mid=low + (high - low) / 2;
        long index=-1;

        if(low>=high-1){
            if(isBadVersion((int) low)){
                return low;
            }
            if(isBadVersion((int) high)){
                return high;
            }
            return -1;
        }

        if(isBadVersion((int) mid)){
            index=search(low, (int) mid);
        }else{
//            System.out.println((mid+1)+" "+high);
            index=search((int) (mid+1), high);
        }
        return index;
    }

    public static int search1(int low, int high){
        int mid=low + (high - low) / 2;
        int index=-1;

        if(low>=high-1){
            if(isBadVersion(low)){
                return low;
            }
            if(isBadVersion(high)){
                return high;
            }
            return -1;
        }

        if(isBadVersion(mid)){
            index=search1(low, mid);
        }else{
            System.out.println((mid+1)+" "+high);
            index=search1(mid+1, high);
        }
        return index;
    }

    public static void main(String[] args) {
        arr=new boolean[2126753391];
        Arrays.fill(arr, true);
        arr[1702766719]=true;
        for(int i=1702766719;i<arr.length;i++){
            arr[i]=false;
        }
        System.out.println(firstBadVersion(2126753390));

        //** Đề bài
        //- Tìm bad version đầu tiên
        //VD: GGGGG(B)BBBBB : Cần tìm B đầu tiên
        //---> Vì đã bị B --> Đằng sau bị bad hết
        //
        //**Bài này tư duy như sau:
        //Cách 1: Dùng trực tiếp binary search
        //1, Bài này là bài dạng dùng lại method của đề
        //--> Thực ra dạng này có thể fake được method
        //--> Bằng biến đổi isBadVersion(n) --> Array[n]
        //2, Dạng này nếu số n quá lớn --> Tràn kiểu int do:
        //int mid= (low+high)/2 --> do cùng kiểu int ==> Sẽ bị (<0)
        //==> Cast ra Long
        //2.1, Ngoài ra ta còn 1 cách nữa để không cần cast(long) as Int
        //mid= low + (high-low)/2 ==> Sẽ không bị quá.
        //--> Chuyển ( + --> - ) : Có thể sẽ không bị quá (int)
    }
}
