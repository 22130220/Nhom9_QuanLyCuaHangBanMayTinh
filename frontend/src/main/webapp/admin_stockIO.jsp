<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Quản lý Xuất Nhập Kho</title>

    <!-- Font & CSS -->
    <link href="${pageContext.request.contextPath}/vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
          type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="${pageContext.request.contextPath}/css/sb-admin-2.min.css" rel="stylesheet">
    <script defer src="https://cdn.jsdelivr.net/npm/alpinejs@3.14.8/dist/cdn.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <style>
        #productTableWrapper {
            max-height: 250px;
            overflow-y: auto;
        }

    </style>
</head>

<body id="page-top">
<div x-data="datatableComponent">
    <div id="wrapper">

        <jsp:include page="shared/slidebar.jsp"/>

        <div id="content-wrapper" class="d-flex flex-column">
            <div id="content">

                <jsp:include page="shared/header.jsp"/>

                <div class="container-fluid">
                    <h1 class="h3 mb-2 text-gray-800">Quản lý Xuất Nhập Kho</h1>
                    <p class="mb-4">Danh sách các lần xuất nhập kho gần đây. Bạn có thể lọc theo ngày, loại giao dịch
                        hoặc
                        sản phẩm.</p>

                    <div class="card shadow mb-4">
                        <div class="card-header py-3 d-flex justify-content-between align-items-center">
                            <h6 class="m-0 font-weight-bold text-primary">Danh sách Xuất Nhập Kho</h6>
                            <button type="button" class="btn btn-success mb-3" x-on:click="openFormModal()">
                                + Thêm nhập kho
                            </button>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <div x-init="init()">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                        <tr>
                                            <th>Mã số</th>
                                            <th>Người thực hiện</th>
                                            <th>Ngày thực hiện</th>
                                            <th>Nhà cung cấp</th>
                                            <th>Ghi chú</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <!-- Modal Nhập Kho -->
    <template x-if="openModal">
        <div class="modal fade d-block" :class="openModal && 'show'" tabindex="-1" role="dialog" style="background-color: rgba(0, 0, 0, 0.5);">
            <div class="modal-dialog modal-xl" role="document" style="max-width: 1000px;">
                <div class="modal-content">
                    <form @submit.prevent="submitStockInForm">
                        <div class="modal-header">
                            <h5 class="modal-title">Phiếu Nhập Kho</h5>
                            <button type="button" class="btn-close" @click="openModal = false"
                                    aria-label="Close">x</button>
                        </div>

                        <div class="modal-body">
                            <!-- Thông tin chung -->
                            <div class="row mb-3">
                                <div class="col-md-4">
                                    <label class="form-label">Người thực hiện</label>
                                    <input type="text" class="form-control" x-model="form.createdBy" readonly>
                                </div>

                                <div class="col-md-4">
                                    <label class="form-label">Nhà cung cấp</label>
                                    <select class="form-control" x-model="form.supplierId" required>
                                        <option value="">-- Chọn nhà cung cấp --</option>
                                        <template x-for="supplier in suppliers" :key="supplier.id">
                                            <option :value="supplier.id" x-text="supplier.name"></option>
                                        </template>
                                    </select>
                                </div>

                                <div class="col-md-4">
                                    <label class="form-label">Ngày nhập</label>
                                    <input type="date" class="form-control" x-model="form.createdDate" required>
                                </div>
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Ghi chú</label>
                                <textarea class="form-control" rows="2" x-model="form.note"></textarea>
                            </div>

                            <h6>Danh sách sản phẩm</h6>
                            <div class="table-responsive" id="productTableWrapper">
                                <table class="table table-bordered align-middle text-center">
                                    <thead class="table-light">
                                    <tr>
                                        <th>Sản phẩm</th>
                                        <th>Số lượng</th>
                                        <th>Đơn giá</th>
                                        <th>Ghi chú</th>
                                        <th>Thao tác</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <template x-for="(item, index) in form.products" :key="index">
                                        <tr>
                                            <td>
                                                <select class="form-control" x-model="item.productId" required>
                                                    <option value="">-- Chọn sản phẩm --</option>
                                                    <template x-for="product in products" :key="product.id">
                                                        <option :value="product.id" x-text="product.id + ' - ' + product.name"></option>
                                                    </template>
                                                </select>
                                            </td>
                                            <td><input type="number" class="form-control" x-model="item.quantity"
                                                       min="1"
                                                       required></td>
                                            <td><input type="number" class="form-control" x-model="item.price" min="0"
                                                       required></td>
                                            <td><input type="text" class="form-control" x-model="item.note"></td>
                                            <td>
                                                <button type="button" class="btn btn-danger btn-sm"
                                                        @click="removeProduct(index)">X
                                                </button>
                                            </td>
                                        </tr>
                                    </template>
                                    </tbody>
                                </table>
                            </div>

                            <button type="button" class="btn btn-secondary mt-2" @click="addProduct">+ Thêm sản phẩm
                            </button>
                        </div>

                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary">Lưu</button>
                            <button type="button" class="btn btn-secondary" @click="openModal = false">Đóng</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </template>
