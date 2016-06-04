package rsi.nameless;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Stéphanie on 30-5-2016.
 */
public class Conversation extends Event implements Serializable{
    private Link currentLink;
    private String npcName, text;
    private String[] options;
    private Character enemy;
    private int imgID;


    public Conversation(String npcName, int imgID, Link start){
        this.npcName = npcName;
        this.imgID = imgID;
        this.currentLink = start;
    }

    public Conversation(String npcName, int imgID, Link start, Character enemy){
        this.npcName = npcName;
        this.imgID = imgID;
        this.currentLink = start;
        this.enemy = enemy;
    }


    public int getImgID() {
        return imgID;
    }
    public Link getLink(){
        return currentLink;
    }

    public Character getEnemy(){
        return enemy;
    }

}
