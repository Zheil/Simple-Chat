package app.zheil.com.testchat2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Авторизация пользователя
 * */
public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.etLogin)
    EditText etLogin;

    @BindView(R.id.etPass)
    EditText etPass;

    private FirebaseAuth mFireAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mFireAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void btnOnClickLogin(View view) {
        signIn();
    }

    private void signIn() {
        try {
            mFireAuth.signInWithEmailAndPassword(etLogin.getText().toString(), etPass.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                success();
                            } else {
                                error();
                            }
                        }
                    });
        } catch (Exception e ) {
            e.printStackTrace();
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    public void success() {
        Intent intent = new Intent(this, FireBaseDialogActivity.class);
        intent.putExtra("userName", etLogin.getText().toString());
        startActivity(intent);
    }

    public void error() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }
}