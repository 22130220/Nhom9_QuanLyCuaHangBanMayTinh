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
    <style>
        #productTableWrapper {
            max-height: 250px;
            overflow-y: auto;
        }

    </style>
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
                        <jsp:include page="shared/modal_stockIn.jsp"/>

                        <button type="button" class="btn btn-success mb-3" data-toggle="modal" data-target="#stockInModal">
                            + Thêm nhập kho
                        </button>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>Mã số</th>
                                    <th>Người thực hiện</th>
                                    <th>Ngày thực hiện</th>
                                    <th>Nhà cung cấp</th>
                                    <th>Ghi chú</th>
                                    <th>Hành động</th>
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

<!-- Modal Nhập Kho -->
<div class="modal fade" id="stockInModal" tabindex="-1" aria-labelledby="stockInModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-xl modal-dialog-scrollable">
        <div class="modal-content">
            <form action="stock-in" method="post">
                <div class="modal-header">
                    <h5 class="modal-title" id="stockInModalLabel">Phiếu Nhập Kho</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>

                </div>

                <div class="modal-body">
                    <!-- Thông tin chung -->
                    <div class="row mb-3">
                        <div class="col-md-4">
                            <label>Người thực hiện</label>
                            <input type="text" class="form-control" name="createdBy" value="${currentUser}" required readonly>
                        </div>

                        <!-- 13.1.6 - Người dùng nhấn vào ô chọn nhà cung cấp và chọn nhà cung cấp từ dropdown. -->
                        <div class="col-md-4">
                            <label>Nhà cung cấp</label>
                            <select class="form-control" name="supplierId" id="supplierSelect" required>
                            </select>
                        </div>
                        <div class="col-md-4">
                            <label>Ngày nhập</label>
                            <input type="date" class="form-control" name="createdDate" value="${today}" required>
                        </div>
                    </div>

                    <!-- 13.1.7 Người dùng điền các thông tin: ngày nhập, ghi chú. -->
                    <div class="mb-3">
                        <label>Ghi chú</label>
                        <textarea class="form-control" name="note" rows="2"></textarea>
                    </div>

                    <!-- 13.1.10 Người dùng chọn sản phẩm từ dropdown và điền số lượng đơn giá, ghi chú cho sản phẩm. -->
                    <h6>Danh sách sản phẩm</h6>
                    <div class="table-responsive" id="productTableWrapper">
                        <table class="table table-bordered align-middle text-center" id="productTable">
                            <thead class="table-light">
                            <tr>
                                <th>Sản phẩm</th>
                                <th>Số lượng</th>
                                <th>Đơn giá</th>
                                <th>Ghi chú</th>
                                <th>Thao tác</th>
                            </tr>
                            </thead>
                            <tbody id="productTableBody">

                            </tbody>
                        </table>
                    </div>

                    <!-- 13.1.8 Người dùng nhấn nút “ + Thêm sản phẩm”.-->
                    <button type="button" class="btn btn-secondary mt-2" id="addProductBtn">+ Thêm sản phẩm</button>

                    <div class="modal-footer">
                        <!--13.1.11 Người dùng nhấn nút “Lưu”. -->
                        <button type="submit" class="btn btn-primary">Lưu</button>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                    </div>
                </div>
            </form>
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
<script src="${pageContext.request.contextPath}/js/stock/stockIO.js"></script>

</body>
</html>
