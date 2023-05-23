package chessmaster.testing;
import chessmaster.environment.Board;
import chessmaster.figures.Pawn;
import chessmaster.figures.Rook;
import chessmaster.game.moves.Move;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RookTest
{
    private Board board;
    private Rook rook;

    private Pawn pawn;

    @BeforeEach
    public void setUp()
    {
        board = new Board();
        rook = new Rook('♖', 0, 0, 'W');
        board.setFigureAtPosition(0, 0, rook);
        pawn = new Pawn('♟', 0, 7, 'B');
        board.setFigureAtPosition(0, 7, pawn);

    }
    @Test
    public void testValidMoveSide() {
        Move move = new Move(7, 0);
        boolean result = board.getFigureAtPosition(0, 0).move(board, move);
        Assertions.assertTrue(result);
        Assertions.assertTrue(board.getFigureAtPosition(7, 0) instanceof Rook);
    }

    @Test
    public void testValidMoveUp() {
        Move move = new Move(0, 7);
        boolean result = board.getFigureAtPosition(0, 0).move(board, move);
        Assertions.assertTrue(result);
        Assertions.assertTrue(board.getFigureAtPosition(0, 7) instanceof Rook);
    }

    @Test
    public void testInvalidMove() {
        Move move = new Move(2, 6);
        boolean result = board.getFigureAtPosition(0, 0).move(board, move);
        Assertions.assertFalse(result);
    }

    @Test
    public void testTake() {
        Move move = new Move(0, 7);
        boolean result = board.getFigureAtPosition(0, 0).move(board, move);
        Assertions.assertTrue(result);
        Assertions.assertTrue(board.getFigureAtPosition(0, 7) instanceof Rook);
        board.showBoard();
    }
}
