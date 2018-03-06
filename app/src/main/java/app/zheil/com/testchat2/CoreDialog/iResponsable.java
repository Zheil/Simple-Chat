package app.zheil.com.testchat2.CoreDialog;

import com.github.bassaer.chatmessageview.model.Message;

/**
 * Created by Zheil on 06.03.2018.
 */

public interface iResponsable {
    void userMessage(Message message);
    void botMessage(Message message);
}
