package E1_Graph.Dijkstra;

import java.util.PriorityQueue;

public class E7_TheMazeIII {

    //u,r,d,l
    public static int[] dx={-1, 0, 1, 0};
    public static int[] dy={0, 1, 0, -1};

    public static class Node{
        int x;
        int y;
        int distance;
        String s;
        public Node(int x, int y, int distance, String s){
            this.x=x;
            this.y=y;
            this.distance=distance;
            this.s=s;
        }

        @Override
        public String toString() {
            return x+", "+y+", dis: "+distance+", instruction: "+s;
        }
    }

    public static class Result{
        int minDis;
        String instruction;
        public Result(int minDist, String instruction){
            this.minDis=minDist;
            this.instruction=instruction;
        }

        @Override
        public String toString() {
            return minDis+", "+ instruction;
        }
    }

    public static String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int n=maze.length;
        int m=maze[0].length;
        //Space :
        //+ x,y,distance
        //+ string : O(k)
        //Space = O(n*m*k)
        //Time : O(n*m*log(n*m))
        PriorityQueue<Node> queue=new PriorityQueue<>((a, b) -> (a.distance-b.distance));
        queue.add(new Node(ball[0], ball[1], 0, ""));
        //Space : O(n*m*4)
        Result[][][] dp=new Result[n][m][4];

        //Time : O(n*m*4)
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                for(int l=0;l<4;l++){
                    dp[i][j][l]=new Result(Integer.MAX_VALUE, null);
                }
            }
        }
        dp[ball[0]][ball[1]][0]=new Result(0, "");
        dp[ball[0]][ball[1]][1]=new Result(0, "");
        dp[ball[0]][ball[1]][2]=new Result(0, "");
        dp[ball[0]][ball[1]][3]=new Result(0, "");

        //Mỗi node trong queue được tính theo 4 directions:
        //+ Mỗi direction là khoảng cách min nhất cho đến thời điểm đang tính từ (start --> node(i,j))
        //+ Ta có (n*m) node như thế
        //* Ở đây do chua tối ưu nên có khả năng các node sẽ được retraverse --> Điều này không hết tốt
        //- Nếu trong trường hợp ta chỉ traverse mỗi node 1 lần
        //- Mỗi node đi qua 4 directions là cùng và push các weight vào heap
        //--> Time = O(n*m+ n*m*4*log(n*m)) ==> Do thuật toán chưa tối ưu nên time complexity khá khó tính toán
        while(!queue.isEmpty()){
            Node curNode=queue.poll();

            //Time : O(4), vì đi 4 directions mà cùng lắm 1 node chỉ có thể đi hết 2*(n+m) nodes thôi
            //+ Đi đủ u,d,l,r
            for(int i=0;i<dx.length;i++){
                int x=curNode.x;
                int y=curNode.y;
                int addedDis=0;
                //Space : O(k)
                StringBuilder curInstruct=new StringBuilder(curNode.s);
                char dirChar=getDir(i);

                //Time : O(n+m)
                while(x+dx[i]>=0&&x+dx[i]<n&&y+dy[i]>=0&&y+dy[i]<m&&maze[x+dx[i]][y+dy[i]]==0
                        &&(x!=hole[0]||y!=hole[1])
                ){
                    x=x+dx[i];
                    y=y+dy[i];
//                    System.out.printf("%s %s, value: %s\n", x, y, maze[x][y]);
                    addedDis++;
                }
                if(addedDis!=0){
                    curInstruct.append(dirChar);
                }
//                System.out.printf("Node: x: %s,y: %s, Prev instruction: %s, cur instruction: %s, addedDis: %s, min str: %s\n",
//                        curNode.x, curNode.y, curNode.s, curInstruct, addedDis, dp[x][y][i].minDis);
//                if(dp[x][y][i].instruction!=null){
//                    System.out.printf("%s compare to %s, valu: %s\n",dp[x][y][i].instruction, curInstruct, dp[x][y][i].instruction.compareTo(curInstruct.toString()));
//                }
                if(curNode.distance+addedDis<=dp[x][y][i].minDis
                        &&(dp[x][y][i].instruction==null||dp[x][y][i].instruction.compareTo(curInstruct.toString())>0)){
                    dp[x][y][i]=new Result(curNode.distance+addedDis, curInstruct.toString());
                    //Time : O(Log(n*m))
                    queue.add(new Node(x, y, curNode.distance+addedDis, curInstruct.toString()));
                }
            }
        }
        String rs="";
        int min=Integer.MAX_VALUE;
        if(dp[hole[0]][hole[1]][0]!=null&&dp[hole[0]][hole[1]][0].minDis<min){
            min=dp[hole[0]][hole[1]][0].minDis;
            rs=dp[hole[0]][hole[1]][0].instruction;
        }
        if(dp[hole[0]][hole[1]][1]!=null&&dp[hole[0]][hole[1]][1].minDis<=min
                &&dp[hole[0]][hole[1]][1].instruction!=null&&rs.compareTo(dp[hole[0]][hole[1]][1].instruction)>0){
            min=dp[hole[0]][hole[1]][1].minDis;
            rs=dp[hole[0]][hole[1]][1].instruction;
        }
        if(dp[hole[0]][hole[1]][2]!=null&&dp[hole[0]][hole[1]][2].minDis<=min
                &&dp[hole[0]][hole[1]][2].instruction!=null&&rs.compareTo(dp[hole[0]][hole[1]][2].instruction)>0){
            min=dp[hole[0]][hole[1]][2].minDis;
            rs=dp[hole[0]][hole[1]][2].instruction;
        }
        if(dp[hole[0]][hole[1]][3]!=null&&dp[hole[0]][hole[1]][3].minDis<=min
                &&dp[hole[0]][hole[1]][3].instruction!=null&&rs.compareTo(dp[hole[0]][hole[1]][3].instruction)>0){
            rs=dp[hole[0]][hole[1]][3].instruction;
        }
