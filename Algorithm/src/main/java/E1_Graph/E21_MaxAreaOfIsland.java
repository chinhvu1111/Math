package E1_Graph;

import java.util.*;

public class E21_MaxAreaOfIsland {

    public static int[] dx={-1,0,1,0};
    public static int[] dy={0,1,0,-1};
    public static int solution(int[][] grid){
        int n=grid.length;
        int m=grid[0].length;
        List<int[]> listIslandCells=new ArrayList<>();

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1){
                    listIslandCells.add(new int[]{i, j});
//                    System.out.printf("Init: %s %s\n", i, j);
                }
            }
        }
        int rs=0;

        for(int[] nodes: listIslandCells){
            if(grid[nodes[0]][nodes[1]]==1){
                grid[nodes[0]][nodes[1]]=0;
            }else{
                continue;
            }
            Queue<int[]> currentNodes=new LinkedList<>();
            currentNodes.add(nodes);
            int currentRs=1;

            while (!currentNodes.isEmpty()){
                int[] currentNode=currentNodes.poll();
//                grid[currentNode[0]][currentNode[1]]=0;
//                System.out.printf("Node: %s %s\n", currentNode[0], currentNode[0]);

                for(int i=0;i<dx.length;i++){
                    int x1=currentNode[0]+dx[i];
                    int y1=currentNode[1]+dy[i];
//                    System.out.printf("Node: %s %s %s\n", x1, y1, visited[x1][y1]);

                    if(x1>=0&&x1<n&&y1>=0&&y1<m&&grid[x1][y1]==1){
                        currentNodes.add(new int[]{x1, y1});
                        grid[x1][y1]=0;
                        currentRs++;
                    }
                }
            }
            rs=Math.max(rs, currentRs);
        }
        return rs;
    }

    public static class Node{
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }
    }

    public static String findRoot(String currentKey, HashMap<String, String> mapRoot){
        String prevKey=currentKey;
        while (!currentKey.equals(mapRoot.get(currentKey))){
            currentKey=mapRoot.get(currentKey);
        }
        mapRoot.put(prevKey, currentKey);
        return currentKey;
    }

    public static int unionFind(String currentNode, String prevKey, HashMap<String, String> mapRoot, HashMap<String, Integer> mapCount){
        String currentRoot=findRoot(currentNode, mapRoot);
        String prevRoot=findRoot(prevKey, mapRoot);
        if(currentRoot.equals(prevRoot)){
            return -1;
        }

        if(!mapCount.containsKey(currentRoot)){
            mapRoot.put(currentRoot, prevRoot);
            mapRoot.put(currentNode, prevRoot);
            mapCount.put(prevRoot, mapCount.get(prevRoot)+1);
            return mapCount.get(prevKey)+1;
        }else if(mapCount.containsKey(prevRoot)&&mapCount.get(prevRoot)>=mapCount.get(currentRoot)){
            mapRoot.put(currentRoot, prevRoot);
            mapRoot.put(currentNode, prevRoot);
            int newValue=mapCount.get(prevRoot)+mapCount.get(currentRoot);
            mapCount.put(prevRoot, newValue);
            return newValue;
        }else if(mapCount.containsKey(prevRoot)&&mapCount.get(prevRoot)<mapCount.get(currentRoot)){
            int newValue=mapCount.get(prevRoot)+mapCount.get(currentRoot);
            mapCount.put(currentRoot, newValue);
            mapRoot.put(prevKey, currentRoot);
            mapRoot.put(prevRoot, currentRoot);
            return newValue;
        }else{
            mapRoot.put(currentRoot, prevRoot);
            mapRoot.put(currentNode, prevRoot);
            mapCount.put(currentRoot, 2);
            return 2;
        }
    }

    public static int solutionDp(int[][] grid){
        int n=grid.length;
        int m=grid[0].length;
        HashMap<String, String> mapRoot=new HashMap<>();
        HashMap<String, Integer> mapCount=new HashMap<>();
        int rs=0;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]!=1){
                    continue;
                }
                //- Cần tính currentNode theo các node khác
                String currentNode=i+" "+j;
                if(!mapRoot.containsKey(currentNode)){
                    mapRoot.put(currentNode, currentNode);
                    mapCount.put(currentNode, 1);
                    rs=Math.max(rs, 1);
                }
                for(int h=0;h<dx.length;h++){
                    int x1=i+dx[h];
                    int y1=j+dy[h];
//                    System.out.printf("Node: %s %s, %s\n", x1, y1, mapRoot);
                    System.out.println(mapCount);

                    if(x1>=0&&x1<n&&y1>=0&&y1<m&&grid[x1][y1]==1){
                        String prevNode=x1+" "+y1;

                        if(mapRoot.containsKey(prevNode)){
//                            System.out.printf("%s %s\n", mapRoot.get(prevNode), x1+" "+y1);
//                            if(true){
//                                return 1;
//                            }
                            int currentRs=unionFind(currentNode, prevNode, mapRoot, mapCount);
                            if(currentRs==-1){
                                continue;
                            }
                            rs=Math.max(rs, currentRs);
                        }
                    }
                }
            }
        }
        return rs;
    }

    public static int maxAreaOfIslandUnionFind(int[][] grid) {
        return solutionDp(grid);
    }

    public static int solutionDFS(int[][] grid, int x, int y){
        if(x<0||y<0||x>=grid.length||y>=grid[0].length||grid[x][y]==0){
            return 0;
        }
        grid[x][y]=0;
        return 1+ solutionDFS(grid, x-1, y) + solutionDFS(grid,x+1, y)
                + solutionDFS(grid, x, y-1) + solutionDFS(grid, x, y+1);
    }

    public static int maxAreaOfIslandDFS(int[][] grid){
        int n=grid.length;
        int m=grid[0].length;
        int rs=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                rs=Math.max(rs, solutionDFS(grid, i, j));
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //-
        //
        //** Idea
        //1.
        //1.0, Brainstorm
        //- Constraints:
        //+ m == grid.length
        //+ n == grid[i].length
        //+ 1 <= m, n <= 50
        //
        //- Mỗi cell chỉ được phép đi qua 1 lần --> Ta chỉ có thể update count cho 1 phần tử 1 lúc.
        //- Tư duy này dạng update số phần tử trong 1 tập hợp
        //1 1 1 1 1 1
        //1 1 1
        //1 1 1
        //1 1
        //
        //1 1 0 (1) --> sẽ bị sai vì bên dưới chưa tính
        //1 1 1 [1]
        //
        //3 3 0 2
        //3 4 5 2
        //+ Làm như thế nào để 1 tập hợp --> Sẽ có chung 1 count?
        //==> Với tư duy dạng này thì ta sẽ hướng đến việc ==> Tìm root + chỉ tính count cho root đó thôi
        //** --> Ở đây ta sẽ dùng UNION FIND ==> Gộp tất cả vào 1 group
        //+ Root là node nào? top-left node.
        // root(i,j) = (e,f) --> Hash()
        //
        //- Gộp như nào thế nào:
        //+ Giữa (i,j) và (k,l) gộp như thế nào?
        //--> Chọn cái nào có (count lớn hớn thì sẽ là root) của cái count nhở hơn
        //+ Kết hợp nhiều subgraph --> cộng dồn count vào 1 graph
        //
        //1.1, Complexity:
        //- Time complexity : O(N*M)
        //+ N is the number of rows of grid array
        //+ M is the number of columns of grid array
        //
        //- Space complexity : O(N*M)
        //
        //* Method 2:
        //2.
        //2.0,
        //- DFS --> Chỉ đơn giản là đi all nodes mỗi lần traverse loop(N*M)
//        int[][] grid={
//                {1,1,0,0,0},
//                {1,1,0,0,0},
//                {0,0,0,1,1},
//                {0,0,0,1,1}};
//        int[][] grid={
//                {1,1,0,1,0},
//                {1,1,1,1,0},
//                {0,0,0,0,1},
//                {0,0,0,1,1}};
        //(3,8), (4,8), (4,9), (4,10), (3,10), (5,10)
        int[][] grid={
                {0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}};
//        int[][] grid={{1}};
        System.out.println(maxAreaOfIslandUnionFind(grid));
        System.out.println(maxAreaOfIslandDFS(grid));
        //#Reference:
        //417. Pacific Atlantic Water Flow
        //463. Island Perimeter
        //1727. Largest Submatrix With Rearrangements
        //2101. Detonate the Maximum Bombs
    }
}
