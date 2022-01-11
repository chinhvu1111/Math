/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import static java.util.stream.Collectors.toList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @author chinhvu
 */
public class KingdomDivisionHack1 {

    public static HashMap<Integer, List<Integer>> hashMap;
    public static long dp[][];
    public static int mod;
    public static boolean visited[];
    
    public static int queue[];
    public static int front=-1,rear=-1;
    public static int length=-1;
    
    public static void push(int x){
        queue[rear+1]=x;
        rear++;
        length++;
    }
    
    public static int pop(){
        int p=queue[front+1];
        front++;
        length--;
        return p;
    }
    
    public static void solution(int x, int count){
        List<Integer> adjacents=hashMap.get(x);
        if(adjacents==null){
            return;
        }
        
        long all=1;
        long unsafe=1;
        long safe=1;
        System.out.println(count);
        
        visited[x]=true;
        for(Integer y:adjacents){
            if(!visited[y]){
                //Làm cái kiểu này nhớ điền visited trước tránh
                //Quay lại vô hạn
//                visited[y]=true;
                solution(y, count+1);
            }else{
                continue;
            }
            all*=dp[y][0]+dp[y][0]+dp[y][1];
            all%=mod;
            unsafe*=dp[y][0];
            unsafe%=mod;
        }
        //Cộng sau thì kết quả mới đúng được
        //Chỉ khi trừ thì mới cộng
        safe=all-unsafe;
        //Or all+=mod
        safe+=mod;
        safe%=mod;
        dp[x][0]=safe;
        dp[x][1]=unsafe;
    }
    
    public static int kingdomDivision(int n, List<List<Integer>> roads) {
    // Write your code here
        hashMap=new HashMap<>();
        int max=0;
        mod=(int)Math.pow(10, 9)+7;
        
        for(List<Integer> adjacents: roads){
            Integer x=adjacents.get(0);
            Integer y=adjacents.get(1);
            max=Math.max(max, Math.max(x, y));
            List<Integer> listX=hashMap.get(x);
            List<Integer> listY=hashMap.get(y);
            
            if(listX==null){
                listX=new ArrayList<>();
                listX.add(y);
            }else{
                listX.add(y);
            }
            if(listY==null){
                listY=new ArrayList<>();
                listY.add(x);
            }else{
                listY.add(x);
            }
            
            hashMap.put(x, listX);
            hashMap.put(y, listY);
        }
        dp=new long[max+1][2];
        visited=new boolean[max+1];
        
        for(int i=1;i<=max;i++){
            dp[i][0]=-1;
            dp[i][1]=-1;
        }
        queue=new int[max+1];
//        solution(max, 0);
        return solutionOptimize(max);
    }
    
    public static int solutionOptimize(int x){
        push(x);
        visited[x]=true;
        
        while(length!=-1){
            int currentNode=pop();
            List<Integer> adjacents=hashMap.get(currentNode);
        
            for(Integer y:adjacents){
                if(!visited[y]){
                    push(y);
                    visited[y]=true;
                }
            }
        }
        for(int i=x;i>=1;i--){
            List<Integer> adjacents=hashMap.get(queue[i-1]);
            long all=1;
            long unsafe=1;
            long safe=1;
        
            for(Integer y:adjacents){
                if(dp[y][1]==-1||dp[y][0]==-1){
                    continue;
                }
                all*=dp[y][0]+dp[y][0]+dp[y][1];
                all%=mod;
                unsafe*=dp[y][0];
                unsafe%=mod;
            }
            //Cộng sau thì kết quả mới đúng được
            //Chỉ khi trừ thì mới cộng
            safe=all-unsafe;
            //Or all+=mod
            safe+=mod;
            safe%=mod;
            dp[queue[i-1]][0]=safe;
            dp[queue[i-1]][1]=unsafe;
        }
        return (int)((dp[x][0]*2)%mod);
    }
    
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/home/chinhvu/NetBeansProjects/Algorithm/src/algorithm/input"));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> roads = new ArrayList<>();

        IntStream.range(0, n - 1).forEach(i -> {
            try {
                roads.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = kingdomDivision(n, roads);

        System.out.println(result);

        bufferedReader.close();
    }
    
}
