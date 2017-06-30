/**
 * Arquivo com as funcoes java script para o projeto. 
 */


/**
 * Formatar os campos de data para exibicao. 
 */
function formatDateToShow(date) {
	var dateAsString = "";
	
	var parts = date.split('T');

	// split date and time
	var date = parts[0];
	var time = parts[1];
	
	// split date parts
	var dateParts = date.split('-');
	dateAsString = dateParts[2] + "/" + dateParts[1] + "/" + dateParts[0] + " ";
	
	// add time part
	dateAsString += time;
	
	return dateAsString;
	
}

/**
 * Configurar campos quando a opcao de coffee break estiver ativa. 
 */
function setCoffeeBreak(checkBox) {
	if (!checkBox.checked) {
		document.getElementById("peopleToCoffeeBreak").value = 0;
		document.getElementById("peopleToCoffeeBreak").disabled = true;
	} else {
		document.getElementById("peopleToCoffeeBreak").disabled = false;
	}	
}

/**
 * Configurar checkbox docampo de coffee break. 
 */
function setHasCoffeeBreak(input) {
	if (input.value === "" || input.value <= 0) {
		document.getElementById("hasCoffeeBreak").checked = false;
	} else {
		document.getElementById("hasCoffeeBreak").checked = true;
	}	
}

/**
 * Configurar campos referentes a opcao de coffee break. 
 */
function configHasCoffeeBreak(people) {
	if (people <= 0) {
		document.getElementById("hasCoffeeBreak").checked = false;
		document.getElementById("peopleToCoffeeBreak").disabled = true;
	} else {
		document.getElementById("hasCoffeeBreak").checked = true;
		document.getElementById("peopleToCoffeeBreak").disabled = false;
	}	
}

/**
 * Configurar campos referentes a opcao de coffee break. 
 */
function printMessage(message) {
    document.getElementById("message").innerText = message;
}


/**
 * Buscar as reservas de sala cadastradas. 
 */
function find(message) {
	
	document.getElementById("key").value = -1;

	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			  
		  	var txt = "<tr><th>Local</th><th>Sala</th><th>Data e Hora de início</th><th>Data e Hora de fim</th><th>Responsável</th><th>Açao</th></tr>";
		  	var reservas = JSON.parse(this.responseText);
		  	
	        for (x in reservas._embedded.reservations) {
	            txt += "<tr>";
	        	txt += "<td>" + reservas._embedded.reservations[x].businessUnit + "</td>";
	        	txt += "<td>" + reservas._embedded.reservations[x].room + "</td>";
	        	txt += "<td>" + formatDateToShow(reservas._embedded.reservations[x].initDate) + "</td>";
	        	txt += "<td>" + formatDateToShow(reservas._embedded.reservations[x].endDate) + "</td>";
	        	txt += "<td>" + reservas._embedded.reservations[x].responsable + "</td>";
	        	txt += "<td> <a href='#' onclick='edit(" + reservas._embedded.reservations[x].key +");'>Editar</a> " +
	        			"	 <a href='#openModal' onclick='edit(" + reservas._embedded.reservations[x].key +");'>Remover</a> </td>";
	            txt += "</tr>";
	        }
	        document.getElementById("lista-reservas").innerHTML = txt;
	        
	        if (message === "") {
	        	printMessage("Total de Reservas: " + reservas._embedded.reservations.length);
	        } else {
	        	printMessage(message);
	        }
	        cancel();            
		}
	};	
	xhr.open("GET", "/api/reservations/", true);
	xhr.send();
}

/**
 * Editar uma reserva de sala. 
 */
function edit(id) {
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			  
		  	var reserva = JSON.parse(this.responseText);
			document.getElementById("key").value = reserva.key;
			document.getElementById("businessUnit").value = reserva.businessUnit;
			document.getElementById("room").value = reserva.room;
			document.getElementById("initDate").value = reserva.initDate;
			document.getElementById("endDate").value = reserva.endDate;
			document.getElementById("responsable").value = reserva.responsable;
			document.getElementById("description").value = reserva.description;
			document.getElementById("peopleToCoffeeBreak").value = reserva.peopleToCoffeeBreak;
			
			configHasCoffeeBreak(reserva.peopleToCoffeeBreak);
		}
	};	
	xhr.open("GET", "/api/reservations/" + id, true);
	xhr.send();
}

