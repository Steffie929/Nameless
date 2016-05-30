package rsi.nameless;

/**
 * Created by Stéphanie on 30-5-2016.
 */
public class Conversation extends Event {
    private Character player;
    private Character npc;


    public Conversation(Character player, Character npc){
        this.player = player;
        this.npc = npc;
    }




}
