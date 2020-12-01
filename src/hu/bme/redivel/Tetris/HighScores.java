package hu.bme.redivel.Tetris;

import java.io.*;
import java.util.ArrayList;

public class HighScores implements Serializable {
    private ArrayList<Entry> highscores;

    public HighScores() {
        try {
            loadHighscores();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Entry> getHighscores() {
        return highscores;
    }

    private void loadHighscores() throws IOException {
        highscores = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("highscores.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split("\t");
            highscores.add(new Entry(data[0], Integer.parseInt(data[1])));
        }
        reader.close();
    }

    private void saveHighscores() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("highscores.txt"));
        for (Entry entry : highscores) {
            writer.write(entry.getName()+"\t"+entry.getPoints());
            writer.newLine();
        }
        writer.close();
    }

    public void addEntry(String name, int points){
        highscores.add(new Entry(name, points));
        highscores.sort(new EntryComparator());
        try {
            saveHighscores();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
