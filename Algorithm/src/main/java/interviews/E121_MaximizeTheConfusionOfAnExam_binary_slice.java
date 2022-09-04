package interviews;

import java.util.Deque;
import java.util.LinkedList;

public class E121_MaximizeTheConfusionOfAnExam_binary_slice {

    public static int maxConsecutiveAnswers(String answerKey, int k) {
        int rs=0;
        int numSetTrue=k;
        int currentValueTrue;
        int numSetFalse=k;
        int currentValueFalse;

        boolean isDisableTrue;
        boolean isDisableFalse;
        int n=answerKey.length();

        for(int i=0;i<n;i++){
            //True answer
            isDisableTrue=false;
            currentValueTrue=0;
            numSetTrue=k;

            //False answer
            isDisableFalse=false;
            currentValueFalse=0;
            numSetFalse=k;
            for(int j=i;j<n;j++){
                //Case 1: FF(done) TTTTT FFF
                if(answerKey.charAt(j)=='T'){
                    currentValueTrue++;
                    isDisableTrue=false;
                }else if(numSetTrue>0&&!isDisableTrue){
                    numSetTrue--;
                    currentValueTrue++;
                }else {
                    numSetTrue=k;
                    isDisableTrue=true;
                    currentValueTrue=0;
                }
//                System.out.printf("%s %s\n\n",j, currentValueTrue);
                rs=Math.max(rs, currentValueTrue);

                //False answer
                if(answerKey.charAt(j)=='F'){
                    currentValueFalse++;
                    isDisableFalse=false;
                }else if(numSetFalse>0&&!isDisableFalse){
                    numSetFalse--;
                    currentValueFalse++;
                }else {
                    numSetFalse=k;
                    isDisableFalse=true;
                    currentValueFalse=0;
                }
//                System.out.printf("%s %s\n\n",j, currentValueTrue);
                rs=Math.max(rs, currentValueFalse);
            }
        }

        return rs;
    }

    public static int maxConsecutiveAnswersOptimizeOnN(String answerKey, int k) {
        int rs=0;
        int numSetTrue;
        int currentValueTrue;
        int numSetFalse;
        int currentValueFalse;
        int n=answerKey.length();

        for(int i=0;i<n;i++){
            //True answer
            currentValueTrue=0;
            numSetTrue=k;

            //False answer
            currentValueFalse=0;
            numSetFalse=k;
            for(int j=i;j<n;j++){
                //Case 1: FF(done) TTTTT FFF
                if(answerKey.charAt(j)=='T'){
                    currentValueTrue++;
                }else if(numSetTrue>0){
                    numSetTrue--;
                    currentValueTrue++;
                }else {
                    break;
                }
//                System.out.printf("%s %s\n\n",j, currentValueTrue);
                rs=Math.max(rs, currentValueTrue);
            }
            for(int j=i;j<n;j++){
                //False answer
                if(answerKey.charAt(j)=='F'){
                    currentValueFalse++;
                }else if(numSetFalse>0){
                    numSetFalse--;
                    currentValueFalse++;
                }else {
                    break;
                }
//                System.out.printf("%s %s\n\n",j, currentValueTrue);
                rs=Math.max(rs, currentValueFalse);
            }
        }

        return rs;
    }

