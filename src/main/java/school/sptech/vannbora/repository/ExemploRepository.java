package school.sptech.vannbora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import school.sptech.vannbora.entidade.Exemplo;

@Repository
public interface ExemploRepository extends JpaRepository<Exemplo, Integer> {}
