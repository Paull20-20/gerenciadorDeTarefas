
package br.com.entidade2;

//Usando JPA usamos @Entity para transformar essa classe em uma tabela no banco de dados 

import java.io.Serializable;
import java.util.Objects;



public class Pessoa { 
    
    private Integer id;
    private String titulo2;
    private String descricao2;
    private String deadline2;     
    private String responsavel2;
    private String prioridade2;

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    
    public String getTitulo2() {
        return titulo2;
    }

    public void setTitulo2(String titulo2) {
        this.titulo2 = titulo2;
    }

    public String getDescricao2() {
        return descricao2;
    }

    public void setDescricao2(String descricao2) {
        this.descricao2 = descricao2;
    }

    public String getDeadline2() {
        return deadline2;
    }

    public void setDeadline2(String deadline2) {
        this.deadline2 = deadline2;
    }

    public String getResponsavel2() {
        return responsavel2;
    }

    public void setResponsavel2(String responsavel2) {
        this.responsavel2 = responsavel2;
    }

    public String getPrioridade2() {
        return prioridade2;
    }

    public void setPrioridade2(String prioridade2) {
        this.prioridade2 = prioridade2;
    }

    
    //Serve para informar ao Java para usar o id para comparar os objetos
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pessoa other = (Pessoa) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
 
    
    
}
