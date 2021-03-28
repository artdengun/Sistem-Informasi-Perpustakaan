
$('document').ready(function(){
	
	$('table #editButton').on('click',function(event){
		event.preventDefault();
			
		var href = $(this).attr('href');
		
		$.get(href, function(anggota, status){
			$('#idEdit').val(anggota.id_anggota);
			$('#namaEdit').val(anggota.nama);
			$('#nimEdit').val(anggota.nim);
			$('#jurusanEdit').val(anggota.jurusan);
			$('#angkatanEdit').val(anggota.angkatan);
			$('#alamatEdit').val(anggota.alamat);
		});					
		$('#editModal').modal();
	});
			$('.table #detailsButton').on('click',function(event) {
    			event.preventDefault();
    			var href= $(this).attr('href');
    			$.get(href, function(anggota, status){
    				$('#id_anggotaDetails').val(anggota.id_anggota);
    				$('#namaDetails').val(anggota.nama);
    				$('#nimDetails').val(anggota.nim);
    				$('#jurusanDetails').val(anggota.jurusan);
    				$('#angkatanDetails').val(anggota.angkatan);
    				$('#alamatDetails').val(anggota.alamat);
    			});
    			$('#detailsModal').modal();
    		});
	$('table #deleteButton').on('click', function(event){
		event.preventDefault();
		
		var href= $(this).attr('href');
		
		$('#confirmDeleteButton').attr('href', href);
		
		$('#deleteModal').modal();
	});
		
});