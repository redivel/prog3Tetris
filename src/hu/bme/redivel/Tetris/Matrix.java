package hu.bme.redivel.Tetris;

public class Matrix {
    private Block[][] matrix;

    public Matrix() {
        matrix = new Block[12][23];
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 23; j++){
                if (i == 0 || i == 11 || j == 22){
                    matrix[i][j] = new Block(i,j,Variations.Border);
                }
                else{
                    matrix[i][j] = new Block(i,j,Variations.Empty);
                }
            }
        }
    }

    public void print(){
        for (int j = 0; j < 23; j++){
            for (int i = 0; i < 12; i++){
                System.out.print(matrix[i][j]);
                if(i < 11){
                    System.out.print("\t");
                }
                else{
                    System.out.print("\n");
                }
            }
        }
    }

    public Block get(int x, int y){
        return matrix[x][y];
    }
}
