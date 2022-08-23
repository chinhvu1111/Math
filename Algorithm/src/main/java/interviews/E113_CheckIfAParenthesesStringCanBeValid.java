package interviews;

public class E113_CheckIfAParenthesesStringCanBeValid {

    public static boolean canBeValidWrong(String s, String locked) {
        int[] stack=new int[s.length()];
        int top=0;
        int n=s.length();

        for(int i=0;i<n;i++){
            if(s.charAt(i)=='('){
                stack[top++]=i;
            }else if(top!=0){
                if(s.charAt(stack[top-1])=='('&&s.charAt(i)==')'){
                    top--;
                }else{
                    stack[top++]=i;
                }
            }else{
                stack[top++]=i;
            }
        }
        for(int i=0;i<top;i++){
            System.out.printf(String.valueOf(s.charAt(stack[i])));
        }
        System.out.println();
        for(int i=0;i<top;i++){
            System.out.printf(String.valueOf(locked.charAt(stack[i])));
        }
        System.out.println("\n-------------------");
        for(int i=top-1;i>=1;i-=2){
            if((s.charAt(stack[i])=='('&&s.charAt(stack[i-1])=='('&&locked.charAt(stack[i])=='0')
            ||(s.charAt(stack[i])==')'&&s.charAt(stack[i-1])==')'&&locked.charAt(stack[i-1])=='0')
            ||(s.charAt(stack[i])=='('&&s.charAt(stack[i-1])==')'&&locked.charAt(stack[i])=='0'&&locked.charAt(stack[i-1])=='0')){
                top-=2;
            }else{
                return false;
            }
        }

        return top<=0;
    }

    public static boolean canBeValidWrong1(String s, String locked) {
        int n=s.length();
        char[] stack=new char[n];
        char[] stackChar=new char[n];
        int topChar=0;
//        int closeBracket[]=new int[n];
//        int openBracket[]=new int[n];
        int top=0;
        int closeBracketCount=0;
        int openBracketCount=0;

        for(int i=0;i<n;i++){
//            openBracket[i]=openBracketCount;
            if(s.charAt(i)=='('){
                openBracketCount++;
            }
//            closeBracket[n-i-1]=closeBracketCount;
            if(s.charAt(n-i-1)==')'){
                closeBracketCount++;
            }
        }

        for(int i=0;i<n;i++){
            if(s.charAt(i)==')'&&openBracketCount<closeBracketCount&&locked.charAt(i)=='0'){
                stackChar[topChar++]='(';
                openBracketCount++;
                closeBracketCount--;
            }else if(s.charAt(i)=='('&&openBracketCount>closeBracketCount&&locked.charAt(i)=='0'){
                stackChar[topChar++]=')';
                openBracketCount--;
                closeBracketCount++;
            }else {
                stackChar[topChar++]=s.charAt(i);
            }
        }
        for(int i=0;i<n;i++){
            if(top!=0){
                if(stack[top-1]=='('&&stackChar[i]==')'){
                    top--;
                }else{
                    stack[top++]=stackChar[i];
                }
            }else{
                stack[top++]=stackChar[i];
            }
        }

//        for(int i=0;i<top;i++){
//            System.out.printf(String.valueOf(s.charAt(stack[i])));
//        }
//        System.out.println();
//        for(int i=0;i<top;i++){
//            System.out.printf(String.valueOf(locked.charAt(stack[i])));
//        }
//        System.out.println();
//        for(int i=top-1;i>=1;i-=2){
//            if((s.charAt(stack[i])=='('&&s.charAt(stack[i-1])=='('&&locked.charAt(stack[i])=='0')
//                    ||(s.charAt(stack[i])==')'&&s.charAt(stack[i-1])==')'&&locked.charAt(stack[i-1])=='0')
//                    ||(s.charAt(stack[i])=='('&&s.charAt(stack[i-1])==')'&&locked.charAt(stack[i])=='0'&&locked.charAt(stack[i-1])=='0')){
//                top-=2;
//            }else{
//                return false;
//            }
//        }

        return top<=0;
    }

