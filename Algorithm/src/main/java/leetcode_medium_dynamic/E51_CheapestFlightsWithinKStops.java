package leetcode_medium_dynamic;

import java.util.*;

public class E51_CheapestFlightsWithinKStops {

    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int dp[][]=new int[n][k+2];
        int arr[][]=new int[n][n];
        boolean visited[][]=new boolean[n][n];
//        int depth[]=new int[n];
        LinkedList<Integer> queue=new LinkedList<>();
        queue.add(src);
        List<Integer>[] nextNodes=new List[n];

        for(int i=0;i<flights.length;i++){
            arr[flights[i][0]][flights[i][1]]=flights[i][2];
            if(nextNodes[flights[i][0]]==null){
                nextNodes[flights[i][0]]=new ArrayList<>();
            }
            nextNodes[flights[i][0]].add(flights[i][1]);
        }
        //Chỗ này phải giảm tăng k+1 --> k+2 vì:
        //1, Ban đầu xuất phái từ (0 -> 1) sẽ được phép cộng thêm 1
        //--> Nếu không cộng thì ct:
        //dp[i][depth[i]]=dp[p][depth[p]]+arr[p][i]; --> Sẽ bị sai (Không theo rule qua các vòng lặp khác nhau)
        //2, Nếu giảm depth[src]=-1; --> Sẻ bị âm công thức bên trên
//        depth[src]=0;
        queue.add(src);

        for(int i=0;i<=k+1;i++){
            dp[src][i]=-1;
        }

        while (!queue.isEmpty()){
            int p=queue.pop();
//            System.out.println(p);

            for(int i=0;i<n;i++){
                //case 3:
                //Nếu dùng visited[i][j] --> Trong đồ thị 2 chiều
                //Sẽ bị cây quay trở lại (1) --> (2) : lần 1 chưa tối ưu (Do Path --> 1 chưa tối ưu)
                //Sau khi đi lại (1) --> Path (1) tối ưu --> Path (2) mới tối ưu
                if(arr[p][i]!=0&&!visited[p][i]){
                    boolean isAdapt=false;
//                    int temp=dp[i][depth[p]+1];
////                    dp[i][depth[i]]=dp[p][depth[p]]+arr[p][i];
//                    dp[i][depth[p]+1]=Math.min(dp[i][depth[p]+1], dp[p][depth[p]]+arr[p][i]);
//                    if(temp==dp[i][depth[p]+1]){
//                        depth[i]=depth[p]+1;
//                    }
                    //case 1: Ở đây có case nó comeback lại với dp[src][i]=-1 --> Thay thế bằng số != -1
                    //--> Từ điểm src --> Tính tiếp đôi khi sẽ gây sai
                    for(int j=0;j<=k;j++){
                        if(dp[p][j]==-1){
                            dp[i][j+1]=arr[p][i];
                        }else if(dp[p][j]!=0&&dp[i][j+1]!=0&&dp[i][j+1]!=-1){
                            int temp=dp[i][j+1];
                            //case 2: Có 1 case đặc biệt ở đây khi đồ thị  có chu kỳ --> Nó sẽ quay lại điểm đã đi qua
                            //Và update ngược lại giá trị + không check lại lấy min nhất
                            //--> Sinh ra từ điểm đó đi đến các điểm bên cạnh sẽ không tối ưu
                            //Giải thích:
                            //- Những điểm [i][j]=0 --> Không cần xét vì (Đường đi đến điểm đó không tồn tại)
                            //- dp[i][j+1]!=0 --> Để dùng điều kiện check min --> Không bị sai
                            //- dp[i][j+1]!= -1 --> Tránh update lại src chắc chăn là đường ngắn nhất (src chính là src)
                            dp[i][j+1]=Math.min(dp[i][j+1], dp[p][j]+arr[p][i]);
                            if(temp!=dp[i][j+1]){
                                isAdapt=true;
                            }
                        }else if(dp[p][j]!=0&&dp[i][j+1]!=-1){
                            dp[i][j+1]=dp[p][j]+arr[p][i];
                        }
                    }
                    if(isAdapt&&nextNodes[i]!=null){
//                        for(int j=0;j<n;j++){
//                            visited[i][j]=false;
//                        }
                        for(int j=0;j<nextNodes[i].size();j++){
                            visited[i][nextNodes[i].get(j)]=false;
                        }
                    }
                    queue.add(i);
                    visited[p][i]=true;
                }
            }
        }

