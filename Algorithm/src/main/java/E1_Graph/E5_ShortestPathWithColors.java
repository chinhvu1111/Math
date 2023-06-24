package E1_Graph;

import java.util.*;

public class E5_ShortestPathWithColors {

    public static int[] solution(List<Integer>[] graphRedColor, List<Integer>[] graphBlueColor, int n){
        int[][] path =new int[n][2];

        for(int i=0;i<n;i++){
            path[i][0]=Integer.MAX_VALUE;
            path[i][1]=Integer.MAX_VALUE;
        }
        path[0][0]=0;
        path[0][1]=0;
        Deque<Integer> nodes=new LinkedList<>();
        nodes.add(0);
        boolean[][] visitedBlue=new boolean[n][n];
        boolean[][] visitedRed=new boolean[n][n];

        while (!nodes.isEmpty()){
            Integer currentNode=nodes.poll();
            List<Integer> nextRedNodes=graphRedColor[currentNode];
            List<Integer> nextBlueNodes=graphBlueColor[currentNode];
//            System.out.printf("Current node : %s \n",currentNode);

            if(nextBlueNodes!=null&&path[currentNode][0]!=Integer.MAX_VALUE){
                for(Integer nextBlue: nextBlueNodes){
//                    System.out.printf("Current node %s, next node %s\n", currentNode, nextBlue);
                    if(visitedBlue[currentNode][nextBlue]){
                        continue;
                    }
                    visitedBlue[currentNode][nextBlue]=true;
                    int nextValue=1+path[currentNode][0];

                    if(nextValue<path[nextBlue][1]){
                        path[nextBlue][1]=nextValue;
                        nodes.add(nextBlue);
                    }
//                    System.out.printf("Blue %s %s\n", nextBlue, path[nextBlue][1]);
                }
            }
            if(nextRedNodes!=null&&path[currentNode][1]!=Integer.MAX_VALUE){
                for(Integer nextRed: nextRedNodes){
//                    System.out.printf("Current node %s, next node %s\n", currentNode, nextRed);
                    if(visitedRed[currentNode][nextRed]){
                        continue;
                    }
                    visitedRed[currentNode][nextRed]=true;
                    int nextValue=1+path[currentNode][1];

                    if(nextValue<path[nextRed][0]){
                        path[nextRed][0]=nextValue;
                        nodes.add(nextRed);
                    }
//                    System.out.printf("Red %s %s\n", nextRed, path[nextRed][0]);
                }
            }
//            System.out.println(nodes);
        }
        int[] answer=new int[n];

        for(int i=0;i<n;i++){
            int currentValue=Math.min(path[i][0], path[i][1]);

            if(currentValue==Integer.MAX_VALUE){
                answer[i]=-1;
            }else{
                answer[i]=currentValue;
            }
        }
        return answer;
    }

    public static int[] solutionOptimization(List<Integer>[] graphRedColor, List<Integer>[] graphBlueColor, int n){
        int[][] path =new int[n][2];

        for(int i=0;i<n;i++){
            path[i][0]=Integer.MAX_VALUE;
            path[i][1]=Integer.MAX_VALUE;
        }
        path[0][0]=0;
        path[0][1]=0;
        Deque<Integer> nodes=new LinkedList<>();
        nodes.add(0);
        boolean[][] visited=new boolean[n][2];
        visited[0][0]=true;
        visited[0][1]=true;

        while (!nodes.isEmpty()){
            Integer currentNode=nodes.poll();
            List<Integer> nextRedNodes=graphRedColor[currentNode];
            List<Integer> nextBlueNodes=graphBlueColor[currentNode];
//            System.out.printf("Current node : %s \n",currentNode);

            if(nextBlueNodes!=null&&path[currentNode][0]!=Integer.MAX_VALUE){
                for(Integer nextBlue: nextBlueNodes){
//                    System.out.printf("Current node %s, next node %s\n", currentNode, nextBlue);
                    if(visited[nextBlue][1]){
                        continue;
                    }
                    visited[nextBlue][1]=true;
                    int nextValue=1+path[currentNode][0];

                    if(nextValue<path[nextBlue][1]){
                        path[nextBlue][1]=nextValue;
                    }
//                    System.out.printf("Blue %s %s\n", nextBlue, path[nextBlue][1]);
                    nodes.addLast(nextBlue);
                }
            }
            if(nextRedNodes!=null&&path[currentNode][1]!=Integer.MAX_VALUE){
                for(Integer nextRed: nextRedNodes){
//                    System.out.printf("Current node %s, next node %s\n", currentNode, nextRed);
                    if(visited[nextRed][0]){
                        continue;
                    }
                    visited[nextRed][0]=true;
                    int nextValue=1+path[currentNode][1];

                    if(nextValue<path[nextRed][0]){
                        path[nextRed][0]=nextValue;
                    }
//                    System.out.printf("Red %s %s\n", nextRed, path[nextRed][0]);
                    nodes.addLast(nextRed);
                }
            }
//            System.out.println(nodes);
        }
        int[] answer=new int[n];

        for(int i=0;i<n;i++){
            int currentValue=Math.min(path[i][0], path[i][1]);

            if(currentValue==Integer.MAX_VALUE){
                answer[i]=-1;
            }else{
                answer[i]=currentValue;
            }
        }
        return answer;
    }

