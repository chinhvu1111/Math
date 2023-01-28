package interviews.bytedance;

import java.util.*;

public class E26_EvaluateDivision_refer {

    public static class Edge{
        String v;
        double value;

        public Edge(String v, double value) {
            this.v = v;
            this.value = value;
        }
    }

    public static void addEdge(HashMap<String, List<Edge>> graph, String a, String b, double value){
        List<Edge> values=graph.get(a);
        if(values==null){
            values=new ArrayList<>();
        }
        values.add(new Edge(b, value));
        graph.put(a, values);
    }

    public static double dfs(HashMap<String, List<Edge>> graph, Set<String> setEdges, String u, String v){
        if(!graph.containsKey(u)||!graph.containsKey(v)){
            return -1;
        }else if(u.equals(v)){
            return 1;
        }
        for(Edge edge: graph.get(u)){
            if(setEdges.contains(edge.v)){
                continue;
            }else if(edge.v.equals(v)){
                return edge.value;
            }
            setEdges.add(edge.v);
            double val=dfs(graph, setEdges, edge.v, v);
            if(val!=-1){
//                System.out.println(val*edge.value);
                return val * edge.value;
            }
        }
        return -1;
    }

    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String, List<Edge>> graph=new HashMap<>();

        for(int i=0;i<equations.size();i++){
            String a=equations.get(i).get(0);
            String b=equations.get(i).get(1);
            double value=values[i];
            addEdge(graph, a, b, value);
            addEdge(graph, b, a, 1/value);
        }
        double[] rs=new double[queries.size()];

        for(int i=0;i<queries.size();i++){
            rs[i]=dfs(graph, new HashSet<>(), queries.get(i).get(0), queries.get(i).get(1));
        }

        return rs;
    }

    public static void println(double [] result){
        for(int i=0;i< result.length;i++){
            System.out.print(result[i]+", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String[][] equations = new String[][] {{"a","b"},{"c","b"},{"d","b"},{"w","x"},{"y","x"},{"z","x"},{"w","d"}};
        double[] values = new double[]{2.0,3.0,4.0,5.0,6.0,7.0,8.0};
//        String[][] queries = {{"a","c"},{"b","c"},{"a","e"},{"a","a"},{"x","x"},{"a","z"}};
        String[][] queries = {{"a","z"}};
        int n=equations.length;
        List<List<String>> equationList=new ArrayList<>();
        List<List<String>> queriesList=new ArrayList<>();

        for(int i=0;i<n;i++){
            List<String> currentList = new ArrayList<>(Arrays.asList(equations[i]));
            equationList.add(currentList);
        }
        for(int i=0;i<queries.length;i++){
            List<String> currentQueriesList = new ArrayList<>(Arrays.asList(queries[i]));
            queriesList.add(currentQueriesList);
        }
        double[] result=calcEquation(equationList, values, queriesList);
        println(result);
        //** Bài này tư duy như sau:
        //1.
        //1.1,
        //- Dựng graph dạng Hash<String, List<Edge>> : danh sách cạnh (edges) tương ứng với điểm cho trước.
        //+ Ta sẽ add edge 2 chiều (value và 1/value)
        //- Sau khi add edge ta sẽ sau đó sẽ tìm (u, v) trên graph đó.
        //+ Chú ý các edge có thể có circle nên cần phải add vertex vào SET để có thể traverse đến được điểm cho trước.
        //1.2, Complexity:
        //- Time complexity: O(k*N)
        //+ k : Là số queries
        //+ N : số lượng vertex nhiều nhất có thể đi qua mỗi lần.
        //- Space complexity : O(n*n) + n = O(n^2)
        //#Reference:
        //400. Nth Digit
        //2307. Check for Contradictions in Equations
    }
}
