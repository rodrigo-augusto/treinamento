package br.com.cast.avaliacao.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cast.avaliacao.model.CursoEntity;

@Repository
public interface CursoRepository extends JpaRepository<CursoEntity, Long> {

    List<CursoEntity> findByAssuntoContaining(final String assunto);

    @Query(value = "select * " +
            "  from CURSO c " +
            " where ( c.DT_INI_CURSO Between :dataInicio and :dataTermino )    " +
            "    or ( c.DT_FIM_CURSO Between :dataInicio and :dataTermino )    " +
            "    or ( :dataInicio Between c.DT_INI_CURSO and c.DT_FIM_CURSO )  " +
            "    or ( :dataTermino Between c.DT_INI_CURSO and c.DT_FIM_CURSO ) ", nativeQuery = true)
    List<CursoEntity> findConflicts(@Param("dataInicio") Calendar dataInicio,
                                    @Param("dataTermino") Calendar dataTermino);
}
