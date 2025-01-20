import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BraceExpansion {
    //tc - o(k^(N/k))  - N is total length of s, k is the average group size
    //sc - o(n)
    List<String> result;
    public String[] expand(String s) {
        List<List<Character>> groups = new ArrayList<>();
        this.result = new ArrayList<>();
        int i =0;
        while(i < s.length()){
            char c = s.charAt(i);
            List<Character> group = new ArrayList<>();
            if(c == '{'){
                i++;
                while(s.charAt(i) != '}'){
                    if(s.charAt(i) != ','){
                        group.add(s.charAt(i));
                    }
                    i++;
                }
                i++;//escape } as well 
            }else{
                group.add(s.charAt(i));
                i++;
            }
            Collections.sort(group); //here group size is very small, so it would be quick 
            groups.add(group);
        }
        dfs(groups, 0, new StringBuilder());

        System.out.println(groups);
        String[] strs = new String[result.size()];
        for(int k = 0; k < result.size(); k++){
            strs[k] = result.get(k);
        }
        return strs;
    }

    private void dfs(List<List<Character>> groups, int curr, StringBuilder path){
        //base
        if(curr == groups.size()){
            result.add(path.toString());
            return;
        }

        //logic 
        List<Character> currGroup = groups.get(curr);
        for(int i =0; i< currGroup.size(); i++){
            char c = currGroup.get(i);
            int le = path.length();
            path.append(c);
            dfs(groups, curr+1, path);
            path.setLength(le);

        }


    }
}
