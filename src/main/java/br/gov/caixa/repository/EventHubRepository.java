package br.gov.caixa.repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.azure.core.http.ContentType;
import com.azure.messaging.eventhubs.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.caixa.dto.ResultadoSimulacaoDto;
import br.gov.caixa.repository.connectioneventhub.IEventHubConnectionManager;


public class EventHubRepository {

    // Cria um connection manager 
    private IEventHubConnectionManager connectionManager;


    public EventHubRepository(IEventHubConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public void publishEvents(ArrayList<ResultadoSimulacaoDto> resultado) throws JsonProcessingException {
        EventHubProducerAsyncClient producer = connectionManager.getEventHubProducerClient();

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");

        // Crio um mapper para poder transformar o ArrayList em Json.
        ObjectMapper mapper = new ObjectMapper();
        String jsonResultados;

        // Cria o JsonResultado a partir dos resultadoSimulacao passado como parametro
        jsonResultados = mapper.writeValueAsString(resultado);

        producer.createBatch().flatMap(batch -> {

            EventData eventData = new EventData(jsonResultados);
            eventData.setContentType(ContentType.APPLICATION_JSON);
            batch.tryAdd(eventData);

            return producer.send(batch);
        }).subscribe(unused -> {},
                error -> System.err.println(f.format(new Date()) + " ERROR Aconteceu um erro: " + error),
                () -> System.out.println(f.format(new Date()) + " SUCCESS JSON Enviado para o EventHub com sucesso."));
    }
}