//        System.out.println(min);
        return min==Integer.MAX_VALUE?"impossible":rs;
    }

    public static String findShortestWayOptimization(int[][] maze, int[] ball, int[] hole) {
        int n=maze.length;
        int m=maze[0].length;
        //Space :
        //+ x,y,distance
        //+ string : O(k)
        //Space = O(n*m*k)
        //Time : O(n*m*log(n*m))
        PriorityQueue<Node> queue=new PriorityQueue<>((a, b) -> (a.distance-b.distance!=0?a.distance-b.distance:a.s.compareTo(b.s)));
        queue.add(new Node(ball[0], ball[1], 0, ""));
        //Space : O(n*m*4)
        Result[][] dp=new Result[n][m];
        boolean[][] visited=new boolean[n][m];

        //Time : O(n*m*4)
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                dp[i][j]=new Result(Integer.MAX_VALUE, null);
            }
        }
        dp[ball[0]][ball[1]]=new Result(0, "");

        while(!queue.isEmpty()){
            Node curNode=queue.poll();
//            System.out.printf("Node : %s, visited : %s\n",curNode, visited[curNode.x][curNode.y]);

            if(visited[curNode.x][curNode.y]){
                continue;
            }
            visited[curNode.x][curNode.y]=true;

            for(int i=0;i<dx.length;i++){
                int x=curNode.x;
                int y=curNode.y;
                int addedDis=0;
                //Space : O(k)
                StringBuilder curInstruct=new StringBuilder(curNode.s);
                char dirChar=getDir(i);

                //Time : O(n+m)
                while(x+dx[i]>=0&&x+dx[i]<n&&y+dy[i]>=0&&y+dy[i]<m&&maze[x+dx[i]][y+dy[i]]==0
                        &&(x!=hole[0]||y!=hole[1])
                ){
                    x=x+dx[i];
                    y=y+dy[i];
//                    System.out.printf("%s %s, value: %s\n", x, y, maze[x][y]);
                    addedDis++;
                }
                if(addedDis!=0){
                    curInstruct.append(dirChar);
                }
//                System.out.printf("Node: x: %s,y: %s, Prev instruction: %s, cur instruction: %s, addedDis: %s, min str: %s\n",
//                        curNode.x, curNode.y, curNode.s, curInstruct, addedDis, dp[x][y].minDis);
//                if(dp[x][y].instruction!=null){
//                    System.out.printf("%s compare to %s, valu: %s\n",dp[x][y].instruction, curInstruct, dp[x][y].instruction.compareTo(curInstruct.toString()));
//                }
                if(curNode.distance+addedDis<=dp[x][y].minDis
                        &&(dp[x][y].instruction==null||dp[x][y].instruction.compareTo(curInstruct.toString())>0)){
                    dp[x][y]=new Result(curNode.distance+addedDis, curInstruct.toString());
                    //Time : O(Log(n*m))
                    queue.add(new Node(x, y, curNode.distance+addedDis, curInstruct.toString()));
                }
            }
        }
        String rs="";
        int min=Integer.MAX_VALUE;
        if(dp[hole[0]][hole[1]]!=null&&dp[hole[0]][hole[1]].minDis<min){
            min=dp[hole[0]][hole[1]].minDis;
            rs=dp[hole[0]][hole[1]].instruction;
        }
