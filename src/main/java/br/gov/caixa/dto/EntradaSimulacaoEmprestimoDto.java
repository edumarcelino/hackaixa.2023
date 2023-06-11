package br.gov.caixa.dto;

import java.math.BigDecimal;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class EntradaSimulacaoEmprestimoDto {

    @NotBlank(message = "Valor Desejado não pode ser vazio. ")
    @Positive(message = "Valor desejado tem de ser positivo e maior que zero.")
    @JsonbProperty("valorDesejado")
    private BigDecimal valorDesejado;

    @Positive(message = "Prazo desejado tem de ser positivo e maior que zero.")
    @NotBlank(message = "Prazo Desejado não pode ser vazio. ")
    @JsonbProperty("prazo")
    private Integer prazo;

    @JsonbCreator
    public EntradaSimulacaoEmprestimoDto(){}

    public BigDecimal getValorDesejado() {
        return this.valorDesejado;
    }

    public void setValorDesejado(BigDecimal valorDesejado) {
        this.valorDesejado = valorDesejado;
    }

    public Integer getPrazo() {
        return this.prazo;
    }

    public void setPrazo(Integer prazo) {
        this.prazo = prazo;
    }

}
