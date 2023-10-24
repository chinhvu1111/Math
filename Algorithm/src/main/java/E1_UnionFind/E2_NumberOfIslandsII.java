package E1_UnionFind;

import java.util.ArrayList;
import java.util.List;

public class E2_NumberOfIslandsII {
    public static int[] dx={-1,0,1,0};
    public static int[] dy={0,1,0,-1};

    public static List<Integer> numIslands2(int n, int m, int[][] positions) {
        //Ex: n=2, m=3
        //Time : O(n*m)
        //Space : O(n*m)
        int[] parents=new int[n*m+1];
        boolean[] visited=new boolean[n*m+1];

        //Time : O(n*m)
        for(int i=1;i<=n*m;i++){
            parents[i]=i;
        }
        int curRs=0;
        List<Integer> rs=new ArrayList<>();

        //Time : O(k)
        //- Mỗi position ta sẽ union find 4 times
        //+ Nhưng tổng số lần ta union find mà hết cận của (n*m) chỉ có thể là (n*m)
        //+ Còn lại các lần sẽ là O(1) vì ta đã đi or chung root or đã đến trước đó rồi.
        //Time : O(n*m+k)
        //
        for(int[] pos: positions){
            int x=pos[0];
            int y=pos[1];
            int curPos=x * m + y+1;

            if(visited[curPos]){
                rs.add(curRs);
                continue;
            }
            int count=0;

            for(int j=0;j<dx.length;j++){
                int x1=x+dx[j];
                int y1=y+dy[j];
                int nextPos=x1 * m + y1+1;
//                System.out.printf("x1: %s, y1: %s\n", x1, y1);

                if(x1>=0&&x1<n&&y1>=0&&y1<m&&visited[nextPos]){
                    //Time : O(n*m*2)
                    if(!unionFind(parents, curPos, nextPos)){
                        count++;
                    }
                }
            }
            curRs=curRs-count+1;
            rs.add(curRs);
            visited[curPos]=true;
        }
        return rs;
    }

    //Time : O(n*m)
    public static int[] findParent(int[] parent, int node){
        int prevNode=node;
        int depth=1;

        while(parent[node]!=node){
            node=parent[node];
            depth++;
        }
        parent[prevNode]=node;
        return new int[]{node, depth};
    }

    public static boolean unionFind(int[] parent, int u, int v){
        int[] parentV=findParent(parent, v);
        int[] parentU=findParent(parent, u);

        if(parentV[0]!=parentU[0]){
            if(parentV[1]>parentU[1]){
                parent[parentU[0]]=parentV[0];
            }else{
                parent[parentV[0]]=parentU[0];
            }
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given matrix empty
        //+ 0 : water
        //+ 1 : land
        //- Init all cells are empty
        //- We perform an add land operation which turn the water into the land
        //- Rule như sau:
        //+ Nếu các land kết hợp với nhau (left, right, up, down) --> merge thành 1 island
        //+ Nếu không kết hợp thì số island tăng lên
        //
        //** Idea
        //1.
        //1.1, Idea
        //- Constraint
        //1 <= m, n, positions.length <= 10^4
        //1 <= m * n <= 10^4
        //positions[i].length == 2
        //0 <= ri < m
        //0 <= ci < n
        //
        //- Brainstorm
        //- Ta có thể làm như nào để có thể lấy được số island mỗi lần change operation?
        //+ Nếu add 1 land vào mà khiến change cả 2 island 2 bên thì --> Kết quả sẽ thay đổi từ 2 --> 1
        //Ex: 1 0 1
        // ==> thay đổi 0 giữa thì kết quả từ (2 --> 1) (1 1 1)
        //Ex : 1 0 0 1
        // ==> Thay đổi 0 giữa thì kết quả từ (1 1 0 1) (2 --> 2) giữ nguyên
        //--> Tức là bài này ta cần phải UNION FIND tìm parent của 4 nodes xung quanh (left, right, top, down)
        //+ Xem parent của chúng sẽ như thế nào?
        //Ex: distinct value= 3 ==> số tập hợp sẽ giảm đi the (number of distinc value -1)
        //
        //- Lấy parent như thế nào cho tối ưu?
        //+ Khoảng cách thôi
        //
        //- UNION FIND duyệt 2 chiều sẽ như thế nào?
        //+ Coi như điểm đầu tiên là root ==> Các điểm sau nếu độc lập thì nó là root của chính nó.
        //
        //- Lưu root như thế nào?
        //+ Có thể lưu theo dạng số (1 --> n)
        //Ex:
        //1  2  3  4  5
        //6  7  8  9  10
        //11 12 13 14 15
        //16 17 18 19 20
        //+ (m) là số column, (n) là số rows
        //(i=1, j=3) : value = (i+1) * m * j+1
        //
        //- Special cases:
//        int m = 3, n = 3;
//        int[][]positions = {{0,0},{0,1},{1,2},{2,1}};

//        int m = 1, n = 1;
//        int[][]positions = {{0,0}};

//        int m = 0, n = 0;
//        int[][]positions = {};

        //8*2+1 ==> 16
//        int m = 8, n = 2;
//        int[][]positions = {{7, 0}};

        //Case nếu trùng --> Add kết quả ở vị trí cũ vào
        int m = 3, n = 3;
        int[][]positions = {{0,0},{0,1},{1,2},{1,2}};
        System.out.println(numIslands2(m, n, positions));
        //
        //1.1, Optimization
        //1.2, Complexity:
        //+ N is the number of rows
        //+ M is the number of columns
        //- Space : O(n*m)
        //- Time : O(n*m*k) ==> Cái này là không đúng vì ta cần detail hơn
        //* Phân tích:
        //- Mỗi position ta sẽ union find 4 times
        //+ Nhưng tổng số lần ta union find mà hết cận của (n*m) chỉ có thể là (n*m)
        //+ Còn lại các lần sẽ là O(1) vì ta đã đi or chung root or đã đến trước đó rồi.
        //Time : O(n*m+k)
        //** Lý giải theo công thức toán:
        //- For T operations, the amortized time complexity of the union-find algorithm
        // (using path compression with union by rank) is O(alpha(T)).
        // Here, α(T)\alpha(T) is the inverse Ackermann function that grows so slowly,
        // that it doesn't exceed 4 for all reasonable T (approximately T < 10^600
        // ).
        // You can read more about the complexity of union-find here.
        //- https://en.wikipedia.org/wiki/Disjoint-set_data_structure#Time_complexity
        // Because the function grows so slowly, we consider it to be O(1).
        //
        //#Reference:
        //2076. Process Restricted Friend Requests
    }
}
