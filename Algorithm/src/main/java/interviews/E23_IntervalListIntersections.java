package interviews;

import java.util.ArrayList;
import java.util.List;

public class E23_IntervalListIntersections {

    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int n=firstList.length;
        int m=secondList.length;
        int iLeft=0;
        int iRight=0;
        int currentX = 0;
        int currentY = 0;

        if(firstList.length==1&&secondList.length==0){
            return firstList;
        }
        if(firstList.length==0&&secondList.length==1){
            return secondList;
        }

        if(firstList.length>=1){
            currentX=firstList[0][0];
            currentY=firstList[0][1];
            iLeft++;
        }else if(secondList.length>=1){
            currentX=secondList[0][0];
            currentY=secondList[0][1];
            iRight++;
        }
        List<int[]> list=new ArrayList<>();

        while (iLeft<n||iRight<m){
            int secondRightX=Integer.MAX_VALUE;
            int secondRightY=Integer.MAX_VALUE;
            int firstRightX=Integer.MAX_VALUE;
            int firstRightY=Integer.MAX_VALUE;
            int arr[][]=null;

            if(iRight<m){
                secondRightX=secondList[iRight][0];
                secondRightY=secondList[iRight][1];
            }
            if(iLeft<n){
                firstRightX=firstList[iLeft][0];
                firstRightY=firstList[iLeft][1];
            }
            if(firstRightX>secondRightX){
                arr=merge(currentX, currentY, secondRightX, secondRightY);
                iRight++;
            }else {
                arr=merge(currentX, currentY, firstRightX, firstRightY);
                iLeft++;
            }
            currentX=arr[1][0];
            currentY=arr[1][1];
            System.out.println(currentX+" "+currentY);
            if(arr[0][1]!=-1&&arr[0][0]<=arr[0][1]){
                list.add(arr[0]);
            }
        }
        int rs[][]=new int[list.size()][2];

