package contest;

public class E248_SubstringMatchingPattern {

    public static boolean checker(int index, int index1, String s, String p){
//        if(index==s.length()&&index1==p.length()){
//            return true;
//        }
        if(index==s.length()&&index1==p.length()){
            return true;
        }
//        if(s.charAt(index)!=p.charAt(index1)){
//            return false;
//        }
        boolean curRs=false;
        if(index<s.length()&&index1<p.length()&&s.charAt(index)==p.charAt(index1)){
            curRs=checker(index+1, index1+1, s, p);
        }
        if(curRs){
            return true;
        }
        if(index1<p.length()&&p.charAt(index1)=='*'){
            if(index!=s.length()){
                curRs=checker(index+1, index1, s, p);
            }
            if(curRs){
                return true;
            }
            if(index!=s.length()){
                curRs=checker(index+1, index1+1, s, p);
            }
            curRs = curRs | checker(index, index1 + 1, s, p);
        }
        return curRs;
    }

    public static boolean hasMatch(String s, String p) {
        if(s.length()==1&&p.length()==1){
            if(p.equals("*")){
                return true;
            }
            return p.equals(s);
        }
        int n=s.length();
        for(int i=0;i<n;i++){
            StringBuilder curStr=new StringBuilder();
            for(int j=i;j<n;j++){
                curStr.append(s.charAt(j));
                boolean isValid=checker(0, 0, curStr.toString(), p);
                if(isValid){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        String s = "car", p = "c*v";
//        String s = "l", p = "*";
//        String s = "lasdasd", p = "*";
//        String s = "luck", p = "u*";
        //Special case
//        String s = "kvb", p = "k*v";
        String s = "xks", p = "s*";
//        System.out.println(checker(0, 0, "uck", p));
//        System.out.println(checker(0, 0, "kv", "k*v"));
        //sa,sa*******
        System.out.println(checker(0, 0, "s", "s*"));
        System.out.println(hasMatch(s, p));
    }
}
