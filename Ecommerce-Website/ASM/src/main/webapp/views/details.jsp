<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<div class="row mt-5"></div>
<div class="row m-5 shadow-sm rounded p-5">
	<table class="table">
		<thead>
			<tr>
				<th colspan="5"><h5 class="fw-bold">Items in your cart</h5></th>
			</tr>
			<tr>
				<th>Image</th>
				<th>Name</th>
				<th>ID Product</th>
				<th>Price</th>
				<th>Quantity</th>
				<th colspan="2">Handle</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${sessionScope.order.orderDetails}" var="ordt">
				<c:set var="total"
					value="${total + ordt.product.price * ordt.quantity }"></c:set>
				<tr>
					<td><img src="${ordt.product.image}" class="rounded"
						width="100px" height="100px" alt="product"></td>
					<td>
						<p class="fw-bold text-black">${ordt.product.name}</p>
					</td>
					<td>
						<p class="fw-bold text-black">${ ordt.product.id }</p>
					</td>
					<td>
						<p class="text-red fs-5 fw-bold">
							<fmt:formatNumber value="${ ordt.product.price }"
								pattern="#,###.00"></fmt:formatNumber>
							VND
						</p>
					</td>
					<td><span class="input-group"> <span><a
								class="btn btn-primary"
								href="/ASM/detail/quantity?action=dec&id=${ordt.product.id}">-</a></span>
							<input type="number" min="1" class="form-control mx-2 rounded"
							disabled name="quantity" value="${ordt.quantity}"> <c:if
								test="${ordt.quantity < ordt.product.stock}">
								<span><a class="btn btn-primary"
									href="/ASM/detail/quantity?action=inc&id=${ordt.product.id}">+</a></span>
							</c:if>
					</span></td>
					<td><a class="btn btn-danger"
						href="/ASM/detail/remove?id=${ordt.product.id}">Delete</a></td>
					<td>
						<!-- Modal add -->
						<div class="modal fade" id="modal_buy_id" tabindex="-1"
							aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title"></h5>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<div class="modal-body">
										<form
											action="/ASM/detail/order-now?id=${ordt.product.id}&quantity=${ordt.quantity}"
											method="post">
											<label class="form-label">Họ và tên</label> <input
												type="text" class="form-control"
												value="${ sessionScope.fullnameLg }"> <label
												class="form-label">Email</label> <input type="email"
												class="form-control" value="${sessionScope.emailLg }">
											<label class="form-label">Số điện thoại</label> <input
												type="number" class="form-control"
												value="${ sessionScope.phoneLg }"> <label
												class="form-label">Your address</label> <select
												name="address" class="form-select">
												<option value="An Giang">An Giang</option>
												<option value="Bac Giang">Bac Giang</option>
												<option value="Bac Kan">Bac Kan</option>
												<option value="Bac Lieu">Bac Lieu</option>
												<option value="Bac Ninh">Bac Ninh</option>
												<option value="Ba Ria-Vung Tau">Ba Ria-Vung Tau</option>
												<option value="Ben Tre">Ben Tre</option>
												<option value="Binh Dinh">Binh Dinh</option>
												<option value="Binh Duong">Binh Duong</option>
												<option value="Binh Phuoc">Binh Phuoc</option>
												<option value="Binh Thuan">Binh Thuan</option>
												<option value="Ca Mau">Ca Mau</option>
												<option value="Cao Bang">Cao Bang</option>
												<option value="Dac Lak">Dac Lak</option>
												<option value="Dac Nong">Dac Nong</option>
												<option value="Dien Bien">Dien Bien</option>
												<option value="Dong Nai">Dong Nai</option>
												<option value="Dong Thap">Dong Thap</option>
												<option value="Gia Lai">Gia Lai</option>
												<option value="Ha Giang">Ha Giang</option>
												<option value="Hai Duong">Hai Duong</option>
												<option value="Ha Nam">Ha Nam</option>
												<option value="Ha Tay">Ha Tay</option>
												<option value="Ha Tinh">Ha Tinh</option>
												<option value="Hau Giang">Hau Giang</option>
												<option value="Hoa Binh">Hoa Binh</option>
												<option value="Hung Yen">Hung Yen</option>
												<option value="Khanh Hoa">Khanh Hoa</option>
												<option value="Kien Giang">Kien Giang</option>
												<option value="Kon Tum">Kon Tum</option>
												<option value="Lai Chau">Lai Chau</option>
												<option value="Lam Dong">Lam Dong</option>
												<option value="Lang Son">Lang Son</option>
												<option value="Lao Cai">Lao Cai</option>
												<option value="Long An">Long An</option>
												<option value="Nam Dinh">Nam Dinh</option>
												<option value="Nghe An">Nghe An</option>
												<option value="Ninh Binh">Ninh Binh</option>
												<option value="Ninh Thuan">Ninh Thuan</option>
												<option value="Phu Tho">Phu Tho</option>
												<option value="Phu Yen">Phu Yen</option>
												<option value="Quang Binh">Quang Binh</option>
												<option value="Quang Nam">Quang Nam</option>
												<option value="Quang Ngai">Quang Ngai</option>
												<option value="Quang Ninh">Quang Ninh</option>
												<option value="Quang Tri">Quang Tri</option>
												<option value="Soc Trang">Soc Trang</option>
												<option value="Son La">Son La</option>
												<option value="Tay Ninh">Tay Ninh</option>
												<option value="Thai Binh">Thai Binh</option>
												<option value="Thai Nguyen">Thai Nguyen</option>
												<option value="Thanh Hoa">Thanh Hoa</option>
												<option value="Thua Thien-Hue">Thua Thien-Hue</option>
												<option value="Tien Giang">Tien Giang</option>
												<option value="Tra Vinh">Tra Vinh</option>
												<option value="Tuyen Quang">Tuyen Quang</option>
												<option value="Vinh Long">Vinh Long</option>
												<option value="Vinh Phuc">Vinh Phuc</option>
												<option value="Yen Bai">Yen Bai</option>
												<option value="Can Tho">Can Tho</option>
												<option value="Da Nang">Da Nang</option>
												<option value="Hai Phong">Hai Phong</option>
												<option value="Hanoi">Ha noi</option>
												<option value="Ho Chi Minh">Ho Chi Minh</option>
											</select>
											<div class="mt-3">
												<button type="button" class="btn btn-secondary"
													data-bs-dismiss="modal">Cancel</button>
												<button class="btn btn-primary">Buy now</button>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="row mt-3">
		<div class="col-10">
			<p class="fw-bold fs-3">
				Total : <span class="text-red"><fmt:formatNumber
						value="${ total }" pattern="#,###.00"></fmt:formatNumber> VND</span>
			</p>
		</div>
		<div class="col-2">
			<a class="btn btn-success" href="/ASM/checkout/index?totalp=${total}">Checkout</a>
		</div>
	</div>
</div>

<div
	class="alert alert-success ${sessionScope.display=='show'?'':'d-none'}"
	role="alert">
	<span> <svg xmlns="http://www.w3.org/2000/svg" width="16"
			height="16" fill="currentColor" class="bi bi-bell"
			viewBox="0 0 16 16">
 				<path
				d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2zM8 1.918l-.797.161A4.002 4.002 0 0 0 4 6c0 .628-.134 2.197-.459 3.742-.16.767-.376 1.566-.663 2.258h10.244c-.287-.692-.502-1.49-.663-2.258C12.134 8.197 12 6.628 12 6a4.002 4.002 0 0 0-3.203-3.92L8 1.917zM14.22 12c.223.447.481.801.78 1H1c.299-.199.557-.553.78-1C2.68 10.2 3 6.88 3 6c0-2.42 1.72-4.44 4.005-4.901a1 1 0 1 1 1.99 0A5.002 5.002 0 0 1 13 6c0 .88.32 4.2 1.22 6z" />
		</svg>
	</span> ${sessionScope.messageSuccess}
</div>
<c:remove var="display" scope="session" />
