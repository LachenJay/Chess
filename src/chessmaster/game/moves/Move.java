package chessmaster.game.moves;

public class Move implements MoveInterface
{
    public int x;
    public int y;

    public Move(int x, int y)
    {
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
