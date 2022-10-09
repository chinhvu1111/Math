package interviews;

import java.util.Arrays;

public class E136_Heaters {

    public static int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int n=houses.length;
        int radius=0;

        for(int i=0;i<n;i++){
            //Right of house = MIN (bisec left)
            int rightHeater=bisectLeft(heaters, houses[i]);
            int currentMin=Integer.MAX_VALUE;

            if(rightHeater<heaters.length){
                currentMin=Math.min(currentMin, heaters[rightHeater]-houses[i]);
//                System.out.printf("%s, ", heaters[rightHeater]-houses[i]+1);
            }
            //Left of house = MAX (bisec right)
            int leftHeater=bisectRight(heaters, houses[i]);

            if(leftHeater-1>=0&&leftHeater-1<heaters.length){
                currentMin=Math.min(currentMin, houses[i]-heaters[leftHeater-1]);
//                System.out.printf("%s, ", houses[i]-heaters[leftHeater]+1);
            }
            radius=Math.max(radius, currentMin);
//            System.out.printf("%s %s %s, ",houses[i], leftHeater-1, rightHeater);
        }
        return radius;
    }

    /*
    Tìm phần tử Lò bên phải của target sao cho :
    - Value của Lò > target
    - Phần tử đó Min nhất
    ** Trả về chỉ số vị trí của (phần tử đầu tiên) > target trong đoạn [lo, hi).
    Đó cũng chính là vị trí chèn phải.
    ---> Khi tìm được index rôi ---> (index-1) ==>ra index của trường hợp (<=)
    VD:
    1,2,4,4,7
    bisect_right(arr, 4) = 4
    bisect_right(arr, 3) = 2
    bisect_right(arr, 0) = 0
    ** Chú ý case này
    //Quá length
    bisect_left(arr, 8) = 5
     */
    public static int bisectRight(int[] heaters, int target){
        int low=0;
        int high=heaters.length-1;
//        int rs=-1;

        while (low<=high){
            int mid=low+(high-low)/2;

            //Vì ta xêt cả trường hợp == target gần (i) nhất
            if(heaters[mid]<=target){
                low=mid+1;
            }else{
//                rs=mid;
                //Đoạn này xét Lò đoạn gần (i) nhất mà heaters[j] >=target
                high=mid-1;
            }
        }
        return low;
    }

    /*
    Tìm phần tử Lò bên trái của target sao cho :
    - Value của Lò >= target
    - Phần tử đó MIN nhất
    ** Trả về chỉ số (vị trí của phần tử đầu tiên) >= target trong đoạn [lo, hi).
    VD:
    1,2,4,4,7
    bisect_left(arr, 4) = 2
    bisect_left(arr, 3) = 2
    bisect_left(arr, 0) = 0
    //Quá length
    bisect_left(arr, 8) = 5
     */
    public static int bisectLeft(int[] heaters, int target){
        int low=0;
        int high=heaters.length-1;
        int rs=-1;

        while (low<=high){
            int mid=low+(high-low)/2;

            if(heaters[mid]<target){
                low=mid+1;
            }else{
                rs=mid;
                high=mid-1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
//        int houses[]=new int[]{1,2,3,4};
//        int heaters[]=new int[]{1,4};

        int houses[]=new int[]{1,5};
        int heaters[]=new int[]{2};
        //
        //** Đề bài:
        //- Tìm bán kính nhỏ nhất của 1 máy sưởi sao cho cover được toàn bộ ngôi nhà --> Chọn duy nhất 1 máy sửi có radius (MIN)
        //
        //** Ta tư duy như sau:
        //0, - Mỗi ngôi nhà tím máy sưởi gần nhất bên trái/ bên phải
        //- bisect Left : value >= target và value MIN
        //- bisect Right : value > target và value MIN
        //==> Mục đích lấy index - 1 ===> Sẽ ra (value <=target)
        //<=> tìm <=target.
        //0.1, Cần rất chứ ý:
        //* bisectLeft
        //VD:
        //1,2,4,4,7
        //bisect_left(arr, 4) = 2
        //bisect_left(arr, 3) = 2
        //bisect_left(arr, 0) = 0
        //Quá length
        //bisect_left(arr, 8) = 5
        //** Thường sẽ implement --> Lấy low
        //===> Sau đó là (index)
        //
        //* bisectRight
        //VD:
        //1,2,4,4,7
        //bisect_right(arr, 4) = 4
        //bisect_right(arr, 3) = 2
        //bisect_right(arr, 0) = 0
        //** Chú ý case này
        //Quá length
        //bisect_left(arr, 8) = 5
        //** Thường sẽ implement --> Lấy low
        //===> Sau đó là (index-1)
        //1,
        //- Tìm bán kính nhỏ nhất của 1 máy sưởi sao cho cover được toàn bộ ngôi nhà --> Chọn duy nhất 1 máy sửi có radius (MIN)
        //==>
        //- Tìm min của left/right của mỗi house + MAX của all house ---> Sẽ ra kết quả.
        //
        //
        System.out.println(findRadius(houses, heaters));
        //Phần test bisect (left/ right)
        int arr[]=new int[]{1,2,4,4,7};
        System.out.println(bisectLeft(arr, 4));
        System.out.println(bisectLeft(arr, 8));
        System.out.println(bisectRight(arr, 4));
        System.out.println(bisectRight(arr, 8));
        //
        //*** Reference:
        //- Remove Nth Node From End of List
        //- Maximize the Beauty of the Garden
        //- Finding 3-Digit Even Numbers
    }
}
