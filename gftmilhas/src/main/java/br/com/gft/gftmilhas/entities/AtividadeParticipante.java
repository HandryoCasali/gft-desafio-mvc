package br.com.gft.gftmilhas.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import br.com.gft.gftmilhas.enums.StatusConclusao;

@Entity
@Table(name = "atividades_participantes")
public class AtividadeParticipante {

    @EmbeddedId
    private AtividadeParticipanteID id;

    @Enumerated(EnumType.STRING)
    private StatusConclusao statusConclusao;

    public AtividadeParticipante(){ }
    
    public AtividadeParticipante(AtividadeParticipanteID id) {
        this.id = id;
    }

    public AtividadeParticipanteID getId() {
        return id;
    }

    public void setId(AtividadeParticipanteID id) {
        this.id = id;
    }
    public StatusConclusao getStatusConclusao() {
        return statusConclusao;
    }

    public void setStatusConclusao(StatusConclusao statusConclusao) {
        this.statusConclusao = statusConclusao;
    }

    @Override
    public String toString() {
        
        return "Nome Participante: " + id.getParticipante().getNome() +
                " Conclusão: " + statusConclusao;
    }

   
}
