package leetcode_medium;

import java.util.Arrays;

public class VideoStitching_73 {

    private static int rs;

    public static int videoStitching(int[][] clips, int time) {
        int n = clips.length;

//        for(int i=0;i<n-1;i++){
//            for(int j=0;j<n-i-1;j++){
//                if(clips[i][0]<clips[j][0]){
//                    int temp=clips[i][0];
//                    clips[i][0]=clips[j][0];
//                    clips[j][0]=temp;
//
//                    temp=clips[i][1];
//                    clips[i][1]=clips[j][1];
//                    clips[j][1]=temp;
//                }
//            }
//        }
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (clips[j][0] > clips[j + 1][0]) {
                    int temp = clips[j][0];
                    clips[j][0] = clips[j + 1][0];
                    clips[j + 1][0] = temp;

                    temp = clips[j][1];
                    clips[j][1] = clips[j + 1][1];
                    clips[j + 1][1] = temp;
                }

        int maxStages[] = new int[n];
        int rs = Integer.MAX_VALUE;
        if(clips[0][0]==0&&clips[0][1]>=time){
            return 1;
        }else if(clips[0][0]==0) {
            maxStages[0] = 1;
        }
        else{
            return -1;
        }

        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;

            if(clips[i][0]==0&&clips[i][1]>=time){
                min=0;
            }

            for (int j = i - 1; j >= 0; j--) {
                if (maxStages[j] != 0 && clips[i][0] <= clips[j][1] && clips[i][1] >= clips[j][0]) {
                    min = Math.min(maxStages[j], min);
                }else {
                    continue;
                }
                //Case 1: Đối với trường hợp (0,2), (0,4)
                //(0,2) có thể thay thế bằng (0,4) nhưng kết quả giữ nguyên
                if(clips[i][0]==clips[j][0]){
                    min=maxStages[j]-1;
                }
            }
            if (min != Integer.MAX_VALUE) {
                maxStages[i] = min + 1;
                if(clips[i][1]>=time){
                    rs=Math.min(rs, min+1);
                }
            }
        }
        return rs==Integer.MAX_VALUE?-1:rs;
    }

    public static void main(String[] args) {
//        int arr[][] = new int[][]{{0, 2}, {4, 6}, {8, 10}, {1, 9}, {1, 5}, {5, 9}};
        //Case 1: Đối với trường hợp (0,2), (0,4)
        //(0,2) có thể thay thế bằng (0,4) nhưng kết quả giữ nguyên
//        int arr[][] = new int[][]{{0,1},{6,8},{0,2},{5,6},{0,4},{0,3},{6,7},{1,3},{4,7},{1,4},{2,5},{2,6},{3,4},{4,5},{5,7},{6,9}};
        int arr[][] = new int[][]{{3,6},{3,6},{0,4},{6,6},{8,10},{6,10},{0,1},{6,9}};
        //Case 1
//        System.out.println(videoStitching(arr, 9));
        System.out.println(videoStitching(arr, 2));
    }
}
