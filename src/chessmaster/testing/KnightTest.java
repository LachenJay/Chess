package chessmaster.testing;

import chessmaster.figures.Pawn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chessmaster.environment.Board;
import chessmaster.game.moves.Move;
import chessmaster.figures.Knight;

public class KnightTest {
    private Board board;
    private Knight knight;

    private Pawn pawn;

    @BeforeEach
    public void setUp() {
        board = new Board();
        knight = new Knight('♘', 1, 0, 'W');
        board.setFigureAtPosition(1, 0, knight);
        pawn = new Pawn('♟', 2, 2, 'B');
        board.setFigureAtPosition(2, 2, pawn);

    }

    @Test
    public void testValidMove() {
        Move move = new Move(1, 2);
        boolean result = board.getFigureAtPosition(1, 0).move(board, move);
        knight = (Knight) board.getFigureAtPosition(2, 2);
        Assertions.assertTrue(result);
        Assertions.assertTrue(board.getFigureAtPosition(2, 2) instanceof Knight);
    }

    @Test
    public void testInvalidMove() {
        Move move = new Move(1, 1); // Invalid move for a knight
        boolean result = board.getFigureAtPosition(1, 0).move(board, move);
        Assertions.assertFalse(result);
    }

    @Test
    public void testTake() {
        Move move = new Move(1, 2);

        boolean result = board.getFigureAtPosition(1, 0).move(board, move);
        Assertions.assertTrue(result);
        // Assert that the target position contains the knight
        Assertions.assertTrue(board.getFigureAtPosition(2, 2) instanceof Knight);
        board.showBoard();
    }
}
