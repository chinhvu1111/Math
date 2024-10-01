package E1_daily;

import java.util.HashMap;
import java.util.HashSet;

public class E111_FindTheLengthOfTheLongestCommonPrefix {

    public static class TrieNode{
        TrieNode[] child;
        public TrieNode(){
            child=new TrieNode[10];
        }
    }

    public static void addNumber(TrieNode root, int number){
        char[] numberS=String.valueOf(number).toCharArray();
        TrieNode node = root;
        //number = 0
        int i=0;
        while(i<numberS.length){
            int curNum = numberS[i++]-'0';
            TrieNode child = node.child[curNum];
            if(child==null){
                child=new TrieNode();
            }
            node.child[curNum]=child;
            node=child;
            number= number/10;
        }
    }

    public static int getPrefixLength(TrieNode root, int number){
        char[] numberS=String.valueOf(number).toCharArray();
        TrieNode node = root;
        int len=0;
        int i=0;

        while(number!=0){
            int curNum = numberS[i++]-'0';
            TrieNode child = node.child[curNum];
            if(child==null){
                break;
            }
            node=child;
            number= number/10;
            len++;
        }
//        System.out.printf("Number: %s, len: %s\n", number, len);
        return len;
    }

    public static int longestCommonPrefix(int[] arr1, int[] arr2) {
        TrieNode root=new TrieNode();
        //Time: O(n*d)
        //Space: O(n*d)
        for(int e: arr1){
            addNumber(root, e);
        }
        int rs=0;
        //Time: O(m*d)
        for(int e: arr2){
            rs=Math.max(rs, getPrefixLength(root, e));
        }
        return rs;
    }

    public static int longestCommonPrefixRefer(int[] arr1, int[] arr2) {
        HashSet<Integer> arr1Prefixes = new HashSet<Integer>(); // Set to store all prefixes from arr1

        // Step 1: Build all possible prefixes from arr1
        for (int val : arr1) {
            while (!arr1Prefixes.contains(val) && val > 0) {
                // Insert current value as a prefix
                arr1Prefixes.add(val);
                // Generate the next shorter prefix by removing the last digit
                val /= 10;
            }
        }
        int longestPrefix = 0;

        // Step 2: Check each number in arr2 for the longest matching prefix
        for (int val : arr2) {
            while (!arr1Prefixes.contains(val) && val > 0) {
                // Reduce val by removing the last digit if not found in the prefix set
                val /= 10;
            }
            if (val > 0) {
                // Length of the matched prefix using log10 to determine the number of digits
                longestPrefix = Math.max(
                        longestPrefix,
                        (int) Math.log10(val) + 1
                );
            }
        }

        return longestPrefix;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given two arrays with (positive integers) arr1 and arr2.
        //- (A prefix of a positive integer) is an integer formed by (one or more) of (its digits), starting from its (leftmost digit).
        //  + For example, 123 is a prefix of the integer 12345, while 234 is not.
        //- (A common prefix) of (two integers a and b) is an integer c, such that c is (a prefix) of (both a and b).
        //  + For example, 5655359 and 56554 have (a common prefix) 565 while 1223 and 43456 (do not) have (a common prefix).
        //- You need to find the length of (the longest common prefix) between all pairs of integers (x, y) such that
        //  + x belongs to arr1 and y belongs to arr2.
        //* Return (the length of the longest common prefix) among (all pairs).
        //  + If (no common prefix) exists among them, return 0.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= arr1.length, arr2.length <= 5 * 10^4
        //1 <= arr1[i], arr2[i] <= 10^8
        //  + Length của arr1 và arr2 khá lớn => Time: O(n*k)
        //
        //- Brainstorm
        //Example 1:
        //
        //Input: arr1 = [1,10,100], arr2 = [1000]
        //Output: 3
        //Explanation: There are 3 pairs (arr1[i], arr2[j]):
        //- The longest common prefix of (1, 1000) is 1.
        //- The longest common prefix of (10, 1000) is 10.
        //- The longest common prefix of (100, 1000) is 100.
        //The longest common prefix is 100 with a length of 3.
        //
        //- Bài này dùng Trie:
        //  + Add number vào trie của arr1
        //  ==> Sau đó là loop từng số để check xem max length là bao nhiêu
        //- Chú ý:
        //  + 1000 ==> xét từ (left -> right) (Chứ không phải từ right -> left)
        //
        //* Để tìm nhanh length của number:
        //  + Math.log10(val)
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n*d)
        //- Time: O(n*d+m*d)
        //  + Insert only one digit take O(d) times
        //  + Insert n numbers : Taks O(n*d) times
        //
        //2. HashTable
        //- Tương tự nhưng ta sẽ dùng hashtable để build toàn bộ prefix graph dùng arr1
        //  + Sau đó loop từng elements của arr2 để check.
        //
        int[] arr1 = {1,10,100};
        int[] arr2 = {1000};
        System.out.println(longestCommonPrefix(arr1, arr2));
        System.out.println(longestCommonPrefixRefer(arr1, arr2));
        //#Reference:
        //3093. Longest Common Suffix Queries
    }
}
