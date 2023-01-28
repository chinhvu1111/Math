package interviews.bytedance;

public class E27_PathWithMaximumGold {

//    public static int[][] dp;
    public static int dxDir[]=new int[]{-1, 0, 1, 0};
    public static int dyDir[]=new int[]{0, 1, 0, -1};

    public static int subMaximumGold(int[][] grid, boolean visited[][], int x, int y, int n, int m){
        int maxGold=Integer.MIN_VALUE;

        for(int i=0;i<dxDir.length;i++){
            int x1=x+dxDir[i];
            int y1=y+dyDir[i];

            if(x1>=0&&x1<n&&y1>=0&&y1<m&&!visited[x1][y1]&&grid[x1][y1]!=0){
                visited[x1][y1]=true;
                maxGold=Math.max(maxGold, subMaximumGold(grid, visited, x1, y1, n, m));
                visited[x1][y1]=false;
            }
        }
//        System.out.println(x+","+y);
        if(maxGold==Integer.MIN_VALUE){
            maxGold=0;
        }
        return maxGold+grid[x][y];
    }

    public static int getMaximumGoldSlow(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
//        dp=new int[n][m];
        boolean[][] visited=new boolean[n][m];
        int rs=Integer.MIN_VALUE;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]!=0){
                    visited[i][j]=true;
                    rs=Math.max(rs, subMaximumGold(grid, visited, i, j, n, m));
                    visited[i][j]=false;
                }
            }
        }
        return rs==Integer.MIN_VALUE?0:rs;
    }

    public static int subMaximumGoldRefactor(int[][] grid, int x, int y, int n, int m){
        int maxGold=Integer.MIN_VALUE;
        int valueOrigin=grid[x][y];
        grid[x][y]=0;

        for(int i=0;i<dxDir.length;i++){
            int x1=x+dxDir[i];
            int y1=y+dyDir[i];

            if(x1>=0&&x1<n&&y1>=0&&y1<m&&grid[x1][y1]!=0){
                maxGold=Math.max(maxGold, subMaximumGoldRefactor(grid, x1, y1, n, m));
            }
        }
        if(maxGold==Integer.MIN_VALUE){
            maxGold=0;
        }
        grid[x][y]=valueOrigin;
        return maxGold+valueOrigin;
    }

    public static int subMaximumGoldRefactor(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
//        dp=new int[n][m];
        int rs=Integer.MIN_VALUE;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]!=0){
                    rs=Math.max(rs, subMaximumGoldRefactor(grid, i, j, n, m));
                }
            }
        }
        return rs==Integer.MIN_VALUE?0:rs;
    }

    public static void main(String[] args) {
        int[][] arr=new int[][]
                {{0,6,0},{5,8,7},{0,9,0}};
//        int[][] arr=new int[][]
//                {{1,0,7},
//                {2,0,6},
//                {3,4,5},
//                {0,3,0},
//                {9,0,20}};
        System.out.println(getMaximumGoldSlow(arr));
        System.out.println(subMaximumGoldRefactor(arr));
        //** Đề bài:
        //- Bài toán tìm số vàng lớn nhất có thể lấy được mà xuất phát từ vị trí nào cũng được.
        //
        //** Bài này tư duy như sau:
        //1.
        //1.1, Bài toán tìm số vàng lớn nhất có thể lấy được mà xuất phát từ vị trí nào cũng được.
        //- Ở đây có vài case cần chú ý như sau:
        //- 5 --> 8 sẽ khác 8 --> 5 vì:
        //+ 5 --> 8 có thể đi được rất nhiều điểm nữa
        //+ 8 --> 5 không thể đi thêm vì bị vây bởi các số 0.
        //+ Ở đây chỉ có thể đệ quy có nhớ về cạnh:
        //VD:
        //Từ 5 --> 8 ==> Vẫn có thể đi được (Do chỉ có 8 --> 5 là chưa đi thôi)
        //==> 5 vẫn có thể lấy kết quả của 8 ==> để 6,7,9 (Từ kết quả của 8 --> 5)
        //
        //+ Ta không dùng mảng [x][y][x1][y1] để lưu khoảng cách
        //==> Ta sẽ chuyển thành dạng 2 chiều dạng [x][y] với x: (0 --> n*m)
        //==> Kể cả ta có dùng mảng 4 chiều --> Không gian nhớ vẫn thế.
        //
        //- Nếu tư duy thực sự không cần đến mức như thế mà liên quan đến thứ tự thì sao?
        //{{0,6,0},
        // {5,8,7},
        // {0,9,0}}
        //
        //6 --> 8 (5,9,7)
        //5 --> 8 (9,7) : Chỗ này cần bỏ kết quả 8 --> 5 đi
        //8 --> 8 (6,5,7,9) : Cần thêm 1 đường nữa là đi lên trên.
        //
        //==> Vậy bài này đơn giản là lúc ta vào ta sẽ Mark điểm vào == true để về sau ta không thể đi tiếp được nữa:
        //+ Việc mở rộng thêm 6
        //+ Hay bỏ 5 đi đều ở mức reset khi traverse xong 1 root.
        //- Ta sẽ sửa lại như sau:
        //+ Thêm đoạn visited[][]
        //==> Cuối cùng thì vẫn là traverse all case.
        //
        //1.2, Tối ưu:
        //- Ta có thể bỏ visited -> Dùng grid[x][y]=0 --> Sau đó reset lại giá trị ban đầu.
        //==> Tối ưu được không gian nhớ
        //1.3, Complexity:
        //- Time complexity = 3^k
        //+ k là số lượng cell có gold
        //+ cell đầu tiên sẽ có 4 neighbor có thể đi được
        //+ Các cell tiếp theo sẽ chỉ có 3 neighbors vì không thể quay về cell cũ
        //+ Time = k (Chính là cái loop bên ngoài n*m) * 4* 3^(k-1) ~ k * 3^(k-2) ~ 3^k
        //+ k -1 : Trừ đi điểm đầu tiên đã đi thì ta có 4 cases xung quanh * 4
        //+ Mỗi neighbors sẽ có 3 cách chọn (do trừ 1 điểm đã đi trước nó)
        //+ 3^k >> k ==> ~ 3^k
        //- Space complexity :
        //+ O(k) : độ sâu của stack (recursion).
        //
        //#Reference:
        //1220. Count Vowels Permutation
        //909. Snakes and Ladders
        //204. Count Primes
        //2303. Calculate Amount Paid in Taxes
    }
}
