
$('document').ready(function(){
	
	$('table #editButton').on('click',function(event){
		event.preventDefault();
			
		var href = $(this).attr('href');
		
		$.get(href, function(buku, status){
			$('#idEdit').val(buku.id_buku);
			$('#judulEdit').val(buku.judul);
			$('#pengarangEdit').val(buku.pengarang);
			$('#penerbitEdit').val(buku.penerbit);
			$('#jumlahEdit').val(buku.jumlah);
		});
		$('#editModal').modal();
	});
			$('.table #detailsButton').on('click',function(event) {
    			event.preventDefault();
    			var href= $(this).attr('href');
    			$.get(href, function(buku, status){
    				$('#id_bukuDetails').val(buku.id_buku);
    				$('#judulDetails').val(buku.judul);
    				$('#pengarangDetails').val(buku.pengarang);
    				$('#penerbitDetails').val(buku.penerbit);
    				$('#jumlahDetails').val(buku.jumlah);
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