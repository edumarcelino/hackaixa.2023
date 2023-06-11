package br.gov.caixa.service;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.gov.caixa.dto.EntradaSimulacaoEmprestimoDto;
import br.gov.caixa.dto.ParcelaDto;
import br.gov.caixa.dto.ParcelaQualificadaDto;
import br.gov.caixa.dto.ResultadoSimulacaoDto;
import br.gov.caixa.dto.TipoSimulacaoDto;
import br.gov.caixa.model.ProdutoModel;
import br.gov.caixa.repository.EventHubRepository;
import br.gov.caixa.repository.connectioneventhub.EventHubConnectionManagerImpl;
import br.gov.caixa.repository.connectioneventhub.IEventHubConnectionManager;
import br.gov.caixa.utils.Util;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SimulacaoService {

    @Inject
    ProdutoService produtoService;

    private String CONNECTION_STRING_EVENT_HUB = "Endpoint=sb://eventhack.servicebus.windows.net/;SharedAccessKeyName=hack;SharedAccessKey=HeHeVaVqyVkntO2FnjQcs2Ilh/4MUDo4y+AEhKp8z+g=;EntityPath=simulacoes";

    
    public ArrayList<ResultadoSimulacaoDto> retornaCalculosSimulacao(EntradaSimulacaoEmprestimoDto simulacao)
            throws JsonProcessingException {

        // Retorno uma lista de produtos, pois no futuro podem existir mais de um
        // produto possivel para o financiamento
        List<ProdutoModel> produtos = produtoService.retornaProdutosEmprestimo(simulacao.getPrazo(), simulacao.getValorDesejado());

        // Instancia o objeto que armazenará o resultado da Simulação
        ArrayList<ResultadoSimulacaoDto> resultadosSimulacao = new ArrayList<ResultadoSimulacaoDto>();

        // Itero na lista de produtos validos.
        for (ProdutoModel produto : produtos) {

            // Chama os metodos que estão na classe util para calcula o financiamento SAC e
            // PRICE
            ArrayList<ParcelaDto> parcelasPrice = Util.calculadoraPRICE(produto, simulacao);
            ArrayList<ParcelaDto> ParcelasSAC = Util.calculadoraSAC(produto, simulacao);

            // Instancio o ArrayList de Parcelas Qualificadas que receberão o rotulo do tipo
            // de financiamento
            ArrayList<ParcelaQualificadaDto> listRetornoParcelasQualificadas = new ArrayList<ParcelaQualificadaDto>();

            // Retorna o calculo das parcelas qualificadas - SAC e PRICE
            ParcelaQualificadaDto parcelaQualificadaPRICE = new ParcelaQualificadaDto(TipoSimulacaoDto.PRICE,
                    parcelasPrice);
            ParcelaQualificadaDto parcelaQualificadaSAC = new ParcelaQualificadaDto(TipoSimulacaoDto.SAC, ParcelasSAC);

            // Adiciono no list de Parcelas qualificadas
            listRetornoParcelasQualificadas.add(parcelaQualificadaSAC);
            listRetornoParcelasQualificadas.add(parcelaQualificadaPRICE);

            // Gero o objeto de retorno das simulacoes
            ResultadoSimulacaoDto resultadoSimulacao = new ResultadoSimulacaoDto(produto,
                    listRetornoParcelasQualificadas);

            // Adiciona o resultados da Simulacao no objeto, pois podem existir mais de um
            // produto que atenda os os parametros de financiamento.
            resultadosSimulacao.add(resultadoSimulacao);
        }

        // Instancio o Connection Manager do EventHub
        IEventHubConnectionManager connectionManager = new EventHubConnectionManagerImpl(CONNECTION_STRING_EVENT_HUB);
        EventHubRepository eventHubRepository = new EventHubRepository(connectionManager);
        eventHubRepository.publishEvents(resultadosSimulacao);

        return resultadosSimulacao;
    }

}