    public static boolean canBeValidWrong2(String s, String locked) {
        int n=s.length();
        int closeBracketCount=0;
        int openBracketCount=0;
        int replaceBracket=0;

        for(int i=0;i<n;i++){
            if(s.charAt(i)=='('){
                openBracketCount++;
            }
            if(s.charAt(n-i-1)==')'){
                closeBracketCount++;
            }
            if(locked.charAt(i)=='0'){
                replaceBracket++;
            }
        }
        int[] stack=new int[n];
        int top=0;

        for(int i=0;i<n;i++){
            if(top!=0){
                if(((s.charAt(i)==')'&&s.charAt(stack[top-1])=='(')
                        || s.charAt(i)=='('&&s.charAt(stack[top-1])=='('&&locked.charAt(i)=='0')
                        ||(s.charAt(i)==')'&&s.charAt(stack[top-1])==')'&&locked.charAt(stack[top-1])=='0')
                        ||(s.charAt(i)=='('&&s.charAt(stack[top-1])==')'&&locked.charAt(i)=='0'&&locked.charAt(stack[top-1])=='0')){
                    top--;
                }else{
                    stack[top++]=i;
                }
            }else{
                stack[top++]=i;
            }
        }
        boolean first=(top!=0&&s.charAt(stack[0])==')')||Math.abs(openBracketCount-closeBracketCount)>replaceBracket;

        top=0;
        stack[0]=0;
        for(int i=n-1;i>=0;i--){
            if(top!=0){
                if((s.charAt(i)=='('&&s.charAt(stack[top-1])==')'
                        || s.charAt(i   )=='('&&s.charAt(stack[top-1])=='('&&locked.charAt(stack[top-1])=='0')
                        ||(s.charAt(i)==')'&&s.charAt(stack[top-1])==')'&&locked.charAt(i)=='0')
                        ||(s.charAt(i)==')'&&s.charAt(stack[top-1])=='('&&locked.charAt(i)=='0'&&locked.charAt(stack[top-1])=='0')){
                    top--;
                }else{
                    stack[top++]=i;
                }
            }else{
                stack[top++]=i;
            }
        }
        boolean second=(top!=0&&s.charAt(stack[0])=='(')||Math.abs(openBracketCount-closeBracketCount)>replaceBracket;

        if(first&&second){
            return false;
        }

        return true;
    }

    public static boolean canBeValidWrong3(String s, String locked) {
        int n=s.length();
        int closeBracketCount=0;
        int openBracketCount=0;
        int replaceBracket=0;
        char newS[]=new char[n];

        for(int i=0;i<n;i++){
            if(s.charAt(i)=='('){
                openBracketCount++;
            }
            if(s.charAt(n-i-1)==')'){
                closeBracketCount++;
            }
            if(locked.charAt(i)=='0'){
                replaceBracket++;
            }
        }
        for(int i=0;i<n;i++){
            if(replaceBracket>0&&closeBracketCount-openBracketCount<=replaceBracket&&locked.charAt(i)=='0'){
                newS[i]='(';
                replaceBracket--;
                closeBracketCount--;
                openBracketCount++;
            }else if(replaceBracket>0&&openBracketCount-closeBracketCount<=replaceBracket&&locked.charAt(i)=='0'){
                newS[i]=')';
                replaceBracket--;
                closeBracketCount++;
                openBracketCount--;
            }else{
                newS[i]=s.charAt(i);
            }
        }
        int[] stack=new int[n];
        int top=0;
        for(int i=0;i<n;i++){
            if(top!=0){
                if(s.charAt(i)==')'&&stack[top-1]=='('){
                    top--;
                }else{
                    stack[top++]=s.charAt(i);
                }
            }else{
                stack[top++]=s.charAt(i);
            }
        }

        return top<=0;
    }

