package br.gov.caixa.utils;

import java.math.BigDecimal;
import java.util.ArrayList;

import br.gov.caixa.dto.EntradaSimulacaoEmprestimoDto;
import br.gov.caixa.dto.ParcelaDto;
import br.gov.caixa.model.ProdutoModel;


public class Util {

    public static ArrayList<ParcelaDto> calculadoraPRICE(ProdutoModel produto,
            EntradaSimulacaoEmprestimoDto simulacao) {

        ArrayList<ParcelaDto> parcelas = new ArrayList<ParcelaDto>();

        // Dados iniciais para calculo
        Double valorDesejado = simulacao.getValorDesejado().doubleValue();
        Double taxaJuros = produto.getPcTaxaJuros().doubleValue();
        Integer quantidadeParcelas = simulacao.getPrazo();

        Double numerador = valorDesejado * taxaJuros;

        // (1 + taxa_juros)^(-num_parcelas)
        Double denominadorPrimeiro = 1 + taxaJuros;
        int expoente = (-1) * quantidadeParcelas;

        BigDecimal denominadorSegundo = new BigDecimal(Math.pow(denominadorPrimeiro, expoente));

        BigDecimal denominador = new BigDecimal(1).subtract(denominadorSegundo);

        // Obteve o valor da Parcela Price
        Double valorParcela = numerador.doubleValue() / denominador.doubleValue();

        // Saldo Devedor da operação inicialmente igual ao valor desejado.
        Double saldoDevedor = valorDesejado;

        // Itera ate a quantidade de Parcelas
        for (int i = 1; i <= quantidadeParcelas; i++) {

            Double valorJuros = saldoDevedor * taxaJuros;
            Double amortizacao = valorParcela - valorJuros;

            ParcelaDto parcela = new ParcelaDto(i, new BigDecimal(amortizacao), new BigDecimal(valorJuros),
                    new BigDecimal(valorParcela));

            parcelas.add(parcela);

            saldoDevedor = saldoDevedor - valorParcela;
        }

        return parcelas;

    }

    public static ArrayList<ParcelaDto> calculadoraSAC(ProdutoModel produto,
            EntradaSimulacaoEmprestimoDto simulacao) {
        ArrayList<ParcelaDto> parcelas = new ArrayList<ParcelaDto>();

        // Dados iniciais para calculo
        Double valorDesejado = simulacao.getValorDesejado().doubleValue();
        Double taxaJuros = produto.getPcTaxaJuros().doubleValue();
        Integer quantidadeParcelas = simulacao.getPrazo();

        // Saldo Devedor da operação inicialmente igual ao valor desejado.
        Double saldoDevedor = valorDesejado;
        Double valorAmortizacao = saldoDevedor / quantidadeParcelas;

        // Itera ate a quantidade de Parcelas
        for (int i = 1; i <= quantidadeParcelas; i++) {

            // Calcular o juros multiplicando o saldo devedor atual pela taxa do
            // financiamento
            Double valorJuros = taxaJuros * saldoDevedor;

            // Valor da parcela
            Double valorParcela = valorAmortizacao + valorJuros;

            ParcelaDto parcela = new ParcelaDto(i, new BigDecimal(valorAmortizacao), new BigDecimal(valorJuros),
                    new BigDecimal(valorParcela));

            saldoDevedor = saldoDevedor - valorParcela;

            parcelas.add(parcela);

        }

        return parcelas;
    }

}
