package contest;

public class E19_FindTheWidthOfColumnsOfAGrid {

    public static int[] findColumnWidth(int[][] grid) {
        int n=grid[0].length;
        int[] rs=new int[n];

        for(int i=0;i<n;i++){
            int max=0;
            for(int j=0;j<grid.length;j++){
//                System.out.println(grid[j][i]);
                int addLen=grid[j][i]<0?1:0;
                max=Math.max(max, getLength(grid[j][i])+addLen);
            }
            rs[i]=(max==0)?1:max;
        }

        return rs;
    }

    public static int getLength(int x){
        int count=0;

        while (x!=0){
            x=x/10;
            count++;
        }
        return count;
    }

    public static void println(int[] arr){
        int n=arr.length;
        for(int i=0;i<n;i++){
            System.out.printf("%s, ", arr[i]);
        }
    }

    public static void main(String[] args) {
//        int[][] grid = {
//        {-15,1,3},
//        {15,7,12},
//        {5,6,-2}};
//        int[][] grid = {
//                {1},
//                {22},
//                {333}};
        int[][] grid = {
                {0}};
        int[] rs=findColumnWidth(grid);
        println(rs);
    }
}
