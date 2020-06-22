package utiles;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;

public class SendHtmlEmail {
    private static String username = "cristiangallo@gmail.com";
    private static String password = "tkGue743";
    private static Boolean debug = true;

    public static void send (String to, String subject, String htmlMsg) {
        HtmlEmail email = new HtmlEmail();
        email.setHostName("smtp.gmail.com");
        System.out.println(to);
        System.out.println(subject);
        System.out.println(htmlMsg);
        email.setAuthentication(username, password);
        email.setDebug(debug);
        email.setSmtpPort(465);
        email.setSSLOnConnect(true);
        MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
        mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
        mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
        mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
        mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
        mc.addMailcap("message/rfc822;; x-java-content- handler=com.sun.mail.handlers.message_rfc822");

        try {
            email.setHtmlMsg(htmlMsg);
            email.setTextMsg("Este es un mensaje importante con contenido HTML y tu cliente de correo electrónico no " +
                    "es compatible con HTML.");
            email.addTo(to);
            email.setFrom(username, "jCable");
            email.setSubject(subject);
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        send("cristiangallo@hotmail.com", "acá va el asunto", "<html>Hola cristian , haz <a href='http://127.0.0.1:8080/activar-usuario'>click aqui</a> " +
                    "para activar tu perfil en jCable.</html>");


    }
}
