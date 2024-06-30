package E1_daily;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;

public class E33_MaxValueOfEquation {

    public static int minMovesToSeat(int[] seats, int[] students) {
        Arrays.sort(seats);
        Arrays.sort(students);
        int n= seats.length;
        int rs=0;

        for(int i=0;i<n;i++){
            rs+=Math.abs(seats[i]-students[i]);
        }
        return rs;
    }

    public static int minMovesToSeatCountingSort(int[] seats, int[] students) {
        int maxSum = 0;
        int n=seats.length;

        for (int i = 0; i < n; i++) {
            maxSum=Math.max(maxSum, Math.max(seats[i], students[i]));
        }
        int[] count=new int[maxSum+1];

        for(int i=0;i<n;i++){
            count[seats[i]]++;
            count[students[i]]--;
        }
        //Ex:
        //seats =   [3,1,5]
        //students =[2,7,4]
        //[1,2,3,4,5,6,7]
        //[1,-1,1,-1,0,1]
        //
        //
        int moves=0;
        int unmatch=0;
        for(int i=0;i<=maxSum;i++){
            moves+=Math.abs(unmatch);
            unmatch+=count[i];
        }

        return moves;
    }

    public static void main(String[] args) {
        //** Requirement
        //- There are (n seats) and (n students) in a room.
        //- You are given an array seats of length n, where (seats[i]) is the position of the (ith seat).
        //- You are also given the array students of length n, where (students[j]) is the position of (the jth student).
        //- You may perform the following move any number of times:
        //  + Increase or decrease (the position) of the ith student by 1 (i.e., moving the ith student from position x to x + 1 or x - 1)
        //* Return (the minimum number of moves) required to move (each student) to a seat such that (no two students) are in (the same) seat.
        //- Note that there may be (multiple seats) or students in (the same position) at the beginning.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint:
        //n == seats.length == students.length
        //1 <= n <= 100
        //1 <= seats[i], students[j] <= 100
        //+ All positive
        //
        //- Brainstorm
        //
        //Example 1:
        //Input: seats = [3,1,5], students = [2,7,4]
        //Output: 4
        //Explanation: The students are moved as follows:
        //- The first student is moved from position 2 to position 1 using 1 move.
        //- The second student is moved from position 7 to position 5 using 2 moves.
        //- The third student is moved from position 4 to position 3 using 1 move.
        //In total, 1 + 2 + 1 = 4 moves were used.
        //
        //- Tức là số lần move MIN để:
        //  + Students có seat hết
        //  + Không có 2 students ngồi chung 1 seat
        //Ex:
        //seats =   [3,1,5]
        //students =[2,7,4]
        //- Greedy được không
        //- Sort cả 2
        //seats =   [1,3,5]
        //students =[2,4,7]
        //sum = 1 + 1 + 2 = 4
        //- Vì là all positive:
        //  + Từ smallest của seat --> smallest của students là tốt nhất.
        //
        //1.1, Optimization
        //
        //1.2, Complexity
        //- N is length of seat
        //- Space: O(log(n)) in java
        //- Time: O(n*log(n))
        //
        //2. Counting sort
        //2.0,
        //
        //Ex:
        //seats =   [3,1,5]
        //students =[2,7,4]
        //- Idea là sẽ gộp cả 2 thằng vào cùng 1 counting array
        //  + Với những students và seats cùng vị trí ==> Triệu tiêu nhau đi.
        //- Max value == max toàn bộ giá trị của (seat) và (students)
        //- Triệt tiêu ntn ==> count[seat[i]]++ còn count[student[i]]--
        //
        //Ex:
        //seats =   [3,3,1,5]
        //students =[2,1,7,4]
        //vals = [1,2,3,4,5,6,7]
        //  + count = [1,0,2,0,1,0,0]
        //  + count = [0,-1,2,-1,1,0,-1]
        //- Ta thấy 3 -> 2 ==> ta chọn điềm gần 3 nhất là 2 thôi
        //- Ta hoàn toàn có thể có 2 số positive cạnh nhau ==> Cùng là seats
        //Ex:
        //[1,2]
        //[3,3]
        //rs = 2 + 1 = 3
        //vals = [1,2,3]
        //count =[1,1,-2]
        //1 -> 3 để có thể thành 3 ==> 1 -> 2 trước
        //- Mở rộng test case thêm có những count==0
        //Ex:
        //[1,3,1] ==> 1,1,3
        //[4,4,5]
        //rs = 3 + 3 + 2 = 8
        //vals = [1,2,3, 4, 5]
        //count =[2,0,1,-2,-1]
        //moves:
        //  + Giả sử
        //      + student = 2 ==> countingSort[2] = 1
        //      + seat = 4 ==> countingSort[4] = -1
        //* Mỗi lần đi từ left -> right 1 đơn vị là ta sẽ:
        //  + Move tất cả các count mà ta lấy được đi 1 unit
        //  ==> Tức là chuyển đổi vals của all các num đã tính count thành (i=index) của counting
        //  ==> moves+ = |unmatched|
        //  + Nếu gặp được (count<0):
        //      + Lúc đó sum nó sẽ tự trừ đi ==> Ta reach đến số (i=index) ==> ta sẽ triệt tiêu được (count số == index) trước đó
        //      ==> Các số trước đó đã được move ==> để thành số (i=index) hiện tại nên triệt tiêu được
        //
        //- Sort space:
        //+ In Python, the sort method sorts a list using the Tim Sort algorithm which is a combination of Merge Sort and
        // Insertion Sort and has O(n) additional space.
        //+ In C++, the sort() function is implemented as a hybrid of Quick Sort, Heap Sort, and Insertion Sort,
        // with a worse-case space complexity of O(logn).
        //+ In Java, Arrays.sort() is implemented using a variant of
        // the Quick Sort algorithm which has a space complexity of O(logn) for sorting two arrays.
        //
        //1.1, Optimization
        //
        //1.2, Complexity
        //- Space: O(maxSum)
        //- Time: O(maxSum)
        //
        //#Reference:
        //1787. Make the XOR of All Segments Equal to Zero
        //2965. Find Missing and Repeated Values
        //2371. Minimize Maximum Value in a Grid
        //2497. Maximum Star Sum of a Graph
        //360. Sort Transformed Array
        //1296. Divide Array in Sets of K Consecutive Numbers
        int[] seats = {3,1,5}, students = {2,7,4};
        System.out.println(minMovesToSeatCountingSort(seats, students));
    }
}
