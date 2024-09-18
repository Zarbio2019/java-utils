package org.util.bck;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmailUtil1 {
	
	protected final static Logger logger = LogManager.getLogger();
	private static final String NROSOLICITUD ="{nroSolicitud}"; 
	private static final String CODCLIENTE ="{codCliente}"; 
	private static final String NOMBCLIENTE ="{nombCliente}"; 
	private static final String NOMBPROCESO ="{nombProceso}"; 
	private static final String IMPORTE ="{importe}"; 
	private static final String MONEDA ="{moneda}"; 
	private static final String CAMPOIMPORTE ="{campoImporte}"; 
	private static final String CAMPOMONEDA ="{campoMoneda}"; 

	public static boolean EnviarMensajeDevolver(
			String strServidorCorreo, String strPuerto, String strFrom, ArrayList<String> strTo, 
			ArrayList<String> strCopia, String strAsunto, String strBody){
		
		boolean esCorrecto = true;
		
		try {
			Properties props = new Properties();

        	props.setProperty("mail.smtp.host", strServidorCorreo);
        	props.setProperty("mail.transport.protocol", "smtp");
            props.setProperty("mail.smtp.host", strServidorCorreo);
            props.setProperty("mail.smtp.port", strPuerto);
            logger.info("Propiedades :: " + props);

        	Session session= Session.getDefaultInstance(props,null);
        	MimeMessage mmMail = new MimeMessage(session);
        	
        	// Quien envia
        	mmMail.setFrom(new InternetAddress(setFormatEmail(strFrom)+".pe"));
        	logger.info("Correo De :: " + strFrom);
        	
        	// Quien recibe
        	if(strTo!=null && !strTo.isEmpty()){
        		for (int i = 0; i < strTo.size(); i++) {
        			String toAddrs = setFormatEmail(strTo.get(i));
        			mmMail.addRecipients(Message.RecipientType.TO, toAddrs.replaceAll("\\;","\\,"));
				}
        	}
        	logger.info("Correo Para :: " + strTo);
        	
        	// Copia a otros
        	if(strCopia!=null && !strCopia.isEmpty()){
        		for (int i = 0; i < strCopia.size(); i++) {
        			String ccAddrs = setFormatEmail(strCopia.get(i));
        			mmMail.addRecipients(Message.RecipientType.CC, ccAddrs.replaceAll("\\;","\\,"));
				}
        	}
        	logger.info("Correo Copia :: " + strCopia);

        	// Asunto
        	if(strAsunto!=null){
        		mmMail.setSubject(strAsunto);
        	}
        	logger.info("Correo Asunto :: " + strAsunto);
        	
        	// Contenido
        	StringBuffer sb = new StringBuffer();
        	sb.append(strBody);
        	
    		MimeMultipart multipart = new MimeMultipart(); 
            MimeBodyPart mbpCuerpo = new MimeBodyPart(); 
            mbpCuerpo.setContent(sb.toString(), "text/html");
            multipart.addBodyPart(mbpCuerpo);           
            
            mmMail.setContent(multipart);
            mmMail.setSentDate(new Date());
            mmMail.saveChanges();

            // Envio del mensaje
            logger.info("Intentando enviar correo.");
            Transport t = session.getTransport("smtp");
    		t.connect();
    		t.sendMessage(mmMail,mmMail.getAllRecipients());
    		t.close();
    		logger.info("Correo se envio exitosamente");
		} catch (Exception e) {
			esCorrecto = false;
			logger.info("Ocurrio un error al enviar el Correo :: " + e);
		}
		
		return esCorrecto;
	}
	
	public static String setFormatEmail(String usuario){
		String mailFmt = "";
		try {
			if(usuario!=null && !usuario.isEmpty()){
				if(!usuario.contains("@")){
					mailFmt = usuario + "@bbva.com";
				}else{
					mailFmt = usuario;
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return mailFmt;
	}
}
