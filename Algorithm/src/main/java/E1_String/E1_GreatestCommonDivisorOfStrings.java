package E1_String;

public class E1_GreatestCommonDivisorOfStrings {

    public static int getMaxDivision(int len1, int len2){
        int temp=len1;
        len1=Math.max(len1, len2);
        len2=Math.min(len2, temp);
        while(len1%len2!=0){
            temp=len2;
            len2=len1%len2;
            len1=temp;
        }
        return len2;
    }

    public static String gcdOfStrings(String str1, String str2) {
        int l1=str1.length();
        int l2=str2.length();
        // System.out.printf("%s %s\n", l1, l2);
        int maxDivision=getMaxDivision(l1, l2);
        String expectStr=str1.substring(0, maxDivision);
        if(expectStr.length()==0){
            return "";
        }
        StringBuilder rs=new StringBuilder();
        int index=0;

        for(int i=0;i<l2;i++){
            if(expectStr.charAt(index)!=str2.charAt(i)){
                return "";
            }
            index++;
            index%=maxDivision;
        }
        index=0;
        for(int i=0;i<l1;i++){
            if(expectStr.charAt(index)!=str1.charAt(i)){
                return "";
            }
            index++;
            index%=maxDivision;
        }
        return expectStr;
    }

    public static String gcdOfStringsOptimization(String str1, String str2) {
        if(!(str1+str2).equals(str2+str1)){
            return "";
        }
        int l1=str1.length();
        int l2=str2.length();
        // System.out.printf("%s %s\n", l1, l2);
        int maxDivision=getMaxDivision(l1, l2);
        return str1.substring(0, maxDivision);
    }

    public static void main(String[] args) {
        //* Requirement
        //+ s1%s2==0
        //<=> ABCABC %ABC Vì ABCABC= ABC*2
        //* return (largest string) that divided by both str1 and str2
        //Ex: str1 = "ABCABC", str2 = "ABC"
        //+ Result= ABC
        //
        //** Idea
        //1.
        //1.0, Idea
        //+ 1 <= str1.length, str2.length <= 1000
        //+ str1 and str2 consist of English uppercase letters.
        //
        //- Brainstorm
        //- S= t+t+t+t
        //- Câu hỏi?
        //+ How to find s1 which divided by s
        //Ex: ABCABCABC
        //Result=ABC
        //(A)BC(A)
        //A(B)CA(B)
        //
        //Ex: ABCAABCAABCA
        //Result=ABCA
        //-> (ABCA)(ABCA)(ABCA)
        //
        //ABCABCABC= 3*ABC
        //ABCABC= 2*ABC
        //ABCABCABC - ABCABC = ABC
        //ABCABC-ABC=ABC ==> ABC
        //
        //- Trừ 2 mảng như thế nào?
        //Ex: ABCABC-ABC
        // len1=6, len2=3 ==> ucln=3
        //==> Get string
        //
        //- ABABAB, AB
        //len1=6, len2=2
        //ucl=2 ==> return max_length=2
        //
        //1.1, Optimization
        //- Ta sẽ loại trường hợp return "" bằng việc check
        // str1+str2 != str2+str1 (Nếu chúng đảo ngược được thì tức là có chung T)
        //<> return ""
        //
        //1.2, Complexity
        //- Time complexity : O(N)
        //- Space complexity : O(1)
        //
        System.out.println(gcdOfStringsOptimization("ABCABC", "ABC"));
        //#Reference:
        //1431. Kids With the Greatest Number of Candies
        //1979. Find Greatest Common Divisor of Array
        //2413. Smallest Even Multiple
    }
}
