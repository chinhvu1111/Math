package interviews;

import java.util.*;

public class E227_ShortestBridge_bfs {

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

        public int getY() {
            return y;
        }
    }

    public static int[] dx=new int[]{-1, 0, 1, 0};
    public static int[] dy=new int[]{0, 1, 0, -1};

    public static void bfs(int x, int y, List<Node> nodes, int[][] grid,
                    int visited[][], int n, int m, int valueVisited){
        Queue<Node> queue=new LinkedList<>();
        Node node=new Node(x, y);
        queue.add(node);
        nodes.add(node);
        visited[x][y]=valueVisited;
        while (!queue.isEmpty()){
            Node currentNode=queue.poll();
            for(int i=0;i<dx.length;i++){
                int x1=currentNode.getX()+dx[i];
                int y1=currentNode.getY()+dy[i];

                if(x1>=0&&x1<n&&y1>=0&&y1<m&&grid[x1][y1]==1&&visited[x1][y1]==0){
                    Node nextNode=new Node(x1, y1);
                    queue.add(nextNode);
                    visited[x1][y1]=valueVisited;
                    nodes.add(nextNode);
                }
            }
        }
    }

    public static int shortestBridge(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        int[][] visited=new int[n][m];
        List<Node> firstNodes=new ArrayList<>();
        List<Node> secondDodes=new ArrayList<>();
        int count=1;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1&&visited[i][j]==0){
                    if(count==1){
                        bfs(i, j, firstNodes, grid, visited, n, m, count);
                    }else{
                        bfs(i, j, secondDodes, grid, visited, n, m, count);
                    }
                    count++;
                }
            }
        }
        int rsLength=Integer.MAX_VALUE;

        for (Node firstNode : firstNodes) {
            for (Node secondNode : secondDodes) {
                rsLength = Math.min(rsLength,
                        Math.abs(firstNode.getX() - secondNode.getX())
                                + Math.abs(firstNode.getY() - secondNode.getY()) - 1);
            }
        }

        return rsLength;
    }

    public static int shortestBridgeRefactorEvenSlow(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        int[][] visited=new int[n][m];
        int[][] depths=new int[n][m];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1){
                    depths[i][j]=1;
                }else{
                    depths[i][j]=Integer.MAX_VALUE;
                }
            }
        }
        List<Node> firstNodes=new ArrayList<>();
        int count=1;
        Queue<Node> seconds=new LinkedList<>();

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1&&visited[i][j]==0){
                    if(count==1){
                        bfs(i, j, firstNodes, grid, visited, n, m, count);
                    }else{
                        seconds.add(new Node(i, j));
                        visited[i][j]=2;
                        count=-1;
                        break;
                    }
                    count++;
                }
            }
            if(count==-1){
                break;
            }
        }
        //Bfs sẽ bị dính case duyệt đến (next đến thì gán visited luôn)
        //==> Lúc sau poll đỉnh đã có ra --> có thể sẽ bị ignore mất.
        // 1 --> 2, 1--> 3, 1--> 4
        //thì add(2,3,4)
        //Giả sử : 2 --> 3
        //- Khi lấy 2 ra quét --> Nó không thể đến được 3 nữa.
        int currentRs=Integer.MAX_VALUE;
        while (!seconds.isEmpty()){
            Node currentNode=seconds.poll();
            int currentDepth=depths[currentNode.getX()][currentNode.getY()];
//            System.out.printf("%s %s\n", currentNode.getX(), currentNode.getY());
            for(int i=0;i<dx.length;i++){
                int x=currentNode.getX()+dx[i];
                int y=currentNode.getY()+dy[i];

                if(x>=0&&x<n&&y>=0&&y<m&&grid[x][y]==0&&visited[x][y]==0){
                    Node nextNode=new Node(x, y);
                    seconds.add(nextNode);
                    visited[x][y]=2;
                    depths[x][y]=currentDepth+1;
//                    System.out.printf("Connect to: %s %s, visited:%s, depth:%s \n", x, y, visited[x][y], depths[x][y]);
                }else if(x>=0&&x<n&&y>=0&&y<m&&grid[x][y]==1&&visited[x][y]==1){
                    currentRs=Math.min(currentRs, currentDepth-1);
//                    System.out.printf("Connect to target: %s %s, visited:%s, depth:%s \n", x, y, visited[x][y], depths[x][y]);
                }else if(x>=0&&x<n&&y>=0&&y<m&&grid[x][y]==1&&visited[x][y]==0&&currentDepth<=depths[x][y]){
                    Node nextNode=new Node(x, y);
                    seconds.add(nextNode);
                    visited[x][y]=2;
                    depths[x][y]=currentDepth;
//                    System.out.printf("Connect to: %s %s, visited:%s, depth:%s \n", x, y, visited[x][y], depths[x][y]);
                }else if(x>=0&&x<n&&y>=0&&y<m&&visited[x][y]==2&&depths[x][y]>currentDepth+1){
                    Node nextNode=new Node(x, y);
                    seconds.add(nextNode);
                    visited[x][y]=2;
                    depths[x][y]=Math.min(currentDepth+1, depths[x][y]);
                }
            }
        }
        if(currentRs!=Integer.MAX_VALUE){
            return currentRs;
        }

        return 0;
    }

    public static int shortestBridgeOptimization(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        int[][] visited=new int[n][m];
        int[][] depths=new int[n][m];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1){
                    depths[i][j]=1;
                }else{
                    depths[i][j]=Integer.MAX_VALUE;
                }
            }
        }
        List<Node> firstNodes=new ArrayList<>();
        int count=1;
        Queue<Node> seconds=new LinkedList<>();

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1&&visited[i][j]==0){
                    if(count==1){
                        bfs(i, j, firstNodes, grid, visited, n, m, count);
                    }else{
                        seconds.add(new Node(i, j));
                        visited[i][j]=2;
                        count=-1;
                        break;
                    }
                    count++;
                }
            }
            if(count==-1){
                break;
            }
        }
        //Bfs sẽ bị dính case duyệt đến (next đến thì gán visited luôn)
        //==> Lúc sau poll đỉnh đã có ra --> có thể sẽ bị ignore mất.
        // 1 --> 2, 1--> 3, 1--> 4
        //thì add(2,3,4)
        //Giả sử : 2 --> 3
        //- Khi lấy 2 ra quét --> Nó không thể đến được 3 nữa.
        int currentRs=Integer.MAX_VALUE;
        while (!seconds.isEmpty()){
            Node currentNode=seconds.poll();
            int currentDepth=depths[currentNode.getX()][currentNode.getY()];
//            System.out.printf("%s %s\n", currentNode.getX(), currentNode.getY());
            for(int i=0;i<dx.length;i++){
                int x=currentNode.getX()+dx[i];
                int y=currentNode.getY()+dy[i];

                if(x>=0&&x<n&&y>=0&&y<m&&grid[x][y]==0&&visited[x][y]==0){
                    Node nextNode=new Node(x, y);
                    seconds.add(nextNode);
                    visited[x][y]=2;
                    depths[x][y]=currentDepth+1;
//                    System.out.printf("Connect to: %s %s, visited:%s, depth:%s \n", x, y, visited[x][y], depths[x][y]);
                }else if(x>=0&&x<n&&y>=0&&y<m&&grid[x][y]==1&&visited[x][y]==1){
                    currentRs=Math.min(currentRs, currentDepth-1);
//                    System.out.printf("Connect to target: %s %s, visited:%s, depth:%s \n", x, y, visited[x][y], depths[x][y]);
                }else if(x>=0&&x<n&&y>=0&&y<m&&grid[x][y]==1&&visited[x][y]==0&&currentDepth<=depths[x][y]){
                    Node nextNode=new Node(x, y);
                    seconds.add(nextNode);
                    visited[x][y]=2;
                    depths[x][y]=currentDepth;
//                    System.out.printf("Connect to: %s %s, visited:%s, depth:%s \n", x, y, visited[x][y], depths[x][y]);
                }else if(x>=0&&x<n&&y>=0&&y<m&&visited[x][y]==2&&depths[x][y]>currentDepth+1){
                    Node nextNode=new Node(x, y);
                    seconds.add(nextNode);
                    visited[x][y]=2;
                    depths[x][y]=Math.min(currentDepth+1, depths[x][y]);
                }
            }
        }
        if(currentRs!=Integer.MAX_VALUE){
            return currentRs;
        }

        return 0;
    }

    public static void main(String[] args) {
//        int[][] arr = new int[][]
//                {
//                        {0, 1},
//                        {1, 0}
//                };
//        int[][] arr = new int[][]
//                {
//                        {0, 1, 0},
//                        {0, 0, 0},
//                        {0, 0, 1}
//                };
//        int[][] arr = new int[][]
//                {
//                        {1,1,1,1,1},
//                        {1,0,0,0,1},
//                        {1,0,1,0,1},
//                        {1,0,0,0,1},
//                        {1,1,1,1,1}
//                };
//        int[][] arr = new int[][]
//                {
//                        {0,0,1,0,1},
//                        {0,1,1,0,1},
//                        {0,1,0,0,1},
//                        {0,0,0,0,0},
//                        {0,0,0,0,0}
//                };
//        int[][] arr = new int[][]{
//                {0,1,0,0,0,0},
//                {0,1,1,1,0,0},
//                {0,0,0,0,0,0},
//                {0,0,0,0,0,0},
//                {0,0,0,0,0,0},
//                {1,1,0,0,0,0}};
        int[][] arr = new int[][]{
                {0,0,0,0,1,1,1,1,1,1},
                {1,1,0,0,1,1,1,0,0,0},
                {1,1,0,0,1,1,1,0,0,0},
                {1,1,1,0,1,1,0,0,0,0},
                {1,1,1,1,0,1,1,0,0,0},
                {0,1,0,1,0,1,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0}};
        System.out.println(shortestBridge(arr));
        //
        //** Đề bài
        //- Trả lại số lượng bits min để connect 2 vùng lands với nhau (grid[i[[j]==1) thì là land
        //
        //** Bài này tư duy như sau:
        //Cách 1: Brute force
        //1,
        //1.2,
        //- Đầu tiên cần tìm all nodes có tập hợp Connected được với nhau (tách thành parts)
        //+ List<Node> firstNodes;
        //+ List<Node> secondNodes;
        //- Sau đó tìm khoảng cách nhỏ nhất giữ 2 nodes bất kỳ trong 2 tập hợp
        //==> (x,y) đến (x1,y1)
        //
        //Cách 2:
        //2.
        //2.1,
        //
        System.out.println(shortestBridgeRefactorEvenSlow(arr));
    }
}
