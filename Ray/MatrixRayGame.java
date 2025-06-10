import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class MatrixRayGame{
    static char[][] matrix;
    static int rows , cols;
    static HashSet<String> atoms = new HashSet<>();
    static HashMap<String,int[]> rayMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter matrix row and column");
        rows = sc.nextInt();
        cols = sc.nextInt();

        matrix = new char[rows][cols];

        for(char[] row :matrix){
            Arrays.fill(row, '-');
        }

        System.out.println("Enter no of atoms");
        int atomCounts = sc.nextInt();

        for(int i=0;i<atomCounts;i++){
            int r = sc.nextInt()-1;
            int c = sc.nextInt()-1;

            matrix[r][c] = 'X';
            atoms.add(r +","+ c);
        }

        System.out.println("Enter No Of Rays");

        int rayCount = sc.nextInt();

        System.out.println("ENter rays like(R3 C4)");
        sc.nextLine();
        String[] rays = sc.nextLine().split(" ");

        for(String ray:rays){
            simulateRay(ray.trim());
        }

        System.out.println("Final Matrix");
        printMatrix();
    }

    static void simulateRay(String ray){
        int r=-1, c=-1, dr=0, dc=0;

        if(ray.startsWith("R")){
            r = rows- Integer.parseInt(ray.substring(1));
            c=0;
            dr=0; dc =1;
        }

        else if(ray.startsWith("C")){
            r = rows-1;
            c= Integer.parseInt(ray.substring(1))-1;
            dr=-1 ; dc=0;
        }


        int cr =r , cc =c;
        boolean reflected = false;
        boolean hit= false;

        while (cr>=0 && cr<rows && cc>=0 && cc<cols) {

            if(matrix[cr][cc] == 'X'){
                matrix[r][c] = 'H';
                hit = true;
                return;
            }

            boolean leftdiag = atoms.contains((cr+1) +"," + (cc-1)) || atoms.contains((cr-1) +"," + (cc-1));
            boolean rightDiag = atoms.contains((cr+1) +"," + (cc+1)) || atoms.contains((cr-1) +"," + (cc+1));

            if(leftdiag && rightDiag){
                matrix[r][c] = 'R';
                reflected = true;
                return;
            }

            if(leftdiag^rightDiag){
                if(dr==0 && dc==1){
                    dr = (leftdiag)? -1:1;
                    dc =0;
                }
                else if(dr==-1 || dr==1){
                    dc = (leftdiag) ?-1:1;
                    dr =0;
                }
            }

            cr+=dr;
            cc+=dc;
        }

        if(!hit && !reflected){
            matrix[r][c]='R';
        }
    }

    static void printMatrix(){
        for(char[] row : matrix){
            for(char ch :row){
                System.out.print(ch +" ");
            }

            System.out.println();
        }
    }
}