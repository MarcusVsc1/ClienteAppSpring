package com.ex.clienter.dto;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotBlank;

import com.ex.clienter.model.Cliente;

public class RequisicaoNovoCliente {
    
    @NotBlank
    private String nomeCliente;

    private String sexo;

    @NotBlank
    private String dataNascimento;

    @NotBlank
    private String cidade;

    public String getNomeCliente() {
        return this.nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getSexo() {
        return this.sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCidade() {
        return this.cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    

    public Cliente toCliente(){
        Cliente cliente = new Cliente();
        cliente.setNomeCliente(nomeCliente);
        System.out.println(dataNascimento);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataCorreta = LocalDate.parse(dataNascimento, formatter);
        cliente.setDataNascimento(dataCorreta);

        cliente.setIdade(Period.between(dataCorreta, LocalDate.now()).getYears());
        cliente.setCidade(cidade);
        cliente.setSexo(sexo);

        return cliente;
    }
    
}
