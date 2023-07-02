package E1_Graph;

import java.util.*;

public class E22_PacificAtlanticWaterFlow {

    public static List<List<Integer>> pacificAtlanticWrongIdea(int[][] heights) {
        int n=heights.length;
        int m=heights[0].length;
        boolean[][] dpPacific=new boolean[n][m];
        boolean[][] dpAtlantic=new boolean[n][m];

        for(int i=0;i<n;i++){
            int rsPacific=Integer.MIN_VALUE;
            int rsAlantic=Integer.MIN_VALUE;
            HashMap<Integer, Integer> mapIndexPacific=new HashMap<>();
            HashMap<Integer, Integer> mapIndexAlantic=new HashMap<>();

            for(int j=0;j<m;j++){
                if(rsPacific<heights[i][j]){
                    dpPacific[i][j]=true;
                }else if((rsPacific==heights[i][j])){
                    int firstIndex=mapIndexPacific.get(heights[i][j]);
                    boolean isValid=true;
                    for(int h=firstIndex;h<j;h++){
                        if(heights[i][h]!=rsPacific){
                            isValid=false;
                            break;
                        }
                    }
                    dpPacific[i][j]=isValid;
                }
                rsPacific=Math.max(rsPacific, heights[i][j]);
//                System.out.printf("%s %s, %s %s\n",rsPacific, heights[i][j], i, j);
                int k=m-j-1;
                if(rsAlantic<heights[i][k]){
                    dpAtlantic[i][k]=true;
                }else if(rsAlantic==heights[i][k]){
                    int firstIndex=mapIndexAlantic.get(heights[i][k]);
                    boolean isValid=true;
                    for(int h=firstIndex;h>k;h--){
                        if(heights[i][h]!=rsAlantic){
                            isValid=false;
                            break;
                        }
                    }
                    dpAtlantic[i][k]=isValid;
                }
                rsAlantic=Math.max(rsAlantic, heights[i][k]);
                if(!mapIndexPacific.containsKey(heights[i][j])){
                    mapIndexPacific.put(heights[i][j], j);
                }
                if(!mapIndexAlantic.containsKey(heights[i][k])){
                    mapIndexAlantic.put(heights[i][k], k);
                }
            }
        }
        System.out.println(dpPacific[2][1]);
        System.out.println(dpAtlantic[2][1]);
        for(int i=0;i<m;i++){
            int rsPacific=Integer.MIN_VALUE;
            int rsAlantic=Integer.MIN_VALUE;
            HashMap<Integer, Integer> mapIndexPacific=new HashMap<>();
            HashMap<Integer, Integer> mapIndexAlantic=new HashMap<>();

            for(int j=0;j<n;j++){
                if(!dpPacific[j][i]&&rsPacific<heights[j][i]){
                    dpPacific[j][i]=true;
                }else if(!dpPacific[j][i]&&rsPacific==heights[j][i]){
                    int firstIndex=mapIndexPacific.get(heights[j][i]);
                    boolean isValid=true;
                    for(int h=firstIndex;h<j;h++){
                        if(heights[h][i]!=rsPacific){
                            isValid=false;
                            break;
                        }
                    }
                    dpPacific[j][i]=isValid;
                }
                rsPacific=Math.max(rsPacific, heights[j][i]);
                System.out.printf("%s %s, %s %s\n",rsPacific, heights[j][i], j, i);
                int k=n-j-1;
                if(!dpAtlantic[k][i]&&rsAlantic<heights[k][i]){
                    dpAtlantic[k][i]=true;
                }else if(!dpAtlantic[k][i]&&rsAlantic==heights[k][i]){
                    int firstIndex=mapIndexAlantic.get(heights[k][i]);
                    boolean isValid=true;
                    for(int h=firstIndex;h>k;h--){
                        if(heights[h][i]!=rsAlantic){
                            isValid=false;
                            break;
                        }
                    }
                    dpAtlantic[k][i]=isValid;
                }
                rsAlantic=Math.max(rsAlantic, heights[k][i]);

                if(!mapIndexPacific.containsKey(heights[j][i])){
                    mapIndexPacific.put(heights[j][i], j);
                }
                if(!mapIndexAlantic.containsKey(heights[k][i])){
                    mapIndexAlantic.put(heights[k][i], k);
                }
//                System.out.printf("%s %s, %s %s\n",rsAlantic, heights[k][i], k, i);
            }
        }
        System.out.println(dpPacific[2][1]);
        System.out.println(dpAtlantic[2][1]);
        List<List<Integer>> rs=new ArrayList<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(dpPacific[i][j]&&dpAtlantic[i][j]){
                    List<Integer> currentRs=new ArrayList<>();
                    currentRs.add(i);
                    currentRs.add(j);
                    rs.add(currentRs);
                }
            }
        }
        return rs;
    }

    public static int[] dx={-1,0,1,0};
    public static int[] dy={0,1,0,-1};
    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
        HashSet<String> setPacificNodes=new HashSet<>();
        HashSet<String> setAlantic=new HashSet<>();
        Queue<int[]> nodes=new LinkedList<>();
        List<List<Integer>> listRs=new ArrayList<>();
        int n=heights.length;
        int m=heights[0].length;

        for(int i=0;i<m;i++){
            nodes.add(new int[]{0, i});
            setPacificNodes.add(0+"_"+i);
        }
        for(int i=1;i<n;i++){
            nodes.add(new int[]{i, 0});
            setPacificNodes.add(i+"_"+0);
        }
        while (!nodes.isEmpty()){
            int[] currentNode=nodes.poll();
            for(int i=0;i<dx.length;i++){
                int x1=currentNode[0]+dx[i];
                int y1=currentNode[1]+dy[i];
                String neighborNode=x1+"_"+y1;

                if(x1>=0&&x1<n&&y1>=0&&y1<m&&!setPacificNodes.contains(neighborNode)&&heights[x1][y1]>=heights[currentNode[0]][currentNode[1]]){
                    setPacificNodes.add(neighborNode);
                    nodes.add(new int[]{x1, y1});
                }
            }
        }
        for(int i=0;i<m;i++){
            nodes.add(new int[]{n-1, i});
            if(setPacificNodes.contains((n-1)+"_"+i)){
                List<Integer> currentRs=new ArrayList<>();
                currentRs.add(n-1);
                currentRs.add(i);
                listRs.add(currentRs);
            }
            setAlantic.add((n-1)+"_"+i);
        }
        for(int i=0;i<n-1;i++){
            nodes.add(new int[]{i, m-1});
            if(setPacificNodes.contains(i+"_"+(m-1))){
                List<Integer> currentRs=new ArrayList<>();
                currentRs.add(i);
                currentRs.add(m-1);
                listRs.add(currentRs);
            }
            setAlantic.add(i+"_"+(m-1));
        }
