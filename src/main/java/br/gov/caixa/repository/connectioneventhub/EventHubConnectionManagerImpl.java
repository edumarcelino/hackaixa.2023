package br.gov.caixa.repository.connectioneventhub;

import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.messaging.eventhubs.EventHubProducerAsyncClient;

public class EventHubConnectionManagerImpl implements IEventHubConnectionManager {
    
    // Assegura de ter apenas um eventHubProducerClient
    private static EventHubProducerAsyncClient eventHubProducerClient;

    public EventHubConnectionManagerImpl(String connectionString) {
        if (eventHubProducerClient == null) {
            eventHubProducerClient = new EventHubClientBuilder().connectionString(connectionString)
                    .buildAsyncProducerClient();
        }
    }

    @Override
    public EventHubProducerAsyncClient getEventHubProducerClient() {
        return eventHubProducerClient;
    }
}