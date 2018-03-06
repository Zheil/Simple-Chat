package app.zheil.com.testchat2.CoreDialog;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;

import com.github.bassaer.chatmessageview.model.Message;

import java.util.Random;

import app.zheil.com.testchat2.MyBot;
import app.zheil.com.testchat2.R;

/**
 * Created by Zheil on 06.03.2018.
 */

public class CoreDialog {
    private User mChatBot;
    private User mChatCurrentUser;
    private User mChatCurrentUser2;
    private MyBot myBot;
    private Boolean isUserBot;
    private Context mContext;
    private iController mController;
    private int mTurnUserDialog = 0;

    public CoreDialog(Boolean isUserBot, Context context, iController controller) {
        this.isUserBot = isUserBot;
        this.mContext = context;
        this.mController = controller;
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
           message = userMessage(userText, changeDialog(), turnTalk);
           mController.userMessage(message);
        }
    }

    private boolean getTurnTalk() {
        return  mTurnUserDialog == 0;
    }

     private User changeDialog() {
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
                //mChatView.receive(receivedMessage);
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
