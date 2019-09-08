package br.com.cast.avaliacao.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Calendar;

public class CursoDTO {

    private Long id;

    private String assunto;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Calendar dataInicio;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Calendar dataTermino;

    private Integer quantidadeAluno;

    private CategoriaDTO categoriaDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public Calendar getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Calendar dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Calendar getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Calendar dataTermino) {
        this.dataTermino = dataTermino;
    }

    public Integer getQuantidadeAluno() {
        return quantidadeAluno;
    }

    public void setQuantidadeAluno(Integer quantidadeAluno) {
        this.quantidadeAluno = quantidadeAluno;
    }

    public CategoriaDTO getCategoriaDTO() {
        return categoriaDTO;
    }

    public void setCategoriaDTO(CategoriaDTO categoriaDTO) {
        this.categoriaDTO = categoriaDTO;
    }

    @Override
    public String toString() {
        return "CursoDTO{" +
                "id=" + id +
                ", assunto='" + assunto + '\'' +
                ", dataInicio=" + dataInicio +
                ", dataTermino=" + dataTermino +
                ", quantidadeAluno=" + quantidadeAluno +
                ", categoriaDTO=" + categoriaDTO +
                '}';
    }
}
