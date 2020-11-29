package hu.bme.redivel.Tetris;

import java.awt.*;
import java.util.Random;

public class Tetrimino {
    private Shape[] rotations;
    private int currentRot;
    int[][][][] coords;
    private Random random = new Random();

    public Tetrimino() {
        currentRot = 0;
        initCoords();
        int rand = random.nextInt(4)+random.nextInt(4);

        rotations = new Shape[]{
                new Shape(coords[rand][0],Variations.valueOf(rand)),
                new Shape(coords[rand][1],Variations.valueOf(rand)),
                new Shape(coords[rand][2],Variations.valueOf(rand)),
                new Shape(coords[rand][3],Variations.valueOf(rand))
        };
    }

    public Tetrimino(Variations v) {
        currentRot = 0;
        initCoords();
        int rand = v.value();

        rotations = new Shape[]{
                new Shape(coords[rand][0],Variations.valueOf(rand)),
                new Shape(coords[rand][1],Variations.valueOf(rand)),
                new Shape(coords[rand][2],Variations.valueOf(rand)),
                new Shape(coords[rand][3],Variations.valueOf(rand))
        };
    }

    private void initCoords() {
        coords = new int[][][][]{
        {
                //I
                {{-2, 0},   {-1, 0},    {0, 0},     {1, 0}},
                {{0, -1},   {0, 0},     {0, 1},     {0, 2}},
                {{1, 1},    {0, 1},     {-1, 1},    {-2, 1}},
                {{-1, 2},   {-1, 1},    {-1, 0},    {-1, -1}}
        },{
                //O
                {{-1, -1},  {0, -1},    {-1, 0},    {0, 0}},
                {{-1, -1},  {0, -1},    {-1, 0},    {0, 0}},
                {{-1, -1},  {0, -1},    {-1, 0},    {0, 0}},
                {{-1, -1},  {0, -1},    {-1, 0},    {0, 0}}
        },{
                //J
                {{-2, -1},  {-2, 0},    {-1, 0},    {0, 0}},
                {{0, -1},   {-1, -1},   {-1, 0},    {-1, 1}},
                {{0, 1},    {0, 0},     {-1, 0},    {-2, 0}},
                {{-2, 1},   {-1, 1},    {-1, 0},    {-1, -1}}
        },{
                //L
                {{-2, 0},   {-1, 0},    {0, 0},     {0, -1}},
                {{-1, 1},   {-1, 0},    {-1, -1},   {0, 1}},
                {{0, 0},    {-1, 0},    {-2, 0},    {-2, 1}},
                {{-1, 1},   {-1, 0},    {-1, -1},   {-2, -1}}
        },{
                //Z
                {{-2, -1},  {-1, -1},   {-1, 0},    {0, 0}},
                {{0, -1},   {0, 0},     {-1, 0},    {-1, 1}},
                {{1, 0},    {-1, 1},    {-1, 0},    {-2, 0}},
                {{-2, 1},   {-2, 0},    {-1, 0},    {-1, -1}}
        },{
                //S
                {{-2, 0},   {-1, 0},    {-1, -1},   {0, -1}},
                {{-1, -1},  {-1, 0},    {0, 0},     {0, 1}},
                {{0, 0},    {-1, 0},    {-1, 1},    {-2, 1}},
                {{-1, 1},   {-1, 0},    {-2, 0},    {-2, -1}}
        }, {
                //T
                {{-2, 0},   {-1, 0},    {0, 0},     {-1, -1}},
                {{-1, -1},  {-1, 0},    {-1, 1},    {0, 0}},
                {{0, 0},    {-1, 0},    {-2, 0},    {-1, 1}},
                {{-1, 1},   {-1, 0},    {-1, -1},    {-2, 0}}
        }
        };
    }

    public void pushToNext() {
        for (int i = 0; i < 6; i++) {
            right();
        }
        for (int i = 0; i < 7; i++) {
            down();
        }
    }

    public void pushToSpawn() {
        for (int i = 0; i < 7; i++) {
            up();
        }
    }

    private void up(){
        for (Shape s:rotations) {
            for (Block b:s.getBlocks()) {
                b.up();
            }
        }
    }

    public void down(){
        for (Shape s:rotations) {
            for (Block b:s.getBlocks()) {
                b.down();
            }
        }
    }

    public void left(){
        for (Shape s:rotations) {
            for (Block b:s.getBlocks()) {
                b.left();
            }
        }
    }

    public void right(){
        for (Shape s:rotations) {
            for (Block b:s.getBlocks()) {
                b.right();
            }
        }
    }

    public void rotate(int dir){
        if(currentRot == 0 && dir == -1){
            currentRot = 3;
        }
        else if(currentRot == 3 && dir == 1){
            currentRot = 0;
        }
        else{
            currentRot += dir;
        }

    }

    public void draw(Graphics g){
        for (Block b:rotations[currentRot].getBlocks()) {
            b.draw(g);
        }
    }

    public Shape getCurShape(){
        return rotations[currentRot];
    }

}
