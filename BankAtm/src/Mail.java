import java.util.Properties;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mail extends CreatePDF{
	Mail m;
     

public void send(String em) {

    
    
    
	final String username = "bishalpurkuti197@gmail.com";
	final String password = "nepaldeshkada";
	String fromEmail ="bishalpurkuti197@gmail.com";
	String toEmail = "em";
	
	Properties properties = new Properties();
	properties.put("mail.smtp.auth", "true");
	properties.put("mail.smtp.starttls.enable", "true");
	properties.put("mail.smtp.host", "smtp.gmail.com");
	properties.put("mail.smtp.port", "587");
	
	Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username,password);
		}
	});
	//Start our mail message
	MimeMessage msg = new MimeMessage(session);
	try {
		msg.setFrom(new InternetAddress(fromEmail));
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
		msg.setSubject("Dear Customer please open the message to get your monthly statement!!!");
		
		Multipart emailContent = new MimeMultipart();
		
		//Text body part
		MimeBodyPart textBodyPart = new MimeBodyPart();
		textBodyPart.setText("Monthly  Bank statement prepared by VISHAL PURKUTI ");
		
		//Attachment body part.
		MimeBodyPart pdfAttachment = new MimeBodyPart();
		pdfAttachment.attachFile("C:\\Users\\Vishal\\eclipse-workspace\\BankAtm\\Statementfinall.pdf");
		
		//Attach body parts
		emailContent.addBodyPart(textBodyPart);
		emailContent.addBodyPart(pdfAttachment);
		
		//Attach multipart to message
		msg.setContent(emailContent);
		
		Transport.send(msg);
		System.out.println("Message has been sent to gmail .");
		

	} catch (Exception e)
	{
		e.printStackTrace();
	}
}



}

