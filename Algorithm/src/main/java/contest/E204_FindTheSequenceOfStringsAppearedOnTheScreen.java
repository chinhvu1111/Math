package contest;

import java.util.*;

public class E204_FindTheSequenceOfStringsAppearedOnTheScreen {

    public static List<String> stringSequence(String target) {
        Queue<String> nodes=new LinkedList<>();
        HashSet<String> visited=new HashSet<>();
        nodes.add("a");
        visited.add("a");
        List<String> rs=new ArrayList<>();
        if("a".equals(target)){
            rs.add("a");
            return rs;
        }
        HashMap<String, String> parent=new HashMap<>();

        while(!nodes.isEmpty()){
            String curNode=nodes.poll();
//            if(curNode.length()>target.length()){
//                break;
//            }
            String newNode = curNode+"a";
            if(!visited.contains(newNode)&&newNode.length()<=target.length()){
                parent.put(newNode, curNode);
                nodes.add(newNode);
                visited.add(newNode);
            }
            if(target.equals(newNode)){
                break;
            }
            int size=curNode.length();
            char c = (curNode.charAt(size-1)=='z')?'a':(char)(curNode.charAt(size-1)+1);
            String newNode1 = curNode.substring(0,size-1)+c;
            //e => a
            if(!visited.contains(newNode1)&&c<=target.charAt(newNode1.length()-1)){
                nodes.add(newNode1);
                parent.put(newNode1, curNode);
                visited.add(newNode1);
            }
            if(target.equals(newNode1)){
                break;
            }
        }
        rs.add(target);
        while (target!=null){
            String prevNode = parent.get(target);
            if(prevNode!=null){
                rs.add(prevNode);
            }
            target=prevNode;
        }
        Collections.reverse(rs);
        return rs.size()<=1?new ArrayList<>():rs;
    }

    public static List<String> getTypingSequence(String target) {
        List<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();

        for (char ch : target.toCharArray()) {
            if (current.length() == 0) {
                current.append('a');
                result.add(current.toString());
            }

            while (current.charAt(current.length() - 1) != ch) {
                char lastChar = current.charAt(current.length() - 1);
                if (lastChar == 'z') {
                    current.setCharAt(current.length() - 1, 'a');
                } else {
                    current.setCharAt(current.length() - 1, (char) (lastChar + 1));
                }
                result.add(current.toString());
            }

            if (current.length() < target.length()) {
                current.append('a');
                result.add(current.toString());
            }
        }

        return result;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given a string target.
        //- Alice is going to type target on her computer using a special keyboard that has only two keys:
        //  + Key 1 appends the character "a" to the string on the screen.
        //  + Key 2 changes the last character of the string on the screen to its next character in the English alphabet.
        //  For example, "c" changes to "d" and "z" changes to "a".
        //* Note that initially there is an empty string "" on the screen, so she can only press key 1.
        //* Return (a list of all strings) that appear on the screen as Alice types target, in the order they appear,
        // using (the minimum key presses).
        //  + Tức là return lại path có đường đi ngắn nhất
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //
        //
        //** Brainstorm
        //- BFS
        //
//        String target = "abc";
//        String target = "z";
//        String target = "a";
//        String target = "aadcddbdbb";
        String target = "abjhgjeaii";
//        System.out.println(stringSequence(target));
        System.out.println(getTypingSequence(target));
    }
}
