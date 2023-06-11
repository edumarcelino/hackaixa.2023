package br.gov.caixa.model;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "Produto", schema = "dbo")
public class ProdutoModel{

    @Id
    @GeneratedValue
    @Column(name = "CO_PRODUTO")
    @JsonbProperty("codigoProduto")
    private Integer coProduto;

    @Column(name = "NO_PRODUTO", nullable = false, columnDefinition = "VARCHAR(200)")
    @JsonbProperty("descricaoProduto")
    private String noProduto;

    @Column(name = "PC_TAXA_JUROS", nullable = false, columnDefinition = "NUMERIC(10,9)")
    @JsonbProperty("taxaJuros")
    private BigDecimal pcTaxaJuros;

    @Column(name = "NU_MINIMO_MESES", nullable = false)
    @JsonbTransient
    private Integer nuMinimoMeses;

    @Column(name = "NU_MAXIMO_MESES", nullable = true)
    @JsonbTransient
    private Integer nuMaximoMeses;

    @Column(name = "VR_MINIMO", nullable = false, columnDefinition = "NUMERIC(18,2)")
    @JsonbTransient
    private BigDecimal vrMinimo;

    @Column(name = "VR_MAXIMO", nullable = true, columnDefinition = "NUMERIC(18,2)")
    @JsonbTransient
    private BigDecimal vrMaximo;

    public Integer getCoProduto() {
        return this.coProduto;
    }

    public void setCoProduto(Integer coProduto) {
        this.coProduto = coProduto;
    }

    public String getNoProduto() {
        return this.noProduto;
    }

    public void setNoProduto(String noProduto) {
        this.noProduto = noProduto;
    }

    public BigDecimal getPcTaxaJuros() {
        return this.pcTaxaJuros;
    }

    public void setPcTaxaJuros(BigDecimal pcTaxaJuros) {
        this.pcTaxaJuros = pcTaxaJuros;
    }

    public Integer getNuMinimoMeses() {
        return this.nuMinimoMeses;
    }

    public void setNuMinimoMeses(Integer nuMinimoMeses) {
        this.nuMinimoMeses = nuMinimoMeses;
    }

    public Integer getNuMaximoMeses() {
        return this.nuMaximoMeses;
    }

    public void setNuMaximoMeses(Integer nuMaximoMeses) {
        this.nuMaximoMeses = nuMaximoMeses;
    }

    public BigDecimal getVrMinimo() {
        return this.vrMinimo;
    }

    public void setVrMinimo(BigDecimal vrMinimo) {
        this.vrMinimo = vrMinimo;
    }

    public BigDecimal getVrMaximo() {
        return this.vrMaximo;
    }

    public void setVrMaximo(BigDecimal vrMaximo) {
        this.vrMaximo = vrMaximo;
    }

}
