package chessmaster.game.textfile;

import java.io.*;

public class ChessMoveRecorder {
    private BufferedWriter writer;
    private BufferedReader reader;

    private String gameName;
    public ChessMoveRecorder() {}
    public ChessMoveRecorder(String gameName) {
        try {
            String fileName = gameName + ".txt";
            String directoryPath = "../Chess/PlayedGames/";
            String filePath = directoryPath + fileName;

            // Check if the file already exists
            File file = new File(filePath);
            int counter = 1;
            while (file.exists()) {
                // Generate a new file name using the Windows duplicate file standard
                String baseName = gameName + "_" + counter;
                fileName = baseName + ".txt";
                filePath = directoryPath + fileName;
                file = new File(filePath);
                counter++;
            }

            writer = new BufferedWriter(new FileWriter(filePath, true));
            reader = new BufferedReader(new FileReader(filePath));
            System.out.println("Text file opened successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while opening the text file.");
            e.printStackTrace();
        }
    }


    public void recordMove(String playerColor, String chessPiece, String startPosition, String endPosition) {
        try {
            writer.write("Player Color: " + playerColor + "\n");
            writer.write("Chess Piece: " + chessPiece + "\n");
            writer.write("Start Position: " + startPosition + "\n");
            writer.write("End Position: " + endPosition + "\n");
            writer.write("\n");

            writer.flush(); // Flush the buffer to ensure the data is written immediately
            System.out.println("Move recorded successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while recording the move.");
            e.printStackTrace();
        }
    }
    public void setGameName(String name)
    {
        this.gameName=name;
    }
    public String readMoves() {
        StringBuilder moves = new StringBuilder();

        try {
            String fileName = this.gameName + ".txt";
            String filePath = "../Chess/PlayedGames/" + fileName;
            BufferedReader fileReader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = fileReader.readLine()) != null) {
                moves.append(line).append("\n");
            }
            fileReader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the moves from the file.");
            e.printStackTrace();
        }
        return moves.toString();
    }

}
