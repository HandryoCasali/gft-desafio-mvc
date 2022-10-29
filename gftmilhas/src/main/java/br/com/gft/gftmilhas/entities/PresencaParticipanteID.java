package br.com.gft.gftmilhas.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;


@Embeddable
public class PresencaParticipanteID implements Serializable{

    @ManyToOne
    private Participante participante;

    @ManyToOne
    private Evento evento;

    private Integer diaEvento;


    public PresencaParticipanteID(){ }

    public PresencaParticipanteID(Participante participante, Evento evento, Integer diaEvento) {
        this.participante = participante;
        this.evento = evento;
        this.diaEvento = diaEvento;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Integer getDiaEvento() {
        return diaEvento;
    }

    public void setDiaEvento(Integer diaEvento) {
        this.diaEvento = diaEvento;
    }
}
