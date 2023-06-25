package contest;

import java.util.ArrayList;
import java.util.List;

public class E28_ConstructTheLongestNewString {
    public static int rs;
    public static void solution(int x, int y, int z, int last, int currentLength){
        rs=Math.max(rs, currentLength);
        if(x==0&&y==0&&z==0){
            return;
        }
        if((last!=0&&x>0)||last==-1){
            solution(x-1, y, z, 0, currentLength+2);
        }
        if((last==0&&y>0)||last==-1){
            solution(x, y-1, z, 1, currentLength+2);
        }
        if((last!=0&&z>0)||last==-1){
            solution(x, y, z-1, 2, currentLength+2);
        }
    }
    public static void solutionRefactor(int x, int y, int z, int last, int currentLength, int max){
        rs=Math.max(rs, currentLength);
        if(rs==max){
            return;
        }
//        if(x==0&&y==0&&z==0){
//            return;
//        }
        if((last==0)||last==-1){
            if(y>0){
                solutionRefactor(x, y-1, z, 1, currentLength+2, max);
            }
        }
        //last==1 or last==2
        if(last != 0){
            if(x>0){
                solutionRefactor(x-1, y, z, 0, currentLength+2, max);
            }
            if(z>0){
                solutionRefactor(x, y, z-1, 2, currentLength+2, max);
            }
        }
//        if((last==2)||last==-1){
//            if(x>0){
//                solutionRefactor(x-1, y,z, 2, currentLength+2, max);
//            }
//            if(z>0){
//                solutionRefactor(x, y,z-1, 2, currentLength+2, max);
//            }
//        }
    }

    public static void solutionDynamicProgramming(int x, int y, int z){
        int n=x+y+z;
        int[][] dp=new int[n+1][3];
        for(int i=0;i<=n;i++){
            dp[i][0]=-1;
            dp[i][1]=-1;
            dp[i][2]=-1;
        }
        dp[0][0]=0;
        dp[0][1]=0;
        dp[0][2]=0;
        rs=Integer.MIN_VALUE;
        int x1=0, y1=0, z1=0;

        while (x1<x||y1<y||z1<z){
            if(dp[x1][1]!=-1||dp[x1][2]!=-1){
                if(x1<x){
                    dp[x1+1][0]= Math.max(dp[x1][1], dp[x1][2])+2;
                    rs=Math.max(rs, dp[x1+1][0]);
                    x1++;
                }
            }
            if(z1<z&&(dp[z1][1]!=-1||dp[z1][2]!=-1)){
                dp[z1+1][2]= Math.max(dp[z1][1], dp[z1][2])+2;
                rs=Math.max(rs, dp[z1+1][2]);
                z1++;
            }
            if(dp[y1][0]!=-1){
                if(y1<y){
                    dp[y1+1][1]= dp[y1][0] + 2;
                    rs=Math.max(rs, dp[y1+1][0]);
                    y1++;
                }
            }
        }
    }

    public static int longestStringRecursion(int x, int y, int z) {
        rs=Integer.MIN_VALUE;
        if(x==0&&y==0&&z==0){
            return 0;
        }
//        List<Integer>[] mapCases=new ArrayList[3];
//        mapCases[0]=new ArrayList<>();
//        mapCases[0].add(1);
//        mapCases[1]=new ArrayList<>();
//        mapCases[1].add(0);
//        mapCases[1].add(2);
//        mapCases[2]=new ArrayList<>();
//        mapCases[2].add(0);
//        mapCases[2].add(2);
        int max=x*2+y*2+z*2;
        solutionRefactor(x,y,z, -1, 0, max);
//        solutionDynamicProgramming(x,y,z);
//        System.out.println(rs);
        return rs;
    }

    public static int longestString(int x, int y, int z) {
        if(x==0&&y==0&&z==0){
            return 0;
        }
        if(x==y){
            return (x+y+z)*2;
        }
        if(y>x){
            return (z + x * 2 + 1)*2;
        }
        return (z + y * 2 + 1)*2;
        //        solutionDynamicProgramming(x,y,z);
//        System.out.println(rs);
    }

