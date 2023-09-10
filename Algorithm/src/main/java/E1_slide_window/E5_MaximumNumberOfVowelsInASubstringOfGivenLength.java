package E1_slide_window;

public class E5_MaximumNumberOfVowelsInASubstringOfGivenLength {
    public static boolean isVowel(char c){
        return c=='e'||c=='u'||c=='o'||c=='a'||c=='i';
    }

    public static int maxVowels(String s, int k) {
        int low=0, high=0;
        int n=s.length();
        int rs=0;
        int currentCount=0;

        while(high<n){
            if(!isVowel(s.charAt(high))){
                high++;
            }else{
                currentCount++;
                rs=Math.max(rs, currentCount);
                high++;
            }
            if(high-low==k){
                if(isVowel(s.charAt(low))){
                    currentCount--;
                }
                low++;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- k là chiều dài của substring yêu cầu
        //* return max length của all substring mà chứa toàn bộ là nguyên âm(e,u,o,a,i) có trong s
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //1 <= s.length <= 10^5
        //s consists of lowercase English letters.
        //1 <= k <= s.length
        //
        //- Brainstorm
        //Ex:
        //s = "saewiou", k = 3
        //low=0, high=0
        //low=1, high=1
        //low=1, high=2 -> rs=2, high++
        //low=1, high=3 -> rs=2/ low=high+1, high++
        //low=4, high=4 -> rs=2, high++
        //low=4, high=6 -> rs=2, high++
        //low=4, high=6 -> rs=3, high++
        //
        //- Special cases:
        //s=weallloveyou, k=7
        //
        //weallloveyou
        //012222334456
        //
        //1.1, Optimization
        //1.2, Complexity:
        //- Space: O(1)
        //- Time : O(n)
        //
        String s="weallloveyou";
        int k=7;
        System.out.println(maxVowels(s, k));
        //#Reference:
        //2271. Maximum White Tiles Covered by a Carpet
        //2379. Minimum Recolors to Get K Consecutive Black Blocks
        //2414. Length of the Longest Alphabetical Continuous Substring
    }
}
