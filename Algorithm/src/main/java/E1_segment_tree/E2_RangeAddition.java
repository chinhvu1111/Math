package E1_segment_tree;

public class E2_RangeAddition {

    public static class LazySegmentTree{

        public int[] tree;
        public int[] lazy;
        public LazySegmentTree(int n){
            tree=new int[4*n];
            lazy=new int[4*n];
        }

        public void processPending(int left, int right, int index){
            if(lazy[index]!=0){
                tree[index]+=(right-left+1)*lazy[index];
                if(left<right){
                    lazy[2*index+1]+=lazy[index];
                    lazy[2*index+2]+=lazy[index];
                }
                lazy[index]=0;
            }
        }

        public void updateRange(int left, int right, int qLeft, int qRight, int index, int inc){
//            System.out.println(index);
            if(qLeft>right || qRight<left){
                return;
            }
            if(qLeft<=left&&qRight>=right){
                //
                lazy[index]+=inc;
                //Because:
                //  (qLeft ------------------- qRight)
                //          (left, right)
                //==> Use (left, right)
                processPending(left, right, index);
                return;
            }
            int mid=left+(right-left)/2;
            updateRange(left, mid, qLeft, qRight, 2*index+1, inc);
            updateRange(mid+1, right, qLeft, qRight, 2*index+2, inc);
            tree[index]=tree[2*index+1]+tree[2*index+2];
        }

        public int queryRange(int left, int right, int qLeft, int qRight, int index){
            if(qLeft>right || qRight<left){
                return 0;
            }
            processPending(left, right, index);
            if(qLeft<=left&&right<=qRight){
                //Because:
                //  (qLeft ------------------- qRight)
                //          (left, right)
                //==> Use (left, right)
//                processPending(left, right, index);
                return tree[index];
            }
            int mid=left+(right-left)/2;
            int leftRs=queryRange(left, mid, qLeft, qRight, 2*index+1);
            int rightRs=queryRange(mid+1, right, qLeft, qRight, 2*index+2);
            return leftRs+rightRs;
        }

    }

    public static int[] getModifiedArray(int length, int[][] updates) {
        LazySegmentTree lazySegmentTree=new LazySegmentTree(length);
        int[] rs=new int[length];

        for(int[] e: updates){
            lazySegmentTree.updateRange(0, length-1, e[0], e[1], 0, e[2]);
        }
        for(int i=0;i<length;i++){
            //Why are not (i,i)
            rs[i]=lazySegmentTree.queryRange(0, length-1, i, i, 0);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an integer length and (an array updates) where updates[i] = [startIdxi, endIdxi, inci].
        //- You have an array arr of length length with (all zeros), and you have (some operation) to apply on arr.
        //- In the ith operation,
        //  + you should increment all the elements arr[startIdxi], arr[startIdxi + 1], ..., arr[endIdxi] by inci.
        //* Return arr after (applying all the updates).
        //
        //** Idea
        //1.
        //1.0, Segment tree
        //- Method-1:
        //+ Constraints:
        //1 <= length <= 10^5
        //0 <= updates.length <= 10^4
        //0 <= startIdxi <= endIdxi < length
        //-1000 <= inci <= 1000
        //  + Time: O(n*k)
        //
        //* Brainstorm:
        //- lazy[index]+=inc;
        //  + Cần cộng lên nếu để = inc ==> Sẽ sai
        //
        //1.1, Cases
        //1.2, Optimization
        //1.3, Complexity
        //- Space: O(4*n)
        //- Time: O(n*log(n))
        //
        int length = 5;
        int[][] updates = {{1,3,2},{2,4,3},{0,2,-2}};
        //
        //1.1, Special cases
        //
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        int[] rs= getModifiedArray(length, updates);
        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s, ", rs[i]);
        }
        System.out.println();
    }
}
