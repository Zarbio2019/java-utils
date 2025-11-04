package org.creational.factory.comm;

public class CommunicationFactory {
	
	public Communication getProcess(String modeOfComm) {
		if("Email".equalsIgnoreCase(modeOfComm)) {
			return new EmailCommunication();
		} else if("mobile".equalsIgnoreCase(modeOfComm)) {
			return new MobileCommunication();
		}
		return null;
	}
}
