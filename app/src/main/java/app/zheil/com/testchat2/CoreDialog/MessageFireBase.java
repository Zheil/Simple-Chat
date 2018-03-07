package app.zheil.com.testchat2.CoreDialog;

/**
 * Модель сообщения, содержит информацию, необходимую для диалога
 * Используется в FireBase
 */
import java.util.Date;
public class MessageFireBase {
        private String textMessage;
        private String autor;
        private long timeMessage;

        public MessageFireBase(String textMessage, String autor) {
            this.textMessage = textMessage;
            this.autor = autor;

            timeMessage = new Date().getTime();
        }

        public MessageFireBase() {

        }

        public String getTextMessage() {
            return textMessage;
        }

        public void setTextMessage(String textMessage) {
            this.textMessage = textMessage;
        }

        public String getAutor() {
            return autor;
        }

        public void setAutor(String autor) {
            this.autor = autor;
        }

        public long getTimeMessage() {
            return timeMessage;
        }

        public void setTimeMessage(long timeMessage) {
            this.timeMessage = timeMessage;
        }
}