    public static int maxConsecutiveAnswersOptimizeONWrong(String answerKey, int k) {
        int rs=0;
        int numSetTrue=0;
        int numSetFalse=0;
        int currentValueTrue=0;
        int currentValueFalse=0;
        int n=answerKey.length();
        int leftIndexTrue=-1;
        int rightIndexTrue=0;
        int leftIndexFalse=-1;
        int rightIndexFalse=0;
        int countTrue=0;
        int countFalse=0;

        for(int i=0;i<n;i++){
            //Case 1: FF(done) TTTTT FFF
            //Case 2: FFTTFFFTTTTTTTFFFTTTTT, k=2
            if(answerKey.charAt(i)=='T'){
                currentValueTrue++;
            }else if(answerKey.charAt(i)=='F'){
                if(countTrue<k){
                    rightIndexTrue=i;
                    currentValueTrue++;
                    countTrue++;
                }else{
                    rightIndexTrue=i;
                    leftIndexTrue++;
                    if(leftIndexTrue>=0&&leftIndexTrue<n&&answerKey.charAt(leftIndexTrue)=='T'){
                        leftIndexTrue=rightIndexTrue;
                        currentValueTrue=1;
                        countTrue=1;
                    }
                }
            }
//            System.out.printf("%s %s %s %s\n",i, leftIndexTrue, rightIndexTrue, currentValueTrue);
            rs=Math.max(rs, currentValueTrue);

            //False answer
            //Case 1: FF(done) TTTTT FFF
            //Case 2: FFTTFFFTTTTTTTFFFTTTTT, k=2
            if(answerKey.charAt(i)=='F'){
                currentValueFalse++;
            }else if(answerKey.charAt(i)=='T'){
                if(countFalse<k){
                    rightIndexFalse=i;
                    currentValueFalse++;
                    countFalse++;
                }else{
                    rightIndexFalse=i;
                    leftIndexFalse++;
                    if(leftIndexFalse>=0&&leftIndexFalse<n&&answerKey.charAt(leftIndexFalse)=='F'){
                        leftIndexFalse=rightIndexFalse;
                        currentValueFalse=1;
                        countFalse=1;
                    }
                }
            }
            System.out.printf("%s %s %s %s\n",i, leftIndexTrue, rightIndexTrue, currentValueTrue);
            rs=Math.max(rs, currentValueFalse);
        }

        return rs;
    }

    /*
    - Đọc lại ở đây
     */
    public static int maxConsecutiveAnswersOptimizeONRedunancy(String answerKey, int k) {
        int rs=0;
        int n=answerKey.length();
        //Không dùng cái này để làm gì hết
//        int[] valuesTrue =new int[n];
//        int[] valuesFalse =new int[n];
//        int queueArr[]=new int[k];
//        int front=-1, rear=-1;
        Deque<Integer> queueTrue=new LinkedList<>();
        int currentValueTrue=0;
        int countWindowTrue=0;
        Deque<Integer> queueFalse=new LinkedList<>();
        int currentValueFalse=0;
        int countWindowFalse=0;
//        StringBuilder log1=new StringBuilder();
//        StringBuilder log2=new StringBuilder();
//        StringBuilder log3=new StringBuilder();

        for(int i=0;i<n;i++){
            //F -> T
            currentValueTrue++;
            countWindowTrue++;
            if (answerKey.charAt(i) == 'F') {
//                if(queueTrue.isEmpty()){
//                    countWindowTrue=1;
//                }
                if(queueTrue.size()==k){
                    countWindowTrue=currentValueTrue;
//                    currentValueTrue=countWindowTrue-valuesTrue[queueTrue.pollFirst()];
                    currentValueTrue=i-queueTrue.pollFirst();
//                    valuesTrue[i]=currentValueTrue;
                }else{
//                    valuesTrue[i]=currentValueTrue;
                }
                queueTrue.add(i);
            }
//            System.out.printf("%s (%s), ", currentValueTrue, answerKey.charAt(i));
            rs=Math.max(rs, currentValueTrue);

            // T -> F
            currentValueFalse++;
            countWindowFalse++;
            if (answerKey.charAt(i) == 'T') {
//                if(queueFalse.isEmpty()){
//                    countWindowFalse=1;
//                }
                if(queueFalse.size()==k){
                    countWindowFalse=currentValueFalse;
//                    currentValueFalse=countWindowFalse-valuesFalse[queueFalse.pollFirst()];
                    currentValueFalse=i-queueFalse.pollFirst();
//                    valuesFalse[i]=currentValueFalse;
                }else{
//                    valuesFalse[i]=currentValueFalse;
                }
                queueFalse.add(i);
            }
//            System.out.printf("%s (%s), ", currentValueFalse, answerKey.charAt(i));
//            log1.append(currentValueFalse).append(",");
//            log2.append(answerKey.charAt(i)).append(",");
//            log3.append(countWindowFalse).append(",");
//            System.out.printf("%s,", currentValueFalse);
//            System.out.printf("%s,", answerKey.charAt(i));
            rs=Math.max(rs, currentValueFalse);
        }
//        System.out.println(log2.toString());
//        System.out.println(log3.toString());
//        System.out.println(log1.toString());

        return rs;
    }

