package app.zheil.com.testchat2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Активити для выбора типа диалога: с ботом или с человеком
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnOnClick_ShowUserChat(View view) {
        showChat(false);
    }

    public void btnOnClick_ShowBotChat(View view) {
        showChat(true);
    }

    private void showChat(Boolean isBot) {
        Intent intent = new Intent(this, DialogActivity.class);
        intent.putExtra("isBot", isBot);
        startActivity(intent);
    }
}