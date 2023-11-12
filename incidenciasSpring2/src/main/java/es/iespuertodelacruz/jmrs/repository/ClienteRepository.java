package es.iespuertodelacruz.jmrs.repository;

import org.springframework.data.jpa.repository.*;
import es.iespuertodelacruz.jmrs.entity.Cliente;

public interface ClienteRepository extends JpaRepository <Cliente, String>{

}
