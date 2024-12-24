package fr.babystaff.babymodelbungee.langue;

public enum Language {
    ENGLISH("EN_uk"),
    FRENCH("FR_fr");

    private final String code;

    Language(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Language fromCode(String code) {
        for (Language lang : values()) {
            if (lang.getCode().equalsIgnoreCase(code)) {
                return lang;
            }
        }
        return ENGLISH; // Langue par défaut
    }
}
