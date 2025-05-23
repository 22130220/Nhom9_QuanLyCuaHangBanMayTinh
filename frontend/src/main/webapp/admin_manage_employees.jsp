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
                    <h1 class="h3 mb-2 text-gray-800">Quản lý nhân viên</h1>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <button type="button" class="btn btn-primary" x-on:click="openFormModal">+ Thêm nhân viên</button> <!--  1.1.7. Người dùng chọn nút “+ Thêm nhân viên” ở phía trên table có màu xanh dương. -->
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <div x-init="init()">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Họ</th>
                                            <th>Tên</th>
                                            <th>Email</th>
                                            <th>Điện thoại</th>
                                            <th>Vị trí</th>
                                            <th>Lương</th>
                                            <th>Ngày vào làm</th>
                                            <th>Role</th>
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

    <template x-if="openModal"> <!-- 1.1.8. Hệ thống hiển thị một modal để người dùng nhập thông tin cần thêm của nhân viên đó -->
        <div class="modal fade d-block" :class="openModal && 'show'" tabindex="-1" role="dialog" style="background-color: rgba(0,0,0,0.5);">
            <div class="modal-dialog modal-lg" role="document" style="max-width: 600px;">
                <div class="modal-content">
                    <form @submit.prevent="submitForm">
                        <div class="modal-header">
                            <h5 class="modal-title">Thêm Nhân viên</h5>
                            <button type="button" class="close" @click="openModal = false">
                                <span>×</span>
                            </button>
                        </div>
                        <div class="modal-body" style="max-height: 60vh; overflow-y: auto;">
                            <template x-if="error">
                                <div class="alert alert-danger" x-text="error"></div> <!--  1.2.2 Trang web nhận được message thì hiển thị lên modal Thêm nhân viên một dialog và trên đó có message trên. -->
                            </template>

                            <div class="form-group">
                                <label>Họ</label>
                                <input type="text" class="form-control" x-model="form.firstName" required>
                            </div>
                            <div class="form-group">
                                <label>Tên</label>
                                <input type="text" class="form-control" x-model="form.lastName" required>
                            </div>
                            <div class="form-group">
                                <label>Email</label>
                                <input type="email" class="form-control" x-model="form.email" required>
                            </div>
                            <div class="form-group">
                                <label>Điện thoại</label>
                                <input type="text" class="form-control" x-model="form.phone" required>
                            </div>
                            <div class="form-group">
                                `   <label>Chức vụ</label>
                                <input type="text" class="form-control" x-model="form.position" required>
                            </div>
                            <div class="form-group">
                                <label>Lương</label>
                                <input type="number" class="form-control" x-model="form.salary" required>
                            </div>
                            <div class="form-group">
                                <label>Ngày vào làm</label>
                                <input type="date" class="form-control" x-model="form.hireDate" required>
                            </div>
                            <hr>
                            <div class="form-group">
                                <label>Tên đăng nhập</label>
                                <input type="text" class="form-control" x-model="form.username" required>
                            </div>
                            <div class="form-group">
                                <label>Mật khẩu</label>
                                <input type="password" class="form-control" x-model="form.password" required>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-secondary" @click="openModal = false">Hủy</button>
                            <button type="submit" class="btn btn-primary">Lưu</button> <!-- 1.1.9. Sau khi người dùng điền đầy đủ thông, nhấn lưu. -->
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
            // 1.1.5. script được gọi tới http://localhost:8081/users/api/employees với phương thức là Get để lấy ra dữ liệu tát cả employee đang có.
            // 1.1.12 Script gọi lại tới api http://localhost:8081/users/api/employees phương thức GET để lấy ra data vừa mới cập nhật lại và hiển thị lên table.
            fetchData() {
                axios.get('http://localhost:8081/users/api/employees')
                    .then(response => {
                        const data = response.data;

                        const formattedData = data.map(emp => ([
                            emp.id,
                            emp.firstName,
                            emp.lastName,
                            emp.email,
                            emp.phone,
                            emp.position,
                            emp.salary.toLocaleString('vi-VN') + ' ₫',
                            emp.hireDate,
                            emp.user?.role || ''
                        ]));

                        this.$nextTick(() => { // 1.1.6. Sau khi nhận được dữ liệu, đoạn script tiến hành thành table, và hiển thị lên trang html.
                            if (this.dt) {
                                this.dt.clear().rows.add(formattedData).draw();
                            } else {
                                this.dt = $('#dataTable').DataTable({
                                    data: formattedData,
                                    columns: [
                                        {title: "ID"},
                                        {title: "Họ"},
                                        {title: "Tên"},
                                        {title: "Email"},
                                        {title: "Điện thoại"},
                                        {title: "Vị trí"},
                                        {title: "Lương"},
                                        {title: "Ngày vào làm"},
                                        {title: "Role"}
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
                firstName: '',
                lastName: '',
                email: '',
                phone: '',
                position: '',
                salary: '',
                hireDate: '',
                username: '',
                password: ''
            },
            async openFormModal() {
                this.openModal = true;
            },
            async submitForm() { // 1.1.10. Sau đó có đoạn script thực hiện việc thêm vào user và employees
                this.error = '';

                try {
                    // Bước 1: Gửi user
                    const userResponse = await axios.post('http://localhost:8081/users/api/users', {
                        username: this.form.username,
                        password: this.form.password,
                        role: "EMPLOYEE"
                    });

                    const userId = userResponse.data.id;

                    // Bước 2: Gửi employee
                    await axios.post('http://localhost:8081/users/api/employees', {
                        firstName: this.form.firstName,
                        lastName: this.form.lastName,
                        email: this.form.email,
                        phone: this.form.phone,
                        position: this.form.position,
                        salary: this.form.salary,
                        hireDate: this.form.hireDate,
                        user: {id: userId}
                    });

                    alert("Thêm thành công!"); // 1.1.11 Hiển thị một alert thóng báo thêm thành công.
                    this.openModal = false;

                    // Reset form
                    this.form = {
                        firstName: '',
                        lastName: '',
                        email: '',
                        phone: '',
                        position: '',
                        salary: '',
                        hireDate: '',
                        username: '',
                        password: ''
                    };

                    if (window.$ && $('#dataTable').DataTable) {
                        this.fetchData();
                    }
                } catch (err) {
                    console.error(err);
                    this.error = "Lỗi khi thêm dữ liệu."; <!--  1.2.1 Nếu có lỗi gì xảy ra thì server sẽ trả về một message “Lỗi khi thêm dữ liệu” -->
                }
            }

        }))
    });
</script>
</body>

</html>