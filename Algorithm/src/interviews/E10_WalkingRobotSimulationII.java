package interviews;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class E10_WalkingRobotSimulationII {
    int x;
    int y;
    int width;
    int height;
    String currentDirection[]=new String[]{"East","North","West","South"};
    int currentDirectioIndex=0;
    int directionX[]={1, 0, -1, 0};
    int directionY[]={0, 1, 0, -1};

    public E10_WalkingRobotSimulationII(int width, int height) {
        this.width=width;
        this.height=height;
    }

    public void step(int num) {
        int step=num;

        if(height>=2){
            step=num%(width*2+(height-2)*2);
        }else if(width>=2){
            step=num%((width-2)*2+height*2);
        }
        if(num/(width*2+(height-2)*2)>=0) {
            if (x == 0 && y == 0&&currentDirectioIndex==0) {
                currentDirectioIndex = 3;
            }
        }
//        if((x==0&&y==0)||(x==width-1&&y==0)||(x==width&&y==height-1)||(x==0&&y==height-1)){
//            currentDirectioIndex--;
//            if(currentDirectioIndex<0){
//                currentDirectioIndex=3;
//            }
//        }

        for(int i=1;i<=step;i++){
            int nextX=x+directionX[currentDirectioIndex];
            int nextY=y+directionY[currentDirectioIndex];

            if(nextX<0||nextX>=width||nextY<0||nextY>=height){
                currentDirectioIndex++;
                if(currentDirectioIndex>3){
                    currentDirectioIndex=0;
                }
                i--;
            }else{
                x=nextX;
                y=nextY;
            }
        }
    }

    public int[] getPos() {
        return new int[]{x,y};
    }

    public String getDir() {
        return currentDirection[currentDirectioIndex];
    }

    public static void main(String[] args) {
//        E10_WalkingRobotSimulationII robot=new E10_WalkingRobotSimulationII(6, 3);
//        robot.step(2);  // It moves two steps East to (2, 0), and faces East.
//        robot.step(2);  // It moves two steps East to (4, 0), and faces East.
//        System.out.println(robot.getPos()); // return [4, 0]
//        System.out.println(robot.getDir()); // return "East"
//        robot.step(2);  // It moves one step East to (5, 0), and faces East.
//        // Moving the next step East would be out of bounds, so it turns and faces North.
//        // Then, it moves one step North to (5, 1), and faces North.
//        robot.step(1);  // It moves one step North to (5, 2), and faces North (not West).
//        robot.step(4);  // Moving the next step North would be out of bounds, so it turns and faces West.
//        // Then, it moves four steps West to (1, 2), and faces West.
//        System.out.println(robot.getPos()); // return [1, 2]
//        System.out.println(robot.getDir()); // return "West"

        //Case 1: Case đặc biệt với:
        //Start =(0,0) Hướng east --> Quay n vòng --> Chuyển qua south
        //--> Các biên khác thì không ảnh hưởng gì cả.
        E10_WalkingRobotSimulationII robot=new E10_WalkingRobotSimulationII(97, 98);
        robot.step(448918);  // It moves two steps East to (2, 0), and faces East.
//        robot.step(66392);  // It moves two steps East to (2, 0), and faces East.
//        robot.step(83376);  // It moves two steps East to (2, 0), and faces East.
//        robot.step(71796);  // It moves two steps East to (2, 0), and faces East.
//        robot.step(57514);  // It moves two steps East to (2, 0), and faces East.
//        robot.step(36284);  // It moves two steps East to (2, 0), and faces East.
//        robot.step(69866);  // It moves two steps East to (2, 0), and faces East.
//        robot.step(31652);  // It moves two steps East to (2, 0), and faces East.
//        robot.step(32038);  // It moves two steps East to (2, 0), and faces East.
        System.out.println(robot.getPos()); // return [1, 2]
        System.out.println(robot.getDir()); // return "West"
    }
}
