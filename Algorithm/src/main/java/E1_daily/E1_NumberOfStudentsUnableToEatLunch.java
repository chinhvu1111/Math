package E1_daily;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class E1_NumberOfStudentsUnableToEatLunch {

    public static int countStudents(int[] students, int[] sandwiches) {
        Queue<Integer> studentQueue=new LinkedList<>();
        Queue<Integer> sanwichesQueue=new LinkedList<>();
        int n=students.length;

        for(int i=0;i<n;i++){
            studentQueue.add(students[i]);
            sanwichesQueue.add(sandwiches[i]);
        }
//        boolean isBreak=false;
        while (true){
            int count=studentQueue.size();
            while(count > 0 && !sanwichesQueue.isEmpty() && !Objects.equals(studentQueue.peek(), sanwichesQueue.peek())){
                studentQueue.add(studentQueue.poll());
                count--;
            }
            if(count==0){
                return studentQueue.size();
            }
            studentQueue.poll();
            sanwichesQueue.poll();
            if(studentQueue.isEmpty()){
                return 0;
            }
            if(sanwichesQueue.isEmpty()){
                return studentQueue.size();
            }
//            System.out.printf("%s %s\n", studentQueue.size(), sanwichesQueue.size());
        }
    }

    public static void main(String[] args) {
        //** Requirement
        //- The school cafeteria offers circular and square sandwiches at lunch break, referred to by numbers 0 and 1 respectively.
        // All students stand in a queue. Each student either prefers square or circular sandwiches.
        //- The number of sandwiches in the cafeteria is equal to the number of students. The sandwiches are placed in a stack.
        // At each step:
        // + If the student at the front of the queue prefers the sandwich on the top of the stack, they will take it and leave the queue.
        // + Otherwise, they will leave it and go to the queue's end.
        //- This continues until none of the queue students want to take the top sandwich and are thus unable to eat.
        //You are given two integer arrays students and sandwiches where sandwiches[i] is the type of the
        // ith sandwich in the stack (i = 0 is the top of the stack) and students[j] is the preference of the
        // jth student in the initial queue (j = 0 is the front of the queue).
        //* Return the number of students that are unable to eat.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= students.length, sandwiches.length <= 100
        //students.length == sandwiches.length
        //sandwiches[i] is 0 or 1.
        //students[i] is 0 or 1.
        //
        //- Brainstorm
        //Ex:
        //Input: students = [1,1,0,0], sandwiches = [0,1,0,1]
        //Output: 0
        //Explanation:
        //- Front student leaves the top sandwich and returns to the end of the line making students = [1,0,0,1].
        //- Front student leaves the top sandwich and returns to the end of the line making students = [0,0,1,1].
        //- Front student takes the top sandwich and leaves the line making students = [0,1,1] and sandwiches = [1,0,1].
        //- Front student leaves the top sandwich and returns to the end of the line making students = [1,1,0].
        //- Front student takes the top sandwich and leaves the line making students = [1,0] and sandwiches = [0,1].
        //- Front student leaves the top sandwich and returns to the end of the line making students = [0,1].
        //- Front student takes the top sandwich and leaves the line making students = [1] and sandwiches = [1].
        //- Front student takes the top sandwich and leaves the line making students = [] and sandwiches = [].
        //Hence all students are able to eat.
        //
        //
//        int[] students = {1,1,0,0}, sandwiches = {0,1,0,1};
        int[] students = {1,1,1,0,0,1}, sandwiches = {1,0,0,0,1,1};
        System.out.println(countStudents(students, sandwiches));
        //#Reference:
        //2073. Time Needed to Buy Tickets
    }
}
