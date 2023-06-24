package E1_Graph;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

public class E9_NearestExitFromEntranceInMaze {
    //        (-1,0)
    //(0,-1)           (0, 1)
    //        (1,0)
    public static int[] dx ={-1, 0, 1, 0};
    public static int[] dy ={0, 1, 0, -1};
    public static int solutionBFS(char[][] maze, int [] entrance){
        int n=maze.length;
        int m=maze[0].length;
        Deque<int[]> nodes=new LinkedList<>();
        nodes.add(entrance);
        HashMap<int[], Integer> depthNode=new HashMap<>();
        depthNode.put(entrance, 0);
        boolean[][] visited=new boolean[n][m];

        while (!nodes.isEmpty()){
            int[] currentNode=nodes.removeFirst();
            visited[currentNode[0]][currentNode[1]]=true;

//            if(currentNode[0]==0||currentNode[1]==0||currentNode[0]==n-1||currentNode[1]==m-1){
//                if(currentNode[0]!=entrance[0]||currentNode[1]!=entrance[1]){
//                    return depthNode.get(currentNode);
//                }
//            }
            for(int i=0;i<dx.length;i++){
                int x1=currentNode[0]+dx[i];
                int y1=currentNode[1]+dy[i];
                int[] newNode=new int[]{x1, y1};
                if(x1<0||x1>=n||y1<0||y1>=m||maze[x1][y1]=='+'|| visited[x1][y1]){
                    continue;
                }
                visited[x1][y1]=true;
                if(x1==0||y1==0||x1==n-1||y1==m-1){
                    return depthNode.get(currentNode)+1;
                }
                depthNode.put(newNode, depthNode.get(currentNode)+1);
                nodes.add(newNode);
            }
        }
        return -1;
    }
    public static int solutionBFSOptimization(char[][] maze, int [] entrance){
        int n=maze.length;
        int m=maze[0].length;
        Deque<int[]> nodes=new LinkedList<>();
        nodes.add(new int[]{entrance[0], entrance[1], 0});
        maze[entrance[0]][entrance[1]]='+';

        while (!nodes.isEmpty()){
            int[] currentNode=nodes.removeFirst();

//            if(currentNode[0]==0||currentNode[1]==0||currentNode[0]==n-1||currentNode[1]==m-1){
//                if(currentNode[0]!=entrance[0]||currentNode[1]!=entrance[1]){
//                    return depthNode.get(currentNode);
//                }
//            }
            for(int i=0;i<dx.length;i++){
                int x1=currentNode[0]+dx[i];
                int y1=currentNode[1]+dy[i];
                int[] newNode=new int[]{x1, y1, 0};
                if(x1<0||x1>=n||y1<0||y1>=m||maze[x1][y1]=='+'|| maze[x1][y1]=='.'){
                    continue;
                }
                maze[x1][y1]='+';
                if(x1==0||y1==0||x1==n-1||y1==m-1){
                    return currentNode[2]+1;
                }
                newNode[2]=currentNode[2]+1;
                nodes.add(newNode);
            }
        }
        return -1;
    }

    public static int nearestExit(char[][] maze, int[] entrance) {
        int rs=solutionBFSOptimization(maze, entrance);
        System.out.println(rs);
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Ta đứng ở entrance --> entrance={x,y} có thể:
        //+ Wall = '+', '' --> có thể move
        //+ move 4 directions
        //+ Tìm đường exit (Khi ta chạm được 1 edge của maze)
        //
        //** Idea
        //1.
        //1.0,
        //- Dùng bfs để tìm đường đi ngắn nhất từ điểm đó cho đến điểm có (x==n-1) or (y==n-1)
        //
        //1.1, Special testcases
        //- mấy bài maze --> Tốt nhất là chia thành n và m
        //- Node đứng ngay border luôn --> Không tính là cạnh
        //
        //--> Queue --> removeFirst() --> Ở đây là mình đang nhầm là removeLast()
        //1.2, Optimization
        //- Time optimization
        //+ Để tránh dùng hash map --> Ta nên lưu depth luôn vào trong node.
        //VD: node ={x,y,depth}
        //- Space optimization
        //+ Để tối ưu thì ta sẽ bỏ visited[][] ==> Bằng cách thay đổi array gốc
        //
        //1.3, Complexity
        //- Time complexity : O(m*n)
        //+ Why
        //+ Pop, add O(1)
        //+ visit : O(1)
        //+ visit all cell : O(m*n)
        //- Space complexity :
        //+ Với các tối ưu space
        //+ Vì ta chỉ có thể đi dọc hoặc ngang (n/ m)
        //--> Queue chỉ lưu tối đa max(m+n) nodes
        //--> Space = max( m + n )
        //
        char [][]maze = {{'+','+','+'},{'.','.','.'},{'+','+','+'}};
        int[] entrance = {1,0};
        System.out.println(nearestExit(maze, entrance));
        //#934. Shortest Bridge
        //515. Find Largest Value in Each Tree Row
        //1229. Meeting Scheduler
        //774. Minimize Max Distance to Gas Station
    }
}
