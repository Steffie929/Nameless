package rsi.nameless;

import android.util.Log;

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
            highscores[i] ="";
        }
    }

    public void resetHighscores(){
        for(int i=0;i<15;i++){
            scores[i] = 0;
            highscores[i] = "";
        }
    }

    public void addScore(int result, Character player, String enemyName){
        String text = "empty";

        for(int i =0; i< scores.length; i++){
            if(result> scores[i]){
                text = player.getName() + "  " + result + " points\n";
                text+= "Level: " + player.getLevel() + "\t\t\t\tGold: " + player.getGold() + "\n";
                text += "Killed by " + enemyName;
                insertInArrays(result, text, i);
                break;
            }
        }
    }

    private void insertInArrays(int result, String text, int index){
        for(int i= 14;i> index; i--){
            if(highscores[i] == i + "")
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
