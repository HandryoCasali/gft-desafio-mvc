package br.com.gft.gftmilhas.enums;

public enum Presenca {
    PRESENTE("Presente"),
    ATRASADO("Atrasado"),
    AUSENTE("Ausente");

    private final String displayName;
    
    private Presenca(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}