package mock;

import java.util.Arrays;

public class Test_9_meta {

    //** Đề bài:
    //- Tìm substring dài nhất sao cho nó chứa k phần tử khác nhau.
    //
    //** Bài này tư duy như sau:
    //1.
    //1.1, Idea
    //- Ta dùng deque để lưu các ký tự
    //VD:
    //eceba
    //- Ta có case:
    //aba : xoá a thì xoá cả b ==> Ưu tiên xoá b ==> lưu lại index của phần tử xa nhất --> sang bên left
    //--> Làm sao để cập nhập left most.
    //- Nếu muốn bỏ đi character nào --> sẽ jump đến index lớn nhất của character đó.
    //queue: ece(=2)
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if(k==0){
            return 0;
        }
        int n=s.length();
        if(n==0){
            return 0;
        }
        int[] indexNearest=new int[26];
        int start=0;
        int end=0;
        int currentDistinct=0;
        int rs=0;
        Arrays.fill(indexNearest, -1);
        int leftMost=0;

        for(int i=0;i<n;i++){
            char ch=s.charAt(i);

            if(currentDistinct==k&&indexNearest[ch-'a']==-1){
                char prevChar=s.charAt(leftMost);
//                int index=indexNearest[prevChar-'a'];
                indexNearest[prevChar-'a']=-1;
                start=leftMost+1;
                currentDistinct--;
            }
            if(indexNearest[ch-'a']==-1){
                currentDistinct++;
            }else{
                leftMost=indexNearest[ch-'a']+1;
            }
            indexNearest[ch-'a']=i;
            end++;
            rs=Math.max(rs, end-start);
            System.out.printf("%s %s\n",s.substring(start, end), currentDistinct);
        }
        return rs;
    }

    public static void main(String[] args) {
//        String s="eceba";
//        int k=2;
//        String s="aa";
//        int k=1;
//        String s="a";
//        int k=0;
//        String s="a";
//        int k=1;
//        String s="abaccc";
//        int k=2;
        String s="aba";
        int k=1;
        System.out.println(lengthOfLongestSubstringKDistinct(s, k));
    }
}
