package app.zheil.com.testchat2.CoreDialog;

/**
 * Created by Zheil on 07.03.2018.
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
