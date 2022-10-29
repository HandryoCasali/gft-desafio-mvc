package br.com.gft.gftmilhas.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class AtividadeParticipanteID implements Serializable {
    
    @ManyToOne
    private Atividade atividade;
    
    @ManyToOne
    private Participante participante;
    
    public AtividadeParticipanteID(){ }

    public AtividadeParticipanteID(Atividade atividade, Participante participante) {
        this.atividade = atividade;
        this.participante = participante;
    }
    
    public Atividade getAtividade() {
        return atividade;
    }
    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }
    public Participante getParticipante() {
        return participante;
    }
    public void setParticipante(Participante participante) {
        this.participante = participante;
    }


}
