package rsi.nameless;

import java.io.Serializable;

/**
 * Created by Stéphanie on 30-5-2016.
 */
public class Link implements Serializable{
    private String text;
    private String[] options = new String[3];
    private Link[] links;
    private boolean last;
    private boolean battle;
    private boolean questAccepted;
    private boolean reward;

    public Link(String text, boolean battle, boolean reward){
        this.text = text;
        last = true;
        this.battle = battle;
        this.reward = reward;
    }

    public Link(String text, String[] options, Link[] links){
        this.text = text;
        this.options = options;
        last = false;
        battle = false;
        questAccepted = false;
        reward = false;

        this.links = links;
    }





    public String getOption(int index){
        return options[index];
    }
    public String getText(){
        return text;
    }

    public Link getNext(int index){
        return links[index];
    }

    public boolean isLast(){
        return last;
    }

    public boolean isBattle() {
        return battle;
    }

    public boolean isReward() {
        return reward;
    }
}
