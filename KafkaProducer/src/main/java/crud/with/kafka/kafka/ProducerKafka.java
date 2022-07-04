package crud.with.kafka.kafka;

import crud.with.kafka.datagateway.ClienteDatagateway;
import crud.with.kafka.model.Cliente;
import java.util.Properties;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProducerKafka {

  private final Producer<String, String> producer;

  public ProducerKafka() {
    this.producer = criarProducer();
  }

  public Producer<String, String> criarProducer(){
    if(producer != null){
      return producer;
    }

    Properties properties = new Properties();
    properties.put("bootstrap.servers", "localhost:9092");
    properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    properties.put("serializer.class", "kafka.serializer.DefaultEncoder");
    return new KafkaProducer<String, String>(properties);
  }

  public void executar(Cliente cliente){
    String chave = UUID.randomUUID().toString();

    log.info("Iniciando envio da mensagem");
    ProducerRecord<String, String> record = new ProducerRecord<String, String>("RegistroEvento", chave, cliente.toString());
    producer.send(record);
    producer.flush();
    producer.close();
    log.info("mensagem enviada com sucesso [{}]", cliente.getName());
  }

  public String enviarMensagem() {
    return "";
  }
}
