package E1_Graph;

import java.util.*;

public class E41_WebCrawlerMultithreaded {

    public static boolean isValidChar(char c){
        return c=='.'||(c>='a'&&c<='z')||(c>='0'&&c<='9')||c=='-';
    }

    public static String getHostName(String url){
        int startIndex=url.indexOf("//")+1;
        StringBuilder hostName=new StringBuilder();

        for(int i=startIndex+1;i<url.length();i++){
            if(isValidChar(url.charAt(i))){
                hostName.append(url.charAt(i));
            }else{
                break;
            }
        }
        return hostName.toString();
    }

    public static List<String> crawl(String startUrl, E40_WebCrawler.HtmlParser htmlParser) {
        Queue<String> nodes = new LinkedList<>();
        HashSet<String> rs=new HashSet<>();
        String curHostName=getHostName(startUrl);
        rs.add(startUrl);
        nodes.add(startUrl);
        // System.out.printf("HostName: %s\n", curHostName);

        // if(curHostName.charAt(curHostName.length()-1)=='-'||curHostName.charAt(0)=='-'){
        //     return new ArrayList<>();
        // }

        while(!nodes.isEmpty()){
            String curUrl=nodes.poll();
            List<String> adjNodes=htmlParser.getUrls(curUrl);
            // System.out.println(adjNodes);

            for(String nextUrl:adjNodes){
                String curHost=getHostName(nextUrl);
                // System.out.printf("Next HostName: %s\n", curHost);

                // if(curHostName.charAt(curHostName.length()-1)=='-'||curHostName.charAt(0)=='-'){
                //     continue;
                // }
                if(curHost.equals(curHostName)&&!rs.contains(nextUrl)){
                    nodes.add(nextUrl);
                    rs.add(nextUrl);
                }
            }
        }
        return new ArrayList<>(rs);
    }

    public static void main(String[] args) {

    }
}
