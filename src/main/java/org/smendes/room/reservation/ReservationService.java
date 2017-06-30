package org.smendes.room.reservation;

import java.util.List;

import org.smendes.room.reservation.entity.Reservation;
import org.smendes.room.reservation.exception.AlreadyBooked;
import org.smendes.room.reservation.repository.ReservationRepository;
import org.smendes.room.reservation.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servico responsavel por tratar as regras de negocio das reservas de sala.
 * @author mendes
 *
 */
@Service
public class ReservationService {
	
	@Autowired
	ReservationRepository reservationRepository;
	
	/**
	 * Salvar uma reserva de sala.
	 * @param reservation Reserva de sala para salvar.
	 * @return Reserva de sala salva.
	 */
	public Reservation save(Reservation reservation) {
		Reservation newReservation = reservationRepository.save(reservation);
		return newReservation;
	}

	/**
	 * Validacoes das regras de negocio para reservar uma sala.
	 * @param reservation Reserva de sala para salvar.
	 * @return Reserva de sala salva.
	 */
	public boolean validate(Reservation reference) {


		//Obter a lista de reservas para uma unidade e sala.
		List<Reservation> items = 
				reservationRepository.findByBusinessUnitAndRoom(reference.getBusinessUnit(), reference.getRoom());
		
		for (Reservation reservation : items) {
			
			// Desconsiderar o mesmo item para possibilitar edicoes
			if (reference.getKey() != null && reference.getKey().equals(reservation.getId())) {
				continue;
			}
			
			
			// Evitar interseccoes nas reservas de salas (reservas 2x a mesma sala ao mesmo tempo).
			boolean beginDateIsBetween = 
					DateUtil.dateIsBetween(reference.getInitDate(), reservation.getInitDate(), reservation.getEndDate());
			
			boolean endDateIsBetween = 
					DateUtil.dateIsBetween(reference.getEndDate(), reservation.getInitDate(), reservation.getEndDate());
			
			if (beginDateIsBetween || endDateIsBetween) {
				throw new AlreadyBooked(reference.toString());
			}
			
		}
		return true;
	}
}
