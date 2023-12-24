package E1_backtrack;

import java.util.*;

public class E2_StrobogrammaticNumberII {
    public static int[] mapVal;
    public static HashSet<String> rsSet;

    public static String getSymmetryString(int[] mapVal, String str){
        int n=str.length();
        StringBuilder rs=new StringBuilder();

        for(int i=n-1;i>=0;i--){
            rs.append(mapVal[str.charAt(i)-'0']);
        }
        return rs.toString();
    }

    public static void solution(String str, List<Integer> listArr, int length, int n){
        if(length==0){
            String symmetryStr=getSymmetryString(mapVal, str);
            if(n%2==1){
                rsSet.add(str+"0"+symmetryStr);
                rsSet.add(str+"1"+symmetryStr);
                rsSet.add(str+"8"+symmetryStr);
            }
            else rsSet.add(str+symmetryStr);
            return;
        }
        for(Integer val: listArr){
            if(val==0&&length==n/2){
                continue;
            }
            solution(str+val, listArr, length-1, n);
        }
    }

    public static List<String> findStrobogrammatic(int n) {
        mapVal=new int[10];
        rsSet=new HashSet<>();
        mapVal[0]=0;
        mapVal[1]=1;
        mapVal[8]=8;
        mapVal[9]=6;
        mapVal[6]=9;
        List<Integer> listArr=new ArrayList<>();
        listArr.add(0);
        listArr.add(1);
        listArr.add(8);
        listArr.add(6);
        listArr.add(9);
        solution("", listArr, n/2, n);
        return new ArrayList<>(rsSet);
    }

    public static int[][] pairs={{1,1},{0,0},{8,8},{6,9},{9,6}};

    public static List<String> generateStroboNumbers(int n, int finalLength){
        if(n==0){
            List<String> list=new ArrayList<>();
            list.add("");
            return list;
        }
        if(n==1){
            List<String> list=new ArrayList<>();
            list.add("0");
            list.add("1");
            list.add("8");
            return list;
        }
        //Time : O(5^(n/2))
        List<String> prevNums=generateStroboNumbers(n-2, finalLength);
//        System.out.println(prevNums);
        List<String> curNums=new ArrayList<>();

        for(String curStr: prevNums){
            //Time : O(5)
            for(int[] p: pairs){
                if(p[0]!=0||n!=finalLength){
                    curNums.add(p[0]+curStr+p[1]);
                }
            }
        }
        return curNums;
    }

    public static List<String> findStrobogrammaticOptimization(int n) {

        return generateStroboNumbers(n, n);
    }

    public static List<String> findStrobogrammaticLevelOrderTraversal(int n) {
        Queue<String> queue=new LinkedList<>();
        int initLen;

        if(n%2==0){
            initLen=0;
            queue.add("");
        }else{
            initLen=1;
            queue.add("0");
            queue.add("1");
            queue.add("8");
        }

        while(initLen<n){
            initLen+=2;
            for(int i=queue.size()-1;i>=0;i--){
                String curNum=queue.poll();

                for(int[] p: pairs){
                    if(initLen!=n||p[0]!=0){
                        queue.add(p[0]+curNum+p[1]);
                    }
                }
            }
        }
        return new ArrayList<>(queue);
    }

