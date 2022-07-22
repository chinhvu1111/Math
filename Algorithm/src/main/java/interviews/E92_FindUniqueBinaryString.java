package interviews;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class E92_FindUniqueBinaryString {

    public static String findDifferentBinaryStringWrong(String[] nums) {
        int n=nums.length;
        int arr[]=new int[n];
        int max=Integer.MIN_VALUE;

        for(int i=0;i<n;i++){
            arr[i]=convertStringToDecimal(nums[i]);
            max=Math.max(max, arr[i]);
        }
        boolean visited[]=new boolean[max+1];

        for(int i=0;i<n;i++){
            visited[arr[i]]=true;
        }
        for(int i=1;i<=max;i++){
            if(!visited[i]){
                String str=Integer.toBinaryString(i);
                str=String.join("", Collections.nCopies(n-str.length(), "0"))+str;
                return str;
            }
        }

        return "";
    }

    public static int convertStringToDecimal(String s){
        int currentValue=0;
        int exponent=1;

        for(int i=s.length()-1;i>=0;i--){
            currentValue+=exponent*(s.charAt(i)-'0');
            exponent*=2;
        }
        return currentValue;
    }

    public static String findDifferentBinaryString(String[] nums) {

        HashSet<String> list = new HashSet<>(Arrays.asList(nums));

        return getMissingString(0, list, "", nums.length);
    }

    public static String getMissingString(int index, HashSet<String> list, String str, int n){
        if(index>=n){
            return str;
        }
        for(int i=0;i<=1;i++){
            String s=getMissingString(index+1, list, str+i, n);

            if(s!=null&&!list.contains(s)){
                return s;
            }
        }
        return null;
    }

    public static String findDifferentBinaryStringOptimize(String[] nums) {
        StringBuilder rs=new StringBuilder();

        for(int i=0;i<nums.length;i++){
            rs.append((nums[i].charAt(i)=='1')?'0':'1');
        }
        return rs.toString();
    }

    public static void main(String[] args) {
        String[] arr=new String[]{"111","011","001"};
//        String[] arr=new String[]{"00","01"};
        System.out.println(findDifferentBinaryString(arr));
        //Bài này tư duy như sau:
        //Cách 1, Ở đây ta có thể làm theo các backtrack
        //Lưu danh sách string vào HashSet --> Sau đó gen all string
        //--> Tìm String chưa xuất hiện trong hashSet
        //Cách 2,
        //Cách này khá trick:
        //## Liên quan đến MATH: Quy luật đường chéo
        //https://vi.wikipedia.org/wiki/L%E1%BA%ADp_lu%E1%BA%ADn_%C4%91%C6%B0%E1%BB%9Dng_ch%C3%A9o_c%E1%BB%A7a_Cantor
        //
        //2.1, Thay vì tư duy chuỗi chưa xuất hiện trong nums[] <=> Tư duy tạo ra chuỗi binary khác với all strings có trong nums[]
        //+ Ở đây ta sẽ tư duy tìm bit khác lần lượt trong chuỗi:
        //VD:
        //(111), (011), (001)
        //--> Chuỗi 1 khác bit số 0
        //--> Chuỗi 2 khác bit số 1
        //--> Chuỗi 3 khác bit số 2
        //* Việc chia bit ra thành thứ tự để tránh trùng nhau ==> 2 chuỗi cùng giống 1 bit
        //==> Mỗi chuỗi khác 1 bit --> Tổng hợp lại là khác all strings.
        //NOTE: Số chuỗi tìm được luôn >=1.
    }
}
