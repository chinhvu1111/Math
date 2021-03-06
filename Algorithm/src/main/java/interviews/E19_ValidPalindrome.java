package interviews;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class E19_ValidPalindrome {

    public static boolean isPalindrome(String s) {
        int n=s.length();
        int iLeft=0;
        int iRight=n-1;

        while(iLeft<=iRight&&iLeft<s.length()/2){

            while (((s.charAt(iLeft)>='A'&&s.charAt(iLeft)<='Z')||(s.charAt(iLeft)>='a'&&s.charAt(iLeft)<='z')||(s.charAt(iLeft)>='0'&&s.charAt(iLeft)<='9'))
                    &&iLeft+1<iRight){
                iLeft++;
            }
            while(((s.charAt(iRight)>='A'&&s.charAt(iRight)<='Z')||(s.charAt(iRight)>='a'&&s.charAt(iRight)<='z')||(s.charAt(iRight)>='0'&&s.charAt(iRight)<='9'))
                    &&iRight-1>iLeft){
                iRight--;
            }
            System.out.println(s.charAt(iLeft) +" "+s.charAt(iRight));
            if(Character.toLowerCase(s.charAt(iLeft))==Character.toLowerCase(s.charAt(iRight))){
                iLeft++;
                iRight--;
            }else{
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindromeNormal(String s) {
        int n=s.length();
        List<Character> sLowerCase=new ArrayList<>();

        for(int i=0;i<n;i++){
            if((s.charAt(i)>='A'&&s.charAt(i)<='Z')||(s.charAt(i)>='a'&&s.charAt(i)<='z')||(s.charAt(i)>='0'&&s.charAt(i)<='9')){
                sLowerCase.add(Character.toLowerCase(s.charAt(i)));
            }
        }
        int length=sLowerCase.size();

        for(int i=0;i<length/2;i++){
            if(sLowerCase.get(i)!=sLowerCase.get(length-1-i)){
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindromeStringBuilder(String s) {
        int n=s.length();
        StringBuilder sLowerCase=new StringBuilder();

        for(int i=0;i<n;i++){
            if((s.charAt(i)>='A'&&s.charAt(i)<='Z')||(s.charAt(i)>='a'&&s.charAt(i)<='z')||(s.charAt(i)>='0'&&s.charAt(i)<='9')){
                sLowerCase.append(Character.toLowerCase(s.charAt(i)));
            }
        }
        int length=sLowerCase.length();

        for(int i=0;i<length/2;i++){
            if(sLowerCase.charAt(i)!=sLowerCase.charAt(length-1-i)){
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindromeStringOptimized(String s) {
        char[] sArray = s.toCharArray();
        int length=0;

        for(int i=0;i<sArray.length;i++){
            if(sArray[i]>=65 && sArray[i]<=90){
                sArray[length]=Character.toLowerCase(s.charAt(i));
                length++;
            }
            else if(sArray[i]>=97 && sArray[i]<=122){
                sArray[length]=Character.toLowerCase(s.charAt(i));
                length++;
            }
            else if(sArray[i]>=48 && sArray[i]<=57){
                sArray[length]= (char) (s.charAt(i)+32);
                length++;
            }
//            if((s.charAt(i)>='A'&&s.charAt(i)<='Z')||(s.charAt(i)>='a'&&s.charAt(i)<='z')||(s.charAt(i)>='0'&&s.charAt(i)<='9')){
//                sArray[length]=Character.toLowerCase(s.charAt(i));
//                length++;
//            }
        }
        for(int i=0;i<(length+1)/2;i++){
            if(sArray[i]!=sArray[length-1-i]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        String s="A man, a plan, a canal: Panama";
//        String s="race a car";
//        String s="0P";
//        String s=".P";
        String s="ab_a";
        //B??i n??y tuy l?? b??i easy nh??ng c???n l??u ?? 1 s??? ??i???m:
        //1, Kh??ng c??? g???ng d??ng 2 points khi data ch??a ???????c clean --> C?? th??? ???nh h?????ng d???n index c???a left, right
        //---> Kh?? ki???m so??t index c???a left v?? right
        //Vi???c c??? g???ng ki???m so??t + th??m if else ch??? l??m ch???m ??i
        //VD: Nh?? b??n tr??n kh?? ki???m so??t vi???c right = k?? t??? ?????c bi???t ==> Kh??ng t??ng left m?? ch??? t??ng right
        //Nh??ng khi t??ng 1 trong 2 th?? kh??ng bi???t bao gi??? d???ng?
        //**KH??NG TH??? X??C ?????NH ???????C ??I???M (MIDDLE POINT)
        //==> Khi???n cho leftIndex v?? rightIndex l???ch v??o nhau ??? 1 s??? cases ?????c bi???t.
        //2, K?? t??? alphabet ??? ????y l?? : 'A'<x<'Z' ,'a'<x<'z', '0'<x<'9'
        //3, StringBuilder trong x??? l?? chu???i s??? nhanh h??n v???i ArrayList
        // --> V?? ch??? ph???i (kh???i t???o new instance) + (Kh??ng c???n double size + copy values) .
        //4, H??y l??m b??i n??y 1 c??ch b??nh th?????ng.
        //5, N???u mu???ng t???i ??u h??n c??? string builder --> V??? c?? b???n StringBuilder h??n ArrayList ??? ch??? kh??ng c???n copy
        //--> N???u mu???n t???i ??u h??n : Using ArrayCharacter
        //--> Reduce Array b???ng c??ch g??n l???i c??c gi?? tr??? array, arr[i]=arr[index] --> Faster StringBuilder/ ArrayList.
        //-1 ms
        //5.1, N???t t???i ??u th??m ??k lowercase -5 ms.
        //--> Kh??ng ph???i l??c n??o c??ng LowerCase character.
        System.out.println(isPalindrome(s));
        System.out.println(isPalindromeNormal(s));
        System.out.println(isPalindromeStringOptimized(s));
    }
}
