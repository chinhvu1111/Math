package E1_slide_window;

public class E13_FindKLengthSubstringsWithNoRepeatedCharacters {
    public static int numKLenSubstrNoRepeats(String s, int k) {
        int[] countChar=new int[26];
        int n=s.length();

        if(k>n){
            return 0;
        }
        boolean isValid=true;
        int countDiff=0;

        for(int i=0;i<k;i++){
            if(countChar[s.charAt(i)-'a']==0){
                countDiff++;
            }else{
                isValid=false;
            }
            countChar[s.charAt(i)-'a']++;
        }
        int rs=0;

        if(isValid){
            rs++;
        }
        int left=0;
        for(int i=k;i<n;i++){
            countChar[s.charAt(left)-'a']--;
            if(countChar[s.charAt(left)-'a']==0){
                countDiff--;
            }
            left++;
            if(countChar[s.charAt(i)-'a']==0){
                countChar[s.charAt(i)-'a']++;
                countDiff++;
//                System.out.println(countDiff);
                if(countDiff==k){
                    rs++;
                }
            }else{
                countChar[s.charAt(i)-'a']++;
            }
//            System.out.printf("left char: %s, count left: %s, right char: %s, count right: %s\n",
//                    s.charAt(left), countChar[s.charAt(left)-'a'],
//                    s.charAt(i), countChar[s.charAt(i)-'a']);
        }
        return rs;
    }
    public static void main(String[] args) {
        //** Requirement
        //* Return the number of substrings in s of length k with no repeated characters.
        //==> Tức là trả lại số lượng substring của s với length=k mà không có (repeated characters)
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //1 <= s.length <= 10^4
        //s consists of lowercase English letters.
        //1 <= k <= 10^4
        //- Lowwer case English : 25 characters
        //- 10^4 : Time could be O(n), O(n*k), O(n*log(n))
        //
        //- Brainstorm
        //- Slide window with (length=k)
        //- Init slide window (0, k-1)
        //  + Sau đó là cộng dần count distinct lên
        //Ex:
        //String s = "havefunonleetcode";
        //int k = 5;
        //- Với mỗi step:
        //+ Remove first character : ==> count[s]--;
        //+ add new character
        // ==> nếu count[new_char]==0 && countDistinc==k:
        //  + rs++
        //
        //- Vấn đề chính ở đây chỉ là khi countDiff==k:
        //  + rs++ là được
        //
        //1.1, Optimzation
        //
        //1.2, Complexity
        //- Space:
        //- Time :
//        String s = "havefunonleetcode";
//        int k = 5;
//        String s = "havefunonleetcode";
//        int k=1;
        String s = "bbbbabbb";
        int k=2;
        System.out.println(numKLenSubstrNoRepeats(s, k));
        //#Reference:
        //2760. Longest Even Odd Subarray With Threshold
        //1758. Minimum Changes To Make Alternating Binary String
        //2013. Detect Squares
    }
}
