package app.zheil.com.testchat2.CoreDialog;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import com.github.bassaer.chatmessageview.model.Message;
import java.util.Random;
import app.zheil.com.testchat2.R;

/**
 * Основная логика работы чата
 */

public class CoreDialog {
    private User mChatBot;
    private User mChatCurrentUser;
    private User mChatCurrentUser2;
    private MyBot myBot;
    private Boolean isUserBot;
    private Context mContext;
    private iResponsable mController;
    private int mTurnUserDialog;

    public CoreDialog(Boolean isUserBot, Context context, iResponsable controller) {
        this.isUserBot = isUserBot;
        this.mContext = context;
        this.mController = controller;
        mTurnUserDialog = 0;
        configUsers();
    }

    public void sendUserMessage(String userText) {
        Message message;
        if(isUserBot) {
             message =  userMessage(userText, mChatCurrentUser, true);

            mController.userMessage(message);
            botReceived();
        } else {
            Boolean turnTalk = getTurnTalk();
           message = userMessage(userText, getCurrentUserAndChangeDialog(), turnTalk);
           mController.userMessage(message);
        }
    }

    public void sendUserMessage(String userText, String author, Boolean isRightCurrentUser) {
        //sendUserMessage(userText);
        mController.userMessage(userMessage(userText, new User(author), isRightCurrentUser));
    }

    private boolean getTurnTalk() {
        return  mTurnUserDialog == 0;
    }

     private User getCurrentUserAndChangeDialog() {
       if( mTurnUserDialog == 0) {
           mTurnUserDialog = 1;
           return mChatCurrentUser;
       } else {
           mTurnUserDialog = 0;
           return mChatCurrentUser2;
       }
   }

    private void botReceived() {
        //Receive message
        final Message receivedMessage = new Message.Builder()
                .setUser(mChatBot)
                .setRight(false)
                .setText(myBot.getMessage())
                .build();

        // This is a demo bot
        // Return within 3 seconds
        int sendDelay = (new Random().nextInt(4) + 1) * 1000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mController.botMessage(receivedMessage);
            }
        }, sendDelay);
    }


    private Message userMessage(String text, User user, boolean isRight) {
        return buildMessage(text, user, isRight);
    }

    private Message buildMessage(String text, User user, boolean isRight) {
        Message message = new Message.Builder()
                .setUser(user)
                .setRight(isRight)
                .setText(text)
                .hideIcon(true)
                .build();
        return message;
    }

    /**
     * Конфигурация пользователей чата
     */
    private void configUsers() {
        String botId = "0";
        Bitmap botIcon = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.emmi);
        String botName = "Emmi";

        String userId = "1";
        Bitmap userIcon = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.andrey);
        String userName = "Andrey";

        mChatBot = new User(botId, botName, botIcon);
        mChatCurrentUser = new User(userId, userName, userIcon);
        mChatCurrentUser2 = new User("3", "Никита", BitmapFactory.decodeResource(mContext.getResources(), R.drawable.nikita));

        myBot = new MyBot(mChatBot.getName(), mChatCurrentUser.getName());
    }


}