//        System.out.println(setPacificNodes);
        while (!nodes.isEmpty()){
            int[] currentNode=nodes.poll();
            for(int i=0;i<dx.length;i++){
                int x1=currentNode[0]+dx[i];
                int y1=currentNode[1]+dy[i];
                String neighborNode=x1+"_"+y1;
//                if(x1>=0&&x1<n&&y1>=0&&y1<m){
//                    System.out.printf("%s %s %s %s\n", neighborNode, heights[x1][y1], heights[currentNode[0]][currentNode[1]],
//                            !setAlantic.contains(neighborNode));
//                }
                if(x1>=0&&x1<n&&y1>=0&&y1<m&&!setAlantic.contains(neighborNode)&&heights[x1][y1]>=heights[currentNode[0]][currentNode[1]]){
                    setAlantic.add(neighborNode);
                    nodes.add(new int[]{x1, y1});
                    if(setPacificNodes.contains(neighborNode)){
                        List<Integer> currentRs=new ArrayList<>();
                        currentRs.add(x1);
                        currentRs.add(y1);
                        listRs.add(currentRs);
                    }
                }
            }
        }
        return listRs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Pacific ocean là edge top left
        //- Pacific ocean là edge bottom right
        //- grid[i][j] là chiều cao trên mực nước biển của cell[i][j]
        //- Có nhiều mưa --> Nước có thể chảy từ nơi có chiều cao cao hơn --> nơi có chiều cao thấp hơn.
        //- Chiều chảy có thể đi 4 hướng (left,right, top, bottom)
        //* Return lại các điểm có thể chảy nước mưa ra cả 2 Pacific ocean và Atlantic ocean.
        //
        //** Idea
        //1.
        //1.0, Brainstorm
        //- Constraints:
        //m == heights.length
        //n == heights[r].length
        //1 <= m, n <= 200
        //0 <= heights[r][c] <= 10^5
        //- Dùng dp[i][j] để lưu kết quả
        //- Với duyệt từ
        //+ [i][0-->n-1] ==> Check alantic (heights[i][j]>=max)
        //+ [0-->m-1][i] ==> Check alantic (heights[i][j]>=max)
        //+ [i][n-1-->0] ==> Check pacific (heights[i][j]>=max)
        //+ [m-1-->0][i] ==> Check pacific (heights[i][j]>=max)
        //==> && 2 kết quả để lấy kết quả cuối cùng
        //** Hiểu sai 1 chút:
        // 5 --> 4 --> 5 : Nước không thể chảy từ 5 --> 5 được
        //Các cases nước không thể chảy:
        //+ 5,2,3,1,4,5 : Do 5 = 5 --> 4 không tăng lên 5 được
        //+ 5,5,5,1,5,5 : case đặc biệt 1 chút so với cases bên trên
        //===> Trường hợp max==current value valid duy nhất là:
        //Ex : 5,5,5,5,1,2,4
        //
        //** Hiểu sai đề lần 2:
        //- Water có thể đi bất cứ đường nào chứ không phải mỗi đường thẳng.
        //- Nếu các neighbors nào < current value --> lan được hết
        //--> Với kiểu này thì có thể dùng BFS : Ta có 200 nodes
        //==> Cần phải traverse tối ưu hơn.
        //+ Nếu grid[i][j] > grid[k][l] mà dp[k][l]=true ==> grid[i][j]=True
        //==> CẦN HIỂU BẢN CHẤT CỦA VIỆC MƯA
        //
