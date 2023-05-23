package chessmaster.menu.choices;

import chessmaster.game.game.Game;
import chessmaster.menu.choices.Choice;

public class StartGame extends Choice
{
    public StartGame()
    {
        super("Start game");
    }

    @Override
    public void Action()
    {
        Game game = new Game();
    }
}
