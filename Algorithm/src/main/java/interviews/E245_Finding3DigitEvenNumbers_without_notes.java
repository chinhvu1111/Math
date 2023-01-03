package interviews;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class E245_Finding3DigitEvenNumbers_without_notes {

    public static void dfs(int currentNumber, int currentLength, int[] digits,
                           List<Integer> evensNumber, TreeSet<Integer> rs, int[] visited){
        if(currentLength==2){
            for(int i=0;i<evensNumber.size();i++){
                if(visited[evensNumber.get(i)]==0){
                    rs.add(currentNumber*10+digits[evensNumber.get(i)]);
//                    System.out.println(currentNumber);
                }
            }
            return;
        }
        for(int i=0;i<digits.length;i++){
            if(currentLength==0&&digits[i]==0){
                continue;
            }
            if(visited[i]==0){
                visited[i]=1;
                dfs(currentNumber*10+digits[i], currentLength+1, digits, evensNumber, rs, visited);
                visited[i]=0;
            }
        }
    }

    public static int[] findEvenNumbers(int[] digits) {
        List<Integer> evensNumber=new ArrayList<>();
        int n=digits.length;
        int[] visited=new int[n];

        for(int i=0;i<n;i++){
            if(digits[i]%2==0){
                evensNumber.add(i);
            }
        }
        TreeSet<Integer> rs=new TreeSet<>();
        dfs(0, 0, digits, evensNumber, rs, visited);
        int[] rsArr=new int[rs.size()];

        Iterator<Integer> iter = rs.iterator();
        int index=0;
        while (iter.hasNext()){
            rsArr[index++]=iter.next();
        }
//        System.out.println(rs);
        return rsArr;
    }

    public static void main(String[] args) {
//        int[] arr=new int[]{2,1,3,0};
        int[] arr=new int[]{2,2,8,8,2};
        findEvenNumbers(arr);
        //
        //
        //#Reference
        //2095. Delete the Middle Node of a Linked List
        //1295. Find Numbers with Even Number of Digits
    }
}
