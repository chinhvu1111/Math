package interviews;

import java.util.LinkedList;
import java.util.Queue;

public class E116_CheckIfThereIsAValidPathInAGrid {

//    public static int dx[][]=new int[][]{{0,0}, {2, -2}, {1, -1}, {-1, 1}, {1, -1}, {1, -1}};
//    public static int dy[][]=new int[][]{{2,-2}, {0, 0}, {1, -1}, {1, -1}, {-1, 1}, {1, -1}};

    public static int dxDir[]=new int[]{-1, 0, 1, 0};
    public static int dyDir[]=new int[]{0, 1, 0, -1};

    public static class Node{
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static boolean hasValidPathWrong(int[][] grid) {
        Queue<Node> integers=new LinkedList<>();
        int n=grid.length;
        int m=grid[0].length;
        boolean[][] visited =new boolean[n][m];

//        if(grid[0][0]==3||grid[0][0]==1){
//            integers.add(new Node(0, -1));
//        }
//        if(grid[0][0]==2||grid[0][0]==6){
//            integers.add(new Node(-1, 0));
//        }

        integers.add(new Node(0, 0));

//        while (!integers.isEmpty()){
//            Node currentNode=integers.poll();
//
//            for(int i=0;i<dxDir.length;i++){
//                int x1=currentNode.x + dxDir[i];
//                int y1=currentNode.y + dyDir[i];
//
//                if(x1>=0&&x1<n&&y1>=0&&y1<m&&!visited[x1][y1]){
//                    for(int j=0;j<dx[grid[x1][y1]-1].length;j++){
//                        int x2=currentNode.x+dx[grid[x1][y1]-1][j];
//                        int y2= currentNode.y+dy[grid[x1][y1]-1][j];
//
//                        if(x2>=n-1&&y2>=m-1){
//                            return true;
//                        }
//                        if(x2>=0&&x2<n&&y2>=0&&y2<m&&!visited[x2][y2]){
//                            integers.add(new Node(x2, y2));
//                            visited[x2][y2]=true;
//                            visited[x1][y1]=true;
//                        }
//                    }
//                }
//            }
//        }
        return false;
    }

    public static int dValid[][][]=new int[][][]{
            {{},{1,3,5},{},{1,4,6}},
            {{2,3,4},{},{2,5,6},{}},
            {{},{},{2,5,6},{1,4,6}},
            {{},{1,3,5},{2,5,6},{}},
            {{2,3,4},{},{},{1,4,6}},
            {{2,3,4},{1,3,5},{},{}}
    };

    public static boolean hasValidPath(int[][] grid) {
        Queue<Node> integers=new LinkedList<>();
        int n=grid.length;
        int m=grid[0].length;

        if(n-1==0&&m-1==0){
            return true;
        }
        boolean[][] visited =new boolean[n][m];

//        if(grid[0][0]==3||grid[0][0]==1){
//            integers.add(new Node(0, -1));
//        }
//        if(grid[0][0]==2||grid[0][0]==6){
//            integers.add(new Node(-1, 0));
//        }

        integers.add(new Node(0, 0));

        while (!integers.isEmpty()){
            Node currentNode=integers.poll();
            int currentBridge=grid[currentNode.x][currentNode.y];

            for(int i=0;i<dxDir.length;i++){
                int x1=currentNode.x + dxDir[i];
                int y1=currentNode.y + dyDir[i];

                if(x1>=0&&x1<n&&y1>=0&&y1<m&&!visited[x1][y1]){
                    int nextBridge=grid[x1][y1];

                    for(int j=0;j<dValid[currentBridge-1][i].length;j++){
                        if(dValid[currentBridge-1][i][j]==nextBridge){
                            if(x1==n-1&&y1==m-1){
                                return true;
                            }
                            integers.add(new Node(x1, y1));
                            visited[x1][y1]=true;
                        }
                    }
                }
            }
        }
        return false;
    }

//    public static Node queue[];
//    public static int front=-1, rear=0, length=0;
//
//    public static Node pop(){
//        Node node=queue[++front];
//        length--;
//        return node;
//    }
//    public static void push(Node node){
//        queue[rear++]=node;
//        length++;
//    }

    public static boolean hasValidPathOptimize(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;

        if(n-1==0&&m-1==0){
            return true;
        }
        Node []queue=new Node[n*m+1];
        boolean[][] visited =new boolean[n][m];
        int front=-1, rear=0, length=0;

//        if(grid[0][0]==3||grid[0][0]==1){
//            integers.add(new Node(0, -1));
//        }
//        if(grid[0][0]==2||grid[0][0]==6){
//            integers.add(new Node(-1, 0));
//        }

        queue[rear++]=new Node(0, 0);
        length++;

        while (length!=0){
            Node currentNode=queue[++front];
            length--;
            int currentBridge=grid[currentNode.x][currentNode.y];

            for(int i=0;i<dxDir.length;i++){
                int x1=currentNode.x + dxDir[i];
                int y1=currentNode.y + dyDir[i];

                if(x1>=0&&x1<n&&y1>=0&&y1<m&&!visited[x1][y1]){
                    int nextBridge=grid[x1][y1];

                    for(int j=0;j<dValid[currentBridge-1][i].length;j++){
                        if(dValid[currentBridge-1][i][j]==nextBridge){
                            if(x1==n-1&&y1==m-1){
                                return true;
                            }
                            queue[rear++]=new Node(x1, y1);
                            length++;
                            visited[x1][y1]=true;
                            //Chỗ này để tránh lặp check mấy cái không cần thiết
                            break;
                        }
                    }
                }
            }
        }
        return false;
    }

