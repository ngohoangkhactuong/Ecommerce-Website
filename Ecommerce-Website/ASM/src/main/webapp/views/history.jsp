<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<div class="container">
	<div class="row mt-5 p-5 border rounded">
		<nav>
			<div class="nav nav-tabs" id="nav-tab" role="tablist">
				<button class="nav-link active" id="nav-home-tab"
					data-bs-toggle="tab" data-bs-target="#nav-verify" type="button"
					role="tab" aria-controls="nav-verify" aria-selected="true"> Đơn hàng chờ xác nhận </button>
				<button class="nav-link" id="nav-contact-tab" data-bs-toggle="tab"
					data-bs-target="#nav-contact" type="button" role="tab"
					aria-controls="nav-contact" aria-selected="false">Đơn
					hàng đã đặt</button>
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
							<th>Address</th>
							<th>Total price</th>
							<th>Detail</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${lhistory}" var="his">
													<c:if test="${ his.status == 0 }">
						
							<tr>
								<td>${ his.id }</td>
								<td>${ his.orderDate }</td>
								<td>${ his.shippingAddress }</td>
								<td>${ his.price }</td>
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
												<form action="/ASM/history/detail?id=${his.id}"
													method="post">

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
															<c:forEach items="${his.historyDetails}" var="htd">
																<tr>
																	<td><img width="50" height="50"
																		src="${ htd.image }"></td>
																	<td>${ htd.name }</td>
																	<td>${ htd.price }</td>
																	<td>${ htd.quantity }</td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</form>
											</div>
										</div>
									</div>
								</td>
							</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="tab-pane fade" id="nav-contact" role="tabpanel"
				aria-labelledby="nav-contact-tab">
						<table class="table table-borderless">
					<thead>
						<tr>
							<th>Id Order</th>
							<th>Order date</th>
							<th>Address</th>
							<th>Total price</th>
							<th>Detail</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${lhistory}" var="his">
							<c:if test="${ his.status == 1 }">
							<tr>
								<td>${ his.id }</td>
								<td>${ his.orderDate }</td>
								<td>${ his.shippingAddress }</td>
								<td>${ his.price }</td>
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
												<form action="/ASM/history/detail?id=${his.id}"
													method="post">

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
															<c:forEach items="${his.historyDetails}" var="htd">
																<tr>
																	<td><img width="50" height="50"
																		src="${ htd.image }"></td>
																	<td>${ htd.name }</td>
																	<td>${ htd.price }</td>
																	<td>${ htd.quantity }</td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</form>
											</div>
										</div>
									</div>
								</td>
							</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
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
