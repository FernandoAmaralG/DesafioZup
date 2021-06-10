package com.zup.desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zup.desafio.entities.Usuario;
import com.zup.desafio.entities.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

	@Query("SELECT v FROM Veiculo v WHERE v.usuario = :usuario")
	public List<Veiculo> listar(@Param("usuario") Usuario usuario);

	@Query("SELECT v FROM Veiculo v WHERE v.id = :id and v.usuario = :usuario")
	public Veiculo obter(@Param("id") Long id, @Param("usuario") Usuario usuario);

}
