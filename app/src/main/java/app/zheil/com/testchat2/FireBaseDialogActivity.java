package app.zheil.com.testchat2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import app.zheil.com.testchat2.CoreDialog.MessageFireBase;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Zheil on 07.03.2018.
 */

public class FireBaseDialogActivity extends AppCompatActivity{
    @BindView(R.id.etText)
    EditText mEtMessage;

    @BindView(R.id.tvLog)
    TextView tvLog;

    private DatabaseReference mFireRef;

    private String mUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setDateFromIntent();

        mFireRef = FirebaseDatabase.getInstance().getReference().child("MyChat");
        displayMessage();
    }



    private void setDateFromIntent() {
        Intent intent = getIntent();
        mUserName = intent.getStringExtra("userName");
    }

    public void btnOnClick_SendMessage(View view) {
        mFireRef.push().setValue(
                new MessageFireBase(mEtMessage.getText().toString(), mUserName));
        mEtMessage.setText("");
    }


    List<MessageFireBase> listMessage = new ArrayList<>();
    private void displayMessage() {
        mFireRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listMessage.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    listMessage.add(snapshot.getValue(MessageFireBase.class));
                    Log.d("MYLOG", listMessage.get(listMessage.size()-1).getTextMessage() + " | "
                            +listMessage.get(listMessage.size()-1).getAutor());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