    public static boolean canBeValidTrying(String s, String locked) {
        int n=s.length();
//        char newS[]=new char[n];
//
//        for(int i=0;i<n;i++){
//            if(s.charAt(i)=='('){
//                openBracketCount++;
//            }
//            if(s.charAt(n-i-1)==')'){
//                closeBracketCount++;
//            }
//            if(locked.charAt(i)=='0'){
//                replaceBracket++;
//            }
//        }
        int openBracketCount=0;
        int closeBracketCount=0;
        int sumOpenBracketCount=0;
        int sumCloseBracketCount=0;
        int bkSumOpen=0;
        int bkSumClose=0;
        int replaceBracket=0;
        boolean isInvalid=false;

        for(int i=0;i<n;i++){
            if(s.charAt(i)=='('){
                sumOpenBracketCount++;
            }
            if(s.charAt(n-i-1)==')'){
                sumCloseBracketCount++;
            }
            if(locked.charAt(i)=='0'){
                replaceBracket++;
            }
        }
        bkSumOpen=sumOpenBracketCount;
        bkSumClose=sumCloseBracketCount;

        for(int i=0;i<n;i++){
            if(openBracketCount>closeBracketCount){
                if(s.charAt(i)==')'){
                    closeBracketCount++;
                }else if(locked.charAt(i)=='0'&&sumOpenBracketCount!=sumCloseBracketCount){
                    closeBracketCount++;
                    sumOpenBracketCount--;
                    sumCloseBracketCount++;
                }
            }else if(openBracketCount<closeBracketCount){
                if(s.charAt(i)=='('){
                    openBracketCount++;
                }else if(locked.charAt(i)=='0'&&sumOpenBracketCount!=sumCloseBracketCount){
                    openBracketCount++;
                    sumOpenBracketCount++;
                    sumCloseBracketCount--;
                }else if(s.charAt(i)==')'){
                    openBracketCount++;
                }else{
                    isInvalid=true;
                    break;
                }
            }else if(s.charAt(i)=='('){
                openBracketCount++;
            }else if(s.charAt(i)==')'){
                closeBracketCount++;
                if(closeBracketCount>openBracketCount){
                    isInvalid=true;
                    break;
                }
            }
        }
        if(!isInvalid&&openBracketCount==closeBracketCount){
            return true;
        }
        openBracketCount=0;
        closeBracketCount=0;
        isInvalid=false;
        sumOpenBracketCount=bkSumOpen;
        sumCloseBracketCount=bkSumClose;

        for(int i=n-1;i>=0;i--){
            if(openBracketCount>closeBracketCount){
                if(s.charAt(i)==')'){
                    closeBracketCount++;
                }else if(locked.charAt(i)=='0'&&sumOpenBracketCount!=sumCloseBracketCount){
                    closeBracketCount++;
                    sumCloseBracketCount++;
                    sumOpenBracketCount--;
                }else{
                    isInvalid=true;
                    break;
                }
            }else if(openBracketCount<closeBracketCount&&(s.charAt(i)=='('||locked.charAt(i)=='0')){
                if(s.charAt(i)=='('){
                    openBracketCount++;
                }else if(locked.charAt(i)=='0'&&sumOpenBracketCount!=sumCloseBracketCount){
                    openBracketCount++;;
                    sumOpenBracketCount++;
                    sumCloseBracketCount--;
                }else if(s.charAt(i)==')'){
                    closeBracketCount++;
                }
            }else if(s.charAt(i)=='('){
                openBracketCount++;
                if(openBracketCount>closeBracketCount){
                    isInvalid=true;
                    break;
                }
            }else if(s.charAt(i)==')'){
                closeBracketCount++;
            }
        }
        if(!isInvalid&&closeBracketCount==openBracketCount){
            return true;
        }

        return false;
    }

    public static boolean canBeValid(String s, String locked) {
        if(s.length()%2==1){
            return false;
        }
        int closed=0;
        int open=0;
        int replace=0;
        int n =s.length();

        for(int i=0;i<n;i++){
            if(locked.charAt(i)=='0'){
                replace++;
            }else if(s.charAt(i)=='('){
                open++;
            }else{
                closed++;
            }
            if(replace+open-closed<0){
                return false;
            }
        }
        open=0;
        closed=0;
        replace=0;
        for(int i=n-1;i>=0;i--){
            if(locked.charAt(i)=='0'){
                replace++;
            }else if(s.charAt(i)=='('){
                open++;
            }else{
                closed++;
            }
            if(replace-open+closed<0){
                return false;
            }
        }
        return true;
    }

