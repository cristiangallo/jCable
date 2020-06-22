package utiles;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class SendHtmlEmail {
    private static String from = "cristiangallo@gmail.com";
    private static String password = "tkGue743";

    public static void send (String to, String subject, String HtmlMsg){
        HtmlEmail email = new HtmlEmail();
        email.setHostName("smtp.gmail.com");
        email.setAuthentication(from, password);
        email.setDebug(true);
        email.setSmtpPort(465);
        email.setSSLOnConnect(true);

        try {
            email.setHtmlMsg(HtmlMsg);
            email.setTextMsg("Este es un mensaje importante con contenido HTML y yu cliente de correo electrónico no " +
                    "es compatible con HTML.");
            email.addTo(to);
            email.setFrom(from, "jCable");
            email.setSubject(subject);
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        send("cristiangallo@gmail.com", "acá va el asunto", "<html>Test message with <b>HTML</b></html>");

    }
}
