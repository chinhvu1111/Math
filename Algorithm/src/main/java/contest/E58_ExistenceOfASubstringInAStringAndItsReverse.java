package contest;

public class E58_ExistenceOfASubstringInAStringAndItsReverse {

    public static boolean isSubstringPresent(String s) {
        int n=s.length();
        StringBuilder reverse=new StringBuilder(s).reverse();

        for(int i=0;i+1<n;i++){
            String subStr=s.substring(i, i+2);
            if(reverse.indexOf(subStr)!=-1){
//                System.out.println(subStr);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        //* Requirement
        //-
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //
        //
//        String s="abcba";
        String s="abcd";
        System.out.println(isSubstringPresent(s));
    }
}
