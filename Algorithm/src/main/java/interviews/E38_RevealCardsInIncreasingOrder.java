package interviews;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class E38_RevealCardsInIncreasingOrder {

    public static int[] deckRevealedIncreasing(int[] deck) {
        Arrays.sort(deck);
        int n=deck.length;
        Queue<Integer> queue=new LinkedList<>();

        for(int i=n-1;i>=0;i--){
            if(!queue.isEmpty()){
                queue.add(queue.poll());
            }
            queue.add(deck[i]);
        }
        int rs[]=new int[queue.size()];

        for(int i=0;i<n;i++){
            rs[n-i-1]=queue.poll();
        }
        return rs;
    }

    public int[] deckRevealedIncreasingArray(int[] deck) {
        Arrays.sort(deck);
        int[] cycleArray=new int[deck.length];
        int res[]=new int[deck.length];
        for(int i=0;i<deck.length;i++){
            cycleArray[i]=i;
        }
        int head=0;
        int tail=0;
        int index=0;
        while(index<deck.length){
            res[cycleArray[head++]]=deck[index++];
            head=head%cycleArray.length;
            if(index<deck.length){
                cycleArray[tail++]=cycleArray[head++];
                tail=tail%cycleArray.length;
                head=head%cycleArray.length;
            }
        }
        return res;
    }

    public static void main(String[] args) {
//        int deck[]=new int[]{17,13,11,2,3,5,7};
        int deck[]=new int[]{};
        deckRevealedIncreasing(deck);

        //Next challenges
        //Minimum Number of Arrows to Burst Balloons
        //Robot Bounded In Circle
        //Sort Features by Popularity
    }
}
