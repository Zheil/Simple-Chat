package app.zheil.com.testchat2.CoreDialog;

import com.github.bassaer.chatmessageview.model.Message;

public interface iResponsable {
    void userMessage(Message message);
    void botMessage(Message message);
}