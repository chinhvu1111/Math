package leetcode_medium_dynamic;

import java.util.*;

public class E41_ShoppingOffers {

    public static HashMap<List<Integer>, Integer> hashMap;

    public static void solution(
            List<Integer> needs,
            List<List<Integer>> special,
            Integer sumPrice){
        Integer v=hashMap.get(needs);

        if(v!=null){
            return ;
        }

        List<Integer> newNeeds=new ArrayList<>();
        int min=sumPrice;

        for(int i=0;i<special.size();i++){
            List<Integer> currentSpecial=special.get(i);
            boolean isNegative=false;

            for(int j=0;j<needs.size();j++){
                int newValue=needs.get(j)-currentSpecial.get(j);

                if(newValue<0){
                    isNegative=true;
                    newNeeds.clear();
                    break;
                }
                newNeeds.add(newValue);
            }
            if(!isNegative){
                solution(
                        newNeeds,
                        special,
                        sumPrice+currentSpecial.get(needs.size()));

                if(hashMap.get(newNeeds)==null
                        ||hashMap.get(newNeeds)>sumPrice+currentSpecial.get(needs.size())){
                    hashMap.put(newNeeds, sumPrice+currentSpecial.get(needs.size()));
                }
                newNeeds=new ArrayList<>();
            }else {
                hashMap.put(needs, min);
            }
        }
    }

    public static int shoppingOffers(
            List<Integer> price,
            List<List<Integer>> special,
            List<Integer> needs) {
        hashMap=new HashMap<>();
        solution(needs, special, 0);
        int rs=0;

        for(int i=0;i<needs.size();i++){
            rs+=price.get(i)*needs.get(i);
        }

        Set<Map.Entry<List<Integer>, Integer>> entries = hashMap.entrySet();

        for(Map.Entry<List<Integer>, Integer> entry: entries){
            List<Integer> remains=entry.getKey();
            int currentRs=0;
            int value=entry.getValue();

            for(int i=0;i<remains.size();i++){
                currentRs+=price.get(i)*remains.get(i);
            }
            rs=Math.min(rs, currentRs+value);
        }
        return rs;
    }

    public static void main(String[] args) {
//        int price[]={2,3,4};
//        int special[][]={{1,1,0,4},{2,2,1,9}};
//        int needs[]={1,2,1};
//        int price[]={2,5};
//        int special[][]={{3,0,5},{1,2,10}};
//        int needs[]={3,2};
//        int price[]={0,0,0};
//        int special[][]={{1,1,0,4},{2,2,1,9}};
//        int needs[]={2,2,1};
//        int price[]={6,3,6,9,4,7};
//        int special[][]={{1,2,5,3,0,4,29},{3,1,3,0,2,2,19}};
//        int needs[]={4,1,5,2,2,4};
//        int price[]={9};
//        int special[][]={{1,10},{2,2}};
//        int needs[]={3};
        int price[]={3,4};
        int special[][]={{1,2,3},{1,2,5}};
        int needs[]={2,2};
        List<Integer> prices=new ArrayList<>();
        List<Integer> need=new ArrayList<>();
        List<List<Integer>> specials=new ArrayList<>();

        for(int i=0;i<price.length;i++){
            prices.add(price[i]);
            need.add(needs[i]);
        }
        for(int i=0;i<special.length;i++){
            List<Integer> integers=new ArrayList<>();
            for(int j=0;j<special[i].length;j++){
                integers.add(special[i][j]);
            }
            specials.add(integers);
        }
        //Bài này chưa tìm được thuật toán 100%
        //Bài này chú ý những điều như sau:
        //1, List<> nếu put vào thì --> Không nên dùng list đó clear đi --> Mất data trong list
        //2, Các kết quả chỉ khác nhau ở cách dùng special --> Sau khi cover đc số trường hợp dùng special
        //--> Dùng các phần tử còn lại chia như thế nào cũng được
        //3, Nếu tính được phép chia 1 phần tử nào đó có rồi
        //VD: (3,4,6) --> Đển được (3,4) --> đã tính được tất cả cây con của nó --> Không cần tính lại
        //3.1, Chú ý: Chỉ put vào map khi giá trị mới < giá trị cũ trong Map
        System.out.println(shoppingOffers(prices, specials, need));
    }
}
