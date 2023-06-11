package br.gov.caixa.dto;

import java.util.ArrayList;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;

public class ParcelaQualificadaDto {
    @JsonbProperty("tipo")
    private TipoSimulacaoDto tipo;

    @JsonbProperty("parcelas")
    private ArrayList<ParcelaDto> parcelas;

    @JsonbCreator
    public ParcelaQualificadaDto(
            TipoSimulacaoDto tipo,
            ArrayList<ParcelaDto> parcelas) {
        this.tipo = tipo;
        this.parcelas = parcelas;
    }

    public TipoSimulacaoDto getTipo() {
        return this.tipo;
    }

    public void setTipo(TipoSimulacaoDto tipo) {
        this.tipo = tipo;
    }


    public ArrayList<ParcelaDto> getParcelas() {
        return this.parcelas;
    }

    public void setParcelas(ArrayList<ParcelaDto> parcelas) {
        this.parcelas = parcelas;
    }

}
