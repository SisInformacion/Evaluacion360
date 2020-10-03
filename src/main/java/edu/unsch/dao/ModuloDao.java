package edu.unsch.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.unsch.entities.Modulo;

@Repository
public interface ModuloDao extends JpaRepository<Modulo, Serializable>{
	/*
	 * new Modulo(m.idmodulo as idmodulo, m.nombre as nombre, m.icono as icono) 
	 */
	@Query(value = "SELECT m "
			+ "FROM PerfilOpcion po "
			+ "INNER JOIN po.opcion o "
			+ "INNER JOIN o.submodulo s "
			+ "INNER JOIN s.modulo m "
			+ "INNER JOIN po.perfil pe "
			+ "WHERE pe.idperfil = SOME ("
									+ "SELECT p.idperfil "
									+ "FROM Perfil as p "
									+ "INNER JOIN p.usuarioPerfils up "
									+ "INNER JOIN up.usuario u "
									+ "WHERE u.usuario = ?1 "
									+ ") "
			+ "GROUP BY m.idmodulo")
	List<Modulo> listaModulos(String usuario);
}
