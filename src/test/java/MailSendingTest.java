import com.DigitalTwin.project.utils.MailUtils;
import org.junit.jupiter.api.Test;

public class MailSendingTest {

    @Test
    public void sendMail() {
        try {
            MailUtils.sendMail("1109896198@qq.com", "6458");//填写接收邮箱※
            System.out.println("发送成功");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}
