<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<div class="slider">
	<div class="slides">
		<input type="radio" name="radio-btn" id="radio1"> <input
			type="radio" name="radio-btn" id="radio2"> <input
			type="radio" name="radio-btn" id="radio3">

		<div class="slide first">
			<img src="${pro1.image}" alt="">

		</div>
		<div class="slide">
			<img src="${pro2.image}" alt="">

		</div>
		<div class="slide">
			<img src="${pro3.image}" alt="">

		</div>

		<div class="navigation-auto">
			<div class="auto-btn1"></div>
			<div class="auto-btn2"></div>
			<div class="auto-btn3"></div>
		</div>
	</div>
	<div class="navigation-manual">
		<label for="radio1" class="manual-btn"></label> <label for="radio2"
			class="manual-btn"></label> <label for="radio3" class="manual-btn"></label>
	</div>
</div>
<div class="row row-cols-2 row-cols-lg-4 g-2 g-lg-3 text-uppercase mt-3 mx-4">

	<div class="col">
		<div class="p-3 border bg-white rounded-3">
			<div class="row">
				<div class="col-8">
					<p>Total products</p>
					<span class="text-primary">${totalPro}</span>
				</div>
				<div class="col-4 d-flex justify-content-center align-items-center">
					<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30"
						fill="currentColor" class="bi bi-cart4" viewBox="0 0 16 16">
					  <path
							d="M0 2.5A.5.5 0 0 1 .5 2H2a.5.5 0 0 1 .485.379L2.89 4H14.5a.5.5 0 0 1 .485.621l-1.5 6A.5.5 0 0 1 13 11H4a.5.5 0 0 1-.485-.379L1.61 3H.5a.5.5 0 0 1-.5-.5zM3.14 5l.5 2H5V5H3.14zM6 5v2h2V5H6zm3 0v2h2V5H9zm3 0v2h1.36l.5-2H12zm1.11 3H12v2h.61l.5-2zM11 8H9v2h2V8zM8 8H6v2h2V8zM5 8H3.89l.5 2H5V8zm0 5a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0zm9-1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0z" />
					</svg>
				</div>
			</div>
		</div>
	</div>
	</div>
<div class="px-5 mt-5">
	<div class="row">
		<div class="col-3 mt-5" style="padding-right: 50px">
			<p class="fw-bold fs-5">Danh mục sản phẩm</p>
			<ul class="list-group text-uppercase border shadow-sm">
				<c:forEach items="${listCategory}" var="category">
					<li class="list-group-item border-0 ${tagactive == category.id? "active ":" " }" >
						<a href="/ASM/home/seller?ctid=${category.id}"
						class="text-black text-decoration-none">${category.name}</a>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div class="col-9">
			<h4 class="fw-bold">Sản phẩm: </h4>
			<div class ="row">
			<form action="/ASM/home/seller/search?index=1&ctid=0" method="post">
				<div class="row"style="float: right">
					<div class="col-5">
						<input class="form-control" type="text" name="str" 
							placeholder="Type here to search..." />
					</div>
					<div class="col-1">
						<button type="submit" class="btn btn-primary">Search</button>
					</div>
				</div>

			</form>
			</div>
			<div class="row row-cols-1 row-cols-md-4 g-4 mt-4">
				<c:forEach var="item" items="${listPagination}">
					<div class="col">
						<div class="card h-90 border-0 shadow-sm rounded"
							style="min-height: 22rem">
							<img src="${ item.image }" class="card-img-top scale"
								height="220px" alt="${ item.descriptions }">
							<div class="card-body">
								<h6 class="card-title fw-bold">
									<a href="/ASM/cart?id=${item.id}"
										class="text-black text-decoration-none">${ item.name }</a>
								</h6>
							</div>
							<div class="card-footer bg-white">
								<p class="card-text text-red fs-5 fw-bold">${item.price }
									VNĐ</p>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<br />
			<div class="row mt-5">
				<p>${mess }</p>
			</div>
			<div class="row mt-5">
				<nav aria-label="Page navigation example">
					<ul class="pagination justify-content-center">
						<c:if test="${index>1}">

							<li class="page-item px-1"><a class="page-link"
								href="/ASM/home/seller?index=${index-1}&ctid=${tagactive}"> <svg
										xmlns="http://www.w3.org/2000/svg" width="16" height="16"
										fill="black" class="bi bi-chevron-left" viewBox="0 0 16 16">
  											<path fill-rule="evenodd"
											d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0z" />
										</svg>
							</a></li>
						</c:if>
						<c:forEach begin="1" end="${endPage}" var="i">
							<li class="page-item px-1  ${index==i?"active":""}"><a
								class="page-link" href="/ASM/home/seller?index=${i}&ctid=${tagactive}">${i}</a></li>
						</c:forEach>
						<c:if test="${index<endPage}">

							<li class="page-item px-1"><a class="page-link"
								href="/ASM/home/seller?index=${index+1}&ctid=${tagactive}"> <svg
										xmlns="http://www.w3.org/2000/svg" width="16" height="16"
										fill="black" class="bi bi-chevron-right" viewBox="0 0 16 16">
  						<path fill-rule="evenodd"
											d="M4.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L10.293 8 4.646 2.354a.5.5 0 0 1 0-.708z" />
					</svg>
							</a></li>
						</c:if>
					</ul>
				</nav>
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

<style>
.wi {
	width: 100%;
}
</style>
