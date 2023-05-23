package chessmaster.testing;

import chessmaster.environment.Board;
import chessmaster.figures.Pawn;
import chessmaster.figures.King;
import chessmaster.game.moves.Move;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KingTest
{
    private Board board;
    private King king;

    private Pawn pawn;

    @BeforeEach
    public void setUp()
    {
        board = new Board();
        king = new King('♔', 0, 0, 'W');
        board.setFigureAtPosition(0, 0, king);
        pawn = new Pawn('♟', 1, 1, 'B');
        board.setFigureAtPosition(1, 1, pawn);

    }
    @Test
    public void testValidMoveSide() {
        Move move = new Move(1, 0);
        boolean result = board.getFigureAtPosition(0, 0).move(board, move);
        Assertions.assertTrue(result);
        Assertions.assertTrue(board.getFigureAtPosition(1, 0) instanceof King);
    }

    @Test
    public void testValidMoveUp() {
        Move move = new Move(0, 1);
        boolean result = board.getFigureAtPosition(0, 0).move(board, move);
        Assertions.assertTrue(result);
        Assertions.assertTrue(board.getFigureAtPosition(0, 1) instanceof King);
    }

    @Test
    public void testInvalidMove() {
        Move move = new Move(2, 6);
        boolean result = board.getFigureAtPosition(0, 0).move(board, move);
        Assertions.assertFalse(result);
    }

    @Test
    public void testValidMoveDiagonal()
    {
        Move move = new Move(1, 1);
        pawn = new Pawn(' ', 1, 1, 'N');
        board.setFigureAtPosition(1, 1, pawn);
        boolean result = board.getFigureAtPosition(0, 0).move(board, move);
        Assertions.assertTrue(result);
    }
    @Test
    public void testTake() {
        Move move = new Move(1, 1);

        boolean result = board.getFigureAtPosition(0, 0).move(board, move);
        Assertions.assertTrue(result);
        Assertions.assertTrue(board.getFigureAtPosition(1, 1) instanceof King);
        board.showBoard();
    }
}
