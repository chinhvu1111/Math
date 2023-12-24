package E1_Graph;

import javafx.util.Pair;

import java.util.*;

public class E42_NumberOfDistinctIslands {

    //up, right, down, left
    public static int[] dx={-1,0,1,0};
    public static int[] dy={0,1,0,-1};

    public static String hashFunc1(int[][] numOneRow, int[][] numOneCol, int minX, int minY, int maxX, int maxY, int n, int m, HashSet<String> traversedNodes){
        //a b
        //c d
        //a(minX, minY)
        //b(minX, maxY)
        //c(maxX, minY)
        //d(maxX, maxY)
        StringBuilder hashStr=new StringBuilder();

        for(int i=minX;i<=maxX;i++){
            if(minY>0){
                hashStr.append(numOneCol[i][maxY]-numOneCol[i][minY-1]).append("|");
            }else{
                hashStr.append(numOneCol[i][maxY]).append("|");
            }
        }
        for(int i=minY;i<=maxY;i++){
            if(minX>0){
                hashStr.append(numOneRow[maxX][i]-numOneRow[minX-1][i]).append("|");
            }else{
                hashStr.append(numOneRow[maxX][i]).append("|");
            }
        }
        return hashStr.toString();
    }

    public static String hashFunc1(List<int[]> grid){
        //a b
        //c d
        //a(minX, minY)
        //b(minX, maxY)
        //c(maxX, minY)
        //d(maxX, maxY)
        StringBuilder hashStr=new StringBuilder();

        for(int[] e: grid){
            hashStr.append(e[0]).append(e[1]);
        }
        return hashStr.toString();
    }

    public static int numDistinctIslandsWrong(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        Queue<int[]> nodes=new LinkedList<>();
        boolean[][] visited=new boolean[n][m];
        HashSet<String> rs=new HashSet<String>();

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(visited[i][j]||grid[i][j]!=1){
                    continue;
                }
                nodes.add(new int[]{i, j});
                //Tìm node ở 3 góc
                //a b
                //c d
                //a : x min ,y min
                //b : x min, y max
                //c : x max, y min.
                //d : x max, y max
//                int[] a=new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
//                int[] b=new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
//                int[] c=new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE};
//                int[] d=new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
                List<int[]> list=new ArrayList<>();

                while(!nodes.isEmpty()){
                    int[] curNode=nodes.poll();
                    visited[curNode[0]][curNode[1]]=true;
                    list.add(new int[]{curNode[0], curNode[1]});

                    for(int h=0;h<dx.length;h++){
                        int x1=curNode[0]+dx[h];
                        int y1=curNode[1]+dy[h];

                        if(x1>=0&&x1<n&&y1>=0&&y1<m&&!visited[x1][y1]&&grid[x1][y1]==1){
                            nodes.add(new int[]{x1, y1});
                            visited[x1][y1]=true;
                        }
                    }
                }
                Collections.sort(list, (a, b) -> {
                    if(a[0]!=b[0]){
                        return a[0]-b[0];
                    }
                    return a[1]-b[1];
                });
//                System.out.printf("%s %s %s %s\n", minX, minY, maxX, maxY);
//                String curHash=hashFunc(numOneRow, numOneCol, minX, minY, maxX,maxY, n, m);
                String curHash=hashFunc1(list);
                rs.add(curHash);
            }
        }
        List<String> list=new ArrayList<>(rs);
        list.sort(Collections.reverseOrder());
        HashSet<String> set=new HashSet<>();
        int count=0;

        for(String s: list){
            if(!set.contains(s)){
                count++;
            }
            System.out.printf("%s, count=%s\n",s, count);
            set.add(s);
        }

        return rs.size();
    }

    public static String hashFunc(int[][] grid, int minX, int minY, int maxX, int maxY, int n, int m, HashSet<String> traversedNodes){
        //a b
        //c d
        //a(minX, minY)
        //b(minX, maxY)
        //c(maxX, minY)
        //d(maxX, maxY)
        StringBuilder hashStr=new StringBuilder();

        for(int i=minX;i<=maxX;i++){
            for(int j=minY;j<=maxY;j++){
                if(traversedNodes.contains(i+" "+j)){
                    hashStr.append(grid[i][j]);
                }else{
                    hashStr.append(0);
                }
            }
            hashStr.append("|");
        }
        return hashStr.toString();
    }
    public static int numDistinctIslandsSlow(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        Queue<int[]> nodes=new LinkedList<>();
        //Time : O(N*M)
        boolean[][] visited=new boolean[n][m];
        HashSet<String> rs=new HashSet();

        //Time : O(N)
        for(int i=0;i<n;i++){
            //Time : O(M)
            for(int j=0;j<m;j++){
                if(visited[i][j]||grid[i][j]!=1){
                    continue;
                }
                HashSet<String> traversedNodes=new HashSet<>();
                nodes.add(new int[]{i, j});
                //Tìm node ở 3 góc
                //a b
                //c d
                //a : x min ,y min
                //b : x min, y max
                //c : x max, y min.
                //d : x max, y max
//                int[] a=new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
//                int[] b=new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
//                int[] c=new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE};
//                int[] d=new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
                int minX=Integer.MAX_VALUE, minY=Integer.MAX_VALUE, maxX=Integer.MIN_VALUE, maxY=Integer.MIN_VALUE;

                //Time : O(N)
                while(!nodes.isEmpty()){
                    int[] curNode=nodes.poll();
                    traversedNodes.add(curNode[0]+" "+curNode[1]);
                    visited[curNode[0]][curNode[1]]=true;
                    minX=Math.min(minX, curNode[0]);
                    minY=Math.min(minY, curNode[1]);
                    maxX=Math.max(maxX, curNode[0]);
                    maxY=Math.max(maxY, curNode[1]);

                    for(int h=0;h<dx.length;h++){
                        int x1=curNode[0]+dx[h];
                        int y1=curNode[1]+dy[h];

                        if(x1>=0&&x1<n&&y1>=0&&y1<m&&!visited[x1][y1]&&grid[x1][y1]==1){
                            nodes.add(new int[]{x1, y1});
                            visited[x1][y1]=true;
                        }
                    }
                }
//                System.out.printf("%s %s %s %s\n", minX, minY, maxX, maxY);
//                String curHash=hashFunc(numOneRow, numOneCol, minX, minY, maxX,maxY, n, m);
                //Time : O(1) --> O(N*M)
                //+ Nếu O(1) thì chạy N*M lần
                //+ Nếu O(N*M) thì chạy 1 lần
                String curHash=hashFunc(grid, minX, minY, maxX,maxY, n, m, traversedNodes);
                System.out.println(curHash);
                rs.add(curHash);
            }
        }
        List<String> list=new ArrayList<>(rs);
        list.sort(Collections.reverseOrder());
        HashSet<String> set=new HashSet<>();
        int count=0;

        for(String s: list){
            if(!set.contains(s)){
                count++;
            }
            System.out.printf("%s, count=%s\n",s, count);
            set.add(s);
        }

        return rs.size();
    }

