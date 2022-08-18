package interviews;

public class E106_MinimizedMaximumOfProductsDistributedToAnyStore {

    public static int minimizedMaximum(int n, int[] quantities) {
        int low=1;
        int high=0;
        int length=quantities.length;

        if(n==1&&length==1){
            return quantities[0];
        }

        for(int i=0;i<length;i++){
            high=Math.max(high, quantities[i]);
        }
        //Thêm dòng này vào win 100%
        if(n==length){
            return high;
        }
        int rs=0;

        while (low<=high){
            int mid=low + (high-low)/2;
            int count=0;

            for(int i=0;i<length;i++){
                count+=(quantities[i]+mid-1)/mid;
            }
            if(count>n){
                //1, Bài này nếu để mid trên đây sẽ bị sai
                //2, chỗ này nếu gán low=mid ==> Sẽ bị loop vô tận
                //==> Cần nhớ rõ tại sao
                low=mid+1;
            }else {
                rs=mid;
                high=mid-1;
            }
        }

        return rs;
    }

    public static void main(String[] args) {
//        int[] quantities=new int[]{15,10,10};
//        int n=7;
        //Case 1: Case này dùng để giải quyết bài toán
//        int[] quantities=new int[]{11,6};
//        int n=6;
        //Case 2: Trường hợp có 1 element
//        int[] quantities=new int[]{100000};
//        int n=1;
        //Case 3 : Này đặc biệt khi n==length()
        //==> Lúc đó chỉ còn 1 cách duy nhất là lấy toàn bộ mảng chính là phép chia
        //Result : max(quantities[i])
        int[] quantities=new int[]{5,7};
        int n=2;
        //Bài này tư duy như sau:
        //1, ta dùng tư duy theo dạng range + condition như bình thường với :
        //- low : 1 (Không phải min vì nếu để min --> Bị sai case {5,7}
        //- high : max(quantities[i])
        //---> Sau đó ta sẽ chạy binary như bình thường để tìm result
        //2, Bài này có 1 số lỗi liên quan đến tư duy như:
        //
        //CODE: ========================
        //if(count>n){
        //                //1, Bài này nếu để mid trên đây sẽ bị sai
        //                //2, chỗ này nếu gán low=mid ==> Sẽ bị loop vô tận
        //                //==> Cần nhớ rõ tại sao
        //                low=mid+1;
        //            }else {
        //                rs=mid;
        //                high=mid-1;
        //            }
        //2.1, Câu hỏi nên để rs ở đâu, trên hay dưới?
        //- Phụ thuộc vào mục đích tìm max/ min
        //+ Min thì ta sẽ để:
        //rs=mid; high=mid-1 ===> Ta bỏ qua giá trị (mid) vì ta đã tìm thấy 1 kết quả + được save ở rs rồi ==> move tiếp (mid-1)
        //- Nếu để như trên (tức là dấu <= được dùng --> rs sẽ đứng ở phía đó):
        //VD : ta có 1 loạt các result liên tiếp:
        //...res1,res2,res3,res4,res5(high)...
        //+ High lúc này sẽ run từ từ (right --> left)
        //--> rs lúc này sẽ chạy đến res1 ==> Min nhất (res1)
        //Tương tự:
        //...res1(low),res2,res3,res4,res5...
        //+ Low lúc này sẽ chạy từ (left --> right)
        //--> rs bắt buộc phải để trong đó --> Max nhất (res5)
        //***KẾT LUẬN:
        //- rs đi theo dấu =
        //- đi từ high --> tìm MAX.
        //- đi từ low ---> tìm MIN.
        System.out.println(minimizedMaximum(n, quantities));
    }
}
