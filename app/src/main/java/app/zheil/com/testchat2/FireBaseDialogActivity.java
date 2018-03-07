package app.zheil.com.testchat2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.github.bassaer.chatmessageview.model.Message;
import com.github.bassaer.chatmessageview.view.ChatView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import app.zheil.com.testchat2.CoreDialog.ControllerDialog;
import app.zheil.com.testchat2.CoreDialog.MessageFireBase;
import app.zheil.com.testchat2.CoreDialog.iResponsable;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Zheil on 07.03.2018.
 */

public class FireBaseDialogActivity extends AppCompatActivity implements iResponsable {


    ChatView mChatView;

    private DatabaseReference mFireRef;
    private ControllerDialog mController;

    private String mUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_firebase);
        mChatView = findViewById(R.id.chat_view_fireBase);
        setUi();
        setListenersDialog();
        setDateFromIntent();

        mController = new ControllerDialog(false, this,this);
        mFireRef = FirebaseDatabase.getInstance().getReference().child("MyChat");
        displayMessage();
    }


    private void setListenersDialog() {
        mChatView.setOnClickSendButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(mChatView.getInputText());
            }
        });
    }

    @Override
    public void userMessage(Message message) {
        mChatView.send(message);

        mChatView.setInputText("");
    }

    @Override
    protected void onStop() {
        super.onStop();
        //AuthUI.getInstance().signOut(this);
    }

    @Override
    public void botMessage(Message message) {

    }

    private void setDateFromIntent() {
        Intent intent = getIntent();
        mUserName = intent.getStringExtra("userName");
    }

    public void sendMessage(String message) {
        mFireRef.push().setValue(
                new MessageFireBase(message, mUserName));

    }


    private int oldIndex = 0;
    private void addMessageToController() {
        Boolean isCurrentUserRightPosition;

        for (int i = oldIndex; i < mListMessage.size(); i++, oldIndex++) {
            if(mUserName.toLowerCase().equals(mListMessage.get(i).getAutor().toLowerCase())) {
                isCurrentUserRightPosition = true;
            } else {
                isCurrentUserRightPosition = false;
            }

            mController.sendUserMessage(
                    mListMessage.get(i).getTextMessage(),
                    mListMessage.get(i).getAutor(),
                    isCurrentUserRightPosition);

        }
        //mListMessage.clear();

    }


    List<MessageFireBase> mListMessage = new ArrayList<>();
    private void displayMessage() {
        mFireRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mListMessage.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    mListMessage.add(snapshot.getValue(MessageFireBase.class));

                   // Log.d("MYLOG", mListMessage.get(mListMessage.size()-1).getTextMessage() + " | "
                         //   + mListMessage.get(mListMessage.size()-1).getAutor());
                }
                addMessageToController();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


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