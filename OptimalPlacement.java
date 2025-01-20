import java.util.*;

public class OptimalPlacement{
    static int result;
    static int H, W;
    public static int getOptimalPlacement(int h, int w, int n){
        int[][] grid = new int[h][w];
        result = Integer.MAX_VALUE;
        H = h;
        W = w;
        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                grid[i][j] = -1;
            }
        }
        dfs(grid, 0, 0, n);
        return result;
    }

    public static void dfs(int grid[][], int i, int j, int n){
        if(n == 0){
            bfs(grid);
            return;
        }
        if(j == W){
            j = 0;
            i++;
        }

        for(int r = i; r < H; r++){
            for(int c = j; c < W; c++){
                grid[r][c] = 0;//place building here
                dfs(grid, r, c+1, n-1);
                grid[r][c] = -1;
            }
        }
    }

    public static void bfs(int[][] grid){
        Queue<int[]> q = new LinkedList<>();
        int dirs[][] = {{-1,0},{0,-1},{0,1},{1,0}};
        boolean visited[][] = new boolean[H][W];
        for(int i = 0; i < H; i++){
            for(int j = 0; j < W; j++){
                if(grid[i][j] == 0){
                    q.add(new int[]{i,j});
                    visited[i][j] = true;
                }
            }
        }
        int dist = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int k = 0; k < size; k++){
                int curr[] = q.poll();
                for(int d[]: dirs){
                    int r = curr[0] + d[0];
                    int c=  curr[1] + d[1];
                    if(r >= 0 && c >=0 && r < H && c < W && !visited[r][c] && grid[r][c] == -1){
                        visited[r][c] = true;   
                        q.add(new int[]{r,c});
                    }
                }
            }
            dist++;
        }
        if(result > dist-1){
            System.out.println(Arrays.deepToString(grid));
        }
        result = Math.min(dist-1, result);
    }

    public static void main(String args[]){
        System.out.println(getOptimalPlacement(4, 5, 3));
    }
}