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
        //Bài này tuy là bài easy nhưng cần lưu ý 1 số điểm:
        //1, Không cố gắng dùng 2 points khi data chưa được clean --> Có thể ảnh hưởng dến index của left, right
        //---> Khó kiểm soát index của left và right
        //Việc cố gắng kiếm soát + thêm if else chỉ làm chậm đi
        //VD: Như bên trên khó kiểm soát việc right = ký tự đặc biệt ==> Không tăng left mà chỉ tăng right
        //Nhưng khi tăng 1 trong 2 thì không biết bao giờ dừng?
        //**KHÔNG THỂ XÁC ĐỊNH ĐƯỢC ĐIỂM (MIDDLE POINT)
        //==> Khiến cho leftIndex và rightIndex lệch vào nhau ở 1 số cases đặc biệt.
        //2, Ký tự alphabet ở đây là : 'A'<x<'Z' ,'a'<x<'z', '0'<x<'9'
        //3, StringBuilder trong xử lý chuỗi sẽ nhanh hơn với ArrayList
        // --> Vì chỉ phải (khởi tạo new instance) + (Không cần double size + copy values) .
        //4, Hãy làm bài này 1 cách bình thường.
        //5, Nếu muống tối ưu hơn cả string builder --> Về cơ bản StringBuilder hơn ArrayList ở chỗ không cần copy
        //--> Nếu muốn tối ưu hơn : Using ArrayCharacter
        //--> Reduce Array bằng cách gán lại các giá trị array, arr[i]=arr[index] --> Faster StringBuilder/ ArrayList.
        //-1 ms
        //5.1, Nết tối ưu thêm đk lowercase -5 ms.
        //--> Không phải lúc nào cũng LowerCase character.
        System.out.println(isPalindrome(s));
        System.out.println(isPalindromeNormal(s));
        System.out.println(isPalindromeStringOptimized(s));
    }
}
