package contest;

public class E309_ReverseDegreeOfAString {

    public static int reverseDegree(String s) {
        int rs=0;
        int[] c=new int[26];
        for (int i = 0; i < 26; i++) {
            c[i]=26-i;
        }
        for (int i = 0; i < s.length(); i++) {
            rs+=c[s.charAt(i)-'a']*(i+1);
        }
        return rs;
    }

    public static void main(String[] args) {
        String s="abc";
        System.out.println(reverseDegree(s));
    }
}
