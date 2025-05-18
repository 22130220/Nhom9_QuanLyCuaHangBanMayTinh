<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

          <div class="mb-3">
            <label>Ghi chú</label>
            <textarea class="form-control" name="note" rows="2"></textarea>
          </div>

          <!-- Danh sách sản phẩm (dạng bảng) -->
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

          <button type="button" class="btn btn-secondary mt-2" id="addProductBtn">+ Thêm sản phẩm</button>

        <div class="modal-footer">
          <button type="submit" class="btn btn-primary">Lưu phiếu nhập</button>
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
        </div>
        </div>
      </form>
    </div>
  </div>
</div>



