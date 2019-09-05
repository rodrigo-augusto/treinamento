package br.com.cast.avaliacao.service;

import java.util.List;
import java.util.Optional;

import br.com.cast.avaliacao.business.Rn0001;
import br.com.cast.avaliacao.model.CategoriaEntity;
import br.com.cast.avaliacao.repository.CategoriaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CategoriaService {

    @Autowired
    private
    CategoriaRepository categoriaRepository;

    @Autowired
    private Rn0001 rn0001;

    public List<CategoriaEntity> findAll () {
        return categoriaRepository.findAll();
    }

    public CategoriaEntity save (final CategoriaEntity entity) {
        rn0001.process(entity.getId(), (id) -> categoriaRepository.findById(id));
        return categoriaRepository.save(entity);
    }

    public CategoriaEntity findById (final Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    public void delete (final Long id) {
        categoriaRepository.deleteById(id);
    }
}
