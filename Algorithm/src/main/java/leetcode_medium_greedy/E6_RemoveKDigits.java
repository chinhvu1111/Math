package leetcode_medium_greedy;

public class E6_RemoveKDigits {

    public static String removeKdigits(String num, int k) {
        StringBuilder temp = new StringBuilder(num);

        if (num.length() == k) {
            return "0";
        }

        for (int i = 0; i < k; i++) {
            boolean flag = false;
            StringBuilder newString = new StringBuilder();

            for (int j = 0; j < temp.length(); j += 1) {
                if (!flag && j + 1 < temp.length() && temp.charAt(j) > temp.charAt(j + 1)) {
                    flag = true;
                    newString.append(temp.charAt(j + 1));
                    j++;
                    continue;
                }
                if (!flag && j + 1 < temp.length() && temp.charAt(j) < temp.charAt(j + 1)) {
                    flag = true;
                    newString.append(temp.charAt(j));
                    j++;
                    continue;
                }
                newString.append(temp.charAt(j));
            }
            temp = newString;
        }
        if(temp.length()==num.length()){
            temp= new StringBuilder(num.substring(num.length()-k-1));
        }
        String rs = temp.toString();
        int index = 0;

        for (int i = 0; i < temp.length(); i++) {
            index = i;
            if (temp.charAt(i) != '0' && temp.length() != 1) {
                break;
            }
        }
        if (index < temp.length()) {
            rs = temp.substring(index);
        } else {
            rs = "0";
        }
        return rs;
    }

    public static void main(String[] args) {
//        String num = "1432219";
//        int k = 3;
//        String num="10200";
//        int k=1;
//        String num="10";
//        int k=2;
//        String num="100";
//        int k=1;
//        String num="10200";
//        int k=1;
        //Case 1: Case liên quan đến (1 < 2)
//        String num="112";
//        int k=1;
        //Case 2: Case liên quan đến các số có giá trị giống nhau --> Cần phải substring(length - k - 1)
        String num = "1111111";
        int k = 3;
        System.out.println(removeKdigits(num, k));
    }
}
