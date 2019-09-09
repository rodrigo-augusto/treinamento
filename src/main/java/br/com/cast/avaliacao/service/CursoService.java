package br.com.cast.avaliacao.service;

import br.com.cast.avaliacao.component.InputValidator;
import br.com.cast.avaliacao.model.CategoriaEntity;
import br.com.cast.avaliacao.model.dto.CategoriaDTO;
import br.com.cast.avaliacao.model.dto.CursoDTO;
import br.com.cast.avaliacao.service.business.*;
import br.com.cast.avaliacao.model.CursoEntity;
import br.com.cast.avaliacao.repository.CursoRepository;
import br.com.cast.avaliacao.util.IMsg;
import br.com.cast.avaliacao.util.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    @Autowired
    private RnG0001 rnG0001;

    @Autowired
    private InputValidator validator;

    public List<CursoDTO> findAll() {
        return cursoRepository.findAll()
                .stream()
                .map(getDtoFromEntityFunction())
                .collect(Collectors.toList());
    }

    public CursoDTO findById(final Long id) {
        return cursoRepository.findById(id)
                .map(getDtoFromEntityFunction())
                .orElse(null);
    }

    public List<CursoDTO> findByAssunto(final String assunto) {
        return cursoRepository.findByAssuntoContaining(assunto)
                .stream()
                .map(getDtoFromEntityFunction())
                .collect(Collectors.toList());
    }

    public CursoDTO save(final CursoDTO dto) {
        final CursoEntity entity = Optional.of(dto)
                .map(getEntityFromDtoFunction())
                .orElse(null);

        rnG0001.process(getValidatorToSave(entity));
        rn0001.process(entity.getId(), (id) -> cursoRepository.findById(id));
        rn0002.process(entity.getDataInicio());
        rn0003.process(entity.getDataInicio(), entity.getDataTermino());
        rn0004.process(entity);

        return Optional.of(cursoRepository.save(entity))
                .map(getDtoFromEntityFunction())
                .orElse(null);
    }

    public void delete(final Long id) {
        cursoRepository.deleteById(id);
    }

    private Function<CursoDTO, CursoEntity> getEntityFromDtoFunction() {
        return (dto) -> Mapper.map(dto, CursoEntity.class, (cursoEntity) -> {
            cursoEntity.setCategoriaEntity(Mapper.map(dto.getCategoria(), CategoriaEntity.class));
        });
    }

    private Function<CursoEntity, CursoDTO> getDtoFromEntityFunction() {
        return (entity) -> Mapper.map(entity, CursoDTO.class, (dto) -> {
            dto.setCategoria(Mapper.map(entity.getCategoriaEntity(), CategoriaDTO.class));
        });
    }

    private InputValidator getValidatorToSave(CursoEntity entity) {
        return validator
                .check(() -> !StringUtils.isEmpty(entity.getAssunto()), IMsg.INPUT_0002)
                .check(() -> entity.getDataInicio() != null, IMsg.INPUT_0003)
                .check(() -> entity.getDataTermino() != null, IMsg.INPUT_0004)
                .check(() -> entity.getCategoriaEntity() != null, IMsg.INPUT_0005);
    }

}
