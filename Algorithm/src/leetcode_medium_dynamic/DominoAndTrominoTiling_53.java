package leetcode_medium_dynamic;

public class DominoAndTrominoTiling_53 {

    public static int numTilings(int n) {
        int dp[]=new int[2*n+1];

        if(n==1){
            return 1;
        }
        dp[2]=1;
        if(2*n+1>3){
            dp[3]=2;
        }
        if(2*n>=4){
            dp[4]=2;
        }
        for(int i=5;i<=2*n;i++){
            if(i%2==0){
//                int sub=0;
//                if(i-4==2){
//                    sub=1;
//                }else{
//                    sub=dp[i-4];
//                }
//                int even=dp[i-2]*2-dp[i-4];
                int even;
                even=(dp[i-2]+dp[i-4])% 1000000007;
                //Chỗ này chỉ có 1 trường hợp thôi
                //Vì (i) là số dấu chấm --> Chứ không phải chia theo vị trí
                int odd=dp[i-3];
                dp[i]=(even+odd)% 1000000007;
            }else{
//                int k=i-3;
//                int r=2;
//
//                for(int j=k;j>=0;j-=2){
//                    if(j==2){
//                        r=(r+2)% 1000000007;
//                        continue;
//                    }
//                    r=(r+(dp[j]*2% 1000000007))% 1000000007;
//                }
//                dp[i]=r% 1000000007;
                dp[i]=(dp[i-2]+ dp[i-3]*2% 1000000007)% 1000000007;
            }
        }
        return dp[2*n]% 1000000007;
    }

    public static int numTilings1(int n) {
        long[] dp = new long[n + 3]; dp[0] = 1; dp[1] = 2; dp[2] = 5;
        for (int i = 3; i < n; i ++) {
            dp[i] = (dp[i - 1] * 2 + dp[i - 3]) % 1000000007;
        }
        return (int)dp[n - 1];
    }

