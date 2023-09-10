package E1_slide_window;

import java.util.Arrays;
import java.util.Comparator;

public class E9_MaximumWhiteTilesCoveredByACarpet {

    public static int maximumWhiteTiles(int[][] tiles, int carpetLen) {
        //Time : O(n*log(n))
        Arrays.sort(tiles, Comparator.comparingInt(o -> o[0]));
        int n=tiles.length;
        int m=tiles[0].length;
        //Space : O(n*2)
        int[][] prefixRanges=new int[n][m];
        int i=0;
        int count=0;

        //Time : O(n)
        for(int[] range: tiles){
            int x,y;
            x=count+1;
            y=count+range[1]-range[0]+1;
            count=y;
            prefixRanges[i][0]=x;
            prefixRanges[i][1]=y;
            i++;
            // System.out.printf("Index: %s, start: %s, end: %s\n", i-1, x, y);
            // System.out.printf("Index: %s, start: %s, end: %s\n", i-1, tiles[i-1][0], tiles[i-1][1]);
        }
        int rs=0;

        //Time : O(n)
        for(i=0;i<n;i++){
            // int startRange=tiles[i][0];
            int endRange=tiles[i][1];

            if(endRange>=carpetLen){
                int beforeValueRange=endRange-carpetLen;
                //Time : O(log(n))
                int indexRange=findIndexStartLessThanVal(beforeValueRange, tiles, 0, i);
                if(indexRange==-1){
                    //Time : O(log(n))
                    indexRange=findIndexEndLessThanVal(beforeValueRange, tiles, 0, i);
                    // System.out.printf("Find new index: %s\n", indexRange);
                }
                int currentCoverLength=0;
                if(indexRange!=-1){
                    currentCoverLength+=prefixRanges[i][1]-prefixRanges[indexRange][1];
                }else if(i>=1){
                    currentCoverLength+=prefixRanges[i][1]-prefixRanges[i-1][1];
                }
                int beforeCover=0;

                if(indexRange!=-1){
                    beforeCover=Math.max(beforeCover, tiles[indexRange][1]-beforeValueRange);
                }else if(beforeValueRange<=tiles[0][0]){
                    currentCoverLength=prefixRanges[i][1];
                }
                // System.out.printf("Index: %s, beforeValueRange: %s, Finding index: %s, current covert: %s, before cover: %s, total: %s\n",i, beforeValueRange, indexRange, currentCoverLength, beforeCover, currentCoverLength + beforeCover);
                rs=Math.max(rs, currentCoverLength+beforeCover);
            }else{
                // System.out.printf("Greater: %s\n", prefixRanges[i][1]);
                rs=prefixRanges[i][1];
            }
        }
        return rs;
    }

    //Time : O(log(n))
    public static int findIndexStartLessThanVal(int value, int[][] tiles, int low, int high){
        int mid=low+(high-low)/2;

        if(low>=high-1){
            if(tiles[high][0]<=value&&tiles[high][1]>=value){
                return high;
            }
            if(tiles[low][0]<=value&&tiles[low][1]>=value){
                return low;
            }
            return -1;
        }
        int[] range=tiles[mid];
        int rs=-1;
        int index=-1;
        if(range[0]>value){
            high=mid-1;
        }else{
            if(range[1]>=value){
                index=mid;
            }
            low=mid+1;
        }
        rs=findIndexStartLessThanVal(value, tiles, low, high);
        if(rs!=-1){
            return rs;
        }
        return index;
    }

    //Time : O(log(n))
    public static int findIndexEndLessThanVal(int value, int[][] tiles, int low, int high){
        int mid=low+(high-low)/2;

        if(low>=high-1){
            if(tiles[high][1]<=value){
                return high;
            }
            if(tiles[low][1]<=value){
                return low;
            }
            return -1;
        }
        int[] range=tiles[mid];
        int rs=-1;
        int index=-1;
        if(range[1]>value){
            high=mid-1;
        }else{
            index=mid;
            low=mid+1;
        }
        rs=findIndexEndLessThanVal(value, tiles, low, high);
        if(rs!=-1){
            return rs;
        }
        return index;
    }