    public static void main(String[] args) {
        // Đề bài:
        //- (A strobogrammatic number) is a number that looks the same (when rotated 180 degrees) (looked at upside down).
        //
        // Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //1 <= n <= 14
        //
        //- Brainstorm
        //Ex:
        //Input: n = 2
        //Output: ["11","69","88","96"]
        //- Các số xuay 180 mà valid:
        //  + 1,6,8,9,0 ==> 5^14 = 6103515625 (Khá lớn)
        //- Loop all 1,6,8,9,0: Để có thể gen ra number hợp lý
        //- Đơn giản là loop all số có thể list ra các số + add vào set là được.
        //  + Cách này với n=14 : Số loop quá lớn
        //Ex:
        //n=4
        //1111
        //8888
        //0000
        //6969
        //Ex:
        //n=6
        //8(6969)8
        //1691
        //- n=14
        //==> Ta chỉ cần tìm n=7 => Là ta có thể map ra đối xứng còn lại
        //
        //1.1, Optimization
        //- Ở đây ta sẽ add trước ==> Sau đó mới từ SET sinh ra các string
        //
        //1.2, Complexity
        //- Space: O(5^(n/2))
        //- Time : O(5^(n/2))
        //
        //2. Dùng quy luật gen string theo length (TOP-DOWN)
        //2.1,
        //- Các cases:
        //- n=1 : 0, 1, and 8.
        //- n=2 : 11, 69, 88, and 96.
        //- n=3 :
        //=> '0' + (1-digit strobogrammatic numbers) + '0'
        //'0' + '0' + '0' = '000'  (1-digit number) (not valid)
        //'0' + '1' + '0' = '010' (2-digit number) (not valid)
        //'0' + '8' + '0' = '080' (2-digit number) (not valid)
        //
        //=> '1' + (1-digit strobogrammatic numbers) + '1'
        //'1' + '0' + '1' = '101'
        //'1' + '1' + '1' = '111'
        //'1' + '8' + '1' = '181'
        //
        //=> '6' + (1-digit strobogrammatic numbers) + '9'
        //'6' + '0' + '9' = '609'
        //'6' + '1' + '9' = '619'
        //'6' + '8' + '9' = '689'
        //
        //=> '8' + (1-digit strobogrammatic numbers) + '8'
        //'8' + '0' + '8' = '808'
        //'8' + '1' + '8' = '818'
        //'8' + '8' + '8' = '888'
        //
        //=> '9' + (1-digit strobogrammatic numbers) + '6'
        //'9' + '0' + '6' = '906'
        //'9' + '1' + '6' = '916'
        //'9' + '8' + '6' = '986'
        //
        //+ n=4
        //=> '1' + (2-digit strobogrammatic numbers) + '1'
        //'1' + '00' + '1' = '1001'
        //'1' + '11' + '1' = '1111'
        //'1' + '69' + '1' = '1691'
        //'1' + '88' + '1' = '1881'
        //'1' + '96' + '1' = '1961'
        //
        //=> '6' + (2-digit strobogrammatic numbers) + '9'
        //'6' + '00' + '9' = '6009'
        //'6' + '11' + '9' = '6119'
        //'6' + '69' + '9' = '6699'
        //'6' + '88' + '9' = '6889'
        //'6' + '96' + '9' = '6969'
        //
        //=> '8' + (2-digit strobogrammatic numbers) + '8'
        //'8' + '00' + '8' = '8008'
        //'8' + '11' + '8' = '8118'
        //'8' + '69' + '8' = '8698'
        //'8' + '88' + '8' = '8888'
        //'8' + '96' + '8' = '8968'
        //
        //=> '9' + (2-digit strobogrammatic numbers) + '6'
        //'9' + '00' + '6' = '9006'
        //'9' + '11' + '6' = '9116'
        //'9' + '69' + '6' = '9696'
        //'9' + '88' + '6' = '9886'
        //'9' + '96' + '6' = '9966'
        //- Fomulas
        //generateStroboNumbers(N) = List("digit1" + "number" + "digit2"
        //                                for each number in generateStroboNumbers(N - 2)
        //                                for each (digit1, digit2) in reversiblePairs
        //                      )
        //- Tức là (current string) được tính theo (prev string)
        //* NOTE:
        //- Nếu n==0 : return list("") ==> 1 phần tử "" nếu empty thì với n%2==0 ==> SAI.
        //  + "" ==> Vẫn append được (first và last)
        //
        //2.1, Optimization
        //
        //2.2, Complexity
        //- Space : O(N*(5^(N/2-1)))
        // + N/2 recursive calls -> Call stack uses O(N/2) space
        // + String trong từng recursion turn sẽ được lưu vào stack:
        //  + Vào last recursion ta không call 1 method recursion + output array ==> Nó không phải auxilary space (Chỗ này không hiểu lắm?)
        //Ex:
        //- n%2=0
        // func(n) -> func(n-2) -> ... -> func(2)(size=5) -> func(0) (size=1)
        //  + Ex: n=6 : 6 -> 4 -> 2 -> 0
        //  + Space=1 + 5 + 5^2 + 4*5^(3-1)[5^(N/2-1)] ~ 5^(N/2)
        //- n%2=1
        // func(n) -> func(n-2) -> ... -> func(3)(size=5*3) -> func(1) (size=3) [ Tương tự ]
        //  + Ta lưu : 5^(N/2-1)
        //- Mỗi string có length=n
        //--> Space : O(N*(5^(N/2-1)))
        //
        //- Time : N*5^(N/2+1)
        //+ Ta có N, N-2, N-4,...,0 : N/2 levels
        //+ Mỗi recursion turn ta sẽ có :
        // + n=1 : 5*1 string
        // + n=2 : 5*5 strings ==> Loop 1 turn
        // ...
        // + n=m : 5^m strings ==> Loop (m-1) turn
        // + n=n/2-1 : 5^(n/2-1) strings ==> loop
        //Total time = 5 + 5^2+ 5^3 + 4(if)* 5^(N/2-1) ~ 5^(N/2)
        //* NOTE:
        //- Appending of a character at the end of string takes : O(1) time ==> (SAI) (với CONCAT STRING) + (ĐÚNG) (với StringBuilder)
        //- Appending of a character at the beginning of string takes : O(N) time
        //==> Ta thêm O(N)
        //-> Time = N*5^(N/2+1)
        //
        //3. Level order traversal - (BOTTOM-UP)
        //3.1, Idea
        //- Ở đây mình dùng queue đi từ length=1 ==> length=n
        //
        //3.2, Complexity
        //- Space : O(N*(5^(N/2-1)))
        //- Time : N*5^(N/2+1)
        //
        //#Reference:
        //246. Strobogrammatic Number
        //248. Strobogrammatic Number III
        //2081. Sum of k-Mirror Numbers
        int n=4;
//        int n=14;
//        System.out.println(findStrobogrammatic(n));
        System.out.println(findStrobogrammaticOptimization(n));
        System.out.println(findStrobogrammaticLevelOrderTraversal(n));
    }
}
