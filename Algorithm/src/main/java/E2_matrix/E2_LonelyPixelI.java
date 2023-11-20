package E2_matrix;

public class E2_LonelyPixelI {

    public static int findLonelyPixel(char[][] picture) {
        int n=picture.length;
        int m=picture[0].length;
        int row[]=new int[n];
        int col[]=new int[m];

        for(int i=0;i<n;i++){
            int numBVal=0;

            for(int j=0;j<m;j++){
                if(picture[i][j]=='B'){
                    numBVal++;
                }
            }
            row[i]=numBVal;
        }
        for(int i=0;i<m;i++){
            int numBVal=0;

            for(int j=0;j<n;j++){
                if(picture[j][i]=='B'){
                    numBVal++;
                }
            }
            col[i]=numBVal;
        }
        int rs=0;

//        for(int i=0;i<n;i++){
//            boolean isValid=false;
//            for(int j=0;j<m;j++){
//                if(row[i]==1&&col[j]==1){
//                    isValid=true;
//                    break;
//                }
//            }
//            if(isValid){
//                rs++;
//            }
//        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(row[i]==1&&col[j]==1&&picture[i][j]=='B'){
                    rs++;
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given an m x n picture consisting of black 'B' and white 'W' pixels
        //* Return the number of black lonely pixels.
        //  + Tức là số lượng B đứng 1 mình trong cả column và row
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //m == picture.length
        //n == picture[i].length
        //1 <= m, n <= 500
        //picture[i][j] is 'W' or 'B'.
        //
        //- Brainstorm
        //- Tư tưởng là chia thành 2 arrays:
        //- array 1 : đếm số B mỗi row
        //- array 2 : Đếm số B mỗi column
        //- Nếu kết hợp 2 array đó từng đôi một
        //  + Với value==1 từ cả 2 array kết hợp với nhau --> Ta sẽ suy ra được cell lonely
        //  count++
        //  + Suy được toạ độ luôn
        //==> WRONG
        //  + Vì nó sẽ bị cùng nhiều row (row[j]) ==> ta sẽ (trỏ vào cùng 1 col[j]==1)
        //  ==> rs sẽ bị sai (Nhiều hơn)
        //
        //- Idea
        //- Duyệt từng cells:
        //  + col[i]==1&&row[j]==1&&picture[i][j]=='B':
        //  rs++
        //  --> Như thế này thì ta sẽ cho kết quả chính xác vì:
        //  + Xét theo từng cell
        //
        //1.1, Optimization
        //
        //1.2, Complexity:
        //- N is the number of row
        //- M is the number of column
        //- Space: O(n+m)
        //- Time : O(n*m)
        //
        //#Reference:
        //533. Lonely Pixel II
        //
    }
}
