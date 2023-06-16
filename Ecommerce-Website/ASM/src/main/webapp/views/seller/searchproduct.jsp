<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<div class="row bg-white mt-5 ">
	<div id="carouselExampleInterval" class="carousel slide wi"
		data-bs-ride="carousel">
		<div class="carousel-inner">
			<div class="carousel-item active p-3" data-bs-interval="10000">
				<img
					src="https://img.freepik.com/free-vector/new-fashion-collection-template-social-media-banner_53876-116219.jpg?w=1380&t=st=1670869731~exp=1670870331~hmac=63eb1c60aafa7c695e926f385205cc688d60c9122788fb194840354f7e0fbeda"
					class="d-block w-100" height="600px" alt="slider01">

			</div>
			<div class="carousel-item p-3" data-bs-interval="2000">
				<img
					src="https://img.freepik.com/free-vector/floral-new-collection-banner-template_1361-1251.jpg?w=996&t=st=1670869766~exp=1670870366~hmac=f6adeff0c977617d3956dd0cc0b4ef4b878e3248b6df5f8a2c50697341e6c2ff"
					class="d-block w-100" height="600px" alt="slider02">

			</div>
			<div class="carousel-item p-3">
				<img
					src="https://img.freepik.com/free-vector/fashion-sale-banners-with-photo_52683-9828.jpg?w=996&t=st=1670869796~exp=1670870396~hmac=899d3250f905ba78eb21f8c4c9f40b5f9cf485a44497640b3d5640fcb4a1a15d"
					class="d-block w-100" height="600px" alt="slider03">

			</div>
		</div>
		<button class="carousel-control-prev" type="button"
			data-bs-target="#carouselExampleInterval" data-bs-slide="prev">
			<svg xmlns="http://www.w3.org/2000/svg" width="35" height="35"
				fill="#dfe4ea" class="bi bi-arrow-left-circle-fill"
				viewBox="0 0 16 16">
  					<path
					d="M8 0a8 8 0 1 0 0 16A8 8 0 0 0 8 0zm3.5 7.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z" />
				</svg>
		</button>
		<button class="carousel-control-next" type="button"
			data-bs-target="#carouselExampleInterval" data-bs-slide="next">
			<svg xmlns="http://www.w3.org/2000/svg" width="35" height="35"
				fill="#dfe4ea" class="bi bi-arrow-right-circle-fill"
				viewBox="0 0 16 16">
  					<path
					d="M8 0a8 8 0 1 1 0 16A8 8 0 0 1 8 0zM4.5 7.5a.5.5 0 0 0 0 1h5.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3a.5.5 0 0 0 0-.708l-3-3a.5.5 0 1 0-.708.708L10.293 7.5H4.5z" />
				</svg>
		</button>
	</div>
</div>
<div class="px-5 mt-5">
	<div class="row">
		<div class="col-3 mt-5" style="padding-right: 50px">
			<p class="fw-bold fs-5">Danh mục sản phẩm</p>
			<ul class="list-group text-uppercase border shadow-sm">
				<c:forEach items="${listCategory}" var="category">
					<li class="list-group-item border-0 ${tagactive == category.id? "active ":" " }" >
						<a href="/ASM/home?ctid=${category.id}"
						class="text-black text-decoration-none">${category.name}</a>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div class="col-9">
			<h4 class="fw-bold">Sản phẩm mới nhất</h4>
				<div class ="row">
			<form action="/ASM/home/seller/search?index=1&ctid=0" method="post">
				<div class="row"style="float: right">
					<div class="col-5">
						<input class="form-control" type="text" name="str" value="${str} "
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
								href="/ASM/home/seller/search?index=${index-1}&ctid=${tagactive}"> <svg
										xmlns="http://www.w3.org/2000/svg" width="16" height="16"
										fill="black" class="bi bi-chevron-left" viewBox="0 0 16 16">
  											<path fill-rule="evenodd"
											d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0z" />
										</svg>
							</a></li>
						</c:if>
						<c:forEach begin="1" end="${endPage}" var="i">
							<li class="page-item px-1  ${index==i?"active":""}"><a
								class="page-link" href="/ASM/home/seller/search?index=${i}&ctid=${tagactive}">${i}</a></li>
						</c:forEach>
						<c:if test="${index<endPage}">

							<li class="page-item px-1"><a class="page-link"
								href="/ASM/home/seller/search?index=${index+1}&ctid=${tagactive}"> <svg
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
