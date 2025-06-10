import java.util.Scanner;

public class BrickBreaker{
    static char grid[][] = new char[7][7];
    static int[][] strength = new int[7][7];
    static int bollRow = 6, bollCol =  3;
    static int lives =5;

    public static void main(String[] args) {

        initialiceGrid();
        Scanner sc = new Scanner(System.in);

        while (lives>0) {
            printGrid();

            System.out.println("ENter Command (St,Lt,RT): ");

            String cmd = sc.nextLine().trim().toLowerCase();

            switch (cmd) {
                case "st":
                    handleStright();
                    break;

                case "lt":
                    handleDiagonal(-1);
                    break;

                case "rt":
                    handleDiagonal(1);
                    break;
            
                default:
                    break;
            }

        }


    }

    public static void initialiceGrid(){
        for(int i=0;i<7;i++){
            for(int j=0;j<7;j++){
                if(i==0 || j==0  || j==6){
                    grid[i][j] = 'W';
                }
                else if(i==6 && j!=0 && j!= 6){
                    grid[i][j] = 'g';
                }
                else{
                    grid[i][j] = ' ';
                }
            }
        }

        int[][] bricks = { {2, 2}, {2, 3}, {2, 4}, {3, 2}, {3, 3}, {3, 4} };

        for(int[] b : bricks){
            grid[b[0]][b[1]] = (char) ('1'); 
            strength [b[0]][b[1]] =1;
        }


        grid[bollRow][bollCol] = 'o';
    }

    public static void printGrid(){
        for(char[] rows : grid){
            for(char i : rows){
                System.out.print(i + " ");
            }
            System.out.println();
        }

        System.out.println("Ball Count : " + lives);
    }

    public static void handleStright(){
        for(int r=bollRow-1;r>=0;r--){
            if(grid[r][bollCol]>='1' && grid[r][bollCol]<='9'){
                hitBrick(r,bollCol);
                return;
            }
            else if(grid[r][bollCol] == 'W'){
                return;
            }
        }
    }

    public static boolean hitBrick(int r , int c){
        strength[r][c] --;
        if(strength[r][c] ==0){
            grid[r][c] = ' ';
            return true;
        }
        return false;
    }

    // public static void handleDiagonal(int dir){
    //     int r = bollRow-1;
    //     int c = bollCol+dir;

    //     grid[bollRow][bollCol] = 'g';

    //     while ((r>=0 && c>=0 && r<7 && c<7)) {
    //         if(grid[r][c] >= '1' && grid[r][c]<='9'){
    //             if(hitBrick(r, c)){
    //                 moveBallDOwn(r,c);
    //             }
    //             return;
    //         }
    //         else if(grid[r][c] == 'W'){
    //             lives--;
    //             return;
    //         }

    //         r--;
    //         c+=dir;
    //     }

    //     grid[bollRow][bollCol] = 'o';
    //     lives--;
    // }


    public static void handleDiagonal(int dir) {
    int r = bollRow - 1;
    int c = bollCol + dir;

    // Clear old ball position before processing
    grid[bollRow][bollCol] = 'g';

    while ((r >= 0 && c >= 0 && r < 7 && c < 7)) {
        if (grid[r][c] >= '1' && grid[r][c] <= '9') {
            if (hitBrick(r, c)) {
                // Brick destroyed: ball falls into that place
                moveBallDOwn(r, c);
            } else {
                // Brick not destroyed: return ball to original position
                grid[bollRow][bollCol] = 'o';
                lives--;
            }
            return;
        } else if (grid[r][c] == 'W') {
            // Hit wall: return to original place
            grid[bollRow][bollCol] = 'o';
            lives--;
            return;
        }

        // Keep going diagonally
        r--;
        c += dir;
    }

    // If nothing is hit (falls out of grid): lose a life
    grid[bollRow][bollCol] = 'o';
    lives--;
}

    public static void moveBallDOwn(int r , int c){
        grid[bollRow][bollCol] = 'g';

        while (r<6 && grid[r+1][c]=='g') {
            r++;
        }

        bollRow = r;
        bollCol = c;
        grid[bollRow][bollCol] = 'o';
        lives--;
    }
}