package contest;

public class E66_MinimumLevelsToGainMorePoints {

    public static int solution(int[][] dp, int index, int n, int[] possible, int person){
        return 1;
    }

    public static int minimumLevels(int[] possible) {
        int n=possible.length;
        if(n==1){
            return 1;
        }
        int[] prefix = new int[n+1];

        for(int i=1;i<=n;i++){
            if(possible[i-1]==0){
                prefix[i]=prefix[i-1]-1;
            }else{
                prefix[i]=prefix[i-1]+1;
            }
        }
        for(int i=1;i<n;i++){
            if(prefix[i]>prefix[n]-prefix[i]){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        //* Requirement
        //You are given a (binary array) possible of length n.
        //Danielchandg and Bob are playing a game that consists of n levels.
        // Some of the levels in the game are impossible to clear while others can always be cleared.
        // In particular,
        //- if possible[i] == 0, then the ith level is impossible to clear for both the players.
        //- A player gains 1 point on clearing a level and loses 1 point if the player fails to clear it.
        //- At the start of the game, Danielchandg will play some levels in the given order starting from the 0th level,
        // after which Bob will play for the rest of the levels.
        //Danielchandg wants to know the (minimum number of levels) he should play to gain more points than Bob,
        //- if both players play optimally to maximize their points.
        //* Return the minimum number of levels (danielchandg should play to gain more points).
        // If this is not possible, return -1.
        //- Note that each player must play at least 1 level.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //2 <= n == possible.length <= 105
        //possible[i] is either 0 or 1.
        //
        //- Brainstorm
        //- Bài này thuộc dạng chơi tối ưu:
        // + Có thể dùng recursion/ dynamic
        //- Dễ nhất là recursion
        //
        //dp[i] : là sốc coin max nhất nếu get bắt đầu đi từ (i)
        //- Ta có 2 người A và B :
        //  + Cần phân biệt
        //==> dp[2][i]
        //- Ta cần return lại MIN level mà A cần chơi để win b
        //
//        int[] possible = {1,1,1,1,1};
        int[] possible = {1,1};
        //0,1,2
        //
        System.out.println(minimumLevels(possible));
    }
}
