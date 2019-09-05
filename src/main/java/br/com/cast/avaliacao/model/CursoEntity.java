package br.com.cast.avaliacao.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "CURSO")
public class CursoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_CURSO", nullable = false)
    private Long id;

    @Column(name = "NM_ASSUNTO_CURSO", nullable = false)
    private String assunto;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "DT_INI_CURSO", nullable = false)
    private Calendar dataInicio;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "DT_FIM_CURSO", nullable = false)
    private Calendar dataTermino;

    @Column(name = "NUM_ALUNO")
    private Integer quantidadeAluno;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ID_CATEGORIA")
    private CategoriaEntity categoria;

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

    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEntity catergoria) {
        this.categoria = catergoria;
    }

    @Override
    public String toString() {
        return "CursoEntity{" +
                "id=" + id +
                ", assunto='" + assunto + '\'' +
                ", dataInicio=" + dataInicio +
                ", dataTermino=" + dataTermino +
                ", quantidadeAluno=" + quantidadeAluno +
                ", categoria=" + categoria +
                '}';
    }
}
