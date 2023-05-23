package chessmaster.menu.choices;

import chessmaster.menu.choices.Choice;

public class Exit extends Choice
{
    public Exit()
    {
        super("Exit");
    }

    @Override
    public void Action()
    {
        System.exit(0);
    }
}
