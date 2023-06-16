<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<div class="container">

	<div class="row mt-5 p-5 border rounded">
		<form action="" method="post">
			<div class="row my-3">
				<div class=col>
					Month: <input type="month" name="month">
				</div>

				<div class="col">
					<input type="submit">
				</div>
				<div class="col">
					<div class="col">
						<div class="p-3 border bg-white rounded-3">
							<div class="row">
								<div class="col-8">
								
									<p>Total sale</p>
									<span class="text-primary">${totalSale}</span>
								</div>
								<div
									class="col-4 d-flex justify-content-center align-items-center">
									<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30"
										fill="currentColor" class="bi bi-currency-dollar"
										viewBox="0 0 16 16">
					  <path
											d="M4 10.781c.148 1.667 1.513 2.85 3.591 3.003V15h1.043v-1.216c2.27-.179 3.678-1.438 3.678-3.3 0-1.59-.947-2.51-2.956-3.028l-.722-.187V3.467c1.122.11 1.879.714 2.07 1.616h1.47c-.166-1.6-1.54-2.748-3.54-2.875V1H7.591v1.233c-1.939.23-3.27 1.472-3.27 3.156 0 1.454.966 2.483 2.661 2.917l.61.162v4.031c-1.149-.17-1.94-.8-2.131-1.718H4zm3.391-3.836c-1.043-.263-1.6-.825-1.6-1.616 0-.944.704-1.641 1.8-1.828v3.495l-.2-.05zm1.591 1.872c1.287.323 1.852.859 1.852 1.769 0 1.097-.826 1.828-2.2 1.939V8.73l.348.086z" />
					</svg>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>

		</form>
		<nav>
			<div class="nav nav-tabs" id="nav-tab" role="tablist">
				<button class="nav-link active" id="nav-home-tab"
					data-bs-toggle="tab" data-bs-target="#nav-verify" type="button"
					role="tab" aria-controls="nav-verify" aria-selected="true">Đơn
					hàng đã hoàn thành </button>
				<button class="nav-link" id="nav-contact-tab" data-bs-toggle="tab"
					data-bs-target="#nav-contact" type="button" role="tab"
					aria-controls="nav-contact" aria-selected="false">Đơn hàng chờ xác nhận </button>
			</div>
		</nav>
		<div class="tab-content" id="nav-tabContent">
			<div class="tab-pane fade show active" id="nav-verify"
				role="tabpanel" aria-labelledby="nav-home-tab">
				<table class="table table-borderless">
					<thead>
						<tr>
							<th>Id Order</th>
							<th>Order date</th>
							<th>Shipping Address</th>
							<th>Customer</th>
							<th>Detail</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${lhistory}" var="his">
							<tr>
								<td>${ his.id }</td>
								<td>${ his.orderDate }</td>
								<td>${ his.shippingAddress }</td>
								<td>${ his.user_email}</td>
								<td class="text-center">
									<button class="btn btn-primary" data-bs-toggle="modal"
										data-bs-target="#modal_update_${ his.id }">
										<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-info" viewBox="0 0 16 16">
  <path d="m8.93 6.588-2.29.287-.082.38.45.083c.294.07.352.176.288.469l-.738 3.468c-.194.897.105 1.319.808 1.319.545 0 1.178-.252 1.465-.598l.088-.416c-.2.176-.492.246-.686.246-.275 0-.375-.193-.304-.533L8.93 6.588zM9 4.5a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>
</svg>
									</button>
									<div class="modal fade" id="modal_update_${his.id}"
										tabindex="-1" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title">Detail</h5>

													<button type="button" class="btn-close"
														data-bs-dismiss="modal" aria-label="Close"></button>
												</div>


												<table class="table table-borderless">
													<thead>
														<tr>
															<th>Image</th>
															<th>Name</th>
															<th>Price</th>
															<th>Quantity</th>
														</tr>
													</thead>
													<tbody>
													<c:set var="total"
																	value="0"></c:set>
														<c:forEach items="${his.historyDetails}" var="htd">
															<c:if test="${ htd.sellid == sessionScope.sellerid }">
																<c:set var="total"
																	value="${total + htd.price * htd.quantity }"></c:set>
																<tr>
																	<td><img width="50" height="50"
																		src="${ htd.image }"></td>
																	<td>${ htd.name }</td>
																	<td>${ htd.price }</td>
																	<td>${ htd.quantity }</td>
																</tr>
															</c:if>
														</c:forEach>
													</tbody>
												</table>
												<div>Total Price : ${total}</div>
											</div>
										</div>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="tab-pane fade" id="nav-contact" role="tabpanel"
				aria-labelledby="nav-contact-tab">
				<p class="alert alert-primary m-3">No notications</p>
			</div>
		</div>
	</div>
</div>
<!-- Back to top -->
<div>
	<button class="backTop" onclick="topFunction()">
		<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30"
			fill="white" class="bi bi-arrow-90deg-up" viewBox="0 0 16 16">
			  <path fill-rule="evenodd"
				d="M4.854 1.146a.5.5 0 0 0-.708 0l-4 4a.5.5 0 1 0 .708.708L4 2.707V12.5A2.5 2.5 0 0 0 6.5 15h8a.5.5 0 0 0 0-1h-8A1.5 1.5 0 0 1 5 12.5V2.707l3.146 3.147a.5.5 0 1 0 .708-.708l-4-4z" />
			</svg>
	</button>
</div>