    public static int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        List<Integer>[] graphRedColor=new ArrayList[n];
        List<Integer>[] graphBlueColor=new ArrayList[n];

        for (int[] redEdge : redEdges) {
            int u = redEdge[0];
            int v = redEdge[1];
            if(graphRedColor[u]==null){
                graphRedColor[u]=new ArrayList<>();
            }
            graphRedColor[u].add(v);
        }
        for (int[] blueEdge : blueEdges) {
            int u = blueEdge[0];
            int v = blueEdge[1];
            if(graphBlueColor[u]==null){
                graphBlueColor[u]=new ArrayList<>();
            }
            graphBlueColor[u].add(v);
        }
        return solutionOptimization(graphRedColor, graphBlueColor, n);
    }

    public static void println(int[] arr){
        for (int j : arr) {
            System.out.printf("%s, ", j);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //** Requirement
        //- Cho redEdges =[[u, v]] cạnh từ u-->v là màu đỏ.
        //- Cho blueEdges =[[u, v]] cạnh từ u-->v là màu xanh.
        //- return answer[] với answer[i] là đường đi ngắn nhất từ 0 --> x sao cho:
        //+ Các màu là xen kẽ nhau giữa (red và blue)
        //** Idea
        //1.
        //1.0,
        //- Constraint:
        //+ 1 <= n <= 100
        //+ 0 <= redEdges.length, blueEdges.length <= 400
        //--> Không quá nhiều
        //
        //- Giả sử ta có 1 path dạng:
        //Test case 1:
        //1 -->(red) 3 -->(blue) 2 -->(red) 4
        //1 -->(red) 6 -->(red) 2 -->(red) 4
        //+ Ta thấy ở 2 và 6 không thoả mãn
        //- Ta thấy 4 sẽ chọn 3 thay vì 6 là cái để tìm tức là:
        //+ Ta phải build graph sao cho mỗi (i) muốn đến (j) --> ta chỉ quét color khác với color của [i, j]
        //VD: i --> j ==> color (red) thì ta sẽ chỉ lấy path của các (blue color) của 2.
        //- Phương pháp ở đây sẽ là top down:
        //+ Bottom up : node=i sẽ đi chọn các điểm trước đó.
        //+ Ta sẽ chọn giá trị min path nhất trong các path đó --> Cùng loại.
        //- Ở mỗi node ta sẽ tìm được các path (red/ blue) --> Nó sẽ phụ thuộc vào node đang connect đến
        //VD:
        //Test case 2:
        //0 -->(blue) 1 -->(red) 3 -->(blue) 2 -->(red) 4
        //0 --> (blue)5 --> (red) 2 --> (red) 4
        //==> Ở đây ta sẽ lấy kết quả last=(red)
        //- Mỗi điểm chỉ có 1 giá trị min của red và blue
        //1.1, Implementation
        //- Mỗi điểm chỉ có 1 giá trị min của red và blue
        //--> min path[2] là được
        //- Việc traverse thì sẽ để đồ thị.
        //- Build graph:
        //+ Đang dùng dạng (u, v) + khác color --> cần build sao để access ngay các (adj nodes) là red or blue
        //+ Build list
        //
        //1.2, Special cases:
        //- path[currentNode][1]==Integer.MAX_VALUE : continue (Tức là điểm đang xét không thể đi từ 0 đến được)
        //- Phải tại 2 array visited truy vết (visitedBlue, visitedRed)
        //+ Vì sẽ có case, các cạnh red và blue chung (u, v) nhưng vẫn tách bạch nhau:
        //VD:
        //int[][]redEdges = {{0,1},{1,2},{2,3},{3,4}};
        //int[][] blueEdges = {{1,2},{2,3},{3,1}};
        //1.3, Optimization
        //- Ta có thể làm ngắn đoạn visited[n][n] đi vì:
        //+ 1 node đã đến là blue thì node đó không cần phải retraverse lại nữa
        //--> Ta sẽ dùng visited[n][2] thay vì là tạo 2 visited[n][n] của blue và red riêng rẽ.
        //<=> Không cần định nghĩa visited là từ node u --> v theo từng color nữa.
        //
        //- solution(List<Integer>[] graphRedColor, List<Integer>[] graphBlueColor, int n) : Solution chưa tối ưu do vẫn dùng 2 visited[n][n] (blue và red)
        //- solutionOptimization(List<Integer>[] graphRedColor, List<Integer>[] graphBlueColor, int n) : Solution tối ưu hơn 1 chút với cách dùng 1 visited
        //
        //Method 2:
        //2.
        //2.0,
        //-
        //#Reference:
        //1376. Time Needed to Inform All Employees
        //2421. Number of Good Paths
        //226. Invert Binary Tree
        //323. Number of Connected Components in an Undirected Graph

//        int n = 5;
        //Test case 1:
        //- Test case bình thường
//        int[][]redEdges = {{0,1}};
//        int[][] blueEdges = {{2,1}};
        // 0 (red)--> 1
        // 2 (blue)--> 1
        //Test case 2:
        //- Test case 2 blue và red path tạo thành 1 đường
//        int[][]redEdges = {{0,1}};
//        int[][] blueEdges = {{1,2}};
        // 0 (red)--> 1 (blue)--> 2
        //Test case 3:
        //- Test case 3 chỉ có red không có blue
//        int[][] redEdges = {{0,1},{1,2}};
//        int[][] blueEdges = {};
        // 0 (red)--> 1 (red)--> 2
        //Test case 4:
//        int n=3;
//        int[][] redEdges = {{0,1},{0,2}};
//        int[][] blueEdges = {{1,0}};
        // 0 (red)--> 1, 0 (red)--> 2
        // 1 (blue)--> 0
//        int[][]redEdges = {{0,1},{1,2},{2,3},{3,4}};
//        int[][] blueEdges = {{1,2},{2,3},{3,1}};
        //Test case 5:
        //- Test case thể hiện việc visited[node][1] đánh dấu qua 1 lần --> Có thể tìm được đáp án nếu về sau
        //VD: node --> 1, node1 --> 1 tốt hơn chẳng hạn.
        //0 -->[ (blue) 1 ] --> (blue) 2 : Ta thấy ở đây 2 có thể đi đến được nhờ luồng bên dưới
        //0 --> (red) 3 --> (blue) 5 --> [ (red) 1 ]  --> Case trên chỉ ok khi vào 1 là (red) ==> Mãi về sau ta mới có ((red) 1)
        //==> Case này vẫn đúng.
        //0 -->[ (blue) 1 ] --> (blue) 2 : Ta thấy ở đây 2 có thể đi đến được nhờ luồng bên dưới
        //0 --> (red) 3 --> (blue) 5 --> [ (red) 1 ]  --> Case trên chỉ ok khi vào 1 là (red) ==> Mãi về sau ta mới có ((red) 1)
        //
//        int n=6;
//        int[][] redEdges = {{0, 3}, {5, 1}};
//        int[][] blueEdges = {{0, 1}, {1, 2}, {3, 5}};
        //0, 1, 2, 3, 4, 5
        //0, 1, 4, 1, -1, 2
        //Test case 6:
        //0 -(blue)-> 1 -(red)-> 1 -(bue)-> 3
        //0 -(blue)-> 2
        //Expected result :
        //0, 1, 2, 3
        //0, 1, 1, (3) lệch đơn vị ==> 2
        int n=4;
        int[][] redEdges = {{1, 1}};
        int[][] blueEdges = {{0, 1}, {1, 3}, {0, 2}};
        //0, 1, 2, 3
        //0, 1, 1, -1
        int[] answer=shortestAlternatingPaths(n, redEdges, blueEdges);
        println(answer);
    }
}
