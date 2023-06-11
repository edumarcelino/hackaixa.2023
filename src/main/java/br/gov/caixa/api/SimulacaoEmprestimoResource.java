package br.gov.caixa.api;

import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.gov.caixa.dto.EntradaSimulacaoEmprestimoDto;
import br.gov.caixa.dto.ResultadoSimulacaoDto;
import br.gov.caixa.exception.CustomException;
import br.gov.caixa.messages.Mensagens;
import br.gov.caixa.service.SimulacaoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/v1/financiamento")
public class SimulacaoEmprestimoResource {

    @Inject
    SimulacaoService simulacaoController;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/simular")
    public ArrayList<ResultadoSimulacaoDto> simularEmprestimo(EntradaSimulacaoEmprestimoDto simulacao) {

        // Validações para gerar exception customizadas.
        if(simulacao.getPrazo() == null){
            throw new CustomException(Mensagens.PRAZO_NAO_PODE_SER_NULO);
        }
        if(simulacao.getValorDesejado() == null){
            throw new CustomException(Mensagens.VALOR_NAO_PODE_SER_NULO);
        }
        
        if (simulacao.getPrazo() <= 0) {
            throw new CustomException(Mensagens.PRAZO_MAIOR_QUE_ZERO);
        }
        if (simulacao.getValorDesejado().doubleValue() <= 0) {
            throw new CustomException(Mensagens.VALOR_MAIOR_QUE_ZERO);
        }

    

        ArrayList<ResultadoSimulacaoDto> resultado;
        try {
            resultado = simulacaoController.retornaCalculosSimulacao(simulacao);
            if (resultado.isEmpty()) {
                throw new CustomException(Mensagens.NAO_ENCONTRADO_PRODUTO_FINANCIAMENTO);
            } else {
                return resultado;
            }
        } catch (JsonProcessingException e) {
            throw new CustomException(Mensagens.ERRO_PROCESSAMENTO_JSON);
        }

    }

}