    public static int maximumWhiteTilesOptimization(int[][] tiles, int carpetLen) {
        //Time : O(n*log(n))
        Arrays.sort(tiles, Comparator.comparingInt(o -> o[0]));
        int n=tiles.length;
        int m=tiles[0].length;
        //Space : O(n*2)
        int[][] prefixRanges=new int[n][m];
        int i=0;
        int count=0;

        //Time : O(n)
        for(int[] range: tiles){
            int x,y;
            x=count+1;
            y=count+range[1]-range[0]+1;
            count=y;
            prefixRanges[i][0]=x;
            prefixRanges[i][1]=y;
            i++;
            // System.out.printf("Index: %s, start: %s, end: %s\n", i-1, x, y);
            // System.out.printf("Index: %s, start: %s, end: %s\n", i-1, tiles[i-1][0], tiles[i-1][1]);
        }
        int rs=0;

        //Time : O(n)
        for(i=0;i<n;i++){
            // int startRange=tiles[i][0];
            int endRange=tiles[i][1];

            if(endRange>=carpetLen){
                int beforeValueRange=endRange-carpetLen;
                //Time : O(log(n))
                int indexRange=findIndex(beforeValueRange, tiles, 0, i);
                int currentCoverLength=0;
                if(indexRange!=-1){
                    currentCoverLength+=prefixRanges[i][1]-prefixRanges[indexRange][1];
                }else if(i>=1){
                    currentCoverLength+=prefixRanges[i][1]-prefixRanges[i-1][1];
                }
                int beforeCover=0;

                if(indexRange!=-1){
                    beforeCover=Math.max(beforeCover, tiles[indexRange][1]-beforeValueRange);
                }else if(beforeValueRange<=tiles[0][0]){
                    currentCoverLength=prefixRanges[i][1];
                }
                // System.out.printf("Index: %s, beforeValueRange: %s, Finding index: %s, current covert: %s, before cover: %s, total: %s\n",i, beforeValueRange, indexRange, currentCoverLength, beforeCover, currentCoverLength + beforeCover);
                rs=Math.max(rs, currentCoverLength+beforeCover);
            }else{
                // System.out.printf("Greater: %s\n", prefixRanges[i][1]);
                rs=prefixRanges[i][1];
            }
        }
        return rs;
    }

    public static int findIndex(int value, int[][] tiles, int low, int high){
        int mid=low+(high-low)/2;

        if(low>=high-1){
            if(tiles[high][0]<=value){
                return high;
            }
            if(tiles[low][0]<=value){
                return low;
            }
            return -1;
        }
        int[] range=tiles[mid];
        int rs=-1;
        int index=-1;
        if(range[0]>value){
            high=mid-1;
        }else{
            index=mid;
            low=mid+1;
        }
        rs=findIndex(value, tiles, low, high);
        if(rs!=-1){
            return rs;
        }
        return index;
    }

