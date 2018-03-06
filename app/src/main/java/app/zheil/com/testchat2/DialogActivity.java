package app.zheil.com.testchat2;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.github.bassaer.chatmessageview.model.Message;
import com.github.bassaer.chatmessageview.view.ChatView;
import app.zheil.com.testchat2.CoreDialog.ControllerDialog;
import app.zheil.com.testchat2.CoreDialog.iResponsable;

/**
 * Активити диалога. Диалог может проходить в двух режимах - с человеком или с ботом
 */

public class DialogActivity extends AppCompatActivity implements iResponsable {

    private ChatView mChatView;
    private ControllerDialog mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        Boolean isBot = getTypeChatFromIntent();

        mChatView = findViewById(R.id.chat_view);
        mController = new ControllerDialog(isBot, this,this);
        setUi();
        setListenersDialog();
    }

    private boolean getTypeChatFromIntent() {
        Intent intent = getIntent();
        return intent.getBooleanExtra("isBot", false);
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


    private void setListenersDialog() {
        //Click Send Button
        mChatView.setOnClickSendButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mController.sendUserMessage(mChatView.getInputText());
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