package org.enums;

public enum Template {
    OK("SUCCESS"),
    ERROR("FAILED");

    private String key;

    private Template(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

    /*************************************************/

    public static void main(String[] args) {
        System.out.println("Template");
        String str = Template.OK.name(); // OK
        System.out.println("str: " + str);
    }
}
