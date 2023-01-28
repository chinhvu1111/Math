package interviews.bytedance;

import javafx.util.Pair;

import java.util.*;

public class E26_EvaluateDivision {

    public static Pair<String, Double> findParent(
            String a,
            HashMap<String, String> parent,
            HashMap<String, Double> mapVals){
        String s=a;
        Double newValue=1d;
        while (parent.containsKey(s)&&!parent.get(s).equals(s)){
            newValue*=mapVals.get(s+","+parent.get(s));
            s=parent.get(s);
        }
        return new Pair<>(s, newValue);
    }

    public static void addRelationShip(String a, String b, double val,
                                       HashMap<String, String> parent,
                                       HashMap<String, Double> mapVals){
        //Ta sẽ quy định 1 chiều a --> p_a ==> (a, p_a)=c
        //a / p_a = c
        Pair<String, Double> leftParent=findParent(a, parent, mapVals);
        //Ta cần tính lại theo parent value như sau:
        //- a/ parent_a (a) = 1
        //- b/ parent_b (a) = 3
        //- c/ parent_c(b) = 2
//        mapVals.put(a+leftParent.getKey(), leftParent.getValue());
//        mapVals.put(leftParent.getKey()+a, 1/leftParent.getValue());
        //b / p_b = c
        Pair<String, Double> rightParent=findParent(b, parent, mapVals);

        if(parent.containsKey(leftParent.getKey())){
            if(!parent.containsKey(rightParent.getKey())
                    ||parent.get(rightParent.getKey()).equals(rightParent.getKey())
                    &&!leftParent.getKey().equals(parent.get(rightParent.getKey()))){
                parent.put(rightParent.getKey(), leftParent.getKey());
                mapVals.put(rightParent.getKey()+","+leftParent.getKey(), leftParent.getValue()/rightParent.getValue()/val);
                mapVals.put(leftParent.getKey()+","+rightParent.getKey(), rightParent.getValue()/leftParent.getValue()*val);
            }
            mapVals.put(b+","+leftParent.getKey(), 1/val*leftParent.getValue());
            mapVals.put(leftParent.getKey()+","+b, 1/(1/val*leftParent.getValue()));
            mapVals.put(a+","+leftParent.getKey(), leftParent.getValue());
            mapVals.put(leftParent.getKey()+","+a, 1/leftParent.getValue());
        }else{
            if(!parent.containsKey(leftParent.getKey())
                    ||parent.get(leftParent.getKey()).equals(leftParent.getKey())
                    &&!rightParent.getKey().equals(parent.get(leftParent.getKey()))){
                parent.put(leftParent.getKey(), rightParent.getKey());
                mapVals.put(leftParent.getKey()+","+rightParent.getKey(), rightParent.getValue()/leftParent.getValue()*val);
                mapVals.put(rightParent.getKey()+","+leftParent.getKey(), leftParent.getValue()/rightParent.getValue()/val);
            }
            mapVals.put(a+","+rightParent.getKey(), val*rightParent.getValue());
            mapVals.put(rightParent.getKey()+","+a, 1/(val*rightParent.getValue()));
            mapVals.put(b+","+rightParent.getKey(), rightParent.getValue());
            mapVals.put(rightParent.getKey()+","+b, 1/rightParent.getValue());
        }
        if(!parent.containsKey(leftParent.getKey())){
            parent.put(leftParent.getKey(), leftParent.getKey());
        }
        if(!parent.containsKey(rightParent.getKey())){
            parent.put(rightParent.getKey(), rightParent.getKey());
        }
//        parent.put(b, rightParent.getKey());
    }

    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String, String> parent =new HashMap<>();
        HashMap<String, Double> mapVals=new HashMap<>();

        for(int i=0;i<equations.size();i++){
            String a=equations.get(i).get(0);
            String b=equations.get(i).get(1);
            addRelationShip(a, b, values[i], parent, mapVals);
        }
        int n=queries.size();
        double[] result =new double[n];