    public static int maximumWhiteTilesReference(int[][] tiles, int carpetLen) {
        //Time : O(n*log(n))
        Arrays.sort(tiles, Comparator.comparingInt(o -> o[0]));
        int rs=0;
        int i=0,j=0;
        int n=tiles.length;
        int coveredArea=0;

        while(j<n&&i<=j){
            int start=tiles[i][0];
            int end=tiles[j][1];

            if(end-start+1<=carpetLen){
                coveredArea+=tiles[j][1]-tiles[j][0]+1;
                rs=Math.max(rs, coveredArea);
                j++;
            }else{
                int nextPos=start+carpetLen-1;
                //Có 2 cases:
                //+ [a,b](i)...[c,(pos),d](j)
                //+ [a,b](i)..(pos)..[c,d](j)
                int s=tiles[j][0];
                int e=tiles[j][1];
                if(nextPos>=s&&nextPos<=e){
                    rs=Math.max(rs, coveredArea+nextPos-s+1);
                }
                coveredArea-=tiles[i][1]-tiles[i][0]+1;
                i++;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a 2D integer array tiles where tiles[i] = [li, ri] represents that every tile j in the range li <= j <= ri is colored white.
        //- You are also given an integer carpetLen, the length of a single carpet that can be placed anywhere.
        //* Return the maximum number of white tiles that can be covered by the carpet.
        //Ex:
        //tiles = [[1,5],[10,11],[12,18],[20,25],[30,32]], carpetLen = 10
        //- Ở đây độ dài carpet dài nhất có thể cover sẽ là 9
        //
        //** Idea
        //1.
        //1.0
        //- Constraints
        //1 <= tiles.length <= 5 * 104
        //tiles[i].length == 2
        //1 <= li <= ri <= 10^9
        //1 <= carpetLen <= 10^9
        //
        //- Brainstorm
        //- Để mọi thứ dễ dàng ta sẽ sort array theo start point trước
        //- Ta có thể đặt carpet ở bất cứ đâu --> Ta sẽ đặt ở chỗ có độ bao phủ cao nhất
        //- Cái thứ 2 ta cần check là xem liệu (value=i - thuộc carpet) có thuộc range nào của tiles hay không
        //- Ta có multiple ranges:
        //(a,b)
        //(c,d) : a<=c
        //Ex:
        //[[1,5],[10,11],[12,18],[20,25],[30,32]], carpetLen = 10
        //i=11
        //i=12
        //==> Do i khá lớn nên ta không thể cộng dần i lên được
        //--> Ở đây chỉ cần cộng thêm index của range là được
        //- Có các cases như sau
        //(a,b), (c,d)
        //d-a=4
        //+ Giả sử giữa b và c có 1 số
        //c-b=2
        //==> d-a+1-(c-b-1) = 5 - 1 = 4
        //+ b<c
        //  + length<c-a+1
        //      + max=min(b-a+1, length)
        //  + length>=c-a+1
        //      + length<=d-a+1
        //          + max=min(d-a+1-(c-b-1), length)
        //      + length>d-a+1
        //          + max=d-a+1 + update thêm index
        //+ b>=c
        //  + length>d-a+1
        //  + length<=d-a+1
        //+ low= i (index của range)
        //+ high=j (index của range)
        //Ex: length=10
        //+ index=0, currenStart=1, currentEnd=5 ==> rs=5 (1 -> 5 thừa 5)
        //- Có thể có cases:
        //+ index=1, prevStart=1, currenStart=10, currentEnd=11 => rs=10 (1 -> 10)
        //  + index=2,
        //+ prevStart=1, currenStart=10, currentEnd=11 => rs=10 (1 -> 10)
        //* ==> Tư duy bên trên nhập nhằng và không có hướng đi cụt thể
        //
        //- Bài này ngoài ra còn 1 việc nữa là carpet có thể để lung tung
        //==> Ta cần phải xét all cases có thể
        //Ex:
        //[[1,5],[10,11],[12,18],[20,25],[30,32]], carpetLen = 10
        //i=0, i=1, i=2, i=3, i=4
        //(1,5), (6,7), (8,14),(15, 20), (21, 23)
        //
        //- Idea
        //- Đặt ở mép end của từng range sẽ cho ta giá trị lớn nhất Vì:
        //+ Nếu không đặt covert từ current start --> current end ==> Ta cũng có thể cover ở trước đó
        //
        //- Idea
        //- Đặt từng end một (index=i) ==> Sau đó tìm index=j sao cho:
        //+ start[j]<= end[i]- length+1
        //+ Ở đây có thể tìm được nhiều start ==> (max start)
        //+ Sau đó apply công thức:
        //+ find max start (end - length +1)
        //Ex:
        //(end - length +1)= 25-10+1 = 15 (x)
        //--> max nearest start = 10
        //==> rs= nearest end - x +1 = 18-15 = 3
        //+ 3 + (20-14) = 4+6 = 9
        //
        //Ex:
        //tiles = [[10,11],[1,1]], carpetLen = 2
        //
        //- Special cases:
        //- [[5802,5819],[5512,5532],[5749,5749],[5538,5555],[5771,5777],[5856,5873],[5778,5794],[5570,5589],[5751,5763],
        // [5649,5658],[5605,5608],[5641,5641],[5837,5841],[5699,5712],[5485,5487],[5724,5735],[5620,5638],[5493,5494],
        // [5677,5682]]
        //
        //Ex:
        //(1,2)
        //(3,6)
        //(10,12): 3
        //length=7
        //
        //- Việc tìm index có thể có những cases sau đây:
        //+ index thuộc (a,b) nào đó: index!=-1
        //  + return index của (a,b)
        //+ index không thuộc (a,b) , index, (c,d) ==> Cần trả lại index của (a,b)
        //+ index không thuộc (a,b) ==> Value của nó bé hơn cả tiles[0][0]
        //  + Lấy luôn prefix của end hiện tại
        //
        //- Nếu before value mà âm tức value không nằm ở trong range tại index=index mà tìm thấy
        //==> Trừ đi nó sẽ bị âm --> Ta không lấy giá trị đó là được
        //
        //- Việc tìm index không thuộc (a,b) chính là tìm range có (max end) <= value
        //==> Hiện tại đang implement 1 cái binary search riêng.
        //
        //1.1, Optimization
        //- Gộp 2 method binary search vào thành 1 bằng cách bỏ phần check end (value >= value) đi.
        //
        //1.2, Complexity
        //- Space : O(n)
        //- Time : O(n*log(n)*2 + n)
        //
        //2.
        //2.0,
        //- Idea
        //- Cách tiếp cận ngược lại khi ta sẽ đặt carpet fit ở start của range thay vì end của range
        //- Ta sẽ cover lần lượt từng index một (j++) (init j=i)
        //+ variable cover thế hiện vùng cover white board
        //  + Nếu ta có thể cover ==> Ta sẽ cộng thêm range hiện tại vào cover + j++
        //  + Không thể cover (Remove range hiện tại khỏi cover) ==> Ta sẽ lấy 1 phần + i++, j++
        //
        //- Nếu (i), (j) pos tiếp theo:
        //Có 2 cases:
        //+ [a,b](i)...[c,(pos),d](j)
        //  + Cộng phần partial area
        //+ [a,b](i)..(pos)..[c,d](j)
        //  + coveredArea-=tiles[i][1]-tiles[i][0]+1
        //  + i++
        //
        //1.1, Optimization:
        //-
        //1.2, Complexity
        //- Space : O(1)
        //- Time : O(n*log(n)+n)
        int[][] tiles = {{1,5},{10,11},{12,18},{20,25},{30,32}};
        int carpetLen = 10;
        System.out.println(maximumWhiteTiles(tiles, carpetLen));
        System.out.println(maximumWhiteTilesOptimization(tiles, carpetLen));
        System.out.println(maximumWhiteTilesReference(tiles, carpetLen));
        //#Reference:
        //2790. Maximum Number of Groups With Increasing Length
        //2251. Number of Flowers in Full Bloom
        //2448. Minimum Cost to Make Array Equal
        //1975. Maximum Matrix Sum
        //1610. Maximum Number of Visible Points
        //2815. Max Pair Sum in an Array
        //1144. Decrease Elements To Make Array Zigzag
        //2617. Minimum Number of Visited Cells in a Grid
        //248. Strobogrammatic Number III
        //2363. Merge Similar Items
        //2358. Maximum Number of Groups Entering a Competition
        //1300. Sum of Mutated Array Closest to Target
    }
}
