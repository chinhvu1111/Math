package E1_minimum_spaning_tree;

import java.util.Arrays;

public class E1_ConnectingCitiesWithMinimumCost {

    public static int[] findParent(int[] parent, int node){
        int prevNode=node;
        int depth=1;

        while(parent[node]!=node){
            node=parent[node];
            parent[prevNode]=node;
            depth++;
        }
//        parent[prevNode]=node;
        return new int[]{node, depth};
    }

    public static boolean unionFind(int[] parent, int u, int v){
        int[] parentU=findParent(parent, u);
        int[] parentV=findParent(parent, v);

        if(parentU[0]!=parentV[0]){
            if(parentU[1]>parentV[1]){
                parent[parentV[0]]=parentU[0];
            }else{
                parent[parentU[0]]=parentV[0];
            }
            return true;
        }
        return false;
    }

    public static int minimumCost(int n, int[][] connections) {
        Arrays.sort(connections, (a, b) -> (a[2]-b[2]));
        int totalCost=0;
        int[] parent=new int[n+1];

        //Time : O(n)
        for(int i=1;i<=n;i++){
            parent[i]=i;
        }

        //Time : O(m)
        for(int[] connection: connections){
            //Time : O(n)
            if(unionFind(parent, connection[0], connection[1])){
                totalCost+=connection[2];
            }
        }
        //Time : O(n)
        for(int i=1;i<n;i++){
            //Time : O(n)
            if(findParent(parent, i)[0]!=findParent(parent, i+1)[0]){
                return -1;
            }
        }
        return totalCost;
    }

    public static int minimumCostOptimization(int n, int[][] connections) {
        //Time : O(m*log(m))
        Arrays.sort(connections, (a, b) -> (a[2]-b[2]));
        int totalCost=0;
        //Time : O(n)
        int[] parent=new int[n+1];

        //Time : O(n)
        for(int i=1;i<=n;i++){
            parent[i]=i;
        }
        int totalCity=0;

        //Time : O(m) ==> Time = O(m+n)
        for(int[] connection: connections){
            //Time : O(n)
            if(unionFind(parent, connection[0], connection[1])){
                totalCost+=connection[2];
                totalCity++;
            }
        }
        //Optimization
        if(totalCity!=n-1){
            return -1;
        }

        return totalCost;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- There are (n) cities (1 to n)
        //- Given connections[i] = [xi, yi, costi]
        //+ Cost-i is the cost of connecting (city-i) and (city-j)
        //+ x-i and y-i is bidirectional connection.
        //* Return (minimum cost) to connect all the n cities that there is (at least one path) between (each pair of cities).
        //- If it is impossible to connect all the n cities ==> return -1.
        //
        //** Bài này tư duy như sau:
        //1.
        //1.1, Idea:
        //- Constraint:
        //1 <= n <= 10^4
        //1 <= connections.length <= 10^4
        //connections[i].length == 3
        //1 <= xi, yi <= n
        //xi != yi
        //0 <= cost-i <= 10^5
        //
        //- Brainstorm
        //- If cannot not connect n cities --> there is not an edge between the a and b ==> return -1
        //  + All edges could not be connected each other
        //- Tất cả các edge đều có vai trò như nhau ==> Thế nên việc add 1 edge để graph có thể thông path:
        //+ Ta sẽ ưu tiên add edge có cost min --> max
        //
        //- Có trường hợp nào add min cost mà gây ra việc add thừa thãi không
        //Ex: add 1 edge min vào rồi + sau đó vẫn chưa connected ==> ta add thêm 1 add có cost lớn hơn vào ==> graph connected
        //Mà nếu không có edge min đã add trước đó hay không thì vẫn connected ? (This edge is redundant?)
        //Ex: Suy luận như sau:
        //+ Add edge trước đó là để nối thông các collection với nhau
        //  + Nếu edge đó không có tác dụng / 2 tập hợp đã thông --> Bỏ qua
        //  + Nếu edge đó dùng để nối thông ==> Có tác dụng
        //+ Khi next edge add vào --> Sẽ connect có thể là 2 tập hợp khác (2 tập hợp này sẽ luôn khác 2 tập hợp trước)
        //  + Vì add vào thì graph mới connected ==> Về cơ bản chúng sẽ không trùng nhau.
        //
        //- Bài này sẽ là sort theo cost + union find là xong.
        //
//        int n = 3;
//        int[][] connections = {{1,2,5},{1,3,6},{2,3,1}};
        int n = 4;
        int[][] connections = {{1,2,3},{3,4,4}};
        System.out.println(minimumCost(n, connections));
        //
        //1.1, Optimization
        //- Sửa lại 1 chút update parent của prevNode khi traverse sẽ gán luôn thay vì kết thúc loop mới assign"
        //+ Để tránh phải đi sâu hơn.
        //==================
        //node=parent[node];
        //parent[prevNode]=node;
        //depth++;
        //==================
        //- Ngoài ra ta có N cities ==> Mỗi lần union find ta sẽ add tối đa 1 city
        //==> Nếu [ (the number of city) add != N-1 ] thì coi như là không add đủ city
        //==> Return -1 (Thay vì dùng parent sẽ gây slow down)
        //
        //- Ta cũng có thể đặt if bên trong for để return result luôn
        //==================
        //if(totalCity==n-1){
        //   return totalCost;
        //}
        //==================
        //
        //1.2, Complexity:
        //- Space : O(n)
        //- Time : O(m*log2(n)+ m*log(m))
        //** Reference:
        //- The time complexity of the union-find algorithm is less than O(log n) and is often constant.
        // The time complexity of (normal union find algorithm) is O(N),
        // but when (optimised using the path compression) and (disjoint set),
        // the time complexity is greatly reduced.
        //
        //** Iterated logarithm
        //- Giả sử ta combine 2 trees có cùng (height=h)
        //  + 2 trees lần lượt có 2^h nodes
        //  + Khi combine ta sẽ có new tree với (height=h+1)
        //- Nếu 2 tree có cùng height ==> new height= the (bigger height) node
        //==> 2^h + 2^h = 2^(h+1) nodes
        //
        //- The height is the maximum number of steps để có thể union được thêm new tree
        //+ n >= 2^h ==> log2(n) >= h >= steps.
        //
        //- Trường hợp worst case là ta sẽ phải traverse findParent() sâu (depth=h= log2(n) )
        //==> m lần find
        //* Time = O (m * log2(n))
        //
        //#Reference:
        //2093. Minimum Cost to Reach City With Discounts
    }
}
