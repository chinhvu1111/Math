package interviews;

public class E115_CheckIfThereIsAValidParenthesesStringPath {

//    public static boolean rs=false;
//    public static int dp[][][];
    public static Boolean dp[][][];

    //Cách làm thứ nhất tính left, right --> SAI (Không ai làm mấy bài như thế lại tính số lượng cả)
    public static boolean dfs(char[][] grid, int x, int y, int numberChar){

//        if(x>=grid.length||y>=grid[0].length){
//            return numberChar==0;
//        }

//        if(dp[x][y][numberChar]){
//            return true;
//        }else if(!dp[x][y][numberChar]){
//            return false;
//        }
        if(dp[x][y][numberChar]!=null){
            return dp[x][y][numberChar];
        }
        int newChar=numberChar;
        if(grid[x][y]=='('){
            newChar++;
        }else{
            newChar--;
        }
        if(x==grid.length-1&&y==grid[0].length-1){
            return newChar==0;
        }
        if(newChar<0){
            dp[x][y][numberChar]=false;
            return false;
        }
//        System.out.printf("%s %s %s\n",numberChar, x, y);
        boolean nextResult=false;

        if(x+1<grid.length){
            nextResult=dfs(grid, x+1, y, newChar);
        }
        if(!nextResult&&y+1<grid[0].length){
            nextResult= dfs(grid, x, y+1, newChar);
        }
        return dp[x][y][numberChar]=nextResult;
    }

    public static boolean hasValidPathDFS(char[][] grid) {
        dp=new Boolean[grid.length][grid[0].length][205];
        return dfs(grid, 0, 0, 0);
    }

