package org.smendes.room.reservation.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Entidade para armazenar as reservas de sala.
 * @author mendes
 *
 */
@Entity
public class Reservation {

	private @Id @GeneratedValue Long id;	
	private String businessUnit;
	private String room;
	private String initDate;
	private String endDate;
	private String responsable;
	private String description;
	private Integer peopleToCoffeeBreak;
	private Long key;	
	
	public Reservation() {
		super();
	}

	public Reservation(String businessUnit, String room, String initDate, String endDate, String responsable, String description,
			Integer peopleToCoffeeBreak) {
		super();
		this.businessUnit = businessUnit;
		this.room = room;
		this.initDate = initDate;
		this.endDate = endDate;
		this.description = description;
		this.responsable = responsable;
		this.peopleToCoffeeBreak = peopleToCoffeeBreak;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", businessUnit=" + businessUnit + ", room=" + room + ", initDate=" + initDate
				+ ", endDate=" + endDate + ", description=" + description + ", responsable=" + responsable +  ", peopleToCoffeeBreak="
				+ peopleToCoffeeBreak + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getInitDate() {
		return initDate;
	}

	public void setInitDate(String initDate) {
		this.initDate = initDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPeopleToCoffeeBreak() {
		return peopleToCoffeeBreak;
	}

	public void setPeopleToCoffeeBreak(Integer peopleToCoffeeBreak) {
		this.peopleToCoffeeBreak = peopleToCoffeeBreak;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public Long getKey() {
		return id;
	}

	public void setKey(Long key) {
		this.key = key;
	}
	
}