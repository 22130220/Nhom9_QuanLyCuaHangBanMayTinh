<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Blank</title>

    <!-- Custom fonts for this template-->
    <link href="${pageContext.request.contextPath}/vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
          type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="${pageContext.request.contextPath}/css/sb-admin-2.min.css" rel="stylesheet">

    <script defer src="https://cdn.jsdelivr.net/npm/alpinejs@3.14.8/dist/cdn.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

</head>

<body id="page-top">
<div x-data="datatableComponent">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <jsp:include page="shared/slidebar.jsp"/>

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <jsp:include page="shared/header.jsp"/>

                <!-- Begin Page Content -->
                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">Tables</h1>
                    <p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below.
                        For more information about DataTables, please visit the <a target="_blank"
                                                                                   href="https://datatables.net">official
                            DataTables documentation</a>.</p>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">DataTables Example</h6>
                            <button type="button" class="btn btn-primary" x-on:click="openFormModal">+ Thêm khách hàng</button> <!--  1.1.7. Người dùng chọn nút “+ Thêm nhân viên” ở phía trên table có màu xanh dương. -->
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <div x-init="init()">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                        <tr>
                                            <th>IDKH</th>
                                            <th>Họ và tên</th>
                                            <th>SDT</th>
                                            <th>Email</th>
                                            <th>Address</th>
                                            <th>Thời gian tạo</th>
                                            <th>Thời gian cập nhật</th>
                                        </tr>
                                        </thead>
                                        <tbody></tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="login.html">Logout</a>
                </div>
            </div>
        </div>
    </div>

    <template x-if="openModal">
        <div class="modal fade d-block" :class="openModal && 'show'" tabindex="-1" role="dialog" style="background-color: rgba(0,0,0,0.5);">
            <div class="modal-dialog modal-lg" role="document" style="max-width: 600px;">
                <div class="modal-content">
                    <form @submit.prevent="submitForm">
                        <div class="modal-header">
                            <h5 class="modal-title">Thêm Khách hàng</h5>
                            <button type="button" class="close" @click="openModal = false">
                                <span>×</span>
                            </button>
                        </div>
                        <div class="modal-body" style="max-height: 60vh; overflow-y: auto;">
                            <template x-if="error">
                                <div class="alert alert-danger" x-text="error"></div>
                            </template>

                            <div class="form-group">
                                <label>Họ và tên</label>
                                <input type="text" class="form-control" x-model="form.fullName" required>
                            </div>
                            <div class="form-group">
                                <label>Email</label>
                                <input type="email" class="form-control" x-model="form.email">
                            </div>
                            <div class="form-group">
                                <label>Điện thoại</label>
                                <input type="text" class="form-control" x-model="form.phoneNumber" required pattern="^\d{10,11}$" title="Số điện thoại gồm 10 hoặc 11 chữ số">
                            </div>
                            <div class="form-group">
                                <label>Địa chỉ</label>
                                <input type="text" class="form-control" x-model="form.address">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-secondary" @click="openModal = false">Hủy</button>
                            <button type="submit" class="btn btn-primary">Lưu</button>
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

<script>
    document.addEventListener('alpine:init', () => {
        Alpine.data('datatableComponent', () => ({
            dt: null,
            init() {
                this.fetchData();
            },
            fetchData() {
                axios.get('http://localhost:8081/users/api/customers')
                    .then(response => {
                        // response.data là ApiResponse, dữ liệu thực tế nằm trong response.data.data
                        const data = response.data;

                        const formattedData = data.map(cust => ([
                            cust.id,
                            cust.fullName,
                            cust.email || '',
                            cust.phoneNumber,
                            cust.address || '',
                            cust.createdAt,
                            cust.updatedAt,
                        ]));

                        this.$nextTick(() => {
                            if (this.dt) {
                                this.dt.clear().rows.add(formattedData).draw();
                            } else {
                                this.dt = $('#dataTable').DataTable({
                                    data: formattedData,
                                    columns: [
                                        {title: "ID"},
                                        {title: "Họ và tên"},
                                        {title: "Email"},
                                        {title: "Điện thoại"},
                                        {title: "Địa chỉ"},
                                        {title: "Thời gian tạo"},
                                        {title: "Thời gian cập nhật"},
                                    ]
                                });
                            }
                        });
                    })
                    .catch(error => {
                        console.error("Lỗi khi gọi API:", error);
                    });
            },

            openModal: false,
            error: '',
            form: {
                fullName: '',
                email: '',
                phoneNumber: '',
                address: ''
            },

            async openFormModal() {
                this.openModal = true;
                this.error = '';
                // Reset form khi mở modal
                this.form = {
                    fullName: '',
                    email: '',
                    phoneNumber: '',
                    address: ''
                };
            },

            async submitForm() {
                this.error = '';

                try {
                    await axios.post('http://localhost:8081/users/api/customers', {
                        fullName: this.form.fullName,
                        email: this.form.email,
                        phoneNumber: this.form.phoneNumber,
                        address: this.form.address
                    });

                    alert("Thêm khách hàng thành công!");
                    this.openModal = false;

                    // Reset form
                    this.form = {
                        fullName: '',
                        email: '',
                        phoneNumber: '',
                        address: ''
                    };

                    if (window.$ && $('#dataTable').DataTable) {
                        this.fetchData();
                    }
                } catch (err) {
                    console.error(err);
                    // Hiển thị thông báo lỗi nếu có
                    this.error = err.response?.data?.message || "Lỗi khi thêm khách hàng.";
                }
            }
        }))
    });
</script>
</body>

</html>