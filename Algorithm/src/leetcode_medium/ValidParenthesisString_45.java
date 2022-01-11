package leetcode_medium;

import java.util.Stack;

public class ValidParenthesisString_45 {

    public static boolean checkValidString(String s) {
        Stack<Character> str=new Stack<>();
        Stack<Character> asterisks=new Stack<>();
        int nx=0;
        int nc=0;
        int totalC=0;
        //Số lượng ( có thể thay thế bằng * sau nó
        int countReduce=0;

        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                str.push('(');
                nx=0;
                nc++;
                totalC++;
            }
            if(s.charAt(i)=='*'){
                asterisks.push('*');
                nx++;
                if(totalC>0){
                    countReduce++;
                    totalC--;
                }
            }
            if(s.charAt(i)==')'){
                //Số lượng ký tự ( đang lớn hơn số lượng ký tự * ở thời điểm xuất hiện ký tự ( gần nhất
                //Do ta đang xét linear nên logic này --> Đúng
                if(str.size()!=0&&nc>=nx){
                    str.pop();
                    if(nc!=0){
                        nc--;
                    }
                }else if(asterisks.size()!=0){
                    asterisks.pop();
                    countReduce--;
                    if(nx!=0){
                        nx--;
                    }
                }else{
                    return false;
                }
            }
        }
        //Sẽ có case * thay thế ) --> Để bù vào (
        //--> Ta phải xác định riêng các cases đó ra
        if(str.size()==0||str.size()<=asterisks.size()&&(nx>=nc)){
            return true;
        }
        return false;
    }

