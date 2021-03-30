/**
 * 
 */

$('document').ready(function() {
	
	$('.table .btn-primary').on('click',function(event){		
		event.preventDefault();		
		var href= $(this).attr('href');		
		$.get(href, function(peminjaman, status){
			$('#idPinjamanEdit').val(peminjaman.id_pinjaman);
			$('#anggotaidEdit').val(peminjaman.anggotaid);
			$('#bukuidEdit').val(peminjaman.bukuid);
			var tanggal_pinjam = peminjaman.tanggal_pinjam.substr(0,10);
			$('#tanggalPinjamEdit').val(peminjaman.tanggal_pinjam);
			var tanggal_kembali = peminjaman.tanggal_kembali.substr(0,10);
			$('#tanggalKembaliEdit').val(peminjaman.tanggal_kembali);
		});
		$('#editModal').modal();		
	});
	
	$('.table #detailsButton').on('click',function(event) {
		event.preventDefault();		
		var href= $(this).attr('href');		
		$.get(href, function(peminjaman, status){
			$('#idPinjamanDetails').val(peminjaman.id_pinjaman);
            $('#anggotaidDetails').val(peminjaman.anggotaid);
            $('#bukuidDetails').val(peminjaman.bukuid);
            $('#tanggalPinjamDetails').val(peminjaman.tanggal_pinjam);
            $('#tanggalKembaliDetails').val(peminjaman.tanggal_kembali);
		});			
		$('#detailsModal').modal();		
	});	
	
	$('.table #deleteButton').on('click',function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$('#deleteModal #delRef').attr('href', href);
		$('#deleteModal').modal();		
	});


});