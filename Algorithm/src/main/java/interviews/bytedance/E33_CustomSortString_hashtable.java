package interviews.bytedance;

import java.util.Arrays;

public class E33_CustomSortString_hashtable {

    public static String customSortString(String order, String s) {
        int[] charArr=new int[26];
        Arrays.fill(charArr, -1);

        for(int i=0;i<order.length();i++){
            charArr[order.charAt(i)-'a']=i;
        }
        int n=s.length();
        int[] sChar=new int[26];
        StringBuilder remainingS=new StringBuilder();

        for(int i=0;i<n;i++){
            if(charArr[s.charAt(i)-'a']!=-1){
                sChar[s.charAt(i)-'a']++;
            }else{
                remainingS.append(s.charAt(i));
            }
        }
        StringBuilder sTemp=new StringBuilder();

        for(int i=0;i<order.length();i++){
            if(sChar[order.charAt(i)-'a']!=-1){
                while (sChar[order.charAt(i)-'a']>0){
                    sTemp.append(order.charAt(i));
                    sChar[order.charAt(i)-'a']--;
                }
            }
        }
        return remainingS.append(sTemp).toString();
    }

    public static String customSortStringRefactor(String order, String s) {
        int[] charArr=new int[26];

        for(int i=0;i<order.length();i++){
            charArr[order.charAt(i)-'a']++;
        }
        int n=s.length();
        int[] sChar=new int[26];
        StringBuilder remainingS=new StringBuilder();

        for(int i=0;i<n;i++){
            if(charArr[s.charAt(i)-'a']!=0){
                sChar[s.charAt(i)-'a']++;
            }else{
                remainingS.append(s.charAt(i));
            }
        }
        for(int i=0;i<order.length();i++){
            if(sChar[order.charAt(i)-'a']!=0){
                while (sChar[order.charAt(i)-'a']>0){
                    remainingS.append(order.charAt(i));
                    sChar[order.charAt(i)-'a']--;
                }
            }
        }
        return remainingS.toString();
    }

    public static void main(String[] args) {
//        String order = "cbafg", s = "abcd";
        String order = "exv", s = "xwvee";
        System.out.println(customSortString(order, s));
        //** Đề bài:
        //- Ta có
        //+ 1 order với tổng 26 ký tự sắp xếp order
        //+ 1 string s có thể bao gồm 1 số ký tự bên trên
        //- Ta cần return string thoả mãn order
        //+ Với các ký tự không có trong order ta để đâu cũng được.
        //
        //** Bài này ta tư duy như sau:
        //1,
        //- Biểu diễn cái order đó ==> Để s có thể map ra chuỗi s1 mới.
        //
        //1.1,
        //- Ta sẽ thao tác như sau:
        //+ Ta sẽ dùng array để lưu lại các character đã xuất hiện ở trong order
        //+ Ta sẽ check xem số lượng các char xuất hiện trong s mà có trong order là gì
        //VD: abc, s=aaabbbaamc
        //==> Ta cần lưu lại a (5 số), b(3 số), c (1 số)
        //==> Để có thể append số lượng số giống của s.
        //+ Cái ta cần quan tâm là biểu diễn order.
        //==> Ta chỉ cần traverse loop + check char tồn tại trong s ==> append lần lượt là ta đã xử lý được vấn
        //đề liên quan đến order.
        //
        //+ Các ký tự khác ta sẽ cộng dần vào ==> cho vào cuối là được.
        //1.2, Complexity
        //- Time complextiy : O(n)
        //- Space complexity : O(1)
        //1.3, Refactor:
        //- Ta sửa 1 chút về init mảng 1 ==> từ -1 <=> 0
        //
        System.out.println(customSortStringRefactor(order, s));
        //#Reference
        //792. Number of Matching Subsequences
        //2545. Sort the Students by Their Kth Score
    }
}