        for(int i=0;i<n;i++){
            List<String> currentQuery=queries.get(i);
            String left=currentQuery.get(0);
            String right=currentQuery.get(1);
            Pair<String, Double> leftParent=findParent(left, parent, mapVals);
            Pair<String, Double> rightParent=findParent(right, parent, mapVals);
            Double valLeft=leftParent.getValue();
            Double valRight=rightParent.getValue();
            Double ex=mapVals.get(leftParent.getKey()+","+rightParent.getKey());
            if((!parent.containsKey(leftParent.getKey())&&!parent.containsKey(rightParent.getKey()))
            ||ex==null){
                result[i]=-1;
                continue;
            }
            result[i]=ex*valLeft/valRight;
        }
        return result;
    }

    public static void println(double [] result){
        for(int i=0;i< result.length;i++){
            System.out.print(result[i]+", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //Case 1:
        //String[][] equations = new String[][] {{"a","b"},{"b","c"}};
        //double[] values = new double[]{2.0,3.0};
        //String[][] queries = {{"a","c"},{"b","a"},{"a","e"},{"a","a"},{"x","x"}};

        //Case 2:
        //- Do sai liên quan đến put value (a,a) phải bằng 1
        //String[][] equations = new String[][] {{"a","e"},{"b","e"}};
        //double[] values = new double[]{4.0,3.0};
        //String[][] queries = {{"a","b"},{"e","e"},{"x","x"}};

        //Case 3
        //- Do trùng hash --> put lại bị sai
        //VD: a + aa == aa + a
        //String[][] equations = new String[][] {{"a","aa"}};
        //double[] values = new double[]{9.0};
        //String[][] queries = {{"aa","a"},{"a","aa"}};

        //Case 4:
        //- Sai do set:
        //+ mapVals.put(b+","+rightParent.getKey(), 1);
        //==>
        //+ mapVals.put(b+","+rightParent.getKey(), rightParent.getValue());
        //String[][] equations = new String[][] {{"x1","x2"},{"x2","x3"},{"x3","x4"},{"x4","x5"}};
        //double[] values = new double[]{3.0,4.0,5.0,6.0};
        //String[][] queries = {{"x1","x5"},{"x5","x2"},{"x2","x4"},{"x2","x2"},{"x2","x9"},{"x9","x9"}};
        //
        //Case 5:
        //- Sai do parent của 2 branch cần phải nối value và parent 1 cách chính xác
        //
//        String[][] equations = new String[][] {{"a","b"},{"e","f"},{"b","e"}};
//        double[] values = new double[]{3.4,1.4,2.3};
//        String[][] queries = {{"b","a"},{"a","f"}};

        //Case 6:
        //(b,b)==4 (Sai khi nhân vào sẽ bị * số chồng lên nhau ==> lớn)
        //- 2 a,b không connect được với nhau =-1
//        String[][] equations = new String[][] {{"a","b"},{"c","d"}};
//        double[] values = new double[]{1,1};
//        String[][] queries = {{"a","c"}};

        //Case 7:
        //- Case này sai do việc tính tỉ lệ thông qua điểm trung gian bị sai số
        //VD:
        //W,d
        //parent(w)=x
        //parent(d)=b
        //X/w=0.2 ==> w/x=5
        //B/d=0.25 ==> d/b=4
        //Mà có w/d=8
        //- x/b=0.2/0.25*8
        //+ Với parent(x)=b
        //- b/x = 0.25/0.2/8
        //+ Với parent(b)=x
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
        //** Đề bài
        //** Bài này tư duy như sau:
        //1.1, Ý tưởng ban đầu:
        //
        //- Có thể quy về 1 số để tính chia hết
        //+ Cách này khá khó vì cần phải tính theo 2 chiều
        //VD: a/b=2 ==> Ta sẽ tính b Theo a hay a Theo b
        //Để các phép tính khác có thể sử dụng lại.
        //- Hoặc có thể dùng tính chất *// để triệt tiêu hết số (Tử số/Mẫu số)
        //+ Vướng các case khác khá khó có thể xử lý
        //VD: ab/cd : Thì ab tính Theo cái gì không thể tính trước
        //Thể nên chỉ có cách 1 tư duy ban đầu ==> Quy về 1 thằng Parent
        //+ Dùng union find mục đích quy về 1 đỉnh Parent.
        //
        //
        //VD:
        //(A,b)=2
        //(B,c)
        //(C/a)
        //==> Để tính c/a ta có thể tính theo nhiều cách
        //+ a được tính =2 * b
        //+ b được tính theo c ==> b= 3c ==> Lúc này muốn chọn tính theo đỉnh nào theo đỉnh nào đơn giản là ta check dựa trên Parent.
        //
        //VD:
        //A/b=2
        //B/c=3
        //==> Tính a*c/ b*b
        //Ta cần phải tính như thế nào?
        //
        //A --> B
        //B <-- C
        //
        //VD-2:
        //
        //A/B=2
        //C/D=3
        //Ta cần phải tính phép chia của 1 phân số không liên quan như:
        //A*C/B*D
        //* ==> Ở đây nếu quy về 1 mối thì ta sẽ không thể quy được
        //Do A và C độc lập.
        //1.2, Vì số lượng chữ số không quá nhiều <=5 + Các số sẽ luôn chia hết cho nhau bằng 1 cách nào đó hoặc nếu không chia hết được thì coi như xong.
        //- Ta có thể for brute force cho all cases.
        //- Để có thể chệch điều kiện có thể chia hết cho nhau hay không ==> Ta cần thành lập graph relationship giữa các điểm.
        //
        //1.3,
        //- Nếu dùng Union find thì ta sẽ tối ưu được với phép chia với 2 điểm cùng chung 1 root
        //==> Thay vì scan nguyên tree --> Ta chỉ cần traverse 1 lần để lấy Parent mà thôi.
        //
        //- Với add edge tương ứng với 2 điểm A và B
        //+ Ta sẽ chọn điểm có Parent != chính nó là root
        //==> Điểm còn lại sẽ là phụ.
        //
        //VD:
        //A --> C --> B
        //A/C=2
        //C/B=3
        //==> Ta sẽ biểu diễn như thế nào.
        //+ Ta sẽ biểu diễn quan hễ giữa các điểm dưới dạng hashmap : hash(a)=b (Parent)
        //+ Tỉ lệ biểu diễn bằng hash(AB)=2 : A/B=2
        //
        //- Với các trường hợp equations có nhiều hơn 2 phần tử ở Mẫu và tử ta sẽ xử lý như thế nào?
        //1.4, Chú ý các cases có thể xảy ra bên trên:
        //- Có 7 case cần chú ý:
        //Case 1:
        //String[][] equations = new String[][] {{"a","b"},{"b","c"}};
        //double[] values = new double[]{2.0,3.0};
        //String[][] queries = {{"a","c"},{"b","a"},{"a","e"},{"a","a"},{"x","x"}};

        //Case 2:
        //- Do sai liên quan đến put value (a,a) phải bằng 1
        //String[][] equations = new String[][] {{"a","e"},{"b","e"}};
        //double[] values = new double[]{4.0,3.0};
        //String[][] queries = {{"a","b"},{"e","e"},{"x","x"}};

        //Case 3
        //- Do trùng hash --> put lại bị sai
        //VD: a + aa == aa + a
        //String[][] equations = new String[][] {{"a","aa"}};
        //double[] values = new double[]{9.0};
        //String[][] queries = {{"aa","a"},{"a","aa"}};

        //Case 4:
        //- Sai do set:
        //+ mapVals.put(b+","+rightParent.getKey(), 1);
        //==>
        //+ mapVals.put(b+","+rightParent.getKey(), rightParent.getValue());
        //String[][] equations = new String[][] {{"x1","x2"},{"x2","x3"},{"x3","x4"},{"x4","x5"}};
        //double[] values = new double[]{3.0,4.0,5.0,6.0};
        //String[][] queries = {{"x1","x5"},{"x5","x2"},{"x2","x4"},{"x2","x2"},{"x2","x9"},{"x9","x9"}};
        //
        //Case 5:
        //- Sai do parent của 2 branch cần phải nối value và parent 1 cách chính xác
        //
//        String[][] equations = new String[][] {{"a","b"},{"e","f"},{"b","e"}};
//        double[] values = new double[]{3.4,1.4,2.3};
//        String[][] queries = {{"b","a"},{"a","f"}};

        //Case 6:
        //(b,b)==4 (Sai khi nhân vào sẽ bị * số chồng lên nhau ==> lớn)
        //- 2 a,b không connect được với nhau =-1
//        String[][] equations = new String[][] {{"a","b"},{"c","d"}};
//        double[] values = new double[]{1,1};
//        String[][] queries = {{"a","c"}};

        //Case 7:
        //- Case này sai do việc tính tỉ lệ thông qua điểm trung gian bị sai số
        //VD:
        //W,d
        //parent(w)=x
        //parent(d)=b
        //X/w=0.2 ==> w/x=5
        //B/d=0.25 ==> d/b=4
        //Mà có w/d=8
        //- x/b=0.2/0.25*8
        //+ Với parent(x)=b
        //- b/x = 0.25/0.2/8
        //+ Với parent(b)=x
        //#Reference:
        //400. Nth Digit
        //2307. Check for Contradictions in Equations
    }
}
