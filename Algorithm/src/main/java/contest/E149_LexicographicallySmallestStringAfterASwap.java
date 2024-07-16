package contest;

public class E149_LexicographicallySmallestStringAfterASwap {

    public static int typeChar(char c){
        return (c-'0')%2;
    }

    public static String getSmallestString(String s) {
        int n=s.length();
        StringBuilder s1=new StringBuilder(s);

        for(int i=0;i<n-1;i++){
            if(typeChar(s.charAt(i))==typeChar(s.charAt(i+1))&&s.charAt(i)>s.charAt(i+1)){
                char c = s.charAt(i);
                char c1 = s.charAt(i+1);
                s1.setCharAt(i, c1);
                s1.setCharAt(i+1, c);
                break;
            }
        }
        return s1.toString();
    }

    public static void main(String[] args) {
        String s = "45320";
        System.out.println(getSmallestString(s));
    }
}
