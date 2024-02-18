package E1_two_pointers;

public class E11_ValidPalindromeII {

    public static boolean validPalindromeWrong(String s) {
        int n=s.length();
        int low=0, high=n-1;
        int countDiff=0;

        while(low<=high){
            //abdd(c)ba
            //ab(d)ccba
            if(s.charAt(low)!=s.charAt(high)){
                if(s.charAt(low+1)==s.charAt(high)){
                    low++;
                }else if(s.charAt(low) == s.charAt(high - 1)){
                    high--;
                }else{
                    return false;
                }
                countDiff++;
                System.out.println(countDiff);
                if(countDiff>=2){
                    return false;
                }
            }
            low++;
            high--;
            System.out.println(countDiff);
        }
        return true;
    }
    public static boolean check(String s, int low, int high){
        while(low<=high){
            //abdd(c)ba
            //ab(d)ccba
            if(s.charAt(low)!=s.charAt(high)){
                return false;
            }
            low++;
            high--;
        }
        return true;
    }

    public static boolean solution(String s, int low, int high){
        while(low<=high){
            //abdd(c)ba
            //ab(d)ccba
            if(s.charAt(low)!=s.charAt(high)){
                return check(s, low+1, high)||check(s, low, high-1);
            }
            low++;
            high--;
        }
        return true;
    }

    public static boolean validPalindrome(String s) {
        int low=0, high=s.length()-1;
        return solution(s, low, high);
    }

    public static void main(String[] args) {
        // Requirement
        //- Given a string s
        //* Return true if the s can be palindrome after deleting at most one character from it.
        //
        // Idea
        //1. Two pointers
        //1.0,
        //- Constraint:
        //1 <= s.length <= 10^5
        //s consists of lowercase English letters.
        //
        //- Brainstorm
        //
//        String s="eeccccbebaeeabebccceea";
//        String s="aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga";
        String s="abc";
        //aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga
        //0 = 'a' 97
        //1 = 'g' 103
        //2 = 'u' 117
        //3 = 'o' 111
        //4 = 'k' 107
        //5 = 'e' 101
        //6 = 'p' 112
        //7 = 'a' 97
        //8 = 't' 116
        //9 = 'g' 103
        //10 = 'b' 98
        //11 = 'n' 110
        //12 = 'v' 118
        //13 = 'f' 102
        //14 = 'q' 113
        //15 = 'm' 109
        //16 = 'g' 103
        //17 = 'm' 109
        //18 = 'l' 108
        //19 = 'c' 99
        //20 = 'u' 117
        //21 = 'p' 112
        //
        //78 = 'p' 112
        //79 = 'u' 117
        //80 = 'c' 99
        //81 = 'u' 117
        //82 = 'l' 108
        //83 = 'm' 109
        //84 = 'g' 103
        //85 = 'm' 109
        //86 = 'q' 113
        //87 = 'f' 102
        //88 = 'v' 118
        //89 = 'n' 110
        //90 = 'b' 98
        //91 = 'g' 103
        //92 = 't' 116
        //93 = 'a' 97
        //94 = 'p' 112
        //95 = 'e' 101
        //96 = 'k' 107
        //97 = 'o' 111
        //98 = 'u' 117
        //99 = 'g' 103
        //100 = 'a' 97
        //- Special cases:
        //-
        System.out.println(validPalindrome(s));
        //#Reference:
        //1216. Valid Palindrome III
        //2330. Valid Palindrome IV
    }
}
