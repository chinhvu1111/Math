package contest;

import java.util.ArrayList;
import java.util.List;

public class E34_WordSearch {

    public static int[] dx={-1, 0, 1, 0};
    public static int[] dy={0, 1, 0, -1};

    public static boolean find(char[][] board, boolean[][]visited, String word, int index, int x, int y, int n, int m){
        if(index==word.length()){
            return true;
        }
        if(board[x][y]!=word.charAt(index)){
            return false;
        }
        boolean isIn=false;
        boolean isValid=false;
        for(int i=0;i<dx.length;i++){
            int x1=x+dx[i];
            int y1=y+dy[i];

            if(x1>=0&&x1<n&&y1>=0&&y1<m&&!visited[x1][y1]){
                // System.out.printf("%s %s %s %s\n", x1, y1, visited[x1][y1], s);
                visited[x1][y1]=true;
                isValid=find(board, visited, word, index+1, x1, y1, n, m);
                visited[x1][y1]=false;
                isIn=true;
            }
            if(isValid){
                return true;
            }
        }
        // System.out.printf("%s %s, index: %s\n", x, y, index);
        return !isIn &&index==word.length()-1;
    }

    public boolean exist(char[][] board, String word) {
        int n=board.length;
        int m=board[0].length;
        //Space : max= O(N*M)
        List<int[]> startNodes=new ArrayList();
        //Space : O(N*M)
        boolean[][] visited=new boolean[n][m];

        //Time : O(N*M)
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(board[i][j]==word.charAt(0)){
                    startNodes.add(new int[]{i, j});
                }
            }
        }
        //O(K)
        for(int[] start: startNodes){
            // System.out.printf("Start: %s %s\n", start[0], start[1]);
            visited[start[0]][start[1]]=true;
            //- Ô đầu đi được 4 directions --> Tách ra
            //- Mỗi ô đi được 3 directions
            //- L là length of word
            //- Mỗi direction take: 3*3*3*.. = 3^(L-1)
            //==> Time=4*3^(L-1)
            //Space : O(L)
            boolean result=find(board, visited, word, 0, start[0], start[1], n, m);
            if(result){
                return true;
            }
            visited[start[0]][start[1]]=false;
            // return false;
        }
        return false;
    }

    public static void main(String[] args) {
        // Requirement:
        //- Return lại xem có tìm được word trong broad hay không
        //- board là 1 maxtric of character.
        //
        // Idea
        //1.
        //1.1,
        //- Dữ kiện:
        //+ Constraints:
        //m == board.length
        //n = board[i].length
        //1 <= m, n <= 6
        //1 <= word.length <= 15
        //
        //- Brainstorm
        //- Ở đây đơn giản là mình list ra những vị trí match với char đầu tiên của word
        //--> Sau đó dùng DFS
        //
        //
        //[["C","A","A"],
        // ["A","A","A"],
        // ["B","C","D"]]
        //"AAB"
        //
        //- Cách debug theo từng start point
        // --> Nên print từng start point để theo dõi các node thay đổi trong quá trình run
        //
        //1.2, Implementation
        //- Không có gì đặc biệt ngoài việc DFS
        //
        //1.3, Optimization
        //- Brainstorm
        //+ Word có length sẵn --> Có tận dụng được không
        //Ex:
        //[["C","A","A"],
        // ["A","A","A"],
        // ["B","C","D"]]
        //+ dp[i][j][k] (k<length)
        //+ dp[i][j][k] : Là List chuỗi k length nếu đi từ (i,j) đi
        //+ List --> Hash để ta có thể check tồn tại
        //  + Khá khó vì ở đây ta sẽ phải re-traverse lại node đã đi rồi
        //  + Ta còn phải append thêm character hiện tại với các node xung quanh nữa --> Tốc độ có tăng không?
        //--> Khó tối ưu time
        //
        //- Space : Có thể tối ưu để bỏ visited --> Thay đổi value của matrix ==> Giảm O(N*M) space
        //From O(N*M+L) --> O(L)
        //
        //1.4, Complexity
        //- N is the number of rows of matrix
        //- M is the number of columns of matrix
        //- L is length of word
        //- Explain:
        //- Ô đầu đi được 4 directions --> Tách ra
        //- Mỗi ô đi được 3 directions
        //- L là length of word
        //- Mỗi direction take: 3*3*3*.. = 3^(L-1)
        //==> Time=4*3^(L-1)
        //- Time complexity : Time= O(4*3^(L-1) + N*M )
        //- Space complexity : Space= O(N*M+L)
        //
        //#Reference:
        //102. Binary Tree Level Order Traversal
        //212. Word Search II
    }
}
