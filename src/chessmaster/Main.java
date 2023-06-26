package chessmaster;


import chessmaster.menu.MainMenu;

public class Main {
    public static void main(String[] args)
    {
        try
        {
            MainMenu mn = new MainMenu();
        }
        catch (Exception e)
        {
            System.out.println("Some weird error occurred: " + e.getMessage());
        }
    }
}