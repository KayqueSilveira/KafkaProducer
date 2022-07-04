package crud.with.kafka.controller;

import crud.with.kafka.datagateway.ClienteDatagateway;
import crud.with.kafka.model.Cliente;
import crud.with.kafka.service.EnviarDadosCliente;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {


  private final EnviarDadosCliente enviarDadosCliente;

  public ClienteController(ClienteDatagateway dadosCliente,
      EnviarDadosCliente enviarDadosCliente) {
    this.enviarDadosCliente = enviarDadosCliente;
  }

  @PostMapping(value = "criar/cliente")
  public Cliente create(@RequestBody Cliente cliente) {
    return enviarDadosCliente.criarCliente(cliente);
  }

}
