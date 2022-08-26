package repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{
	public Optional<Admin> findByLoginUsuario(String admin);

	public List<Admin> findAllByNomeUsuarioContainingIgnoreCase(String admin);
}
