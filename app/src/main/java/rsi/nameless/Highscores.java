package rsi.nameless;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Stéphanie on 5-6-2016.
 */
public class Highscores implements Serializable{
    private String[] highscores;
    private int[] scores;
    private String newScore;

    public Highscores(){
        highscores = new String[15];
        scores = new int[15];

        for(int i=0;i<15;i++){
            scores[i] = 0;
            highscores[i] = (i+1) + ": ";
        }
    }

    public void resetHighscores(){
        for(int i=0;i<15;i++){
            scores[i] = 0;
            highscores[i] = (i+1) + ": ";
        }
    }

    public void addScore(int result, Character player){
        String text = "empty";

        for(int i =0; i< scores.length; i++){
            if(result> scores[i]){
                text = (i+1) + ":  " + player.getName() + "  " + result + "points\n";
                text+= "level: " + player.getLevel() + "\tgold: " + player.getGold() + "\n";
                text += "killed by .... not yet implemented\n";
                insertInArrays(result, text, i);
                break;
            }
        }
    }

    private void insertInArrays(int result, String text, int index){
        for(int i= 14;i> index; i--){
            scores[i] = scores[i-1];
            highscores[i] = highscores[i-1];
        }
        scores[index] = result;
        highscores[index] = text;
    }

    public String[] getHighscores(){
        return highscores;
    }
    public int[] getScores(){
        return scores;
    }
}
