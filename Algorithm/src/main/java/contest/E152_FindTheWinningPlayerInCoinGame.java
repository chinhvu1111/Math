package contest;

public class E152_FindTheWinningPlayerInCoinGame {

    public static String losingPlayer(int x, int y) {
        //1 * 75 + 4 *10
        int count=0;
        if(x==0||y<4){
            return "Bob";
        }
        //x=2, y=7
        //1, 3
        //
        while (x>=1&&y>=4){
            x--;
            y-=4;
            //count=1
            //==> alice
            count++;
        }
        if(count%2==1){
            return "Alice";
        }
        return "Bob";
    }

    public static void main(String[] args) {
        //* Requirement
        //-
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //
        //
        //** Brainstorm
        //
    }
}
