package chessmaster.menu;

import chessmaster.menu.choices.Exit;
import chessmaster.menu.choices.PlayedGames;
import chessmaster.menu.choices.StartGame;

import java.util.Scanner;

public class MainMenu
{
    private StartGame stGame = new StartGame();
    private PlayedGames plGames = new PlayedGames();

    private Exit exit = new Exit();
    public MainMenu()
    {
        System.out.println(stGame.getName() + "[" + 1 +"]");
        System.out.println(plGames.getName() + "[" + 2 +"]");
        System.out.println(exit.getName() + "[" + 3 +"]");
        System.out.print("Choose by typing in a number: ");
        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();



        if(isNumber(choice))
        {
            int x = Integer.parseInt(choice);
            switch (x)
            {
                case 1: stGame.Action(); break;
                case 2: plGames.Action(); break;
                case 3: exit.Action(); break;
                default: System.out.println("That is not a choice"); new MainMenu(); break;
            }
        }
        else if(choice.trim().isEmpty() || !isNumber(choice))
        {
            System.out.println("That is not a choice");
            new MainMenu();
        }

    }
    public boolean isNumber(String str)
    {
        if(str == null || str.isEmpty())
        {
            return false;
        }
        for(int i = 0; i < str.length(); i++)
        {
            if(!Character.isDigit(str.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }
}
