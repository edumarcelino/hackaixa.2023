package br.gov.caixa.dto;

import java.util.ArrayList;

import br.gov.caixa.model.ProdutoModel;
import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;

public class ResultadoSimulacaoDto {

    @JsonbProperty("produto")
    private ProdutoModel produto;

    @JsonbProperty("resultadoSimulacao")
    private ArrayList<ParcelaQualificadaDto> parcelas;

    @JsonbCreator
    public ResultadoSimulacaoDto(
            @JsonbProperty("produto") final ProdutoModel produto,
            @JsonbProperty("resultadoSimulacao") ArrayList<ParcelaQualificadaDto> parcelas) {
        this.produto = produto;
        this.parcelas = parcelas;
    }

    public ProdutoModel getProduto() {
        return this.produto;
    }

    public void setProduto(ProdutoModel produto) {
        this.produto = produto;
    }

    public ArrayList<ParcelaQualificadaDto> getParcelas() {
        return this.parcelas;
    }

    public void setParcelas(ArrayList<ParcelaQualificadaDto> parcelas) {
        this.parcelas = parcelas;
    }

}
