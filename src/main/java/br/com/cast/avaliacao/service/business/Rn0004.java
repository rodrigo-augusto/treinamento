package br.com.cast.avaliacao.service.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cast.avaliacao.model.CursoEntity;
import br.com.cast.avaliacao.repository.CursoRepository;
import br.com.cast.avaliacao.util.IMsg;

@Component
public class Rn0004 {

    @Autowired
    private CursoRepository cursoRepository;

    public void process(final CursoEntity entity) {
        final List<CursoEntity> conflitos = cursoRepository.findConflicts(
                entity.getDataInicio(),
                entity.getDataTermino());

        final boolean isSameEntity = conflitos
                .stream()
                .anyMatch(e -> e.getId().equals(entity.getId()) && conflitos.size() == 1);

        if (!isSameEntity && !conflitos.isEmpty()) {
            throw new BusinessException(IMsg.MSG_0005);
        }
    }
}
