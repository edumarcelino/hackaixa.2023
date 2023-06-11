package br.gov.caixa.dto;

import java.util.List;

import br.gov.caixa.model.ProdutoModel;
import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;

public class SimulacaoEmprestimoDto {

    private ProdutoModel produto;
    private String tipo;
    private List<ParcelaDto> parcelas;

    @JsonbCreator
    public SimulacaoEmprestimoDto(
            @JsonbProperty("produto") final ProdutoModel produto,
            @JsonbProperty("tipo") final String tipo,
            @JsonbProperty("parcelas") final List<ParcelaDto> parcelas) {
        this.produto = produto;
        this.tipo = tipo;
        this.parcelas = parcelas;

    }

    public List<ParcelaDto> getParcelas() {
        return this.parcelas;
    }

    public void setParcelas(List<ParcelaDto> parcelas) {
        this.parcelas = parcelas;
    }

    public ProdutoModel getProduto() {
        return this.produto;
    }

    public void setProduto(ProdutoModel produto) {
        this.produto = produto;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
