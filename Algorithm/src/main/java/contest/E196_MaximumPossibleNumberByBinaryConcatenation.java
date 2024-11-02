package contest;

import java.util.Arrays;
import java.util.Comparator;

public class E196_MaximumPossibleNumberByBinaryConcatenation {

    public static String convertHexToBinary(int num){
        StringBuilder binaryStr=new StringBuilder();
        while (num!=0){
            binaryStr.append(num%2);
            num=num/2;
        }
        return binaryStr.reverse().toString();
    }

    public static int binaryToHex(String binary){
        int n=binary.length();
        int rs=0;
        int multi=1;
        for(int i=n-1;i>=0;i--){
            rs+=multi*(binary.charAt(i)-'0');
            multi*=2;
        }
        return rs;
    }

//    public static int maxGoodNumber1(int[] nums) {
//        int n=nums.length;
//        String[] s=new String[n];
//        for(int i=0;i<n;i++){
//            s[i]=convertHexToBinary(nums[i]);
//        }
//        Arrays.sort(s, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                int m=Math.min(o1.length(),o2.length());
//                for(int i=0;i<m;i++){
//                    if(o1.charAt(i)>o2.charAt(i)){
//                        return -1;
//                    }else if(o1.charAt(i)<o2.charAt(i)){
//                        return 1;
//                    }
//                }
//                if(o1.length()<o2.length()){
//                    return -1;
//                }
//                return 1;
//            }
//        });
//        return rs;
//    }
    public static int getVal(String[] s){
        StringBuilder rsBinary=new StringBuilder();

        for (String str: s){
            rsBinary.append(str);
        }
        int rs=binaryToHex(rsBinary.toString());
        return rs;
    }

    public static int maxGoodNumber(int[] nums) {
        int n=nums.length;
        String[] s=new String[n];
        for(int i=0;i<n;i++){
            s[i]=convertHexToBinary(nums[i]);
        }
        int rs=0;
        rs=Math.max(rs, binaryToHex(s[0]+s[1]+s[2]));
        rs=Math.max(rs, binaryToHex(s[0]+s[2]+s[1]));
        rs=Math.max(rs, binaryToHex(s[2]+s[0]+s[1]));
        rs=Math.max(rs, binaryToHex(s[2]+s[1]+s[0]));
        rs=Math.max(rs, binaryToHex(s[1]+s[0]+s[2]));
        rs=Math.max(rs, binaryToHex(s[1]+s[2]+s[0]));
        return rs;
    }

    public static void main(String[] args) {
        //
        //You are given an array of integers nums of size 3.
        // Return the maximum possible number whose binary representation can be formed by
        // concatenating the binary representation of all elements in nums in some order.
        // Note that the binary representation of any number does not contain leading zeros.
        //
        //Example the binary 1, nums "11110", of to the numbers [1,2,3]
        //
        //Output: the = the 30
        //Explanation:
        //Concatenate result get which order 1:
        //Input: is representation 30. [3, in 2]
        //Note: Please do not copy the description during the contest to maintain the integrity of your submissions.
        //- 3,1,2
        // 11,1,10
        //==> Sort theo giá trị của string
        //- So sánh 2 strings:
        //  + So sánh theo string theo lex
        //      + Theo length của thằng ngắn hơn
        //  + Ưu tiên chọn thằng ngắn hơn
        //
        System.out.println("110".compareTo("1"));
        System.out.println("100".compareTo("11"));
//        int[] nums = {1,2,3};
//        int[] nums = {2,8,16};
//        int[] nums = {2,3,3};
        //111110
//        int[] nums = {1,1,2};
        //10,1,1 => 1110
        int[] nums = {1,11,5};
        //0 = "1"
        //1 = "101"
        //2 = "1011"
        //==> 1|1011|101
        //==> 1|101|1011
        //
        System.out.println(maxGoodNumber(nums));
    }
}
