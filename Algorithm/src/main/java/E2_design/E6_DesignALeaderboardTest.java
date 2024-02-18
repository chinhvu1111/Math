package E2_design;

import java.util.*;

public class E6_DesignALeaderboardTest {

    public static class Leaderboard {

        public class Node{
            int player;
            int score;
            public Node(int player, int score){
                this.player=player;
                this.score=score;
            }

            @Override
            public String toString() {
                return "Node{" +
                        "player=" + player +
                        ", score=" + score +
                        '}';
            }
        }

        public PriorityQueue<Node> topKPlayers;
//        public PriorityQueue<Node> remainingPlayers;
        public HashMap<Integer, Node> scoreInfor;
//        public int sumTopKScore;

        public Leaderboard() {
            topKPlayers=new PriorityQueue<>(new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    if(o1.score!=o2.score){
                        return o2.score-o1.score;
                    }
                    return o1.player-o2.player;
                }
            });
            scoreInfor=new HashMap<>();
//            sumTopKScore=0;
        }

        public void addScore(int playerId, int score) {
            if(!scoreInfor.containsKey(playerId)){
                Node newPlayer=new Node(playerId, score);
                scoreInfor.put(playerId, newPlayer);
//                sumTopKScore+=newPlayer.score;
                topKPlayers.add(newPlayer);
            }else{
                Node oldElement=scoreInfor.get(playerId);
                oldElement.score=oldElement.score+score;
                reheapify();
            }
        }

        public void reheapify(){
            if(!topKPlayers.isEmpty()) {
                topKPlayers.add(topKPlayers.remove());
            }
        }

        public int top(int k) {
            int count=0;
            int sum=0;
            List<Node> list=new ArrayList<>();

            while(count<k&&!topKPlayers.isEmpty()){
                Node curElement=topKPlayers.poll();
                sum+=curElement.score;
                list.add(curElement);
                count++;
            }
            topKPlayers.addAll(list);
            return sum;
        }

        public void reset(int playerId) {
            if(scoreInfor.containsKey(playerId)){
                Node oldElement=scoreInfor.get(playerId);
                oldElement.score=0;
                reheapify();
            }
        }
    }

    public static class Node{
        int value;
        public Node(int value){
            this.value=value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }

    public static void main(String[] args) {
        // Requirement
        //* Design a Leaderboard class, which has 3 functions:
        //+ addScore(playerId, score): Update the leaderboard by adding score to the given player's score.
        // If there is no player with such id in the leaderboard, add him to the leaderboard with the given score.
        //+ top(K): Return the score sum of the top K players.
        //+ reset(playerId): Reset the score of the player with the given id to 0 (in other words erase it from the leaderboard).
        // It is guaranteed that the player was added to the leaderboard before calling this function.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= playerId, K <= 10000
        //It's guaranteed that K is less than or equal to the current number of players.
        //1 <= score <= 100
        //There will be at most 1000 function calls.
        //
        //- Brainstorm
        //- Priority queue + hashmap
        //- Priority queue sort by:
        //+ score : Cái này để lấy k thằng lớn nhất
        //+ player id : Cái này để khi reset score ==> Ta có thể check xem queue có tồn tại player id đó không.
        //
        //- Mình cần thử case update lại value của 1 node trong priority Queue bằng hashmap
        //==> check PriorityQueue nó có tự sort lại hay không
        //* ==> Change value không update lại peek
        //** REHEAPIFY:
        //if(!queue.isEmpty()) {
        //            queue.add(queue.remove());
        //}
        //
        Node x=new Node(4);
        Node x1=new Node(8);
        Node x2=new Node(2);
        PriorityQueue<Node> queue=new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.value-o2.value;
            }
        });
        queue.add(x);
        queue.add(x1);
        queue.add(x2);
        System.out.println(queue.peek());
        System.out.println(queue);
        x1.value=1;
        System.out.println(queue.peek());
        System.out.println(queue);
        //==> Change value không update lại peek
        //Try re-heapify
        if(!queue.isEmpty()) {
            queue.add(queue.remove());
        }
        System.out.println("After reheapifying");
        System.out.println(queue.peek());
        System.out.println(queue);
        //test
        System.out.println("Test");
        Leaderboard leaderboard=new Leaderboard();
        leaderboard.addScore(1, 100);
        System.out.println(leaderboard.top(10));
    }
}
