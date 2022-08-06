/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcode_medium_dynamic;

/**
 *
 * @author chinhvu
 */
public class E31_UniqueSubstringsInWraparoundString {

    public static boolean solution(
            int min,
            int max,
            int remainingSum){
        int rangeChoose=remainingSum-max;
        int chosenNumber=0;
        boolean rs = false;
        boolean predicate=false;

        for(int i=min;i<rangeChoose;i++){
            if(i==min){
                predicate=(i+min+1+max)<remainingSum;
            }else{
                predicate=i+min+max<remainingSum;
            }
            if(predicate){
                chosenNumber=i;
                break;
            }
        }
        if(!predicate){
            return false;
        }
        if(chosenNumber==min){
            rs=solution(min+1,max, remainingSum-chosenNumber);
        }else if(chosenNumber==max){
            rs=solution(min,max-1, remainingSum-chosenNumber);
        }else {
            rs=solution(min,max, remainingSum-chosenNumber);
        }
        if(rs){
            return false;
        }
        return true;
    }

    public static int getNumberSubString(int[] characters){
        int n=characters.length;
        int rs=0;

        //Tính cả 2 đầu
        int low=0;
        int high=n-1;
        int len=0;
        if(characters[0]!=0){
            len++;
        }
        for(int i=1;i<n;i++){
            if(characters[i]==characters[i-1]&&characters[i-1]!=0){
                len++;
            }else{
                low=i;
                break;
            }
        }
        if(len!=n&&characters[n-1]!=0){
            len++;
        }
        for(int i=n-2;i>=low+len;i--){
            if(characters[i]==characters[i+1]&&characters[i+1]!=0){
                len++;
            }else{
                high=i;
                break;
            }
        }
//        if(characters[low]!=0){
//            len++;
//        }
//        boolean isInterruptedRight=false;
//        boolean isInterruptedLeft=false;
//        while (characters[low]!=0||characters[high]!=0&&low<high){
//            if(characters[low]!=0
//                    &&!isInterruptedLeft
//                    &&(characters[low]==characters[low+1])){
//                len++;
//                low++;
//            }else{
//                isInterruptedLeft=true;
//            }
//            if(characters[high]!=0
//                    &&!isInterruptedRight
//                    &&characters[high]==characters[high-1]){
//                len++;
//                high--;
//            }else{
//                isInterruptedRight=true;
//            }
//            if(isInterruptedLeft&&isInterruptedRight){
//                break;
//            }
//        }
//        if(characters[low]!=0){
//            low++;
//        }
        rs+=len*(len+1)/2;
        int indexbefore=low;
        //Chú ý kỹ năng cơ bản, với nhưng trường hợp như thế này
        //1,Không cần currentIndex vì có (i) rồi
        //---> Chỉ cần có beforeIndex xem có reset hay không thôi

        //2, Với những bài (tăng/Tính) khi thay đổi  (0->5)/(5->0): Mình chỉ được tính 1 chiều thôi
        //Và phải xác định khi nào mới continue
        //(i)==0&& (i-1)!=0 OR
        //(i)!=0&&(i-1)==0
        for(int i=low+1;i<=high;i++){
            if(characters[i]!=0&&characters[i-1]==0){
                indexbefore=i;
                continue;
            }
            if(characters[i]!=characters[i-1]&&characters[i-1]!=0){
                int length=i-indexbefore;
                rs+=length*(length+1)/2;
                indexbefore=i;
            }
        }
        return rs;
    }

//    public static int getNumberSubStr(int[] characters){
//        int n=characters.length;
//        int rs=0;
//
//        //Tính cả 2 đầu
//        int low=0;
//        int high=n-1;
//        int len=0;
//
//        for(int i=0;i<n;i++){
//            if(characters[i]!=0){
//                len++;
//            }else{
//                low=i;
//                break;
//            }
//        }
//        for(int i=n-1;i>=0;i--){
//            if(characters[i]!=0){
//                len++;
//            }else{
//                high=i;
//                break;
//            }
//        }
//        rs+=len*(len+1)/2;
//        int indexbefore=low;
//        //Chú ý kỹ năng cơ bản, với nhưng trường hợp như thế này
//        //1,Không cần currentIndex vì có (i) rồi
//        //---> Chỉ cần có beforeIndex xem có reset hay không thôi
//
//        //2, Với những bài (tăng/Tính) khi thay đổi  (0->5)/(5->0): Mình chỉ được tính 1 chiều thôi
//        //Và phải xác định khi nào mới continue
//        //(i)==0&& (i-1)!=0 OR
//        //(i)!=0&&(i-1)==0
////        for(int i=low+1;i<=high;i++){
////            if(characters[i]!=0&&characters[i-1]==0){
////                indexbefore=i;
////                continue;
////            }
////            if(characters[i]!=characters[i-1]&&characters[i-1]!=0){
////                int length=i-indexbefore;
////                rs+=length*(length+1)/2;
////                indexbefore=i;
////            }
////        }
//        int temp=0;
//        for(int i=low+1;i<=high;i++){
//            if(characters[i]==0){
//                continue;
//            }
//            if(characters[i]!=0&&characters[i-1]==0){
//                rs+=temp*(temp+1)/2;
//            }else{
//                temp++;
//            }
//        }
//        return rs;
//    }

//    public static int findSubstringInWraproundString(String p) {
//        String s="zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd";
//        int m=p.length();
//        int characters[]=new int [26];
//        int init=1;
//        characters[p.charAt(0)-'a']=1;
////        int min=Integer.MAX_VALUE;
////        int max=Integer.MIN_VALUE;
//
//        for(int i=1;i<m;i++){
//            char currentC=p.charAt(i);
//            char beforeC=p.charAt(i-1);
//            if(currentC==beforeC+1||currentC=='a'&&beforeC=='z'){
//                characters[currentC-'a']=characters[beforeC-'a'];
//            }else if(characters[currentC-'a']==0){
//                characters[currentC-'a']=++init;
//            }
////            min=Math.min(characters[currentC-'a'], min);
////            max=Math.max(characters[currentC-'a'],max);
//        }
//        System.out.println(characters);
////        int dp[][]=new int[n+1][m+1];
////
////        for(int i=0;i<n;i++){
////            for(int j=0;j<m;j++){
////                if(s.charAt(i-1)==p.charAt(j-1)){
////
////                }
////            }
////        }
//        return getNumberSubString(characters);
//
//    }
    
