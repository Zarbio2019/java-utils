package org.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/*
 * Send Email with and without attachments.
 */
public class EmailUtil
{
   public static void main(String[] args)
   {
	   // Configuration
	   String smtpServer = "smtp.gmail.com"; // gmail = smtp.gmail.com, hotmail = smtp.live.com, office 365 = smtp.office365.com, yahoo = smtp.mail.yahoo.com
       String port = "587"; // TLS = 587, SSL = 465
       String userName = "zarod2019";
       String password = "bananas@2020";
       String auth = "true";
       
       Properties properties = System.getProperties();
       properties.put("mail.smtp.host", smtpServer);
       properties.put("mail.smtp.port", port);
       properties.put("mail.smtp.starttls.enable", "true");
       properties.put("mail.smtp.auth", auth);
      
       Session session = null;
       
       if(auth.equals("true")) // with authentication
       {
	      session = Session.getInstance(properties, new javax.mail.Authenticator() {
	          protected PasswordAuthentication getPasswordAuthentication() {
	        	  return new PasswordAuthentication(userName, password);
	          }
	      });
       }
       else
       {
    	   session = Session.getDefaultInstance(properties, null);
    	   //session = Session.getInstance(properties);
       }

       session.setDebug(true);
      
       // Message
       Date dateNow = new Date();
       String date = new SimpleDateFormat("yyyy-MM-dd").format(dateNow);
		
       final String from = "zarod2019@gmail.com";
       final String[] to = ("zarod2019@gmail.com;test2020@hotmail.com").split(";");
       final String[] cc = ("emailcc1@gmail.com;emailcc2@hotmail.com").split(";");
       final String[] bcc = null;
       final String subject = "Carga automática de archivos - Correctos" + " - Fecha Proceso: " + date;
       final String content = "Proceso de Carga archivos.";
       
       final String attach1 = new File("files\\text1.txt").getAbsolutePath();
       final String attach2 = new File("files\\canary.jpg").getAbsolutePath();
       final String attach3 = new File("files\\excel1.xlsx").getAbsolutePath();
       final String[] attachments = (attach1 + ";" + attach2 + ";" + attach3).split(";");
       
      try {

    	  MimeMessage message = new MimeMessage(session);
    	  message.setSubject(subject);
    	      	  
    	  if(!attachments.equals(null) && attachments[0]!="")
    	  {
	    	  // Attachments
	    	  MimeMultipart multiParte = new MimeMultipart();
	    	  BodyPart bodyPart = new MimeBodyPart();
	    	  bodyPart.setContent(content, "text/html;charset=UTF-8");
	    	  multiParte.addBodyPart(bodyPart);
				
	    	  for(String att: attachments) {
	    		  File fileName = new File(att);
	
	    		  BodyPart attach = new MimeBodyPart();
					            
	    		  DataSource source = new FileDataSource(att); 
	    		  attach.setDataHandler(new DataHandler(source)); 
					
	    		  attach.setFileName(fileName.getName());
		          multiParte.addBodyPart(attach);
	    	  }
	    	  
	    	  message.setContent(multiParte);// for a html email
    	  }
    	  else
    	  {
    		  //message.setText("This is actual message");
    		  message.setContent("This is actual message", "text/html");  
    	  }
    	  
          for (int i = 0; i < to.length; i++) {
        	  message.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
          }
			
          // Send
          message.setSentDate(new Date());
          message.setFrom(new InternetAddress(from, false));
          //message.setFrom(new InternetAddress(from));
          
          System.out.println("sending...");
          Transport.send(message);
          System.out.println("Sent message successfully....");
      } catch (MessagingException ex) {
          ex.printStackTrace();
      }
   }
}