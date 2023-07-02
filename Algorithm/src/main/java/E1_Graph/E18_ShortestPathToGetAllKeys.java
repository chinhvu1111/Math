package E1_Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class E18_ShortestPathToGetAllKeys {

    //          (-1,0)
    //    (0,-1)(0,0),(0,1)
    //          (1,0)
    public static int[] dx={-1,0,1,0};
    public static int[] dy={0,1,0,-1};
    public static int solution(String[] grid){
        int n=grid.length;
        int m=grid[0].length();
        if(n==1&&m==1){
            if(grid[0].charAt(0)!='@'){
                return -1;
            }else{
                return 0;
            }
        }
        int startX=-1, startY=-1;
        int[][][] dp=new int[n][m][64];
        int numKeys=0;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                Arrays.fill(dp[i][j], -1);
                if(grid[i].charAt(j)=='@'){
                    startX=i;
                    startY=j;
                }
                if(grid[i].charAt(j)>='a'&&grid[i].charAt(j)<='f'){
                    numKeys++;
                }
            }
        }
        int size=(1<<(numKeys))-1;
        //numKeys=2
        //size=11 = 3
//        System.out.println(size);
        Queue<int[]> nodes=new LinkedList<>();
        nodes.add(new int[]{startX, startY, 0, 0});
        boolean[][][] visited=new boolean[n][m][size+1];
        visited[startX][startY][0]=true;

        while (!nodes.isEmpty()){
            int[] currentNode=nodes.poll();
//            System.out.printf("Start : %s %s %s %s\n", currentNode[0], currentNode[1], currentNode[2], currentNode[3]);
            for(int i=0;i<dx.length;i++){
                int x1=currentNode[0]+dx[i];
                int y1=currentNode[1]+dy[i];
                int currentVisitedCells=currentNode[3];
//                if(x1>=0&&x1<n&&y1>=0&&y1<m){
//                    System.out.printf("%s %s %s %s %s %s\n", x1, y1, currentNode[2], currentVisitedCells+1, grid[x1].charAt(y1), visited[x1][y1][currentNode[2]]);
//                }

                if(x1>=0&&x1<n&&y1>=0&&y1<m&&!visited[x1][y1][currentNode[2]]&&grid[x1].charAt(y1)!='#'){
                    char currentChar=grid[x1].charAt(y1);
                    int newValue=-1;

                    if((currentChar=='.'||currentChar=='@')
                        || ('A'<=currentChar&&currentChar<='F'
                            &&(currentNode[2]|1<<(currentChar-'A'))==currentNode[2])){
                        newValue=currentNode[2];
                        visited[x1][y1][newValue]=true;
                        nodes.add(new int[]{x1, y1, newValue, currentVisitedCells+1});
                    }else if('a'<=currentChar&&currentChar<='f'){
                        newValue=currentNode[2]|(1<<(currentChar-'a'));
                        visited[x1][y1][newValue]=true;
                        nodes.add(new int[]{x1, y1, newValue, currentVisitedCells+1});
                    }else {
                        continue;
                    }
//                    System.out.printf("%s %s %s %s\n", x1, y1, newValue, currentVisitedCells+1);
                    if(newValue==size){
                        return currentVisitedCells+1;
                    }
                }
            }
        }
        return -1;
    }

    public static int shortestPathAllKeys(String[] grid) {
        return solution(grid);
    }

    public static void main(String[] args) {
        //** Requirement
        //- Tìm đường đi ngắn nhất (shortest) để có thể lấy all keys từ grid
        //+ Chỉ có thể đi qua khoá A nếu có key a mở khoá
        //+ Ta có tổng cộng 6 keys : (a,b,c,d,e,f)
        //+ Ta chỉ có thể đi 4 hướng + không được đi vào wall
        //- Thông tin như sau:
        //+ '.' is an empty cell.
        //+ '#' is a wall.
        //+ '@' is the starting point.
        //- Khi nào thì get all keys : Lấy số key == số key đã có
        //
        //** Idea
        //1.
        //1.0, Brainstorm
        //- Constraint:
        //+ m == grid.length
        //+ n == grid[i].length
        //+ 1 <= m, n <= 30
        //
        //- Can we go back to the cells that have been moved before?
        //+ Yes, nhưng chỉ được phép quay lại old cells khi:
        //+ Vị trí hiện tại đã nhặt được thêm key ==> Việc quay lại mới có tác dụng.
        //--> dp[i][j][64] : Lưu thông tin là số cells đã đi đến đây
        //0,0,0,0,0,0,0
        //
        //- Can we go back to the starting points?
        //+ Yes
        //
        //- Ta sẽ dùng BFS với visisted là visited[i][j][64]
        //- 1 node(x,y) có thể đi được đển 4 direction khi:
        //+ visited[x1][y1][currentKey] ==-1
        //VD:
        //@.b..a
        //###.##
        //.BA...
        //- Nếu gặp A thì ta sẽ check khoá:
        //+ current value có key a hay không:
        //000001 or 0000001 --> Giữ nguyên thì có rồi (OK)
        //
        //- Special test cases:
        //+ grid={"@"}
        //+ grid={"@a...A...a"}
        //
        //1.1, Complexity:
        //- Time complexity : O(N)
        //- Space complexity : O(N)
        //
//        System.out.println( (1<<2) -1);
//        String[] grid = {
//                "@.a..",
//                "###.#",
//                "b.A.B"};
//        String[] grid = {"@Aa"};
//        String[] grid = {"@..aA","..B#.","....b"};
//        String[] grid = {"@.a..","###.#","b.A.B"};
//        String[] grid = {
//                "@a...A....",
//                ".......b..",
//                ".....c##Cd"};
        String[] grid = {
                "@...a",
                ".###A",
                "b.BCc"};
        System.out.println(shortestPathAllKeys(grid));
        //#Reference:
        //200. Number of Islands
        //1521. Find a Value of a Mysterious Function Closest to Target
        //1640. Check Array Formation Through Concatenation
        //665. Non-decreasing Array
    }
}