        for(int i=0;i<list.size();i++){
            rs[i][0]=list.get(i)[0];
            rs[i][1]=list.get(i)[1];
        }
        return rs;
    }

    public static int[][] intervalIntersectionFixed(int[][] firstList, int[][] secondList) {
        int n=firstList.length;
        int m=secondList.length;
        int arr[][]=new int[n+m][2];
        int iLeft=0;
        int iRight=0;
        int index=0;

        while(iLeft<n||iRight<m){

            if(iLeft<n&&iRight<m&&firstList[iLeft][0]>secondList[iRight][0]){
                arr[index][0]=secondList[iRight][0];
                arr[index][1]=secondList[iRight][1];
                index++;
                iRight++;
            }else if(iLeft<n){
                arr[index][0]=firstList[iLeft][0];
                arr[index][1]=firstList[iLeft][1];
                index++;
                iLeft++;
            }else{
                arr[index][0]=secondList[iRight][0];
                arr[index][1]=secondList[iRight][1];
                index++;
                iRight++;
            }
        }
        List<int[]> list=new ArrayList<>();
        int currentX=-1;
        int currentY=-1;

        if(arr.length>=1){
            currentX=arr[0][0];
            currentY=arr[0][1];
        }
        if(arr.length==1){
            int rs[][]=new int[1][2];
            rs[0][0]=currentX;
            rs[0][1]=currentY;
            return rs;
        }
        for(int i=1;i<arr.length;i++){
            int nextX=arr[i][0];
            int nextY=arr[i][1];


            if(nextY<nextX){
                int currentRs[]=new int[2];
                currentRs[0]=currentX;
                currentRs[1]=currentY;
                currentX=nextX;
                currentY=nextY;
                list.add(currentRs);
            }else{
                int currentRs[][]=merge(currentX, currentY, nextX, nextY);
                currentX=currentRs[1][0];
                currentY=currentRs[1][1];

                if(currentRs[0][1]!=-1){
                    list.add(currentRs[0]);
                }
            }
        }
        int rs[][]=new int[list.size()][2];

        for(int i=0;i<list.size();i++){
            rs[i][0]=list.get(i)[0];
            rs[i][1]=list.get(i)[1];
        }
        return rs;
    }

    public int[][] intervalIntersectionDiscuss(int[][] first, int[][] second) {
        if(first == null || first.length == 0 && second == null || second.length == 0)
            return new int[][] {};

        List<int[]> list = new ArrayList<>();
        int i = 0, j = 0;

        while(i < first.length && j < second.length) {
            int[] A = first[i];
            int[] B = second[j];

            int x = Math.max(A[0], B[0]);
            int y = Math.min(A[1], B[1]);

            if(x <= y)
                list.add(new int[] {x, y});

            if(A[1] < B[1])
                i++;
            else
                j++;
        }

        return list.toArray(new int[list.size()][]);
    }

    public int[][] intervalIntersectionOptimized(int[][] first, int[][] second) {
        if(first==null||first.length==0||second==null||second.length==0){
            return new int[][]{};
        }

        int iLeft=0;
        int iRight=0;
        int n=first.length;
        int m=second.length;
        List<int[]> list = new ArrayList<>();

        while(iLeft<n&&iRight<m){
            int left[]=first[iLeft];
            int right[]=second[iRight];
            int x=Math.max(left[0], right[0]);
            int y=Math.min(left[1], right[1]);

            if(left[1]<right[1]){
                iLeft++;
            }else{
                iRight++;
            }
            if(x<=y){
                list.add(new int[]{x,y});
            }
        }
        return list.toArray(new int[list.size()][]);
    }

    public static int[][] merge(int x, int y, int x1, int y1){
        int arr[][]=new int[2][2];

        if(y<x1){
            arr[0][0]=-1;
            arr[0][1]=-1;
            arr[1][0]=x1;
            arr[1][1]=y1;
            return arr;
        }
        int interX=Math.max(x, x1);
        int interY=Math.min(y, y1);
        arr[0][0]=interX;
        arr[0][1]=interY;
        int remainX=-1;
        int remainY=-1;

        if(y>y1){
            remainX=y1;
            remainY=y;
        }else{
            remainX=y;
            remainY=y1;
        }
        arr[1][0]=remainX;
        arr[1][1]=remainY;
        return arr;
    }

    public static void main(String[] args) {
//        int firstList[][]=new int[][]{{0,2},{5,10},{13,23},{24,25}};
//        int secondList[][]=new int[][]{{1,5},{8,12},{15,24},{25,26}};

//        int firstList[][]=new int[][]{};
//        int secondList[][]=new int[][]{{1,5}};
//        int firstList[][]=new int[][]{{1,5}};
//        int secondList[][]=new int[][]{};
        //Case 1:
        int firstList[][]=new int[][]{{14,16}};
        int secondList[][]=new int[][]{{7,13},{16,20}};
        //Wrong way
//        System.out.println(intervalIntersection(firstList, secondList));
//        int firstList[][]=new int[][]{};
//        int secondList[][]=new int[][]{};
        //Bài này tư duy như sau:
        //ERROR - Cách làm sai:
        //1, Nếu ta làm theo cách tư duy kiểu map nodes:
        //1.1, Chọn (x,y) đẩu tiên có thể là tọa độ của (first và second list)
        //Sau đó nếu ta làm theo cách này các bước như sau:
        //- Lần lượt so sánh tọa độ của điểm hiện tại (current_x, current_y) với các điểm ở trong first, second list
        //- Giá trị trả về sẽ là (khoảng giao giữa 2 điểm đó) + (khoảng thừa trả lại của 2 điểm đó)
        //- Lấy giá trị khoảng thừa đó --> Đi so sánh tiếp với 2 điểm tiếp theo của first, second list.
        //Nhưng sẽ bị sai case
//        int firstList[][]=new int[][]{{14,16}};
//        int secondList[][]=new int[][]{{7,13},{16,20}};
        //Output : {14, 13}, {16, 16}
        //Expected : {16,16}
        //SAI : {14,16} và {7,13} không giao nhau ở điểm nào --> Không được phép lấy.
        //==> Chỉ trả lại phần thừa mà thôi.

        //2, Cách 2:
        //Merge tất cả vào 1 array + ordered by arr[0] tức là trục X.
        //Các bước tư duy như sau:
        //- Merge first list và second list
        //- Merge các tọa độ --> Chỉ fix lỗi bên trên:
        //+ Nếu left, right point không giao nhau gì (disjoint) --> Loại ko thêm vào list.
        //===> Làm kiểu này về cơ bản sẽ slow hơn so với việc chỉ map điểm.

        //3, Cách 3:
        //Đơn thuần là 2 pointers
        //MIN, MAX của các điểm
        //--> Nhưng khi add vào list + thêm điều kiện kiểm tra điểm x<=y hay không
        // --> Vì đôi khi chúng có thể ko giao nhau ==> Gây ra x>y.
        System.out.println(intervalIntersection(firstList, secondList));
        System.out.println(intervalIntersectionFixed(firstList, secondList));
    }
}
