package interviews;

public class E99_KokoEatingBananas_binary {

    public static int minEatingSpeed(int[] piles, int h) {
        int max=0;
        int n=piles.length;

        for(int i=0;i<n;i++){
            max=Math.max(max, piles[i]);
        }
        if(h==n){
            return max;
        }
        int low=1;
        int high=1_000_000_009;
        int rs=0;

        while(low<=high){
            int mid=low + (high-low)/2;
            int count=0;

            for(int i=0;i<n;i++){
                count+=Math.ceil((double) piles[i]/mid);
//                System.out.println((piles[i]+mid-1)/mid);
                System.out.println(count);
            }
            System.out.printf("count: %s, mid: %s\n",count, mid);
            if(count>h){
                low=mid+1;
            }else{
                rs=mid;
                high=mid-1;
            }
        }
        return rs;
    }

    public static int minEatingSpeedOptimize(int[] piles, int h) {
        int n=piles.length;

        //1, Cần đổi thành 1 để tránh chia /0
        int low= 1;
        int high=1000000000;
        int rs=0;

        while(low<=high){
            int mid=low + (high-low)/2;
            int count=0;

            for(int i=0;i<n;i++){
//                count+=Math.ceil((double) piles[i]/mid);
                count+=(piles[i]+mid-1)/mid;
//                System.out.println((piles[i]+mid-1)/mid);
//                System.out.println(count);
            }
//            System.out.printf("count: %s, mid: %s\n",count, mid);
            if(count>h||count<0){
                low=mid+1;
            }else{
                rs=mid;
                high=mid-1;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{3,6,7,11};
//        int h=8;
//        int arr[]=new int[]{30,11,23,4,20};
//        int h=5;
//        int arr[]=new int[]{30,11,23,4,20};
//        int h=6;
        //Case 3: Sum vượt quá int --> return 0
//        int arr[]=new int[]{332484035,524908576,855865114,632922376,222257295,690155293,112677673,679580077,337406589,290818316,877337160,901728858,679284947,688210097,692137887,718203285,629455728,941802184};
//        int h=823855818;
        ////Case 4: Case liên quan đến (a+b-1)/b --> Nếu cộng vào count sẽ bị quá INT
        //<> dùng Math.ceil thì không bị quá.
        int arr[]=new int[]{805306368,805306368,805306368};
        int h=1000000000;
        System.out.println(minEatingSpeed(arr, h));
        System.out.println(minEatingSpeedOptimize(arr, h));
        //Cần hiểu rõ đề bài trước:
        //1, Vấn đề chính ở đây là chọn những piles nào để ăn:
        //---> Đề bài cho 1 case tức là chỉ có 1 case có thể xảy ra thôi.
        //Nhưng
        //Input: piles = [3,6,7,11], h = 8
        //Output: 4
        //==> Ở đây sẽ xảy ra cả 2 cases : (Nhỏ hơn/ lớn hơn)
        //+ Còn 1 case nữa là lớn hơn, nếu lớn hớn thì trừ như thế nào?
        //VD: Khi nào thì chọn (3)/ (3,6)/ (3,7)
        //
        //2,
        //Ở đây tìm k nhỏ nhất --> Tức là :
        //+ Chia làm sao cho tối ưu nhất
        //VD: [3,6,7,11]
        //Giả sử k=3
        //1, 3
        //2, (6,7,11) --> ăn 3 (3,6,11)
        //3, (3, 7, 11) --> (7,11)
        //4, (7,11) --> (4,11)
        //5, (4,11) --> (1,11)
        //6, (1,11) --> (1,8)
        //7, (1,8) --> (1,5)
        //8, (1,5) --> ....
        //==> Không được.
        //
        //Giả sử k=5
        //1, (3,6,7,11) --> (4,7,11)
        //2, (4,7,11) --> (6,11)
        //3, (6, 11) --> (1,11)
        //4, (1,11) --> (7)
        //5, (7) --> (2)
        //6, (2) --> ()
        //7,
        //8,  --> ....
        //
        //--> Khi ăn thì số lượng sẽ được giảm đi.
        //Nếu mình chọn ăn những
        //
        //VD
        //30,11,23,4,20
        //k=30
        //Với test case này ta xác định được rằng
        //koko cần ăn liên tiếp --> Vì nếu không ăn liên tiếp thì ta đã chọn 24 là kết quả rồi.
        //
        //1, (30,11,23,4,20) --> (11,23,4,20)
        //2, (11,23,4,20) --> (0, 4, 4, 20)
        //3, (6, 11) --> (1,11)
        //4, (1,11) --> (7)
        //5, (7) --> (2)
        //=====> Hiểu sai
        //Đề bài:
        //- Ân lần lượt (0 --> n-1)
        //- Nếu gặp piles[i] > k --> (-k)
        //- Nếu <k --> Chỉ được phép ăn cái đó.
        //---> Tìm k sao cho sau h hours thì ăn xong.

        //Bài này tư duy như sau:
        //1, Bài này có 1 số lỗi sai trong tư duy cần phải khắc phục:
        //1.1, Phần chia range cho mid cần clear, chỉ cần sai 1 dấu = ở 2 phần so sánh (count>=h)/ (count<=h)
        // thì tư duy nó cũng sẽ khác hẳn
        //Ở đây nếu
        //+ mid > count : tức là số hiện tại quá lớn
        //+ mid < count : tức là số hiện tại quá nhỏ
        //+ mid == count : Tức là ta đã tìm được ít nhất 1 kết quả phù hợp.
        //1.2, Binary search dạng bài có thể tìm kiếm thấy nhiều kết quả phù hợp
        //** CHÚ Ý : Dạng này cần phải tạo (var RS "RIÊNG")
        //VD:
        //(low) 1, (2,4,5), (high)10
        //Giả sử (2,4,5) đều là giá trị k thỏa mãn
        //+ Ta sẽ expect ở đây là giá trị min
        // --> khi (count==h) rs=mid; high=mid-1
        // ==> (DỊCH SANG TRÁI ĐỂ LẤY GIÁ TRỊ MIN NHẤT);
        // <> trong trường hợp tìm MAX --> Ta sẽ dịch sang right.
        //1.2.1, Quy luật cần phải nắm chắc:
        //+ low+1 : Tức là dịch value sang right (value nhỏ đi)
        //+ high-1 : Tức là dịch value sang left (value lớn hơn)
        //
        //** Để dấy = ở điều kiện mà ta muốn tím kiếm
        //VD: như ở trên tìm MIN --> Ta sẽ để dấu = ở chỗ (GIẢM DẦN)
        //CODE
        //========
        // ==> if(count<=h) high=mid-1; rs=mid;
        //========
        //2, Tối ưu:
        //2.1, Ta có thể dùng 1_000_000_000 <=> sum
        //sum quá lớn
        //2.2, Vấn đề chính ở đây là :
        //+ Chuyển đổi Math.ceil((double) piles[i]/mid) ==> (piles[i]+mid-1)/mid
        //* Chú ý : (piles[i]+mid-1)/mid
        //Nếu cộng count += (piles[i]+mid-1)/mid
        //----> Có thể dẫn đến quá số
        //+ Nhưng nếu dùng count+ = Math.ceil((double) piles[i]/mid) ===> Không bị quá số INTEGER.
        //---> Nó sẽ tự động chuyển về (MAX_INTEGER_VALUE)
        //2.3, Để tránh /mid=0 --> low=1
    }
}
