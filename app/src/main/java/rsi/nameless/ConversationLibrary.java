package rsi.nameless;

import java.util.ArrayList;

/**
 * Created by Stéphanie on 30-5-2016.
 */
public class ConversationLibrary {
    private ArrayList<Conversation> conversations;
    private EnemyLibrary enemyLib;

    public ConversationLibrary(EnemyLibrary enemyLib){
        this.enemyLib = enemyLib;
        conversations = new ArrayList<>();
        conversations.add(new Conversation ("Stranger", R.drawable.stranger ,firstConversation(), enemyLib.getBoss(2)));
        conversations.add(new Conversation ("Yourself", R.drawable.ci_1_nosw_nosh ,secondConversation(), enemyLib.getBoss(0)));
    }

    public Link firstConversation(){
        String text;

        text = "Good, I'll see you around, remember I will pay good money if you find an artefacts in this region";
        Link c1 = new Link(text, false, true);

        text = "Keeping the artefact will have very serious consequences, you will bring it to me!";
        Link c2 = new Link(text, false, false);

        text = "How dare you speak to me that way! I am .. from ... You will regret this! ";
        Link a3 = new Link(text, true, false);

        String[] options = new String[3];
        Link[] linksA = {c1, c2, c2};
        text = "Insolent fool, I don't have time to play games. \nIf you happen to come across an artefact, bring it to me and I will reward you very generously.\n" +
                "I wouldn't suggest keeping it, I doubt you will like the consequences..";
        options[0] = "Sounds good";
        options[1] = "We'll see. I have to think about it";
        options[2] = "Yea right, I'll just hand over a probably valuable artefact..";
        Link b3 = new Link(text, options, linksA);

        options = new String[3];
        text = "Hmm, that's too bad. Well if you happen to come across an artefact, bring it to me and I will reward you very generously.";
        options[0] = "Sounds good";
        options[1] = "We'll see. I have to think about it";
        options[2] = "Yea right, I'll just hand over a probably valuable artefact..";
        Link b2 = new Link(text, options, linksA);

        options = new String[3];
        text = "Interesting, the artefact must be here * evil laugh*. I have a proposal for you. \nShould you find a strange artefact" +
                " somewhere in the area bring it to me and I will reward you very generously.";
        options[0] = "Sounds good";
        options[1] = "We'll see. I have to think about it";
        options[2] = "Yea right, I'll just hand over a probably valuable artefact..";
        Link b1 = new Link(text, options, linksA);

        options = new String[3];
        text = "Actually it is my business. But I will make you a deal. If you happen to come across an artefact, bring it to me and I will reward you very generously.\n" +
                "I wouldn't suggest keeping it, I doubt you will like the consequences..";
        options[0] = "Sounds good";
        options[1] = "We'll see. I have to think about it";
        options[2] = "Yea right, I'll just hand over a probably valuable artefact..";
        Link b4 = new Link(text, options, linksA);

        options = new String[3];
        Link[] linksB = {b1, b2, b4};
        text = "Charming, Have you heard about any unusual events recently? Unusually aggressive wildlife, \n" +
                "natural disasters or something similar? ";
        options[0] = "Well, now that you mention it the animals here have become really aggressive lately.";
        options[1] = "No, things are pretty much the same as always.";
        options[2] = "That is none of your business.";
        Link a2 = new Link(text, options, linksB);

        options = new String[3];
        Link[] linksC = {b1,b2,b3};
        text = "*Sigh* Let's skip the pleasantries. Have you heard about any unusual events recently?\n"
                + "Unusually aggressive wildlife, natural disasters or something similar? ";
        options[0] = "Well, now that you mention it the animals here have become really aggressive lately.";
        options[1] = "No, things are pretty much the same as always.";
        options[2] = "Well, the sky turned green yesterday and I did see some flying elephants...";
        Link a1 = new Link(text, options, linksC);

        options = new String[3];
        Link[] linksD = {a1,a2,a3};
        text = "Hello, \nyou look like you are a local here *lets hope you're more useful than the others*. \n" +
                "Can you help me with something? ";
        options[0] = "Sure friend, what do you need?";
        options[1] = "Depends, what do you want?";
        options[2] = "I don't have time for this. Get lost.";
        Link start = new Link(text, options, linksD);

        return start;
    }

