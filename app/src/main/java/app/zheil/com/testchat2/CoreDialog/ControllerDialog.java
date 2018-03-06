package app.zheil.com.testchat2.CoreDialog;

import android.content.Context;
import com.github.bassaer.chatmessageview.model.Message;

/**
 * Контроллер для получения запросов из активити, пересылки их в логику и возврат результата.
 */

public class ControllerDialog implements iResponsable{
    private CoreDialog mCore;
    private iResponsable mRespons;

    public ControllerDialog(Boolean isBot, Context context, iResponsable responsable) {
        this.mRespons = responsable;
        mCore = new CoreDialog(isBot, context, this);
    }

    public void sendUserMessage(String userText) {
        mCore.sendUserMessage(userText);
    }

    @Override
    public void userMessage(Message message) {
        mRespons.userMessage(message);
    }

    @Override
    public void botMessage(Message message) {
        mRespons.botMessage(message);
    }
}