package br.com.gft.gftmilhas.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.gft.gftmilhas.entities.PresencaParticipante;

public class FormPresencaParticipanteDTO {

    private List<PresencaParticipante> presencas = new ArrayList<>();


    public FormPresencaParticipanteDTO(){}

    public FormPresencaParticipanteDTO(List<PresencaParticipante> presencas) {
        this.presencas = presencas;
    }

    public void addAll(List<PresencaParticipante> presencas){
        this.presencas.addAll(presencas);
    }

    public List<PresencaParticipante> getPresencas() {
        return presencas;
    }

    public void setPresencas(List<PresencaParticipante> presencas) {
        this.presencas = presencas;
    }
   
}
