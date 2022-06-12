package leetcode_medium_greedy;

import java.util.*;

public class E7_QueueReconstructionByHeight {

    static class Node{
        int x;
        int y;
        Node next;
        Node previous;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int[][] reconstructQueue(int[][] people) {
        int n=people.length;
        int m=people[0].length;
        Integer[][] peopleBox=new Integer[n][m];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                peopleBox[i][j]=people[i][j];
            }
        }
        Arrays.sort(peopleBox,new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] t1, Integer[] t2) {
                if(t1[0]>=t2[0]){
                    return 1;
                }
                return -1;
            }
        });
        Node root=null;

        if(n>=1){
            root=new Node(peopleBox[0][0], peopleBox[0][1]);
        }

        for(int i=1;i<n;i++){
            Integer currentNodeX=peopleBox[i][0];
            Integer currentNodeY=peopleBox[i][1];
            Node newNode=new Node(currentNodeX, currentNodeY);
            Node temp=root;
            int count=0;

            if(temp.next==null){
                if(temp.y>newNode.y){
                    newNode.next=temp;
                    root=newNode;
                }else{
                    root.next=newNode;
                }
                continue;
            }
            while (temp!=null){
                if(temp.x<=currentNodeX){
                    count++;
                }
                if(count==currentNodeY&&currentNodeX>=temp.x){
                    Node nextNode=temp.next;
                    temp.next=newNode;
                    newNode.next=nextNode;
                    break;
                }
                temp=temp.next;
            }
        }
        int rs[][]=new int[n][m];

        return null;
    }

    public static void main(String[] args) {
        int people[][]=new int[][]{{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
        System.out.println(reconstructQueue(people));
    }
}