</div>

    <!-- Bootstrap core JavaScript-->
    <script src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="${pageContext.request.contextPath}/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="${pageContext.request.contextPath}/js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="${pageContext.request.contextPath}/vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="${pageContext.request.contextPath}/vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="${pageContext.request.contextPath}/js/demo/datatables-demo.js"></script>
    <%--<script src="${pageContext.request.contextPath}/js/stock/stockIO.js"></script>--%>
    <script>
        document.addEventListener('alpine:init', () => {
            Alpine.data('datatableComponent', () => ({
                openModal: false,
                suppliers: [],
                products: [],
                form: {
                    createdBy: '',
                    supplierId: '',
                    createdDate: '',
                    note: '',
                    products: [
                        {productId: '', quantity: 1, price: 0, note: ''}
                    ]
                },

                init() {
                    this.fetchData();
                },
                fetchData() {
                    axios.get('http://localhost:8081/stockIO/api/stock-in')
                        .then(response => {
                            const data = response.data;

                            const formattedData = data.map(si => ([
                                si.id,
                                si.creater_id,
                                si.created_date,
                                si.supplier_id,
                                si.note || '',
                            ]));

                            this.$nextTick(() => {
                                if (this.dt) {
                                    this.dt.clear().rows.add(formattedData).draw();
                                } else {
                                    this.dt = $('#dataTable').DataTable({
                                        data: formattedData,
                                        columns: [
                                            {title: "Mã số"},
                                            {title: "Người thực hiện"},
                                            {title: "Ngày thực hiện"},
                                            {title: "Nhà cung cấp"},
                                            {title: "Ghi chú"},
                                        ]
                                    });
                                }
                            });
                        })
                        .catch(error => {
                            console.error("Lỗi khi gọi API:", error);
                        });
                },
                async openFormModal() {
                    console.log("Mở modal");
                    this.openModal = true;
                    this.loadSuppliers();
                    this.loadProducts();
                    this.form.createdBy = 1;
                    this.form.createdDate = this.getTodayDate();
                },

                getTodayDate() {
                    const today = new Date();
                    return today.toISOString().split('T')[0];
                },

                loadSuppliers() {
                    axios.get('http://localhost:8081/stockIO/api/suppliers')
                        .then(response => {
                            this.suppliers = response.data;
                        })
                },
                loadProducts() {
                    axios.get('http://localhost:8081/product/api/products')
                        .then(response => {
                            this.products = response.data;
                        })
                },

                addProduct() {
                    this.form.products.push({productId: '', quantity: 1, price: 0, note: ''});
                },

                removeProduct(index) {
                    this.form.products.splice(index, 1);
                },

                submitStockInForm() {
                    const payload = {
                        createdBy: this.form.createdBy,
                        supplierId: this.form.supplierId,
                        createdDate: this.form.createdDate,
                        note: this.form.note,
                        products: this.form.products.map(p => ({
                            productId: p.productId,
                            quantity: p.quantity,
                            unitPrice: p.price,
                            note: p.note
                        }))
                    };
                    console.log(payload);

                    axios.post('http://localhost:8081/stockIO/api/stock-in', payload)
                        .then(response => {
                            alert(response);
                            this.openModal = false;
                            this.fetchData();
                            this.resetForm();
                        })
                        .catch(error => {
                            console.error('Lỗi khi lưu phiếu nhập kho:', error);
                            alert('Có lỗi xảy ra khi lưu phiếu nhập kho!');
                        });
                },

                resetForm() {
                    this.form = {
                        createdBy: 'Tên người dùng hiện tại',
                        supplierId: '',
                        createdDate: this.getTodayDate(),
                        note: '',
                        products: [
                            {productId: '', quantity: 1, price: 0, note: ''}
                        ]
                    };
                },
            }));
        });
    </script>

</body>
</html>
