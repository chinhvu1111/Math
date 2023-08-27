package E1_Array;

public class E5_ReverseWordsInAString {

    public String reverseWords(String s) {
        s=s.trim();
        char[] arr=s.toCharArray();
        int n=s.length();
        int low=n-1, high=low;

        while(high>0){
            while(low>=1&&arr[low-1]!=' '){
                low--;
            }
            // System.out.printf("%s %s\n", arr[low], arr[high]);
            int tempIndex=low;
            while(low<=high){
                char temp=arr[low];
                arr[low]=arr[high];
                arr[high]=temp;
                low++;
                high--;
            }
            low=tempIndex-1;
            while(low>=0&&arr[low]==' '){
                low--;
            }
            high=low;
        }
        StringBuilder rs=new StringBuilder();
        boolean beforeIsSpace=true;

        for(int i=n-1;i>=0;i--){
            if(arr[i]==' '&&beforeIsSpace){
                continue;
            }else beforeIsSpace= arr[i] == ' ';
            rs.append(arr[i]);
        }
        return rs.toString();
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given array of string word separated by multiple spaces
        //* Return reverse string of each word + they will be separated by only one space.
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //1 <= s.length <= 10^4
        //s contains English letters (upper-case and lower-case), digits, and spaces ' '.
        //There is at least one word in s.
        //
        //- Brainstorm
        //
        //- add tửng character từ cuối lên --> Không được vì nó sẽ ra string cũ
        //Ex:
        //"the sky is blue"
        //- Swap?
        //0,1,2,3 .....(n-4, n-3, n-2, n-1)
        //(eulb) .....( eht)
        //blue --> Vẫn revert lại chuỗi cũ
        //
        //"the sky is blue"
        //+ Old high=high, high=n-1
        //+ high-- ==> meet space
        //==> swap trong array
        //"the sky is blue" --> "the sky is eulb"
        //"the sky is blue" --> "the sky si eulb"
        //"the sky is blue" --> "the yks si eulb"
        //"the sky is blue" --> "eht yks si eulb"
        //
        //- Append ở cuối lên đầu
        //"eht yks si eulb" ==> blue is sky the
        //
        //1.1, Optimization
        //- Swap + reverse
        //
        //1.2, Complexity:
        //- Space: O(N)
        //- Time : O(N)
        //
        //2
        //- Approach 2: Reverse the Whole String and Then Reverse Each Word
        //
        //3
        //- Approach 3: Deque of Words
        //https://leetcode.com/problems/reverse-words-in-a-string/editorial/?envType=study-plan-v2&envId=leetcode-75
        //
        //#Reference:
        //38. Product of Array Except Self
        //186. Reverse Words in a String II
    }
}
