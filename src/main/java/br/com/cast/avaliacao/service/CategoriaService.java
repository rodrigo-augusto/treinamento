package br.com.cast.avaliacao.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import br.com.cast.avaliacao.component.InputValidator;
import br.com.cast.avaliacao.model.dto.CategoriaDTO;
import br.com.cast.avaliacao.service.business.Rn0001;
import br.com.cast.avaliacao.model.CategoriaEntity;
import br.com.cast.avaliacao.repository.CategoriaRepository;
import br.com.cast.avaliacao.service.business.RnG0001;
import br.com.cast.avaliacao.util.IMsg;
import br.com.cast.avaliacao.util.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private Rn0001 rn0001;

    @Autowired
    private RnG0001 rnG0001;

    @Autowired
    private InputValidator validator;

    public List<CategoriaDTO> findAll() {
        return categoriaRepository.findAll()
                .stream()
                .map(getDtoFromEntityFunction())
                .collect(Collectors.toList());
    }

    public CategoriaDTO findById(final Long id) {
        return categoriaRepository.findById(id)
                .map(getDtoFromEntityFunction())
                .orElse(null);
    }

    public CategoriaDTO save(final CategoriaDTO dto) {
        final CategoriaEntity entity = Optional.of(dto)
                .map(getEntityFromDtoFunction())
                .orElse(null);

        rnG0001.process(getValidatorToSave(entity));
        rn0001.process(entity.getId(), (id) -> categoriaRepository.findById(id));

        return Optional.of(categoriaRepository.save(entity))
                .map(getDtoFromEntityFunction())
                .orElse(null);
    }

    public void delete(final Long id) {
        categoriaRepository.deleteById(id);
    }

    private Function<CategoriaDTO, CategoriaEntity> getEntityFromDtoFunction() {
        return (dto) -> Mapper.map(dto, CategoriaEntity.class);
    }


    private Function<CategoriaEntity, CategoriaDTO> getDtoFromEntityFunction() {
        return (entity) -> Mapper.map(entity, CategoriaDTO.class);
    }

    private InputValidator getValidatorToSave(CategoriaEntity entity) {
        return validator.check(() -> !StringUtils.isEmpty(entity.getDescricao()), IMsg.INPUT_0001);
    }
}
