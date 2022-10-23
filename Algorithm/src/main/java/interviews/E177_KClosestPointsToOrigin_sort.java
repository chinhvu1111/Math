package interviews;

import java.util.Arrays;
import java.util.Random;

public class E177_KClosestPointsToOrigin_sort {

    public static Random random=new Random();

    public static int[][] kClosest(int[][] points, int k) {
        int indexKClosetElements=findIndexKCloset(points, 0, points.length-1, k);

        int [][] result= Arrays.copyOfRange(points, 0, indexKClosetElements+1);
        System.out.println(indexKClosetElements);
        return result;
    }

    public static int findIndexKCloset(int[][] points, int left, int right, int k){
//        System.out.printf("%s %s %s, ", left, right, right-left+1);
//        System.out.println();
//        int randomIndex=left + random.nextInt(right-left+1);
//        swap(points[right], points[randomIndex]);

        int pivot=right;
        int currentIndex=left;
        int pivotValue=getDistance(points[pivot][0], points[pivot][1]);

        //
        //0,3,2,(4: currrentIndex)
        //===> Chú ý : Ở đây phải lấu currentIndex ==> Thì mới đúng
        //--> nếu lấy currentIndex-1 <=> nums[ currentIndex-1]==2 --> Chưa chắc đã phải là số lớn nhất
        for(int i=left;i<right;i++){
            if(getDistance(points[i][0], points[i][1])<pivotValue){
                swap(points[i], points[currentIndex++]);
            }
        }
//        System.out.printf("Value : %s, Find index %s left: %s, right: %s, %s, \n", pivotValue, currentIndex, left, right, k);
        swap(points[currentIndex], points[right]);

        if(currentIndex+1==k){
            return currentIndex;
        }
        int rs;

        if(currentIndex+1>k){
            rs=findIndexKCloset(points, left, currentIndex-1, k);
        }else{
            rs=findIndexKCloset(points, currentIndex+1, right, k);
        }
        return rs;
    }

    public static int getDistance(int x, int y){
        return x*x+y*y;
    }

    public static void swap(int []a, int []b){
        int temx=a[0];
        int temy=a[1];
        a[0]=b[0];
        a[1]=b[1];
        b[0]=temx;
        b[1]=temy;
    }

    public static void main(String[] args) {
        int[][] arr=new int[][]{{3,3},{5,-1},{-2,4}};
        int k=2;
//        int[][] arr=new int[][]{{0,1},{1,0}};
//        int k=2;
//        int[][] arr=new int[][]{{1,3},{-2,2},{2,-2}};
//        int k=2;
        int[][] result=kClosest(arr, k);

        for(int i=0;i<result.length;i++){
            for(int j=0;j<result[i].length;j++){
                System.out.print(result[i][j]);
            }
            System.out.println();
        }
        //
        //**
        //
        //** Bài này tư duy như sau:
        //0, Lỗi sai:
        //0.1, - Phổ biến ở chỗ currentIndex
        //** Câu hỏi lấy (currentIndex hay currentIndex+1/ CurrentIndex-1)
        //
        //0,3,2,(4: currrentIndex)
        //===> Chú ý : Ở đây phải lấu currentIndex ==> Thì mới đúng
        //--> nếu lấy currentIndex-1 <=> nums[ currentIndex-1]==2 --> Chưa chắc đã phải là số lớn nhất
        //
        //0.2, So sánh <value / <=value
        //==> Nên so sánh < value ==> Để phân ra (value) ==> Sẽ là vị trí (currentIndex)
        //==> Cũng là lớn thứ k
        //
        //0.3, Nếu trả lại index --> không cần k -currentIndex gì cả
        //--> Trừ khi ta đang xét số lần thì mới cần
        //
        //0.4, Điều kiện if(currentIndex+1==k) ==> Nó phải kéo theo bên dưới xét tiếp if(currentIndex+1 > k)
        //==> Mình bị sai cái này không debug được ra.
    }
}
