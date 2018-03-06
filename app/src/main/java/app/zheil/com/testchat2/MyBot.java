package app.zheil.com.testchat2;

import java.util.Random;

public class MyBot {
    private String mBotName;
    private String mUserName;
    private Random mRandom;
    private String[] words;

    public MyBot(String botName, String userName) {
        this.mBotName = botName;
        this.mUserName = userName;

        words = new String[]{"Hello " + mUserName + " !", "How do you do?", "I want to eat!", "How did you find me?", "^_^ ok)", "I'm " + mBotName};
    }

    public String getMessage() {
        mRandom = new Random();
        int rndIndex = mRandom.nextInt(words.length);
        return words[rndIndex];
    }
}