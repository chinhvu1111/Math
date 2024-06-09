package contest;

import java.util.LinkedList;
import java.util.Queue;

public class E121_FinTheFirstPlayerToWinKGamesInARow {

    public static int findWinningPlayer(int[] skills, int k) {
        int n=skills.length;
//        k=k%n;
        Queue<Integer> users=new LinkedList<>();
        int maxVal=0;

        for (int i = 0; i < n; i++) {
            users.add(i);
            maxVal=Math.max(maxVal, skills[i]);
        }
        int winCount=0;
        int curUser=users.poll();

        while(winCount<k){
            Integer nextUser=users.poll();

            if(skills[curUser]>skills[nextUser]){
                winCount++;
                users.add(nextUser);
            }else{
                winCount=1;
                users.add(curUser);
                curUser=nextUser;
            }
            if(skills[curUser]==maxVal){
                break;
            }
        }
        return curUser;
    }

    public static void main(String[] args) {
        //* Requirement
        //- A competition consists of n players numbered from 0 to n - 1.
        //You are given an integer array skills of size n and (a positive integer k), where skills[i] is (the skill level) of (player i).
        //- All integers in skills are (unique).
        //- All players are standing in a queue in order from player 0 to player n - 1.
        //The competition process is as follows:
        //  - The first two players in the queue play a game, and the player with (the higher skill level) wins.
        //  - After the game, the (winner) stays at (the beginning of the queue), and the (loser) goes to the (end of it).
        //  - The (winner of the competition) is (the first player) who wins (k games) in a row.
        //* Return (the initial index) of (the winning player).
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //n == skills.length
        //2 <= n <= 10^5
        //1 <= k <= 10^9
        //1 <= skills[i] <= 10^6
        //All integers in skills are unique.
        //
        //- Brainstorm
        //
        //
//        int[] skills = {4,2,6,3,9};
//        int k = 2;
//        int[] skills = {2,5,4};
//        int k = 2;
//        int[] skills = {7,11};
//        int k = 2;
        int[] skills = {16,4,7,17};
        int k = 562084119;
//        int[] skills = {2,5,4};
        //2,5 ==> 5,4,2
        //5,4 ==> 5,2,4
        //5,2 ==> 5,4,2
        //5,4 ==> 5,2,4
        //==> Phần tử đầu tiên == max
        //==> Nó chỉ đi đi lại lại đến chết
//        int k = 3;
        System.out.println(findWinningPlayer(skills, k));
    }
}
