package rsi.nameless;

import java.io.Serializable;

/**
 * Created by Stéphanie on 30-5-2016.
 */
public class Conversation extends Event implements Serializable{
    private Link currentLink;
    private String npcName, text;
    private String[] options;
    private Character enemy;
    private int imgID;


    /**
     * The constructor of a conversation that cannot end in a battle.
     * @param npcName the name of the person you're talking to.
     * @param imgID the image shown on screen.
     * @param start the first link of the conversation.
     */
    public Conversation(String npcName, int imgID, Link start){
        this.npcName = npcName;
        this.imgID = imgID;
        this.currentLink = start;
    }

    /**
     * the Constructor of a conversation that could end in a battle depending on your choices.
     * @param npcName
     * @param imgID
     * @param start
     * @param enemy
     */
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
