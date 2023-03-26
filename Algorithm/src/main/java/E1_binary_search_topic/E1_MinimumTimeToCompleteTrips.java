package E1_binary_search_topic;

public class E1_MinimumTimeToCompleteTrips {

    public static long getNumTrips(int[] times, long specificTime){
        long rs=0;
        for(int e:times){
            rs+=specificTime/e;
        }
        return rs;
    }
    public static long minimumTime(int[] time, int totalTrips) {
        long low=1;
        long high=100_000_000_000_000L;// Khá lớn --> Giới hạn lại
        long rs=-1;

        while (low<=high){
            //1(low),2(high)
            //mid=1
            long mid=low+(high-low)/2;
            long currentTotalTrips=getNumTrips(time, mid);

            if(currentTotalTrips>=totalTrips){
                rs=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        if(rs==-1){
            return low;
        }
        return rs;
    }

    public static long minimumTimeRefactor(int[] time, int totalTrips) {
        long low=Long.MAX_VALUE;
        long high=Long.MIN_VALUE;// Khá lớn --> Giới hạn lại
        for(Integer i:time){
            low=Math.min(low, i);
            high=Math.max(high, i);
        }
        low=low*time.length*totalTrips;
        high=high*time.length*totalTrips;
        long rs=-1;

        while (low<=high){
            //1(low),2(high)
            //mid=1
            long mid=low+(high-low)/2;
            long currentTotalTrips=getNumTrips(time, mid);

            if(currentTotalTrips>=totalTrips){
                rs=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        if(rs==-1){
            return low;
        }
        return rs;
    }
    public static void main(String[] args) {
//        int[] times=new int[]{1,2,3};
//        int totalTrips = 5;
//        int[] times=new int[]{2};
//        int totalTrips = 1;
        int[] times=new int[]{66};
        int totalTrips = 8295;
        System.out.println(minimumTime(times, totalTrips));
        System.out.println(minimumTimeRefactor(times, totalTrips));
        //
        //** Đề bài:
        //- time[i] là thời gian để bus thứ (i) complete (one) trip
        //- Các xe bus thực hiện trip 1 cách độc lập
        //- Cho (totalTrips) chỉ ra số lượng trips mà tất cả các buses phải hoàn thành (tổng)
        //==> Tính thời gian ít nhất min(time) để hoàn thành (totalTrips)
        //VD:
        //time = [1,2,3], totalTrips = 5
        //
        //t=1 --> Có bus Indexs=[0] chạy hoàn thành (thêm) ==> index=0 chạy dc 1 [tổng =1]
        //t=2 --> Có bus Indexs=[0,1] chạy hoàn thành (thêm) ==> index=0 chạy thêm dc (1), 1 chạy dc (1) [tổng =2]
        //t=3 --> Có bus Indexs=[0,1,2] chạy hoàn thành (thêm) ==> index=0 chạy thêm dc 1, index=2 chạy được 1 (index 2 đang chạy dở) [ Tổng =2]
        //total=1+2+2=5
        //
        //** Bài này tư duy như sau:
        //1.
        //1.0, Xây dựng idea
        //1 <= time.length <= 10^5
        //1 <= time[i], totalTrips <= 10^7
        //
        //- Loại recursive (brute force)
        //VD:
        //time = [1,2,3], totalTrips = 5
        //+ Giả sử thời gian để chạy đủ 5 là (t)
        //==> Với t đó thì trips có thể hoàn thành từ time=[1,2,3] là
        //+ x>5 --> Giảm time xuống
        //+ x<=5 --> tạm lấy kết quả x + tìm tiếp ==> Rồi return.
        //
        //==> Đây là ý tưởng của binary search.
        //
        //1.1,
        //- Tìm trips có thể done trong thời gian xác định x
        //VD:
        //với time=[1,2,3]
        //total=6
        //1 +1
        //2
        //3
        //4
        //5
        //6 ==> Từ 6 ta có thể tính được luôn all ==> for all elements
        //
        //
        //1.2, Lỗi liên quan đến limit high
        //- long high=100_000_000_000_000L;
        //==> Khá lớn
        //** Nên tính range (high) trước dựa trên các limitation đã có:
        //1 <= time.length <= 10^5
        //1 <= time[i], totalTrips <= 10^7
        //==> Tổng time =10^5 * 10^7= 10^12.
        //
        //1.3, Complexity:
        //- Time complexity : O(log(n))
        //- Space complexity : O(1)
        //
        //# Reference:
        //# Same as format:
        //Minimum Time to Complete Trips
        //Kth Missing Positive Number
        //Maximum Tastiness of Candy Basket
        //Number of Flowers in Full Bloom
        //
        //#Similar:
        //2188. Minimum Time to Finish the Race
        //1870. Minimum Speed to Arrive on Time
        //2141. Maximum Running Time of N Computers
        //2398. Maximum Number of Robots Within Budget
    }
}