        int rs=Integer.MAX_VALUE;
        for(int i=0;i<=k+1;i++){
            if(dp[dst][i]!=0){
                rs=Math.min(dp[dst][i], rs);
            }
        }
        return (rs==Integer.MAX_VALUE)?-1:rs;
    }

    public static int findCheapestPrice1(int n, int[][] flights, int src, int dst, int k) {
        int[][] dp = new int[k+2][n];
        for(int i=0; i<=k+1; i++){
            Arrays.fill(dp[i],Integer.MAX_VALUE);
        }
        //fly from src to scr cost 0
        for(int i=0; i<=k+1; i++){
            dp[i][src] = 0;
        }

        for(int i=1; i<=k+1; i++){
            for(int[] f: flights){
                if(dp[i-1][f[0]]!=Integer.MAX_VALUE){
                    dp[i][f[1]] = Math.min(dp[i][f[1]],dp[i-1][f[0]]+f[2]);
                }
            }
        }
        return dp[k+1][dst] == Integer.MAX_VALUE ? -1 : dp[k+1][dst];
    }

    public static int findCheapestPrice2(int n, int[][] flights, int src, int dst, int K) {

        // Build the adjacency matrix
        int adjMatrix[][] = new int[n][n];
        for (int[] flight: flights) {
            adjMatrix[flight[0]][flight[1]] = flight[2];
        }

        // Shortest distances array
        int[] distances = new int[n];

        // Shortest steps array
        int[] currentStops = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(currentStops, Integer.MAX_VALUE);
        distances[src] = 0;
        currentStops[src] = 0;

        // The priority queue would contain (node, cost, stops)
        PriorityQueue<int[]> minHeap = new PriorityQueue<int[]>((a, b) -> a[1] - b[1]);
        minHeap.offer(new int[]{src, 0, 0});

        while (!minHeap.isEmpty()) {

            int[] info = minHeap.poll();
            int node = info[0], stops = info[2], cost = info[1];

            // If destination is reached, return the cost to get here
            if (node == dst) {
                return cost;
            }

            // If there are no more steps left, continue
            if (stops == K + 1) {
                continue;
            }

            // Examine and relax all neighboring edges if possible
            for (int nei = 0; nei < n; nei++) {
                if (adjMatrix[node][nei] > 0) {
                    int dU = cost, dV = distances[nei], wUV = adjMatrix[node][nei];

                    // Better cost?
                    if (dU + wUV < dV) {
                        minHeap.offer(new int[]{nei, dU + wUV, stops + 1});
                        distances[nei] = dU + wUV;
                    }
                    else if (stops < currentStops[nei]) {
                        minHeap.offer(new int[]{nei, dU + wUV, stops + 1});
                    }
                    currentStops[nei] = stops;
                }
            }
        }

        return distances[dst] == Integer.MAX_VALUE? -1 : distances[dst];
    }

    public static int findCheapestPriceRecode(int n, int[][] flights, int src, int dst, int K) {
        // Build the adjacency matrix
        int adjMatrix[][] = new int[n][n];
        for (int[] flight: flights) {
            adjMatrix[flight[0]][flight[1]] = flight[2];
        }

        // Shortest distances array
        int[] distances = new int[n];

        // Shortest steps array
        int[] currentStops = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(currentStops, Integer.MAX_VALUE);
        distances[src] = 0;
        currentStops[src] = 0;

        // The priority queue would contain (node, cost, stops)
        PriorityQueue<int[]> minHeap = new PriorityQueue<int[]>((a, b) -> a[1] - b[1]);
        minHeap.offer(new int[]{src, 0, 0});

        while(!minHeap.isEmpty()){
            int currentCode[]=minHeap.poll();
            int node=currentCode[0], cost=currentCode[1], preNodes=currentCode[2];

            // If destination is reached, return the cost to get here
            if (node == dst) {
                return cost;
            }

            // If there are no more steps left, continue
            if (preNodes == K + 1) {
                continue;
            }

            for(int i=0;i<n;i++){
                int adj=adjMatrix[node][i];

                if(adj>0){
                    //Distance chỉ dùng để compare --> Không dùng để tính gì
                    //--> Vì current distance of node --> Poll ra có rồi
                    if(adj + cost< distances[i]){
                        distances[i]=adj + cost;
                        //Mục đích của việc lưu này sẽ lưu 3 biến:
                        //+ Điểm hiện tại (i)
                        //+ distance[i] khoảng cách ngắn nhất đến điểm (i)
                        //+ Số điểm đã qua khi đến (i)
                        //minHeap --> Sắp xếp lại toàn bộ theo thứ tự tăng dần của distance[i] (Lấy min)
                        minHeap.offer(new int[]{i, distances[i], preNodes+1});

                        //case 1:
                        //Chỗ này sẽ tối ưu cho việc push ít node vào hơn ở bên dưới
                        currentStops[i]=preNodes;
                        continue;
                    }
                    //Case có thể đến (i) sẽ mất weight nhiều hơn + (số điểm đã qua nhỏ hơn)
                    //--> Có thể xảy ra cases:
                    //+ distance[i] nhỏ nhưng (ko đi được tiếp) --> (Không đến được đich) --> Vô ích
                    //+ distance[i] lớn nhưng (đi được tiếp) --> (Đến được đích)
                    else if(preNodes< currentStops[i]){
                        minHeap.offer(new int[]{i, distances[i], preNodes+1});
                    }
                    //Nếu cả 2 đều lớn hơn distance và currenStops[i] (Min nhất trong tất cả trường hợp)
                    //Chỉ dùng để compare --> Tối ưu 1 chút về việc push vào hay không bên trên
                    //--> Tức là giá trị (Số điểm đã qua tại (i) đang xét lớn hơn giá trị trước dó xét
                    // + distance[i] > distance (Trước đó xét) --> Không cần xét
                    //case 1:
//                    currentStops[i]=Math.min(currentStops[i], preNodes);
                    //--> Sai test case 48:
                    //Vẫn có khả năng:
                    // --> Ta lấy (i) có các điểm trước đó lớn --> Việc luôn xét min là sai
                    //MIN: Ở đấy là với các trường hợp (distance cũng min) thì mới đúng
                    //Nếu (số điểm đã qua min) + (distance không min) --> Vỗ nghĩa
                    currentStops[i]=Math.min(preNodes, currentStops[i]);
                }
            }
        }

        return distances[dst] == Integer.MAX_VALUE? -1 : distances[dst];
    }

    public static void main(String[] args) {
//        int[][] flights = {{0,1,100},{1,2,100},{0,2,500}};
//        int n=3;
//        int src = 0, dst = 2, k = 1;
//        int[][] flights = {{3,4,4},{2,5,6},{4,7,10},{9,6,5},{7,4,4},{6,2,10},{6,8,6},{7,9,4},{1,5,4},{1,0,4},{9,7,3},{7,0,5},{6,5,8},{1,7,6},{4,0,9},{5,9,1},{8,7,3},{1,2,6},{4,1,5},{5,2,4},{1,9,1},{7,8,10},{0,4,2},{7,2,8}};
//        int n=10;
//        int src = 6, dst = 0, k = 7;
        //case 2:
//        int[][] flights = {{10,14,43},{1,12,62},{4,2,62},{14,10,49},{9,5,29},{13,7,53},{4,12,90},{14,9,38},{11,2,64},{2,13,92},{11,5,42},{10,1,89},{14,0,32},{9,4,81},{3,6,97},{7,13,35},{11,9,63},{5,7,82},{13,6,57},{4,5,100},{2,9,34},{11,13,1},{14,8,1},{12,10,42},{2,4,41},{0,6,55},{5,12,1},{13,3,67},{3,13,36},{3,12,73},{7,5,72},{5,6,100},{7,6,52},{4,7,43},{6,3,67},{3,1,66},{8,12,30},{8,3,42},{9,3,57},{12,6,31},{2,7,10},{14,4,91},{2,3,29},{8,9,29},{2,11,65},{3,8,49},{6,14,22},{4,6,38},{13,0,78},{1,10,97},{8,14,40},{7,9,3},{14,6,4},{4,8,75},{1,6,56}};
//        int n=15;
//        int src = 1, dst = 4, k = 10;
        //case 3:
        int[][] flights = {{0,1,100},{0,2,100},{0,3,10},{1,2,100},{1,4,10},{2,1,10},{2,3,100},{2,4,100},{3,2,10},{3,4,100}};
        int n=5;
        int src = 0, dst = 4, k = 3;

        //Bài này tồn tại khá nhiều cases đặc biệt:
        //Ta tư duy như sau:
        //Cách 1:
        //Vì tìm đường đi ngắn nhất từ src --> dst qua k điểm
        //1, Tư tưởng là sẽ lưu tại mỗi node(i)[k bậc]
        //dp[i][0..k] thể hiện là độ dài đường đi ngắn nhất khi đến (i) qua k node trước đó
        //+ Không tính node src, dst
        //1.1, Vì không tính node(src) --> Giá trị của src phải khác các node còn lại dp[src][i...]=-1
        //1.2, Ta thấy rằng khi dp[src][0] --> dp[i][j+1: 1] mà:
        //Ở (i) ta chưa đi qua đỉnh nào --> dp[i][j: 0:k+1] sẽ là đường đi ngắn nhất đến (i) qua (k) node trước đó
        //--> Quy luật này sẽ không áp dụng với src: dp[src][0] --> (-1)
        //2, Bài này ta sẽ dùng BFS để duyệt:
        //2.1, Vì phải update all (bậc node trước đó) --> while(queue.isEmpty) + for(0 --> k+1)
        //3, Có các cases đặc biệt như sau:
        //case 1: Ở đây có case nó comeback lại với dp[src][i]=-1 --> Thay thế bằng số != -1
        //--> Từ điểm src --> Tính tiếp đôi khi sẽ gây sai
//        if(dp[p][j]==-1){
//            dp[i][j+1]=arr[p][i];
        //--> Ta phải xét xem điểm hiện tại pop ==-1 không --> ==-1 (src xét riêng + không update)
        //case 2: Có 1 case đặc biệt ở đây khi đồ thị  có chu kỳ --> Nó sẽ quay lại điểm đã đi qua
        //Và update ngược lại giá trị + không check lại lấy min nhất
        //--> Sinh ra từ điểm đó đi đến các điểm bên cạnh sẽ không tối ưu
        //Giải thích:
        //- Những điểm [i][j]=0 --> Không cần xét vì (Đường đi đến điểm đó không tồn tại)
        //- dp[i][j+1]!=0 --> Để dùng điều kiện check min --> Không bị sai
        //- dp[i][j+1]!= -1 --> Tránh update lại src chắc chăn là đường ngắn nhất (src chính là src)
        //Code:
        //else if(dp[p][j]!=0&&dp[i][j+1]!=0&&dp[i][j+1]!=-1){
        //}else if(dp[p][j]!=0&&dp[i][j+1]!=-1){ --> Tránh trường hợp (-1) + arr[i][p]
        //=-1 (Do điểm tính tiếp chính là src)
        //case 3:
        //Nếu dùng visited[i][j] --> Trong đồ thị 2 chiều
        //Sẽ bị cây quay trở lại (1) --> (2) : lần 1 chưa tối ưu (Do Path --> 1 chưa tối ưu)
        //Sau khi đi lại (1) --> Path (1) tối ưu --> Path (2) mới tối ưu
        //Solution:
        //+ Update lại all tất cả các điểm trong trường hợp ta tính min tức là có xét a=Math.min(a, dp+arr) --> isAdapt=true
//        if(isAdapt&&nextNodes[i]!=null){
////                        for(int j=0;j<n;j++){
////                            visited[i][j]=false;
////                        }
//            for(int j=0;j<nextNodes[i].size();j++){
//                visited[i][nextNodes[i].get(j)]=false;
//            }
//        }
        //Cách 2:
        //Ta tính từng bậc 1:
        //- Nếu bậc 1 thì đường đi ngắn nhất để đến các điểm là bn?
        //- Sau đó tính bậc 2 theo bậc 1
        //--> Vẫn chậm
        //Cách 3:
        // Dùng Priority queue: Mục đích là chỉ lấy những cost[i] min nhất để tính
        // Xem bên trên

        System.out.println(findCheapestPrice(n, flights, src, dst, k));
        System.out.println(findCheapestPrice1(n, flights, src, dst, k));
        System.out.println(findCheapestPrice2(n, flights, src, dst, k));
        System.out.println(findCheapestPriceRecode(n, flights, src, dst, k));
    }
}
