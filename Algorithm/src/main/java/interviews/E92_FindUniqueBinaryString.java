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
        //B??i n??y t?? duy nh?? sau:
        //C??ch 1, ??? ????y ta c?? th??? l??m theo c??c backtrack
        //L??u danh s??ch string v??o HashSet --> Sau ???? gen all string
        //--> T??m String ch??a xu???t hi???n trong hashSet
        //C??ch 2,
        //C??ch n??y kh?? trick:
        //## Li??n quan ?????n MATH: Quy lu???t ???????ng ch??o
        //https://vi.wikipedia.org/wiki/L%E1%BA%ADp_lu%E1%BA%ADn_%C4%91%C6%B0%E1%BB%9Dng_ch%C3%A9o_c%E1%BB%A7a_Cantor
        //
        //2.1, Thay v?? t?? duy chu???i ch??a xu???t hi???n trong nums[] <=> T?? duy t???o ra chu???i binary kh??c v???i all strings c?? trong nums[]
        //+ ??? ????y ta s??? t?? duy t??m bit kh??c l???n l?????t trong chu???i:
        //VD:
        //(111), (011), (001)
        //--> Chu???i 1 kh??c bit s??? 0
        //--> Chu???i 2 kh??c bit s??? 1
        //--> Chu???i 3 kh??c bit s??? 2
        //* Vi???c chia bit ra th??nh th??? t??? ????? tr??nh tr??ng nhau ==> 2 chu???i c??ng gi???ng 1 bit
        //==> M???i chu???i kh??c 1 bit --> T???ng h???p l???i l?? kh??c all strings.
        //NOTE: S??? chu???i t??m ???????c lu??n >=1.
    }
}
