package school.sptech.vannbora.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.vannbora.entidade.Responsavel;

import java.util.Optional;

public interface ResponsavelRepository extends JpaRepository<Responsavel, Integer> {
    Optional<Responsavel> findByCpf(String cpf);
}