    //Cách dùng queue
    public static int maxConsecutiveAnswersOptimizeONOptimize(String answerKey, int k) {
        int rs=0;
        int n=answerKey.length();
        int[] queueArrTrue=new int[n];
        int frontTrue=0, rearTrue=0;
        int currentValueTrue=0;
        int[] queueArrFalse=new int[n];
        int frontFalse=0, rearFalse=0;
        int currentValueFalse=0;

        for(int i=0;i<n;i++){
            //F -> T
            currentValueTrue++;
            if (answerKey.charAt(i) == 'F') {
                if(rearTrue-frontTrue==k){
                    currentValueTrue=i-queueArrTrue[frontTrue++];
                }
                queueArrTrue[rearTrue++]=i;
            }
//            System.out.printf("%s (%s), ", currentValueTrue, answerKey.charAt(i));
            rs=Math.max(rs, currentValueTrue);

            // T -> F
            currentValueFalse++;
            if (answerKey.charAt(i) == 'T') {
                if(rearFalse-frontFalse==k){
                    currentValueFalse=i-queueArrFalse[frontFalse++];
                }
                queueArrFalse[rearFalse++]=i;
            }
            rs=Math.max(rs, currentValueFalse);
        }

        return rs;
    }

    //Cách chia đoạn (count--)
    public static int maxConsecutiveAnswersOptimizeONRefer(String answerKey, int k) {
        int rs=0;
        int n=answerKey.length();
        int maxCount=0;
        int start=0;
        int[] count =new int[128];

        for(int i=0;i<n;i++){
            maxCount=Math.max(maxCount, ++count[answerKey.charAt(i)-'A']);

            while (i-start+1-maxCount>k){
                count[answerKey.charAt(start)-'A']--;
                start++;
            }
            rs=Math.max(rs, i-start+1);
        }

        return rs;
    }

    //Cách beat 99%
    public static int maxConsecutiveAnswersOptimizeONReferOptimize(String answerKey, int k) {
        int rs=0;
        int n=answerKey.length();
        int maxCount=0;
        int[] count =new int[128];

        for(int i=0;i<n;i++){
            maxCount=Math.max(maxCount, ++count[answerKey.charAt(i)-'A']);

            //res --> Chuyển về (i-start)
            //+ Mỗi lần nó luôn tăng rs++ ==> Có thể viết ntn
            if (rs-maxCount<k){
                rs++;
            }else count[answerKey.charAt(i-rs)-'A']--;
        }

        return rs;
    }

