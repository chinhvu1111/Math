package E1_daily;

import java.util.Arrays;
import java.util.Collections;

public class E90_MaximumNumberOfWeeksForWhichYouCanWork {

    public static long numberOfWeeksWrong(int[] milestones) {
        long rs=0;
        int n=milestones.length;
        int numBlank=0;
        int numIntersection=0;
        Integer[] arr=new Integer[n];

        for (int i = 0; i < n; i++) {
            arr[i]=milestones[i];
            numBlank+=arr[i];
        }
        Arrays.sort(arr, Collections.reverseOrder());

        for(int i=0;i<n;i++){
//            if(numBlank==0){
//                break;
//            }
            if(numBlank+numIntersection<arr[i]){
                rs+=numIntersection;
                rs+=(numBlank-1)/2;
                break;
            }
            int newVal=arr[i];
            if(arr[i]<=numIntersection){
                numIntersection-=arr[i];
            }else{
                newVal=arr[i]-numIntersection;
                numIntersection=0;
            }
            int curVal =Math.min((numBlank+1)/2,newVal);
            numBlank-=curVal;
            rs+=curVal;
            numIntersection+=newVal-1;
        }
        return rs;
    }

    public static long numberOfWeeks(int[] milestones) {
//        long rs=0;
        int n=milestones.length;
        long sum=0;
        int maxCount=0;

        for (int i = 0; i < n; i++) {
            sum+=milestones[i];
            maxCount=Math.max(maxCount, milestones[i]);
        }
//        if(n==1){
//            return 1;
//        }
        long valFromMaxCount=Math.min((sum+1)/2, maxCount);
        //Check xem có điền được không:
        //- 1,0,1,0,1,0
        //  + Các số có count<=countMax có điền được vào vị trí trống hay không
        //      + Điền được thì ta lấy:
        //          + sum-(sum+1)/2+maxCount
        //      + Không điền được
        //          ==> Tính theo các vị trí điền được của các số còn lại
        //          ==> Cũng phải (*2+1) là các vị trí có thể điền project[i] mà có (mileStone[i]==maxCount)
        //          + rs = (sum-maxCount)*2+1
        //
        if(sum-maxCount+1>=valFromMaxCount){
            return sum-maxCount+valFromMaxCount;
        }
        return (sum-maxCount)*2+1;
    }

    public static long numberOfWeeksOptimization(int[] milestones) {
//        long rs=0;
        int i,j,max=-1,n=milestones.length;
        long sum=0;

        for(i=0;i<n;i++)
        {
            max=Math.max(max, milestones[i]);
            sum+=milestones[i];
        }
        long x=sum-max;

        if(max-x>1)
            return sum-(max-x-1);
        return sum;
    }