    public static boolean canBeValidOptimize(String s, String locked) {
        if(s.length()%2==1){
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        //case 1:
        //))())) --> ()()()
//        String s="))()))";
//        String locked="010100";
//        String s=")";
//        String locked="0";
//        String s=")(";
//        String locked="00";
        //Case 2:
        //Nếu làm 2 chiều thì vẫn lỗi thôi
        //left :
        //)()))()(()(((())(()()))))((((()())(())
        //==> ) xuất hiện đều string --> false
        //11101100010001001011000000110010100101
        //
        //right :
        //())()))()(()(((())(()()))))((((()())((
        //==> ( xuất hiện cuối tring --> false
        //10111011000100010010110000001100101001
//        String s="())()))()(()(((())(()()))))((((()())(())";
//        String locked="1011101100010001001011000000110010100101";
//        String s="()))";
//        String locked="0011";
        //Case 3:
        //+ Case có ) ngay ở đầu --> Lỗi luôn
//        String s="))))(())((()))))((()((((((())())((()))((((())()()))(()";
//        String locked="101100101111110000000101000101001010110001110000000101";
        //Case 4:
        //+ Case nhỏ đăc biệt --> Trả kết quả true
//        String s="()))";
//        String locked="1011";
        //Case 5:
//        String s=")(";
//        String locked="11";
//        System.out.println(canBeValidWrong(s, locked));
        //Case 6:
//        String s="())()))()(()(((())(()()))))((((()())(())";
//        String locked="1011101100010001001011000000110010100101";
        //Case 6.1:
        //+ sub case của case trên
        String s="((()())(())";
        String locked="10010100101";
        System.out.println(canBeValid(s, locked));
        //Bài này tư duy như sau:
        //Cách tư duy 1 : ===> SAI
        //1, Nếu cứ tư duy theo hướng dùng stack
        //Nếu chọn biến đổi dựa trên số lượng '(' / số lượng ')'
        //1.1 Biến đổi xong --> Cần - đi phần biến đổi
        //- Biến đổi thì trừ ở đâu?
        //VD: ((((i)()()
        //+ Nếu ta ở vị trí (i) (Đếm số lượng ký tự '(' từ đầu đến (i)) : trừ đi k '(' đằng trước --> Đến vị trí (j>i) ta có thể việc trừ đó vẫn đúng với (j)
        //+ Nếu ta ở vị trí (i) (Đếm số lượng ký tự ')' từ cuối đến (i) : Trừ đi k ')' đằng trước --> Đến vị trí (j>i) ---> Việc trừ đó không còn đúng với (j) vì:
        //Số ký tự trừ trước ')' --> Chưa chắc sau (i)
        //===> Cách này sai.
        //
        //2, Tư duy theo phương pháp loại trừ:
        //Số lượng ký tự '(' , ')'
        //2.1, 1 chuỗi thỏa mãn khi :
        //+ số lượng '(' == số lượng ')'
        //+ Bỏ đi các trường hợp đặc biệt
        //VD: ))(( --> Các trường hợp )))()(((
        //
        //010000
        //))()()
        //
        //2.2,
        //
        //())()))()(()(((())(()()))))((((()())(())
        //1011101100010001001011000000110010100101
        //
        //Với solution chạy từ left --> right (Stack + locked=0)
        //left :
        //)()))()(()(((())(()()))))((((()())(())
        //11101100010001001011000000110010100101
        //
        //Với solution chạy từ right --> left (Stack + locked=0)
        //right :
        //())()))()(()(((())(()()))))((((()())((
        //10111011000100010010110000001100101001
        //
        //Tại sao lại sai?
        //2.2.1,
        //()))
        //1011
        //Left to right : ))
        //Right to left : ))
        //==> Với tư duy như thế này thì result vẫn như thế.
        //
        //2.2.2,
        //()()))
        //011011
        //+ open = 2
        //+ close = 4
        //
        //--> ()(())
        //
        //()()))
        //011011
        //()(())
        //
        //2.2.3, Nếu sử dụng tư duy đếm số lượng open close
        //+ Các ( thì luôn đóng vai trò tương đương nhau
        //( đứng ở (i) <=> ( đứng ở vị trí (j)
        //==> Tư duy này bị sai.
        //VD ()))())
        //--> Miễn có đủ số ) thì vai trò của các ( là như nhau.
        //--> Sai
        //Thử các cases đặc biệt:
        //
        //VD:
        //()))((()
        //close = open =4
        //Nếu ))) được chuyển bớt sang bên phải --> S sẽ valid.
        //
        //
        //original : ())()))()(()(((())(()()))))((((()())(())
        //tranfer  : (()()()(((()(((((((()((((((((((()()(((()
        //1011101100010001001011000000110010100101
        //
        //close (20) = open (20) ---> Vẫn invalid
        ////Số lượng close và open có thể như nhau --> Nhưng chuỗi có thể valid / invalid.
        //----> Tư duy theo số lượng sẽ bị sai.
        //
        //3,
        //+ Tư duy theo dạng chỉ kiểm tra valid
        //+ Tư duy theo dạng transfer(s) --> s1
        //--> Áp dụng tư duy stack để kiểm tra valid(s1)
        //VD:
        //()))((()
        //10010011
        //==> Phải tư duy chuyển về được
        //((()))()
        //
        //+ Tư duy đếm số lượng (open/ close)
        //--> Chọn transfer
        //VD:
        //()))((()
        //10010011
        //
        //+ Chiều xuôi
        //s     : ()))
        //locked: 1011
        //close : 0
        //open  : 1
        //s1    : ()))
        //--> tư duy này sai
        //
        //+ Chiều ngược
        //s     : ()))
        //locked: 1011
        //close : 0
        //open  : 1
        //s1    :
        //
        //
        //#Case 1:
        //sum of open=2
        //sum of close =2
        //(())
        //0101
        //Nếu đi từ cuối sẽ chọn như sau:
        //((() --> Sai
        //
        //#Case 2:
        //close=2
        //open =1;
        //((()
        //1101
        //
        //#Case 3:
        //((()())(())
        //10010100101
        //sum of open :
        //sum of close :
        //
        //............... Với số lượng cases lỗi nhiều ==> Chuyển đổi sang cách làm khác.
        //
        //Cách 2:
        //2.1,
        //Ta có 1 số caes đặc biệt như sau:
        //
        //#Case 1
        //()())) --> ()(())
        //011011
        //---> Case này có thể tư duy dạng greedy đếm số lượng (, ) từ (left to right) + (right to left)
        //===> Như case này (right to left) mới return true
        //
        //#Case 2
        //+ Case với số lượng open = close
        //sum of open=2
        //sum of close =2
        //(())
        //0101
        //---> Case này đặc biệt với tư duy chọn trường hợp (greedy)
        //+ Nếu traverse (right --> left) : ) thứ 2 sẽ bị ==> ( do ta muốn so sánh số lượng ( và số lương ) đã có.
//        System.out.println(canBeValid(new StringBuilder(s).reverse().toString(), new StringBuilder(locked).reverse().toString()));
        //+ Ta đã cố xử lý case bên trên bằng cách so sánh (sum of closed brackets) và (sum of open brackets)
        //---> Có vẻ vẫn sai.
        //
        //Case 3:
//        String s=")(";
//        String locked="11";
        //+ Case này ) / ( sẽ đứng hàng cuối + không thể thay đổi
        //+ return false
        //
        //2.2, Tư duy như sau:
        //2.2.1 Áp dụng tư duy khá giống bên trên, đếm số lượng (closed) và (open) lần lượt + condition để xem S có valid hay không!
        //---> Chỉ khác 1 chỗ
        //+ Đếm replace lần lượt cùng luôn
        //+ SỐ lượng ( và ) sẽ không bao gồm các giá trị S[i] mà locked[i]='0'
        //Tại sao lại như thế?
        //VD:
        //#Case 1
        //()))
        //1011
        //--> Nếu đếm greedy bình thường sẽ sai
        //--> Đếm bình thường ta sẽ tìm cách chọn giá trị cho index (i) khi locked[i]='0'
        //#Case 2
        //(())
        //1010
        //+ Xét từ (left--> right) đếm lần lượt sẽ sai
        //#Case 3
        //()))((()
        //10010011
        //==> Việc chọn giá trị ntn sẽ bị SAI do:
        //--> Với case này thì việc chọn giá trị cho các index có locked[i]=='0' sẽ bị SAI
        //VD :
        //left to Right
        //() (Lúc này ta sẽ chọn tiếp theo ký tự nào)
        //locked[2]=='0' : Ta có thể chọn value cho ký tự tại (i==2)
        //+ Nếu ta giữ nguyên ) --> Sai : RESULT : ())
        //---> Không thể căn cứ vào (open và closed) để đánh giá chọn ( / ).
        //CHI TIẾT HƠN:
        //VD: (Vẫn ví dụ trên)
        // ()))((()
        // 10010011
        // Giá trị ban đầu
        // ()')')((() <=> ()'(')((()
        //+ Vì locked[i==2]=='0' : Giá trị ban đầu là vô nghĩa có thể là '(' / ')' --> Không thể căn cứ vào đó mà chọn value
        //====> TA SAI TỪ BƯỚC NÀY "KHÔNG CHỌN GIÁ TRỊ TRỰC TIẾP ĐƯỢC CHO các locked[i]=='0' .
        //====> Tính count tính những giá trị (open/ closed) + locked[i]!='0'.
        //#Case 4
        // ())())
        //110101
        //---> 1 case tương tự không thể áp dụng tư duy SAI đi cả (left to right) / (right to left)
        //** CONCLUSION:
        //- Với nhưng bài transfer kiểu này, những giá value tự do lựa chọn --> Không thể tính vào vì:
        //+ Chọn được nó phụ thuộc cả vào các phần tử phía sau nữa.
        //VD:
        // (()())
        // 111011
        //--> Ở (i==3) : Ta không thể chọn dựa trên các ký tự đằng trước
        //VD
        // (()(
        // 1110
        // --> Tất nhiên lúc này (i==3) ta sẽ chọn ')"
        // ---> Nhưng nếu thêm )) --> Đã đủ closed --> Lúc đó ta lại chọn  "("
        //- Ta sẽ xét tổng thể và ưu tiên:
        //+ left to right : khi closed > open + replace : Chắc chắn sai
        //VD:
        // ())))
        // 11011
        //+ right to left : khi open > closed + replace : Chắc chắn sai
        //VD:
        // (((()
        // 11011
        //- Cần phải duyệt 2 đầu
        //s="())(()(()(())()())(())((())(()())((())))))(((((((())(()))))("
        //l="100011110110011011010111100111011101111110000101001101001111"
        //+ --> Sé có các case nếu xét (left to right) số lượng '(' > ')' rất nhiều
        //==> Ở đây vẫn return true
        //+ Nếu ta thêm trường hợp xét ngược (right to left) ---> Lúc này đối xứng nên sẽ xử lý được cases đó.
        //4, Suy nghĩ sai lầm:
        //4.1, Tranfer tự do để được valid { () } gần nhất --> sau đó là apply tư duy stack
        //+ Duyệt 2 đầu vẫn sai.
        // (())))
        // 100001
        // --> Với case này sai
        //+ Left to right : ()))) return false (Vì đứng ở (i==2) không biết ký tự (i==3) đang điền là gì)
        //+ Right to left : ('('()() return false (Vì đứng ở (i==1) không biết ký tự (i==0) đang điền là gì)
        //4.2, Vừa duyệt vừa transfer vẫn sai
        //4.3, Đếm số lượng '(' và ')' sau đó đánh giá trừ dần để chọn
        //---> Cái này sai tư duy vì "Ta không biết trừ ở đâu"
        //==> Tư duy kiểu này luôn sai
        //4.4, Tư duy kiểu prefix[i], tức là (i) :
        // + closed[i] là số ')' sau (i)
        // + open [i] là số '(' trước (i)
        //--> Dựa vào đó trừ dần đánh giá (trừ dần '(' và ')')
        //--> Nhưng với (j > i) --> ta không thể xác định được từ (i) --> (j) vì ta không biết trừ ntn
        //+ Có thể (i) sẽ trừ các giá trị giữa (i và j) hoặc là sau (j)
        //===> Không thể dùng số lượng ntn được.
        //4.5, Tư duy cộng dần (open + closed) ===> Như đã giải thích thiếu bỏ qua (i) với locked[i]=='0'
        //
        //5, Tối ưu:
        //
    }
}