    public static boolean hasValidPath(char[][] grid) {
        Boolean[][][] dp =new Boolean[grid.length][grid[0].length][205];
        int n=grid.length;
        int m=grid[0].length;

        if(grid[0][0]=='('){
            dp[0][0][1]=false;
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                int addOpenDown=0;
                int addOpenRight=0;

                if(i+1<n&&grid[i+1][j]=='('){
                    addOpenDown=1;
                }else if(i+1<n){
                    addOpenDown=-1;
                }
                if(j+1<m&&grid[i][j+1]=='('){
                    addOpenRight=1;
                }else if(j+1<m){
                    addOpenRight=-1;
                }
                for(int h=0;h<=i+j+1;h++){
//                    if(dp[i][j+1][h]=){
//                        dp[i][j+1][h]=dp[i][j][h+addOpen];
//                    }else{
//                        dp[i][j+1][h]=dp[i][j+1][h]|dp[i][j][h+addOpen];
//                    }
                    if(dp[i][j][h]==null){
                        continue;
                    }
                    if(j+1<m&&h+addOpenRight==0){
//                        dp[i][j+1][h+addOpenRight]=dp[i][j+1][h]|dp[i][j][h];
                        dp[i][j+1][h+addOpenRight]=true;
                    }else if(j+1<m&&h+addOpenRight>0){
                        dp[i][j+1][h+addOpenRight]=false;
                    }
                    if(i+1<n&&h+addOpenDown==0){
                        dp[i+1][j][h+addOpenDown]=true;
                    }else if(i+1<n&&h+addOpenDown>0){
                        dp[i+1][j][h+addOpenDown]=false;
                    }
                }
            }
        }
        return dp[n - 1][m - 1][0]!=null&& dp[n - 1][m - 1][0];
    }

    public static void main(String[] args) {
//        char grid[][]=new char[][]
//                {{'(','(','('}, {')','(',')'}, {'(','(',')'}, {'(','(',')'}};
        char grid[][]=new char[][]{{')',')'}};
        System.out.println(hasValidPathDFS(grid));
        //Bài này tư duy như sau:
        //1, 1 vài những lỗi sai cần nhớ như sau:
        //1.1, Với những bài kiểm tra valid ()()()
        //--> Check số lượng ký tự ( và ) ==> Không có ý nghĩa gì cả
        //--> Bài như thế này chỉ có cách là dùng stack[], hoặc là kiểm tra bằng cách rút gọn hơn:
        //+ Đếm số lượng ký tự ( (numberOpen) ==> Khi gặp ) : Ta sẽ trừ dần số lượng các ký tự đó đi
        //---> Đến cuối nếu (numberOpenBrank==0) : return true.
        //1.2, Các số truyên vào đầu method đệ quy --> Nên immutable vì:
        //+ Ta cần truyền vào nhiều recursive method khác nhau --> Không được phép thay đổi nó.
        //1.3, Bài này có thể tư duy theo những hướng như sau:
        //- Dạng recursion : vì số lượng case sẽ tăng theo cấp số (mũ) ^2 + memo(Lưu kết quả trung gian)
        //- (i,j) --> (i+1,j) / (i, j+1) : Ta có thể chọn theo các tiêu chí ưu tiên để tránh (recusion)
        //1.4, Bài dạng này return khi gặp điểm cuối thì :
        //(i==n-1&&j==m-1) return ;
        //<> ==> Đi theo (right/ down) tiếp.
        //
        //Cách 1 : Dfs + MEMO
        //2, Bài này ta sẽ dùng PP recursion + memo (Lưu kết quả trung gian)
        //2.1,
        //+ dp[i][j][k]
        //Ở đây phần khó nhất là phần (k), khi tư duy ban đầu là tư duy (CHỈ CÓ THỂ LÀM BÀI NÀY BẰNG STACK)
        //--> Mà stack[] làm recursion (Lưu refer rất tệ)
        //--> Nếu dùng string, số lần check VALID (Mỗi step rất nặng do heigh<=2^100)
        ///* SOLUTION:
        //- Ta sẽ dùng phương pháp check VALID thu gọn:
        //+ Đếm số lượng '(' (numberOpen) --> Sau đó trừ dần khi gặp ')' qua mỗi step
        //===> Chạy đến cuối nếu (numberOpen==0) return true.
        //2.2, Tối ưu tiếp:
        //+ left: là số lượng (
        //+ right: là số lượng )
        //if(right > lert) return false; : Chắc chắn invalid.
        //<=> numberOpen<0 --> đã trừ hết ( + thừa ) --> Invalid.
        //
        //3, Dùng Array dạng (Int) more slower so với (Boolean)
        //--> Nếu muốn dùng (boolean) để lưu (tracing)
        //--> Ta nên dùng Object Boolean
        //+ Object Boolean có thể store đến 3 giá trị (false, true, null)
        //==> Trong trường hợp này :
        //CODE:
        //===== CODE rác
        //if(dp[x][y][numberChar]){
//            return true;
//        }else if(!dp[x][y][numberChar]){
//            return false;
//        }
        //===== CODE rác
        //===> Ta reduce code bằng cách:
        //+ if(dp[x][y][numberBrank]!=null) return;
        //+ <> ==null --> xử lý tiếp
        //
        //Cách 2:
        //1, Dùng dynamic programming:
        //1.1, Ở đây ta sẽ dùng scan all giá trị hiệu của (open và closed)
        //+ 0 <= h <= (i+j+1)
        //1.1, Mỗi vị trí (i, j) ta có thể tính cho các vị trị (i+1, j)/ (i, j+1)
        //- Xét đủ từ (i==0 --> n-1, j==0 --> m-1)
        //1.2, Chú ý xét các giá trị ban đầu:
        //- dp[i][j][1]=false khi và khỉ khi (grid[i][j]==')')
        //---> nếu lúc đầu grid[i][j]=='(' ==> dp[i][j][1]=null (Coi như ta không gán giá trị cho nó)
        //===> Luôn luôn invalid (Và không thể dùng nó để tính tiếp).
        //- Nên dùng Boolean Object để có multiple value.
        System.out.println(hasValidPath(grid));
    }
}
