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
        conversations.add(new Conversation ("Wise man", R.drawable.desert_boss ,thirdConversation(), enemyLib.getBoss(1)));
    }

    public Link firstConversation(){
        String text;

        text = "Good, I'll see you around. Remember, I will pay good money if you find any strange artifacts in this region!";
        Link c1 = new Link(text, false, true);

        text = "Keeping the artifact will have very serious consequences. Bring it to me!";
        Link c2 = new Link(text, false, false);

        text = "How dare you speak to me that way! Do not cross me - you will regret this! ";
        Link a3 = new Link(text, true, false);

        String[] options = new String[3];
        Link[] linksA = {c1, c2, c2};
        text = "Insolent fool, I don't have time to play games. \nIf you happen to come across an artifact, bring it to me and I will reward you very generously.\n" +
                "I wouldn't suggest keeping it, I doubt you would like the consequences of that...";
        options[0] = "Sounds good!";
        options[1] = "We'll see. I have to think about it.";
        options[2] = "Yeah right, I'll just hand over a probably valuable artifact... You think I'm stupid?";
        Link b3 = new Link(text, options, linksA);

        options = new String[3];
        text = "Hmm, that's too bad. Well, if you happen to come across an artifact, bring it to me and I will reward you very generously.";
        options[0] = "Sounds good!";
        options[1] = "We'll see. I have to think about it.";
        options[2] = "Yeah right, I'll just hand over a probably valuable artifact... You think I'm stupid?";
        Link b2 = new Link(text, options, linksA);

        options = new String[3];
        text = "Interesting, the artifact must be here *evil laugh*. I have a proposal for you. \nShould you find a strange artifact" +
                " somewhere in the area, bring it to me and I will reward you very generously.";
        options[0] = "Sounds good!";
        options[1] = "We'll see. I have to think about it.";
        options[2] = "Yeah right, I'll just hand over a probably valuable artifact... You think I'm stupid?";
        Link b1 = new Link(text, options, linksA);

        options = new String[3];
        text = "Actually, it is my business. But I will make you a deal. If you happen to come across an artifact, bring it to me and I will reward you very generously.\n" +
                "I wouldn't suggest keeping it, I doubt you would like the consequences of that...";
        options[0] = "Sounds good!";
        options[1] = "We'll see. I have to think about it.";
        options[2] = "Yeah right, I'll just hand over a probably valuable artifact... You think I'm stupid?";
        Link b4 = new Link(text, options, linksA);

        options = new String[3];
        Link[] linksB = {b1, b2, b4};
        text = "Charming.\n Have you heard about any unusual events recently? Unusually aggressive wildlife, \n" +
                "natural disasters or something similar? ";
        options[0] = "Well, now that you mention it, the animals here have become really aggressive lately.";
        options[1] = "No, things are pretty much the same as always.";
        options[2] = "That is none of your business.";
        Link a2 = new Link(text, options, linksB);

        options = new String[3];
        Link[] linksC = {b1,b2,b3};
        text = "*Sigh* Let's skip the pleasantries. Have you heard about any unusual events recently?\n"
                + "Unusually aggressive wildlife, natural disasters or something similar? ";
        options[0] = "Well, now that you mention it, the animals here have become really aggressive lately.";
        options[1] = "No, things are pretty much the same as always.";
        options[2] = "Well, the sky turned green yesterday and I did see some flying elephants...";
        Link a1 = new Link(text, options, linksC);

        options = new String[3];
        Link[] linksD = {a1,a2,a3};
        text = "Hello. \nYou look like you are a local here. Let's hope you're more useful than the last one. \n" +
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
                "I'll be able to do something about it. \n" +
                "Let's see, that wolf was the strangest monster yet, so if the artifact exists, it has to be somewhere around here.\n\n" +
                "*Comes across a strange jewel* \nThis must be it!\nOkay, I found the artifact. Now what do I do?";
        options[0] = "I should find that skeleton and give it to him, he seemed like he knew what he was talking about.";
        options[1] = "I'll keep it with me for now, but I really should find someone who knows more about this thing.";
        options[2] = "This might be worth something. Time to find a buyer.";
        links[0] = end1;
        links[1] = end2;
        links[2] = end3;
        Link a1 = new Link(text, options, links);

        text = "If there really is something going on here I should probably find out what it is. I can always walk away later.\n" +
                "Let's see, that wolf was the strangest monster yet, so if the artifact exists, it has to be somewhere around here.\n\n" +
                "*Comes across a strange jewel* \nThis must be it!\nOkay, I found the artifact. Now what do I do?";
        options[0] = "I should find that skeleton and give it to him, he seemed like he knew what he was talking about.";
        options[1] = "I'll keep it with me for now, but I really should find someone who knows more about this thing.";
        options[2] = "This might be worth something. Time to find a buyer.";
        links[0] = end1;
        links[1] = end2;
        links[2] = end3;
        Link a2 = new Link(text, options, links);

        text = "This is all way too weird and too dangerous! I'm going to forget anything happened, it's time to go home!\n" +
                "*BAM* \nOuch, stupid forest. I'm outside for 5 minutes and already tripping over things. ...Hey, wait a second.\n" +
                "This must be that artifact the skeleton was talking about!\nOkay, I found the artifact. Now what do I do?";
        options[0] = "I should find that skeleton and give it to him, he seemed like he knew what he was talking about.";
        options[1] = "I'll keep it with me for now, but I really should find someone who knows more about this thing.";
        options[2] = "This might be worth something. Time to find a buyer.";
        links[0] = end1;
        links[1] = end2;
        links[2] = end3;
        Link a3 = new Link(text, options, links);

        links = new Link[3];
        options = new String[3];
        text = "Whew, that was one tough fight. And one really weird wolf. Wolves aren't supposed to act that way. ...Right? \n" +
                "It seems the whole world has gone crazy. Maybe that floating skeleton was actually telling the truth. \n" +
                "Maybe all these weird events aren't random, they could be caused by something. But do I really want to get involved in this?";
        options[0] = "Hell yes!";
        options[1] = "It can't hurt to find out more.";
        options[2] = "No way, I'm out of here.";
        links[0] = a1;
        links[1] = a2;
        links[2] = a3;
        Link start = new Link(text, options, links);

        return start;
    }

    public Link thirdConversation(){
        String text;

        text = "You have to go defeat the Demon. His lair is north of here. You are our only hope!";
        Link c2 = new Link(text, false, false);

        text = "That means the artifact is controlling them already! You must go and stop the Demon - he won't rest until he has the artifact.";
        Link a3 = new Link(text, true, false);

        String[] options = new String[3];
        Link[] linksA = {c2, a3};


        options = new String[1];
        text = "What you do or do not want doesn't matter. The Demon is here, and he won't stop until he has the artifact.\n You are our only hope.";
        options[0] = "Alright, here I go.";
        Link b1 = new Link(text, options, linksA);

        options = new String[2];
        Link[] linksB = {b1, c2};
        text = "*Gasps* Oh no! That is the Demon!\nIf he gets his hands on it, he will be able to control the animals and enslave the country!"
                + "You have beaten me. That means you might be able to defeat him.";
        options[0] = "To be honest, I really don't feel like battling an all-powerful demon....";
        options[1] = "Okay. I will try. Do you know where his lair is?";
        Link a2 = new Link(text, options, linksB);

        options = new String[3];
        Link[] linksC = {a3,a2,c2};
        text = "This artifact is invaluable. Selling it will make you rich beyond belief - and possibly end life as you know it.\n"
                + "If the Demon gets his hands on it, he will be able to control the animals and enslave the country!";
        options[0] = "Well, now that you mention it, the animals here have become really aggressive lately.";
        options[1] = "The Demon? Does he happen to look like a floating skeleton, by any chance?.";
        options[2] = "We have to prevent that! What do I do?";
        Link a1 = new Link(text, options, linksC);

        options = new String[3];
        Link[] linksD = {a1,a2,a3};
        text = "Good, you've beaten me and earned the right to know what this jewel is and why you are here. \nWhat do you want to know first?";
        options[0] = "I want to know the value of it! Can I sell it?";
        options[1] = "Some floating skeleton was asking after it. Do you know anything about him?";
        options[2] = "What does it do? Why are all the animals acting so crazy?";
        Link start = new Link(text, options, linksD);

        return start;
    }

    public Conversation getConversation(int index){
        return conversations.get(index);
    }
}
