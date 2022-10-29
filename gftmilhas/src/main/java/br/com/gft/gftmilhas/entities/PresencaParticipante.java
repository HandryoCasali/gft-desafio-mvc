package br.com.gft.gftmilhas.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import br.com.gft.gftmilhas.enums.Presenca;

@Entity
@Table(name = "presencas_participantes")
public class PresencaParticipante {
    @EmbeddedId
    private PresencaParticipanteID id;

    @Enumerated(EnumType.STRING)
    private Presenca presenca;

    public PresencaParticipante(){ }

    public PresencaParticipante(PresencaParticipanteID id) {
        this.id = id;
    }
    public PresencaParticipanteID getId() {
        return id;
    }
    public void setId(PresencaParticipanteID id) {
        this.id = id;
    }
    public Presenca getPresenca() {
        return presenca;
    }
    public void setPresenca(Presenca presenca) {
        this.presenca = presenca;
    }

}
