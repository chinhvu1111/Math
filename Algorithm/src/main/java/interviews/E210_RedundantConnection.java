package interviews;

public class E210_RedundantConnection {

    public static int findParent(int[] parent, int u){
        while (parent[u]!=u){
            u=parent[u];
        }
        return u;
    }

    public static boolean canBeUnion(int[] parent, int[] height, int u, int v){
        int parentU=findParent(parent,u);
        parent[u]=parentU;
        int parentV=findParent(parent,v);
        parent[v]=parentV;

        if(parentU==parentV){
            return true;
        }
        if(height[parentU]>=height[parentV]){
            height[parentU]=height[parentV]+1;
            parent[parentV]=parentU;
        }else{
            height[parentV]=height[parentU]+1;
            parent[parentU]=parentV;
        }
        return false;
    }

    public static int[] findRedundantConnection(int[][] edges) {
        int n=edges.length;
        //Vì graph có cycle ban đầu nên số điểm = số edges
        int[] parent=new int[n+1];
        int[] height=new int[n+1];

        for(int i=0;i<n;i++){
            parent[i]=i;
            height[i]=1;
        }

        int[] result=new int[2];

        for(int i=0;i<n;i++){
            int u=edges[i][0];
            int v=edges[i][1];

            if(canBeUnion(parent, height, u, v)){
                result[0]=u;
                result[1]=v;
            }
        }
        return result;
    }

    public static void prinln(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
//        int[][] arr=new int[][]{{1,2},{1,3},{2,3}};
        int[][] arr=new int[][]{{1,2},{2,3},{3,4},{1,4},{1,5}};
        //Beat : 92.36 --> 100%
        int[] result=findRedundantConnection(arr);
        //Time complexity:
        //- O(log(log(N))

        prinln(result);
    }
}
