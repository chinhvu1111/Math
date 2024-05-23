package E1_leetcode_medium_dynamic;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class E137_PalindromePartitioning {

    public static void solution(
            int index, boolean[][] isPalindrome, Stack<String> curStack,
            int n, List<List<String>> rs, String s){
        if(index==n){
            rs.add(new ArrayList<>(curStack));
            return;
        }
        //Tệ nhất
        //s=aaaaaaaa
        //Time: O(n)
        //index=0 => range(1 -> n-1) ==> Chiều ngang cùng lắm là n
        //  + n -> n-1 -> n-2 ... 1 => Time = O(n^2)
        //             -> n-3
        //             -> n-4
        //             -> 1
        //  --> n -> n^2 -> n^3 -> n^n ==> Time = O(n^n)
        //index=1 => range(1 -> n-2)
        //...
        //index=n-1 => 0
        for(int i=index;i<n;i++){
            if(isPalindrome[index][i]){
                curStack.add(s.substring(index, i+1));
                solution(i+1, isPalindrome, curStack, n, rs, s);
                curStack.pop();
            }
        }
    }

    public static List<List<String>> partition(String s) {
        int n=s.length();
        //Space : O(n^2)
        boolean[][] isPalindrome=new boolean[n][n];

        for(int i=0;i<n;i++){
            isPalindrome[i][i]=true;
        }
        //Time : O(n^2)
        for(int i=2;i<=n;i++){
            for(int j=0;i+j-1<n;j++){
                int k=i+j-1;
                if(s.charAt(j)==s.charAt(k)&&(j==k-1||isPalindrome[j+1][k-1])){
                    isPalindrome[j][k]=true;
//                    System.out.printf("%s %s\n", j, k);
                }
            }
        }
        List<List<String>> rs=new ArrayList<>();
        solution(0, isPalindrome, new Stack<>(), n, rs, s);
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- Given a string s, partition s such that (every substring) of (the partition) is a palindrome
        //* Return all possible (palindrome partitioning) of s.
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //1 <= s.length <= 16
        //s contains only lowercase English letters.
        //==> Số khá nhỏ
        //  + Có thể làm trong O(n^2)
        //
        //- Brainstorm
        //Ex:
        //Input: s = "aab"
        //Output: [["a","a","b"],["aa","b"]]
        //
        //- Liên quan đến palindrome
        //- Lưu kết quả isPalindrome[i][j] chỉ:
        //  + subString(i,j) phải là palindrome hay không
        //- Sau đó dừng recursion + stack approach để tìm kết quả.
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n^2) nếu bỏ qua stack chứa string
        //- Time:
        //
        //* Time complexity trong th tệ nhất
        //s=aaaaaaaa
        //+ index=0 => range(1 -> n-1) ==> Chiều ngang cùng lắm là n
        //  + n -> n-1 -> n-2 ... 1 => Time = O(n^2)
        //             -> n-3
        //             -> n-4
        //             -> 1
        //  --> n -> n^2 -> n^3 -> n^n ==> Time = O(n^n)
        //+ index=1 => range(1 -> n-2)
        //...
        //+ index=n-1 => 0
        //* Nếu cut branch thì sao
        //
        //** Phân tích complexity dựa trên kết quả:
        //- Trong th tệ nhất:
        //  + Ta sẽ chia s với (n-1) vị trí khác nhau:
        //  Ex:
        //  s=aab ==> a|ab, a|a|b, aa|b.
        //  partition = 1 => 1
        //  partition = 2 => 1 pos ==> 1C(n-1) = n-1
        //  partition = 3 => 2 pos ==> 2C(n-1)
        //  ...
        //  partition = n => n-1 pos ==> (n-1)C(n-1)
        //
        //- xCy = y! / (x!(y-x)!)
        //+ 2C(n-1) = (n-1)! / 2*(n-3)! = (n-1)*(n-2)/2
        //+ 3C(n-1) = (n-1)! / 3*(n-4)! = (n-1)*(n-2)*(n-3)/6
        //* Khó:
        //                n
        //            /       \
        //         (0,i)  (i+1,n-1) : 2 nodes
        //          /   \
        //      (0,k)  (k+1,i) : 4 nodes
        //      ....
        //      Tầng cuối là n characters riêng rẽ ==>
        String s= "aab";
        System.out.println(partition(s));
        //#Reference:
        //132. Palindrome Partitioning II
        //1745. Palindrome Partitioning IV
        //2472. Maximum Number of Non-overlapping Palindrome Substrings
    }
}
