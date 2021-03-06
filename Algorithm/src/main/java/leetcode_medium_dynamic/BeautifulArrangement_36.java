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
public class BeautifulArrangement_36 {

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
                //1.V???n ????? ??? ????y l?? ?????n khi n??o d???ng l???i: --> Ph???i v??o ???????c if th?? m???i d???ng l???i
                rs+=chooseSolution(arr, start-1);
                swap(arr, i, start);
            }
//            if(arr[i]%start==0||arr[start]%i==0){
//                swap(arr, start, i);
//            }
//            //2. Khi v???n ????? l?? ch??? nh???ng tr?????ng h???p cho k???t qu??? ????ng --> Return 1
//            //Khi n??o th?? kh??ng ch???y ti???p
//            rs+=chooseSolution(arr, start+1);
//            if(arr[i]%start==0||arr[start]%i==0){
//                swap(arr,i, start);
//            }

            //3, C??u h???i l?? t???i sao kh??ng nh??t ph???n swap n??y v??o trong (if)
            //V?? vi???t l???i ph???n predicate th??nh: arr[start]%i==0 thay v?? laf arr[start]%start==0
            //Answer: --> ????? l??m m???i th??? clear h??n :
            //Swap tr?????c ????? quy v??? ch??? 1 v??? tr?? <=> Thay v?? x??t 2 v??? tr?? (i), (start)
            //--> Quy v??? start th??i : arr[start]%start=0
            swap(arr, start, i);
            if (arr[start] % start == 0 || start % arr[start] == 0) rs+=chooseSolution(arr, start+1);
            //3.1, swap(i,j) v???i swap(j,i) l?? nh?? nhau
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
