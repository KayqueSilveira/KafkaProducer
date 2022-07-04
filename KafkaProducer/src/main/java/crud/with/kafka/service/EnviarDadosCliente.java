package crud.with.kafka.service;

import crud.with.kafka.datagateway.ClienteDatagateway;
import crud.with.kafka.kafka.ProducerKafka;
import crud.with.kafka.model.Cliente;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EnviarDadosCliente {

  @Autowired
  private ClienteDatagateway clienteDatagateway;
  @Autowired
  private ProducerKafka producerKafka;

  public Cliente criarCliente(final Cliente cliente){
    if(!existsClient(cliente)){
      clienteDatagateway.save(cliente);
      producerKafka.executar(cliente);
      return cliente;
    }
    throw new IllegalArgumentException("cliente ja existe");
  }

  public void deletarCliente(final Long id){
    clienteDatagateway.deleteById(id);
  }
  public boolean existsClient(Cliente cliente){
    return clienteDatagateway.existsById(cliente.getId());
  }


}
