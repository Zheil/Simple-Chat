package app.zheil.com.testchat2.CoreDialog;

import java.util.Random;

/**
 * Модель бота. Класс отвечает за поведение бота в чате
 */

public class MyBot {
    private String mBotName;
    private String mUserName;
    private Random mRandom;
    private String[] mBotWords;

    public MyBot(String botName, String userName) {
        this.mBotName = botName;
        this.mUserName = userName;
        mBotWords = new String[]{"Hello " + mUserName + " !", "How do you do?", "I want to eat!", "How did you find me?", "^_^ ok)", "I'm " + mBotName};
    }

    public String getMessage() {
        mRandom = new Random();
        int rndIndex = mRandom.nextInt(mBotWords.length);
        return mBotWords[rndIndex];
    }
}