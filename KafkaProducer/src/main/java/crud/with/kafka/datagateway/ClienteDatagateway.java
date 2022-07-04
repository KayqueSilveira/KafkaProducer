package crud.with.kafka.datagateway;


import crud.with.kafka.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteDatagateway extends JpaRepository<Cliente, Long> {

}
