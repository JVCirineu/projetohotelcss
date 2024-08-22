
$(document).ready(function() {
	loadReservas();

	$('#reservaForm').submit(function(event) {
		event.preventDefault();
		const id = $('#reservaId').val();
		if (id) {
			updateReserva(id);
		} else {
			addReserva();
		}
	});
});

function loadReservas() {
	$.getJSON('/api/reservas', function(data) {
		$('#reservaTableBody').empty();
		data.forEach(reserva => {
			$('#reservaTableBody').append(
				`<tr>
						<td>${reserva.id}</td>
						<td>${reserva.guest.name}</td>
						<td>${reserva.room.number}</td>
                        <td>${reserva.dataCheckin}</td>
                        <td>${reserva.dataCheckout}</td>
                          
                            <td>
                                <button class="btn btn-sm btn-warning" onclick="showEditReservaForm('${reserva.id}','${reserva.guest.id}','${reserva.room.id}','${reserva.dataCheckin}', '${reserva.dataCheckout}')">Edit</button>
                                <button class="btn btn-sm btn-danger" onclick="deleteReserva(${reserva.id})">Delete</button>
                            </td>
                 </tr>`
			);
		});
	});
}

function showAddReservaForm() {
	$('#formTitle').text('Add Reserva');
	$('#reservaId').val('');
	$('#reservaGuest').val('');
	$('#reservaRoom').val('');
	$('#reservaDateIn').val('');

	$('#reservaDateOut').val('');

	$('#reservaFormModal').show();
}


function showEditReservaForm(id,guestName, roomNumber, dataCheckin, dataCheckout) {
	$('#formTitle').text('Edit Reserva');
	$('#reservaId').val(id);
	$('#reservaGuest').val(guestName);
	$('#reservaRoom').val(roomNumber);
	$('#reservaDateIn').val(dataCheckin);
	$('#reservaDateOut').val(dataCheckout);
	$('#reservaFormModal').show();
}

function closeReservaForm() {
	$('#reservaFormModal').hide();
}

function addReserva() {
	const reserva = {
		guest: { id: $('#reservaGuest').val() },
		room: { id: $('#reservaRoom').val() },
		dataCheckin: $('#reservaDate').val(),
		dataCheckout: $('#reservaDate').val()
	};
	console.log(reserva); // Verifique o valor aqui
	$.ajax({
		url: '/api/reservas',
		type: 'POST',
		contentType: 'application/json',
		data: JSON.stringify(reserva),
		success: function() {
			closeReservaForm();
			loadReservas();
		}
	});
}


function updateReserva(id) {
	const reserva = {
		guestName: $('#reservaGuest').val(),
		roomNumber: $('#reservaRoom').val(),
		dataCheckin: $('#reservaDateIn').val(),
		dataCheckin: $('#reservaDateOut').val()

	};
	$.ajax({
		url: `/api/reservas/${id}`,
		type: 'PUT',
		contentType: 'application/json',
		data: JSON.stringify(reserva),
		success: function() {
			closeReservaForm();
			loadReservas();
		}
	});
}

function deleteReserva(id) {
	if (confirm('Deseja deletar o quarto?')) {
		$.ajax({
			url: `/api/reservas/${id}`,
			type: 'DELETE',
			success: function() {
				loadReservas();
			}
		});
	}
}
