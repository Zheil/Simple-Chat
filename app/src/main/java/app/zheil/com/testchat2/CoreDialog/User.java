package app.zheil.com.testchat2.CoreDialog;

import android.graphics.Bitmap;
import com.github.bassaer.chatmessageview.model.IChatUser;
import org.jetbrains.annotations.NotNull;

/**
 * Модель пользователя. Содержит информацию, необходимую для диалога
 */
class User implements IChatUser {
    private String mId;
    private Bitmap mIcon;
    private String nName;

    public User( String nName) {
        this.mId = "0";
        this.nName = nName;
        this.mIcon = null;
    }

    public User(String mId, String nName, Bitmap mIcon) {
        this.mId = mId;
        this.nName = nName;
        this.mIcon = mIcon;
    }

    public Bitmap getIcon() {
        return mIcon;
    }

    public String getName() {
        return nName;
    }


    @Override
    public void setIcon(Bitmap bitmap) {
        this.mIcon = bitmap;
    }


    @NotNull
    @Override
    public String getId() {
        return mId;
    }
}