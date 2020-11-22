package hu.bme.redivel.Tetris;

public class Matrix {
    private char[][] matrix;

    public Matrix() {
        matrix = new char[23][12];
        for (int j = 0; j < 23; j++) {
            for (int i = 0; i < 12; i++) {
                if(i == 0 || i == 11 || j == 22){
                    matrix[j][i] = 'b';
                }
                else{
                    matrix[j][i] = 'e';
                }
            }
        }
    }

    public void print(){
        for (int j = 0; j < 23; j++) {
            for (int i = 0; i < 12; i++) {
                System.out.print(matrix[j][i]);
                if(i < 11) {
                    System.out.print("\t");
                }
                else{
                    System.out.print("\n");
                }
            }
        }
    }
}
