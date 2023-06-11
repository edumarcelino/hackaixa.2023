package br.gov.caixa.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;

public class ParcelaDto {


    private Integer numero;
    private BigDecimal valorAmortizacao;
    private BigDecimal valorJuros;
    private BigDecimal valorPrestacao;

    @JsonbCreator
    public ParcelaDto(
            @JsonbProperty("numero") final Integer numero,
            @JsonbProperty("valorAmortizacao") final BigDecimal valorAmortizacao,
            @JsonbProperty("valorJuros") final BigDecimal valorJuros,
            @JsonbProperty("valorPrestacao") final BigDecimal valorPrestacao) {
        this.numero = numero;
        this.valorAmortizacao = valorAmortizacao;
        this.valorJuros = valorJuros;
        this.valorPrestacao = valorPrestacao;
    }

    public Integer getNumero() {
        return this.numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public BigDecimal getValorAmortizacao() {
        return this.valorAmortizacao.setScale(2, RoundingMode.CEILING);
    }

    public void setValorAmortizacao(BigDecimal valorAmortizacao) {
        this.valorAmortizacao = valorAmortizacao;
    }

    public BigDecimal getValorJuros() {
        return this.valorJuros.setScale(2, RoundingMode.CEILING);
    }

    public void setValorJuros(BigDecimal valorJuros) {
        this.valorJuros = valorJuros;
    }

    public BigDecimal getValorPrestacao() {
        return this.valorPrestacao.setScale(2, RoundingMode.CEILING);
    }

    public void setValorPrestacao(BigDecimal valorPrestacao) {
        this.valorPrestacao = valorPrestacao;
    }



}
