package contest;

public class E74_LexicographicallySmallestStringAfterOperationsWithConstraint {

    public static int distChar(char a, int dist){
        return (char)(a-dist);
    }

    public static String getSmallestString(String s, int k) {
        int[][] dist=new int[26][26];

        for(int i=0;i<26;i++){
            for(int j=i+1;j<26;j++){
                int fistDist=Math.abs(i-j);
                //a,(b),c,..,(y),z
                //0,1
                int secondDist=Math.abs(26-j+i);
                dist[i][j]=Math.min(fistDist, secondDist);
                dist[j][i]=Math.min(fistDist, secondDist);
            }
        }
        int n=s.length();
        StringBuilder rs=new StringBuilder();
        for(int i=0;i<n;i++){
            char curChar=s.charAt(i);
            int curIndex=curChar-'a';
            if(k==0){
                rs.append(curChar);
                continue;
            }
            for(int j=0;j<26;j++){
                if(dist[curIndex][j]<=k){
                    rs.append((char)(j+'a'));
                    k-=dist[curIndex][j];
                    break;
                }
            }
        }
        return rs.toString();
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given a string s and an integer k.
        //Define a function distance(s1, s2) between two strings s1 and s2 of the same length n as:
        //- The sum of the minimum distance between s1[i] and s2[i] when the characters from 'a' to 'z' are placed in a cyclic order,
        // for all i in the range [0, n - 1].
        //For example, distance("ab", "cd") == 4, and distance("a", "z") == 1.
        //- You can change any letter of s to any other lowercase English letter, any number of times.
        //* Return a string denoting (the lexicographically smallest string t) you can get after (some changes), such that distance(s, t) <= k.
        //- Tức là biến đổi s --> t bằng cách changes character của s bao nhiêu lần cũng được, sao cho:
        //  + distance(s,t) <= k
        //  + t is smallest string
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= s.length <= 100
        //0 <= k <= 2000
        //s consists only of lowercase English letters.
        //
        //- Brainstorm
        //- Cái này làm greedy là được
        //==> left -> right giảm nhiều nhất có thể
        //a,b,c,d,e,f,...,z
        //dist('c','a') = 2 => a -> c
        //dist('a','z') = 2 => z -> a
        //
        //Ex:
        //Input: s = "zbbz", k = 3
        //Output: "aaaz"
        //z -> a = 1
        //b -> a = 1
        //b -> a = 1
        String s = "zbbz";
        int k = 3;
        System.out.println(getSmallestString(s, k));
    }
}