    static int d[][]; // to traverse 4 directions
    static int dir[][]; // to know if there is valid route to any of 4 directions from current cell
    public static boolean hasValidPathRefer(int[][] grid) {

        d = new int[][]{{0,-1},{-1,0},{0,1},{1,0}};
        dir =   new int[][]{
                {1,0,1,0},
                {0,1,0,1},
                {1,0,0,1},
                {0,0,1,1},
                {1,1,0,0},
                {0,1,1,0}
        };
        return helper(grid,0,0);
    }
    public static boolean helper(int grid[][],int x,int y){
        if(x==grid.length-1 && y==grid[0].length-1)
            return true;

        int curr = grid[x][y];
        grid[x][y] = -1; // to avoid loops
        for(int i=0;i<4;i++){
            int dx = x + d[i][0]; // new row co-ordinate
            int dy = y + d[i][1]; // new col co-ordinate
            if(dx>=0 && dx<grid.length && dy>=0 && dy<grid[0].length && grid[dx][dy]!=-1){
                // if there is a route from curr cell to next cell
                if(dir[curr-1][i] == dir[grid[dx][dy]-1][(i+2)%4] && dir[curr-1][i] == 1){
                    if(helper(grid,dx,dy))
                        return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        int[][] grid=new int[][]{{2,4,3}, {6,5,2}};
        //Case 1:
        //+ Trường hợp điểm cách quãng, điểm init
        //+ Trường hợp quay lại đường cũ
//        int[][] grid=new int[][]{{1,1,2}};
//        int[][] grid=new int[][]{{1,1,1,1,1,1,3}};
//        int[][] grid=new int[][]{{4,1},{6,1}};
//        int[][] grid=new int[][]{{1}};
        int[][] grid=new int[][]{{1,2,1},{1,2,1}};
//        System.out.println(hasValidPath(grid));
        System.out.println(hasValidPathOptimize(grid));
        //Tối ưu:
        System.out.println(hasValidPathRefer(grid));
        //Bài này tư duy như sau:
        //0, Các cases
        //Case 1:
        //+ Trường hợp điểm cách quãng, điểm init (Hiểu sai)
        //+ Trường hợp quay lại đường cũ --> Các điểm cũ (Không cần quay lại)
        //- int[][] grid=new int[][]{{1,1,2}};
        //1, Bài này gặp những lỗi như sau:
        //1.1, Lỗi liên quan đến đọc đề, chưa xác định được điểm bắt đầu ở đâu:
        //+ Nhầm tưởng là bắt đầu ở ngoài bàn cờ --> Nhầm sang việc nhảy cóc
        //VD: (0, -1) --> gặp (1) (Đường nằm ngang) --> (0, 2)
        //---> Thựa tể là start (0,0) (Ở đó đã có đường nằm ngang rồi) ==> Lúc đó ta quan tâm đến việc (Đường nằm ngang) --> Connect được với
        //Các đường nào (Dọc/ Thẳng...)
        //2, Bài này tư duy theo phương pháp BFS:
        //2.1, Cái quan trọng ở đây là khi nào add vào (queue)
        //- 1 point có thể đi được qua 4 phía (-1,0), (0,1), (1,0),(0,-1)
        //==> lúc đó ta sẽ quyết định (i,j) có thể đi đến các vị trí khác được không bằng cách lưu trước các khả năng có thể xảy ra với
        //từng kiểu bridge
        //==> Ta sẽ dùng arr[][][] để lưu thông tin
        //VD: brigde=1 --> Có thể đi được {{},{1,3,5},{},{1,4,6}}
        //+ index=0 : {}, vị trí top của (i,j)
        //+ index=1 : {1,3,5}, vị trí right của (i,j)
        //+ index=2 : {}, vị trí bottom của (i,j)
        //+ index=3 : {1,4,6}, vị trí left của (i,j)
        //- Sau khi loop all nodes xung quanh (tương ứng index)
        //---> Kiếm tra bridge tiếp có trùng với value đã định nghĩ không
        //OK --> Add queue.
        //
        //3, Tối ưu:
        //3.1, Có thể viết thêm break trong lúc loop all cases (Chỉ cần tìm thấy ok thì không cần check nữa)
        //3.2, Tổ chức việc bridge --> Rest of bridge khác
        //==> Ta có thể dùng arr[i][j; 0-->4 (4 hướng)] để tổ chức :
        //VD : arr[i][j]==1 : tức là (bridge value = i) ==> Có thể quay theo hướng (j)
        //VD: bridge =1 có thể quay left/ right ={0,1,0,1} (Mốc là top, khác 1 chút với đề bên trên, với mốc =left)
        //+ khi current (i,j) --> (i,j+1) (Quay right)
        //Check arr[ grid[i,j]-1 ][1]==1 && arr[ grid[i1,j1] -1 ][3] ==1 (Tức là 2 điểm CÓ THỂ ĐI ĐỐI NGỊCH NHAU LÀ ĐƯỢC)
        //** NOTE: 2 điểm đối nghịch nau là được.
    }
}
