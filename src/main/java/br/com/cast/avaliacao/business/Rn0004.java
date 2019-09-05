package br.com.cast.avaliacao.business;

import java.util.List;

import br.com.cast.avaliacao.model.CursoEntity;
import br.com.cast.avaliacao.repository.CursoRepository;
import br.com.cast.avaliacao.ui.IMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Rn0004 {

    @Autowired
    private CursoRepository cursoRepository;

    public void process(final CursoEntity entity) {
        final List<CursoEntity> conflitos = cursoRepository.findConflicts(
                entity.getDataInicio(),
                entity.getDataTermino());

        final boolean isSameEntity = conflitos.stream()
                .filter(e -> e.getId().equals(entity.getId()))
                .findAny()
                .map(e -> conflitos.size() == 1)
                .orElse(Boolean.FALSE);

        if (!isSameEntity && !conflitos.isEmpty()) {
            throw new RuntimeException(IMsg.MSG_0005);
        }
    }
}
