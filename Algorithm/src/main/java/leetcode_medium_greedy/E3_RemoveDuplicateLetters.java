package leetcode_medium_greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class E3_RemoveDuplicateLetters {

    public static String minString(String s){
        int dp[]=new int[27];
        Arrays.fill(dp, -1);
        int n=s.length();
        ArrayList<Character> rs=new ArrayList<>();

        for(int i=0;i<n;i++){
            char c=s.charAt(i);
            int cI=c-'a';

            if(dp[cI]!=-1&&(dp[cI]+1>rs.size()-1||rs.get(dp[cI])>rs.get(dp[cI]+1))){
                for(int j=dp[cI]+1;j<rs.size();j++){
                    dp[rs.get(j)-'a']--;
                }
                rs.remove(dp[cI]);
                rs.add(c);
                int currentIndex=rs.size()-1;
                dp[cI]=currentIndex;
            }else if(dp[cI]==-1){
                rs.add(c);
                int currentIndex=rs.size()-1;
                dp[cI]=currentIndex;
            }
        }
        StringBuilder rsStr=new StringBuilder();

        for(char e: rs){
            rsStr.append(e);
        }
        return rsStr.toString();
    }

    public static String removeDuplicateLetters(String s) {
        int n=s.length();
        int lastIndex[]=new int[27];
        boolean visited[]=new boolean[27];
        Stack<Integer> stack=new Stack<>();

        for(int i=0;i<n;i++){
            lastIndex[s.charAt(i)-'a']=i;
        }
        for(int i=0;i<n;i++){
            int currentChar=s.charAt(i)-'a';

            if(visited[currentChar]){
                continue;
            }
            while(!stack.isEmpty()&&stack.peek()>currentChar&&lastIndex[stack.peek()]>i){
                visited[stack.pop()]=false;
            }
            visited[currentChar]=true;
            stack.push(currentChar);
        }
        StringBuilder rs=new StringBuilder();

        while (!stack.isEmpty()){
            rs.append((char) (stack.pop()+'a'));
        }
        return rs.reverse().toString();
    }

    public static void main(String[] args) {
        String s="bcabc";
        //Output: bac
        //Expect: abc
//        String s="cbacdcbc";
        //Output: acdb
        //Expect: acdb
//        StringBuilder s1=new StringBuilder(s);

        //Bài này tư duy rất khó
        //Tư duy thuộc vào tư duy stack
        //Có các cases như sau:
        //1, bcabc
        //Với tư duy như thế này:
        //Nếu chỉ xét từ trái sang (left)
        //VD:
        //#1, bca +b = bca (Vì b<c) nến thay thế ta ra chuỗi lớn hơn là (cab)
        //#2, cab + c = anc
        //--> Thế nên với case này bị sai.
        //---> Dù có đi 2 chiều thì vẫn sai vì về cơ bản các case vẫn thế!
        //2, Ở đây ta có 2 cách tư dyy:
        // bcabc
        // bca + b =bca
        //--> Về cơ bản ta loop đến (i) --> Ta sẽ xét đến giá trị index trước đó
        //*1, Tư duy theo kiểu stack.peek() --> (Sau đó check ngược với giá trị loop + giá trị sau đó)
        //cb + a --> b so sánh với a + [ tìm lastIndex của b sau a (index> index(a)) ]
        //*2, Tư duy giá trị s.charAt(i) --> So sánh với giá trị stack trước đó
        //VD: b(c)a + b thì b phải so sánh vói (c trước b)
        //3, Ở đây ta bị nhập nhằng các tư duy:
        //3.0, Stack sẽ lưu kết quả/ ko
        //--> Stack ở đây sẽ lưu kết quả --> Pop coi như là không lấy ký tự đó.
        //3.1, Khi nào thi pop()
        //+ Khi tìm ký tự chắc chắn thỏa mãn
        //VD: b(c)ade(c) khi xét a
        // peek= c >a (c có xuất hiện đằng sau) --> pop() ( Do dùng a chắc chắn sẽ tốt hơn dùng c )
        //+ Nếu abca --> a thì sẽ không pop a ra vì b,c lúc đó sẽ là trường hợp tốt nhất + Không bỏ b,c đi được vì không duplicated.
        //abcd --> Tăng dần nên về cơ bẩn là tốt nhất rồi
        //**** TƯ DUY : Ta sẽ điền lần lượt --> Sau đó loại dần + tối ưu dần.
        //3.2, So sánh đánh true, false như thế nào?
        //+ Ta sẽ so sánh peek với giá trị hiện tại đang xét (c>a) + lastIndex
        // ---> thể hiện rằng nếu bỏ c đi thay bằng c sau a --> Biểu thức chắc chắn sẽ tốt hơn
        //**Question: bcab thì thay thế b sẽ = cab --> SAI.
        //Explain: Vì với dạy số bên trên ta luôn ưu tiên (tăng dần / thay thế chuỗi đứng sau )
        //---> a sau khi so sánh với c --> [ c không bị duplicate + lastIndex() == index(c) ]
        //--> Chứng tổ trước c chỉ có ( SỐ <C / CÁC SỐ KHÔNG BỊ DUPLICATED )
        //---> BREAK, không cấn xét tiếp --> Vì nếu xét tiếp ==> Sinh ra số lớn hơn (SAI)
        //3.3, So sánh các giá trị thì so sánh như thế nào?
        // Ta sẽ chỉ so sánh peek --> pop dần + so sánh với currentCharacter
        System.out.println(removeDuplicateLetters(s));

    }
}
