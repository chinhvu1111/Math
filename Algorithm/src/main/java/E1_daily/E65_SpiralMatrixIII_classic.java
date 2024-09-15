package E1_daily;

public class E65_SpiralMatrixIII_classic {
    public static int[][] dirXY={{0,1},{1,0},{0,-1},{-1,0}};

    public static int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        int dir=1;
        int r=2;
        int x=rStart;
        int y=cStart+1;
        int[][] rs=new int[rows*cols][2];
        rs[0][0]=rStart;
        rs[0][1]=cStart;
        int traversedCellNum=1;

        if(y<cols){
            rs[traversedCellNum][0]=x;
            rs[traversedCellNum][1]=y;
            traversedCellNum++;
        }
        int traversedCellNumCur=1;

        while(traversedCellNum<rows*cols){
            while (traversedCellNumCur<(r*2-1)*4-4){
                int[] curDir=dirXY[dir];
                x = x+curDir[0];
                y = y+curDir[1];
//                System.out.printf("x: %s, y: %s\n", x, y);
                if(x>=0&&x<rows&&y>=0&&y<cols){
//                    if(Math.abs(x-rStart)==r-1&&Math.abs(y-cStart)==r-1&&traversedCellNum!=(r*2-1)*4-5){
//                        dir=(dir+1)%4;
//                    }
                    rs[traversedCellNum][0]=x;
                    rs[traversedCellNum][1]=y;
                    traversedCellNum++;
                }
                traversedCellNumCur++;
                if(((Math.abs(x-rStart)==r-1&&Math.abs(curDir[0])==1)||(Math.abs(y-cStart)==r-1&&Math.abs(curDir[1])==1))&&traversedCellNumCur!=(r*2-1)*4-4){
                    dir=(dir+1)%4;
                }
            }
            int[] curDir=dirXY[dir];
            x = x+curDir[0];
            y = y+curDir[1];
//            System.out.printf("new: x: %s, y: %s\n", x, y);
            if(x>=0&&x<rows&&y>=0&&y<cols){
                rs[traversedCellNum][0]=x;
                rs[traversedCellNum][1]=y;
                traversedCellNum++;
            }
            r++;
            if(((Math.abs(x-rStart)==r-1&&Math.abs(curDir[0])==1)||(Math.abs(y-cStart)==r-1&&Math.abs(curDir[1])==1))){
                dir=(dir+1)%4;
            }
            traversedCellNumCur=1;
        }
        return rs;
    }

    public static int[][] spiralMatrixIIIOptimization(int rows, int cols, int rStart, int cStart) {
        int[][] dir = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        int[][] traversed = new int[rows * cols][2];
        int idx = 0;

        for(int step=1, direction=0; idx<rows*cols;){
            for(int i=0;i<2;i++){
                //Đi hết 1 hướng
                for(int j=0;j<step;j++){
                    if(rStart>=0&&rStart<rows&&cStart>=0&&cStart<cols){
                        traversed[idx][0]=rStart;
                        traversed[idx][1]=cStart;
                        idx++;
                    }
                    rStart+=dir[direction][0];
                    cStart+=dir[direction][1];
                }
                //Chuyển hướng
                direction = (direction + 1) % 4;
            }
            step++;
        }
        return traversed;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You start at the cell (rStart, cStart) of an rows x cols grid facing east.
        //- (The northwest corner) is at (the first row and column) in the grid,
        // and (the southeast corner) is (at the last row and column).
        //- You will walk in (a clockwise spiral shape) to visit every position in this grid.
        // Whenever you move outside the grid's boundary, we continue our walk outside the grid (but may return to the grid boundary later.).
        // Eventually, we reach all rows * cols spaces of the grid.
        //* Return (an array of coordinates) representing (the positions of the grid) in the order you visited them.
        //Ex:
        //16 7  8   9
        //15 6  1   2
        //14 5  4   3
        //13 12 11 10
        //1 -> 2 -> 3 -> 4 -> 5 -> 6 ->9
        //-> out -> out -> out -> 10 -> 11 ->...
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= rows, cols <= 100
        //0 <= rStart < rows
        //0 <= cStart < cols
        //+ Size k quá lớn
        //
        //- Brainstorm
        //- Traverse in spiral way:
        //  + Lấn ra ngoài
        //  + Size của spiral sẽ lớn dần theo đường đi
        //- Vì lấn ra ngoài:
        //  + Có thể chọn array có size lớn hơn cần thiết --> Đi được ra ngoài
        //  + Tính index chứ không cần đi thật sự.
        //
        //- Khi nào thi ta đi vòng tiếp:
        //  + Khi vòng trước đã đi hết:
        //      + Số lượng ô đã đủ
        //- Các ô sẽ được traverse xuay quanh start point với:
        //  + Bán kính tăng dần là được
        //
        //- Khi nào kết thúc:
        //  + Đi được hết các ô trong grid
        //- Khi đi hết các cell trong 1 turn:
        //  + Point kết thúc sẽ tiếp tục di chuyển same direction 1 unit để mở rộng bán kính
        //- Ta chỉ cần chuyển direction đúng + bán kính thoả mãn + số lượng ô là được
        //  + Số lượng ô cần đi 1 turn:
        //      = (2*r-1)^2
        //
        //- Directions:
        //  + left to right: (0,1)
        //  + top to down: (1,0)
        //  + right to left: (0,-1)
        //  + down to top : (-1,0)
        //  ==> direction: 0 -> 3
        //
        //- Điều kiện để change direction:
        //  + Điểm hiện tại có khoảng cách (x or y) đến (startR, startC) == r-1
        //  + Nếu |x-startR| == r-1 && (phải đang move theo hướng x)
        //  + Nếu |y-startC| == r-1 && (phải đang move theo hướng y)
        //
        //*NOTE:
        //- Cần tính cả start point ==> Count vào
        //- Lần cuối cùng sẽ không change direction.
        //
        //1.1, Optimization
        //- Ta có thể làm nó dễ hơn:
        //  + direction = 0 -> East, direction = 1 -> South
        //  + direction = 2 -> West, direction = 3 -> North
        //- Main point ở đây là:
        //  + Số steps di chuyển trước khi change direction sẽ increase sau mỗi (2 lần change direction)
        //
        //1.2, Complexity
        //- Space: O(rows*cols)
        //- Time: O(max(row, col)^2)
        //
        int rows = 5, cols = 6, rStart = 1, cStart = 4;
//        int[][] rs= spiralMatrixIII(rows, cols, rStart, cStart);
        int[][] rs= spiralMatrixIIIOptimization(rows, cols, rStart, cStart);

        for(int i=0;i<rs.length;i++){
            System.out.printf("%s %s\n", rs[i][0], rs[i][1]);
        }
        //
        //#Reference:
        //54. Spiral Matrix
        //2326. Spiral Matrix IV
    }
}
