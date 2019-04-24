package Models;

import Classes.*;

import java.io.*;

public class CompetitionModel {
    public Competition competition;

    public CompetitionModel() throws IOException {
        loadCompetition();
    }

    public void createCompetition(String type) throws IOException {
        if (type.equals("C")) competition = new Championnat("");
        else competition = new Tournoi("");
        saveCompetition();
    }

    public String getTypeCompetition() {
        return competition.getClass().toString();
    }

    public Competition getCompetition() {
        return competition;
    }

    public void saveCompetition() throws IOException {
        ObjectOutputStream oos = null;
        FileOutputStream fout = null;
        try{
            fout = new FileOutputStream("save.txt");
            oos = new ObjectOutputStream(fout);
            oos.writeObject(competition);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(oos != null){
                oos.close();
            }
        }
    }

    private void loadCompetition() throws IOException {
        ObjectInputStream objectinputstream = null;
        try {
            File file = new File("save.txt");
            if (file.exists()) {
                FileInputStream streamIn = new FileInputStream("save.txt");
                objectinputstream = new ObjectInputStream(streamIn);
                Competition readCase = (Competition) objectinputstream.readObject();
                competition = readCase;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(objectinputstream != null){
                objectinputstream.close();
            }
        }
    }
}
