package chessmaster.menu.choices;

import chessmaster.game.textfile.ChessMoveRecorder;
import chessmaster.menu.choices.Choice;

public class FileChoice extends Choice
{

    public FileChoice(String name)
    {
        super(name);
    }

    @Override
    public void Action()
    {
        ChessMoveRecorder chMove = new ChessMoveRecorder();

        chMove.setGameName(this.name);

        chMove.readMoves();
    }


}
