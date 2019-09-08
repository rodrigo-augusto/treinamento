package br.com.cast.avaliacao.controller;

import br.com.cast.avaliacao.model.CategoriaEntity;
import br.com.cast.avaliacao.model.dto.CategoriaDTO;
import br.com.cast.avaliacao.service.CategoriaService;
import br.com.cast.avaliacao.util.Mapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
