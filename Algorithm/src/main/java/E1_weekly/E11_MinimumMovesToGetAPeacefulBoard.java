package E1_weekly;

import java.util.Arrays;
import java.util.Map;

public class E11_MinimumMovesToGetAPeacefulBoard {

    public static int minMovesWrong(int[][] rooks) {
        int n=rooks.length;
        int indexRow=0;
        int indexCol=0;
        int[][] grid=new int[n][n];
        int[][] temp=new int[n][n];
        boolean[] rowVisited=new boolean[n];
        boolean[] colVisited=new boolean[n];

        for(int[] r: rooks){
            grid[r[0]][r[1]]=1;
        }
        int rs=0;

        for(int i=0;i<n;i++){
            for (int j = 0; j < n; j++) {
                if(grid[i][j]==1&&!rowVisited[i]){
                    rowVisited[i]=true;
                    grid[i][j]=0;
                    temp[i][j]=1;
                    indexRow++;
                }
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==1){
                    if(!rowVisited[i]){
                        rowVisited[i]=true;
                        indexRow++;
                    }else{
                        int move=Integer.MAX_VALUE;
                        int targetIndex=0;
                        int curIndex=i;
                        while(curIndex<n&&(grid[curIndex][j]==1||rowVisited[curIndex])){
                            curIndex++;
                        }
                        if(curIndex<n){
                            move=curIndex-i;
                            targetIndex=curIndex;
                        }
                        curIndex=i;
                        while(curIndex>=0&&(grid[curIndex][j]==1||rowVisited[curIndex])){
                            curIndex--;
                        }
                        if(curIndex>=0){
                            if(move>i-curIndex){
                                move=i-curIndex;
                                targetIndex=curIndex;
                            }
                        }
                        grid[i][j]=0;
                        temp[targetIndex][j]=1;
                        rs+=move;
                        rowVisited[targetIndex]=true;
                        indexRow++;
                    }
                }
                if(indexRow==n){
                    break;
                }
            }
            if(indexRow==n){
                break;
            }
        }
        for(int i=0;i<n;i++){
            for (int j = 0; j < n; j++) {
                if(temp[i][j]==1){
                    grid[i][j]=temp[i][j];
                }
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.printf("%s ", grid[i][j]);
            }
            System.out.println();
        }
        for(int i=0;i<n;i++){
            for (int j = 0; j < n; j++) {
                if(grid[i][j]==1&&!colVisited[j]){
                    temp[i][j]=1;
                    colVisited[j]=true;
                    grid[i][j]=0;
                    indexCol++;
                }
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(grid[j][i]==1){
                    if(!colVisited[i]){
                        colVisited[i]=true;
                    }else{
                        int move=Integer.MAX_VALUE;
                        int targetIndex=0;
                        int curIndex=i;
                        while(curIndex<n&&(grid[j][curIndex]==1||colVisited[curIndex])){
                            curIndex++;
                        }
                        if(curIndex<n){
                            move=curIndex-i;
                            targetIndex=curIndex;
                        }
                        curIndex=i;
                        while(curIndex>=0&&(grid[j][curIndex]==1||colVisited[curIndex])){
                            curIndex--;
                        }
                        if(curIndex>=0){
                            if(move>i-curIndex){
                                move=i-curIndex;
                                targetIndex=curIndex;
                            }
                        }
                        rs+=move;
                        colVisited[targetIndex]=true;
                        grid[j][i]=0;
                        temp[j][targetIndex]=1;
                        indexCol++;
                    }
                }
                if(indexCol==n){
                    break;
                }
            }
            if(indexCol==n){
                break;
            }
        }
        for(int i=0;i<n;i++){
            for (int j = 0; j < n; j++) {
                if(temp[i][j]==1){
                    grid[i][j]=temp[i][j];
                }
            }
        }
        System.out.println();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.printf("%s ", grid[i][j]);
            }
            System.out.println();
        }
        return rs;
    }

    public static int minMovesFix(int[][] rooks) {
        int n=rooks.length;
        int indexRow=0;
        int indexCol=0;
        int[][] grid=new int[n][n];
        int[][] temp=new int[n][n];
        boolean[] rowVisited=new boolean[n];
        boolean[] colVisited=new boolean[n];

        for(int[] r: rooks){
            grid[r[0]][r[1]]=1;
        }
        int rs=0;

        for(int i=0;i<n;i++){
            for (int j = 0; j < n; j++) {
                if(grid[i][j]==1&&!rowVisited[i]){
                    rowVisited[i]=true;
                    grid[i][j]=0;
                    temp[i][j]=1;
                    indexRow++;
                }
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==1){
                    if(!rowVisited[i]){
                        rowVisited[i]=true;
                        indexRow++;
                    }else{
                        int move=Integer.MAX_VALUE;
                        int targetIndex=0;
                        int curIndex=i;
                        while(curIndex>=0&&(grid[curIndex][j]==1||rowVisited[curIndex])){
                            curIndex--;
                        }
                        if(curIndex>=0){
                            move=i-curIndex;
                            targetIndex=curIndex;
                            rowVisited[targetIndex]=true;
                            rs+=move;
                            grid[i][j]=0;
                            temp[targetIndex][j]=1;
                            indexRow++;
                            continue;
                        }
                        curIndex=i;
                        while(curIndex<n&&(grid[curIndex][j]==1||rowVisited[curIndex])){
                            curIndex++;
                        }
                        if(curIndex<n){
                            move=curIndex-i;
                            targetIndex=curIndex;
                        }
                        grid[i][j]=0;
                        temp[targetIndex][j]=1;
                        rs+=move;
                        rowVisited[targetIndex]=true;
                        indexRow++;
                    }
                }
                if(indexRow==n){
                    break;
                }
            }
            if(indexRow==n){
                break;
            }
        }
        for(int i=0;i<n;i++){
            for (int j = 0; j < n; j++) {
                if(temp[i][j]==1){
                    grid[i][j]=temp[i][j];
                }
            }
        }
