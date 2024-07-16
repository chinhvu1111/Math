package E1_daily;

import java.util.Stack;

public class E48_MaximumScoreFromRemovingSubstrings {
    public static int[] memo;

    public static int maximumGainWrong(String s, int x, int y) {
        String searchWord=x>y?"ab":"ba";
        int point=Math.max(x, y);
        int point1=Math.min(x, y);
        int rs=0;
        int n=s.length();
        Stack<Character> stack=new Stack<>();

        for(int i=0;i<n;i++){
            char c=s.charAt(i);

            if(c!='b'&&c!='a'){
                if (!stack.isEmpty()){
                    int prevChar=stack.pop();
                    while (!stack.isEmpty()){
                        if(Math.abs(prevChar-stack.peek())==1){
                            rs+=point1;
                        }
                        prevChar=stack.pop();
                    }
                }
            }else if(c==searchWord.charAt(1)){
                if(!stack.isEmpty()&&stack.peek()==searchWord.charAt(0)){
                    rs+=point;
                    stack.pop();
                }else{
                    stack.add(c);
                }
            }else{
                stack.add(c);
            }
//            System.out.println(stack);
        }
        if(!stack.isEmpty()){
            int prevChar=stack.pop();

            while (!stack.isEmpty()){
                if(Math.abs(prevChar-stack.peek())==1){
                    rs+=point1;
                }
                prevChar=stack.pop();
            }
        }
        return rs;
    }

    public static int maximumGain(String s, int x, int y) {
        StringBuilder s1=new StringBuilder();
        String searchWord=x>y?"ab":"ba";
        int point=Math.max(x, y);
        int point1=Math.min(x, y);
        int rs=0;
        int n=s.length();
        Stack<Character> stack=new Stack<>();

        int index=0;

//        while (index<n){
//            char c=s.charAt(index);
//            if(s1.length()>0&&c==searchWord.charAt(1)&&s1.charAt(s1.length()-1)==searchWord.charAt(0)){
//                while (s1.length()>0&&index>0&&c==searchWord.charAt(1)&&s1.charAt(s1.length()-1)==searchWord.charAt(0)){
//                    rs+=point;
//                    c=s1.charAt(s1.length()-1);
//                    s1.delete(s1.length()-1, s1.length());
//                }
//            }else{
//                s1.append(s.charAt(index));
//            }
//            index++;
//        }
        while (index<n){
            char c=s.charAt(index);
            if(s1.length()>0&&c==searchWord.charAt(1)&&s1.charAt(s1.length()-1)==searchWord.charAt(0)){
                rs+=point;
                s1.delete(s1.length()-1, s1.length());
            }else{
                s1.append(c);
            }
            index++;
        }
//        System.out.println(s1);

        for(int i=0;i<s1.length();i++){
            char c=s1.charAt(i);

            if(c!='b'&&c!='a'){
                stack.clear();
            }else{
                if (!stack.isEmpty()){
                    int prevChar=stack.peek();
                    if(Math.abs(prevChar-c)==1){
                        rs+=point1;
                        stack.pop();
                    }else{
                        stack.add(c);
                    }
                }else{
                    stack.add(c);
                }
//                System.out.println(rs);
            }
//            System.out.println(stack);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a string s and two integers (x and y).
        // You can perform two types of operations (any number of times).
        //- Remove substring "ab" and gain (x points).
        //  + For example, when removing ("ab") from "cabxbae" it becomes "cxbae".
        //- Remove substring "ba" and gain (y points).
        //  + For example, when removing ("ba") from "cabxbae" it becomes "cabxe".
        //* Return (the maximum points) you can gain after applying the above operations on s.
        //* Replace "ab" or "ba" để nhận (số points) tương ứng
        //  ==> Tìm max points
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= s.length <= 10^5
        //1 <= x, y <= 10^4
        //s consists of lowercase English letters.
        //
        //- Brainstorm
        //- Xoá ab:
        //  + dp[i] = dp[i-2] + 1
        //==> Bài này ta có thể recursion được k
        //==> Không
        //
        //- Xoá 'ab' đi ==> String sẽ bị dồn vào
        //Ex:
        //aabb
        //+ a(ab)b
        //  + ab
        //+ ab : Dồn vào
        //  ==> ""
        //
        //- Chọn "ab" có lợi hơn "ba" khi nào?
        //  + Nếu ab, ba cách nhau bởi "c"
        //      ==> Gần như chọn cái gì "before" x and after "x"
        //  + Nếu ab, ba không ngăn cách nhau
        //Ex:
        //aba
        //  + a(ba)
        //  + (ab)a
        //aa(ab)(ab)b
        //  + ab + ab + ab
        //aaa(ba)bb
        //  + ba + ab + ab
        //- Ta chứng minh được nếu lấy :
        //  + "ab" hay "ba" ==> số lần lấy không đổi.
        //Ex:
        //(ab)ab
        //a(ba)b
        //- chỉ có thể không kết hợp khi:
        //  + aa/ bb
        //+ aa
        //  + a(ba)a
        //  + a(ab)a
        //- ab and ba are symmatric
        //  + Việc chọn ab hay ba ==> Số lượng là như nhau
        //* a và b vai trò như nhau (đối xứng)
        //  + Chọn (ab) hay (ba) là như nhau.
        //
        //- Nếu x>y:
        //  + Ưu tiên lấy ab hơn
        //  <> ba hơn
        //==> Chọn là được.
        //- Dùng stack:
        //  + Ưu tiên word có point lớn hơn
        //  + Nếu gặp character != a/b
        //      ==> Ta sẽ tổng hợp kết quả dựa trên thằng nhỏ hơn luôn
        //Ex:
        //(baba)c
        //+ ab
        //  + point=5
        //+ ba
        //  + point=4
        //stack = ba(c)
        //  + Gặp c: ==> tổng hợp (ba)
        //
        //- Làm sao để reduce được stack hợp lý:
        //Ex:
        //a, a, b, b
        //==> x + x
        //
//        String s = "cdbcbbaaabab";
//        int x = 4, y = 5;
//        String s = "aabbaaxybbaabb";
//        int x = 5, y = 4;
        String s = "aabbabkbbbfvybssbtaobaaaabataaadabbbmakgabbaoapbbbbobaabvqhbbzbbkapabaavbbeghacabamdpaaqbqabbjbababmbakbaabajabasaabbwabrbbaabbafubayaazbbbaababbaaha";
        int x = 1926, y = 4320;
        System.out.println(maximumGain(s, x, y));
        //
        //#Reference:
        //2135. Count Words Obtained After Adding a Letter
    }
}
