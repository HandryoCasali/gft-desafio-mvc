package br.com.gft.gftmilhas.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.gft.gftmilhas.entities.AtividadeParticipante;

public class FormEntregaAtividadeDTO {

    private List<AtividadeParticipante> atividades = new ArrayList<>();

    public FormEntregaAtividadeDTO(){}

    public FormEntregaAtividadeDTO(List<AtividadeParticipante> atividades) {
        this.atividades = atividades;
    }

    public void addAll(List<AtividadeParticipante> atividades){
        this.atividades.addAll(atividades);
    }

    public List<AtividadeParticipante> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<AtividadeParticipante> atividades) {
        this.atividades = atividades;
    }

}
