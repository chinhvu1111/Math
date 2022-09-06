package interviews;

import java.util.*;

public class E150_TaskScheduler {

    static class Node{
        int x;
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
        while (!queue.isEmpty()){

        }

        return 1;
    }

    public static void main(String[] args) {
        String[] s=new String[]{"A","A","A","A","A","A","B","C","D","E","F","G"};
        int n=2;

    }
}
