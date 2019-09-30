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

import br.com.cast.avaliacao.model.dto.CategoriaDTO;
import br.com.cast.avaliacao.service.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping("/categoria")
@Api(value = "Catergoria")
public class CatergoriaController extends BaseController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "Recupera todos os recursos")
    public List<CategoriaDTO> findAll() {
        return categoriaService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Recupera um recurso por ID")
    public ResponseEntity findById(@PathVariable(value = "id") Long id) {
        return processNoContent(categoriaService.findById(id));
    }

    @PostMapping
    @ResponseBody
    @ApiOperation(value = "Insere um recurso")
    public ResponseEntity create(@Valid @RequestBody CategoriaDTO dto) {
        return processBusinessWithReturn(() -> categoriaService.save(dto));
    }

    @PatchMapping
    @ResponseBody
    @ApiOperation(value = "Atualiza um recurso")
    public ResponseEntity update(@Valid @RequestBody CategoriaDTO dto) {
        return processBusinessWithoutReturn(() -> categoriaService.save(dto));
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Deleta um recurso")
    public ResponseEntity delete(@PathVariable(value = "id") Long id) {
        return processBusinessVoid(() -> categoriaService.delete(id));
    }

}
