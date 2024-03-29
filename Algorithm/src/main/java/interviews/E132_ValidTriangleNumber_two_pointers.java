package interviews;

import java.util.Arrays;

public class E132_ValidTriangleNumber_two_pointers {

    public static int triangleNumberMethod1(int[] nums) {
        Arrays.sort(nums);
        int n=nums.length;
        int rs=0;

        for(int i=1;i<n-1;i++){
             int currentValue=nums[i];
             int j;
             int index;

             for(j=0;j<i;j++){
                 int rightValueEval=nums[j]+currentValue;
                 index=findIndexElement(i+1, n-1, nums, rightValueEval);

//                 System.out.printf("%s %s %s | ", nums[j], currentValue, index);
                 if(index!=-1){
                     rs+=index-i;
                 }
//                 System.out.printf("%s %s %s, ",j, i, index);
             }
//             System.out.printf("%s %s, ", j+1, currentValue);
        }

        return rs;
    }

    public static int findIndexElement(int low, int high, int arr[], int value){
        int mid=low+(high-low)/2;

        if(low>=high-1){
            if(arr[high]<value){
                return high;
            }
            if(arr[low]<value){
                return low;
            }
            return -1;
        }

        if(arr[mid]>=value){
            high=mid-1;
        }else{
            low=mid;
        }
        return findIndexElement(low, high, arr, value);
    }

    public static int triangleNumber(int[] nums) {
        int rs=0;
        int n=nums.length;
        Arrays.sort(nums);

        for(int i=1;i<n;i++){
            int currentValue=nums[i];
            int low=0;
            int high=i-1;

            while (low<high){
                if(nums[low]+nums[high]>currentValue){
                    rs+=high-low;
                    high--;
                }else {
                    low++;
                }
            }
        }

        return rs;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{2,2,3,4};
//        int[] arr =new int[]{4,2,3,4};
        int[] arr =new int[]{1,2,3,4,5,6};
        //Case 1: Sai khi {2,(3),4,4}
        //==> 2,3,(4,4) : Ta có 2 cases để chọn cái này.
        //int[] arr =new int[]{2,2,3,4};
        //2,(3),4,4
        //3: Có 2 giá trị thỏa mãn
        System.out.println(triangleNumberMethod1(arr));
        //Bài này tư duy như sau:
        //Cách 1:
        //1, Độ phức tạp O( n^2 * log(n) )
        //1.1, Tư duy này là tư duy phổ biến --> Phân tích thành nhiều cases (mid value)
        //VD: a, b, (c), d, e
        //- 1 vòng for cho mid (1 --> n-2)
        //- 1 vòng for cho (left) ==> Lúc đó ta chỉ cần tìm (right) phù hợp.
        //- Với việc phân tích thành từng (left, mid)
        //---> rs= all sum[ right/(left, mid) ] : tìm tổng all right thỏa mãn với từng (left, mid)
        //- Áp dụng binary search : Tìm right xa nhất mà thỏa mãn (left + value > right)
        //--> Số lượng lúc đó sẽ bằng rs+= (index_right) - (index_middle)
        //2, Cách log ra để xem kết quả:
        //2.1, Log trong (for), (log ra ngoài for), (log trong if) .
        //
        //0, Bài này thêm kinh nghiệm + thêm 1 luồng tư duy liên quan đến Thinking idea
        //==> Thảo luận về tips của bài toán --> Xem nên:
        //- Biến đổi bài toán như thế nào --> Áp dụng 1 vài quy luật về toán
        //- Phân tích rõ tư tưởng/ problems thay vì chỉ tập trung vào code luôn. ===> (Có thể dẫn đến đi sai hướng/ Solution không tối ưu)
        //
        //
        //Cách 2:
        //1,
        //1.0, ==> Bài này dạng tip tricks ==> Cần (TƯ DUY RỘNG RA).
        //1.1,Tư duy theo dạng chia case --> Không được vì nó lại quay về bài toán O(n^3)
        //1,2,3,(4),4,5,5,6,6,6
        //1,4,6
        //4 + 1 <6
        //==>
        //
        //1.2, Tư duy khác 1 chút
        //1,2,3,(4),4,5,5,6,6,6
        //
        //a,b,c,d,e
        //
        //a+b<c
        //--> a+b<e
        //
        //a + b < c
        //
        //1.3, nếu nums[i] đóng vai trò khác VD (c) thì sao:
        //a + b < c
        //- nums[i] mà đóng vai trò (a/b) ===>  Cuối cùng thì bài toán nó vẫn quay về O(n^3)
        //với b < (c-a) (c giảm / a tăng)
        //===> numss[i] đóng vai trò là c ==> kết quả vẫn thế vì chỉ là phép chuyển vế mà thôi.
        //1.4,
        //VD:
        //(1,2,3,4),7
        //
        //(3,4,5,6),7
        //case này ta có nhưng dãy thỏa mãn như:
        //(3,6,7), (3,5,7), (4,6,7), (4,5,7)
        //==> Chia ntn?
        //- Phân tích
        //Khi chọn case (3,6,7) ==> sẽ chứng minh được (all cases) (4 (>3),6, [7] ) --> Cũng thỏa mãn khi ghép với 7
        //--> Ta sẽ cộng vào kết quả --> right--;
        System.out.println(triangleNumber(arr));
    }
}