    public static int findSubstringInWraproundString2(String p) {
        String s="zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd";
        int m=p.length();
        int characters[]=new int [26];
        int init=1;
        characters[p.charAt(0)-'a']=1;
//        int min=Integer.MAX_VALUE;
//        int max=Integer.MIN_VALUE;

        for(int i=1;i<m;i++){
            char currentC=p.charAt(i);
            char beforeC=p.charAt(i-1);
            if(currentC==beforeC+1
                    ||currentC=='a'&&beforeC=='z'
                    ||currentC==beforeC-1){
                characters[currentC-'a']=characters[beforeC-'a'];
            }{
                characters[currentC-'a']=++init;
            }
//            min=Math.min(characters[currentC-'a'], min);
//            max=Math.max(characters[currentC-'a'],max);
        }
        System.out.println(characters);
//        int dp[][]=new int[n+1][m+1];
//
//        for(int i=0;i<n;i++){
//            for(int j=0;j<m;j++){
//                if(s.charAt(i-1)==p.charAt(j-1)){
//
//                }
//            }
//        }
        return getNumberSubString(characters);

    }

    public static int findSubstringInWraproundString(String p){
        int n=p.length();
//        int dp[]=new int[n];
        int character[]=new int[26];
        int rs=0;
//        dp[0]=1;
        character[p.charAt(0)-'a']=1;
        int init=1;

        for(int i=1;i<n;i++){
//            dp[i]=1;
            int beforeChar=p.charAt(i-1);
            int currentChar=p.charAt(i);

            if(currentChar==beforeChar+1
                    ||currentChar=='a'&&beforeChar=='z'){
//                dp[i]=dp[i-1]+1;
                init++;
            }else{
                init=1;
            }
            character[p.charAt(i)-'a']=Math.max(character[p.charAt(i)-'a'], init);
        }

        for(int i=0;i<26;i++){
            rs+=character[i];
        }
        return rs;
    }

