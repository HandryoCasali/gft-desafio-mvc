package br.com.gft.gftmilhas.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gft.gftmilhas.entities.Evento;
import br.com.gft.gftmilhas.entities.Participante;
import br.com.gft.gftmilhas.entities.PresencaParticipante;
import br.com.gft.gftmilhas.entities.PresencaParticipanteID;
import br.com.gft.gftmilhas.repositories.EventoRepository;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private PresencaParticipanteService presencaService;

    public List<Evento> listar() {
        return eventoRepository.findAll();
    }

    public Evento buscarPorId(Long id) throws Exception {
        Optional<Evento> optParticipante = eventoRepository.findById(id);

        if (optParticipante.isEmpty()) {
            throw new Exception("Não foi encontrado evento com id: " + id);
        }

        return optParticipante.get();
    }

    public Evento cadastrar(Evento evento) {
        evento = eventoRepository.save(evento);

        List<Participante> participantes = new ArrayList<>();
        evento.getGrupos()
                    .forEach(g -> participantes.addAll(g.getParticipantes()));

        for (int dia = 1; dia <= evento.getDiasEvento(); dia++) {
            for (Participante p : participantes) {
                presencaService.cadastrar(new PresencaParticipante(new PresencaParticipanteID(p, evento, dia)));
            }
        }
        return evento;
    }

    public void deletar(Long id) {
        eventoRepository.deleteById(id);
    }

    public boolean existe(Long id) {
        return eventoRepository.existsById(id);
    }

}
