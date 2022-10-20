package br.org.serratec.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name="categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_categoria")
    private Long idCategoria;

    @NotBlank
    @Column(name="nome", nullable=false, length=30)
    private String nome;

    @Column(name="descricao", length = 200)
    private String descricao;

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCategoria);
    }

    @Override 
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Categoria other = (Categoria) obj;
        return Objects.equals(idCategoria, other.idCategoria);
    }
}