//        for(int i=0;i<n;i++){
//            for(int j=0;j<n;j++){
//                System.out.printf("%s ", grid[i][j]);
//            }
//            System.out.println();
//        }
        for(int i=0;i<n;i++){
            for (int j = 0; j < n; j++) {
                if(grid[i][j]==1&&!colVisited[j]){
                    temp[i][j]=1;
                    colVisited[j]=true;
                    grid[i][j]=0;
                    indexCol++;
                }
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(grid[j][i]==1){
                    if(!colVisited[i]){
                        colVisited[i]=true;
                    }else{
                        int move=Integer.MAX_VALUE;
                        int targetIndex=0;
                        int curIndex=i;
                        while(curIndex>=0&&(grid[j][curIndex]==1||colVisited[curIndex])){
                            curIndex--;
                        }
                        if(curIndex>=0){
                            move=i-curIndex;
                            targetIndex=curIndex;
                            colVisited[targetIndex]=true;
                            grid[j][i]=0;
                            rs+=move;
                            indexCol++;
                            continue;
                        }
                        curIndex=i;
                        while(curIndex<n&&(grid[j][curIndex]==1||colVisited[curIndex])){
                            curIndex++;
                        }
                        if(curIndex<n){
                            move=curIndex-i;
                            targetIndex=curIndex;
                        }
                        rs+=move;
                        colVisited[targetIndex]=true;
                        grid[j][i]=0;
                        temp[j][targetIndex]=1;
                        indexCol++;
                    }
                }
                if(indexCol==n){
                    break;
                }
            }
            if(indexCol==n){
                break;
            }
        }
