package br.com.cast.avaliacao.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cast.avaliacao.model.dto.CursoDTO;
import br.com.cast.avaliacao.service.CursoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping("/curso")
@Api(value = "Curso")
public class CursoController extends BaseController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "Recupera todos os recursos")
    public List<CursoDTO> findAll() {
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
    public List<CursoDTO> findByAssunto(@PathVariable(value = "assunto") String assunto) {
        return cursoService.findByAssunto(assunto);
    }

    @PostMapping
    @ResponseBody
    @ApiOperation(value = "Insere um recurso")
    public ResponseEntity create(@Valid @RequestBody CursoDTO dto) {
        return processBusinessWithReturn(() -> cursoService.save(dto));
    }

    @PatchMapping
    @ResponseBody
    @ApiOperation(value = "Atualiza um recursos")
    public ResponseEntity update(@Valid @RequestBody CursoDTO dto) {
        return processBusinessWithoutReturn(() -> cursoService.save(dto));
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Deleta um recurso")
    public ResponseEntity delete(@PathVariable(value = "id") Long id) {
        return processBusinessVoid(() -> cursoService.delete(id));
    }
}
