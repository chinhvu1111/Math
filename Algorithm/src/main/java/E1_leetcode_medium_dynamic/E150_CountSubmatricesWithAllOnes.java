package E1_leetcode_medium_dynamic;

public class E150_CountSubmatricesWithAllOnes {

    public static int numSubmat(int[][] mat) {
        int n=mat.length;
        int m=mat[0].length;
        int[][] left=new int[n][m];

        for(int i=0;i<n;i++){
            int prevLeft=-1;
            int countLeft=0;

            for(int j=0;j<m;j++){
                if(mat[i][j]!=prevLeft||mat[i][j]==0){
                    countLeft=mat[i][j];
                }else{
                    countLeft+=1;
                }
                left[i][j]=countLeft;
                prevLeft=mat[i][j];
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                System.out.printf("%s,", left[i][j]);
            }
            System.out.println();
        }
        int rs=0;
        for(int i=0;i<m;i++){
            int prevDown=-1;
            int countDown=0;
//            int minLeft=left[0][i];

            for(int j=0;j<n;j++){
                if(left[j][i]!=prevDown){
                    countDown=1;
                }else{
                    countDown+=1;
                }
//                left[j][i]=countDown;
                if(mat[j][i]!=0){
                    rs+=countDown*left[j][i];
                    int minLeft=left[j][i];
                    int k=j-1;
                    boolean isUpdated=false;
                    while (k>=0&&mat[k][i]!=0){
                        minLeft=Math.min(minLeft, left[k][i]);
                        k--;
                        isUpdated=true;
                    }
                    if(isUpdated){
                        rs+=minLeft*(j-k-1);
                    }
                }
                prevDown=left[j][i];
            }
//            rs+=countDown*(countDown+1)/2*minLeft;
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given an m x n binary matrix mat,
        //* Return the number of submatrices that have (all ones).
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= m, n <= 150
        //mat[i][j] is either 0 or 1.
        //- m, n không quá lớn.
        //
        //- Brainstorm
        //Ex:
        //Input: mat =
        // [
        // [1,1,0],
        // [1,1,1],
        // [1,1,1]
        // ]
        //- Biến đổi:
        // [
        // [1,1,0],
        // [1,1,1],
        // [1,1,1],
        // [1,1,0]
        // ]
        //- Rectangle không giống như square:
        //  + Rectangle có (width) and (height)
        //  ==> Biểu diễn ntn?
        //- Ở đây ta sẽ không xét mỗi (i,j)
        //Ex:
        // [
        // [1,1],
        // [1,1],
        // [1,1],
        // [1,1]
        // ]
        //max size = [4,2]
        //==> rs= 6
        //
        //Ex:
        // [
        // [1,1,0],
        // [1,1,0],
        // [1,1,1],
        // [1,1,1]
        // [1,1,1]
        // ]
        //[2,1] size là [3,2]
        //[4,2] size là [2,3]
        //  + [4,2] nó liên quan đến [4,1]
        //- Tính sum left:
        //Ex:
        // [
        // [1,2,0],
        // [1,2,0],
        // [1,2,3],
        // [1,2,3]
        // [1,2,3]
        // ]
        //- nếu 3,3,3 cạnh nhau => Ta có thể có 6 retangles có thể tạo ra với:
        //  + Cùng value
        //=> rs:
        //+ col index=0:
        //  + 1+2+3+4+5 = 15
        //+ col index=1:
        //  + common val = 2
        //  + (1+2+3+4+5)*(1+2)
        //      + Nếu *2 này thì ta có thể bị duplicate với trường hợp val=1 đằng trước
        //      + Ngoài ra thì còn tính cả all 1 tại chính col đó.
        //+ col index=2:
        //  + common val = 3
        //  + (1+2+3)*(1+2+3)
        //  ==> Ntn thì sẽ bị trùng với 2
        //
        //- Ở đây để loại bỏ trùng
        //  + Ta sẽ lấy công thức:
        //  + left*(left+1)/2 * down*(down+1)/2
        //Ex:
        // [
        // [1,0,1],
        // [1,1,0],
        // [1,1,0]
        // ]
        //==>
        //Ex:
        // [
        // [1,0,1],
        // [1,2,0],
        // [1,2,0]
        // ]
        //+ left val = 2: rs+= 2*(2+1)/2 * 2*(2+1)/2 = 9
        //+ left val = 1 : rs+=1 + 3*(3+1)/2 = 1 + 6 = 7
        //==> Nếu tính ntn thì sẽ bị trùng col index=0
        //rs= 3*(3+1)/2 + 2*(2+1)/2*2 + 1 = 6 + 3*2 + 1 = 13
        //--> Ta có thể tính theo từng col một
        //* CT:
        //+  rs = down*(down+1)/2 * current_left + ...
        //- Ta sẽ tính cho 1 tập hợp các left bằng nhau.
        //
        // [
        // [1,1,0],
        // [1,1,1],
        // [1,1,1]
        // [1,1,0]
        // ]
        //Ex:
        //mat = [[0,1,1,0],[0,1,1,1],[1,1,1,0]]
//        int[][] mat =
//                {
//                        {1,0,1},
//                        {1,1,0},
//                        {1,1,0}};
//        int[][] mat =
//                {
//                        {0,1,1,0},
//                        {0,1,1,1},
//                        {1,1,1,0}
//                };
        int[][] mat = {
                {1,1,1,1,0},
                {1,0,0,1,0},
                {0,0,1,0,1},
                {0,1,0,0,0}};
        //0,1,2,0,
        //0,1,2,3,
        //1,2,3,0,
        //+ rs += 1
        //+ rs += 6
        //+ rs += 3*(3+1)/2*2 = 6*2 = 12
        //+ rs += 1
        //==> Tính theo mat là sai ==> Phải tính theo left
        //
        //+ rs+=1
        //+ rs+=1 + 2*1 + 1*2 = 9
        //+ rs+=2*1 + 2*2 + 1*3 = 9
        //+ rs+= 3*1 = 3
        //==> rs=22, expect: 24
        //==> Làm ntn sẽ thiếu cases.
        //-
        //#Reference:
        //2043. Simple Bank System
        //2272. Substring With Largest Variance
        //361. Bomb Enemy
        //
        System.out.println(numSubmat(mat));
    }
}