    public Link secondConversation(){
        String text;
        String[] options = new String[3];
        Link[] links = new Link[3];
        text = "One problem, I don't know where that skeleton went. But I believe there is an old hermit in " +
                "the desert a little west of here who is supposedly an expert in weird stuff. \nLet's go talk to him.";
        Link end1 = new Link(text,false, false);

        text = "I believe there is an old hermit in " +
                "the desert a little west of here who is supposedly an expert in weird stuff. \nLet's go talk to him.";
        Link end2 = new Link(text,false, false);

        text = "To sell it, I first have to know what it is. I believe there is an old hermit in " +
                "the desert a little west of here who is supposedly an expert in weird stuff. \nLet's go talk to him.";
        Link end3 = new Link(text,false, false);

        links = new Link[3];
        text = "If there really is something going on here I want to know exactly what it is, why it's happening and maybe\n" +
                "I can do something about it. \n" +
                "Let's see, that wolf was the strangest monster yet, so if the artefact exists, it has to be somewhere around here.\n" +
                "*Got something* This must be it!\nOkay, I found the artefact. Now what do I do?";
        options[0] = "I should find that skeleton and give it to him, he seemed like he knew what he was talking about.";
        options[1] = "I'll keep it with me for now, but i really should find someone who knows more about this thing.";
        options[2] = "This might be worth something. Time to find a buyer.";
        links[0] = end1;
        links[1] = end2;
        links[2] = end3;
        Link a1 = new Link(text, options, links);

        text = "If there really is something going on here I should probable find out what it is. I can always walk away later.\n" +
                "Let's see, that wolf was the strangest monster yet, so if the artefact exists, it has to be somewhere around here.\n" +
                "*Got something* This must be it!\nOkay, I found the artefact. Now what do I do?";
        options[0] = "I should find that skeleton and give it to him, he seemed like he knew what he was talking about.";
        options[1] = "I'll keep it with me for now, but i really should find someone who knows more about this thing.";
        options[2] = "This might be worth something. Time to find a buyer.";
        links[0] = end1;
        links[1] = end2;
        links[2] = end3;
        Link a2 = new Link(text, options, links);

        text = "This is all way too weird and too dangerous! I'm going to forget anything happend, it's time to go home!\n" +
                "*BAM* Ouch, stupid forest. I'm outside for 5 minutes and already tripping over things. Hey, wait a second.\n" +
                "This must be that artefact the skeleton was talking about!\nOkay, I found the artefact. Now what do I do?";
        options[0] = "I should find that skeleton and give it to him, he seemed like he knew what he was talking about.";
        options[1] = "I'll keep it with me for now, but i really should find someone who knows more about this thing.";
        options[2] = "This might be worth something. Time to find a buyer.";
        links[0] = end1;
        links[1] = end2;
        links[2] = end3;
        Link a3 = new Link(text, options, links);

        links = new Link[3];
        options = new String[3];
        text = "Whew, that was one tough fight. And one really weird wolf. Wolves aren't supposed to act that way right? \n" +
                "It seems the whole world has gone crazy. Maybe that floating skeleton was actually telling the truth. \n" +
                "Maybe all these weird events aren't random, they could be caused by something. But do I really want to get involved with this?";
        options[0] = "Hell yes!";
        options[1] = "It can't hurt to find out more.";
        options[2] = "No way, I'm out of here.";
        links[0] = a1;
        links[1] = a2;
        links[2] = a3;
        Link start = new Link(text, options, links);

        return start;
    }

    public Conversation getConversation(int index){
        return conversations.get(index);
    }
}
