package E1_daily;

import java.util.Arrays;
import java.util.Comparator;

public class E58_SortTheStudentsByTheirKthScore {

    public static void quickSort(int[][] score, int k, int low, int high){
//        System.out.printf("Low: %s, high: %s\n", low, high);
        if(low<high){
            int newPos=partition(score, low, high, k);
            quickSort(score, k, low, newPos);
            quickSort(score, k, newPos+1, high);
        }
    }

    public static int partition(int[][] score, int low, int high, int k){
        int pivot = high;
        int i=low, j=low;

        for(;i<high;i++){
            if(score[i][k]>=score[pivot][k]){
                int[] temp=score[i];
                score[i]=score[j];
                score[j]=temp;
                j++;
            }
        }
        int[] temp=score[j];
        score[j]=score[pivot];
        score[pivot]=temp;
        return j-1;
    }

    public static int[][] sortTheStudents(int[][] score, int k) {
        quickSort(score, k, 0, score.length-1);
        return score;
    }

    public static void main(String[] args) {
        //** Requirement
        //- There is a class with m students and n exams.
        //- You are given a 0-indexed m x n integer matrix score, where each row represents one student
        // and score[i][j] denotes the score the (ith student) got in (the jth exam).
        //- The matrix score contains ( ("distinct") integers only).
        //- You are also given an integer k.
        //- Sort the students (i.e., the rows of the matrix) by their scores in the kth (0-indexed) exam from (the highest) to (the lowest).
        //* Return the matrix after sorting it.
        //* Đại loại là sort matrix dựa trên (kth column).
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //m == score.length
        //n == score[i].length
        //1 <= m, n <= 250
        //1 <= score[i][j] <= 10^5
        //score consists of distinct integers.
        //0 <= k < n
        //+ Time: O(n*m*k)
        //
        //- Brainstorm
        //- Follow E241_SortThePeopleClassic
        //
        //1.1, Optimization
        //
        //1.2, Complexity
        //- N is the numer of row of the score
        //- Space: O(log(n))
        //- Time: O(n*log(n))
        //
        int[][] score = {{10,6,9,1},{7,5,11,2},{4,8,3,15}};
        int k = 2;
        sortTheStudents(score, k);
        for (int i = 0; i < score.length; i++) {
            for (int j = 0; j < score[i].length; j++) {
                System.out.printf("%s ", score[i][j]);
            }
            System.out.println();
        }
        //587. Erect the Fence
    }
}
