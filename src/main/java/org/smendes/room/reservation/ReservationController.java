package org.smendes.room.reservation;

import org.smendes.room.reservation.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller responsavel por expor as rest APIs.
 * @author mendes
 *
 */
@RestController
public class ReservationController {
 
	@Autowired
	ReservationService reservationService;
	
    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index.html");
    }
    
    /**
     * Salvar uma nova reserva
     * @param reservation Objeto com os dados da reserva
     * @return HttpStatus.OK ou HttpStatus.NOT_ACCEPTABLE
     */
    @RequestMapping(value = "/reservations/save/", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Reservation reservation) {
 
    	Reservation newReservation = null;
    	if(reservationService.validate(reservation)) {
    		reservationService.save(reservation);
        	return new ResponseEntity<Reservation>(newReservation, HttpStatus.OK);
    	} else {
    		return new ResponseEntity<Reservation>(newReservation, HttpStatus.NOT_ACCEPTABLE);
    	}
    }

}
