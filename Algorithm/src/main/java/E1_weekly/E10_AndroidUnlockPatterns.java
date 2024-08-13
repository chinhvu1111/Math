package E1_weekly;

import java.util.ArrayList;
import java.util.List;

public class E10_AndroidUnlockPatterns {

    public static int[] memo;

//    public static boolean isTraversed(int x, int y, int x1, int y1, boolean[][] visited){
//        //
//        //0 0 0 1
//        //0 0 0 0
//        //0 1 0 0
//        int minX=Math.min(x, x1);
//        int minY=Math.min(y, y1);
//        int maxX=Math.max(x, x1);
//        int maxY=Math.max(y, y1);
//        int count=0;
//
//        while (minX<maxX&&minY<maxY){
//            if(visited[minY][minY]){
//                count++;
//            }
//            minX++;
//            minY++;
//        }
//        return count==Math.abs(x-x1);
//    }

    public static boolean isTraversed(int x, int y, int x1, int y1, boolean[][] visited){
        int t=0;
        //
        //0 0 0 1
        //0 0 0 0
        //0 1 0 0
        //  + (x,y), (x1,y1)
        //      + (x,y)
        //              (x1,y1)
        //      + (x1,y1)
        //              (x,y)
        //      +         (x,y)
        //        (x1,y1)
        //      +         (x1,y1)
        //        (x,y)
        int count=0;
        if(x<=x1&&y<=y1){
            while (x<=x1&&y<=y1){
                if(visited[x][y]){
                    count++;
                }
                x++;
                y++;
                t++;
            }
        }else if(x1<=x&&y1<=y){
            while (x1<=x&&y1<=y){
                if(visited[x1][y1]){
                    count++;
                }
                x1++;
                y1++;
                t++;
            }
        }else if(x<=x1&&y1<=y){
            while (x<=x1&&y>=y1){
                if(visited[x][y]){
                    count++;
                }
                x++;
                y--;
                t++;
            }
        }else if(x1<=x&&y1>=y){
            while (x1<=x&&y1>=y){
                if(visited[x1][y1]){
                    count++;
                }
                x1++;
                y1--;
                t++;
            }
        }
        return count==t-1;
    }

    public static boolean check(int x, int y, int x1, int y1, boolean[][] visited){
        int t=0;
        int count=0;
        if(x==x1){
            if(y<=y1){
                while (y<=y1){
                    if(visited[x][y]){
                        count++;
                    }
                    y++;
                    t++;
                }
            }else{
                while (y1<=y){
                    if(visited[x1][y1]){
                        count++;
                    }
                    y1++;
                    t++;
                }
            }
        }else{
            if(x<=x1){
                while (x<=x1){
                    if(visited[x][y]){
                        count++;
                    }
                    x++;
                    t++;
                }
            }else{
                while (x1<=x){
                    if(visited[x1][y1]){
                        count++;
                    }
                    x1++;
                    t++;
                }
            }
        }
        return count==t-1;
    }

    public static void solution(int index,int x, int y, List<int[]> nodes, boolean[][] visited){
        memo[index]++;
        for(int[] nextNode: nodes){
            if(visited[nextNode[0]][nextNode[1]]){
                continue;
            }
            if(nextNode[0]!=x&&nextNode[1]!=y
                    &&((Math.abs(nextNode[0]-x)!=Math.abs(nextNode[1]-y))
                    ||Math.abs(nextNode[0]-x)==1)){
                visited[nextNode[0]][nextNode[1]]=true;
                solution(index+1, nextNode[0], nextNode[1], nodes, visited);
                visited[nextNode[0]][nextNode[1]]=false;
            }else if((nextNode[0]==x&&Math.abs(nextNode[1]-y)==1)||(nextNode[1]==y&&Math.abs(nextNode[0]-x)==1)){
                visited[nextNode[0]][nextNode[1]]=true;
                solution(index+1, nextNode[0], nextNode[1], nodes, visited);
                visited[nextNode[0]][nextNode[1]]=false;
            }else if(
                    (Math.abs(nextNode[0]-x)==Math.abs(nextNode[1]-y)&&isTraversed(x, y, nextNode[0], nextNode[1], visited))
            ||(((nextNode[0]-x)==0||(nextNode[1]-y)==0)&&check(x, y, nextNode[0], nextNode[1], visited))){
//                System.out.printf("x:%s, y:%s, x1:%s, y1:%s\n", x, y, nextNode[0], nextNode[1]);
                visited[nextNode[0]][nextNode[1]]=true;
                solution(index+1, nextNode[0], nextNode[1], nodes, visited);
                visited[nextNode[0]][nextNode[1]]=false;
            }
        }
    }

