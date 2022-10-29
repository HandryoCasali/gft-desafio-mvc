package br.com.gft.gftmilhas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gft.gftmilhas.entities.AtividadeParticipante;
import br.com.gft.gftmilhas.entities.Evento;
import br.com.gft.gftmilhas.entities.GrupoParticipante;
import br.com.gft.gftmilhas.entities.Participante;
import br.com.gft.gftmilhas.entities.PresencaParticipante;
import br.com.gft.gftmilhas.enums.Presenca;
import br.com.gft.gftmilhas.enums.StatusConclusao;
import br.com.gft.gftmilhas.repositories.GrupoParticipanteRepository;

@Service
public class GrupoParticipanteService {
    @Autowired
    private GrupoParticipanteRepository grupoParticipanteRepository;

    public List<GrupoParticipante> listarPorPontos(Evento evento){
        return grupoParticipanteRepository.findByEventoOrderByPontuacaoDesc(evento);
    }

    public GrupoParticipante buscarPorId(Long id) throws Exception {
        Optional<GrupoParticipante> optParticipante = grupoParticipanteRepository.findById(id);

        if (optParticipante.isEmpty()) {
            throw new Exception("Não foi encontrado grupo com id: " + id);
        }

        return optParticipante.get();
    }

    public GrupoParticipante cadastrar(GrupoParticipante grupo) throws Exception {
        if(grupo.getId() == null){
            List<GrupoParticipante> grupos = grupoParticipanteRepository.findByNome(grupo.getNome());
            if (grupos != null) {
                for (GrupoParticipante g : grupos) {
                    boolean jaExisteGrupoNesseEvento = g.getEvento().getId() == grupo.getEvento().getId();
                    if (jaExisteGrupoNesseEvento) {
                        throw new Exception("Já existe grupo com esse nome cadastrado nesse evento!");
                    }
                }
                }
        }
        return grupoParticipanteRepository.save(grupo);
    }

    public void deletar(Long id) {
        grupoParticipanteRepository.deleteById(id);
    }

    public boolean existe(Long id) {
        return grupoParticipanteRepository.existsById(id);
    }

    public Long calcularPontuacao(GrupoParticipante grupo) {
        Long pontuacao = 0l;
        boolean temDireitoBonusAtividade = true;
        boolean temDireitoBonusPresenca = true;
        List<Participante> participantes = grupo.getParticipantes();
        for (Participante p : participantes) {
            for (AtividadeParticipante ap : p.getAtividades()) {
                if (ap.getStatusConclusao() == StatusConclusao.CONCLUIU) {
                    pontuacao += 5;
                } else if (ap.getStatusConclusao() == StatusConclusao.CONCLUIU_COM_ATRASO) {
                    pontuacao += 3;
                } else if (ap.getStatusConclusao() == StatusConclusao.NAO_CONCLUIU || ap.getStatusConclusao() == null) {
                    temDireitoBonusAtividade = false;
                }
            }
            for (PresencaParticipante presenca : p.getPresencas()) {
                Presenca statusPresenca = presenca.getPresenca();
                if (statusPresenca == Presenca.PRESENTE) {
                    pontuacao += 10;
                } else if (statusPresenca == Presenca.ATRASADO) {
                    pontuacao += 8;
                } else if (statusPresenca == Presenca.AUSENTE || statusPresenca == null) {
                    temDireitoBonusPresenca = false;
                }
            }
        }

       
            if (temDireitoBonusAtividade) {
                pontuacao += 3;
            }
            if (temDireitoBonusPresenca) {
                pontuacao += 5;
            }
        

        return pontuacao;
    }

}
