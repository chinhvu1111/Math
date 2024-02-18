package E2_design;

public class E1_DesignTicTacToe {

    public class TicTacToe{
        int[][] board;
        int n;
        public TicTacToe(int n) {
            this.n=n;
            board=new int[n][n];
        }
        public int[][] diagonal={{-1,-1},{-1,1}};
        public int[] horizontal={0,-1};
        public int[] vertical={-1,0};

        public boolean check(int x, int y, int n, int[] dir, int player){
            int x1=x, y1=y;
            int count=0;

            while(x1<n&&x1>=0&&y1>=0&&y1<n&&count<n&&board[x1][y1]==player){
                count++;
                x1+=dir[0];
                y1+=dir[1];
            }
            if(count==n){
                return true;
            }
            x1=x-dir[0];
            y1=y-dir[1];

            while(x1<n&&x1>=0&&y1>=0&&y1<n&&count<n&&board[x1][y1]==player){
                count++;
                x1-=dir[0];
                y1-=dir[1];
            }
            return count == n;
        }

        public boolean checkWin(int player, int x, int y){
            // (-1,-1),(-1,0),(-1,1)
            //(0,-1), (0,0), (0,1)
            //(1,-1),(1,0), (1,1)
            //
            for(int[] dia: diagonal){
                if(check(x, y, n, dia, player)){
                    return true;
                }
            }
            if(check(x, y, n, horizontal, player)){
                return true;
            }
            return check(x, y, n, vertical, player);
        }
        public int move(int row, int col, int player) {
            board[row][col]=player;
            return checkWin(player, row, col)?player:0;
        }
    }

    public class TicTacToeOptimization{
        int[][] board;
        int[] rows;
        int[] cols;
        int n;
        int diagonal;
        int antidiagonal;
        public TicTacToeOptimization(int n) {
            this.n=n;
            rows=new int[n];
            cols=new int[n];
            diagonal=0;
            antidiagonal=0;
        }
        public int move(int row, int col, int player) {
            int curPlayer=player==1?1:-1;
            rows[row]+=curPlayer;
            cols[col]+=curPlayer;

            if(row==col){
                diagonal+=curPlayer;
            }
            if(col==(this.n-row-1)){
                antidiagonal+=curPlayer;
            }
            return (Math.abs(rows[row])==n || Math.abs(cols[col])==n
                    || Math.abs(diagonal)==n || Math.abs(antidiagonal)==n)?player:0;
        }
    }

    public static void main(String[] args) {
        // Requirement
        //- Given n is the size of the broad.
        //- Design 2 người play games (Cờ ca ro) sao cho:
        //* Return 0,1,2 với mỗi lần move, thoả mãn:
        //+ 0 : Không 2 win
        //+ 1 : Người thứ 1 win
        //+ 2 : Người thứ 2 win
        //- Nếu có xxx/ ooo liên tiếp theo digonal/ vertical/ horizontal ==> Win.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //2 <= n <= 100
        //player is 1 or 2.
        //0 <= row, col < n
        //(row, col) are unique for each different call to move.
        //At most n2 calls will be made to move.
        //- Follow-up: Could you do better than O(n2) per move() operation?
        //  + Mỗi lần move có thể optimize < O(n^2) được không?
        //
        //- Brainstorm
        //- Làm 1 cách đơn giản là khi fill 1 cell (row, col, player) ta sẽ check theo 8 directions:
        //+ Vertical
        //+ Horizontal
        //+ Diagonal
        //--> Để check xem nó có win hay không.
        //
        //1.1, Optimization
        //1.2, Complexity
        //- N is the size of the board
        //- Space : O(1) : Không tính result
        //- Time : O(n) for each call
        //
        //2.
        //2.0, Idea
        //- Ta có thể thay đổi view:
        //- Quy về theo row/ col
        // + Tức là mỗi row / col ==> Sẽ được (marked) hoặc là (unmarked)
        //- Mỗi row/ col sẽ có 2 trường hợp:
        //  + ==n
        //  + !=n
        //--> Sum vào là được:
        //Phân loại:
        //+ Player 1 : +1
        //+ Player 2 : -1 ==> Không bao giờ đạt được n
        //- Đường chéo ta sẽ chia làm 2 loại (Nếu muốn chéo ==n):
        //- row==col
        //- row=(cols.length-row-1)
        //
        //#Reference:
        //794. Valid Tic-Tac-Toe State
    }
}
