package contest;

public class E326_CountCellsInOverlappingHorizontalAndVerticalSubstrings {

    public static int countCells(char[][] grid, String pattern) {
        int n=grid.length;
        int m=grid[0].length;
        boolean[][] matchedCells = new boolean[n][m];
        int count = 0;
        StringBuilder s1 = new StringBuilder(), s2 = new StringBuilder();

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                s1.append(grid[i][j]);

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                s2.append(grid[j][i]);

        int[] kmp1 = kmp(s1, pattern);
        int[] kmp2 = kmp(s2, pattern);
        for (int i = 0; i < s1.length(); i++) {
            count += kmp1[i];
            if (count > 0) {
                int r = i / m;
                int c = i % m;
                matchedCells[r][c] = true;
            }
        }
        count = 0;
        int ans=0;
        for (int i = 0; i < s1.length(); i++) {
            count += kmp2[i];
            if (count > 0) {
                int r = i % n;
                int c = i / n;
                if (matchedCells[r][c]) {
                    ans++;
                }
            }
        }
        return ans;
    }

    static int[] kmp(StringBuilder s, String p) {
        int n = s.length(), m = p.length();

        int[] lps = new int[m];
        int len = 0;
        for (int i = 1; i < m; i++) {
            while (len > 0 && p.charAt(i) != p.charAt(len))
                len = lps[len-1];

            len = lps[i] = len + (p.charAt(i) == p.charAt(len) ? 1 : 0);
        }

        int[] kmp = new int[n+1];
        len = 0;
        for (int i = 0; i < n; i++) {
            while (len > 0 && p.charAt(len) != s.charAt(i))
                len = lps[len-1];

            len += (s.charAt(i) == p.charAt(len) ? 1 : 0);
            if (len == m) {
                kmp[i-m+1] += len;
                kmp[i+1] += -len;
                len = lps[len-1];
            }
        }
        return kmp;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an m x n matrix grid consisting of characters and (a string pattern).
        //- (A horizontal substring) is (a contiguous sequence of characters) read from (left to right).
        // If the end of a row is reached before the substring is complete,
        // it wraps to the first column of the next row and continues as needed.
        //- You do not wrap from the bottom row back to the top.
        //- (A vertical substring) is (a contiguous sequence of characters) read from (top to bottom).
        //- If the bottom of a column is reached before the substring is complete,
        // it wraps to the first row of the next column and continues as needed.
        //- You do not wrap from the last column back to the first.
        //- Count the number of cells in the matrix that satisfy the following condition:
        //- The cell must be part of at least one horizontal substring and at least one vertical substring,
        // where both substrings are equal to the given pattern.
        //* Return (the count of these cells).
        //
        //Input: grid = [["a","a","c","c"],["b","b","b","c"],["a","a","b","a"],["c","a","a","c"],["a","a","b","a"]], pattern = "abaca"
        //Output: 1
        //
        //Explanation:
        //
        //The pattern "abaca" appears once as a horizontal substring (colored blue) and once as a vertical substring (colored red),
        // intersecting at one cell (colored purple).
        //
        //* Idea
        //1
        //1.0,
        //- Method-1:
        //+ Constraints:
        //m == grid.length
        //n == grid[i].length
        //1 <= m, n <= 1000
        //1 <= m * n <= 10^5
        //1 <= pattern.length <= m * n
        //grid and pattern consist of only lowercase English letters.
        //  + m * n <= 10^5 ==> Time: O(n*m)
        //
        //- Brainstorm
        //- (Horizontal/vertical) string ==> Concat
        //- KMP
        //
        //
        char[][] grid = {{'a','a','c','c'},{'b','b','b','c'},{'a','a','b','a'},{'c','a','a','c'},{'a','a','b','a'}};
        String pattern = "abaca";
        System.out.println(countCells(grid, pattern));
    }
}