    public static void main(String[] args) {
        //** Requirement:
        //- x string AA
        //- y string BB
        //- z string AB
        //--> Tìm số độ dài string tối đa để nối các string với nhau thành new string (s)
        //- Sao cho s không được bao gồm 'AAA' hoặc 'BBB' như 1 substring
        //
        //** Idea
        //1.
        //1.1,
        //- Dữ kiện:
        //Constraints:
        //1 <= x, y, z <= 50
        //--> Khá lớn ==> Nếu dùng đệ quy để làm thì khá khó
        //- Tìm max length string
        //* Brainstorm
        //- AA, BB, AB
        //Không được bao gồm AAA, BBB
        //+ (AAAB) : không được chứa
        //+ (BBBB) : không được chứa
        //+ (ABBB) : không được chứa
        //
        //- 1 string có thể append vào:
        //+ s + old string
        //+ old + s
        //- Nếu dùng recursion method
        //- Add last là:
        //+ AA --> Không được add AA, AB
        //+ BB --> Không được add BB
        //+ AB --> Không được add BB
        //- Add first là:
        //+ AA --> Không được add AA
        //+ BB --> Không được add BB
        //+ AB --> Không được add AA
        //===> Nếu làm như thế này thì ta có thể dần dần được
        //
        //- Cut branch:
        //1.1, Implementation
        //- Lưu phần tử đã add vào cuối (1,2,3)
        // (i) --> sẽ có danh sách riêng
        // <>
        //+ AA : 0 --> 1(BB)
        //+ BB : 1 --> 0(AA),2(AB)
        //+ AB : 2 --> 0(AA),2(AB)
        //--> Tức là
        //VD: (last==0) --> được chọn 1
        //---> Với tư duy bên trên nếu dùng recursive --> ta sẽ bị timeout do độ sâu của method <=50 có thể đến 2^50
        //
        //- Với tư duy này --> Ta cần dùng dynamic programming
        //dp[i][0] với i là length của str khi chọn (0)(AA) là word cuối cùng.
        //+ dp[i][0]= max(dp[i-1][1], dp[i-1][2])+2
        //+ dp[i][2]= max(dp[i-1][1], dp[i-1][2])+2
        //+ dp[i][1]= dp[i-1][0] + 2
        //Ex: x=1, y=1, z=1
        //- Steps:
        //+ dp[1][0]=2, dp[1][1]=2, dp[1][2]=2
        //+ dp[2][0]=4, dp[1][1]=4, dp[1][2]=4
        //---> Tư duy này (WRONG)(sai) do ta phải biết rằng ta chọn các last word có thể phụ thuộc lên nhau.
        //
        //* Có các kiểu tư duy như sau:
        //- Tập trung vào AAA và BBB --> Để ta chia cases sau đó suy luận.
        //- Vẫn 1 phần sử dụng tư duy trên --> Nhưng bài này cần hơn thế:
        //+ Ta có AA, BB, AB:
        //+ Thay vì chỉ tư duy về suffix AAA,BBB ta sẽ tư duy về các (CÁC CÁCH KẾT HỢP ĐỂ CHO STRING MAX LENGTH (Optimization))
        //VD:
        // Với AA, BB, AB thì ta có thể tư duy như bên trên:
        //+ AA : 0 --> 1(BB)
        //+ BB : 1 --> 0(AA),2(AB)
        //+ AB : 2 --> 0(AA),2(AB)
        //Nhưng giá trị MAX length ta có thể chọn được liệu có thể như nhau + ta sẽ chọn 1 cases trong rất nhiều cases có được không?
        //VD:
        //x=2,y=2,z=2
        //+ AABBAABB(AB)(AB)
        //==> Ta thấy răng AA và BB là đối xứng với nhau
        //AABBAABB(AB)(AB) --> nếu ta chuyển thành BB trước
        //BBAABBAA(AB) ==> Sẽ bị dính AAA liên tiếp
        // ==> nên ta sẽ chọn AA trước ==> Chọn làm sao để (BB sẽ đứng cuối)(last)
        //- Số (BB)> số (AA)
        //VD: (BBAABBAABB)AB ==> nếu thừa BB thì bỏ đi
        //--> value = count(AA)+count(AA+1) + count(AB)
        //
        //- Số (AA)> số (BB)
        //+ AA(BB)AA --> (BB) mà thay bằng AB thì cũng thế thôi ==> length=6
        //+ BBAA(AA/AB) --> (BB) mà thay bằng AB thì cũng thế thôi ==> length=4 --> Ngắn hơn
        //+ ABABAB...AA(BB)AA(BB)AA... --> count(AB) + min(count(BB)+ count(BB)+1) ==> Ta sẽ chọn cái này
        //--> length=
        //
        //VD:
        //x=2,y=5,z=1
        //(BB)AABBAA(BB)AB
        //
        //- Số (AA) = số (BB)
        //- AABBAABBAB = sum all
//        System.out.println(longestString(2,5,1));
//        System.out.println(longestString(3,2,2));
        System.out.println(longestStringRecursion(1,1,1));
        System.out.println(longestString(1,1,1));
//        System.out.println(longestString(5,32,50));
        //#Reference:
        //1. Two Sum
        //1488. Avoid Flood in The City
        //2089. Find Target Indices After Sorting Array
        //1885. Count Pairs in Two Arrays
    }
}
