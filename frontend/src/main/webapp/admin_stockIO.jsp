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
    <link href="${pageContext.request.contextPath}/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/sb-admin-2.min.css" rel="stylesheet">
</head>

<body id="page-top">
<div id="wrapper">

    <jsp:include page="shared/slidebar.jsp"/>

    <div id="content-wrapper" class="d-flex flex-column">
        <div id="content">

            <jsp:include page="shared/header.jsp"/>

            <div class="container-fluid">
                <h1 class="h3 mb-2 text-gray-800">Quản lý Xuất Nhập Kho</h1>
                <p class="mb-4">Danh sách các lần xuất nhập kho gần đây. Bạn có thể lọc theo ngày, loại giao dịch hoặc sản phẩm.</p>

                <div class="card shadow mb-4">
                    <div class="card-header py-3 d-flex justify-content-between align-items-center">
                        <h6 class="m-0 font-weight-bold text-primary">Danh sách Xuất Nhập Kho</h6>
<%--                        <jsp:include page="shared/modal_stockIn.jsp"/>--%>
                        <!-- Nút mở modal -->
                        <button type="button" class="btn btn-success mb-3" data-toggle="modal" data-target="#stockInModal">
                            + Nhập kho
                        </button>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>Mã giao dịch</th>
                                    <th>Loại</th>
                                    <th>Sản phẩm</th>
                                    <th>Số lượng</th>
                                    <th>Đơn giá</th>
                                    <th>Ngày giao dịch</th>
                                    <th>Ghi chú</th>
                                    <th>Hành động</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>TXK001</td>
                                    <td><span class="badge badge-danger">Xuất kho</span></td>
                                    <td>Chuột Logitech M185</td>
                                    <td>5</td>
                                    <td>150,000₫</td>
                                    <td>2024-05-15</td>
                                    <td>Xuất cho khách hàng A</td>
                                    <td>
                                        <a href="#" class="btn btn-sm btn-info">Chi tiết</a>
                                        <a href="#" class="btn btn-sm btn-danger">Xoá</a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>TNK012</td>
                                    <td><span class="badge badge-success">Nhập kho</span></td>
                                    <td>Bàn phím cơ DareU EK87</td>
                                    <td>10</td>
                                    <td>550,000₫</td>
                                    <td>2024-05-12</td>
                                    <td>Nhập từ nhà cung cấp B</td>
                                    <td>
                                        <a href="#" class="btn btn-sm btn-info">Chi tiết</a>
                                        <a href="#" class="btn btn-sm btn-danger">Xoá</a>
                                    </td>
                                </tr>
                                <!-- Thêm các dòng dữ liệu từ backend -->
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
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
</body>
</html>
