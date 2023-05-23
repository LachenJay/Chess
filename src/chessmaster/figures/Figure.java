package chessmaster.figures;

import chessmaster.environment.Board;
import chessmaster.game.moves.Move;

public abstract class Figure
{
    public char name;
    public int xPosition;
    public int yPosition;
    public char color;


    public Figure(char name, int x, int y, char color)
    {
        this.name = name;
        this.xPosition=x;
        this.yPosition=y;
        this.color = color;
    }



    public char getName() {
        return name;
    }

    public void setName(char name) {
        this.name = name;
    }


    public int getxPosition() {
        return xPosition;
    }

    public char getColor()
    {
        return this.color;
    }
    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public abstract boolean move(Board board, Move move);

    private void take(Board board, Move move){}




}
