package br.org.serratec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.serratec.model.Perfil;



@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long>{

}
