package school.sptech.vannbora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.vannbora.entidade.RegistroFatura;

public interface RegistroFaturaRepository extends JpaRepository<RegistroFatura, Integer> {

    List<RegistroFatura> findAllByFaturaId(int faturaId);
    
}
