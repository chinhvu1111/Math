package E1_binary_search_topic;

public class E12_FindPeakElement {

    public static int findPeakElementLinearTime(int[] nums) {
        int n=nums.length;

        for(int i=0;i<n;i++){
            boolean greaterLeft=false;
            boolean greaterRight=false;

            if((i>0&&nums[i]>nums[i-1]||i==0)){
                greaterLeft=true;
            }
            if((i+1<n&&nums[i]>nums[i+1]||i==n-1)){
                greaterRight=true;
            }
            if(greaterLeft&&greaterRight){
                return i;
            }
        }
        return -1;
    }

    public static int findPeakElement(int[] nums) {
        int n=nums.length;

        if(n==1){
            return 0;
        }
        if(n>=2){
            if(nums[1]<nums[0]){
                return 0;
            }else if(nums[n-2]<nums[n-1]){
                return n-1;
            }
        }
        int low=0, high=n-1;

        while(low<high){
            int mid=low+(high-low)/2;
            if(mid>0&&nums[mid]>nums[mid-1]){
                if(mid<n-1&&nums[mid]>nums[mid+1]){
                    return mid;
                }
                low=mid;
            }else if(mid>0&&nums[mid]<nums[mid-1]){
                if(mid>=2&&nums[mid-1]>nums[mid-2]){
                    return mid-1;
                }
                high=mid-1;
            }else{
                high=mid-1;
            }
            //mid==0/ nums[mid]==nums[mid-1]
            //mid=0 ==> 0,0 ==> Không thể
        }

        return -1;
    }

    public static int findPeakElementOptimization(int[] nums) {
        int n=nums.length;
        int low=0, high=n-1;

        while(low<high){
            int mid=low + (high-low)/2;
            if(nums[mid]>nums[mid+1]){
                high=mid;
            }else{
                low=mid+1;
            }
        }

        return low;
    }

    public static void main(String[] args) {
        // Đề bài:
        //- Cho 1 array thoả mãn:
        //+ nums[i] != nums[i + 1] for all valid i.
        //* return index thoả mãn ( nums[index-1] < nums[index] > nums[index+1] )
        //
        // Tư duy
        //1.
        //1.1, Idea
        //1 <= nums.length <= 1000
        //-2^31 <= nums[i] <= 2^31 - 1
        //** nums[i] != nums[i + 1] for all valid i.
        //
        //- Brainstorm
        //- Bài này có thể làm với độ phức tạp O(n) 1 cách dễ dàng hơn
        //
        //- Với độ phức tạp O(log(n))
        //Ex:
        //nums = [1,2,1,3,5,6,4]
        //nums = [1,2,1,(3),5,6,4]
        //+ mid = low + (high-low)/2, có cases:
        //+ nums[mid-1] <= nums[mid] <= nums[mid+1]
        //-> Loại index= mid-1 và mid = [ (low, mid-2), (mid+1, high) ]
        //+ nums[mid-1] <= nums[mid] >= nums[mid+1]
        //-> Không loại index nào trừ (mid đã xét) = [ (low, mid-2), (mid+2, high) ]
        //+ nums[mid-1] >= nums[mid] >= nums[mid+1]
        //-> Loại index= mid-1 và mid  = [ (low, mid-1), (mid+2, high) ]
        //+ nums[mid-1] >= nums[mid] <= nums[mid+1]
        //-> Loại index= mid = [ (low, mid-1), (mid+1, high) ]
        //
        //--> Như thế này thì số cases sẽ không giảm được ==> Do mỗi case vẫn có 2 sub-cases.
        //
        //- Ta chỉ cần return lại 1 index mà thoã mãn
        //+ nums[index-1] <= nums[index] >= nums[index+1]
        //- 1 dãy số có thể có 2 cases:
        //+ Tăng
        //+ Giảm
        //+ Ta có thể chọn index=0 khi <=> -1< nums[0] > nums[1]
        //--> Tăng từ index=0 --> index=1
        //+ Ta có thể chọn index=n-1 khi <=> nums[n-2] < nums[n-1] > -1
        //--> Tăng từ index=n-2 --> index=n-1
        //Ex:
        //1,2,3,1
        //Order:
        //+ Tăng, tăng, giảm
        //==> Index thoả mãn khi (tăng, giảm/ giảm, tăng)
        //
        //- 1 array có lẻ size:
        //Ex:
        //1,2,3,6,5
        //+ Để không chọn index=0/ n-1 ==> (first increase) và (last decrease)
        //Order: Tăng,...,Giảm
        //* ==> Ở giữa sẽ có 1 thằng (Tăng --> Giảm) cùng lắm là đến (n-1) giảm
        //---> Nó chính là kết quả.
        //- Bài toán trở thành
        //+ Xét 2 đầu của array
        //+ Tìm phần tử thoả mãn (Tăng --> Giảm)
        //Ex:
        //1,2,1,3,5,4,2
        //mid = 3
        //+ low=0, high= 6 -> mid=3, nums[mid-1]<nums[mid] ==> Có thể có index nằm right
        //+ low=3, high= 6 -> mid=4, nums[mid-1]<nums[mid] ==> check (nums[mid]>nums[mid+1]) | mid==n-1 (Không cần check vì mình đã xét biên rồi)
        //
        //+ nums[mid-1]>nums[mid] ==> (low, mid-1)
        //
        //** EXP:
        //- Đề bài bên trên là không đủ --> Cần đọc constraint bên dưới để clear rõ thông tin
        //==> Tránh như bài này sẽ xảy ra cases số liên tiếp giống nhau ==> Không xử lý được, Nên đề bài nó cho:
        //+ nums[i] != nums[i + 1] for all valid i.
        //
        //1.1, Optimization
        //- Tối ưu hơn thì 2 đầu nó đã là (tăng và giảm rồi)
        //--> Ta chỉ cần tìm 1 cặp (Giảm là được)
        //+ Tìm (nums[index]> nums[index+1]) ==> Bên left của nó phải tăng
        //Có cases như sau:
        //+ nums[index] > nums[index+1] ==> high=mid (Dịch sang trái) do đã tăng rồi ==> Tìm giảm nó bên left
        //+ nums[index] < nums[index+1] ==> high=mid (Dịch sang phải) do đã giảm rồi ==> Tìm giảm nó bên right
        //** Nó sẽ thành 1 cặp (Tăng --> Giảm)
        //- Kinh nghiệm binary search (triple tăng giảm)
        //+ ( nums[index-1] < nums[index] > nums[index+1] ) ==> Mình chỉ cần chạy loop (low, high) bình thường là được
        //
        //1.2, Complexity:
        //- Space : O(1)
        //- Time : O(log(n))
        //
        //#Reference:
        //852. Peak Index in a Mountain Array
        //1901. Find a Peak Element II
        //2210. Count Hills and Valleys in an Array
//        int[] nums = {1,2,1,3,5,6,4};
//        int[] nums = {1,1,1,3,3,5,6,4};
        int[] nums = {1};
        System.out.println(findPeakElement(nums));
        System.out.println(findPeakElementOptimization(nums));
    }
}