//        int[][] heights={{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
//        int[][] heights={
//                {2,1},
//                {1,2}};
//        int[][] heights={
//                {1,2,2,3,5},
//                {3,2,3,4,4},
//                {2,4,5,3,1},
//                {6,7,1,4,5},
//                {5,1,1,2,4}};
//        int[][] heights={
//                {1,2,3},
//                {8,9,4},
//                {7,6,5}};
        int[][] heights={
                {1,4,3},
                {8,1,4},
                {7,5,5}};
        //+ Expected result: [[0,1],[0,2],[1,0],[1,2],[2,0],[2,1],[2,2]]
        //
        //Test case 1:
        //[[1,5,3],
        // [8,1,5],
        // [7,5,5]]
        //+ Expected result : [[0,1],[0,2],[1,0],[1,2],[2,0],[2,1],[2,2]]
        //+ Vẫn có [2,1] vì [2,1] -> [2,2] -> [1,2] -> [0,2]
        //
        //Test case 2:
        //[[1,5,3],
        // [8,1,5],
        // [7,5,4]]
        //+ Expected result : [[0,1],[0,2],[1,0],[1,2],[2,0]]
        //+ Không có [2,1]
        //
        //Test case 3:
        //[[1,5,3],
        // [8,1,2],
        // [7,5,4]]
        //+ Expected result : [[0,1],[0,2],[1,0],[2,0]]
        //+ Không có [2,1]
        //*** : Bản chất của việc mưa <> với việc đổ nước
        //- Mưa thì chỉ có thể chảy tử nhà cao --> nhà thấp
        //==> Bài toán này đơn giản là ta đi từ a -> b -> c miễn là a<=b<=c<=d là được.
        //
        //- Tư duy từ ngoài vào --> add vào all nodes ở rìa vào
        //+ Tất cả các nodes quét được khi chạy từ rìa Pacific trước vào --> =true hết
        //+ Tất cả các nodes bằng true đó sẽ được xét tiếp xem có đi được đến Atlantic hay không
        //
        //- Cách làm:
        //Từ 1 node có thể có rất nhiều paths đi kèm nó
        //--> Tư duy dạng traverse từ rìa Pacific --> rìa Alantic có vẻ hơi rườm ra vì ta cần add all nodes trên đường đi vào result
        //+ Chạy từ rìa vào với Pacific traverse all nodes có thể tìm thấy + add vào hashset
        //- Sẽ có cases nó đi từ Pacific Ocean --> (i,j) và (Atlantic Ocean) --> (i,j) : Là 2 đường khác nhau
        //
        //1.1, Complexity:
        //- Time complexity : O(N*M)
        //+ Traverse two times --> Worst case
        //- Space complexity : O(2*N*M) = O(N*M)
        //+ 2 hashset, 2 queues
        //+ Worst case, space = O(N*M*3) ==> O(N*M)
        //
        System.out.println(pacificAtlantic(heights));
        //#Reference:
        //997. Find the Town Judge
        //2718. Sum of Matrix After Queries
        //1604. Alert Using Same Key-Card Three or More Times in a One Hour Period
        //852. Peak Index in a Mountain Array
    }
}
