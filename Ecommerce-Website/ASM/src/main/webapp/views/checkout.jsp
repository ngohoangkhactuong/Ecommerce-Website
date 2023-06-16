<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<div class="container">
	<div class="row mt-5 pt-5">
		<div class="col-6 p-5">
			<h3>BILLING DETAILS</h3>
			<hr />
			<form action="/ASM/checkout/payment" method="post">
				<p class="fw-bold">> Thông tin khách hàng</p>
				<label class="form-label mt-1">Họ và tên <span
					class="text-danger">*</span></label> <input type="text"
					class="form-control" name="fullname" required="required"
					oninvalid="this.setCustomValidity('Không được để trống fullname')"
					oninput="this.setCustomValidity('')" value ="${user.fullname}" /> <label
					class="form-label mt-1">Email</label> <input type="email"
					class="form-control" name="email" required="required"
					oninvalid="this.setCustomValidity('Không được để trống email')"
					oninput="this.setCustomValidity('')" value ="${user.email}"/> <label
					class="form-label mt-1">Số điện thoại <span
					class="text-danger">*</span></label> <input type="text"
					class="form-control" required="required"
					oninvalid="this.setCustomValidity('Không được để trống phone number')"
					oninput="this.setCustomValidity('')"value ="${user.phonenumber}" /> <label
					class="form-label mt-1">Địa chỉ giao hàng <span
					class="text-danger">*</span></label> <input type="text"
					class="form-control" name="address" required="required"
					oninvalid="this.setCustomValidity('Không được để trống address')"
					oninput="this.setCustomValidity('')" /> <label
					class="form-label mt-1">Ghi chú</label>
				<textarea class="form-control" rows="5"></textarea>
				<div class="row mt-5">
					<button type="submit" class="btn btn-dark">Hoàn tất đơn
						hàng</button>
				</div>
			</form>
		</div>
		<div class="col-6 p-5 bg-light rounded">
			<h3>YOUR ORDER</h3>
			<table class="table table-borderless">
				<thead>
					<tr>
						<th>Product</th>
						<th>Quantity</th>
						<th>Total</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${sessionScope.order.orderDetails}" var="dt">
						<c:set var="total"
							value="${total + dt.product.price * dt.quantity }"></c:set>
						<c:set var="quantityCheckout" value="${dt.quantity}"
							scope="session"></c:set>
						<c:set var="proId" value="${dt.product.id }" scope="session"></c:set>
						<tr>
							<td>${dt.product.name }</td>
							<td>x ${dt.quantity }</td>
							<td>${dt.quantity*dt.product.price}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<hr />
			<p class="fw-bold">Phương thức thanh toán</p>
			<div class="list-group">
				<label class="list-group-item bg-light border-0"> <input
					class="form-check-input me-1" name="payment" type="radio" value="">
					Thanh toán qua Momo
				</label> <label class="list-group-item bg-light border-0"> <input
					class="form-check-input me-1" name="payment" type="radio" value="">
					Thanh toán qua Paypal
				</label> <label class="list-group-item bg-light border-0"> <input
					class="form-check-input me-1" name="payment" type="radio" value="">
					Thanh toán qua Zalo Pay
				</label> <label class="list-group-item bg-light border-0"> <input
					class="form-check-input me-1" name="payment" type="radio" value="">
					Thanh toán qua thẻ
				</label>
			</div>
			<hr />
			<p class="fw-bold fs-3">
				Total : <span class="text-red"><fmt:formatNumber
						value="${ total }" pattern="#,###.00"></fmt:formatNumber> VND</span>
			</p>
		</div>
	</div>
</div>
<div
	class="alert alert-success ${sessionScope.display=='show'?'':'d-none'}"
	role="alert"
	style="right: 30px; bottom: 30px; width: auto; position: fixed; z-index: 999">
	<span> <svg xmlns="http://www.w3.org/2000/svg" width="16"
			height="16" fill="currentColor" class="bi bi-bell"
			viewBox="0 0 16 16">
 				<path
				d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2zM8 1.918l-.797.161A4.002 4.002 0 0 0 4 6c0 .628-.134 2.197-.459 3.742-.16.767-.376 1.566-.663 2.258h10.244c-.287-.692-.502-1.49-.663-2.258C12.134 8.197 12 6.628 12 6a4.002 4.002 0 0 0-3.203-3.92L8 1.917zM14.22 12c.223.447.481.801.78 1H1c.299-.199.557-.553.78-1C2.68 10.2 3 6.88 3 6c0-2.42 1.72-4.44 4.005-4.901a1 1 0 1 1 1.99 0A5.002 5.002 0 0 1 13 6c0 .88.32 4.2 1.22 6z" />
		</svg>
	</span> ${sessionScope.messageSuccess}
</div>
<c:remove var="display" scope="session" />
