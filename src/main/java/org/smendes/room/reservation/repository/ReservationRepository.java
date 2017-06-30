package org.smendes.room.reservation.repository;

import java.util.List;

import org.smendes.room.reservation.entity.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Repositorio de manipulacao aos dados das reservas de sala.
 * @author mendes
 *
 */
@RepositoryRestResource
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

	/**
	 * Obter a lista de reservas de salas.
	 * @param businessUnit Unidade de negocio (local)
	 * @param room Sala
	 * @return Lista de reservas
	 */
	List<Reservation> findByBusinessUnitAndRoom(String businessUnit, String room);
	
}