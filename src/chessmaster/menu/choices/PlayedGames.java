package chessmaster.menu.choices;

import chessmaster.game.textfile.ChessMoveRecorder;
import chessmaster.menu.MainMenu;
import chessmaster.menu.choices.Choice;
import chessmaster.menu.choices.FileChoice;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class PlayedGames extends Choice {
    public PlayedGames() {
        super("Played Games");
    }

    @Override
    public void Action() {
        ArrayList<FileChoice> fChoice = new ArrayList<>();

        ArrayList<String> fileNames = new ArrayList<>();

        fileNames = findTxtFileNames();

        for (String s : fileNames) {
            fChoice.add(new FileChoice(s));
        }

        for (int i = 0; i < fChoice.size(); i++) {
            System.out.println(fChoice.get(i).getName() + "[" + i + "]");
        }
        System.out.println("Delete Single File" + "[" + (fChoice.size()) + "]");
        System.out.println("Delete All Files" + "[" + (fChoice.size() + 1) + "]");
        System.out.println("Exit" + "[" + (fChoice.size() + 2) + "]");
        Scanner sc = new Scanner(System.in);

        System.out.print("Choose file/option you want to see or use by its number: ");
        String choice = sc.next();

        if (isNumber(choice)) {
            int x = Integer.parseInt(choice);

            if (x == fChoice.size()) {
                deleteSingleFile();
            } else if (x == fChoice.size() + 1) {
                deleteAllFiles();
            } else if (x > fChoice.size()) {
                MainMenu n = new MainMenu();
            } else {
                ChessMoveRecorder chMove = new ChessMoveRecorder();
                chMove.setGameName(fChoice.get(x).getName());
                System.out.println(chMove.readMoves());
            }
        }

        this.Action();
    }

    public static ArrayList<String> findTxtFileNames() {
        ArrayList<String> txtFileNames = new ArrayList<>();

        String filePath = "../Chess/PlayedGames/";
        File directory = new File(filePath);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    String fileName = file.getName();
                    String nameWithoutExtension = fileName.substring(0, fileName.lastIndexOf('.'));
                    txtFileNames.add(nameWithoutExtension);
                }
            }
        }

        return txtFileNames;
    }

    public boolean isNumber(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public void deleteSingleFile() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of the file you want to delete: ");
        String choice = sc.next();

        if (isNumber(choice)) {
            int x = Integer.parseInt(choice);

            ArrayList<String> fileNames = findTxtFileNames();

            if (x >= 0 && x < fileNames.size()) {
                String fileName = fileNames.get(x) + ".txt";
                String filePath = "../Chess/PlayedGames/" + fileName;
                File file = new File(filePath);

                if (file.exists() && file.length() > 0) {
                    if (file.delete()) {
                        System.out.println("File deleted successfully.");
                    } else {
                        System.out.println("Failed to delete the file.");
                    }
                } else {
                    System.out.println("The file is empty or does not exist.");
                }
            } else {
                System.out.println("Invalid file number.");
            }
        } else {
            System.out.println("Invalid input. Please enter a valid file number.");
        }
    }

    public void deleteAllFiles() {
        String filePath = "../Chess/PlayedGames/";
        File directory = new File(filePath);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    if (file.exists() && file.length() > 0) {
                        if (file.delete()) {
                            System.out.println("File deleted successfully: " + file.getName());
                        } else {
                            System.out.println("Failed to delete the file: " + file.getName());
                        }
                    } else {
                        System.out.println("The file " + file.getName() + " is empty or does not exist.");
                    }
                }
            }
        } else {
            System.out.println("No files found in the directory.");
        }
    }

}
