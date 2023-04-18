package mock;

import java.util.HashSet;

public class Test_7_amazone {

    public class Node{
        int x=0;
        int y=0;
        long dist=0;

        public Node(int x, int y, long dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    //** Đề bài:
    //- Tìm k điểm gần nhất (0,0)
    //
    //** Bài này tư duy như sau:
    //1.
    //1.0, Idea
    //- Đầu tiên vì kết quả unique --> Ta cần distinct trước ==> Hashset<Int[]>
    //- Tạo array mới chứa các Node[] ==> Mục đích là để swap elements
    //- Node chứa x,y, dist(long)
    //- Sau đó sẽ dùng select sort để có thể tính
    //1.1, Select sort : Tìm phần tử nhỏ thứ k (4)
    //VD:
    //2,3,5,1,12,7,9,1,5
    //2,3,5(y),1(x),12,7,9,1,(4)
    //- X sẽ tăng liên tục
    //2,3,1,5(y),12,7,9,1(x),(4)
    //2,3,1,1,12(y do y++),7,9,5,(4)
    //
    //2,3,5,1,12,7,9,1,5
    //--> Lấy phần tử cuối làm chuẩn.
    //+ 2 pointers:
    //x=0 : [ Tăng liên tục ] Vị trí phần tử nhỏ hơn 4 --> thì ta mới replace
    //y=0 : Vị trí phần tử >4 ==> y luôn ++ nếu có replace vì giữa khoảng (x,y) replace luôn tồn tại số > pivot / chỉ (x,y) cạnh nhau là replace.
    //** Chỉ đến khi tìm được x mới --> replace (x với y)
    public int[][] kClosest(int[][] points, int k) {
        HashSet<int[]> hashSet=new HashSet<>();
        int n=points.length;

        for(int i=0;i<n;i++){
            int x=points[i][0];
            int y=points[i][1];
            hashSet.add(new int[]{x, y});
        }
        Node[] nodes=new Node[hashSet.size()];
        int index=0;

        for(int[] e:hashSet){
            Node node=new Node(e[0], e[1], getDist(e[0], e[1]));
            nodes[index]=node;
            index++;
        }
        quickSelect(nodes, 0, nodes.length-1, k);
        int[][] rs=new int[k][2];

        for(int i=0;i<k;i++){
            rs[i][0]=nodes[i].x;
            rs[i][1]=nodes[i].y;
            System.out.printf("%s %s\n", nodes[i].x, nodes[i].y);
        }
        return rs;
    }

    public void quickSelect(Node[] nodes, int low, int high, int k){
        while (low<=high){
            System.out.printf("%s %s\n", low, high);
            Node pivot=nodes[high];
            int j=0;

            for(int i=low;i<=high;i++){
                if(nodes[i].dist < pivot.dist){
                    Node tmp=nodes[i];
                    nodes[i]=nodes[j];
                    nodes[j]=tmp;
                    j++;
                }
            }
            if(j!=high){
                Node tmp=nodes[high];
                nodes[high]=nodes[j];
                nodes[j]=tmp;
            }
            if(j+1==k){
                return;
            }
            if(j+1>k){
                high=j-1;
            }else{
                low=j+1;
            }
        }
    }

    public long getDist(int x, int y){
        return (long) x *x+ (long) y *y;
    }

    public static void main(String[] args) {
        Test_7_amazone test7Amazone=new Test_7_amazone();
        Test_7_amazone test7Amazone1=new Test_7_amazone();
        int[][]points2 = {{1,3},{-2,2}};
        int k = 1;
        test7Amazone.kClosest(points2, k);
        int[][]points1 = {{3,3},{5,-1},{-2,4}};
        k = 2;
        test7Amazone1.kClosest(points1, k);
        //#Reference:
        //974. Subarray Sums Divisible by K
        //692. Top K Frequent Words
        //1779. Find Nearest Point That Has the Same X or Y Coordinate
    }
}
