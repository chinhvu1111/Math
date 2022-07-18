package interviews;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class E77_CanMakePalindromeFromSubstring {

    public static List<Boolean> canMakePaliQueriesWrong(String s, int[][] queries) {
        List<Boolean> rs=new ArrayList<>();
        int characters[]=new int[27];
        int n=s.length();
        int prefixOdd[]=new int[n];
        int numberOdd=0;

        for(int i=0;i<n;i++){

            if(characters[s.charAt(i)-'a']%2==0){
                //Số trước đó là even
                numberOdd++;
            }else{
                //Số trước đó là odd
                numberOdd--;
            }
            prefixOdd[i]=numberOdd;
            characters[s.charAt(i)-'a']++;
        }
        for(int i=0;i<queries.length;i++){

        }
        return rs;
    }

    public static List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        List<Boolean> rs=new ArrayList<>();
        int n=s.length();
        int frequencyChars[][]=new int[n][27];

        frequencyChars[0][s.charAt(0)-'a']=1;
        for(int i=1;i<n;i++){
            for(int j=0;j<=26;j++){
                frequencyChars[i][j]=frequencyChars[i-1][j];
            }
            frequencyChars[i][s.charAt(i)-'a']++;
        }
        for(int i=0;i<queries.length;i++){
            int left=queries[i][0];
            int right=queries[i][1];
            int replacement=queries[i][2];
            int numberOdds=0;

            for(int j=0;j<27;j++){
                int currentNumber=0;

                if(left-1>=0){
                    currentNumber=frequencyChars[right][j]-frequencyChars[left-1][j];
                }else{
                    currentNumber=frequencyChars[right][j];
                }

                if(currentNumber%2==1){
                    numberOdds++;
                }
            }
            if((right-left+1)%2==0){
                if(replacement>=numberOdds/2){
                    rs.add(true);
                }else{
                    rs.add(false);
                }
            }else{
                if(replacement>=(numberOdds-1)/2){
                    rs.add(true);
                }else{
                    rs.add(false);
                }
            }
        }
        return rs;
    }

    public static List<Boolean> canMakePaliQueriesOptimize(String s, int[][] queries){
        List<Boolean> rs=new ArrayList<>(queries.length);
        int n=s.length();
        int frequencyChars[][]=new int[n][27];

        frequencyChars[0][s.charAt(0)-'a']=1;
        for(int i=1;i<n;i++){
            for(int j=0;j<=26;j++){
                frequencyChars[i][j]=frequencyChars[i-1][j];
            }
            frequencyChars[i][s.charAt(i)-'a']++;
        }
        for(int i=0;i<queries.length;i++){
            int left=queries[i][0];
            int right=queries[i][1];

            if(right==left){
                rs.add(true);
                continue;
            }

            int replacement=queries[i][2];
            int numberOdds=0;

            for(int j=0;j<27;j++){
                int currentNumber=0;

                if(frequencyChars[right][j]==0){
                    continue;
                }
                if(left-1>=0){
                    currentNumber=frequencyChars[right][j]-frequencyChars[left-1][j];
                }else{
                    currentNumber=frequencyChars[right][j];
                }

                if(currentNumber%2==1){
                    numberOdds++;
                }
            }
            if(replacement>=numberOdds/2){
                rs.add(true);
            }else{
                rs.add(false);
            }
        }
        return rs;
    }

    public static List<Boolean> canMakePaliQueriesMethod2(String s, int[][] queries){
        List<Boolean> rs=new ArrayList<>(queries.length);
        int n=s.length();
        int frequencyChars[]=new int[n];

        frequencyChars[s.charAt(0)-'a']= (1 << s.charAt(0) - 'a');
        int prev=1;

        for(int i=1;i<n;i++){
            prev=frequencyChars[i-1];
            int indexX=s.charAt(i)-'a';
            frequencyChars[i]=prev^(1<<indexX);
        }
        for(int i=0;i<queries.length;i++){
            int left=queries[i][0];
            int right=queries[i][1];
            int replacement=queries[i][2];
            int numberOdds=0;

            if(left>0){
                numberOdds=frequencyChars[right]^frequencyChars[left-1];
            }else{
                numberOdds=frequencyChars[right];
            }
            Integer numberBits=Integer.bitCount(numberOdds);

            if(numberBits/2<=replacement){
                rs.add(true);
            }else{
                rs.add(false);
            }
        }
        return rs;
    }

    public static void main(String[] args) {
//        String s="abcda";
//        int [][]queries=new int[][]{{3,3,0},{1,2,0},{0,3,1},{0,3,2},{0,4,1}};
        String s="lyb";
        int [][]queries=new int[][]{{0,1,0},{2,2,1}};
        //Để bài:
        //+ Mỗi chuổi có thể rearrange + replace ki số trong đó.
        canMakePaliQueries(s, queries);
        canMakePaliQueriesOptimize(s, queries);
        //Bài này tư duy như sau:
        //Cách 1:
        //Để 1 số rearrange lại thành số Palindrome thì cần:
        //- Số lượng số odd:
        //+ Có 1 chữ số có số chữ số odd
        //+ Các chữ số còn lại even
        //- Số lượng chữ số even:
        //+ Tất cả các số đều có số lượng chữ số even.
        //CT:
        //Số lượng chữ số lẻ trong (i,j) là k
        //j-i+1 : Là số even --> Cần bù ít nhất k/2 ký tự để thành chẵn.
        //j-i+1 : Là số odd --> Vẫn cần bù ít nhất (k-1)/2 ký tự.
        //--> Ở đây ta tư duy sao để:
        //Mỗi lần queries ta sẽ tính ra được ngay số chữ số lẻ nằm trong khoảng (i,j) bất kỳ:
        //VD : Sau khi thử trên abdbd
        //+ (abd): số ký tự có count lẻ =3, replace = 1.
        //+ (abdcb) : số ký tự có count lẻ =2, replace = 0.
        //--> Ta không thể prefix cho số ký tự có số lượng là số lẻ / số ký tự cần replace.
        //** SOLUTION:
        //- Ta sẽ lưu tất cả số lượng của all ký tự tại (i) ==> đến lúc queries ta sẽ tính lại.
        //- Số lượng ký tự <=27 --> Độ phức tạp lúc này sẽ không quá lớn.
        //
        //Cách 2:
        //1, Vẫn key idea là lưu count của từng ký tự tại vị trí (i) --> Để có thể kết hợp với vị trí bất kỳ (j<i)
        //--> Tính ra số lượng các chữ số lẻ giữa (i và j)
        //2, Ở đây ta sẽ dùng phép dịch bits để tính:
        //--> Vấn đề ở đây ta chỉ quan tâm đến số chữ số (odd) --> Tương ứng với bit 1
        //- Mỗi vị trí (i) ta sẽ lưu 27 bits chỉ ở vị trí (i) có bao nhiều số hiện tại có count lẻ <=> bit=1
        //2.1, Muốn xác định range, số lượng count giữa (i,j)
        //int value=frequen[right]^frequen[left]
        //--> value chính là bits thể hiện cho số lượng số lẻ giữa (i,j)
        //2.2, Ở đây ta sẽ dùng phép xor để thể hiện cho tính chất này:
        //VD: lẻ ^ 1 =chẵn <=> 1^1 --> 0
        // chẳn ^1 =lẻ <=> 0&1 =1
        //2.3, Mỗi lần dịch bit
        //+ value[i]=value[i-1]^(1<<s[i]-'a')
        //==> Vì cần dịch 1 trong 26 bits đã có.
        //** EXP :
        //- Với những bài dạng lẻ / chẵn ta hoàn toàn có thể dùng dịch bit --> Lưu lại danh sách số (lẻ /chẵn)
        //==> Tại vị trí (i)
        canMakePaliQueriesMethod2(s, queries);
        System.out.println();
        System.out.println(1<<2);
        System.out.println(0^0);
        System.out.println(0^1);
        System.out.println(1<<0);
        System.out.println(1^(1<<0));
    }
}
