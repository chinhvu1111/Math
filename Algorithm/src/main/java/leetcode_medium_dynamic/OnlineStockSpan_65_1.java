package leetcode_medium_dynamic;

import java.util.Stack;

class Pair{
    private Integer x;
    private Integer y;

    public Pair(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}

public class OnlineStockSpan_65_1 {
    private Stack<Pair> stack;
    public OnlineStockSpan_65_1(Stack<Pair> stack) {
        stack=new Stack<>();
    }

    public int next(int price) {
        int rs=1;

        while (!stack.isEmpty()){
            Pair before=stack.peek();

            if(before.getX()>price){
                break;
            }else{
                rs+=before.getY();
                stack.pop();
            }
        }
        stack.push(new Pair(price, rs));
        return rs;

        //Đề bài ở đây là tính chiều dài mà max nhất của số max liên tiếp nhau.
        //VD: 1,2,7,4,10
        // 1,2,3,1,5
        //Bài này là 1 dạng bài toán con của bài 65
        //1, Thường bài này gặp khó khăn trong việc phải đảm bảo thứ tự phần từ + phải giảm được độ phức tạp cùa thuật toán.
        //1.1, Cách cũ thường độ phức tạp sẽ là O(n*n) --> Khi ta phải check lại hoàn toàn giá trị cũ để cộng lên
        //2, Ở đây ta cần chú ý thêm 1 điều nữa:
        //+ Check lại gặp giá trị lớn hơn --> Break (Tức là không cần xét tiếp).
        //+ Chỉ check lại các giá trị nhỏ hơn.
        //Cases tổng quát như sau:
        //1,3,4,(70),(65),63,20,(68),(71)
        //+ Ở đây với trường hợp 68 --> Ta sẽ chỉ tính đến 65.
        // --> Ta có 1 điều hiểu sai rằng:
        // Ta cần lưu lại mọi số trước số (65) --> Để xét đủ độ dài cho đến số (65)
        //+ Đối với trường hợp (71) --> Ta sẽ tính qua số (70)
        //--> Tương tự như trên ta cần lưu hết
        //2.1, Vì quan niêm hơi ngắn --> Ta sẽ không giảm được độ dài.
        //3, Trong 1 khoảng dài dp[i]=3 : Độ dài chuỗi liên tiếp max nhất khi dừng ở [i]
        //VD: 1,8,6,7, x
        //arr[3] (=7) => dp[3]=2
        //+ Đối với X thì trong khoảng 6,7 ta chỉ cần so sánh với (7)
        //Vì thứ tự liên tiếp nhau left --> right: a,b,c,d nếu:
        //+ b<c --> ta chỉ cần xét (c) không cần b
        //+ b>c --> Ta có thể bỏ qua không cần xét b luôn.
        //*** Nhưng b vẫn cần dùng khi xét số d > b
        //--> Ở đây ta có thể hoàn toàn bỏ C --> Giảm số vòng lặp
        //4, Tư duy bỏ phần tử để giám số vòng lặp:
        //+ Lưu kết quả tại mỗi vị trí vòa dp[] --> Dùng lại khi muốn
        //+ Các phần tử bỏ đi sẽ không dùng đến nữa (Ý nghĩa của nó đã được gộp vào phần tử khác).
    }
}
