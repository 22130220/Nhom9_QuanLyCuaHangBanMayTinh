$(document).ready(function () {
    $.ajax({
        url: '/api/stock-in',
        method: 'GET',
        success: function (data) {
            let tbody = $('#dataTable tbody');
            tbody.empty();

            data.forEach(function (item) {
                let row = `
                        <tr>
                            <td>${item.id}</td>
                            <td>${item.executor}</td>
                            <td>${item.executionDate}</td>
                            <td>${item.supplierName}</td>
                            <td>${item.note || ''}</td>
                            <td>
                                <button class="btn btn-info btn-sm">Xem</button>
                                <button class="btn btn-danger btn-sm">Xóa</button>
                            </td>
                        </tr>`;
                tbody.append(row);
            });
        },
        error: function (err) {
            alert("Không thể tải danh sách!");
            console.log(err);
        }
    });
});
let cachedProducts = null;

// 13.1.2 - Hiển thị form
$('#stockInModal').on('show.bs.modal', function () {
    // 13.1.3 Hệ thống gọi ProductController để lấy danh sách sản phẩm.
    $.get('http://localhost:8081/products/api/products', function (data) {
        cachedProducts = data;

        // 13.1.5 - Gán dữ liệu vào modal
        renderProductsForAllRows(data);
    });
    // 13.1.4. Hệ thống gọi SupplierController để lấy danh sách nhà cung cấp.
    $.get('http://localhost:8081/stockIO/api/suppliers', function (data) {

        // 13.1.5 - Gán dữ liệu vào modal
        renderSuppliers(data);
    });

});

function renderSuppliers(data) {
    const supplierSelect = $('#supplierSelect');
    supplierSelect.empty();
    supplierSelect.append('<option value="">-- Chọn nhà cung cấp --</option>');
    data.forEach(s => {
        supplierSelect.append($('<option>', {
            value: s.id,
            text: s.note || 'Tên nhà cung cấp trống'
        }));
    });
}


function renderProductsForAllRows(data) {
    $('.productSelect').each(function () {
        const select = $(this);
        select.empty();
        select.append('<option value="">-- Chọn sản phẩm --</option>');
        data.forEach(p => {
            select.append($('<option>', {
                value: p.id,
                text: p.note || 'Tên sản phẩm trống'
            }));
        });
    });
}


const productTableBody = document.getElementById("productTableBody");


function createProductRow() {
    const tr = document.createElement("tr");
    tr.classList.add("product-row");

    tr.innerHTML =
        '<td>' +
        '<select class="form-control productSelect" name="productIds[]" required>' +
        cachedProducts.map(function(p) {
            return '<option value="' + p.id + '">' + p.note + '</option>';
        }).join('') +
        '</select>' +
        '</td>' +
        '<td><input type="number" class="form-control" name="quantities[]" min="1" required></td>' +
        '<td><input type="number" class="form-control" name="prices[]" min="0" required></td>' +
        '<td><input type="text" class="form-control" name="notes[]"></td>' +
        '<td><button type="button" class="btn btn-danger btn-sm" onclick="removeProductRow(this)">&times;</button></td>';

    return tr;
}

// 13.1.9  - Hệ thống thêm một dòng mới vào bảng
document.getElementById("addProductBtn").addEventListener("click", () => {
    productTableBody.appendChild(createProductRow());
});

// 13.1.12 Hệ thống gửi dữ liệu lên StockInController để tạo nhập kho mới.
$('#stockInForm').on('submit', function(e) {
    e.preventDefault();

    // Lấy dữ liệu chung
    let createdBy = $(this).find('input[name="createdBy"]').val();
    let supplierId = $(this).find('select[name="supplierId"]').val();
    let createdDate = $(this).find('input[name="createdDate"]').val();
    let note = $(this).find('textarea[name="note"]').val();

    // Lấy dữ liệu sản phẩm từ bảng
    let items = [];
    $('#productTableBody tr').each(function(index) {
        let productId = $(this).find('select.productSelect').val();
        let quantity = $(this).find('input.quantityInput').val();
        let unitPrice = $(this).find('input.unitPriceInput').val();
        let itemNote = $(this).find('input.itemNoteInput').val();

        // if (productId && quantity && unitPrice) {
        //     if (quantity <= 0) {
        //         alert(`Số lượng không hợp lệ ở dòng ${index + 1}`);
        //         return false;
        //     }
        //     if (unitPrice <= 0) {
        //         alert(`Đơn giá không hợp lệ ở dòng ${index + 1}`);
        //         return false;
        //     }
            items.push({
                productId: parseInt(productId),
                quantity: parseInt(quantity),
                unitPrice: parseFloat(unitPrice),
                note: itemNote || ''
            });
        // }
    });
    // if (!createdBy || !supplierId || !createdDate) {
    //     alert('Vui lòng điền đầy đủ thông tin người thực hiện, nhà cung cấp và ngày nhập.');
    //     return;
    // }
    //
    // if (items.length === 0) {
    //     alert('Vui lòng thêm ít nhất một sản phẩm hợp lệ để nhập kho.');
    //     return;
    // }


    // Tạo object dữ liệu gửi lên server
    let dataToSend = {
        createrID: createdBy,
        supplierId: parseInt(supplierId),
        createdDate: createdDate,
        note: note,
        items: items
    };

    $.ajax({
        url: 'api/stock-in/',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(dataToSend),
        success: function(response) {
            alert('Lưu nhập kho thành công!');
            $('#stockInModal').modal('hide');
        },
        error: function(xhr) {
            alert('Lỗi khi lưu nhập kho: ' + xhr.responseText);
        }
    });
});

function removeProductRow(button) {
    const rows = productTableBody.querySelectorAll(".product-row");
        button.closest("tr").remove();
}