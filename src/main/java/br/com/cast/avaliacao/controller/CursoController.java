package br.com.cast.avaliacao.controller;

import br.com.cast.avaliacao.model.CursoEntity;
import br.com.cast.avaliacao.service.CursoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping("/curso")
@Api(value = "Curso")
public class CursoController extends BaseController  {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "Recupera todos os recursos")
    public List<CursoEntity> findAll() {
        return cursoService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Recupera um recurso por ID")
    public ResponseEntity findById(@PathVariable(value = "id") Long id) {
        return processNoContent(cursoService.findById(id));
    }

    @GetMapping("/assunto/{assunto}")
    @ResponseBody
    @ApiOperation(value = "Recupera recursos com aplicacao de filtros")
    public List<CursoEntity> findByAssunto(@PathVariable(value = "assunto") String assunto) {
        return cursoService.findByAssunto(assunto);
    }

    @PostMapping
    @ResponseBody
    @ApiOperation(value = "Insere um recurso")
    public ResponseEntity create(@Valid @RequestBody CursoEntity entity) {
        return processBusinessWithReturn(() -> cursoService.save(entity));
    }

    @PatchMapping
    @ResponseBody
    @ApiOperation(value = "Atualiza um recursos")
    public ResponseEntity update(@Valid @RequestBody CursoEntity entity) {
        return processBusinessWithoutReturn(() -> cursoService.save(entity));
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Deleta um recurso")
    public ResponseEntity delete(@PathVariable(value = "id") Long id) {
        return processBusinessVoid(() -> cursoService.delete(id));
    }
}
