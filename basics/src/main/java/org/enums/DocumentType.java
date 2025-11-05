package org.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public enum DocumentType {
	DNI("L"),
    RUC("R"),
	PASSPORT("P"),
    DIPLOMATIC("D"),
    FOREIGNERS("E"),
    MILITARY("M"),
	POLICE("T");

	private String value;

	private static Map<String, DocumentType> map = new HashMap<>();

	static {
		for (DocumentType docType : DocumentType.values()) {
			map.put(docType.getValue(), docType);
		}
	}
	/* or:
	private static final Map<String, DocumentType> DOCUMENT_TYPE_MAP =
		Collections.unmodifiableMap(EnumSet.allOf(DocumentType.class)
										   .stream().collect(Collectors.toMap(DocumentType::getValue, e -> e)));
	
	or:
		Arrays.stream(values()).forEach(docType -> 
			map.put(docType.getValue(), docType)
		);
	*/
	
	/*
	* convert enum to Map<String, String>
	private static Map<String, String> map;

	static {
		map = new HashMap();
		Arrays.stream(values()).forEach(docType -> 
			map.put(docType.name(), docType.getValue())
		);

		// or:
		for (DocumentType docType : DocumentType.values()) {
			map.put(docType.name(), docType.getValue());
		}
	}
	*/
	
	private DocumentType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}

	public static DocumentType getKey(String value) {
		return map.get(value);

		/* or:
        for (DocumentType enumValue : DocumentType.values()) {
            if (enumValue.getValue().equals(value)) {
                return enumValue;
            }
        }
        return null;
		*/
	}

    public static String getNameByValue(String value) {
        return map.get(value).name();
    }
	
	public static String getValueByName(String name) {
		return DocumentType.valueOf(name).getValue();
	}
	
	// getValue: busca valor del doc enum
	public static String getDocumentType(String name){
		String value = "";
		DocumentType[] listTemp = DocumentType.values();
		for(DocumentType d : listTemp){
			if(d.toString().equals(name)){
				value = d.getValue();
				break;
			}
		}
		return value;
	}
	
	public static String getDocumentType2(String tipoDoc){
		for (DocumentType dt : DocumentType.values()) {
			if (dt.getValue().equals(tipoDoc)) {
				return dt.name();
			}
		}
		return null;
	}
	
	// getKey: get Key from value, else is founded then throws exception
	// ref: java\springboot\projects\web\reddit clone\backend\spring-reddit-clone-master\src\main\java\com\programming\techie\springredditclone\model\VoteType.java
	public static DocumentType lookup(String valueToFind) throws Exception {
		return Arrays.stream(DocumentType.values())
				.filter(value -> value.getValue().equals(valueToFind))
				.findAny()
				.orElseThrow(() -> new Exception("Document not found"));
	}
			
	/*************************************************/
	
	public static void main(String[] args) {
			
		System.out.println("DocumentType");

		try {
			// get Name string by Name
			String name = DocumentType.DNI.toString(); // DNI
			name = DocumentType.DNI.name(); // DNI

			// get DocumentType by Key
			DocumentType dt = DocumentType.valueOf("DNI");

			// get DocumentType by Value
			DocumentType dt1 = DocumentType.getKey("L");
	
			DocumentType dt2 = DocumentType.lookup("L");

			// get Value
			String value = DocumentType.DNI.getValue(); // L
			
			// getValue: busca valor del doc enum
			value = getDocumentType("DNI"); // L
					
			// get Value by Name
			String value1 = DocumentType.getValueByName("DNI"); // L
	
			// get Name by Value
			name = DocumentType.getNameByValue("L"); // DNI
			
			// get Name by Value
			name = DocumentType.getDocumentType2("L"); // DNI
	
			if("DNI".equals(value))
				System.out.println("true");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}