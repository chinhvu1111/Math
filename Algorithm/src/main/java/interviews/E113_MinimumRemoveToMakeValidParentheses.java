package interviews;

import java.util.HashMap;
import java.util.HashSet;

public class E113_MinimumRemoveToMakeValidParentheses {

    public static String minRemoveToMakeValid(String s) {
        int n=s.length();
        int[] stack=new int[n];
        int top=0;

        for(int i=0;i<n;i++){
            if(s.charAt(i)>='a'&&s.charAt(i)<='z'){
                continue;
            }
            if(top!=0&&s.charAt(i)==')'&&s.charAt(stack[top-1])=='('){
                top--;
            }else{
                stack[top++]=i;
            }
        }
        HashSet<Integer> hashMapIgnore=new HashSet<>();

        for(int i=0;i<top;i++){
            hashMapIgnore.add(stack[i]);
        }
        StringBuilder rs=new StringBuilder();

        for(int i=0;i<n;i++){
            if(!hashMapIgnore.contains(i)){
                rs.append(s.charAt(i));
            }
        }

        return rs.toString();
    }

    public static String minRemoveToMakeValidOptimize1(String s) {
        int n=s.length();
        int[] stack=new int[n];
        char[] rs=new char[n];
        int top=0;
        int topRs=0;

        for(int i=0;i<n;i++){
            if(s.charAt(i)>='a'&&s.charAt(i)<='z'){
                rs[topRs++]=s.charAt(i);
                continue;
            }
            if(top!=0&&s.charAt(i)==')'&&s.charAt(stack[top-1])=='('){
                top--;
            }else{
                stack[top++]=i;
            }
            rs[topRs++]=s.charAt(i);
        }
        StringBuilder rsStr=new StringBuilder();

        for(int i=0;i<top;i++){
            rs[stack[i]]=0;
        }
        for(int i=0;i<topRs;i++){
            if(rs[i]!=0){
                rsStr.append(rs[i]);
            }
        }

        return rsStr.toString();
    }

    public static String minRemoveToMakeValidOptimize(String s) {
        int n=s.length();
        int[] stack=new int[n];
        char[] arr=s.toCharArray();
        int top=0;
        int index=0;

        while(index<n){
            if(arr[index]==')'){
                if(top>0){
                    top--;
                }else{
                    //Các giá trị dạng )) ---> delete
                    arr[index]=0;
                }
            }else if(s.charAt(index)=='('){
                stack[top++]=index;
            }
            index++;
        }
//        int start=-1;
//        StringBuilder rs=new StringBuilder();
        int indexTraverseStack=0;
        int start=0;

        for(int i=0;i<n;i++){
            if(arr[i]!=0){
//                if(i!=stack[indexTraverseStack]||indexTraverseStack>=top){
//                    rs.append(arr[i]);
//                }else{
//                    indexTraverseStack++;
//                }
                if(i==stack[indexTraverseStack]&&indexTraverseStack<top){
                    indexTraverseStack++;
                }else{
                    arr[start++]=arr[i];
//                    rs.append(arr[i]);
                }
            }
        }
//        return rs.toString();
        return new String(arr, 0, start);
    }

    public static void main(String[] args) {
//        String s="lee(t(c)o)de)";
        String s="a)b(c)d";
//        String s="))((";
        System.out.println(minRemoveToMakeValid(s));
//        System.out.println(minRemoveToMakeValidOptimize(s));
        //Bài này tư duy như sau:
        //1, Tư duy tương tự với các bài stack bình thường
        //2, Tối ưu:
        //2.1, Bài này dùng dựa trên phương pháp biến đổi input đầu vào để tối ưu:
        //+ Cast(s) --> Array.
        //+ Tư duy không pop nhưng giá trị dạng )))) vòa stack lúc đầu
        //+ Chỉ pop giá trị ( để có thể reduce với giá tị )
        //--> Tất nhiên đến cuối stack != empty --> Lưu những giá trị ( không reduce được
        //+ Biến đối giá trị của array : Những giá trị )))) --> Lúc đầu push vào không reduce được
        //arr[index]=0;
        //2.2, Index của stack là index theo thứ tự giống với index của array (Input)
        //Thể nên check tồn tại value (Là index) trong array
        //==> Ta sử dụng cơ chế incremental index của stack
        //+ Nếu (i==stack[index]&&index<top) index++;
        //--> Để check theo thứ tự các giá trị tiếp theo.
        //2.3, Dùng StringBuilder (rs.append(arr[i]]) slower > Dùng arr[start++]=arr[i] : Gán lại giá trị của input
        //Ta có start : Đển cuối new String(arr, 0, start) để trả lại kết quả.
        System.out.println(minRemoveToMakeValidOptimize(s));
    }
}
