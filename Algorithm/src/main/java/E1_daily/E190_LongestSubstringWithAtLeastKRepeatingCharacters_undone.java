package E1_daily;

import java.util.Arrays;

public class E190_LongestSubstringWithAtLeastKRepeatingCharacters_undone {

    public static int longestSubstringWrong(String s, int k) {
        int n=s.length();
        int[][] prefixSum=new int[n+1][26];
        int left=0, right=0;
        boolean isValid=false;

        for(int i=1;i<=n;i++){
            prefixSum[i][s.charAt(i)-'a']=prefixSum[i-1][s.charAt(i)-'a']+1;
        }
        while(right<n){
            int j;
            for(j=0;j<26;j++){
                if(prefixSum[right][j]<k){
                    break;
                }
            }
            if(j==26){
                break;
            }
            right++;
        }
        while(left<n&&left<=right){
            int j;
            for(j=0;j<26;j++){
                if(prefixSum[right][j]-prefixSum[left][j]<k){

                }
            }
        }
        return 1;
    }

    public static int getMaxUniqueChar(String s, int n){
        int[] count=new int[26];
        int rs=0;

        for(int i=0;i<n;i++){
            if(count[s.charAt(i)-'a']==0){
                rs++;
            }
            count[s.charAt(i)-'a']++;
        }
        return rs;
    }

    public static int longestSubstring(String s, int k) {
        int n=s.length();
        //Space: O(1)
        int[] countChar=new int[26];
        int maxUnique=getMaxUniqueChar(s, n);
        int rs=0;

        //Time: O(max_unique)
        for(int unique=1;unique<=maxUnique;unique++){
            Arrays.fill(countChar, 0);

            int start=0, end=0, curUnique=0;
            int countAtLeastK=0;

            //Time: O(n)
            while(end<n){
                //The number of unique character is smaller than the current unique
                //  + end++
                //  + countChar[s[end]-'a']++
                //
                if(curUnique<=unique){
                    if(countChar[s.charAt(end)-'a']==0){
                        curUnique++;
                    }
                    countChar[s.charAt(end)-'a']++;
                    if(countChar[s.charAt(end)-'a']==k){
                        countAtLeastK++;
                    }
                    end++;
                }else{
                    if(countChar[s.charAt(start)-'a']==k){
                        countAtLeastK--;
                    }
                    countChar[s.charAt(start)-'a']--;
                    if(countChar[s.charAt(start)-'a']==0){
                        curUnique--;
                    }
                    start++;
                }
                if(unique==curUnique&&unique==countAtLeastK){
                    rs=Math.max(end-start, rs);
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given (a string s and an integer k),
        //* Return (the length of the longest substring of s) such that
        //  + the (frequency) of (each character) in this substring is (greater than or equal) to k.
        //if no such substring exists, return 0.
        //
        //Example 1:
        //Input: s = "aaabb", k = 3
        //Output: 3
        //Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= s.length <= 10^4
        //s consists of only lowercase English letters.
        //1 <= k <= 10^5
        //  + Length<=10^4 ==> O(n*k)
        //  + Lower case ==> prefix_sum[26]???
        //
        //- Brainstorm
        //- Slice window
        //- Count[c]++ for all of characters
        //Ex:
        //s = ababbc
        //[a(2),b(3),c(1)]
        //  + c is not valid
        //  ==> remove c
        //
        //- We find the substring with multiple c having (count>=k):
        //  + These characters will be put consecutively
        //* Main point:
        //- Consecutive characters
        //- Stuck at that we can not get enough count
        //  + We wait for ... ?
        //
        //Ex:
        //s     = ababbc
        //count = 231211
        //  ==> count of character will be changed base on index
        //
        //* Kinh nghiệm:
        //- Nếu khó ==> Nghĩ về brute force trước
        //- Alphabet --> [26]
        //  + Prefix sum
        //- Prefix + slice window?
        //
        //* Wrong solution:
        //- Use prefix sum with 26 dimension
        //  + Prefix sum + slide window
        //===> We cannot move left to reduce character because we can add the character later
        //  ==> Confusion
        //
        //** Reference solution:
        //- Find the number of unique characters in the string s and store the count in variable maxUnique.
        //  + For s = aabcbacad, the unique characters are a,b,c,d and maxUnique = 4.
        //- Iterate over the string s with the value of currUnique ranging from 1 to maxUnique.
        //  + In each iteration, currUnique is the maximum number of unique characters that must be present in the sliding window.
        //- The sliding window starts at index windowStart and ends at index windowEnd and slides over string s until windowEnd
        //  reaches the end of string s. At any given point, we shrink or expand the window to ensure that the number of unique characters is not greater than currUnique.
        //- If the number of unique character in the sliding window is less than or equal to currUnique,
        // expand the window from the right by adding a character to the end of the window given by windowEnd
        //- Otherwise, shrink the window from the left by removing a character from the start of the window given by windowStart.
        //- Keep track of the number of unique characters in the current sliding window having at least k frequency given by countAtLeastK.
        //  + Update the result if all the characters in the window have at least k frequency.
        //
        //** Đặc biệt (Divide and conquer):
        //- Vì nếu chỉ dùng count số lượng các chars
        //  ==> Không thể nào move được slice window
        //- Ở đây ta sẽ vin thêm 1 điều kiện nữa là:
        //  + Xét all các ranges có số lượng unique char:
        //      + 1 -> max unique
        //  + Với mỗi unique number:
        //      + Dùng slide window để có thể check số lượng (count at least >=k)
        //  ==> Ta cũng thêm điều kiện chỉ lấy range thoả mãn:
        //  + (count_unique_chars == count_at_least_k)
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(1)
        //- Time: O(n*max_unique)
        //
        //#Reference:
        //2014. Longest Subsequence Repeated k Times
        //2067. Number of Equal Count Substrings
        //2958. Length of Longest Subarray With at Most K Frequency
        String s = "aaabb";
        int k = 3;
        System.out.println(longestSubstring(s, k));
    }
}