    public static void main(String[] args) {
//        int n=5;
//        int n=3;
//        int n=4;
        int n=30;
        //Giải thích đầy đủ về tư duy trong note, VNTRAVEL:

        //Bài này tư duy như sau:
        //1, Đầu tiên ta cần xác định dp[i] có ý nghĩa như thế nào?
        //1.1, Có 2 lựa chọn ở đây:
        //+ Dựa trên số chấm dp[i]: (i) Là số chấm
        //+ Dựa trên độ dài dp[i]: (i) là độ dài
        //2, Ở đây mình nhìn vào thì không rõ là nếu làm theo (i: chiều dài) hơi khó?
        //--> Mình đã chọn đi theo số chám (i)
        //3, 1 số các quan điểm sai lầm làm mất thời gian!
        //VD: [6]= [4]*[2]*2 -1
        //--> Bên trên là 1 quan điểm sai lầm
        //Mình đã liệt kê các kết quả của [2],[3],[4],...
        //3.1, Ở đây mình gặp vấn đề liên quan đến số (2) nó có thể sẽ có 2 kết quả:
        //+ 1: Khi nó đứng 1 mình (Chỉ có thể dựng đứng thẳng)
        //+ 2: Khi nó có thể kết hợp với các domino khác
        //Solution:
        // - Ở đây d[2] ban đầu mình sẽ gán =1, còn sau nó tính theo (2) --> Mình sẽ tính nó là (2)
        // - Sau đó mình mới phát hiện ra, vấn đề chính ở đây chính là việc nhân 1 domino cuối
        //--> Không cần thiết phải tính theo dp[2] : Vì nếu tính [2] là số cuối
        // --> dp[i]= 2* dp[i-2] thay vì dp[i]=dp[2]*dp[i-2]
        //3.2, Mình bị nhầm với việc tính số trường hợp theo kiểu hoán đổi vị trí:
        //dp[i]=(dp[2]:2) * dp[i-2] *2 - (Trường hợp trùng)
        // - Ở đây mình tư duy sai lầm như sau:
        // VD:
        //dp[6]=dp[4]*dp[2]
        //Tức là việc mình swap: [4]|[2] và [2]|[4] là khác nhau
        //--> Ta tìm cách trừ đi các trường hợp trùng của [2],[4]
        //+ Nếu [4] chứa [2] thì: Có rất nhiều các tường hợp trùng nhau
        //** Cái sai ở đây là "Ta đang muốn tính số trường hợp kết thúc tại (domino=2)"
        //--> Bỏ những (trường hợp !=2 đi)
        //NHƯNG: Số trường hợp: Kết thúc tại (2) --> Bị DUPLICATE
        //Solution: Mình đã nghĩ ra việc:
        //Số trường hợp trùng: đơn giản là số trường hợp kết thúc tại vị trí (2)
        //*** Cố định 2 (SỐ CHẴN)
        //** Trường hợp 1:
        //- dp[i]+=dp[i-2] : Ở đây sự kết thúc với (2) nhưng mà là (2: đứng thẳng)
        //----> Không chỉ liên quan đến số (i)
        //######### Mà còn liên quan đến (i) ở đây đặc điểm như thế nào #########
        //** Trường hợp 2:
        //dp[i]+=dp[i-4] : Ở đây sự kết thúc tại (2) nằm ngang
        //--> Nằm ngang thì phải là 2 domino (i==4)

        //** Cố định 3: SỐ LẺ
        //- Ở đây việc cố định 3 sẽ phức tạp hơn
        //Nếu ta tính chi tiết về kết hợp [2] với [3]
        //--> Trong trường hợp này ta swap được
        //Các trường hợp như sau:
        //+ [3] (Có 2 trường hợp đứng thẳng chiều/ Ngược chiều) | [2]
        //Lúc đó (2) sẽ chỉ có thể kết hợp sau khi kết hợp length(i)=3 (Nhét xuống dưới)
        //+ [2] | [3]: [2] bắt buộc đứng thẳng, (Có 2 trường hợp đứng thẳng chiều/ Ngược chiều)
        //--> Length(i)=3 --> Vì sau đó (3) cũng có thể kết hợp để tạo thành (Khối chữ nhật)
        //Lỗi sai:
        //+ Việc swap: Sẽ bị thiếu trường hợp vì [2] cũng có thể kết hợp với ("từng") [3] trong dp[i-3]
        //--> Sau đó mình đã (k-3)
        //Xét (j: k-3) --> j-=2
        //dp[i]+=dp[j]
        //** Explain: [3] kết hợp từng với [i=-2] sau đó.
        //----> Chậm.
        //SOLUTION:
        //CT: dp[i]=(dp[i-2]+ dp[i-3]*2% 1000000007)% 1000000007;
        //Ở đây:
        //Ta sẽ cố định không quan tâm đến (i: Số điểm trong domino)
        //---> ##### Cố định (domino đứng cuối là cái gì)
        //+ Domino đứng cuối có thể là (2/3)
        //---> Vì [3] có 2 trường hợp (Đứng thẳng/ Đứng ngược)
        //+ Kinh nghiệm cho việc phân chia trường hợp của trường hợp
        //VD: LẺ/CHẴN --> Trường hợp con
        //--> Các trường hợp con (Có thể giao nhau / có thể trùng nhau)
        //--> Vì ta đã chia lẻ chẵn rồi!

        //EXP:
        //- (i): ngoài quan tâm đến số ---> Cần ý nghĩa nữa (Nằm ngang, nằm dọc).
        //- Chú ý các trường hợp lặp --> Thường sẽ khó ra kết quả hơn: Vì có khả năng bị duplicate
        //- Với các bài tổng số điểm kiểu như thế này --> Không nên dùng cơ chế swap để có thể tính số trường hợp xảy ra.
        //VD: [4]|[2] <-> [2]|[4] (swap)
        //- Trường hợp cuối cùng nên không thực hiện hóa với, VD: dp[4] * (dp[2]) --> SAI (Về tư duy)
        //- Đừng tập trung vào số điểm (i) ----> Cố định (domino đứng cuối là cái gì)
        //VD:
        //dp[i]=dp[i-2]+ dp[i-3]*2
        //+ Domino đứng cuối có thể là (2/3)
        //---> Vì [3] có 2 trường hợp (Đứng thẳng/ Đứng ngược)
        //- Kinh nghiệm cho việc phân chia trường hợp của trường hợp
        //VD: LẺ/CHẴN --> Trường hợp con
        //--> Các trường hợp con (Có thể giao nhau / có thể trùng nhau)
        //--> Vì ta đã chia lẻ chẵn rồi!
        System.out.println(numTilings(n));
        System.out.println(numTilings1(n));
    }
}
