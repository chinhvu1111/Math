package E1_Array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class E11_MaximumDistanceInArrays {

    public static class Node{
        List<Integer> sortedArray;
        int index;
        public Node(List<Integer> sortedArray, int index){
            this.sortedArray=sortedArray;
            this.index=index;
        }
    }
    public static int maxDistance(List<List<Integer>> arrays) {
        int x;
        int y;
        int rs=Integer.MIN_VALUE;

        //[[1,4],[0,5]]
        //list1= [[0,5],[1,4]]
        //list2=[[0,5],[1,4]]
        //- Space : O(E)
        List<Node> listSortedMin=new ArrayList<>();
        List<Node> listSortedMax=new ArrayList<>();
        int i=0;
        //Time : O(m)
        for(List<Integer> list: arrays){
            listSortedMin.add(new Node(list, i));
            listSortedMax.add(new Node(list, i));
            i++;
        }
        //Time : m*O(m)
        listSortedMin.sort((a, b) -> (a.sortedArray.get(0) - b.sortedArray.get(0)));
        listSortedMax.sort((a, b) -> (b.sortedArray.get(b.sortedArray.size() - 1) - a.sortedArray.get(a.sortedArray.size() - 1)));
        int n=arrays.size();

        //Time : O(m)
        for(i=0;i<n;i++){
            int minIndex=listSortedMin.get(i).index;
            int maxIndex=listSortedMax.get(i).index;
            if(minIndex==maxIndex){
                if(i<n-1){
                    rs=Math.max(rs, getResult(listSortedMin, listSortedMax, i, i+1));
                }
                continue;
            }
            x=listSortedMin.get(i).sortedArray.get(0);
            y=listSortedMax.get(i).sortedArray.get(listSortedMax.get(i).sortedArray.size()-1);
            rs=y-x;
            break;
        }
        return rs;
    }

    public static int getResult(List<Node> listSortedMin, List<Node> listSortedMax, int i, int j){
        int curRs=Integer.MIN_VALUE;
        int x=listSortedMin.get(i).sortedArray.get(0);
        int y=listSortedMax.get(j).sortedArray.get(listSortedMax.get(j).sortedArray.size()-1);
        curRs=Math.max(curRs, y-x);
        x=listSortedMin.get(j).sortedArray.get(0);
        y=listSortedMax.get(i).sortedArray.get(listSortedMax.get(i).sortedArray.size()-1);
        curRs=Math.max(curRs, y-x);
        return curRs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given m arrays where each array is (sorted) in ascending order (sorted all)
        //+ Choose the number from 2 arrays from m given arrays
        //+ Return maximum |a-b|
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //m == arrays.length
        //2 <= m <= 10^5
        //1 <= arrays[i].length <= 500
        //-10^4 <= arrays[i][j] <= 10^4
        //arrays[i] is sorted in ascending order.
        //There will be at most 105 integers in all the arrays.
        //- Ta có đến 10^5 arrays
        //- Số lượng elements tất cả cùng lắm 10^5
        //==> sort được
        //- Số có thể là số <0 ==> Mà |a-b|, b càng smaller thì |a-b| càng lớn.
        //- Mỗi array được sorted sẵn rồi
        //
        //1 <= arrays[i].length <= 500
        //--> Lấy element số 0 thoải mái
        //
        //- Brainstorm
        //- Ta chọn min nhất với max nhất là được
        //  + Ta sẽ sort trong mỗi array có trong arrays đã cho.
        //
        //- Vấn đề là phải chọn chính xác a và b từ 2 arrays
        //+ Cần loại bỏ đi trường hợp (max và min) cùng chung 1 array
        //Ex:
        //arrays = [[1,2,3],[4,5],[1,2,3]]
        //==> Biến đổi thành
        //arrays = [[1,3],[4,5],[1,3]]
        //- Ta thấy rằng 1 array có thể chọn nó như là min(b)/max(a)
        //  + Tư duy theo kiểu chọn min ==> tính với max remaining array không được.
        //- Idea
        //- Sort 2 chiều
        //+ Sort all arrays theo max element + lưu lại index
        //+ Sort all arrays theo min element + lưu lại index
        //- Khi ta sort rồi thì:
        //+ Traverse từ i (0 --> m-1)
        //+ Kết quả tốt nhất sẽ chạy từ i(0 --> m-1)
        //  + Nếu 2 sorted array tại index=0 mà có old index giống nhau ==> next i++
        //<> return result
        //
        //- Special cass:
        //
        //arr=[[-2],[-3,-2,1]]
        //sortedMinIncre=[-3,-2,1], [-2]
        //sortedMaxDec=[-3,-2,1], [-2]
        //
        //1.1, Optimization
        //1.2, Complexity
        //- m is the length of arrays
        //- E is the number of elements of all arrays
        //- Space : O(E)
        //- Time : O(m*log(m))
        //
        //#Reference:
        //1441. Build an Array With Stack Operations
        //2158. Amount of New Area Painted Each Day
        //2304. Minimum Path Cost in a Grid
        //
        int[][] arr={{1,4},{0,5}};
        List<List<Integer>> arrList=new ArrayList<>();
        for(int[] a:arr){
            List<Integer> curList=new ArrayList<>();
            for(int e:a){
                curList.add(e);
            }
            arrList.add(curList);
        }
        System.out.println(maxDistance(arrList));
    }
}
