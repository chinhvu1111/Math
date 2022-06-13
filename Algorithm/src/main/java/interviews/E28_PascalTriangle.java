package interviews;

import java.util.ArrayList;
import java.util.List;

public class E28_PascalTriangle {

    public static List<List<Integer>> generate(int numRows) {
//        int arr[][]=new int [numRows][numRows];
        int index=1;
        List<List<Integer>> list=new ArrayList<>();

        for(int i=0;i<numRows;i++){
            List<Integer> integers=new ArrayList<>();
            for(int j=0;j<index;j++){
                if(j==0||j==index-1){
//                    arr[i][j]=1;
                    integers.add(1);
                }else{
                    int x=i-1;
                    int y=j-1;

                    int point1=0;
                    int point2=0;

                    //                        point1=arr[x][y];
                    point1=list.get(x).get(y);
                    point2=list.get(x).get(j);
                    //                    arr[i][j]=point1+point2;
                    integers.add(point1+point2);
                }
            }
            list.add(integers);
            index++;
        }
        return list;
    }

    public static void main(String[] args) {
        int n=5;
//        int n=1;
        //Tối ưu ở chỗ check if/else --> Ta sẽ bỏ qua các giá trị nằm ở biên (==0/ ==n-1)
        //Else các case còn lại thì i>0, j>0 luôn lớn hơn 0
        //---> Nên không cần check if/else --> 100%
        generate(n);
    }
}
