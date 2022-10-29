package br.com.gft.gftmilhas.enums;

public enum StatusConclusao {
    NAO_CONCLUIU("Não concluiu"),
    CONCLUIU("Concluiu"),
    CONCLUIU_COM_ATRASO("Concluiu com atraso");

    private final String displayName;
    
    private StatusConclusao(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
