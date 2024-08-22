
$(document).ready(function() {
	loadGuests();

	$('#guestForm').submit(function(event) {
		event.preventDefault();
		const id = $('#guestId').val();
		if (id) {
			updateGuest(id);
		} else {
			addGuest();
		}
	});
});

function loadGuests() {
	$.getJSON('/api/guests', function(data) {
		$('#guestTableBody').empty();
		data.forEach(guest => {
			$('#guestTableBody').append(
				`<tr>
        <td>${guest.id}</td>
        <td>${guest.name}</td>
        <td>${guest.email}</td>
        <td>${guest.telefone}</td>
        <td>${guest.rua}</td>
        <td>${guest.cidade}</td>
        <td>${guest.estado}</td>
        <td>${guest.numero}</td>
        <td>${guest.cep}</td>
        <td>${guest.cpf}</td>
        <td>
            <button class="btn btn-sm btn-warning" onclick='showEditGuestForm(${JSON.stringify(guest)})'>Edit</button>
            <button class="btn btn-sm btn-danger" onclick="deleteGuest(${guest.id})">Delete</button>
        </td>
     </tr>`
			);
		});
	});
}

function showAddGuestForm() {
	$('#formTitle').text('Add hospede');
	$('#guestId').val('');
	$('#guestName').val('');
	$('#guestEmail').val('');
	$('#guestPhone').val('');
	$('#guestStreet').val('');
	$('#guestCity').val('');
	$('#guestState').val('');
	$('#guestNumber').val('');
	$('#guestCEP').val('');
	$('#guestCPF').val('');
	$('#guestFormModal').show();
}

function showEditGuestForm(guest) {
	$('#formTitle').text('Edit Hospede');
	$('#guestId').val(guest.id);
	$('#guestName').val(guest.name);
	$('#guestEmail').val(guest.email);
	$('#guestPhone').val(guest.telefone);
	$('#guestStreet').val(guest.rua);
	$('#guestCity').val(guest.cidade);
	$('#guestState').val(guest.estado);
	$('#guestNumber').val(guest.numero);
	$('#guestCEP').val(guest.cep);
	$('#guestCPF').val(guest.cpf);
	$('#guestFormModal').show();
}


function closeGuestForm() {
	$('#guestFormModal').hide();
}

function addGuest() {
	const guest = {
		name: $('#guestName').val(),
		email: $('#guestEmail').val(),
		telefone: $('#guestPhone').val(),
		rua: $('#guestStreet').val(),
		cidade: $('#guestCity').val(),
		estado: $('#guestState').val(),
		numero: $('#guestNumber').val(),
		cep: $('#guestCEP').val(),
		cpf: $('#guestCPF').val()
	};
	$.ajax({
		url: '/api/guests',
		type: 'POST',
		contentType: 'application/json',
		data: JSON.stringify(guest),
		success: function() {
			closeGuestForm();
			loadGuests();
		}
	});
}

function updateGuest(id) {
	const guest = {
		name: $('#guestName').val(),
		email: $('#guestEmail').val(),
		telefone: $('#guestPhone').val(),
		rua: $('#guestStreet').val(),
		cidade: $('#guestCity').val(),
		estado: $('#guestState').val(),
		numero: $('#guestNumber').val(),
		cep: $('#guestCEP').val(),
		cpf: $('#guestCPF').val()
	};
	$.ajax({
		url: `/api/guests/${id}`,
		type: 'PUT',
		contentType: 'application/json',
		data: JSON.stringify(guest),
		success: function() {
			closeGuestForm();
			loadGuests();
		}
	});
}

function deleteGuest(id) {
	if (confirm('Realmente vai deletar?')) {
		$.ajax({
			url: `/api/guests/${id}`,
			type: 'DELETE',
			success: function() {
				loadGuests();
			}
		});
	}
}
