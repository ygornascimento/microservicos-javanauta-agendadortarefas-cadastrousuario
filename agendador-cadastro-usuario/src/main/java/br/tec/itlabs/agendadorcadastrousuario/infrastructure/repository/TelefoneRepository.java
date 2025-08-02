package br.tec.itlabs.agendadorcadastrousuario.infrastructure.repository;

import br.tec.itlabs.agendadorcadastrousuario.infrastructure.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

}
