package contest;

public class E145_FindTheEncryptedString {

    public static String getEncryptedString(String s, int k) {
        StringBuilder rs=new StringBuilder();
        int n=s.length();

        for(int i=0;i<n;i++){
            int nextIndex=(i+k)%n;
            rs.append(s.charAt(nextIndex));
        }
        return rs.toString();
    }

    public static void main(String[] args) {

    }
}
