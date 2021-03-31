
$('document').ready(function(){
	
	$('table #editButton').on('click',function(event){
		event.preventDefault();
			
		var href = $(this).attr('href');
		
		$.get(href, function(pengembalian, status){
			$('#idPengembalianEdit').val(pengembalian.id_kembali);
			$('#namaEdit').val(pengembalian.anggotaid);
			$('#tanggalKembaliEdit').val(pengembalian.pinjamanid);
			$('#terlambatEdit').val(pengembalian.terlambat);
			$('#jumlahEdit').val(pengembalian.jumlah_denda);
		});					
		$('#editModal').modal();
	});

	$('.table #detailsButton').on('click',function(event) {
		event.preventDefault();		
		var href= $(this).attr('href');		
		$.get(href, function(pengembalian, status){
			$('#idPengembalianDetails').val(pengembalian.id_kembali);
			$('#namaDetails').val(pengembalian.anggotaid);
			$('#tanggalKembaliDetails').val(pengembalian.pinjamanid);
			$('#terlambatDetails').val(pengembalian.terlambat);
			$('#jumlahDetails').val(pengembalian.jumlah_denda);
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