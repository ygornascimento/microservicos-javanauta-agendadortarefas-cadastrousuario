package br.tec.itlabs.agendadorcadastrousuario.infrastructure.repository;

import br.tec.itlabs.agendadorcadastrousuario.infrastructure.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
