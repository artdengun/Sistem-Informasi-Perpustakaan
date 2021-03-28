	$('document').ready(function() {

		$('.table .btn-primary').on('click',function(event){
			event.preventDefault();
			var href= $(this).attr('href');
			$.get(href, function(peminjaman, status){
				$('#id_peminjamanEdit').val(peminjaman.id_pinjaman);
				$('#anggotaEdit').val(peminjaman.anggota_id);
				$('#bukuEdit').val(peminjaman.buku_id);
				$('#tanggal_pinjamEdit').val(peminjaman.tanggal_pinjam);
				$('#tanggal_kembaliEdit').val(peminjaman.tanggal_kembali);
			});
			$('#editModal').modal();
		});

		$('.table #deleteButton').on('click',function(event) {
			event.preventDefault();
			var href = $(this).attr('href');
			$('#deleteModal #delRef').attr('href', href);
			$('#deleteModal').modal();
		});
	});
