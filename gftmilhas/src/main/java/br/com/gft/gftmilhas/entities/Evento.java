package br.com.gft.gftmilhas.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "eventos")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Nome é necessário.")
    private String nome;

    @Column(nullable = false)
    @NotBlank(message = "Descrição é necessário.")
    private String descricao;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataInicio;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataFinal;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.REMOVE)
    private List<GrupoParticipante> grupos = new ArrayList<>();

    @OneToMany(mappedBy = "evento", cascade = CascadeType.REMOVE , fetch = FetchType.EAGER)
    private List<Atividade> atividades = new ArrayList<>();;

    @OneToMany(mappedBy = "id.evento", cascade = CascadeType.REMOVE)
    private List<PresencaParticipante> presencas = new ArrayList<>();;

   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public int getDiasEvento(){
        if(dataFinal == null || dataInicio == null){
            return 0;
        }
        long diferenca = dataFinal.getTime() - dataInicio.getTime();

        TimeUnit time = TimeUnit.DAYS; 
        int dias = (int) time.convert(diferenca, TimeUnit.MILLISECONDS) + 1;
        
        return dias;
    }

    public List<GrupoParticipante> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<GrupoParticipante> grupos) {
        this.grupos = grupos;
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }

    public List<PresencaParticipante> getPresencas() {
        return presencas;
    }

    public void setPresencas(List<PresencaParticipante> presencas) {
        this.presencas = presencas;
    }

}
