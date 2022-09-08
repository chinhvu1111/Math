package interviews;

import java.util.*;

public class E150_TaskScheduler {

    static class Node{
        int x;
        int currentPosition=-1;
        char c;

        public Node(int x, char c) {
            this.x = x;
            this.c = c;
        }
    }

    public static int leastInterval(char[] tasks, int n) {
        HashMap<Character, Integer> hashMap=new HashMap<>();
        int length=tasks.length;
        PriorityQueue<Node> queue= new PriorityQueue<>((node, t1) -> t1.x - node.x);

        for(int i=0;i<length;i++){
            if(!hashMap.containsKey(tasks[i])){
                hashMap.put(tasks[i], 1);
            }else{
                hashMap.put(tasks[i], hashMap.get(tasks[i])+1);
            }
        }
        Set<Map.Entry<Character, Integer>> entries = hashMap.entrySet();

        for(Map.Entry<Character, Integer> entry: entries){
            Node node=new Node(entry.getValue(), entry.getKey());
            queue.add(node);
        }
        List<Character> rs=new ArrayList<>();
        int index=0;

        while (!queue.isEmpty()){
            List<Node> temp=new ArrayList<>();

            while (!queue.isEmpty()){
                Node currentNode=queue.poll();

                if(currentNode.currentPosition!=-1&&(index-currentNode.currentPosition<n+1)){
//                    System.out.printf("%s %s %s %s, ", index, currentNode.c, currentNode.currentPosition, currentNode.x);
                    temp.add(currentNode);
                    continue;
                }
                currentNode.x--;
                currentNode.currentPosition=index;
//                System.out.printf("%s %s %s, ", currentNode.c, currentNode.currentPosition, currentNode.x);

                if(currentNode.x>0){
                    temp.add(currentNode);
                }
                rs.add(currentNode.c);
                break;
            }
            index++;
            queue.addAll(temp);
        }

        return index;
    }

    public static int leastIntervalOptimize(char[] tasks, int n) {
        if(n==0) return tasks.length;
        int[] count =new int[27];
        int length=tasks.length;

        for(int i=0;i<length;i++){
            count[tasks[i]-'A']++;
        }
        int rs=0;
        int t=0;
        PriorityQueue<Integer> integers=new PriorityQueue<>((integer, t1) -> t1-integer);

        for(int i=0;i<=26;i++){
            if(count[i]!=0){
                integers.add(count[i]);
//                System.out.printf("%s, ", count[i]);
            }
        }
//        System.out.println();

        while (!integers.isEmpty()){
            Integer currentValue=integers.poll();
            rs=Math.max(rs, (currentValue-1)*(n+1)+1+t);
//            System.out.printf("%s, ", (currentValue-1)*(n+1)+1+t);
            t++;
        }

        return rs;
    }

    public static void main(String[] args) {
//        char[] s=new char[]{'A','A','A','A','A','A','B','C','D','E','F','G'};
//        char[] s=new char[]{'A','A','A','B','B','B'};
//        char[] s=new char[]{'A','A','A','B','B','B'};
//        char[] s=new char[]{'A'};
//        char[] s=new char[]{'A','A','A'};
        char[] s=new char[]{'A','A','A','B','B','B', 'C','C','C', 'D', 'D', 'E'};
        int n=2;
        System.out.println(leastInterval(s, n));
        System.out.println(leastIntervalOptimize(s, n));
        //
    }
}
