package br.com.cast.avaliacao.model;

import javax.persistence.*;

@Entity
@Table(name = "CATEGORIA")
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_CATEGORIA", nullable = false)
    private Long id;

    @Column(name = "NM_CATEGORIA", nullable = false)
    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "CatergoriaEntity{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
