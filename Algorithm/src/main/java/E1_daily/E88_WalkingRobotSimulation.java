package E1_daily;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public class E88_WalkingRobotSimulation {

    public static int robotSim(int[] commands, int[][] obstacles) {
        List<int[]> directions=new ArrayList<>();
        directions.add(new int[]{0, 1});
        directions.add(new int[]{-1, 0});
        directions.add(new int[]{0, -1});
        directions.add(new int[]{1, 0});

        int dir=0;
        int n=commands.length;
        int m=obstacles.length;
        int[] curDir= directions.get(0);
        //Space: O(m)
        HashMap<Integer, TreeSet<Integer>> adjNodesX=new HashMap<>();
        HashMap<Integer, TreeSet<Integer>> adjNodesY=new HashMap<>();
        int rs=0;
        boolean rootIsObstacle=false;

        //Time: O(m)
        for(int i=0;i<m;i++){
            if(obstacles[i][0]==0&&obstacles[i][1]==0){
                rootIsObstacle=true;
                continue;
            }
            //Time: O(log(n))
            TreeSet<Integer> adjY = adjNodesX.getOrDefault(obstacles[i][0], new TreeSet<>());
            if(adjY==null){
                adjY=new TreeSet<>();
            }
            adjY.add(obstacles[i][1]);
            adjNodesX.put(obstacles[i][0], adjY);
            //
            TreeSet<Integer> adjX = adjNodesY.getOrDefault(obstacles[i][1], new TreeSet<>());
            if(adjX==null){
                adjX=new TreeSet<>();
            }
            adjX.add(obstacles[i][0]);
            adjNodesY.put(obstacles[i][1], adjX);
        }
        int x=0;
        int y=0;
        //(a,b) ==> (a+k,b+k)
        //Time: O(n)
        for(int i=0;i<n;i++){
            if(commands[i]==-1){
                //3->2->1->0
                dir--;
                if(dir<0){
                    dir=3;
                }
            }else if(commands[i]==-2){
                dir=(dir+1)%4;
            }else{
                int x1=curDir[0]*commands[i]+x;
                int y1=curDir[1]*commands[i]+y;
                //+ dy=0 ==> search x
                //+ dx=0 ==> search y
                //
                //+ dx=0 ==> search y
                if(curDir[0]==0){
                    TreeSet<Integer> adjY=adjNodesX.get(x);
                    if(adjY==null){
                        x=x1;
                        y=y1;
//                        continue;
                    }
                    //Tăng x
                    else if(curDir[1]==1){
                        //x,x1, (min, max)
                        //(min, max)<x,x1
                        //- Bị case:
                        //x,x1, [x2,x3,x4]
                        if(y1<adjY.first()||y>adjY.last()){
                            x=x1;
                            y=y1;
//                            continue;
                        }else{
                            x=x1;
                            //x,x1, [x2,x3,x4]
                            //x, [x2,(x1),x3,x4]
                            //x, [x2,x3,x4], (x1)
                            //==> search cái nào nhỏ nhất >= x
                            //
                            //Time: O(log(n))
                            Integer minGreaterY=adjY.ceiling(y);
//                            if(minGreaterY!=y){
//                                minGreaterY=minGreaterY-1;
//                            }
                            minGreaterY=minGreaterY-1;
                            y=Math.min(y1, minGreaterY);
//                            continue;
                        }
                        //Giảm y
                    }else if(curDir[1]==-1){
                        //(min, max)<x1,x
                        //x1,x< (min, max)
                        if(y1>adjY.last()||y<adjY.first()){
                            x=x1;
                            y=y1;
//                            continue;
                        }else{
                            //[x2,x3,x4], x1,x
                            //[x2,x3,(x1),x4], x
                            //x1,[x2,x3,x4], x
                            x=x1;
                            //Chỗ này là +1 chứ không phải -1
                            Integer lessThanY = adjY.floor(y);
//                            if(lessThanY!=y){
//                                lessThanY=lessThanY+1;
//                            }
                            lessThanY=lessThanY+1;
                            y=Math.max(y1, lessThanY);
//                            y=adjY.last()+1;
//                            continue;
                        }
                    }
                    //- x thì có thể đi theo chiều:
                    //  + Tăng
                    //      + So sánh với: >min x
                    //  + Giảm
                    //      + So sánh với: <max x
                    //- y thì có thể đi theo chiều:
                    //  + Tăng
                    //  + Giảm
                    //
                }else if(curDir[1]==0){
                    TreeSet<Integer> adjX=adjNodesY.get(y);
                    if(adjX==null){
                        x=x1;
                        y=y1;
//                        continue;
                    }
                    //Tăng x
                    else if(curDir[0]==1){
                        //x,x1< (min, max)
                        //(min, max)<x x1
                        if(x1<adjX.first()||x>adjX.last()){
                            x=x1;
                            y=y1;
//                            continue;
                        }else{
                            Integer minGreaterX=adjX.ceiling(x);
//                            if(minGreaterX!=x){
//                                minGreaterX=minGreaterX-1;
//                            }
                            minGreaterX=minGreaterX-1;
                            x=Math.min(x1, minGreaterX);
                            y=y1;
//                            continue;
                        }
                        //Giảm x
                    }else if(curDir[0]==-1){
                        //(min, max)<x1,x
                        //x1,x (min, max)
                        if(x1>adjX.last()||x<adjX.first()){
                            x=x1;
                            y=y1;
//                            continue;
                        }else{
//                            x=adjX.last()+1;
                            Integer lessThanX = adjX.floor(x);
//                            if(lessThanX!=x){
//                                lessThanX=lessThanX+1;
//                            }
                            lessThanX=lessThanX+1;
                            x=Math.max(x1, lessThanX);
                            y=y1;
//                            continue;
                        }
                    }
                    //- x thì có thể đi theo chiều:
                    //  + Tăng
                    //      + So sánh với: >min x
                    //  + Giảm
                    //      + So sánh với: <max x
                    //- y thì có thể đi theo chiều:
                    //  + Tăng
                    //  + Giảm
                    //
                }
                if(rootIsObstacle){
                    TreeSet<Integer> adjY=adjNodesX.get(0);
                    if(adjY==null){
                        adjY=new TreeSet<>();
                    }
                    adjY.add(0);
                    adjNodesX.put(0, adjY);
                    TreeSet<Integer> adjX=adjNodesY.get(0);
                    if(adjX==null){
                        adjX=new TreeSet<>();
                    }
                    adjX.add(0);
                    adjNodesY.put(0, adjX);
                }
            }
//            System.out.printf("Pos: %s, x:%s, y:%s\n", i, x, y);
            rs=Math.max(rs, x*x+y*y);
            curDir=directions.get(dir);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- A robot on an infinite XY-plane starts at point (0, 0) facing north.
        // The robot can receive a sequence of these three possible types of commands:
        //  + -2: Turn left (90 degrees).
        //  + -1: Turn right (90 degrees).
        //  + 1 <= k <= 9: Move forward k units, one unit at a time.
        //- (Some of the grid squares) are obstacles. The (ith obstacle) is at grid point obstacles[i] = (xi, yi).
        //- If the robot runs into (an obstacle), then it will instead stay in (its current location) and move on to (the next command).
        //      + Tức là nó sẽ đứng ngay trước obstacle ==> Chuyển qua next command
        //* Return the (maximum Euclidean distance) that the robot ever gets from (the origin squared)
        // (i.e. if the distance is 5, return 25).
        //- Note:
        //  + North means +Y direction.
        //  + East means +X direction.
        //  + South means -Y direction.
        //  + West means -X direction.
        //  + There can be obstacle in [0,0].
        //          W (-1,0)
        //            ^
        //            |
        // S (0,-1) <- -> N (0,1)
        //           |
        //           V
        //           E (1,0)
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= commands.length <= 10^4
        //commands[i] is either -2, -1, or an integer in the range [1, 9].
        //0 <= obstacles.length <= 10^4
        //-3 * 10^4 <= xi, yi <= 3 * 10^4
        //  + Số lượng commands = 10^4
        //  + Số lượng obstacles cũng lớn ==> Map để check là được
        //  + (x,y) khá lớn:
        //      + Việc đi lần lượt không đủ tốt.
        //
        //- Brainstorm
        //- Bài này ta có thể dùng DFS để giải
        //- Direction:
        //  + Turn left
        //      + N->W->S->E
        //  + Turn right
        //      + N->E->S->W
        //- turn left:
        //  + Thay đổi phần cộng direction
        //
        //- Check gặp obstacle ntn?
        //  + Do 1 point chỉ có thể đi theo 1 trục x/y
        //      + Cùng row
        //      + Cùng col
        //- Điểm cùng 1 trục:
        //  + Cùng x or y
        //- Để xét lần lượt thì ta sẽ cần sort:
        //  - Các điểm tăng dần y theo x
        //  - Các điểm tăng dần x theo y
        //  + Tức là tạo map:
        //      + [x1]: danh sách điểm x=x1 và y tăng dần
        //      + [y1]: danh sách điểm y=y1 và x tăng dần
        //  + Do các điểm duplication ==> dồn vào 1 obstacle là được:
        //      + Dùng treeSet
        //
        //- Nó chỉ có 2 trục (X/Y):
        //  + Chỉ cần search 1 trong 2 trục là được.
        //
        //
//        int[] commands = {4,-1,3};
//        int[][] obstacles = {};
//        int[] commands = {4,-1,4,-2,4};
//        int[][] obstacles = {{2, 4}};
//        int[] commands = {-2,-1,8,9,6};
//        int[][] obstacles = {{-1,3},{0,1},{-1,5},{-2,-4},{5,4},{-2,-3},{5,-1},{1,-1},{5,5},{5,2}};
//        int[] commands = {7,-2,-2,7,5};
//        int[][] obstacles = {{-3,2},{-2,1},{0,1},{-2,4},{-1,0},{-2,-3},{0,-3},{4,4},{-3,3},{2,2}};
        //
        //- Special cases:
        //- Case vị trí với:
        //  -1/1: ceil, floor
        //- Case liên quan đến (k không đủ) còn chưa reach được đến (ceil/floor)
        //
        //- Case node(x,y) chính là obstacle:
        //  + Cần ignore khi add vào map adj đầu tiên
        //      + Để tránh search bên trong
        //  * Nhưng cần add sau khi thoát khỏi root(0,0) đầu tiên
        //      + Vì về sau nó có thể quay lại chính root đó
//        int[] commands = {2,-1,8,-1,6};
//        int[][] obstacles = {{1,5},{-5,-5},{0,4},{-1,-1},{4,5},{-5,-3},{-2,1},{-2,-5},{0,5},{0,-1}};
        int[] commands = {-2,-1,-2,3,7};
        int[][] obstacles = {{1,-3},{2,-3},{4,0},{-2,5},{-5,2},{0,0},{4,-4},{-2,-5},{-1,-2},{0,2}};
        System.out.println(robotSim(commands, obstacles));
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(m)
        //- Time: O(n*log(n)+m*log(n))
        //
        //#Reference:
        //460. LFU Cache
        //911. Online Election
        //1996. The Number of Weak Characters in the Game
        //908. Smallest Range I
        //1986. Minimum Number of Work Sessions to Finish the Tasks
        //2913. Subarrays Distinct Element Sum of Squares I
    }
}
