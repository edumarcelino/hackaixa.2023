package br.gov.caixa.repository.connectioneventhub;
import com.azure.messaging.eventhubs.EventHubProducerAsyncClient;

public interface IEventHubConnectionManager {
    EventHubProducerAsyncClient getEventHubProducerClient();
}
