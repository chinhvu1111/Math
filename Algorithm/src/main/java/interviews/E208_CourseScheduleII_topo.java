package interviews;

import java.util.*;

public class E208_CourseScheduleII_topo {

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] degrees=new int[numCourses];
        Map<Integer, Set<Integer>> map=new HashMap<>();
        Deque<Integer> deque=new LinkedList<>();
        List<Integer> list=new ArrayList<>();

        for(int[] pre: prerequisites){
            if(!map.containsKey(pre[1])){
                map.put(pre[1], new HashSet<>());
            }
            map.get(pre[1]).add(pre[0]);
            degrees[pre[0]]++;
        }
//        Set<Map.Entry<Integer, Set<Integer>>> entries = map.entrySet();
//        for(Map.Entry<Integer, Set<Integer>> entry: entries){
//            if(degrees[entry.getKey()]==0){
//                deque.add(entry.getKey());
//            }
//        }
        for(int i=0;i<numCourses;i++){
            if(degrees[i]==0){
                deque.add(i);
                list.add(i);
            }
        }
        while (!deque.isEmpty()){
            int currentValue=deque.pop();
            Set<Integer> nextCourse=map.get(currentValue);
            if(nextCourse==null){
                continue;
            }
            for(Integer course: nextCourse){
                degrees[course]--;
                if(degrees[course]==0){
                    list.add(course);
                    deque.add(course);
                }
            }
        }
        if(list.size()!=numCourses){
            return new int[]{};
        }
        int[] result=new int[list.size()];
        for(int i=0;i<list.size();i++){
            result[i]=list.get(i);
        }
        return result;
    }

    public static int[] findOrderRefactor(int numCourses, int[][] prerequisites) {
        int[] degrees=new int[numCourses];
        Map<Integer, Set<Integer>> map=new HashMap<>();
        int[] deque=new int[numCourses];
        int first=0, last=0;

        for(int[] pre: prerequisites){
            if(!map.containsKey(pre[1])){
                map.put(pre[1], new HashSet<>());
            }
            map.get(pre[1]).add(pre[0]);
            degrees[pre[0]]++;
        }
        for(int i=0;i<numCourses;i++){
            if(degrees[i]==0){
                deque[last++]=i;
            }
        }
        while (first<last){
            int currentValue=deque[first++];
            Set<Integer> nextCourse=map.get(currentValue);
            if(nextCourse==null){
                continue;
            }
            for(Integer course: nextCourse){
                degrees[course]--;
                if(degrees[course]==0){
                    deque[last++]=course;
                }
            }
        }
        if(last!=numCourses){
            return new int[]{};
        }
        return deque;
    }

    public static void println(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.printf("%s, ",arr[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
//        int numCourses=4;
//        int[][] prerequisites=new int[][]{{1,0}, {2,0}, {3,1}, {3,2}};
//
        //int numCourses=3;
        //int[][] prerequisites=new int[][]{{1,0}, {1,2}, {0,1}};

        int numCourses=2;
        int[][] prerequisites=new int[][]{{1,0}};
        //
        //** Đề bài
        //- Liệt kê ra cách để học hết tất cả các course
        //VD: [1,2] --> nếu muốn học course 2 thì cần phải học course 1
        //VD:
        //                      0
        //                   1     2
        //                      3
        //--> result : 0,2,1,3 ==> Lần lượt ăn những node có bậc =0
        //--> Số đỉnh đi vào nó bằng 0
        //
        //** Bài này tư duy như sau:
        //1, Dùng phương pháp Topological sorting
        //1.1,
        //Các steps như sau:
        //- Đi từ bậc =0
        //- Các đỉnh kề cùa node đó --> Sẽ được giảm bậc đi 1 (degrees[node]--) sau khi đã mất parent
        //- Khi có degree[node]==0 --> Tức là đỉnh đó biến mất --> thêm vào trong queue
        //==> Vì chỉ có degree[node]==0 --> Mới được phép đi qua đỉnh đó.
        //- Add đỉnh đó vào result
        //
        //1.2,
        //- Time complexity : O(N+M)
        //- Space complextity : O(N)
        //
        //2, Tối ưu
        //2.1, Ở đây trả về dãy đã đi qua
        //==> Hoàn toàn có thể trả về queue là xong --> Nhưng queue trong trường hợp này dùng poll
        //==> Để có thể không mất các phần tử thì ta implement queue như 1 array
        //2.2, Số lượng courses --> Chính là kích thước của queue ==> Chính là đường đi
        //--> Dùng first + last ==> Để biểu diễn
        //2.3, Chú ý:
        //- Nếu không đi hết all course --> return empty
        //==> Chỉ cần so sánh (last != numsCourse) : Vì last luôn + 1 cho đến cuối
        //
        //2.4, Result : beat 93.48%
        //
        //3, Ứng dụng của sắp xếp Topological sorting:
        //
        //- Kiểm tra đồ thị có hướng có chu trình hay không.
        //- Trong một số bài toán, sắp xếp topo là quá trình tiền xử lý input để phục vụ cho một thuật toán khác (vd: Dynamic programming).
        //#Reference
        //- Course Schedule
        //- Alien Dictionary
        //- Minimum Height Trees
        //- Sequence Reconstruction
        //- Course Schedule III
        //- Parallel Courses
        //- Find All Possible Recipes from Given Supplies
        //- Build a Matrix With Conditions
        //- Sort Array by Moving Items to Empty Space
        int[] result=findOrder(numCourses, prerequisites);
        println(result);

        result=findOrderRefactor(numCourses, prerequisites);
        println(result);
    }
}
