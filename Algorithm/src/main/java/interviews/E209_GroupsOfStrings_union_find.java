package interviews;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class E209_GroupsOfStrings_union_find {

    public static int findParent(int[] parent, int u){
        while (parent[u]!=u){
            u=parent[u];
        }
        return u;
    }

    public static void union(int[] size, int[] height, int[] parent, int u, int v){
        int parentU=findParent(parent, u);
        parent[u]=parentU;
        int parentV=findParent(parent, v);
        parent[v]=parentV;

        if(parentU==parentV){
            return;
        }
        numberGroupsInit--;
        if(height[parentU]>height[parentV]){
            height[parentU]=height[parentV]+1;
            size[parentU]+=size[parentV];
            maxNumberOfGroup=Math.max(maxNumberOfGroup, size[parentU]);
            parent[parentV]=parentU;
//            System.out.printf("%s is parent of %s\n", parentU, parentV);
        }else{
            height[parentV]=height[parentU]+1;
            parent[parentU]=parentV;
            size[parentV]+=size[parentU];
            maxNumberOfGroup=Math.max(maxNumberOfGroup, size[parentV]);
//            System.out.printf("%s is parent of %s\n", parentV, parentU);
        }
    }

    public static int numberGroupsInit;
    public static int maxNumberOfGroup;

    public static int[] groupStringsUnionFind(String[] words) {
        //Contains all bit mask of all strings
        HashMap<Integer, Integer> hashMapMask=new HashMap<>();
        int n=words.length;
        int[] parent=new int[n];
        int[] height=new int[n];
        int[] size=new int[n];
        numberGroupsInit=n;
        maxNumberOfGroup=0;
        if(n!=0){
            maxNumberOfGroup=1;
        }
        for(int i=0;i<n;i++){
            height[i]=1;
            parent[i]=i;
            size[i]=1;
        }
        int[] indexBitMask=new int[n];

        for(int i=0;i<n;i++){
            int lS=words[i].length();
            int mask=0;

            for(int j=0;j<lS;j++){
                mask|=1<<words[i].charAt(j)-'a';
            }
            hashMapMask.put(mask, i);
            indexBitMask[i]= mask;
        }

        long startTime=System.currentTimeMillis();
        long endTime=System.currentTimeMillis();

        for(int i=0;i<n;i++){
            String currentWord=words[i];
            int currentBitmask=indexBitMask[i];

//            startTime=System.currentTimeMillis();
            union(size, height, parent, i, hashMapMask.get(currentBitmask));
//            endTime=System.currentTimeMillis();
//            System.out.println("Run in :"+(endTime-startTime)+ " seconds");

            //Remove one of them
            //Transfer string s --> s1 --> Check exists
            startTime=System.currentTimeMillis();
            for(int j=0;j<currentWord.length();j++){
                //delete
                int deletedBitMask=currentBitmask ^ 1<<(currentWord.charAt(j)-'a');
                if(hashMapMask.containsKey(deletedBitMask)){
                    union(size, height, parent, i, hashMapMask.get(deletedBitMask));
//                    System.out.printf("%s %s is same group\n", words[i], words[hashMapMask.get(deletedBitMask)]);
//                    continue;
                }

                //Replace + add
                for(int h=0;h<26;h++){
                    if(h==(currentWord.charAt(j)-'a')||(deletedBitMask|1<<h)==deletedBitMask){
                        continue;
                    }
                    //Replace
//                    int replacedBitMask=currentBitmask ^ 1<<(currentWord.charAt(j)-'a') | 1<<h;
                    int replacedBitMask=deletedBitMask | 1<<h;

                    if(hashMapMask.containsKey(replacedBitMask)){
                        union(size, height, parent, i, hashMapMask.get(replacedBitMask));
//                        System.out.printf("%s %s is same group\n", words[i], words[hashMapMask.get(replacedBitMask)]);
//                        break;
                    }

//                    int addBitMask=currentBitmask | 1<<h;
//
//                    if(hashMapMask.containsKey(addBitMask)){
//                        union(size, height, parent, i, hashMapMask.get(addBitMask));
////                        System.out.printf("%s %s is same group\n", words[i], words[hashMapMask.get(addBitMask)]);
////                        break;
//                    }
                }
            }
            if(i%1000==0){
                endTime=System.currentTimeMillis();
                System.out.println("Run in :"+(endTime-startTime)+ " millies seconds");
            }
        }
        endTime=System.currentTimeMillis();
        System.out.println("Run in :"+(endTime-startTime)+ " seconds");

        return new int[]{numberGroupsInit,maxNumberOfGroup};
    }

    public static void main(String[] args) {
//        String[] words=new String[]{"ghnv","uip","tenv","hvepx","e","ktc","byjdt","ulm","cae","ea"};
        String[] words = new String[]{"umeihvaq",
                "ezflcmsur", "ynikwecaxgtrdbu", "u", "q", "gwrv", "ftcuw", "ocdgslxprzivbja", "zqrktuepxs", "cpqolvnwxz", "geqis", "xgfdazthbrolci", "vwnrjqzsoepa", "udzckgenvbsty", "lpqcw", "nekpvchqfgdo", "iapjhxvdrmwetz", "gw", "waxokchnmifsruj", "vqp", "vbpkij", "ufjvbstzh", "swiu", "knslbdcahfrox", "ctofplkhednmv", "g", "zk", "idretzjbpl", "pxqdauys", "mfgrqaktbzpv", "vdtq", "wyxjrcie", "kl", "jpcdzmli", "oth", "yumdawhfbskcjo", "rvfksqhu", "swemnvjpg", "rnl", "zgd", "rmzdbcsqht", "ure", "qlusoaxprtebn", "zkbmvtpya", "jszxuwevfidkm", "smlft", "cpwugmbzfsqr", "cblkjevhp", "iyfnozaulex", "qvlok", "wsgm", "du", "awyplckj", "aey", "ycsjqnt", "vtoqzsyx", "ejqixsmrdhlofyp", "kvlmurbzjg", "lysdahgpwmrcn", "af", "jkezhdu", "etjzqiyghdnovm", "ycwdfnluoke", "kwshbx", "pyvaznljqwes", "xakinu", "e", "zjexfgvhtabwcy", "thuvwlnjkbxym", "jorzeslpidmhubq", "wnr", "qzdv", "qeovrbmwzgpdh", "jkioenptaygfubh", "bvndzxijope", "cudizhjntbes", "rnhzitpqoexwb", "ihezcmfqouyl", "q", "mwtsdjqn", "hrmc", "hxaocbyikluvqsf", "d", "vgwjzuaondbcm", "ibqxltf", "rzyhguptmesqo", "ruwgy", "jvprwhtzuf", "aupngodjexkiw", "yhijelwpvtsrbqc", "gtick", "koilywcfbs", "elv", "dehxzlitskq", "ptvbkql", "msfxyjahlzo", "oslxzfwrpmtyh", "gypuchkwa", "rsqij", "tw", "igbcylqfhtmjkr", "nryhzjgi", "pw", "bnfairow", "xjzrf", "olxfypjtmrncuv", "ifhue", "akcvofuyzwbj", "tvhxfeuiykpwbsz", "wnrztclfpm", "ozvypnfwrqg", "cwkgr", "gjyzrucplbsfe", "pdtzmfoy", "wehd", "bnvqhcmg", "uyw", "sgynxljqbf", "tvxbq", "wcmguioelbdrkvx", "okvtyexuj", "hjbc", "uidcswzm", "jemtkvshizaub", "rmb", "jpgnqdemzcxa", "dmalekhiyj", "akocedu", "rlpqufcv", "r", "lohgs", "xapnorj", "cdb", "icopdtzxy", "xcrflvojqgpkwt", "elv", "rp", "yv", "u", "atdxqeilhkg", "olfvmrgkb", "rplxskabvtqmhw", "n", "rldswkyoujmfxpn", "rvgejzdusoya", "hvoft", "wskgmjchz", "luagnzkj", "ywe", "i", "wcqtsk", "umpvywknjbxacsd", "ynavjpcrgq", "jyftmklci", "xfol", "zh", "kut", "zvawyielscotkn", "p", "wykpqdjoz", "uabtpxkvq", "uabtifwhrvxc", "sdcamqup", "srghwfptloxvke", "sfdywtx", "tuohnxzjqmac", "pwxjyhdurnfz", "axgfcuqtiyhjz", "rwqpyh", "bmoznqavicdgp", "jcu", "vnkc", "jpb", "nvfqyahjkul", "radpctwixygb", "pvjmk", "s", "dzyqjbwucne", "mgh", "ivc", "eaqc", "yjimsadtcwbgk", "lo", "ayirlsfevtwpnd", "wcsk", "xlvejy", "kcjrqf", "a", "ixsdga", "vk", "cqxyfotziwrvl", "zmxboiewhfdjlnr", "kdpwngf", "zyretijxpw", "ncw", "ljw", "mrxeciy", "aqwcofnjypsgi", "byuvhj", "ukidyqzhxgowmc", "cpqsmu", "auwmcrpdisbzokg", "pxgwmvfq", "azgljrsyeqwxfic", "xmlgpdrzwqe", "emgdcqntjpwrf", "hrwq", "zmjkx", "npabcide", "dvlfxnt", "kilqsvmborf", "lvsxjnbimhpzfow", "sqcym", "tcjmkwq", "yugkwdzvmteon", "pq", "nklmb", "azqcnodkimtxve", "ovpcfe", "uqkcwjimbvdyx", "xvdazh", "xk"};
//        String[] words=new String[]{"a","b","ab","cde"};
//        String[] words=new String[]{"a","ab","abc"};
        int[] result=groupStringsUnionFind(words);
        System.out.println(result[0]+" "+result[1]+"\n");
        //1000
//        System.out.println(getBitK(3, 8));
        System.out.println('z'-'a');
        //
        //
        //
        //- Không cần add --> delete là ngược lại của add rồi
        //- Tối ưu bằng cách :
        //+ log end time - start time
        //+ Log theo từng batch 1000, compare với reference code
        //+ Thêm đoạn code để tránh union (find) quá nhiều
        //=====Code
        //if(h==(currentWord.charAt(j)-'a')||(deletedBitMask|1<<h)==deletedBitMask){
        //    continue;
        //}
        //=====Code
        //- TLE nếu dùng cách bình thường O(N^2)
        //- Các case sai : Duplicate cần làm rõ

    }
}