//        System.out.println(min);
        return min==Integer.MAX_VALUE?"impossible":rs;
    }

    public static char getDir(int dir){
        switch(dir){
            case 0: return 'u';
            case 1: return 'r';
            case 2: return 'd';
        }
        return 'l';
    }

    public static String findShortestWayOptimization1(int[][] maze, int[] ball, int[] hole) {
        int n=maze.length;
        int m=maze[0].length;
        //Space :
        //+ x,y,distance
        //+ string : O(k)
        //Space = O(n*m*k)
        //Time : O(n*m*log(n*m))
        PriorityQueue<Node> queue=new PriorityQueue<>((a, b) -> (a.distance-b.distance!=0?a.distance-b.distance:a.s.compareTo(b.s)));
        queue.add(new Node(ball[0], ball[1], 0, ""));
        //Space : O(n*m)
        boolean[][] visited=new boolean[n][m];

        //+ Ta sẽ đi qua all cells : O(n*m)
        //+ Add cell to queue : Log(n*m)
        //+ Mỗi cell đi qua all neigbors + add new node
        //=> Time complexity : O(n*m*log(n*m))
        while(!queue.isEmpty()){
            Node curNode=queue.poll();

            if(visited[curNode.x][curNode.y]){
                continue;
            }
            if(curNode.x==hole[0]&&curNode.y==hole[1]){
                return curNode.s;
            }
            visited[curNode.x][curNode.y]=true;

            for(int i=0;i<dx.length;i++){
                int x=curNode.x;
                int y=curNode.y;
                int addedDis=0;
                StringBuilder curInstruct=new StringBuilder(curNode.s);
                char dirChar=getDir(i);

                while(x+dx[i]>=0&&x+dx[i]<n&&y+dy[i]>=0&&y+dy[i]<m&&maze[x+dx[i]][y+dy[i]]==0
                        &&(x!=hole[0]||y!=hole[1])
                ){
                    x=x+dx[i];
                    y=y+dy[i];
//                    System.out.printf("%s %s, value: %s\n", x, y, maze[x][y]);
                    addedDis++;
                }
                if(addedDis!=0){
                    curInstruct.append(dirChar);
                }
                queue.add(new Node(x, y, curNode.distance+addedDis, curInstruct.toString()));
            }
        }
        return "impossible";
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given a maze
        //+ Empty space : 0
        //+ Wall : 1
        //- The ball can roll 4 directions and it won't stop rolling until hitting a wall.
        //- If the ball stop, it will choose (next direction)
        //- There is a hole in this maze, the ball will drop into the hole if it rolls onto the hole
        //* Return the (instruction string) contains all the instructions that
        // the ball should follow to drop in the hole with the shortest distance possible.
        //-  If there are multiple valid instructions, return the lexicographically minimum one.
        //<> If we don't have any solution --> return "impossible"
        //+ u : up
        //+ d : down
        //+ l : left
        //+ r : right
        //- "distance": The distance is the number of empty spaces traveled by the ball
        // from the start position (excluded) to the destination (included).
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //m == maze.length
        //n == maze[i].length
        //1 <= m, n <= 100
        //maze[i][j] is 0 or 1.
        //ball.length == 2
        //hole.length == 2
        //0 <= ballrow, holerow <= m
        //0 <= ballcol, holecol <= n
        //Both the ball and the hole exist in an empty space, and they will not be in the same position initially.
        //The maze contains at least 2 empty spaces.
        //- Số cells cũng không quá lớn ==> Có thể traverse từng cells được
        //
        //** Dijkstra chỉ dùng cho (weight>=0)
        //- Dijkstra's algorithm solves the shortest-path problem for any weighted,
        // directed graph with (non-negative weights).
        // It can handle graphs consisting of cycles, but (negative weights) will cause this algorithm to produce (incorrect results).
        //
        //- Brainstorm
        //Ex:
        //maze =
        // [[0,0,0,0,0],
        // [1,1,0,0,1],
        // [0,0,0,0,0],
        // [0,1,0,0,1],
        // [0,1,0,0,0]], ball = [4,3], hole = [0,1]
        //
        //- Tìm đường đến 1 hole nào đó từ ball
        //+ Ở đây ta thấy rằng các weight đều > 0
        //  + Vì là dạng sorted ==> các cạnh edge mà càng min/ max đến trước ==> Lúc nào cũng tốt hơn
        //  Vì lựa chọn giữa (a/b) ==> Ta chọn node tốt hơn ==> Để có thể kết hợp cùng với các nodes sau đó cho kết quả
        //  tốt hơn.
        //Ex:
        // 1 -- 2 -- 5 (target) --> Sai tất cả là target hết
        //  \       |
        //   3 -- 4
        //- Giả dụ trong trường hợp nhân với nhau theo số thực
        //+ (1,2)= 0.1
        //+ (1,3)=0.2
        //+ (2,5)= 0.4
        //+ (3,4)=0.6
        //+ (4,5)=0.001
        //==> Ở đây ta thấy nếu đi (1 - 3 - 4) sẽ tệ hơn (1 - 2 - 5) (target=5)
        //Nhưng khi đi (1 - 3 - 4 - 5) sẽ tốt hơn (1 - 2 - 5) (target=5)
        //+ Step 0: start=1
        //+ Step 1: 1 -> 2(0.1), 1 -> 3(0.1)
        //1 2   3
        //0 0.1 0.2
        //+ Step 2: Chọn 2 (Chọn node chưa đi mà min distance)
        // 1 - 2 - 5 = 0.1*0.4
        //1 | 2   | 3   | 5
        //0 |0.1  | 0.2 | 0.04
        //+ Step 3: Chọn 5 min=0.04 + visited[5]=false
        //1 | 2   | 3   | 5     | 4
        //0 |0.1  | 0.2 | 0.04  | 0.00004
        //** Ta thấy ở đây rằng distance(5)= 0.04 không phải min_distance từ (1 -> 5)
        //===> Thuật toán dijikstra chỉ để tìm (node có khoảng cách min/max) đến (start node)?
        //* Dijikstra không phải thuật toán dạng tìm kiếm min distance của 1 node --> all nodes còn lại? ==> "WRONG IDEA"
        //** Chẳng qua bài trên là dạng đặc biệt
        //- Chỉ có thể tìm min distance của all remaining nodes nếu nó là (directed graph)
        //  + Khi chiều (4 -> 5) là 1 chiều ==> 5 không thể xuống 4 được, tính được bình thường cho (5 theo 1 - 3 - 4 -5)
        //  + Khi chiều (5 -> 4) là 1 chiều ==> 5 không thể xuống 4 được, tính được bình thường cho (4 theo 1 - 2 - 5 - 4) và (5)
        //- Nếu không phải nhân số thực --> Gía trị sẽ cộng dồn ==> Sẽ không có case (1 - 3 -4 - 5) < (1 - 2 - 5) (Do 4 - 5 small)
        //- Nếu hướng (4 - 5) và (5 -> 4) cùng tồn tại ==> Nhân số thực sẽ ra small dần --> 0 (Nên sẽ không có case ntn)
        //
        //- Quay trở lại thì bài này có thể làm dijijkstra được
        //- 1 node có thể đi đến rìa của wall ==> Sau đó chọn lại direction
        //- Mỗi node(i,j) sẽ có weight để có thể đến được
        //  + Ta sẽ update sau mỗi lần roll
        //- Ở đây ta thấy là sẽ là cộng dồn ==> undirected graph
        //  + Nếu node nào đi rồi thì sao?
        //Ex:
        //1 ---- 2
        //\   /
        // 3 --- 4
        //weight(1,2) = 0.1
        //weight(2,3) = 0.2
        //weight(1,3) = 2
        //weight(3, 4) = 4
        //+ Step 1:
        // 1
        // 0
        //+ Step 2: min=0 (Vị trí node 1)
        //  + 1 ->2
        //  + 1 -> 3
        // 1 | 2   |  3
        // 0 | 0.1 |  2
        //+ Step 3: min=(1,2) ==> 2 -> 3
        // 1 | 2   |  3 (updated)
        // 0 | 0.1 |  0.1+0.2=0.3
        //- Ta thấy dù 3 đã visited ==> Nhưng có updated tức là ta vẫn có thể xét 3 tiếp (Mặc dù đây là undirected graph)
        //** Ta vẫn phải (add vào queue) với nhưng (update nodes)
        //==> Chứ (không phải) cứ (run qua rồi) ta sẽ (không xét nữa)
        //
        //- Khi 1 node mới đến 1 node khác
        //  + Nếu node mới với node hiện tại cùng direction mà (kết quả ta sắp đến) tệ hơn (kết quả đã tính trước đó)
        //  ==> Ta không add vào queue.
        //- weight của mỗi node(i,j) sẽ không bao gồm string (Thử xem)
        //  + Mà chỉ các edge bao gồm thôi ==> Để có thể tính được luôn.
        //1.1, Implementation
        //- weight(i,j, direction) ==> array biểu diễn.
        //- PriorityQueue : add (i,j, weight)
        //  + Ta sẽ tính cho mọi node trên đường đi ==> Để có thể loại nhanh các cases tệ hơn kết quả cũ
        //      + Bằng cách so sánh với các node cùng direction trên đường đi.
        //
        //1.1, Optimization
        //- Ở đây mỗi node trong queue ta đều có thể cache lại thông tin để không phải retraverse thừa thãi
        //+ Những điểm không ở biên ta cũng cache lại
        //Ex: 1 -> 2(Đã tính theo hướng right rồi) -> 3
        //  + Giả sử 1 --> 2 mà 2 đã tính theo hướng right rồi còn tốt hơn 1 ==> Ta sẽ không cần xét sang right tiếp nữa
        //  --> Kết quả tệ đi (Tính từ 1 -> 2 sẽ > so với kết quả đã tính trước ở 2) nên bỏ qua.
        //
        //+ Nếu 1 node đi direction ngược lại 1 node khác có direction trái dấu là không cần thiết
        //Ex: 1 -> 2 -> 3 (trong khi 3 đi theo hướng <-) ===> Tư duy WRONG nhé vì ta không hề biết target ở đâu nên việc tính
        //như thế này là độc lập và vẫn đúng
        //
        //- Ngoài ra khi xem standard code:
        //+ Nó có dùng 1 đoạn liên quan đến visited (Tức là nhưng điểm đã xét thì không xét nữa)
        //  + Check node sau khi poll khỏi queue
        //- Vậy việc update lại node thì sao?
        //Ex:
        //1 ---- 2
        //\   /
        // 3 --- 4
        //+ 3 cần tính lại từ 2 vì đi từ (1 - 2 - 3) có weight smaller than 1 - 3
        //** QUY LUẬT theo "Dạng" của Dijikstra
        //- Theo dạng min/max tích các weight : Phải dùng directed graph
        //- Theo dạng sum weight : Không dùng được visited[][] nếu update lại node cũ mặc dù đã đi qua ta vẫn phải update vào queue
        //- Theo dạng maxtrix : Đi từng unit một (Tìm shortest path đến hole chẳng hạn)
        //  + Dạng này thì nó là accumulator cộng dồn nên nếu đã visit rồi ==> Thì ta sẽ ignore
        //Ex:
        //0  0    0  0 0
        //0  0    1  0 0
        //0 (0)   0  0 0
        //0  0   [0] 0 0
        //- [start] ==> (target) : thì ta thấy nếu visited trước tức là shortest path.
        //+ Thế nên những node mà đã visited ==> Ta sẽ ignore.
        //
        //- Nếu ta tư duy như thế này thì ta sẽ không cần quan tâm đến direction nữa
        //  + Direction lúc đó sẽ chỉ là 1 solution để optimize liên quan đến các node trung gian thôi.
        //
        //- Nếu sửa theo visited thì sẽ bị special case:
        //+ Nếu có 2 node đều có thể đến target:
        //  + Cùng distance
        //  + Nhưng string cái add vào queue sau sẽ cho kết quả tốt hơn (later_string > prev_string)
        //==> nếu ta visited trước ==> Thì ta sẽ không tính case phía sau nữa
        //  + Thiếu cases
        //** Kinh ngiệm:
        //+ Với những bài tìm shortest path mà theo nhiều predicate ==> Min/Max heap đều phải sort theo all predicates
        //  + Khi đó giá trị xét mới nhất chính là giá trị cần [ Không suy ngĩ theo kiểu all directions + tính min all directions]
        //+ Ta sẽ return ngay khi đến được target ==> Speed up.
        //
        //- Thêm 1 điều nữa, vì là dạng Dijikstra acculmulator
        //==> Ta sẽ không cần phải dùng dp (Tức là memorized array để lưu kết quả - Vì ta đã ignore các node đã visited)
        //+ Bỏ đi và khi tìm được kết quả ==> Ta sẽ return.
        //
        //1.2, Complexity
        //+ Chưa optimize : Khá khó tính
        //- Space : O(n*m*k)
        //- Time : O(n*m+ n*m*4*log(n*m))
        //+ Đã optimized
        //- Space complexity : O(n*m)
        //- Time complexity : O(n*m*log(n*m))
        //
//        int[][] maze =
//                {
//                        {0,0,0,0,0},
//                        {1,1,0,0,1},
//                        {0,0,0,0,0},
//                        {0,1,0,0,1},
//                        {0,1,0,0,0}};
//        int[] ball = {4,3}, hole = {0,1};

//        int[][] maze = {{0,0,0,0,0},{1,1,0,0,1},{0,0,0,0,0},{0,1,0,0,1},{0,1,0,0,0}};
//        int[] ball = {4,3}, hole = {3,0};

//        int[][] maze={
//                {0,0,0,0,0,0,0},
//                {0,0,1,0,0,1,0},
//                {0,0,0,0,1,0,0},
//                {0,0,0,0,0,0,1}};
        //{0,0,0,0,(0),0,  0},
        //{0,0,1,0, 0, 1,  0},
        //{0,0,0,0, 1, 0,  0},
        //{0,0,0,0, 0,(0), 1}};
//        int[] ball = {0,4}, hole = {3,5};

        int[][] maze={
                {0,1,0,0,1,0,0,1,0,0},
                {0,0,1,0,0,1,0,0,1,0},
                {0,0,0,0,0,0,1,0,0,1},
                {0,0,0,0,0,0,1,0,0,1},
                {0,1,0,0,1,0,0,1,0,0},
                {0,0,1,0,0,1,0,0,0,0},
                {0,0,0,0,0,0,1,0,0,0},
                {1,0,0,1,0,0,0,0,0,1},
                {0,1,0,0,1,0,0,1,0,0},
                {0,0,0,0,0,1,0,0,1,0}};
        int[] ball = {2,4}, hole = {7,6};
        System.out.println(findShortestWay(maze, ball, hole));
        System.out.println(findShortestWayOptimization(maze, ball, hole));
        //- Removed memorized array
        System.out.println(findShortestWayOptimization1(maze, ball, hole));
        System.out.println("rdrdrdldl".compareTo("drdrdrdldl"));
    }
}
