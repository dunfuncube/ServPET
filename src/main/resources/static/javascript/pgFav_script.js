// 使用 SweetAlert2 進行取消收藏
function openCancelModal(pgFavId) {
	Swal.fire({
		title: '確定要取消收藏此位美容師嗎？',
		text: '此操作無法復原！',
		icon: 'warning',
		showCancelButton: true,
		confirmButtonText: '確定',
		cancelButtonText: '取消',
		reverseButtons: true
	}).then((result) => {
		if (result.isConfirmed) {
			deleteFavorite(pgFavId);
		}
	});
}

// 發送請求取消收藏
function deleteFavorite(pgFavId) {
	$.ajax({
		url: '/pgFav/deleteFavorite',
		type: 'POST',
		data: { pgFavId: pgFavId },
		success: function(response) {
			Swal.fire({
				icon: 'success',
				title: '成功！',
				text: '美容師收藏已取消！'
			}).then(() => {
				location.reload(); // 重新加載頁面
			});
		},
		error: function() {
			Swal.fire({
				icon: 'error',
				title: '錯誤！',
				text: '取消失敗，請稍後再試！'
			});
		}
	});
}