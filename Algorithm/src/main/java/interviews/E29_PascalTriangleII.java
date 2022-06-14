package interviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class E29_PascalTriangleII {

    public static List<Integer> getRow(int rowIndex) {
        int index=1;
        List<Integer>[] list=new ArrayList[1];
        list[0]=new ArrayList<>();
        list[0].add(1);

        for(int i=0;i<=rowIndex;i++){
            List<Integer> integers=new ArrayList<>();
            for(int j=0;j<index;j++){
                if(j==0||j==index-1){
//                    arr[i][j]=1;
                    integers.add(1);
                }else{
//                    int x=i-1;
                    int y=j-1;

                    int point1=0;
                    int point2=0;

                    //                        point1=arr[x][y];
                    point1=list[0].get(y);
                    point2=list[0].get(j);
                    //                    arr[i][j]=point1+point2;
                    integers.add(point1+point2);
                }
            }
//            list[0]=list[1];
            list[0]=integers;
            index++;
        }
        return list[0];
    }

    public static List<Integer> getRowOptimized(int n) {
        Integer[] arr = new Integer[n + 1];
        Arrays.fill(arr, 0);
        long val = 1;

        for(int i = 0; i <= n; i++) {
            arr[i] = (int)val;
            val = val * (n - i)/(i + 1);
        }

        return Arrays.asList(arr);
    }

    public static void main(String[] args) {
        int n=3;
//        int n=0;
        //Bài này chỉ cần dùng 1 list là đủ
        //Các giá trị được trung gian được lưu vào list temp --> Sẽ không thay đổi luôn các giá trị của list ==> Kết quả tính tiếp vẫn sẽ đúng
        //--> Chỉ dùng 1 list là được <-> Không cần list[2].
        getRow(n);
        getRowOptimized(n);
    }
}
