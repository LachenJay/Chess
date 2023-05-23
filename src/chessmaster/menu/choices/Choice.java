package chessmaster.menu.choices;

public abstract class Choice
{
    public String name;


    public Choice(String name)
    {
        this.name = name;
    }

    public void Action(){}

    public String getName()
    {
        return this.name;
    }
}