//    public static boolean checkValidString1(String s) {
//        Stack<Character> str=new Stack<>();
//        Stack<Character> asterisks=new Stack<>();
//        int nx=0;
//        int nc=0;
//        int totalC=0;
//        //Số lượng ( có thể thay thế bằng * sau nó
//        int countReduce=0;
//
//        for(int i=0;i<s.length();i++){
//            if(s.charAt(i)=='('){
//                str.push('(');
//                nc++;
//                totalC++;
//            }
//            if(s.charAt(i)=='*'){
//                asterisks.push('*');
//                nx++;
//                if(totalC>0){
//                    countReduce++;
//                    totalC--;
//                }
//            }
//            if(s.charAt(i)==')'){
//                //Số lượng ký tự ( đang lớn hơn số lượng ký tự * ở thời điểm xuất hiện ký tự ( gần nhất
//                //Do ta đang xét linear nên logic này --> Đúng
//                if(str.size()!=0&&nc>=nx){
//                    str.pop();
//                    if(nc!=0){
//                        nc--;
//                    }
//                }else if(asterisks.size()!=0){
//                    asterisks.pop();
//                    if(nx!=0){
//                        nx--;
//                    }
//                }else{
//                    return false;
//                }
//            }
//        }
//        //Sẽ có case * thay thế ) --> Để bù vào (
//        //--> Ta phải xác định riêng các cases đó ra
//        if(str.size()==0||str.size()<=asterisks.size()&&(nx>=nc)){
//            return true;
//        }
//        return false;
//    }

    public static boolean checkValidString2(String s) {
        int n=s.length();
        //Init ở đây là 0
        int cmin=0;
        int cmax=0;

        for(int i=0;i<n;i++){
            if(s.charAt(i)=='('){
                cmin++;
                cmax++;
            }
            if(s.charAt(i)==')'){
                cmin--;
                cmax--;
            }
            if(s.charAt(i)=='*'){
                cmin--;
                cmax++;
            }
            if(cmax<0){
                return false;
            }
            cmin=Math.max(cmin, 0);
        }
        //Phần này phải để bên trong loop
//        cmin=Math.min(cmin, 0);
        return cmin==0;
    }

    public static void main(String[] args) {
//        String s="(*))";
//        String s="(*)";
//        String s="((((()(()()()*()(((((*)()*(**(()))))) (())()())(((())())())))))))(((((())*)))()))(()((*()*(*)))(*)()";
//        String s="(((((*(()((((*((**(((()()*)()()()*((((**)())*)*)))))))(())(()))())((*()()(((()((()*(())*(()**)()(())";
        //Tức là sẽ có case mà ( --> sẽ không tồn tại ký tự | */ ) | (bên phải của nó nữa)
//        String s="**(()";
//        String s="(((((*(()((((*((**(((()()*)()()()*((((**)())*)*)))))))(())(()))())((*()()(((()((()*(())*(()**)()(())";
        //Case đặc biệt
//        String s="(**)())";
//        String s="(*)(";
        //( * --> Phụ thuộc order (Do sẽ có trường hợp * trước không thể áp dụng cho '(' sau
//        String s="(*)";
        //Trường hợp này * tại vị trí (i=2) sẽ được thay thế bằng )
        //--> *()(((*))
//        String s="(*((*)";
        String s="((((()(()()()*()(((((*)()*(**(())))))(())()())(((())())())))))))(((((())*)))()))(()((*()*(*)))(*)()";
//        String s="(())(())(((()*()()()))()((()()(*()())))(((*)()";
        System.out.println(checkValidString(s));
//        System.out.println(checkValidString1(s));

        //https://leetcode.com/problems/valid-parenthesis-string/discuss/543521/Java-Count-Open-Parenthesis-O(n)-time-O(1)-space-Picture-Explain
        //Bài này tư duy khá khoai
        //1, Ta thấy rằng khi xét thêm * tức là ta sẽ phải xét đến order của * so với ( vì:
        //Ex: *** (( --> Các ký tự ** đằng sau (( dường như vô dụng không thể thay thế * <=> (/ ) để biểu thức trên hợp lệ được
        //1.1, Vì đang xét đến order nên ta có ý tưởng đến việc ưu tiên ) sẽ ghép với ( / *
        // Dựa trên:
        //+ nc: Số ký tự ( cho đến vị trí điền )
        //+ nx: Số ký tự * cho đến vị trí điền )
        //Quy luật: Cứ khi gặp ( --> nc++, nx=0
        //--> Vì các ký tự sau * sau ( đều vô nghĩa --> reset nx=0
        //+ Nếu nc>=nx --> Ta sẽ ưu tiên ghép ( : nc--
        // Cứ như thế đến cuối ta xét:
        //nx>=nc --> return true (Tức là còn đủ ký tự * để ghép với ( : * <=> )
        //1.2, Nếu làm như thế này sẽ bị dinh 1 cases đặc biệt:
        //String s="(*((*)";
        // --> () ((*) --> Chuỗi này hợp lệ nhưng nếu xét theo kiểu trên thì sẽ bị (SAI)
        //1.3, Thư đếm các chuỗi thay thế --> Không liên quan đến phép tính cuối này

        //2, Tư tuy mới:
        //2.1, Làm thế nào để không xảy ra case: )( --> Ta sẽ luôn điền ( trước
        //--> Ở đây ta sẽ lấy ( làm mốc để tính
        //2.2, Ở đây phường pháp này cần trả lời các câu hỏi:
        //Ký tự * * (() --> Phải trả lại false : Tức là ký tự * sau ( là vô nghĩa
        //2.3, Có 1 quy tắc cần nhớ trong những bài check validate của ()():
        //+ Nếu tính đên vị trí (i) nếu các ký tự ) > số ký tự ( --> Chuỗi đó sẽ luôn false
        //--> Ở đây ta sẽ xét theo tư duy cận dưới và cận trên (cmin, cmax)
        //+ cmin: Số ký tự '(' mà all(* <=> ')' ) : Trường hợp này là số ký tự '(' nhỏ nhất
        //+ cmax: Số ký tự '(' mà all(* <=> '(' ) : Trường hơp này là số ký tự '(' lớn nhất
        //2.4, Ở đây số ký tự ( sẽ giảm đi nếu được ghép cùng với ')'
        // --> Return valid khi số ký tự '(' ==0
        //2.5, Ý nghĩa cmin, cmax:
        //*, cmax trường hợp lớn nhất số '(' : Mục đích để kiểm tra cmax<0 --> Chắc chắn false không cần làm gì thêm
        //+ Vì số ký tự ')' > '(' + chuyển đổi (* <=> '(' )
        // --> Nếu bỏ cmax --> Sai vì sẽ có case
        //cmin <0, cmax <0 --> Tức là không thể cân bằng ')' ---> Số ký tự ')' quá lớn
        //cmax chỉ muốn xét rằng việc chuyển đổi (*) --> '(' liệu có thể cân bằng được với ')' hay không?
        //Tất nhiên còn phụ thuộc vào cmin như các case:
        //Ex: **(((*): Chuyển đổi cmax >0 (OK)
        // nhưng cmin thì không được: **( --> (Tạch ngay lúc này)
        //CÓ THỂ CODE TRÊN MÌNH THIẾU TRƯỜNG HỢP TẠCH NTN

        //*, cmin trường hợp nhỏ nhất với '(':
        //Nếu cmin <0 :VD: ()) : cmin =-1
        //--> Trường hợp trên sẽ bị loại
        //--> Ta sẽ chọn cách tốt hơn + ( miễn là cmax >0) --> Ta hoàn toàn có thể tăng cmin lên (k đơn vị)
        //--> Ở đây ta lấy cmin = Max(cmin, 0)
        //Question: Tại sao không lấy >0 mà lại lấy 0
        //---> cmin= 0 : là giới hợn các trường hợp có thể thỏa mãn nếu ta điền tiếp ký tự sau đó nữa
        //---> Và ta cũng mong muốn từ đó tìm ra chuỗi có cmin=0
        //3, Tư duy chia cận để xét tiếp trường hợp sau đó:
        //cmin, cmax ---> Duyệt để thụ gọn cận dần
        //--> Mong muốn (cmin==0)
        System.out.println(checkValidString2(s));
    }
}
