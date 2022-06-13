package interviews;

import java.util.ArrayList;
import java.util.List;

public class E29_PascalTriangleII {

    public static List<Integer> getRow(int rowIndex) {
        int index=1;
        List<Integer>[] list=new ArrayList[2];
        list[0]=new ArrayList<>();
        list[0].add(1);

        for(int i=1;i<=rowIndex;i++){
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
            list[0]=list[1];
            list[1]=integers;
            index++;
        }
        return list[1];
    }

    public static void main(String[] args) {
        int n=5;
        getRow(n);
    }
}
