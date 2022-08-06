/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package leetcode_medium_dynamic;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chinhvu
 */
public class E36_BeautifulArrangement {

    public static List<Integer>[] nextNodes;
    public static boolean visited[];

    public static int solution(int node, int length, String s){
        if(length==visited.length-1){
            System.out.println(s);
            return 1;
        }
        int rs=0;
        for(int i=0;i<nextNodes[node].size();i++){
            if(!visited[nextNodes[node].get(i)]){
                visited[nextNodes[node].get(i)]=true;
                rs+=solution(node+1, length+1, s+nextNodes[node].get(i));
                visited[nextNodes[node].get(i)]=false;
            }
        }
        return rs;
    }

    public static int countArrangement(int n) {
        nextNodes = new ArrayList[n+1];
        visited = new boolean[n+1];
        for (int i = 1; i <= n; i++) {
            nextNodes[i] = new ArrayList();
        }
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(j%i==0||i%j==0){
                    nextNodes[i].add(j);
                }
            }
        }
        return solution(1,0,"");
    }

    public static void swap(int arr[], int i, int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    public static int countArrangement1(int n){
        int arr[]=new int[n+1];

        for(int i=1;i<=n;i++){
            arr[i]=i;
        }
        int rs=chooseSolution(arr, n);
        return rs;
    }

    public static int chooseSolution(int arr[], int start){
//        if(start==arr.length){
//            return 1;
//        }
        if(start==0){
            return 1;
        }
        int rs=0;

        for(int i=start;i>=1;i--){
            if(arr[i]%start==0||start%arr[i]==0){
                swap(arr, i, start);
                //1.Vấn đề ở đây là đến khi nào dừng lại: --> Phải vào được if thì mới dừng lại
                rs+=chooseSolution(arr, start-1);
                swap(arr, i, start);
            }
//            if(arr[i]%start==0||arr[start]%i==0){
//                swap(arr, start, i);
//            }
//            //2. Khi vấn đề là chỉ những trường hợp cho kết quả đúng --> Return 1
//            //Khi nào thì không chạy tiếp
//            rs+=chooseSolution(arr, start+1);
//            if(arr[i]%start==0||arr[start]%i==0){
//                swap(arr,i, start);
//            }

            //3, Câu hỏi là tại sao không nhét phần swap này vào trong (if)
            //Và viết lại phần predicate thành: arr[start]%i==0 thay vì laf arr[start]%start==0
            //Answer: --> Để làm mọi thứ clear hơn :
            //Swap trước đề quy về chỉ 1 vị trí <=> Thay vì xét 2 vị trí (i), (start)
            //--> Quy về start thôi : arr[start]%start=0
            swap(arr, start, i);
            if (arr[start] % start == 0 || start % arr[start] == 0) rs+=chooseSolution(arr, start+1);
            //3.1, swap(i,j) với swap(j,i) là như nhau
            swap(arr,start, i);

        }
        return rs;
    }

    public static void main(String[] args) {
        int n=4;
//        System.out.println(countArrangement(n));
        System.out.println(countArrangement1(n));
    }
}
