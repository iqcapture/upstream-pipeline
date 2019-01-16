package mil.navy.navfac.nitep.utility;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.core.env.Environment;

public class MailSender {    
    public String from = "noreply@nitc.navy.mil";
    public String to;
    public String recipients;
    public String subject;
    public String content;
    public String contentHtml;
    public String contentType = "text/plain";  // text/html, text/plain
    public String attach;
    private Environment env;
    
    public MailSender(Environment envIn) {
    	this.env = envIn;
    }
    
    public void sendMailMessage() throws AddressException, MessagingException, IOException {
  	   Session session = null;
  	   Properties props = new Properties();

  	   System.out.println("MailSender::sendMailMessage preparing props...");

 	   props.put("mail.smtp.auth", env.getProperty("mail.smtp.auth"));
 	   props.put("mail.smtp.starttls.enable", env.getProperty("mail.smtp.starttls.enable"));
 	   props.put("mail.smtp.host", env.getProperty("mail.smtp.host"));
 	   props.put("mail.smtp.port", env.getProperty("mail.smtp.port"));

  	   System.out.println("MailSender::sendMailMessage props -> auth: " + env.getProperty("mail.smtp.auth"));
  	   System.out.println("MailSender::sendMailMessage props -> starttls: " + env.getProperty("mail.smtp.starttls.enable"));
  	   System.out.println("MailSender::sendMailMessage props -> host: " + env.getProperty("mail.smtp.host"));
  	   System.out.println("MailSender::sendMailMessage props -> port: " + env.getProperty("mail.smtp.port"));

 	   
 	   // Init session
 	   if (env.getProperty("mail.smtp.auth").equals("true")) {
     	   session = Session.getInstance(props, new javax.mail.Authenticator() {
     	      protected PasswordAuthentication getPasswordAuthentication() {
     	         return new PasswordAuthentication(env.getProperty("mail.smtp.auth.email"), env.getProperty("mail.smtp.auth.token"));
     	      }
     	   });
 	   } else {	   
 		   session = Session.getDefaultInstance(props);
 	   }
 	   
 	   // Prepare message
 	   Message msg = new MimeMessage(session);
 	   msg.setFrom(new InternetAddress(this.from, false));
 	   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.to));
 	   msg.setSubject(this.subject);
 	   
 	   // set message to text or html ("text/plain", "text/html")
 	   msg.setContent(this.content, this.contentType);
 	   msg.setSentDate(new Date());	   
 	   Transport.send(msg);   
 	}
    
    public void sendMailAttachment() throws AddressException, MessagingException, IOException {
   	   Session session = null;
  	   Properties props = new Properties();
  	   
  	   props.put("mail.smtp.auth", env.getProperty("mail.smtp.auth"));
  	   props.put("mail.smtp.starttls.enable", env.getProperty("mail.smtp.starttls.enable"));
  	   props.put("mail.smtp.host", env.getProperty("mail.smtp.host"));
  	   props.put("mail.smtp.port", env.getProperty("mail.smtp.port"));
  	   
  	   //System.out.println("MailSender::sendMailAttachment props -> auth: " + env.getProperty("mail.smtp.auth"));
  	   //System.out.println("MailSender::sendMailAttachment props -> starttls: " + env.getProperty("mail.smtp.starttls.enable"));
  	   //System.out.println("MailSender::sendMailAttachment props -> host: " + env.getProperty("mail.smtp.host"));
  	   //System.out.println("MailSender::sendMailAttachment props -> port: " + env.getProperty("mail.smtp.port"));
  	   
  	   // Init sesssion
  	   if (env.getProperty("mail.smtp.auth").equals("true")) {
 	    	   session = Session.getInstance(props, new javax.mail.Authenticator() {
 	    	      protected PasswordAuthentication getPasswordAuthentication() {
 	    	         return new PasswordAuthentication(env.getProperty("mail.smtp.auth.email"), env.getProperty("mail.smtp.auth.token"));
 	    	      }
 	    	   });
  	   } else {	   
  		   session = Session.getDefaultInstance(props);
  	   }
  	    
  	   // Prepare message
  	   Message msg = new MimeMessage(session);
  	   msg.setFrom(new InternetAddress(this.from, false));
  	   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.to));
  	   msg.setSubject(this.subject);
  	   msg.setSentDate(new Date());
  	   
  	   // For MIME attachment, construct a Mime body and parts
 	   MimeBodyPart messageBodyPart = new MimeBodyPart();
 	   messageBodyPart.setContent(this.contentHtml, "text/html");

 	   Multipart multipart = new MimeMultipart();
 	   multipart.addBodyPart(messageBodyPart);
 	   MimeBodyPart attachPart = new MimeBodyPart();

 	   attachPart.attachFile(this.attach);
 	   multipart.addBodyPart(attachPart);
 	   msg.setContent(multipart);
  	   
  	   Transport.send(msg);   
  	}
}
