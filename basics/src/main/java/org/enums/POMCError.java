package org.enums;

public enum POMCError {

	// PARAMETER(codeAdvice, message, isRollback)
	WRONG_PARAMETER("POMC00060002", "WRONG_PARAMTER", false),
	ERROR_SERVICE_ASO("POMC00060003", "ERROR_SERVICE_ASO", false),
	ERROR_PROCESS_ID("POMC00060004", "ERROR_PROCESS_ID", false),
	NO_EXIST_PROPOSAL("POMC00060005","NO_EXIST_PROPOSAL", false),
	NO_MATCH_DOCUMENT("POMC00060006","NO_MATCH_DOCUMENT", false);

	private String codeAdvice;
	private String message;
	private boolean isRollback;

	private POMCError(String codeAdvice, String message, boolean isRollback) {
		this.codeAdvice = codeAdvice;
		this.message = message;
		this.isRollback = isRollback;
	}

	public String getCodeAdvice() {
		return codeAdvice;
	}

	public String getMessage() {
		return message;
	}

	public boolean isRollback() {
		return isRollback;
	}
}
/* call:
throw POMCExceptions.build(POMCError.ERROR_PROCESS_ID, "contactId is not equivalent to business Process Id");
*/