//    public static int numDistinctIslands(int[][] grid) {
//        int n=grid.length;
//        int m=grid[0].length;
//        //Cố định col
//        int[][] numOneRow=new int[n][m];
////        //Cố định row
//        int[][] numOneCol=new int[n][m];
////
//        for(int i=0;i<n;i++){
//            int numOneVal=0;
//
//            for(int j=0;j<m;j++){
//                if(grid[i][j]==1){
//                    numOneVal++;
//                }
//                numOneCol[i][j]=numOneVal;
//            }
//        }
//        for(int i=0;i<m;i++){
//            int numOneVal=0;
//
//            for(int j=0;j<n;j++){
//                if(grid[j][i]==1){
//                    numOneVal++;
//                }
//                numOneRow[j][i]=numOneVal;
//            }
//        }
////        for(int i=0;i<n;i++){
////            for(int j=0;j<m;j++){
////                System.out.printf("%s, ", numOneCol[i][j]);
////            }
////            System.out.println();
////        }
////        System.out.println();
////        for(int i=0;i<n;i++){
////            for(int j=0;j<m;j++){
////                System.out.printf("%s, ", numOneRow[i][j]);
////            }
////            System.out.println();
////        }
////        System.out.println();
//        Queue<int[]> nodes=new LinkedList<>();
//        boolean[][] visited=new boolean[n][m];
//        HashSet<String> rs=new HashSet<String>();
//
//        for(int i=0;i<n;i++){
//            for(int j=0;j<m;j++){
//                if(visited[i][j]||grid[i][j]!=1){
//                    continue;
//                }
//                HashSet<String> traversedNodes=new HashSet<>();
//                nodes.add(new int[]{i, j});
//                //Tìm node ở 3 góc
//                //a b
//                //c d
//                //a : x min ,y min
//                //b : x min, y max
//                //c : x max, y min.
//                //d : x max, y max
////                int[] a=new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
////                int[] b=new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
////                int[] c=new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE};
////                int[] d=new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
//                int minX=Integer.MAX_VALUE, minY=Integer.MAX_VALUE, maxX=Integer.MIN_VALUE, maxY=Integer.MIN_VALUE;
//
//                while(!nodes.isEmpty()){
//                    int[] curNode=nodes.poll();
//                    traversedNodes.add(curNode[0]+" "+curNode[1]);
//                    visited[curNode[0]][curNode[1]]=true;
//                    minX=Math.min(minX, curNode[0]);
//                    minY=Math.min(minY, curNode[1]);
//                    maxX=Math.max(maxX, curNode[0]);
//                    maxY=Math.max(maxY, curNode[1]);
//
//                    for(int h=0;h<dx.length;h++){
//                        int x1=curNode[0]+dx[h];
//                        int y1=curNode[1]+dy[h];
//
//                        if(x1>=0&&x1<n&&y1>=0&&y1<m&&!visited[x1][y1]&&grid[x1][y1]==1){
//                            nodes.add(new int[]{x1, y1});
//                            visited[x1][y1]=true;
//                        }
//                    }
//                }
////                System.out.printf("%s %s %s %s\n", minX, minY, maxX, maxY);
////                String curHash=hashFunc(numOneRow, numOneCol, minX, minY, maxX,maxY, n, m);
//                //==> Phần này nếu traverse bình thường thì sẽ không được.
//                String curHash=hashFunc1(numOneRow, numOneCol, minX, minY, maxX, maxY, n, m, traversedNodes);
//                rs.add(curHash);
//            }
//        }
//        List<String> list=new ArrayList<>(rs);
//        list.sort(Collections.reverseOrder());
//        HashSet<String> set=new HashSet<>();
//        int count=0;
//
//        for(String s: list){
//            if(!set.contains(s)){
//                count++;
//            }
//            System.out.printf("%s, count=%s\n",s, count);
//            set.add(s);
//        }
//
//        return rs.size();
//    }

    public int numDistinctIslands(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;

        Set<Set<Pair<Integer, Integer>>> uniqueIsland=new HashSet<>();
        boolean[][] visited=new boolean[n][m];
        Queue<int[]> nodes=new LinkedList<>();

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(visited[i][j]||grid[i][j]!=1){
                    continue;
                }
                nodes.add(new int[]{i, j});
                int minX=Integer.MAX_VALUE, minY=Integer.MAX_VALUE;
                Set<Pair<Integer, Integer>> curIsland=new HashSet<>();
                Set<Pair<Integer, Integer>> curMovedIsland=new HashSet<>();

                //Time : O(N)
                while(!nodes.isEmpty()){
                    int[] curNode=nodes.poll();
                    visited[curNode[0]][curNode[1]]=true;
                    minX=Math.min(minX, curNode[0]);
                    minY=Math.min(minY, curNode[1]);
                    curIsland.add(new Pair<>(curNode[0], curNode[1]));

                    for(int h=0;h<dx.length;h++){
                        int x1=curNode[0]+dx[h];
                        int y1=curNode[1]+dy[h];

                        if(x1>=0&&x1<n&&y1>=0&&y1<m&&!visited[x1][y1]&&grid[x1][y1]==1){
                            nodes.add(new int[]{x1, y1});
                            visited[x1][y1]=true;
                        }
                    }
                }
                for(Pair<Integer, Integer> e: curIsland){
                    curMovedIsland.add(new Pair<>(e.getKey()-minX , e.getValue()-minY));
                }
                uniqueIsland.add(curMovedIsland);
            }
        }
        return uniqueIsland.size();
    }

    public int numDistinctIslandsOptimization(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;

        Set<Set<Pair<Integer, Integer>>> uniqueIsland=new HashSet<>();
        boolean[][] visited=new boolean[n][m];
        Queue<int[]> nodes=new LinkedList<>();

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(visited[i][j]||grid[i][j]!=1){
                    continue;
                }
                nodes.add(new int[]{i, j});
                Set<Pair<Integer, Integer>> curIsland=new HashSet<>();
                //Time : O(N)
                while(!nodes.isEmpty()){
                    int[] curNode=nodes.poll();
                    visited[curNode[0]][curNode[1]]=true;
                    curIsland.add(new Pair<>(curNode[0]-i, curNode[1]-j));

                    for(int h=0;h<dx.length;h++){
                        int x1=curNode[0]+dx[h];
                        int y1=curNode[1]+dy[h];

                        if(x1>=0&&x1<n&&y1>=0&&y1<m&&!visited[x1][y1]&&grid[x1][y1]==1){
                            nodes.add(new int[]{x1, y1});
                            visited[x1][y1]=true;
                        }
                    }
                }
                uniqueIsland.add(curIsland);
            }
        }
        return uniqueIsland.size();
    }

    public static int numDistinctIslandsHashStr(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        Set<String> uniqueIsland=new HashSet<>();
        boolean[][] visited=new boolean[n][m];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(visited[i][j]||grid[i][j]!=1){
                    continue;
                }
                StringBuilder curPath=new StringBuilder();
                dfs(i, j,'0', n, m, curPath, visited, grid);
                uniqueIsland.add(curPath.toString());
            }
        }
        return uniqueIsland.size();
    }

    public static void dfs(int x, int y, char dir, int n, int m, StringBuilder path, boolean[][] visited, int[][] grid){
        if(x<0||x>=n||y<0||y>=m||visited[x][y]|| grid[x][y] == 0){
            return;
        }
        path.append(dir);
        visited[x][y]=true;
        dfs(x-1, y, 'U', n, m, path, visited, grid);
        dfs(x+1, y, 'D', n, m, path, visited, grid);
        dfs(x, y+1, 'R', n, m, path, visited, grid);
        dfs(x, y-1, 'L', n, m, path, visited, grid);
        path.append("0");
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given the mxn binary matrix.
        //- An Island is a group of 1 connected 4 directionally (horizontal or vertical)
        //- An island is considered to be the (same) as another if and only if one island can be translated (and not rotated or reflected) to equal the other.
        //* Return the number of distinct islands.
        //Ex:
        //1 1 vs 1 1
        //1      1
        //They are equal
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //m == grid.length
        //n == grid[i].length
        //1 <= m, n <= 50
        //grid[i][j] is either 0 or 1.
        //
        //- Brainstorm
        //- Bài này dùng hashtable/ hashfunction để tìm distinct
        //Ex:
        //grid =
        // [
        // [1,1,0,0,0],
        // [1,1,0,0,0],
        // [0,0,0,1,1],
        // [0,0,0,1,1]
        // ]
        //- Bài toán con --> So sánh xem 2 ma trận khác nhau không
        //Ex:
        //1 1 vs 1 1
        //1      1 1
        //- Size matrix != ==> Khác nhau
        //- Build hash function?
        //- Đặc điểm của matrix:
        //  + Size : NxM
        //  + The number of 1 value
        //- How to identify:
        //1 1 vs 1 1
        //1 0    0 1
        //+ Size: 2x2
        //+ Ta có thể phân biệt được theo số lượng chữ số 1 trên từng column or từng row 
        //+ M1:  row(2,1)
        //+ M2:  row(2,1)
        //+ M1:  colum (2,1)
        //+ M2:  column(1,2)
        //
        //1 1 vs 1 1
        //1 0    0 1
        //1 0    1 0
        //- Một vùng sẽ bao gồm 4 điểm:
        //+ minX
        //+ minY
        //+ maxX
        //+ maxY
        //
        //--> Nếu dùng cách này thì sẽ bị collision.
        //- Nếu hashfunc= concat all (0,1) từ vị trí (minX, minY) => (maxX, maxY)
        //==> Thì bị case return 67 nhưng lại return 68.
        //  + Tại sao lại bị thế:
        //      ==> Nếu tính min, max như thế này thì các giá trị mà không thuộc tập hợp =0/1 cũng bị add vào có thể gây sai
        //      Ex:
        //      1 0 0 1
        //      0 0 0 1
        //      1 1 1 1
        //      => 1 không thuộc ở cell đầu tiên không hề thuộc collection
        //      Ex:
        //      1 1 0 1
        //      0 0 0 1
        //      1 1 1 1
        //      ==> bên trên và cái này là 2 hash function khác nhau : Mặc dù chỉ có 1 value là
        //      0 0 0 1
        //      1 1 1 1
        //      --> Chỉ được điền những giá trị trong tập hợp thôi
        //- Liệu có thể add vào list sau đó sort theo (x, y) thì ta có thể concat all values của chúng được không?
        //+ Không được vì ta sẽ thiếu dấu xuống dòng nên cần phải tính làm sao để có thể concat được cả "\n"
        //--> Ta sẽ add các node traversed trong 1 turn vào hashset => Sau đó sẽ concat để bỏ các nodes mà không có trong tập hợp đi là được.
        //
        //- Nếu ta dùng cách sum các row, col theo column/ row
        //  + Ta sẽ tính sum row, col trước
        //  + Thì kết quả sẽ không đúng vì có 1 số grid[i][j] sẽ không được tính vào ==> Phần này sẽ bị slow.
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Time : O(2*N+M)
        //- Space: O(N*M)
        //
        //2.
        //- Đặc điểm hình dạng của 1 area trong matrix:
        //  + Ta có thể move area đó sang góc trái = cách trừ đều đi minX và minY
        //  + Nếu ta dùng (DFS/BFS) để traverse left, right ==> Nó sẽ cho hình dạng giống nhau khi traverse
        //      + Điểm đầu tiên sẽ là điểm trên cùng sau đó --> Traverse left, right, bottom (Không có top)
        //- Để check xem current island có unique hay không:
        //  - Đổi view:
        //      + Chuyển từ value (0/1) --> List of nodes int[]{x,y}
        //      --> Vì (same order) + (move về top-left) nên việc so sánh sẽ là loop all các nodes khác để compare
        //- Cache lại phần compare --> Add the pair(x,y)
        //  + hashCode của 2 object cùng loại + cùng value ==> Equal
        //
        //2.1, Optimization
        //- Nếu trừ minX và minY ==> Ta sẽ phải tìm minX, minY
        //  ==> nếu ta sẽ chỉ move hệ quy chiếu với start_node thì sao?
        //  + Nó sẽ chỉ (-x_start, -y_start) mà không cần loop để tìm minX, minY.
        //  + Move hệ quy chiếu về 1 đỉnh thì sẽ vẫn đúng ==> View về start point.
        //
        //2.2, Complexity:
        //- Space : O(N*M)
        //- Time : O(N*M)
        //
        //3. Hash theo traverse path
        //3.0, Idea
        //- Vì ta vừa rút ra việc path sẽ giống nhau nếu traverse bằng DFS/ BFS ==> thế nên ta có thể dùng string để lưu vết
        //==> Hashset được.
        //- Cần lưu path theo :
        //  + D: down
        //  + U : up
        //  + R : right
        //  + L : left
        //- Có lưu theo value 0,1 được không?
        //  + Không
        //  Nếu lưu theo value 0/1 --> Thì không thể hiện được hình dạng
        //
        //- Ở đây ta không dùng BFS được ==> chỉ dùng được DFS.
        //- Ta sẽ chia thành 4 directions nhưng sẽ có cases đặc biệt như:
        //Ex:
        // 1 1 0
        // 0 1 0
        // 0 1 0
        // 0 1 1 -> R
        //=> curRs= RDDDR ==> RDDDR
        // 1 1 0
        // 0 1 0
        // 0 1 1 -> R
        // 0 1 0 -> Cuối con đường (Tức là đi mọi directions rồi) ==> thêm 0
        //=> curRs= RDDDR ==> RDDD0R
        // 1 1 0
        // 0 1 1 -> R
        // 0 1 0 -> Cuối con đường (Tức là đi mọi directions rồi) ==> thêm 0
        // 0 1 0 -> Cuối con đường (Tức là đi mọi directions rồi) ==> thêm 0
        //=> curRs= RDDDR ==> RDDD00R
        //--> Thể nên mỗi khi run đến hết ta cần phải append thêm ("0") để phân biệt các cases với nhau.
        //
        //3.1, Optimization
        //3.2, Complexity:
        //- Space : O(N*M)
        //- Time : O(N*M)
        //
        int[] arr=new int[]{1,3};
        int[] arr1=new int[]{1,3};
        //Compare thử hashCode của 2 arrays int[]
        System.out.println(Objects.equals(Arrays.hashCode(arr), Arrays.hashCode(arr1)));
        int[][] grid = {
                {1,1,0,1,1},
                {1,0,0,0,0},
                {0,0,0,0,1},
                {1,1,0,1,1}};
        System.out.println(numDistinctIslandsSlow(grid));
        System.out.println(numDistinctIslandsHashStr(grid));
        //#Reference:
        //711. Number of Distinct Islands II
        //1905. Count Sub Islands
    }
}
