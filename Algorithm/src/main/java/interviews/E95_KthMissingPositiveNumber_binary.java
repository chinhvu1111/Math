package interviews;

public class E95_KthMissingPositiveNumber_binary {

    public static int findKthPositive(int[] arr, int k) {
        int leftValue=0;
        int n=arr.length;
        int count=0;

        for(int i=0;i<n;i++){
            if(arr[i]>leftValue){
                int min=Math.min(arr[i]-leftValue-1, k-count);
                count+=min;
                int rightValue=leftValue+min;

                if(count==k){
                    return rightValue;
                }
                leftValue=arr[i];
            }
        }
        if(count<k){
            return arr[n-1]+k-count;
        }
        return 1;
    }

    public static int findKthPositiveOptimize(int[] arr, int k) {
        int low=0, high=arr.length-1;

        while (low <= high){
            int mid=low + (high-low)/2;

            if(low>=high-1){
                if(arr[low]-k>low){
                    return low+k;
                }
                if(arr[high]-k>high){
                    return high+k;
                }
                break;
            }
//            System.out.printf("%s %s\n", low, high);
            if(arr[mid]-k>mid){
                high=mid;
            }else if(arr[mid]-k<=mid){
                low=mid+1;
            }
        }

        return arr.length+k;
    }

    public static int findKthPositiveRefactor(int[] A, int k) {
        int l = 0, r = A.length, m;

        while (l < r) {
            m = (l + r) / 2;
            if (A[m] - 1 - m < k)
                l = m + 1;
            else
                r = m;
        }
        return l + k;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{2,3,4,7,11};
//        int k=5;
//        int arr[]=new int[]{1,2,3,4};
//        int k=2;
//        int arr[]=new int[]{1,5,8,10};
//        int k=12;
        //Case 1:
        //Cách binary
        //+ Case này xảy ra khi:
        //Ta để điều kiện :
        //if(low >= high-1 )
        // if(arr[low]-k >= low) return low+k;
        //+, arr[0]=1:
        // 1 - 1 = 0 >= (i=0) ==> Sai.
        // >= Đổi thành > .
//        int arr[]=new int[]{1,2};
//        int k=1;
        //Case 2:
        //Cách binary
        //+ Case này xảy ra khi ta để điều kiện:
        //Code:
        //if ( low!=0&&arr[low]-k>low )
        // return low+k
        //--> Sau khi bỏ (low==0) : Kết quả đã trả lại đúng.
//        int arr[]=new int[]{2};
//        int k=1;
        //
        //Case 3:
        //Liên quan đến việc
        //Điều kiện : arr[mid]-k>mid
        //--> high=mid+1 thì sẽ bị thiếu trường hợp do
        //Điều kiện đúng ở đây là : if(arr[mid]-k>mid) return result
        //==> Cần giữ lại index=mid
        //==>(Đổi thành) high=mid.
//        int arr[]=new int[]{1,10,21,22,25};
//        int k=12;
        //
        //Case 4:
        //Case này liên quan đến việc ta phân chia các cases chia mid thiếu trường hợp:
        //+ if(arr[mid]-k<mid)
        // low=mid+1;
        //Nếu viết ntn --> Sẽ bị thiếu case arr[mid]-k==mid --> low/high không tăng có thể dẫn đến loop vô tận.
        //==> Đổi thành
        //if(arr[mid]-k<=mid) low=mid+1;
        int[] arr=new int[]{8,17,23,34,37,42};
        int k=16;
        System.out.println(findKthPositive(arr, k));
        //Bài này ta tư duy như sau:
        //Cách 1:
        //1, Bài này áp dụng tư duy tương tự như các bài xử lý missing trước:
        //1.1, Bổ sung thêm đoạn:
        //+ Math.min() : Để làm code trở nên shorter.
        //
        //Cách 2:
        //Khi làm theo phương pháp Binary search sẽ gặp 1 vài vấn đề
        //- Liên quan đến giới hạn mid --> Thường sẽ có 2 options:
        //+ Range 3 conditions
        //+ Range 2 conditions + dk check đều method
        //--> Nếu chia không tốt hoàn toàn có thể dẫn đến slow rất nhiều / loop vô tận
        //Loop vô tận khi luôn chia được mid.
        //- Liên quan đến điều kiện check để chia trường hợp :
        //---> Có thể điều kiện đó có thể hơi đặc biệt ==> Cái chính là phải kiểm soát được điều kiện đó --> Recursive có thể dẫn đến kết quả được.
        //+ Nếu ta chia không đủ case --> <, > (Thiếu =) chẳng hạn
        //==> Có thể dẫn đến loop vô tận.
        //VD: Case 4 bên trên.
        //Cách này ta tư duy như sau:
        //1, Để tìm được số value-k tức là value thiếu thứ k phải thỏa mãn các điều kiện như sau:
        //1.1, Số đó chưa xuất hiện trong arr[]
        //+ Số đó phải thuộc giữa (arr[i] ... arr[i+1]) nào đó
        //1.12, Số đó đứng thứ k trong các số chưa xuất hiện
        //+ Giả sử số đó nằm giữa (arr[i] .(v-k).. arr[i+1])
        //--> Trước v-k có thể điền (k-1) số không phải các số từ (0-->i)
        //+ (0--> i) là (i+1) số
        //+ (k) số có thể điền
        //---> k+i+1 số cho đến (v-k)
        //---> Ta xuất phát từ 1 :
        // CT: v-k=K+i+1.
        //** KINH NGHIỆM : NÊN XÂC ĐỊNH RÕ (i) Ở ĐÂY LÀ : (LƠW/HIGH) (INDEX LỚN HAY NHỎ hơn) (Đến lúc return mới biết)
        //- Với v-k=K+i+1 : (i lúc này sẽ là LOW)
        //VD: {2,3,4,7(low),11(high)}, k=5
        //+ low=3 --> v-k=3+5+1=9
        //--> Lúc này ta sẽ ưu tiên tìm HIGH hơn (Thực ra không phải là HIGH/LOW mà là tìm về phía INDEX lớn/nhỏ hơn)
        //---> Tức là if(value-low+1>=k) return low+k+1;
        //- Với v-k=K+i : (i lúc này sẽ là HIGH)
        //VD: {2,3,4,7(low),11(high)}, k=5
        //+ high=4 --> v-k=4+5=9
        //--> Lúc này ta sẽ ưu tiên tìm HIGH hơn (Thực ra không phải là HIGH/LOW mà là tìm về phía INDEX lớn/nhỏ hơn)
        //---> if(value-low>k) return low+k;
        //
        //Cách 2.1:
        //Vẫn là cách 2 nhưng refactor:
        //1, Tối ưu phần liên quan đến return --> Ta chỉ cần tìm ( index+k+1 <=> arr.length()+k )
        //2, Phần chia điều kiện rõ ràng hơn.
        System.out.println(findKthPositiveOptimize(arr, k));
        System.out.println(findKthPositiveRefactor(arr, k));
        //Reference:
        //+ Kth Missing Positive Number
        //+ Minimum Number of Days to Make m Bouquets
        //+ Find the Smallest Divisor Given a Threshold
        //+ Divide Chocolate
        //+ Capacity To Ship Packages In N Days
        //+ Koko Eating Bananas
        //+ Minimize Max Distance to Gas Station
        //+ Split Array Largest Sum
    }
}
