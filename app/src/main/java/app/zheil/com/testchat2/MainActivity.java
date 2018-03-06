package app.zheil.com.testchat2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.bassaer.chatmessageview.model.Message;
import com.github.bassaer.chatmessageview.view.ChatView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ChatView mChatView;
    private User mChatBot;
    private User mChatCurrentUser;
    private MyBot myBot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mChatView = findViewById(R.id.chat_view);

        configUsers();
        setUi();
        setListeners();
    }


    private void setListeners() {
        //Click Send Button
        mChatView.setOnClickSendButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //new message
                Message message = new Message.Builder()
                        .setUser(mChatCurrentUser)
                        .setRight(true)
                        .setText(mChatView.getInputText())
                        .hideIcon(true)
                        .build();
                //Set to chat view
                mChatView.send(message);

                //Reset edit text
                mChatView.setInputText("");

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
                        mChatView.receive(receivedMessage);
                    }
                }, sendDelay);
            }
        });
    }

    /***
     * Set UI parameters
     */
    private void setUi() {
        mChatView.setRightBubbleColor(ContextCompat.getColor(this, R.color.green500));
        mChatView.setLeftBubbleColor(Color.WHITE);
        mChatView.setBackgroundColor(ContextCompat.getColor(this, R.color.blueGray500));
        mChatView.setSendButtonColor(ContextCompat.getColor(this, R.color.cyan900));
        mChatView.setSendIcon(R.drawable.ic_action_send);
        mChatView.setRightMessageTextColor(Color.WHITE);
        mChatView.setLeftMessageTextColor(Color.BLACK);
        mChatView.setUsernameTextColor(Color.WHITE);
        mChatView.setSendTimeTextColor(Color.WHITE);
        mChatView.setDateSeparatorColor(Color.WHITE);
        mChatView.setInputTextHint("new message...");
        mChatView.setMessageMarginTop(5);
        mChatView.setMessageMarginBottom(5);
    }

    private void configUsers() {
        String botId = "0";
        Bitmap botIcon = BitmapFactory.decodeResource(getResources(), R.drawable.emmi);
        String botName = "Emmi";

        String userId = "1";
        Bitmap userIcon = BitmapFactory.decodeResource(getResources(), R.drawable.andrey);
        String userName = "Andrey";

        mChatBot = new User(botId, botName, botIcon);
        mChatCurrentUser = new User(userId, userName, userIcon);

        myBot = new MyBot(mChatBot.getName(), mChatCurrentUser.getName());
    }
}