/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.algorithms;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static java.util.stream.Collectors.toList;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.naming.spi.DirStateFactory.Result;

/**
 *
 * @author chinhvu
 */
public class KingdomDivision {
    
    public static HashMap<Integer, List<Integer>> hashMap;
    public static int dp[][];
    public static int mod;
    
    public static void solution(int x){
        List<Integer> adjacents=hashMap.get(x);
        
        int all=1;
        int unsafe=1;
        int safe=1;
        for(Integer y:adjacents){
            solution(y);
            
            all*=dp[y][0]+dp[y][0]+dp[y][1];
            all%=mod;
            all+=mod;
            unsafe*=dp[y][0];
            unsafe%=mod;
        }
        safe=all-unsafe;
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
            max=Math.max(x, y);
            List<Integer> listX=hashMap.get(x);
            List<Integer> listY=hashMap.get(x);
            
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
        dp=new int[max][2];
        
        solution(max);
        return (dp[max][0]*2)%mod;
    }
    
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input"));
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


        bufferedReader.close();
    }
    
}
