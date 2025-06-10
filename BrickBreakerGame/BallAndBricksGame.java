import java.util.Scanner;

public class BallAndBricksGame {
    private static final int SIZE = 7;
    private static final int INITIAL_LIVES = 5;
    private static char[][] grid;
    private static int ballRow;
    private static int ballCol;
    private static int ballLives;
    private static Scanner scanner;

    public static void main(String[] args) {
        initializeGame();
        scanner = new Scanner(System.in);
        
        while (ballLives > 0) {
            printGrid();
            System.out.println("Ball count: " + ballLives);
            System.out.print("Enter command (St, Lt, Rt): ");
            String command = scanner.nextLine().trim();
            
            if (command.equalsIgnoreCase("St")) {
                moveStraight();
            } else if (command.equalsIgnoreCase("Lt")) {
                moveLeftDiagonal();
            } else if (command.equalsIgnoreCase("Rt")) {
                moveRightDiagonal();
            } else {
                System.out.println("Invalid command. Use St, Lt, or Rt.");
            }
        }
        
        System.out.println("Game Over! No more lives left.");
        scanner.close();
    }

    private static void initializeGame() {
        grid = new char[SIZE][SIZE];
        ballLives = INITIAL_LIVES;
        
        // Initialize walls and empty spaces
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i == 0 || i == SIZE - 1 || j == 0 || j == SIZE - 1) {
                    grid[i][j] = 'w';
                } else {
                    grid[i][j] = ' ';
                }
            }
        }
        
        // Set ground (bottom row except walls)
        for (int j = 1; j < SIZE - 1; j++) {
            grid[SIZE - 1][j] = 'g';
        }
        
        // Place the ball in the center of the ground
        ballRow = SIZE - 1;
        ballCol = SIZE / 2;
        grid[ballRow][ballCol] = 'o';
        
        // Place bricks
        placeBrick(2, 2, '1');
        placeBrick(2, 3, '1');
        placeBrick(2, 4, '1');
        placeBrick(3, 2, '1');
        placeBrick(3, 3, '1');
        placeBrick(3, 4, '1');
    }

    private static void placeBrick(int row, int col, char strength) {
        grid[row][col] = strength;
    }

    private static void printGrid() {
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void moveStraight() {
        int currentRow = ballRow;
        int currentCol = ballCol;
        
        // Move up until hitting something
        while (currentRow > 0) {
            currentRow--;
            
            if (grid[currentRow][currentCol] != ' ' && grid[currentRow][currentCol] != 'g') {
                // Hit something
                if (grid[currentRow][currentCol] == 'w') {
                    // Hit wall - bounce back
                    ballLives--;
                    return;
                } else {
                    // Hit brick
                    hitBrick(currentRow, currentCol);
                    return;
                }
            }
        }
    }

    private static void moveLeftDiagonal() {
        int currentRow = ballRow;
        int currentCol = ballCol;
        boolean movingUp = true;
        
        while (true) {
            // Move diagonally
            if (movingUp) {
                currentRow--;
                currentCol--;
            } else {
                currentRow++;
                currentCol++;
            }
            
            // Check boundaries
            if (currentRow < 0 || currentRow >= SIZE || currentCol < 0 || currentCol >= SIZE) {
                ballLives--;
                return;
            }
            
            char cell = grid[currentRow][currentCol];
            if (cell != ' ' && cell != 'g') {
                if (cell == 'w') {
                    // Hit wall - change direction
                    movingUp = !movingUp;
                } else {
                    // Hit brick
                    if (hitBrick(currentRow, currentCol)) {
                        // Brick destroyed - continue moving down
                        movingUp = false;
                    } else {
                        // Brick not destroyed - return to original position
                        return;
                    }
                }
            }
            
            // If moving down and reached ground
            if (!movingUp && currentRow == SIZE - 1) {
                // Update ball position
                grid[ballRow][ballCol] = 'g';
                ballRow = currentRow;
                ballCol = currentCol;
                grid[ballRow][ballCol] = 'o';
                return;
            }
        }
    }

    private static void moveRightDiagonal() {
        int currentRow = ballRow;
        int currentCol = ballCol;
        boolean movingUp = true;
        
        while (true) {
            // Move diagonally
            if (movingUp) {
                currentRow--;
                currentCol++;
            } else {
                currentRow++;
                currentCol--;
            }
            
            // Check boundaries
            if (currentRow < 0 || currentRow >= SIZE || currentCol < 0 || currentCol >= SIZE) {
                ballLives--;
                return;
            }
            
            char cell = grid[currentRow][currentCol];
            if (cell != ' ' && cell != 'g') {
                if (cell == 'w') {
                    // Hit wall - change direction
                    movingUp = !movingUp;
                } else {
                    // Hit brick
                    if (hitBrick(currentRow, currentCol)) {
                        // Brick destroyed - continue moving down
                        movingUp = false;
                    } else {
                        // Brick not destroyed - return to original position
                        return;
                    }
                }
            }
            
            // If moving down and reached ground
            if (!movingUp && currentRow == SIZE - 1) {
                // Update ball position
                grid[ballRow][ballCol] = 'g';
                ballRow = currentRow;
                ballCol = currentCol;
                grid[ballRow][ballCol] = 'o';
                return;
            }
        }
    }

    private static boolean hitBrick(int row, int col) {
        char brick = grid[row][col];
        
        if (brick > '0' && brick <= '9') {
            int strength = Character.getNumericValue(brick) - 1;
            
            if (strength == 0) {
                // Brick destroyed
                grid[row][col] = ' ';
                return true;
            } else {
                // Brick weakened
                grid[row][col] = (char) (strength + '0');
                return false;
            }
        }
        
        return false;
    }
}