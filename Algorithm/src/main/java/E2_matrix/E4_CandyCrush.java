package E2_matrix;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class E4_CandyCrush {

    public static int[] dx={-1,0,1,0};
    public static int[] dy={0,1,0,-1};

    public static class Node{
        int x;
        int y;
        int[] value;
        public Node(int x, int y, int[] value){
            this.x=x;
            this.y=y;
            this.value=value;
        }
    }

    public static boolean crush(List<int[]>[] candies, boolean[][][] visited, int n, int m){
        List<Node>[] removedElement=new ArrayList[m];
        //- Duyệt từ bottom up
        //- Ta sẽ phân 2 chiều:
        //  + Vertical: dx[]=-1/1
        //  + Horizontal : dy[]=-1/1
        for(int i=n-1;i>=0;i--){
            for(int j=0;j<m;j++){
                List<int[]> curList=candies[j];
                if(curList.size()<=i){
                    continue;
                }
//                int size=curList.size();
                int[] curNode=curList.get(i);
                if(curNode.length==0){
                    continue;
                }
                int curVal=curNode[0];

                for(int h=0;h<dx.length;h++){
                    int x1=i+dx[h];
                    int y1=j+dy[h];
                    int direction=dx[h]==1||dx[h]==-1?0:1;
                    boolean isValid=false;
                    List<Node> temp=new ArrayList<>();

                    while(x1>=0&&y1>=0&&y1<m&&x1<candies[y1].size()&&!visited[x1][y1][direction]
                            &&candies[y1].get(x1).length>0
                            &&candies[y1].get(x1)[0]==curVal){
                        temp.add(new Node(x1, y1, candies[y1].get(x1)));
                        isValid=true;
                        x1=x1+dx[h];
                        y1=y1+dy[h];
                    }
                    if(isValid&&temp.size()>=2){
                        if(removedElement[j]==null){
                            removedElement[j]=new ArrayList<>();
                        }
                        removedElement[j].add(0, new Node(i, j, curNode));
                        for(Node e: temp){
//                            System.out.printf("%s %s\n", j, e[1]);
                            if(removedElement[e.y]==null){
                                removedElement[e.y]=new ArrayList<>();
                            }
                            removedElement[e.y].add(e);
                            visited[e.x][e.y][direction]=true;
                        }
                    }
                }
            }
        }
        boolean isStable=true;

        for(int i=0;i<m;i++){
            List<Node> curList=removedElement[i];

            if(curList==null){
                continue;
            }
            for(Node e: curList){
                visited[e.x][e.y][0]=false;
                visited[e.x][e.y][1]=false;
                if(candies[i].remove(e.value)){
                    candies[i].add(new int[]{});
                }
//                System.out.printf("Remove :%s %s\n", e.x, e.y);
                isStable=false;
            }
        }
        return isStable;
    }


    public static int[][] candyCrush(int[][] board) {
        int n=board.length;
        int m=board[0].length;

        List<int[]>[] candies=new ArrayList[m];
        for(int i=0;i<m;i++){
            candies[i]=new ArrayList<>();
            for(int j=n-1;j>=0;j--){
                candies[i].add(new int[]{board[j][i]});
            }
        }
        boolean[][][] visited=new boolean[n][m][2];

        //Processing
        while(!crush(candies, visited, n, m)){
        }
        int[][] rs=new int[n][m];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                List<int[]> curList=candies[j];
                if(curList.size()<=n-i-1||candies[j].get(i).length==0){
                    rs[n-i-1][j]=0;
                    continue;
                }
                rs[n-i-1][j]=candies[j].get(i)[0];
            }
        }
        return rs;
    }

    public static void println(int[][] rs){
        int m=rs[0].length;

        for (int[] r : rs) {
            for (int j = 0; j < m; j++) {
                System.out.printf("%s ", r[j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given the matrix
        //- Rule :
        //+ Trò chơi candy crush ta sẽ crush (phá huỷ) tất cả các viên kẹo giống loại kề nhau theo chiều (vertical/ horizontal)
        //  + Phải ít nhất 3 candy mới được crushed
        //  + Khi crush thì ta sẽ crush at the same time
        //+ Các cell bị crush =0
        //  + Các cell khác có thể drop qua cell =0 được (empty)
        //+ Các candies sẽ được crush cùng lúc --> Chứ không lần lượt
        //* return the board when it is stable
        //==> Không thể crush được nữa.
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //m == board.length
        //n == board[i].length
        //3 <= m, n <= 50
        //1 <= board[i][j] <= 2000
        //
        //- Brainstorm
        //- Nếu ta mô phỏng drop của cell ==> nên chạy từ bottom lên top
        //  + Vì nó sẽ drop từ element bottom trước ==> top
        //
        //- Simple idea
        //Ta chia thành 2 operations:
        //+ Crush operation
        //+ Drop operation
        //- Idea operations:
        //- Crush operation
        //  + Mỗi cell đi theo 4 direction --> Khá tệ
        //      - Dùng visited[][][2] : 0(vetical)/1 (horizontal) để:
        //      + Đánh phần tử đó sẽ crush rồi theo chiều (vertical/ horizontal)
        //      + Nếu mà 1 trong 2 phía đã đánh dấu ==>Ta không đi theo chiều đó nữa.
        //  --> Sau khi tối ưu : Ta có thể 4 directions bình thường
        //
        //- Điều thứ 2
        //+ Moving phần tử trong array --> Có time complexity khá lớn
        //==> Dùng list.
        //
        //- Mỗi col sẽ phân thành 1 list
        //  + Ta sẽ duyệt all list ==> Không xoá ngay
        //  + Ta vẫn sẽ cần visited
        //      + Ta sẽ đánh dấu các node cần remove ==> Có thể sẽ add vào list luôn theo dạng Node(x,y) để có thể traverse nhanh hơn.
        //
        int[][] board =
                {
                        {110,5,112,113,114},
                        {210,211,5,213,214},
                        {310,311,3,313,314},
                        {410,411,412,5,414},
                        {5,1,512,3,3},
                        {610,4,1,613,614},
                        {710,1,2,713,714},
                        {810,1,2,1,1},
                        {1,1,2,2,2},
                        {4,1,4,4,1014}};
        int[][] rs=candyCrush(board);
        println(rs);
        //#Reference:
        //2237. Count Positions on Street With Required Brightness
        //2518. Number of Great Partitions
        //1912. Design Movie Rental System
    }
}
