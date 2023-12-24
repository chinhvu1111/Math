package E1_Graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class E45_MinimumKnightMoves {

    static int[] dx={-2,-1,1,2,2,1,-1,-2};
    static int[] dy={1,2,2,1,-1,-2,-2,-1};
    public static int minKnightMoves(int x, int y) {
        if(x==0&&y==0){
            return 0;
        }
        Queue<int[]> nodes=new LinkedList<>();
        nodes.add(new int[]{300, 300, 0});
        boolean[][] visited=new boolean[601][601];

        while(!nodes.isEmpty()){
            int[] curNode=nodes.poll();
            for(int i=0;i<dx.length;i++){
                int x1=curNode[0]+dx[i];
                int y1=curNode[1]+dy[i];
                int depth=curNode[2]+1;

                if(visited[x1][y1]){
                    continue;
                }
                if(x1==x+300&&y1==y+300){
                    return depth;
                }
                visited[x1][y1]=true;
                nodes.add(new int[]{x1, y1, depth});
            }
        }
        return 1;
    }

    public static HashMap<String, Integer> memo;

    //Space : O(max(|x|,|y|)(Stack space) + O(|x|*|y|) (The number of cells)
    //Time : O(|x|*|y|)
    public static int solution(int x, int y){
        String key=x+","+y;
        if(memo.containsKey(key)){
            return memo.get(key);
        }
        if(x+y==0){
            return 0;
        }else if(x+y==2){
            return 2;
        }else{
            int curRs=Math.min(solution(Math.abs(x-2), Math.abs(y-1)), solution(Math.abs(x-1), Math.abs(y-2)))+1;
            memo.put(key, curRs);
            return curRs;
        }
    }

    public static int minKnightMovesDFS(int x, int y) {
        if(x==0&&y==0){
            return 0;
        }
        memo=new HashMap<>();
        return solution(Math.abs(x), Math.abs(y));
    }

    public static void main(String[] args) {
        //** Requirement
        //- In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].
        //A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction,
        // then one square in an orthogonal direction.
        //- Về cơ bản là start(0,0) --> (x,y) hết bao nhiều steps nếu đi 8 directions như knight
        //
        //** Idea
        //1.
        //1.0, Idea
        //- Constraint
        //-300 <= x, y <= 300
        //0 <= |x| + |y| <= 300
        //==> Tạm thời có thể giới hạn được chess board
        //- Brainstorm
        //- Cơ bản là dùng BFS
        //- Max size của array = (x+y) = 600
        //==> Ta lấy 601 là được.
        //
        //1.1, Optimization
        //1.2, Complexity:
        //- Space : O(max(|x|,|y|)^2)
        //- Time : O(max(|x|,|y|)^2)
        //
        //2. DFS + memoriation
        //2.0, Idea
        //- x,y có thể <0
        //- Yêu cầu đề bài là tìm số step ngắn nhất có thể đi từ (0,0) --> (x,y)
        //- Ta chia thành 4 quarters:
        //  + Việc đi từ (0,0) --> (x,y) <=> (x,y) --> (0,0)
        //  --> Ta có thể lấy (x,y) >0 được: abs(x), abs(y)
        //- Việc ta tìm số min steps từ (x,y) -> (0,0):
        //  + Ta chỉ cần giảm (x,y) đồng thời (1/2) để có thể (x,y) đến được gần (0,0) hơn
        //  ==> Thay vì xét 8 directions thì ta sẽ xét 2 directions (x-1, y-2)/ (x-2, y-1)
        //- Thế còn những cases nó cần go down để có thể reach target.
        //(x+y==2) : Sát (0,0) thì chỉ có cách (go down/ up) 1 lần để có thể return result
        //Ex:
        // 0 0 0 0
        // 0 0 2 0 --> x<0
        // z 0 0 0
        // 0 h 0 0
        // 0 0 0 0
        // 0 0 0 0
        //--> h -> 2 -> z
        //(x+y==2)
        //Ex:
        //0 0 0 0 0
        //0 0 0 0 0 --> x<0
        //0 z 0 0 0
        //0 0 h 0 0
        //2 0 0 0 0
        //0 0 0 0 0
        //^
        //y<0
        //(x+y==2)
        //--> Return 2
        //
        //- Ở đây thêm 1 phần caching liên quan đến hashmap nữa --> Optimize time complexity
        //
        //2.1, Optimization
        //2.2, Complexity
        //Space : O(max(|x|,|y|)(Stack space) + O(|x|*|y|) (The number of cells)
        //Time : O(|x|*|y|)
        //
        //3. Nếu size của array không cho trước |x,y|<=300 thì sao
        //3.0, Idea
        //
        //#Reference:
        //2596. Check Knight Tour Configuration
    }
}
