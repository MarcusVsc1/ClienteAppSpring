package com.ex.clienter.dto;
import javax.validation.constraints.NotBlank;

import com.ex.clienter.model.Cidade;

public class RequisicaoNovaCidade {

    @NotBlank
    private String nomeCidade;
    @NotBlank
    private String estado;

    public String getNomeCidade() {
        return this.nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public Cidade toCidade(){
        Cidade cidade = new Cidade();
        cidade.setEstado(estado);
        System.out.println(estado);
        cidade.setNomeCidade(nomeCidade);

        return cidade;
    }

}