/**
 * Salvar uma reserva de sala. 
 */
function save() {

	var isOk = validate();
	if (!isOk) {
		return;
	}	
	
	var reserva ={};
    
    if (document.getElementById("key").value != -1) {
        reserva.id = document.getElementById("key").value;
    }
    reserva.key = document.getElementById("key").value;
    reserva.businessUnit = document.getElementById("businessUnit").value;
    reserva.room = document.getElementById("room").value;
    reserva.initDate = document.getElementById("initDate").value;
    reserva.endDate = document.getElementById("endDate").value;
    reserva.responsable = document.getElementById("responsable").value;
    reserva.description = document.getElementById("description").value;
    reserva.peopleToCoffeeBreak = document.getElementById("peopleToCoffeeBreak").value;

    var http = new XMLHttpRequest();
    var url = "/reservations/save/";
    http.open("POST", url, true);
    http.setRequestHeader("Content-type", "application/json");

    http.onreadystatechange = function() {
    	if (this.status == 406) {
        	printMessage("OOps... Sala já reservada nesse período.");
    	} else {
    		find("Reserva de sala efetuada com sucesso.")
    	}
    }
    http.send(JSON.stringify(reserva));    
    
}

/**
 * Validar uma reserva. 
 */
function validate() {
    
	if (document.getElementById("businessUnit").value === "") {
    	printMessage("O campo 'Local' é obrigatório");
		return false;
	}

	if (document.getElementById("room").value === "") {
    	printMessage("O campo 'Local' é obrigatório");
		return false;
	}
	
	if (document.getElementById("initDate").value === "") {
    	printMessage("O campo 'Data e Hora de início' é obrigatório");
		return false;
	}

	if (document.getElementById("endDate").value === "") {
    	printMessage("O campo 'Data e Hora de término' é obrigatório");
		return false;
	}

	if (document.getElementById("responsable").value === "") {
    	printMessage("O campo 'Responsável' é obrigatório");
		return false;
	}

	if (document.getElementById("description").value === "") {
    	printMessage("O campo 'Descricao' é obrigatório");
		return false;
	}
	return true;
}


/**
 * Remover uma reserva cadastrada. 
 */
function remove() {

    var reserva ={};
    
    reserva.key = document.getElementById("key").value;
    reserva.businessUnit = document.getElementById("businessUnit").value;
    reserva.room = document.getElementById("room").value;
    reserva.initDate = document.getElementById("initDate").value;
    reserva.endDate = document.getElementById("endDate").value;
    reserva.responsable = document.getElementById("responsable").value;
    reserva.description = document.getElementById("description").value;
    reserva.peopleToCoffeeBreak = document.getElementById("peopleToCoffeeBreak").value;

    var http = new XMLHttpRequest();
    var url = "/api/reservations/" + reserva.key;
    http.open("DELETE", url, true);
    http.setRequestHeader("Content-type", "application/json");

    http.onreadystatechange = function() {
    	find("Reserva de sala removida com sucesso.");
    }
    http.send(JSON.stringify(reserva));    
    
}


/**
 * Cancelar uma edicao de reserva. 
 */
function cancel() {
	document.getElementById("key").value = -1;
    document.getElementById("businessUnit").value = "";
    document.getElementById("room").value = "";
    document.getElementById("initDate").value = "";
    document.getElementById("endDate").value = "";
    document.getElementById("responsable").value = "";
    document.getElementById("description").value = "";
    
    document.getElementById("hasCoffeeBreak").checked = false;
    document.getElementById("peopleToCoffeeBreak").value = "0";
    document.getElementById("peopleToCoffeeBreak").disabled = true;
}