    public static int numberOfPatterns(int m, int n) {
        memo=new int[10];
        List<int[]> nodes=new ArrayList<>();
        boolean[][] visited=new boolean[3][3];

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                nodes.add(new int[]{i, j});
            }
        }
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                visited[i][j]=true;
                solution(0, i, j, nodes, visited);
                visited[i][j]=false;
            }
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(memo[i]);
        }
        int rs=0;
        for(int i=m-1;i<n;i++){
            rs+=memo[i];
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Android devices have a special lock screen with a 3 x 3 grid of dots.
        // Users can set an "unlock pattern" by connecting the dots in a specific sequence,
        // forming a series of joined line segments where each segment's endpoints are two consecutive dots in the sequence.
        // A sequence of k dots is a valid unlock pattern if both of the following are true:
        //  + All the dots in the sequence are distinct.
        //  + If the line segment connecting two consecutive dots in the sequence passes through the center of any other dot, the other dot must have previously appeared in the sequence. No jumps through the center non-selected dots are allowed.
        //  + For example, connecting dots 2 and 9 without dots 5 or 6 appearing beforehand is valid because the line from dot 2 to dot 9 does not pass through the center of either dot 5 or 6.
        //  + However, connecting dots 1 and 3 without dot 2 appearing beforehand is invalid because the line from dot 1 to dot 3 passes through the center of dot 2.
        //Here are some example valid and invalid unlock patterns:
        //
        //  + The 1st pattern [4,1,3,6] is invalid because the line connecting dots 1 and 3 pass through dot 2,
        //  but dot 2 did not previously appear in the sequence.
        //  + The 2nd pattern [4,1,9,2] is invalid because the line connecting dots 1 and 9 pass through dot 5,
        //  but dot 5 did not previously appear in the sequence.
        //  + The 3rd pattern [2,4,1,3,6] is valid because it follows the conditions.
        //  The line connecting dots 1 and 3 meets the condition because dot 2 previously appeared in the sequence.
        //  + The 4th pattern [6,5,4,1,9,2] is valid because it follows the conditions.
        //  The line connecting dots 1 and 9 meets the condition because dot 5 previously appeared in the sequence.
        //
        //Given two integers m and n,
        //* return (the number of "unique" and "valid" unlock patterns) of the Android grid lock screen that consist of:
        //  + at least (m keys)
        //  + at most (n keys).
        //- Two unlock patterns are considered unique:
        // + if there is a dot in one sequence that is not in the other
        // + or (the order of the dots) is different.
        //
        //**Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= m, n <= 9
        //
        //- Brainstorm
        //- Ít nhất m keys + tối đa n keys
        //  + Đoạn này thì có thể xét từng số lượng được không?
        //- Tìm 1 path có x keys trong grid ntn?
        //  + Start từ 1 cell nào đó
        //- 1 cell (i,j) đến (x,y) nếu qua 1 cell nào đó:
        //  + Nó sẽ qua nhiều cells ở giữa nữa
        //  + Không qua cell nào --> Không qua hết
        //==> Miễn là không tạo đường chéo là được
        //1 0 0
        //0 0 1
        //==> Hiệu giữa |x1-x| != |y1-y|
        //- Vì có thể có case:
        //  + |x1-x| != |y1-y| == 1 thoả mãn ==> loại ra là được.
        //
        //- Special cases:
        //0 0 1 0 0 1
        //==> (x1-x==0) != (y1-y==3) ==> Invalid
        //0 0 1 1 ==> Valid
        //
        //- Còn case node đi rồi:
        //==> |x1-x|==|y1-y| vẫn valid
        //  + Các node nằm trên đường chéo giữa (x1,y1) và (x,y) traversed
        //- Có các cases như sau:
        //  + (x,y), (x1,y1)
        //      + (x,y)
        //              (x1,y1)
        //      + (x1,y1)
        //              (x,y)
        //      +         (x,y)
        //        (x1,y1)
        //      +         (x1,y1)
        //        (x,y)
        //
        //- n sẽ có limit = 9
        //  + Số dot sẽ unique ==> length limit = 9
        //
        //- Backtrack thử:
        //- List ra số các cell (i,j)
        //==> Backtrack thôi.
        //- Số lượng cases tính ntn?
        //  + 1 -> 2 -> 4
        //  + 1 -> 2 -> 3
//        int m = 1, n = 1;
        int m = 1, n = 2;
//        int m = 1, n = 3;
        System.out.println(numberOfPatterns(m, n));
    }
}