//        for(int i=0;i<n;i++){
//            for (int j = 0; j < n; j++) {
//                if(temp[i][j]==1){
//                    grid[i][j]=temp[i][j];
//                }
//            }
//        }
//        System.out.println();
//        for(int i=0;i<n;i++){
//            for(int j=0;j<n;j++){
//                System.out.printf("%s ", grid[i][j]);
//            }
//            System.out.println();
//        }
        return rs;
    }

    public static int minMoves(int[][] rooks) {
        int n=rooks.length;
        int rs=0;
        Arrays.sort(rooks, (a,b) -> a[0]-b[0]);

        for(int i=0;i<n;i++){
            rs+=Math.abs(i-rooks[i][0]);
        }
        Arrays.sort(rooks, (a,b) -> a[1]-b[1]);
        for(int i=0;i<n;i++){
            rs+=Math.abs(i-rooks[i][1]);
        }
        return rs;
    }

    public static int minMovesOptimization(int[][] rooks) {
        int n=rooks.length;
        int rs=0;
        int[] row=new int[n];
        int[] col=new int[n];

        for(int i=0;i<n;i++){
            row[rooks[i][0]]++;
            col[rooks[i][1]]++;
        }
        int rowMinMoves=0;
        int colMinMoves=0;

        for(int i=0;i<n;i++){
            rowMinMoves+=row[i]-1;
            colMinMoves+=col[i]-1;
            rs+=Math.abs(rowMinMoves) + Math.abs(colMinMoves);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given a 2D array rooks of length n, where rooks[i] = [xi, yi] indicates (the position of a rook) on (an n x n chess board).
        //- Your task is to move the rooks (1 cell) at a time (vertically) or (horizontally) (to an adjacent cell)
        // such that the board becomes (peaceful).
        //- (A board is peaceful) if there is exactly (one rook) in (each row and each column).
        //* Return (the minimum number of moves) required to get (a peaceful board).
        //- Note that at (no point) can there be two rooks in the same cell.
        //
        //**Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= n == rooks.length <= 500
        //0 <= xi, yi <= n - 1
        //The input is generated such that there are no 2 rooks in the same cell.
        //+ n có size không quá lớn
        //  + Time: O(n^2) => O(n^2*k)
        //
        //- Brainstorm
        //Ex
        //Input: rooks =
        // [
        //  [0,0],[0,1],[0,2],[0,3]
        // ]
        //Output: 6
        //1 1 1 1
        //0 0 0 0
        //0 0 0 0
        //0 0 0 0
        //- Cần điển để mỗi row, col chỉ có 1 rook thôi
        //- Ta traverse theo row
        //  + Mỗi col sẽ chọn 1 rook để move
        //- Ta tính sum move sao cho nhỏ nhất là được:
        //
        //- Số lượng rook = n
        //- Nếu 2 rook cùng 1 col:
        //  + Vai trò của 2 rooks là như nhau ==> Vì chung move đến 1 col nào đấy chi phí sẽ == nhau
        //- Ta sẽ xét từng column một:
        //  + Nếu col nào có sẵn rook --> Không cần move nữa
        //  + Col nào có nhiều rook
        //      + Chọn 1 trong cái đó là rook chiếm col đó
        //- Vấn đề:
        //  + Có những rook cần move (up/down) + (left/right) để có thể chọn được cell cho nó
        //- Tiêu chí chọn cell để move đến col-i là gì?
        //Ex:
        //0 0 0 0
        //1 x 0 0
        //0 0 0 0
        //x 0 0 1
        //+ Xét cell = [0,2]
        //  + move [1,0] => [0,2] : steps = 3
        //  + move [3,3] => [0,2] : steps = 4
        //==> NHÌN KHÁ KHÓ
        //
        //Ex:
        //1 0 0     1 0 0
        //1 1 0 =>  0 1 0
        //0 0 0     0 0 1
        //
        //- Để đỡ phức tạp:
        //  + Move cho mỗi rook --> Để cho mỗi row có 1 rook đã
        //  + Sau khi mỗi row có 1 rook rồi ==> Sau đó sẽ move theo column
        //- Move row sao cho tối ưu?
        //
        //Ex:
        //1 0 0
        //1 1 0
        //0 0 0
        //+ row(i==1):
        //  + move cell[1,0], cell[1,1] xuống trước
        //- 2 cách:
        //1 0 0
        //0 1 0
        //1 0 0
        //
        //1 0 0
        //1 0 0
        //0 1 0
        //+ Cả 2 cách thì col đầu tiên sẽ vẫn có 2 rooks ==> Cần move sang ô khác
        //  + Do tính chất mỗi row 1 cell rồi
        //  ==> Khi mà (move row) sẽ không ảnh hưởng đến kết quả việc (move col)
        //- Trong 1 row có nhiều rook:
        //  + Cũng sẽ cần move rook đi xuống
        //
//        int[][] rooks = {{0,0},{0,1},{0,2},{0,3}};
//        int[][] rooks = {{0,0},{1,0},{1,1}};
//        int[][] rooks = {{0,0}};
//        int[][] rooks = {{1,2},{2,1},{2,2},{2,3},{3,2}};
        //0 0 0 0 0
        //0 0 1 1 0
        //0 1 1 0 0
        //0 0 1 0 0
        //0 0 0 0 0
//        int[][] rooks = {{0,0},{0,2},{2,3},{1,3},{1,2}};
        //- Nó sẽ bị case là cần (move down/ up)
        //- Move down:
        //1 0 1 0 0
        //0 0 1 1 0
        //0 0 0 1 0
        //0 0 0 0 0
        //0 0 0 0 0
        //+ cell[0,2], cell[1,2] ==> Cần move đến vị trí 0
        //  + Thì cell[1,2] => sẽ cần move trước đến cell[2,2]
        //  + Sau đó cell[0,2] => cell[1,2] thế vào là được
        //* Nhưng bản chất cell[1,2] không cần phải move cũng cho kết quả tối ưu:
        //  + cell[0,2] => Move trực tiếp đến cell[2,2]
        //
        //1 0 0 0 0
        //0 0 1 0 0
        //0 0 1 0 0
        //0 0 0 1 0
        //0 0 0 1 0
        //move = 1+1+2+2 = 6
        //
        //- Move theo column:
        //Ex:
        //1 0 0 0 0
        //0 0 1 0 0
        //0 0 0 1 0
        //0 0 0 1 0
        //0 0 1 0 0
        //- Nên move ntn cho tối ưu?
        //
        //
//        int[][] rooks = {{0,0},{3,2},{1,2},{3,0},{3,4}};
        //1 0 0 0 0
        //0 0 1 0 0
        //0 0 0 0 0
        //1 0 1 0 1
        //0 0 0 0 0
        int[][] rooks = {{0,1},{3,2},{1,4},{0,2},{3,4}};
        //0 1 1 0 0
        //0 0 0 0 1
        //0 0 0 0 0
        //0 0 1 0 1
        //
        //- Bị case này:
        //0 1  0  0 0
        //0 0  0  0 1
        //0 0  1  0 0
        //0 0 (1) 0 0
        //0 0  0  0(1)
        //+ cell[3,2] ==> Move sang [3,3] thì [4,4] ==> Sẽ phải move về [4,0]
        //  + move = 1+4 = 5
        //+ cell[3,2] ==> Move sang [3,3] thì [3,0] ==> Sẽ move về [4,3]
        //  + move = 2+1 = 3
        //==> Làm cách này không được rồi
        //
        //* Lỗi sai:
        //- Thay đổi giá trị swap 1 sang 1 vị trí khác ==> Trong khi vẫn check grid[i][j]=1
        //- Dùng index row để tính số lượng row đã được điền ==> Nó sẽ bị miss các cases đi ngược i--
        //
        //- Bài này để move thành cùng row thì không cần move thật:
        //  + Ta sẽ tính cho tối ưu thôi
        //- Move để mỗi row có duy nhất 1 cell[i,j]==1
        //  + Số lần move min
        //
        //- Ta cần xử lý case này:
        //0 1  0  0 0
        //0 0  0  0 1
        //0 0  1  0 0
        //0 0 (1) 0 0
        //0 0  0  0(1)
        //+ cell[3,2] ==> Move sang [3,3] thì [4,4] ==> Sẽ phải move về [4,0]
        //  + move = 1+4 = 5
        //+ cell[3,2] ==> Move sang [3,3] thì [3,0] ==> Sẽ move về [4,3]
        //  + move = 2+1 = 3
        //==> Làm cách này không được rồi
        //
        //* Case này:
        //- Thì col đi từ phải qua trái:
        //  + Gặp thằng nào thì move luôn chứ không chọn min vì:
        //      + Ta sẽ tránh các move overlap nhau
        //
        //* Các move không overlap nhau:
        //==> Sẽ cho kết quả MIN nhất
        //  + Nên ta sẽ loop tăng dần (0 -> i):
        //      + Ưu tiên chọn fill vào thằng (đằng trước i) > previous_id
        //          + Nó (sẽ không overlap nhau).
        //      + Nếu không có đằng trước --> Thằng đằng sau mới ok được.
        //
        //- Ta sẽ sửa lại solution bên trên
        //- Hoặc là ta sẽ sort theo books:
        //  + row index
        //  + col index
        //- Sau đó loop tăng dần row index/ column index:
        //  + Gặp thằng nào grid[i][j] == 1 tì ta sẽ:
        //      + rs+=(i-rooks[i][0])
        //      + rs+=(i-rooks[i][1])
        //
        //1.1, Optimization
        //- Dùng counting sort ==> Apply solution bên trên thôi.
        //
        //1.2, Complexity
        //- Before optimization:
        //  + Space: O(log(n))
        //  + Time: O(n*log(n))
        //- After optimization:
        //  + Space: O(1)
        //  + Time: O(n)
        //
//        System.out.println(minMovesWrong(rooks));
//        System.out.println(minMovesFix(rooks));
        System.out.println(minMoves(rooks));
        //
        //#Reference:
        //782. Transform to Chessboard
        //947. Most Stones Removed with Same Row or Column
        //
    }
}
