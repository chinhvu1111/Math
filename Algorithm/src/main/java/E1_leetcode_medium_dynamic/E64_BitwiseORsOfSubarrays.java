package E1_leetcode_medium_dynamic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class E64_BitwiseORsOfSubarrays{
    public static HashMap<Integer, Boolean> result;
    //1, Không dùng left, right dp[][] vì độ dài của array: 5 * 104
    public static int mapping[];

//    public static int solution(int arr[]){
//
//    }

    public static int subarrayBitwiseORsWrong(int[] arr) {
        int n=arr.length;
        result=new HashMap<>();
        mapping=new int[1 << 15+1];
        int arrConst=1<<15;

        for(int i=2;i<=n;i++){
            for(int j=0;i+j<n;j++){
                int k=i+j-1;

                int left=arrConst>>(j+1);
                int right=arrConst << (n-k-2);
                int currentArr=arrConst | left | right;
                int leftBefore=arrConst>>(j+2);
//                int rightBefore=arrConst << (n-k-1);
                int keyLeftBefore=arrConst | leftBefore | right;
//                int keyRightBefore=arrConst | left | rightBefore;
                int valueLeftBefore=mapping[keyLeftBefore];

                mapping[currentArr]=valueLeftBefore | arr[j];
                result.put(valueLeftBefore | arr[j], true);
            }
        }
        return result.size();

    }

    public static int subarrayBitwiseORs(int[] arr) {
        HashSet<Integer> rs=new HashSet<>();
        HashSet<Integer> currentResult;
        HashSet<Integer> beforeResult=new HashSet<>();
        int n=arr.length;
        rs.add(arr[0]);
        beforeResult.add(arr[0]);

        for(int i=1;i<n;i++){
            currentResult=new HashSet<>();
            currentResult.add(arr[i]);

            for(Integer e:beforeResult){
                if((arr[i]|e)==e){
                    break;
                }
                currentResult.add(arr[i]|e);
            }
            rs.addAll(beforeResult=currentResult);
        }
        return rs.size();
    }

    public static int subarrayBitwiseORsOptimized(int[] arr) {
        Set set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
            for(int j=i-1;j>=0;j--){
                if((arr[j]|arr[i])==arr[j]){
                    break;
                }
                //arr[i]|=arr[j];
                //set.add(arr[i]);
                arr[j]|=arr[i];
                set.add(arr[j]);
            }
        }
        return set.size();
    }

    public static void main(String[] args) {
        System.out.println(1 << 15);
//        int arr[]=new int[]{1,1,2};
        int arr[]=new int[]{1,2,4};
//        int dp[]=new int[1_000_000_000];

        //Vấn đề ở đây là số quá lớn
        //0 <= nums[i] <= 109
        //--> Lưu ở array không thể được.
        //1, Ở đây có thể ta sẽ lưu chiều dài theo length của array
        // dp[i][j] nhưng length 1 <= nums.length <= 5 * 104 --> quá lớn
        //--> Ý tưởng sẽ dùng số nhị phân để lưu thông tin.
        //Ý tưởng này sai:
        //Khi lưu array thì số lượng phần tử của decimal < so với tính theo chiều dài chuỗi nhị phân bình thường
        //VD: length = 10
        //1111111111 = 1048575
        //So với việc dùng dp[i: 0--> n-1][j:0--> n-1] = 10*10 =100
        //--> case này số lượng phần tử nếu dùng nhị phân 1048575 > 100 rất nhiều --> Cách này sai.
        //2, Vấn đề liên quan đến tìm các cặp khác nhau --> Vì số quá lớn
        //Nhưng chỉ lớn theo max --> Số lượng chữ số không nhiều --> Ta sẽ sử dụng hashMap để tìm kiếm cặp trùng
        //--> Ý tưởng ở đây là sử dụng Hash để check số cặp trùng với độ phức tạp là k
        //Kinh nghiệm:
        //+ Không sử dụng HashMap để check giống nhau --> Vì lưu 2 params (Tăng vùng nhớ)
        //+ Sử dụng HashSet thay thế HashMap

        //############2.1, Đối với cách nghĩ theo kiểu tư duy theo gián tiếp length
        //Tức là [length+1=f(length)
        //--> Việc lưu: length=1, length=2: Không thể được vì kích thước Array quá lớn (Lưu theo decima [i][j], binary đều không được)
        //Kinh nghiệm:
        // + Đáng nhẽ chỗ này nên dừng luôn việc tính lưu kết quả theo length
        // + Đáng nhẽ mình nên nghĩ cách xử lý bài toán --> Thay vì break ra các câu hỏi nhỏ + các câu hỏi đó không phục vụ cho việc
        //xử lý như:
        //2.1.1, Quan hệ giữa phép (or) (+)
        //+ Or sẽ có các khả năng (Giữ nguyên / + thêm)
        //VD
        //3 OR 1=3 --> Tức là 3 có thể tách thành 2 số (X) or 1
        // ---> Mình đã nghĩ cho việc complexity O(n) nhưng nó xày ra case:
        //1,2,6,4
        // (1|2|6|4) != (2|6|4)
        //+ --> Không thế sinh ra (2|6|4) --> Nếu chỉ dùng 1 loop
        //+ kết quả phía sau khi dùng (2|6|4=6) --> Sẽ khác với dùng (1|2|6|4) OR tiếp
        //--> Vì các sô mà để trông chữ số 0 + bù 1 vào là rất nhiều
        //VD: a|b|c|d != b|c|d --> Tồn tại rất nhiều
        //2.2, Tư duy sai lầm về việc suy nghĩ tiếp đến các cases loại trừ reduce:
        //Cases:
        //+ 1,1,2: Trùng khi sinh ra cùng nhau
        //+ 1,2,4,1,2: Trùng khi sinh ra riêng biệt nhưng giống nhau
        //+ 3,4,2,5 :  Trùng khi sinh ra riêng biệt nhưng khác nhau
        //---> Xét các cases này --> chỉ chứng tỏ nếu loop 1 vòng thì không làm được.
        //There are at most 30 bits for a positive number 0 <= A[i] <= 10^9.
        //So there are at most 30 different values for B[0][i], B[1][i], B[2][i], ..., B[i][i].
        //Finally cur.size() <= 30 and res.size() <= 30 * A.length()
        //
        //In a worst case, A = {1,2,4,8,16,..., 2 ^ 29}

        //############2.2, Đối với cách nghĩ theo kiểu trực tiếp
        //2.2.1, Cách nghĩ này :
        // + Ta sẽ lưu các kết quả của mỗi bước là (i)
        // + Kết quả [i]=fx(i-1)
        // + Kết quả cuối cùng rs= f(0) + f(1) +... f(n-1)
        //Tức là vỡi môi (i) ta có 1 số trường hợp nào đó
        //Với range Array (0 --> n-1) --> Ta xét mọi (i) sao cho các trường hợp
        //** (i) độc lập với (i+1) (i-1) --> SUM(i: 0--> n-1) chính là kết quả
        //Tưu duy như sau:
        //Vì (i) --> Tính theo (i-1) mà syb array liên tiếp nhau
        //VD: (1,1,2,4)
        //(i)=4 nối (i-1) = 2
        //--> 4 sẽ chỉ nối với các phần tử kết thúc bởi (2)
        //==> Kết luận dp[i] sẽ lưu số phần tử liên tiếp kết thúc bởi (i)
        //(4) sẽ kết hợp các chuỗi kết thúc bởi (2)
        //* Câu hỏi:
        //+ Thế còn các chuỗi kết thúc bởi (1)
        //--> Nhìn xa ta đã tính trước + đã được (Thêm/Ghi đè) vào result.
        //Kinh nghiệm: all cases thường sẽ là:
        //+ (i) độc lập (i-1)
        //+ Thường là bắt đầu/ kết thúc (start/ end) bởi (i)
        //3, Tối ưu
        //Không dùng HashSet lưu kết quả trung gian
        //3.1, Cách tư duy khác biệt hoàn toàn:
        //+ Nếu ta muốn kết quả cuối cùng rs: --> Array source thay đổi ra sao cũng được
        //source | x --> Sẽ được lưu lại vào rs và (source) sẽ không cần dùng lại khi kết hợp với (y) vì:
        //source luôn đi sau là (x) --> Chứ không phải (y)
        //Tư duy như sau:
        //+ Với việc thêm 1 arr[i]
        //--> Các kết quả trung gian được tính trước đó (j: (i-1) --> 0) sẽ được nối với (i)
        //+ Ta sẽ nối lần lượt và bỏ qua luôn các case (x|y=x) --> Vì có xét thêm thì cùng không cần
        //+ arr[j]|=arr[i];:
        //*** Tư tưởng ở đây là tư tưởng arr[i] sẽ lưu kết quả kết thúc tại (i) (tính từ CUỐI lên ĐẦU) (n-1 --> 0)
        //--> Dùng array (i-1: n-1 --> 0) để khớp với kết quả trước + (bỏ qua nếu OR không tác dùng gì luôn)
        //case:
        //1,2,4,1
        //+ (1) Or end(4) --> Chỉ cần 1 case (4 OR (i)=1) --> Break
        //+ Xét từ cuối kết quả trước trở đi --> Mới khớp (vì trước có (4) rồi => Sau chắc chắn sẽ có 4)

        //Kinh nghiệm:
        //+ Không sử dụng HashMap để check giống nhau --> Vì lưu 2 params (Tăng vùng nhớ)
        //+ Sử dụng HashSet thay thế HashMap
        //All cases thường sẽ là:
        //+ (i) độc lập (i-1)
        //+ Thường là bắt đầu/ kết thúc (start/ end) bởi (i)
        //Tư tưởng kết thúc tại (i) có 2 tư tưởng:
        //+ Từ cuối lên (n-1 --> 0)
        //Quy luật code:
        //- Các số từ (i: 0 --> n-1) sẽ add lần lượt các phần tử từ đầu xuống (0--> n-1)
        //--> Cha tính trước sau đó đến con??? Hơi dị.
        // ---> Miễn là kết quả đúng.
        //+ Từ đầu xuống (0 --> n-1)
//        System.out.println(subarrayBitwiseORs(arr));
        System.out.println(3|4);
        System.out.println(2|5);
        System.out.println(1|2|6|4);
        System.out.println(2|6|4);
        System.out.println(subarrayBitwiseORs(arr));
        System.out.println(subarrayBitwiseORsOptimized(arr));
    }
}