    public static void main(String[] args) {
        //** Requirement
        //- There are (n projects) numbered from (0 to n - 1).
        //- You are given an integer array milestones where each milestones[i] denotes (the number of milestones) (the ith project) has.
        //- You can work on the projects following these (two rules):
        //  + (Every week), you will finish ("exactly") (one milestone) of (one project).
        //  + You must work (every week).
        //  + You (cannot work) on (two milestones) from (the same project) for (two consecutive weeks).
        //      + Tức là phải work sole.
        //- Once (all the milestones) of (all the projects) are finished,
        // or
        //- if (the only milestones) that you can work on will cause you to violate (the above rules),
        //  + you will stop working.
        //      + Tức là ta không thể chọn cách violated
        //* Note that you may (not) be able to finish (every project's milestones) due to (these constraints).
        //* Return (the maximum number of weeks) you would be able to work on the projects (without violating the rules) mentioned above.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //n == milestones.length
        //1 <= n <= 10^5
        //1 <= milestones[i] <= 10^9
        //  + N khá lớn: Time : O(n)
        //  + milestones[i] khá lớn <= 10^9 ==> Long
        //
        //- Brainstorm
        //Example 2:
        //
        //Input: milestones = [5,2,1]
        //Output: 7
        //Explanation: One possible scenario is:
        //- During the 1st week, you will work on a milestone of project 0.
        //- During the 2nd week, you will work on a milestone of project 1.
        //- During the 3rd week, you will work on a milestone of project 0.
        //- During the 4th week, you will work on a milestone of project 1.
        //- During the 5th week, you will work on a milestone of project 0.
        //- During the 6th week, you will work on a milestone of project 2.
        //- During the 7th week, you will work on a milestone of project 0.
        //The total number of weeks is 7.
        //- Note that you cannot work on the last milestone of project 0 on 8th week because it would violate the rules.
        //Thus, one milestone in project 0 will remain unfinished.
        //
        //- Bài này có vẻ giống bài điền số sao cho không có 2 số liên tiếp giống nhau
        //  + Ta sẽ điền sao cho không có 2 project giống nhau thực hiện cùng liên tiếp
        //pos: 1,2,3,4,5,6,7,8
        //val: 1,0,1,0,1,0,1,0
        //val: 1,2,1,2,1,0,1,0
        //val: 1,2,1,2,1,3,1,0
        //  + Bản chất là ta sẽ chọn điền nhiều nhất có thể:
        //      + Điền 1 trước ==> 2 ==> 1
        //  ==> Thực ra rất cần sort (Không cần sort là sai).
        //  + Vì bản chất các value có vai trò như nhau vì đều cần:
        //      + Đứng cách nhau (ít nhẩt) 1 đơn vị đôi 1
        //Ex:
        //Input: milestones = [5,2,1]
        //pos: 1,2,3,4,5,6,7,8
        //+ Điền 2 -> 1
        //val: 2,1,2,1,0,1,0,1
        //+ Điền 2 -> 3
        //val: 2,3,2,1,0,1,0,1 ==> rs = 6 (không min)
        //- Có cách nào tính ra kết quả mà không cần fill number không?
        //
        //- Nếu điền từng số ==> sum(10^9):
        //  + TLE chắc
        //Ex:
        //Input: milestones = [5,2,1]
        //- Mình chỉ cần tìm kết quả tối đa là được (Không cần fill value)
        //pos: 1,2,3,4,5,6,7,8
        //val: 1,0,1,0,1,0,1,0
        //val: 1,2,1,2,1,0,1,0
        //val: 1,2,1,2,1,3,1,0
        //- 1 project bắt buộc phải làm liên tiếp 2 tuần khi nào?
        //  + Khi chỉ còn duy nhất 1 loại project để chọn
        //  + Các vị trí trống giữa khe của các project trước không còn
        //Ex:
        //Input: milestones = [5,2,1]
        //Max = 5
        //- 1 project điền được 4 chỗ 1,0,1,0,1,0,1
        //  + Khe=3
        //- 2 có thể điền vào khe trước
        //  + khe=2
        //- 1 không thể điền tiếp được nữa.
        //
        //- Nếu mà theo bên trên ==> Cần sort
        //* Bài này khác bài điền number ở chỗ (QUAN TRỌNG):
        //  + Nó không có giới hạn length
        //- Nếu ta tính trước
        // ==> Sai vì có thể sau (không đủ điền các giá trị sole)
        //- Ta nên tính ntn để có thể ra kết quả:
        //Ex:
        //pos: 1,2,3,4,5,6,7,8
        //val: 1,0,1,0,1,0,1,0
        //val: 1,2,1,2,1,0,1,0
        //val: 1,2,1,2,1,3,1,0
        //- Khi điền 1 ==> Ta không thể lấy full pos từ 1 được mà còn phải phụ thuộc 2,3 nữa
        //- Kết quả <= sum
        //- Do điền liên tục value:
        //Ex:
        //Input: milestones = [5,2,1,1,1,2,1]
        //pos: 1,2,3,4,5,6,7,8,9,10,11,12,13
        //val: 1,0,1,0,1,0,1,0
        //val: 1,2,1,2,1,0,1,0
        //val: 1,2,1,2,1,3,1,0
        //+ Ta thấy rằng mấy thằng mà chỉ có ít count
        //  + Luôn có thể xếp rất dễ
        //  ==> Thường mấy value mà không điền được là những project có số milestone lớn
        //** Ta chỉ không điền được cái project cuối thôi vì:
        //  + Nếu có >=2 project thì ta vẫn có thể nối chúng với nhau được
        //- Vì tính chất các project khác id (Quan trọng(:
        //  + Thằng có count lớn hơn ==> Luôn fill được thằng có fill nhỏ hơn.
        //==> Ta chỉ quan tâm thằng max count có điền được không thôi.
        //
        //* Check xem có điền được không:
        //- 1,0,1,0,1,0
        //  + Các số có count<=countMax có điền được vào vị trí trống hay không
        //      + Điền được thì ta lấy:
        //          + sum-(sum+1)/2+maxCount
        //      + Không điền được
        //          ==> Tính theo các vị trí điền được của các số còn lại
        //          ==> Cũng phải (*2+1) là các vị trí có thể điền project[i] mà có (mileStone[i]==maxCount)
        //          + rs = (sum-maxCount)*2+1
        //
//        int[] milestones = {5,2,1};
//        int[] milestones = {1000000000};
        int[] milestones = {1,10,7,1,7,2,10,10,355359359};
        //rs=97 <> 177679752
        //- Ta thấy ở đây nếu chỉ quan tâm giá trị cuối
        //  ==> Sai do ta không thể nào điển được.
        //Ex:
        //int[] milestones = {9,1,1};
        //1,2,3,4,5,6,7,8,9,10,11
        //1,0,1,0,1,0,1,0,1,0,1
        //==>
        //Chỉ có thể điên vào (các vị trí sole)
        //  ==> Điền được thì mới tính
        System.out.println(numberOfWeeksWrong(milestones));
        System.out.println(numberOfWeeks(milestones));
        System.out.println(numberOfWeeksOptimization(milestones));
        //
        //1.1, Optimization
        //- Thực ra việc điền các số còn lại vào maxCount mà (không) fit:
        //  + maxCount - (sum - maxCount) > 1
        //      + return (sum-maxCount)*2+1
        //  <> Nếu fit:
        //      + Lấy sum được luôn.
        //
        //1.2, Complexity
        //- Space: O(1)
        //- Time: O(n)
        //
        //#Reference:
        //794. Valid Tic-Tac-Toe State
        //3244. Shortest Distance After Road Addition Queries II
        //916. Word Subsets
    }
}
