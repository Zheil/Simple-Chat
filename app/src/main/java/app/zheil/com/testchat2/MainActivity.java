package app.zheil.com.testchat2;


import android.graphics.Color;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.bassaer.chatmessageview.model.Message;
import com.github.bassaer.chatmessageview.view.ChatView;


import app.zheil.com.testchat2.CoreDialog.ControllerDialog;

import app.zheil.com.testchat2.CoreDialog.iResponsable;

public class MainActivity extends AppCompatActivity implements iResponsable {

    private ChatView mChatView;
    private ControllerDialog mContrller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mChatView = findViewById(R.id.chat_view);
        mContrller = new ControllerDialog(false, this,this);

        setUi();
        setListenersDialog();


    }


    @Override
    public void userMessage(Message message) {
        //Set to chat view
        mChatView.send(message);

        //Reset edit text
        mChatView.setInputText("");
    }

    @Override
    public void botMessage(Message message) {
        mChatView.receive(message);
    }

    /*

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
                mChatView.receive(receivedMessage);
            }
        }, sendDelay);
    }
*/


    private int mNumberDialog = 0;

    private boolean getTurnTalk() {
        return mNumberDialog == 0;
    }


   /* private User changeDialog() {
        if(mNumberDialog == 0) {
            mNumberDialog = 1;
            return mChatCurrentUser;
        } else {
            mNumberDialog = 0;
            return mChatCurrentUser2;
        }
    }*/

    private void setListenersDialog() {
        //Click Send Button
        mChatView.setOnClickSendButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContrller.sendUserMessage(mChatView.getInputText());

               /* if(isUserBot) {
                    userMessage(mChatCurrentUser, true);
                    botReceived();
                } else {
                    Boolean turnTalk = getTurnTalk();
                    userMessage(changeDialog(), turnTalk);
                }*/

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


}