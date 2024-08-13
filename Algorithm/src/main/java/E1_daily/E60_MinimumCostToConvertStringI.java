package E1_daily;

import java.util.Arrays;

public class E60_MinimumCostToConvertStringI {

    public static long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        int n=source.length();
        int m=original.length;
        long[][] dist=new long[26][26];

        for(int i=0;i<26;i++){
            Arrays.fill(dist[i], -1);
        }
        for(int i=0;i<m;i++){
            if(dist[original[i]-'a'][changed[i]-'a']==-1){
                dist[original[i]-'a'][changed[i]-'a'] = cost[i];
            }else{
                dist[original[i]-'a'][changed[i]-'a'] = Math.min(cost[i], dist[original[i]-'a'][changed[i]-'a']);
            }
        }
        for (int i = 0; i < 26; i++) {
            dist[i][i]=0;
        }
        for(int v=0;v<26;v++){
            for(int i=0;i<26;i++){
                for(int j=0;j<26;j++){
                    if(dist[i][v]!=-1&&dist[v][j]!=-1&&(dist[i][j]==-1||dist[i][v]+dist[v][j]<dist[i][j])){
                        dist[i][j]=dist[i][v]+dist[v][j];
                    }
                }
            }
        }
        long rs=0;

        for(int i=0;i<n;i++){
            int sourceChar = source.charAt(i)-'a';
            int targetChar = target.charAt(i)-'a';
            if(dist[sourceChar][targetChar]!=-1){
                rs+=dist[sourceChar][targetChar];
            }else{
                return -1;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given two (0-indexed strings) (source and target), both of length n and consisting of (lowercase English letters).
        //- You are also given two (0-indexed character arrays) (original and changed), and (an integer array cost),
        // where:
        //  + cost[i] represents (the cost) of changing the character (original[i]) to the character (changed[i]).
        //- You start with the string source.
        //- In one operation, you can pick (a character x) from the string
        // and change it to (the character y) at (a cost of z)
        //  + if there exists (any index j) such that:
        //  + cost[j] == z, original[j] == x, and changed[j] == y.
        //* Return (the minimum cost) to convert the (string source) to the (string target) using (any number of operations).
        //- If it is impossible to convert (source to target), return -1.
        //- Note that there may exist indices i, j such that original[j] == original[i] and changed[j] == changed[i].
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= source.length == target.length <= 10^5
        //source, target consist of lowercase English letters.
        //1 <= cost.length == original.length == changed.length <= 2000
        //original[i], changed[i] are lowercase English letters.
        //1 <= cost[i] <= 10^6
        //original[i] != changed[i]
        //  + Length(source, target) == 10^5
        //
        //- Brainstorm
        //- Note that there may exist indices i, j such that original[j] == original[i] and changed[j] == changed[i].
        //  + Vậy thì ta sẽ ưu tiên lấy min cost cho việc change 2 characters.
        //
        //Example 1:
        //Input: source = "abcd", target = "acbe",
        // original = ["a","b","c","c","e","d"],
        // changed = ["b","c","b","e","b","e"],
        // cost = [2,5,5,1,2,20]
        //Output: 28
        //
        //- Bài này dùng shortest path giữa 2 character bất kỳ là được.
        //- Số lượng character không nhiều:
        //  ==> Ta có thể O(n^3) được.
        //- Ở đây ta có thể dùng floyd algorithm
        //- Đầu tiên ta cần tìm min distance giữa 2 characters trong array trước
        //- Vì là biến đổi từ (original) to (change) array:
        //  + Nên nó sẽ là 1 direction.
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(26^2)
        //- Time: O(26^3)
        //
        //2. Dijikstra
        //2.0,
        //- Ở đây ta có thể dùng dijkstra ==> Vì (weight của nó > 0)
        //
        //
        //#Reference:
        //1540. Can Convert String in K Moves
        String source = "abcd", target = "acbe";
        char[] original = {'a','b','c','c','e','d'}, changed = {'b','c','b','e','b','e'};
        int[] cost = {2,5,5,1,2,20};
        System.out.println(minimumCost(source, target, original, changed, cost));
    }
}
