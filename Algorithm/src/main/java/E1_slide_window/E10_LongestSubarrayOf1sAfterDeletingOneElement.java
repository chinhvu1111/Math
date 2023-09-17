package E1_slide_window;

public class E10_LongestSubarrayOf1sAfterDeletingOneElement {

    public static int longestSubarray(int[] nums) {
        int n=nums.length;
        //Space : O(n)
        int[] leftNumOne=new int[n];
        int count=0;

        //Time : O(n)
        for(int i=0;i<n;i++){
            if(nums[i]==1){
                count++;
            }else{
                leftNumOne[i]=count;
                count=0;
            }
        }
        int rs=0;
        count=0;

        //Time : O(n)
        for(int i=n-1;i>=0;i--){
            if(nums[i]==1){
                count++;
            }else{
                rs=Math.max(rs, count+leftNumOne[i]);
                count=0;
            }
        }
        rs=Math.max(count-1, rs);
        return rs;
    }

    public static int longestSubarraySlideWindow(int[] nums) {
        int n=nums.length;
        int start=0;
        int numZero=0;
        int rs=0;
        int i;

        //Time : O(n)
        for(i=0;i<n;i++){
            if(nums[i]==0){
                numZero++;
            }
//            System.out.printf("%s %s\n", start, i);
            rs=Math.max(rs, i-start-1);
            if(numZero>1){
//                int beforeTmp=start;
                while(start<n&&nums[start]==1){
                    start++;
                }
//                System.out.printf("Num zero >1 : %s %s %s\n", beforeTmp, start, i);
                if(start<n&&nums[start]==0){
                    start++;
                }
                numZero--;
            }
        }
        if(numZero<=1){
            rs=Math.max(rs, i-1-start);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Chỉ được phép xoá 1 element từ array
        //* return lại subarry longest having only one 1 value
        //Ex:
        //0,1,1,1,0,1,1,0,1
        //+ Delete index=4
        //==> return 5 (1,1,1,1,1)
        //
        //** Idea
        //1.
        //1.0
        //- Constraints
        //1 <= nums.length <= 105
        //nums[i] is either 0 or 1.
        //
        //- Brainstorm
        //- Nếu chỉ xoá 1 element thì sẽ chọn xoá 0 nào mà có số số 1 sum(left và right) lớn nhất
        //- Bài này sẽ là dạng lưu số lượng số số 1 bên (left/ right) của 0
        //Ex:
        //[0,1,1,1,0,1,1,0,1]
        //+ Left to right:
        //[0,1,2,3,0,1,2,0,3]
        //+ Right to left:
        //[0,3,2,1,0,2,1,0,1]
        //
        //1.1, Optimization
        //1.2, Complexity:
        //- Space: O(n)
        //- Time: O(n)
        //
        //* Method-2:
        //2.
        //2.0, Slide window
        //
        //Ex:
        //[0,1,1,1,0,1,1,0,1]
        //+ Ta thấy ở đây nếu số lượng chữ số 0 <=1 : Thì 1 subarray vẫn được tính là valid
        //+ Nếu số lượng chữ số 0 > 1 ==> Thì ta sẽ cut đi những số 1 cho đến khi gặp 0
        //
        //Ex:
        //[1,1,(0),1,1,1,0,1,1,(0),1]
        //+ count=0
        //+ countZero=2
        //+ rs=Math.max(rs, end-
        //+ while(==1) start++
        //Ex:
        //(0),1,1,1,(0),1,1,0,1
        //+ index=4
        //+ start=0 ==> 4-0
        //
        //2.1, Optimization
        //- Chú ý phần cut đầu
        //Eg:
        //[(1),1,1,[0],1,1,(0)]
        //+ Cut 1 hết đi khi gặp số 0 thứ 2
        //+ Đến số 0 thứ 1 --> start++
        //=====
        //if(start<n&&nums[start]==0){
        //                    start++;
        //                }
        ///====
        //---> Có thể chuyển thành start++ luôn vì có case:
        //[(0),1,1,1,(0),1,1,0]
        //+ Không có số 1 ở đầu
        //--> Luôn start++
        //
        //2.2,
        //- Space : O(1)
        //- Time : O(n1) (n1 >n) (~n)
        int[] arr=new int[]{0,1,1,1,0,1,1,0,1};
        System.out.println(longestSubarray(arr));
        System.out.println(longestSubarraySlideWindow(arr));
        //#Reference:
        //661. Image Smoother
        //1801. Number of Orders in the Backlog
        //1110. Delete Nodes And Return Forest
        //2603. Collect Coins in a Tree
        //2009. Minimum Number of Operations to Make Array Continuous
        //493. Reverse Pairs
    }
}
