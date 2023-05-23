package chessmaster.testing;
import chessmaster.environment.Board;
import chessmaster.figures.Pawn;
import chessmaster.figures.Bishop;
import chessmaster.game.moves.Move;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BishopTest
{
    private Board board;
    private Bishop bishop;

    private Pawn pawn;

    @BeforeEach
    public void setUp()
    {
        board = new Board();
        bishop = new Bishop('♗', 0, 0, 'W');
        board.setFigureAtPosition(0, 0, bishop);
        pawn = new Pawn('♟', 4, 4, 'B');
        board.setFigureAtPosition(0, 7, pawn);

    }


    @Test
    public void testInvalidMove() {
        Move move = new Move(2, 6);
        boolean result = board.getFigureAtPosition(0, 0).move(board, move);
        Assertions.assertFalse(result);
    }

    @Test
    public void testTake() {
        Move move = new Move(4, 4);
        boolean result = board.getFigureAtPosition(0, 0).move(board, move);
        Assertions.assertTrue(result);
        Assertions.assertTrue(board.getFigureAtPosition(4, 4) instanceof Bishop);
        board.showBoard();
    }
}
