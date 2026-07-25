import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        int length = friends.length;
        
        int[][] map = new int[length][length];
        
        HashMap<String,Integer> hmap = new HashMap<>();
        int index = 0;
        for(String s: friends){
            if(hmap.get(s)==null){
                hmap.put(s,index++);
            }
        }
        
        for(String s : gifts){
            String[] strs = s.split(" ");
            String from = strs[0];
            String to = strs[1];
            int start = hmap.get(from);
            int end = hmap.get(to);
            map[start][end]++;
        }

        int[] presentPoint = new int[length];
        int[] count = new int[length];
        
        for(int i=0;i<length;i++){
            
            int gives = 0;
            for(int j=0;j<length;j++){
                gives += map[i][j];
            }
            
            int takes = 0;
            for(int j=0;j<length;j++){
                takes += map[j][i];
            }
            presentPoint[i] = gives - takes;
        }
        for(int i=0;i<length;i++){
            for(int j=0;j<length;j++){
                if (map[i][j] == map[j][i]){
                    if(presentPoint[i] == presentPoint[j]){
                        continue;
                    }else if(presentPoint[i] > presentPoint[j]){
                        count[i]++;
                    }else{
                        count[j]++;
                    }
                }else if (map[i][j] > map[j][i]){
                    count[i]++;
                }else{
                    count[j]++;
                }
            }
        }
        for(int i=0;i<length;i++){
            answer = Math.max(answer,count[i]/2);
        }
        
        return answer;
    }
}