    public static void main(String[] args) {
        int maxChoosableInteger=4;
        int desiredTotal= 6;
        //true
//        int maxChoosableInteger=10;
//        int desiredTotal= 11;
        //false
//        System.out.println(canIWin(maxChoosableInteger, desiredTotal));
//        System.out.println(2&3);
//        String s="zab";
//        String s="cac";
//        String s="cacbce";
//        String s="abcde";
//        String s="zaba";

        //Bài này là 1 bài khá khó
        //Ta có các điều cần chú ý như sau:
        //1, Ở đây bài toàn là tìm số chuỗi thỏa mãn điều kiện tăng dần (a-->z) + theo cycle (z --> a)
        //****************
        //Khời nguồn của suy nghĩ sai:
        //Ta đã suy nghĩ rằng việc tính ta sẽ dựa trên chỉ 1 chuỗi (a,b...,z)
        //1.1, Ý tưởng ở đây là ta sẽ chỉ quan tâm đến chuỗi lớn nhất tuân theo quy luật
        //VD: abcd (số lượng chuỗi) --> Bao gồm số lượng chuỗi trong (abc)
        //1.2, Ta sẽ chỉ đánh dấu những chuỗi tăng dần
        //VD: abcd --> ta sẽ khởi tạo 1 mảng dạng characters[26]
        //character[c-'a']=init++
        //---> Tại sao character[c-'a']= (1) mà lại (init++)
        //***************** Phần tư duy này sai
        //===> Đây là điều thừa thãi vì --> Cái ta quan tẫm là (chuỗi liên tiếp có độ dài lớn nhất)
        //---> Các chuỗi liên tiếp riêng rẽ (Không nhất thiết cần đánh dấu giá trị khác nhau) 
        //--> Vì cái ta quan tấm (số lượng chuỗi)/(Liệt kê tất cả chuỗi: Thì vẫn thế)
        //*****************
        //---> Nếu làm như thế này ta vẫn phải gán character[c-'a']=init++
        //VD: (za)d(abc)
        //--> Nếu theo tư tưởng cũ za=1, abc=2
        //--> Ta sẽ tính số lưỡng chuỗi ứng với từng (za, abc)
        //Nếu coi như =1 hết:
        //(z:1,a:1,b:1,c:1, d:1)
        //--> Kết quả sai khi ta sẽ tính cả chuỗi (zabc) vào --> Mặc dù nó bị phân cách bởi chữ 'd'
        //1.3, Vởi tư duy này ta sẽ chia ra:
        //- Xử lý trường hợp 2 đâu trước --> Không cần thiết
            //Viết ở (đầu/cuối) thì vẫn quy luật thế: rs+=length*(length+1)/2
            //Số chuỗi của chuỗi có độ dài (length): (length)*(length+1)/2
            //Nên ta không cần xét riêng --> (Chuỗi ở cuỗi cũng không thế ghép với chuỗi ở đầu được)
        //- Tính đến trường hợp ở giữa
        
        //Chú ý kỹ năng cơ bản, với nhưng trường hợp như thế này
//        //1,Không cần currentIndex vì có (i) rồi
//        //---> Chỉ cần có beforeIndex xem có reset hay không thôi
//
//        //2, Với những bài (tăng/Tính) khi thay đổi  (0->5)/(5->0): Mình chỉ được tính 1 chiều thôi
//        //Và phải xác định khi nào mới continue
//        //(i)==0&& (i-1)!=0 OR
//        //(i)!=0&&(i-1)==0
        //VD: 0, 5, 0
        //--> Ta không if([i] compare [i-1])
        //Ta: VD chỉ xét 1 trường hợp
        //(i)==0&& (i-1)!=0 OR
        //(i)!=0&&(i-1)==0
//        for(int i=low+1;i<=high;i++){
//            if(characters[i]!=0&&characters[i-1]==0){
//                indexbefore=i;
//                continue;
//            }
//            if(characters[i]!=characters[i-1]&&characters[i-1]!=0){
//                int length=i-indexbefore;
//                rs+=length*(length+1)/2;
//                indexbefore=i;
//            }
//        }

        //Kết quả:
        //rs+=character[i];ALL
        //---------------------------Đến đây bài toán tưởng như sẽ được giải quyết nhưng không---------------------------
        //Vì bài toán của ta là nhiều dãy (abcd abcd...) --> Lặp lại nhau
        //Tức là: ta sẽ chỉ quan tâm đến chuỗi P --> Lắp vào chuỗi S
        //Chuỗi S là chuỗi vô tận lặp lại
        //Chuỗi P mà nhỏ, không lặp lại thì --> Kết quả (abcd) (1 lần vẫn đúng)
        //Chuỗi P lặp lại --> Đó là cái mà ta đang quan tâm
        //KẾT QUẢ BÀI : --> Hoàn toàn phụ thuộc vào chuỗi P --> for(P)
        //VD:TOÁNlại
        //Có thể có những trường hợp: (cd ab) --> Chuỗi này sẽ thiếu nếu không xét lặp 
        //***********Bài toán trở thành
        //Tín tổng chuỗi của chuỗi: ABCABCAB
        //1, Do quy thành: Tổng chuỗi bằng tổng các chuỗi đơn lẻ (Liên tiếp nhau)
        //VD: ABCABCAB|C|CDE
        //= (ABCABCAB) + (CDE)
        //1.1, Các chuỗi bao nhau --> Chỉ lấy chuỗi có đọ dài lớn nhất
        //1,2, Câu hỏi đặt ra là loại trường hợp:
        //VD: (abcd|e|za) như thế nào
        //--> Nếu dùng character[] đánh dấu loại thì phải dùng (init++)
        //1.3, Có 1 số cases đặc biệt cần xét:
        //abc abc
        //abc cd
        //c abc abc
        //--> Ta đã thử nhiều cách nhóm nhưng hầu hết gặp khó khăn
        //1.4, Ta quay lại tư duy cũ, chuỗi to chứa chuỗi con --> Ta cần loại đi các cases:
        //1.4.1, VD: VD: abcabcab|c|za
        //---> za phải có giá trị trong character (khác)
        //1.4.2, rs+=character[i]; --> Các chuỗi có thể lặp lại:
        //VD: abcabcab|c|za|abc
        //Các giá trị character['a'] ==> Phải được "Không cộng dồn" (Bao nhau) + "Lấy Max" (Vì abc lặp lại ở chuỗi riêng rẽ khác)
        //1.5, Quay lại với tư duy quy hoạch động như sau:
        //abc
        //Tổng số chuỗi = (Số chuỗi chỉ kết thúc bởi 'a') + (Số chuỗi chỉ kết thúc bởi 'b') + (Số chuỗi chỉ kết thúc bởi 'c')
        //Ta thấy rằng:
        //Số chuỗi chỉ kết thúc bởi 'b'=(Số chuỗi chỉ kết thúc bởi 'a': Do được nối với b) + (b riêng lẻ)
        //Số chuỗi chỉ kết thúc bởi 'a'=1
        //Số chuỗi chỉ kết thúc bởi 'b'=2
        //Số chuỗi chỉ kết thúc bởi 'c'=3 --> Quy lụât (i+1)
        //Tổng số chuỗi =6
        //---> Khi gặp trùng --> Max nhất
        //1.5.1, Loại:
        //VD:  abcabcab|c|zab
        //--> zab ta có thể bỏ ab ra khỏi (zab) --> Chỉ (+ z) 
        //- Chỉ xét đằng trước vì : chỉ có case đó mới thiếu
        //- Đã abcabc đẫ bao zab... --> Tính các (trường hợp đằng sau) là thừa
        String s="abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
        System.out.println(findSubstringInWraproundString(s));
    }
}
