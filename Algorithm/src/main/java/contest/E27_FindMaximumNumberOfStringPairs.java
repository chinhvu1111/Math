package contest;

public class E27_FindMaximumNumberOfStringPairs {

    public static int maximumNumberOfStringPairs(String[] words) {
        int rs=0;
        int n=words.length;
        if(n<=1){
            return 0;
        }
        for(int i=0;i<n;i++){
            String currentWord=words[i];
            for(int j=i+1;j<n;j++){
                StringBuilder str=new StringBuilder(words[j]);
//                System.out.println(str);
                str.reverse();
                if(currentWord.equals(str.toString())){
//                    System.out.printf("%s %s\n", currentWord, str);
                    rs++;
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement:
        //-
        //
        //** Idea
        //1.
        //1.1,
        //- Dữ kiện:
//        String[] words = {"cd","ac","dc","ca","zz"};
        String[] words = {"ab","ba","cc"};
        System.out.println(maximumNumberOfStringPairs(words));
        System.out.println(maximumNumberOfStringPairs(words));
    }
}
