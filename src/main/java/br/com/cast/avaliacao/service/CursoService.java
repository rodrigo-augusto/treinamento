package br.com.cast.avaliacao.service;

import br.com.cast.avaliacao.business.Rn0001;
import br.com.cast.avaliacao.business.Rn0002;
import br.com.cast.avaliacao.business.Rn0003;
import br.com.cast.avaliacao.business.Rn0004;
import br.com.cast.avaliacao.model.CursoEntity;
import br.com.cast.avaliacao.repository.CursoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private Rn0001 rn0001;

    @Autowired
    private Rn0002 rn0002;

    @Autowired
    private Rn0003 rn0003;

    @Autowired
    private Rn0004 rn0004;

    public List<CursoEntity> findAll() {
        return cursoRepository.findAll();
    }

    public CursoEntity findById(final Long id) {
        return cursoRepository.findById(id).orElse(null);
    }

    public CursoEntity save(final CursoEntity entity) {
        rn0001.process(entity.getId(), (id) -> cursoRepository.findById(id));
        rn0002.process(entity.getDataInicio());
        rn0003.process(entity.getDataInicio(), entity.getDataTermino());
        rn0004.process(entity);
        return cursoRepository.save(entity);
    }

    public void delete(final Long id) {
        cursoRepository.deleteById(id);
    }

    public List<CursoEntity> findByAssunto(final String assunto) {
        return cursoRepository.findByAssuntoContaining(assunto);
    }
}
