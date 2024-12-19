// 取消訂單的 SweetAlert2 燈箱
let currentPdoId = null;

function openCancelModal(pdoId) {
    currentPdoId = pdoId;

    Swal.fire({
        title: '確定要取消此筆訂單嗎？',
        text: '訂單取消後將無法復原！',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: '確認取消',
        cancelButtonText: '返回',
        reverseButtons: true
    }).then((result) => {
        if (result.isConfirmed) {
            confirmCancel();
        }
    });
}

function confirmCancel() {
    if (currentPdoId) {
        fetch(`/mebPdo/${currentPdoId}/cancel`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' }
        })
            .then((response) => response.json())
            .then((data) => {
                if (data.success) {
                    Swal.fire('已取消', data.message, 'success').then(() => {
                        // 更新整列外觀
                        const row = document.querySelector(`tr[data-pdoid="${currentPdoId}"]`);
                        if (row) {
                            row.classList.add('cancelled-row');
                            row.querySelectorAll('button').forEach((btn) => {
                                btn.classList.add('disabled-btn');
                                btn.disabled = true;
                            });
                        }
                    });
                } else {
                    Swal.fire('錯誤！', data.message || '請稍後再試', 'error');
                }
            })
            .catch((error) => {
                console.error('Error:', error);
                Swal.fire('錯誤！', '發生錯誤，請稍後再試', 'error');
            });
    }
}



// 修改地址的 SweetAlert2 燈箱
let currentEditPdoId = null;

function openEditAddressModal(pdoId, currentAddress) {
    currentEditPdoId = pdoId;

    Swal.fire({
        title: '請輸入新的配送地址：',
        input: 'text',
        inputValue: currentAddress, // 預設當前地址
        showCancelButton: true,
        confirmButtonText: '確認',
        cancelButtonText: '取消',
        preConfirm: (newAddress) => {
            if (!newAddress) {
                Swal.showValidationMessage('地址不能為空');
                return false;
            }
            return newAddress; // 返回輸入的值
        }
    }).then((result) => {
        if (result.isConfirmed) {
            confirmUpdateAddress(result.value); // 呼叫確認更新地址
        }
    });
}

function confirmUpdateAddress(newAddress) {
    if (currentEditPdoId) {
        fetch(`/mebPdo/${currentEditPdoId}/updateAddress`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: `newAddress=${encodeURIComponent(newAddress)}`
        })
        .then(response => {
            return response.text().then(message => {
                if (response.ok) {
                    Swal.fire({
                        icon: 'success',
                        title: '成功！',
                        text: message // 使用後端返回的成功訊息
                    }).then(() => {
                        location.reload(); // 重新加載頁面
                    });
                } else {
                    Swal.fire({
                        icon: 'error',
                        title: '錯誤！',
                        text: message // 使用後端返回的錯誤訊息
                    });
                }
            });
        })
        .catch(error => {
            console.error('Error:', error);
            Swal.fire({
                icon: 'error',
                title: '錯誤！',
                text: '發生錯誤，請稍後再試'
            });
        });
    }
}