    public static void main(String[] args) {
//        String answerKey="TTFF";
//        int k=2;
//        String answerKey="FFTTFFFFFFTTFFFFTTTTT";
//        int k=4;
//        String answerKey="FFTTTTTF";
//        int k=3;
//        String answerKey="FFTTFFFTTTTTTTFFFTTTTT";
//        int k=2;
        String answerKey="FFFTTFTTFT";
        int k=3;

        System.out.println(maxConsecutiveAnswers(answerKey, k));
        System.out.println(maxConsecutiveAnswersOptimizeOnN(answerKey, k));
        //Với cách code này gặp rất nhiều lỗi sai:
        //- Tư duy dùng (countWindowFalse) để lưu số lượng elements trong 1 cycle
        //--> Dùng để tính các phần tử liên tiếp (cần transform trong 1 cycle)
        //VD : F,F,F,T(value=4),T,F,T,T(countWindowFalse=8),F,T,F,F,F,F,F
        //Vẫn là cycle đó thêm 1 transform (T -> F) tức là cần pop 1 element ra
        //--> currentValue = countWindowFalse - ( (độ dài chuỗi trước đó tại index=3 : T )==4 )
        //<=> currentValue = 8 - 4 = 4
        //** Problem : Vấn đề ở đây bao giờ (countWindowFalse) thay đổi
        //VD : FFFF(TT)FF(TT(T)(Tại vị trí này countWindowFalse=1) TTTT) ===> Ý TƯỞNG SAI
        //VD: TTTT(T)TTFTTTTT
        //k=2 --> countWindowFalse luôn = 2 : Vì các phép toán chỉ là (pop + add) đi liên mà thôi
        // ==> Không có reset về 1 ==> Sẽ reset về (value) trong (khoảng) từ (index pop ra) đến (i) .
        // ===> Rất khó có thể làm bằng VALUE.
        //*** Kinh nghiệm : (Slide window)(Chỉ dùng (INDEX) để lấy cycle)
        //+ Lưu value tại (i) là số lượng chuỗi tìm được trước đó ==> Cách suy nghĩ này sai.
        //- Đọc lại thì đọc ở đây
        System.out.println(maxConsecutiveAnswersOptimizeONRedunancy(answerKey, k));
        System.out.println(maxConsecutiveAnswersOptimizeONOptimize(answerKey, k));
        //Bài này tư duy như sau:
        //0, Bài này có khá nhiều cases cần fix:
//        Case 1 : Phải set nhiều đoạn
//        FF(TT)FFFFFF(TT)FFFF(TTTTT)
//        k=4
//        True
//        Không phải lúc nào đánh k cũng bắt đầu bằng T --> Bắt đầu bằng F
        // Không phải lúc nào ta cũng start at T --> Để tính số lượng liên tiếp lớn nhất
        //==> Ta cần start F để có thể replace F --> T + T đằng sau đó --> Tính số lượng liên tiếp lớn nhất.
//        Case 1, Tuy là set nhiều đoạn nhưng vẫn cần phái chạy từ (left --> right)
//        ==> Chỉ cần O(n)
//        Case 1.1
//        (FF)TTTT(F) :
//        + Case này phải (chọn SET 1 trong 2 phía)
//        k=2
//        rs=6
//        Case 1.2 :
//        + Không thể đi 2 chiều (FF)TTTT(F) (k=3 có thể set 2 chiều)
//        + Case này phải start với (F) (Change value ở 2 phía của T)
//        k=3
//        rs=6
//        Case 4:
//        FFF(TT)F(TT)FT : Ở đây có nhiều cách để change (value)
//        3
//        FFFFFFFTFT
//        FFTTTTTTTT
//        wrong : 7
//        expect : 8
//        Case 5:
//        FFF(TT)F(TT)F(T)FFFFF
//        Set F to T
//        FFF(TT)F(TT)F(T)FFFFF
//        123 45 5 67 7 8
        //Cách 1:
        //1, Độ phức tạp O(n^2)
        //1.1, Với (n) việc start at (i) --> Ta có thể cover được hết các trường hợp bù (k) elements.
        //1.2, Với cách suy nghĩ như thế này
        //VD: Vị trí (i) ta sẽ replace tất cả (k) phần tử từ vị trí (i) trở đi ==> Sau đó tính (số lượng các ký tự T liên tiếp)
        //==> N trường hợp bắt đầu với vị trí (i) --> Cover all cases.
        //
        //Cách 2:
        //1, Độ phức tạo O(n)
        //2,
        //Với cách code này gặp rất nhiều lỗi sai:
        //2.1, Tư duy theo dạng
        //FFF(TT)F(TT)F(T)
        //    12   1 0
        //==> Tư duy theo dạng trừ dần value, (1,2,3) --> (2,1,0)
        //+ Với tư duy theo dạng này TT(FF)[ (FF)TTT(F) ], k=3
        //--> Khó tìm ra kết quả trong [] = 5
        //Khi mà (currentValue) (Kết quả bài toán thay đổi) ==> không biết khi nào thì ta sẽ thay đổi
        //--> (Khi mà sau (FF) vẫn còn 2 (TT), currentValue mới khi chọn (FF) từ vị trí (index=4) cần phải loại các value (TT) =2 đi.
        //
        //2.2,
        //* Tư duy dùng leftIndex, rightIndex --> Không thích hợp vì ta cần lưu danh sách index cần loại đi
        //- Tư duy queue (Add index) : Dùng để lưu danh sách index của các elements dùng để (change T -> F/ F -> T)
        //+ Mục đích để loại bỏ case : (TTFF)TTTTTF --> Khi ta bỏ F thì ta cũng phải bỏ đi số lượng T sau F đó (VD: Number(T)=2)
        //==> Tư duy ngu ngốc là ta sẽ tính currentValue tại vị trí (F) --> pop thì lấy currentValue trừ đi (Tư duy khởi nguyên)
        //+ mặc dù sẽ bị sai --> Do nếu lấy currentValue - (trừ) ==> Không được
        //+ currentValue được update liên tục ---> Ta cần tính 1 biến cycle trong lần check đó
        // ==> (countWindowFalse) : luôn cộng liên tục ++, chỉ Update khi hết cycle đó (Mặc dù tư duy này cũng sai)
        //---> Sai do (countWindowFalse) luôn cộng lên (Không bao h reset)
        //+ (countWindowFalse) - value_pop (không tính được là nó tính đến vị trí nào)
        //VD: TTTTFF(FF)(TT)FF(TT(T), k=2
        //Với case nhiều FFFF liên tiếp với (k==2) --> Tính (countWindowFalse) rất khó do:
        //+ countWindowFalse không reset về 1 --> reset trong khoảng pop ra cho đến (i) --> dùng (i) cho nhanh (- trừ đi index pop ra)
        //
        //2.3, Tư duy dùng (countWindowFalse) để lưu số lượng elements trong 1 cycle
        //--> Dùng để tính các phần tử liên tiếp (cần transform trong 1 cycle)
        //VD : F,F,F,T(value=4),T,F,T,T(countWindowFalse=8),F,T,F,F,F,F,F
        //Vẫn là cycle đó thêm 1 transform (T -> F) tức là cần pop 1 element ra
        //--> currentValue = countWindowFalse - ( (độ dài chuỗi trước đó tại index=3 : T )==4 )
        //<=> currentValue = 8 - 4 = 4
        //** Problem : Vấn đề ở đây bao giờ (countWindowFalse) thay đổi
        //VD : FFFF(TT)FF(TT(T)(Tại vị trí này countWindowFalse=1) TTTT) ===> Ý TƯỞNG SAI
        //VD: TTTT(T)TTFTTTTT
        //k=2 --> countWindowFalse luôn = 2 : Vì các phép toán chỉ là (pop + add) đi liên mà thôi
        // ==> Không có reset về 1 ==> Sẽ reset về (value) trong (khoảng) từ (index pop ra) đến (i) .
        // ===> Rất khó có thể làm bằng VALUE.
        //
        //3, Dùng queue dạng Array --> Tốt hơn khi sử dụng lại (Cùng kích thước)
        //==> Do chỉ cần reset front=0, rear=0.
        //
        //*** KINH NGHIỆM :
        //- (Slide window)(Chỉ dùng (INDEX) để lấy cycle)
        //
        //Cách 3:
        //
        System.out.println(maxConsecutiveAnswersOptimizeONRefer(answerKey, k));
        //3.1, Tương tự bài (E122_LongestRepeatingCharacterReplacement) tối ưu:
        // res --> Chuyển về (i-start)
        //+ Mỗi lần nó luôn tăng rs++ ==> Có thể viết ntn
        //=============CODE=============
        //if (rs-maxCount<k){
        //    rs++;
        //}else count[answerKey.charAt(i-rs)-'A']--;
        //=============CODE=============
        System.out.println(maxConsecutiveAnswersOptimizeONReferOptimize(answerKey, k));
    }
}
