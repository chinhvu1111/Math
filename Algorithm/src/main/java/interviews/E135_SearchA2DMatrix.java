package interviews;

public class E135_SearchA2DMatrix {

    public static boolean searchMatrix(int[][] matrix, int target) {
        int n=matrix.length;
        int m=matrix[0].length;
        int[] low=new int[]{0, 0};
        int[] high=new int[]{n-1, m-1};

        while (low[0]<high[0]||(low[0]==high[0]&&low[1]<high[1])){
            int[] mid=getMiddlePoint(low[0], low[1], high[0], high[1], n, m);
            System.out.printf("%s %s, ", mid[0], mid[1]);
//            Thread.sleep(1000);

            if(matrix[mid[0]][mid[1]]>target){
                if(mid[1]>0){
                    high[0]= mid[0];
                    high[1]= mid[1]-1;
                }else if(mid[0]>0){
                    high[0]= mid[0]-1;
                    high[1]= m-1;
                }else break;
            }else if(matrix[mid[0]][mid[1]]<target){
                if(mid[1]<m-1){
                    low[0]= mid[0];
                    low[1]= mid[1]+1;
                }else if(high[0]<n-1&&mid[1]>=m-1){
                    low[0]= mid[0]+1;
                    low[1]= 0;
                }else break;
            }else return true;
        }
        if(matrix[low[0]][low[1]]==target||matrix[high[0]][high[1]]==target){
            return true;
        }
        return false;
    }

    public static int[] getMiddlePoint(int x, int y, int x1, int y1, int n, int m){
        int left=x*m+(y+1);
        int right=x1*m+(y1+1);

        int plus=(right-left+1)/2;
        int rightY=y+plus%m-1;

        if(rightY>=m){
//            System.out.printf("Larger %s", rightY-m);
            return new int[]{x+plus/m+1, rightY-m+1};
        }
        System.out.printf("plus %s, ", plus);
//        System.out.println(plus);
//        System.out.printf("%s %s, ", ( x+plus/m ), ( y+plus%m-1 ));
        return new int[]{x+plus/m, y+plus%m};
    }

    public static void main(String[] args) throws InterruptedException {
//        int[][] arr=new int[][]{{1,3,5,7}, {10,11,16,20}, {23,30,34,60}};
//        int[][] arr=new int[][]{{1,3,5,7}, {10,11,16,20}, {23,30,34,60}};
//        int[][] arr=new int[][]{{1,3,5,7,13}};
//        int[][] arr=new int[][]{{1},{3}};
        int[][] arr=new int[][]{{-9,-8,-8},{-5,-3,-2},{0,2,2},{4,6,8}};

//        int rs[]=getMiddlePoint(0,0, 2,3, 2, 3);
//        System.out.printf("%s %s\n", rs[0], rs[1]);
        System.out.println(searchMatrix(arr, 3));
        System.out.println(searchMatrix(arr, 13));

    }
}
