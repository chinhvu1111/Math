package E2_design;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class E3_DesignSnakeGame {

    public static class SnakeGame {

        int curFoodIndex;
        int curScore;
        int curX;
        int curY;
        Queue<Pair<Integer, Integer>> snake;
        HashSet<Pair<Integer, Integer>> snakeSet;
        HashMap<String, Pair<Integer, Integer>> dir;
        int[][] food;
        int w, h;

        public SnakeGame(int width, int height, int[][] food) {
            this.food=food;
            curFoodIndex=0;
            curScore=0;
            curX=0;
            curY=0;
            snake=new LinkedList<>();
            snakeSet=new HashSet<>();
            Pair<Integer, Integer> initNode = new Pair<>(0, 0);
            snake.add(initNode);
            snakeSet.add(initNode);
            dir=new HashMap<>();
            dir.put("U", new Pair<>(-1,0));
            dir.put("D", new Pair<>(1,0));
            dir.put("L", new Pair<>(0,-1));
            dir.put("R", new Pair<>(0,1));
            w=width;
            h=height;
        }

        public int move(String direction) {
            Pair<Integer, Integer> curDir=dir.get(direction);
            curX=curX+curDir.getKey();
            curY=curY+curDir.getValue();
            // System.out.printf("CurX:%s, CurY: %s, curFoodIndex: %s, Food length: %s\n", curX, curY, curFoodIndex, food.length);
            // System.out.printf("%s\n", snake);
            if(curX<0||curX>=h||curY<0||curY>=w){
                // System.out.printf("Cond: %s\n", -1);
                return -1;
            }
            Pair<Integer, Integer> newNode=new Pair<>(curX, curY);
            if(curFoodIndex<food.length&&curX==food[curFoodIndex][0]&&curY==food[curFoodIndex][1]){
                curScore++;
                curFoodIndex++;
            }else{
                snakeSet.remove(snake.poll());
            }
            if(snakeSet.contains(newNode)){
                // System.out.printf("Set: %s\n", -1);
                return -1;
            }
            // System.out.printf("curFoodIndex: %s\n", curFoodIndex);
            snake.add(newNode);
            snakeSet.add(newNode);
            return curScore;
        }
    }

    public static void main(String[] args) {
        // Requirement
        //- Play snake game
        //- List of food: Chỉ được ăn từng food
        //  + Nếu food with index=0 have been ate ==> The food with index=1 will appear
        //- Mỗi lần ăn 1 food ==> Snake sẽ dài ra 1 unit
        //* Return scores for each move
        //- return -1 when:
        //  + Snake go out of bound
        //  + Snake go to itself
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= width, height <= 104
        //1 <= food.length <= 50
        //food[i].length == 2
        //0 <= ri < height
        //0 <= ci < width
        //direction.length == 1
        //direction is 'U', 'D', 'L', or 'R'.
        //At most 10^4 calls will be made to move.
        //- Chiều dài tối đa của snake=50
        //
        //- Brainstorm
        //- Phần khó nhất là phần update vị trí của nake
        //+ Khi nó ăn xong food + tăng length + move
        //+ Khi nó move với length=x
        //
        //- Khi ăn food xong :
        //  + Tăng length: mark length++
        //  + Next move: ==> Lúc này mới là đánh dấu length tăng
        //- Khi snake move với length=x
        //  + Update lại các node của nó di chuyển theo ==> ta dùng queue là được pop dần các node ở last ra thôi.
        //- Để check xem có đi vào chính nó hay không:
        //  + Dùng hashSet cho node trong snake là được.
    }
}
