package contest;

import java.util.HashMap;

public class E5_MinimumConsecutiveCardsToPickUp {

    public static int minimumCardPickup(int[] cards) {
        int rs=Integer.MAX_VALUE;
        HashMap<Integer, Integer> map=new HashMap<>();
        int n=cards.length;

        for(int i=0;i<n;i++){
            if (map.containsKey(cards[i])) {
                int oldIndex=map.get(cards[i]);
                rs=Math.min(i-oldIndex+1, rs);
            }
            map.put(cards[i], i);
        }
        return rs==Integer.MAX_VALUE?-1:rs;
    }

    public static void main(String[] args) {
//        int[] arr =new int[]{3,4,2,3,4,7};
//        int[] arr =new int[]{3,3,4,7};
        int[] arr =new int[]{3};
        System.out.println(minimumCardPickup(arr));
    }
}
