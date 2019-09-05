package br.com.cast.avaliacao.controller;

import br.com.cast.avaliacao.model.CategoriaEntity;
import br.com.cast.avaliacao.service.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public List<CategoriaEntity> findAll() {
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
    public ResponseEntity create(@Valid @RequestBody CategoriaEntity entity) {
        return processBusinessWithReturn(() -> categoriaService.save(entity));
    }

    @PatchMapping
    @ResponseBody
    @ApiOperation(value = "Atualiza um recurso")
    public ResponseEntity update(@Valid @RequestBody CategoriaEntity entity) {
        return processBusinessWithoutReturn(() -> categoriaService.save(entity));
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Deleta um recurso")
    public ResponseEntity delete(@PathVariable(value = "id") Long id) {
        return processBusinessVoid(() -> categoriaService.delete(id));
    }

}
