package interviews.bytedance;

public class E32_CrawlerLogFolder {

    public static int minOperations(String[] logs) {
        int rs=0;
        int n=logs.length;

        for(int i=0;i<n;i++){
            String op=logs[i];
            if(op.equals("../")){
                rs= Math.max(rs - 1, 0);
            }else if(!op.equals("./")){
                rs++;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
//        String[] logs=new String[]{"d1/","d2/","../","d21/","./"};
//        String[] logs=new String[]{"d1/","d2/","./","d3/","../","d31/"};
        String[] logs=new String[]{"d1/","d2/","./","d3/","../","d31/"};
        System.out.println(minOperations(logs));
        //#Reference
        //1599. Maximum Profit of Operating a Centennial Wheel
        //682. Baseball Game
    }
}
