package interviews.bytedance;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class E20_DecodeString_undone {

    public static String decodeStringSlow(String s) {
        int n=s.length();
        StringBuilder rs=new StringBuilder();
        Stack<String> strStack=new Stack<>();
        StringBuilder currentValue=new StringBuilder();

        for(int i=0;i<n;i++){
            if(s.charAt(i)>='0'&&s.charAt(i)<='9'){
                if(!"".equals(currentValue.toString())){
                    strStack.add(currentValue.toString());
                    currentValue=new StringBuilder();
                }
                StringBuilder numRp=new StringBuilder();
                while (i<n&&s.charAt(i)>='0'&&s.charAt(i)<='9'){
                    numRp.append(s.charAt(i));
                    i++;
                }
                i--;
                strStack.add(numRp.toString());
//                System.out.println(numRp);
                continue;
            }
            if(s.charAt(i)!='['&&s.charAt(i)!=']'){
                currentValue.append(s.charAt(i));
            }else if(s.charAt(i)==']'){
                if(!strStack.isEmpty()){
                    StringBuilder newValue= new StringBuilder(currentValue.toString());
                    while (!strStack.peek().equals("[")){
                        newValue.insert(0, strStack.pop());
                    }
                    strStack.pop();
                    StringBuilder currentRp=new StringBuilder();
                    int repeatNum=Integer.parseInt(strStack.pop());
                    for(int j=0;j<repeatNum;j++) currentRp.append(newValue);
                    newValue = new StringBuilder(currentRp.toString());
                    strStack.add(newValue.toString());
                }
//                System.out.println(topVal);
                currentValue=new StringBuilder();
            }else if(s.charAt(i)=='['){
                strStack.add("[");
            }else if(!"".equals(currentValue.toString())){
                strStack.add(currentValue.toString());
//                System.out.println(currentValue);
                currentValue=new StringBuilder();
                strStack.add("[");
            }
        }
//        if(!"".equals(currentValue.toString())){
//            strStack.add(currentValue.toString());
//        }
        String currentStr="";
        while (!strStack.isEmpty()){
            String prevValue=strStack.pop();
            if(!"".equals(prevValue)&&isNumeric(prevValue)){
                StringBuilder currentRs=new StringBuilder();
                int numRepeat=Integer.parseInt(prevValue);
                for(int j=0;j<numRepeat;j++) currentRs.append(currentStr);
                currentStr=currentRs.toString();
            }else{
                currentStr=prevValue+currentStr;
            }
        }
        return currentStr + currentValue;
    }

    public static boolean isNumeric(String str)
    {
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    public static String decodeStringRefactor(String s) {
        Stack<String> stackOps=new Stack<>();
        Stack<Integer> stackNums=new Stack<>();
        int n=s.length();
        StringBuilder res=new StringBuilder();
        int index=0;

        while (index<n){
            if(Character.isDigit(s.charAt(index))){
                int count=0;
                while (Character.isDigit(s.charAt(index))){
                    count=count*10+(s.charAt(index)-'0');
                    index++;
                }
                stackNums.add(count);
            }else if(s.charAt(index)=='['){
                stackOps.add(res.toString());
                res=new StringBuilder();
                index++;
            }else if(s.charAt(index)==']'){
                StringBuilder temp=new StringBuilder(stackOps.pop());
                Integer repeatNumber=stackNums.pop();

                while (repeatNumber>0){
                    temp.append(res);
                    repeatNumber--;
                }
                res=temp;
                index++;
            }else{
                res.append(s.charAt(index++));
            }
        }
        return res.toString();
    }

    public static String decodeStringRecursion(String s) {
        Deque<Character> queue=new LinkedList<>();
        int n=s.length();

        for(int i=0;i<n;i++){
            queue.add(s.charAt(i));
        }
        return helper(queue);
    }

    public static String helper(Deque<Character> queue){
        StringBuilder sb=new StringBuilder();
        int num=0;

        while (!queue.isEmpty()){
            char c=queue.pop();
            if(Character.isDigit(c)){
                num=num*10+(c-'0');
            }else if(c=='['){
                String subStr=helper(queue);
                while (num>0){
                    sb.append(subStr);
                    num--;
                }
            }else if(c==']'){
                break;
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }
    public static void main(String[] args) {
//        String s = "3[a]2[bc]";
//        String s = "3[a2[c]]";
//        String s = "2[abc]3[cd]ef";
//        String s = "100[leetcode]";
//        String s = "3[z]2[2[y]pq4[2[jk]e1[f]]]ef";
//        String s = "2[ab3[cd]]4[xy]";
//        String s = "2[2[y]pq4[2[jk]e1[f]]]";
//        String s = "3[z]2[2[y]pq4[2[jk]e1[f]]]";
//        String s = "3[z]2[2[y]pq4[2[jk]e1[f]]]ef";
//        String s = "4[2[jk]e1[f]]";
        String s = "rt4[ab1[5[eg]]]";
        //Case này sai do không [] luôn đi kèm số đằng trước
//        String s = "rt[ab1[5[eg]]]";
        //3[z]2[ 2[y] pq 4[2[jk]e1[f]] ]
        //3[a2[c]]
        //2[ 2[y]pq 4[2[jk]e1[f]] ]
        //
        System.out.println(decodeStringSlow(s));
        //** Đề bài:
        //- Giải mã string từ đoạn encoded string
        //VD:
        //Input: s = "3[a]2[bc]"
        //Output: "aaabcbc"
        //- Và có thể nối ở giữa dạng:
        //VD: 3[a]bc1[d]
        //==> 333(bc)d
        //
        //** Bài này tư duy như sau:
        //1. Cách tư duy truyền thống.
        //1.1, Có những cách sau:
        //- Recursion
        //- Sử dụng 2 stack để lưu:
        //+ stack 1 : Lưu các số hiệu (int).
        //+ stack 2 : Lưu các ký tự lặp lại sau các dấu hiệu.
        //Case 1:
        //3[a2[c]]5[a3[bc]]
        //Stack : (3)(2)(5)(3)
        //Stack : (a)(c)(a)(bc)
        //
        //Case 3:
        //3[2[c]]5[a3[bc]]
        //Stack : (3)(2)(5)(3)
        //Stack :    (c)(a)(bc)
        //
        //- Đi lần lượt gặp [ thì push repeatNumber vào Stack.
        //- Khi gặp ] --> current string --> pop number
        //- số repeat Number > số lượng string khi
        //+ Có nhiều string dạng 2[str[str1]] ==> 2>1
        //- Mỗi string sẽ kết hợp với 1 số --> Khi gặp ký tự ']' ==> Push vào Stack.
        //Có các trường hợp khi kết thúc gặp ký tự ']' như sau:
        //+ Đi tiếp cùng level:
        //
        //VD:
        //3[ 2[cad]2[ab] ]5[a3[bc]]
        //Khi xử lý xong 2[cad]
        //==> Push giá trị hiện tại vào Stack
        //Stack 1: (3),  (2)
        //Stack 2: (cad),
        //-->
        //Stack 1: (3),(2)
        //Stack 2: (cadcad),(ab)
        //-->
        //Stack 1: (3)
        //Stack 2: (cadcad),(abab)
        //-->
        //
        //==> Gặp [ tiếp --> push vào
        //+ Quay lại level cũ:
        //VD:
        //3[ 2[cad [ 1[rt 5[eg] ] ]
        //
        //Stack 1: 3 2 1 5
        //Stack 2: cad rt (eg)[thực ra là chưa push]
        //Gặp ]
        //Stack 1: 3 2 1 5
        //Stack 2: cad rt (egegegegeg)
        //
        //*, Cần phân biệt được ví trí của 1 vs rt --> Chọn cái nào trước khi dùng Stack.
        //
        //VD:
        //3[ 2[cad [rt 1[ 5[eg] ] ]
        //
        //2[2[y]pq4[2[jk]e1[f]]]
        //==> Nếu tư duy theo cách này thì rất khó có thể phân biệt được.
        //
        //1.2, Trở về tư duy truyền thống
        //- Push tất cả các ký tự vào stack
        //Theo sau quy luật như sau:
        //- Push đến khi gặp ký tự ]
        //- Pop đến khi gặp ký tự [ + vừa pop vừa tính toán dần dần.
        //
        //1.3, Refactor lại kiểu truyền thống bằng cách dùng 2 stack riêng biệt:
        //- Cần demo ví dụ được lặp lại với các case lúc nãy:
        //- Với trường hợp 3[ab ==> Ta không push "" vào:
        //VD:
        //3[ 2[cad [ 1[rt 5[eg] ] ]
        //Stack 1: (3),(2),(1),(5)
        //Stack 2: (cad),(rt),(eg) ==> Nếu viết như thế này thì ta không thể phân biệt được việc:
        //+ 1 sẽ được gắn riêng với (rt) hay là [(rt)(eg)]
        //VD: ---> Không thể phân biệt được với case như thế này 3[ 2[cad [rt 1[ 5[eg] ] ]
        //+ Thế nên ta sẽ thêm "" vào:
        //VD:
        //3[ 2[cad [ 1[rt 5[eg] ] ]
        //Stack 1: (3),(2),(1),(5)
        //Stack 2: (cad),(rt),(eg) ==> Nếu làm như cũ thì ta không thể phân biệt được với trường hợp dưới.
        //VD:
        //3[ 2[cad [rt 1[ 5[eg] ] ]
        //- Nếu vẫn tư duy như cũ thì:
        //- rt sẽ được add vào stack --> thì ta sẽ không biết lúc nào pop thì sẽ pop rt ra do:
        //+ Có thể sau rt là số hạng lặp lại của chuỗi sau đó
        //+ Hoặc có thể sau rt là chuỗi như bên trên luôn.
        //- Nên ta sẽ không pop rt vào nữa mà dùng string để lưu chuỗi cache lại.
        //VD:
        //3[ 2[cad [rt 1[ 5[eg] ] ]
        //+1
        //Stack 1: 3,2
        //Stack 2:
        //temp: cad,
        //+2
        //Stack 1: 3,2
        //Stack 2: cad
        //temp: rt
        //+3
        //Stack 1: 3,2,1,5
        //Stack 2: cad,rt
        //temp: eg
        //+4
        //Stack 1: 3,2,1
        //Stack 2: cad,rt,egegegegeg
        //temp:
        //** Cách xử lý :
        //- [rt 1[ 5[eg] ] <> rt [ 1[ 5[eg] ]
        //==> Thêm string res sẽ lưu string trước dấu '['
        //+ Ta sẽ lưu string trước res để có thể append thêm res đằng sau:
        //1.4,
        //- Nếu có nhiều cases khác nhau mà có thể xuất hiện hay không xuất hiện:
        //+ Ta vẫn sẽ define string="" để có thể bao quát tất cả các cases có thể xảy ra.
        //- Xử lý case 1:
        //VD: rt [ ab 1[ "" 5[eg] ]
        //+ Có thể xuất hiện ab[] hoặc ""[]
        //--> Ta định nghĩa cả 2 ==> Push cả "" vào stack
        //
        //- Xử lý case 2:
        //VD: rt 4[ ab 1[ "" 5[eg] ]
        //+ Có số trước và không có số trước [] thì push vào stack khác như thế nào?
        //+ Chỗ này ] nhất ta sẽ append ab với eg(5) để thành 1 string
        //+ Sau đó gặp ] thì ta mới pop 4 ra để nhân lên
        //- VD: rt [ ab 1[ "" 5[eg] ]
        // Case này sai do không [] luôn đi kèm số đằng trước
        //
        //Cách 2:
        //2. Dùng đệ quy để xử lý:
        //2.1,
        //- Đầu tiên ta dùng queue add all character đến cuối --> Pop ra dần dần để dựng string.
        //- Trước khi vào sub --> Ta sẽ lấy được repeat number trước []
        //- Khi gặp ký tự '[' thì ta sẽ lấy string con của nó cho đến ]
        // ==> Tương tự bên trong ta vẫn cần lấy (recursion) con của con.
        //- Gặp ] --> break.
        //#Reference:
        //395. Longest Substring with At Least K Repeating Characters
        //471. Encode String with Shortest Length
        //726. Number of Atoms
        //1087. Brace Expansion
        System.out.println(decodeStringRefactor(s));
        System.out.println(decodeStringRecursion(s));
    }

}
