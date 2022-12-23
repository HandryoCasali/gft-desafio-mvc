package br.com.gft.gftmilhas.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "participantes")
public class Participante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Nome é necessário.")
    private String nome;

    @Column(nullable = false)
    @NotBlank(message = "Email é necessário.")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Quatro Letras é necessário.")
    @Size(min = 4, max = 4, message = "É necessário ser 4 caracteres.")
    private String quatroLetras;

    private String nivel;

    private String urlFoto;
  
    @ManyToOne
    private GrupoParticipante grupo;

    @OneToMany(mappedBy = "id.participante", cascade = CascadeType.REMOVE)
    private List<AtividadeParticipante> atividades;

    @OneToMany(mappedBy = "id.participante", cascade = CascadeType.REMOVE)
    private List<PresencaParticipante> presencas;

    public Participante() {
    }

    public Participante(String nome, String email, String quatroLetras, String nivel, GrupoParticipante grupo, String urlFoto) {
        this.nome = nome;
        this.email = email;
        this.quatroLetras = quatroLetras;
        this.nivel = nivel;
        this.grupo = grupo;
        this.urlFoto = urlFoto;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQuatroLetras() {
        return quatroLetras;
    }

    public void setQuatroLetras(String quatroLetras) {
        this.quatroLetras = quatroLetras;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public GrupoParticipante getGrupo() {
        return grupo;
    }

    public void setGrupo(GrupoParticipante grupo) {
        this.grupo = grupo;
    }

    public List<AtividadeParticipante> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<AtividadeParticipante> atividades) {
        this.atividades = atividades;
    }

    public List<PresencaParticipante> getPresencas() {
        return presencas;
    }

    public void setPresencas(List<PresencaParticipante> presencas) {
        this.presencas = presencas;
    }

}
