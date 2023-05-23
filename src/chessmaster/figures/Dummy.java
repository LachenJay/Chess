package chessmaster.figures;

import chessmaster.environment.Board;
import chessmaster.game.moves.Move;

public class Dummy extends Figure
{

    public Dummy(char name, int x, int y, char color) {
        super(name, x, y, color);
    }

    @Override
    public boolean move(Board board, Move move) {
        return true;
    }


    public void take(Board board, Move move)
    {


    }
}
