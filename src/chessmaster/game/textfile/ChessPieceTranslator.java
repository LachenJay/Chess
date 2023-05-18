package chessmaster.game.textfile;

public class ChessPieceTranslator
{
    public String translateChessPiece(char piece) {
        switch (piece) {
            case '♔':
                return "white king";
            case '♕':
                return "white queen";
            case '♖':
                return "white rook";
            case '♗':
                return "white bishop";
            case '♘':
                return "white knight";
            case '♙':
                return "white pawn";
            case '♚':
                return "black king";
            case '♛':
                return "black queen";
            case '♜':
                return "black rook";
            case '♝':
                return "black bishop";
            case '♞':
                return "black knight";
            case '♟':
                return "black pawn";
            default:
                return "Unknown chess piece";
        }
    }
}
