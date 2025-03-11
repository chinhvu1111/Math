package contest.test;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class CheckTheStructure {

//    public static String isValid(List<Integer> a) {
//        int n=a.size();
//        int index=0;
//
//        while(index<n){
//            int left=Integer.MIN_VALUE;
//            int right=Integer.MAX_VALUE;
//            if(2*index+1<n){
//                left=a.get(2*index+1);
//            }
//            if(2*index+2<n){
//                right=a.get(2*index+2);
//            }
//            if(a.get(index)<=left){
//                return "NO";
//            }
//            if(a.get(index)>=right){
//                return "NO";
//            }
//            index++;
//        }
//        return "YES";
//    }

    public static String isValid(List<Integer> a){
        Stack<Integer> s = new Stack<>();
        int rootVal = Integer.MIN_VALUE;

        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) < rootVal)
                return "NO";
            while (!s.isEmpty() && s.peek() < a.get(i)) {
                rootVal = s.pop();
            }
            s.push(a.get(i));
        }
        return "YES";
    }

    public static void main(String[] args) {
        Integer[] nodes= {2,1,3,4,5};
        System.out.println(isValid(Arrays.asList(nodes)));

    }
}
