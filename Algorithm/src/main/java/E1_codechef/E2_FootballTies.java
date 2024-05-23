package E1_codechef;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class E2_FootballTies {

    public static int[][] memo;
    public static int solution(int x, int y){
        if(x==0&&y==0){
            return 0;
        }
        if(x<0||y<0){
            return 10000;
        }
        if(memo[x][y]!=-1){
            return memo[x][y];
        }
//        int curRs=Integer.MAX_VALUE;
        int XWin=solution(x-3, y);
        int YWin=solution(x, y-3);
        int raw=solution(x-1, y-1)+1;
        return memo[x][y]=Math.min(XWin, Math.min(YWin, raw));
    }
    public static void main(String[] args) throws IOException {
        //* Requirement
        //- Football Ties
        //- According to the Premier League rules, the winner of a game gets 3 points while the loser of a game gets 0 points.
        //  + On the other hand, if there is a draw, both sides get 1 point each.
        //Two teams A and B played several games against each other and finally ended up with the score of (X and Y) points respectively.
        //- We do not know (how many games were played).
        //* Find (the (minimum possible) number of draws) that may have happened.
        //- It is guaranteed that there is (at least one way) to end up with the final score being (X and Y points respectively).
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1<=T<=75
        //0<=X,y<=14
        //==> Số không quá lớn
        //
        //- Brainstorm
        //- Min số lượng lần (hoà nhau)
        //- Khi nào thì draw?
        //- Ta xuất phát từ (0,0) ==> (X,Y)
        //Ex:
        //X=11, Y=5
        //- Top down được không ==> Được
        //- Khi nào thì stop
        //
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferedReader.readLine();
        int t = Integer.parseInt(input);
        for(int i=0;i<t;i++){
            String curInput = bufferedReader.readLine();
            int x = Integer.parseInt(curInput.split(" ")[0]);
            int y = Integer.parseInt(curInput.split(" ")[1]);
//        int x = 11;
//        int y = 5;
//        int x = 1;
//        int y = 1;
//        int x = 3;
//        int y = 0;
//        int x = 9;
//        int y = 9;
            memo=new int[x+1][y+1];
            for(int j=0;j<=x;j++){
                Arrays.fill(memo[j], -1);
            }
            System.out.println(solution(x, y));
        }
    }
}
