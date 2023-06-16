<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<div class="container bg-white">
	<div class="row mt-5">
		<span> <svg xmlns="http://www.w3.org/2000/svg" width="16"
				height="16" fill="currentColor" class="bi bi-chevron-right"
				viewBox="0 0 16 16">
  		<path fill-rule="evenodd"
					d="M4.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L10.293 8 4.646 2.354a.5.5 0 0 1 0-.708z" />
	</svg> <span>${ sessionScope.product.name }</span>
		</span>
	</div>
	<div class="row p-5">
		<div class="col-6 overflow-hidden border border-1">
			<img src="${ sessionScope.product.image }" class="card-img-top scale"
				alt="product">
		</div>
		<div class="col-6 px-5">
			<h5 class="fw-bold">${ sessionScope.product.name }</h5>
			<p>${ sessionScope.product.descriptions }</p>
			<h5 class="fw-bold">Màu sắc : Blue</h5>
			<h5 class="fw-bold">Kích thước :</h5>
			<div class="btn-group" role="group"
				aria-label="Basic radio toggle button group">
				<input type="radio" class="btn-check" name="btnradio" id="xs"
					autocomplete="off" checked> <label
					class="btn btn-outline-dark mx-2" for="xs">XS</label> <input
					type="radio" class="btn-check" name="btnradio" id="s"
					autocomplete="off"> <label
					class="btn btn-outline-dark mx-2" for="s">S</label> <input
					type="radio" class="btn-check" name="btnradio" id="m"
					autocomplete="off"> <label
					class="btn btn-outline-dark mx-2" for="m">M</label> <input
					type="radio" class="btn-check" name="btnradio" id="l"
					autocomplete="off"> <label
					class="btn btn-outline-dark mx-2" for="l">L</label> <input
					type="radio" class="btn-check" name="btnradio" id="xl"
					autocomplete="off"> <label
					class="btn btn-outline-dark mx-2" for="xl">XL</label>
			</div>
			<h5 class="fw-bold mt-2">Đơn giá :</h5>
			<p class="card-text text-red fs-5 fw-bold">${ sessionScope.product.price }
				VNĐ</p>

			<h5 class=" fw-bold">
				<a href="/ASM/home/seller?index=1&ctid=0"
					class="text-black text-decoration-none">Brand:
					${sessionScope.product.seller.name}</a>
			</h5>
			<div class="row px-3">
				<a class="btn btn-dark"
					href="/ASM/detail/index?prid=${sessionScope.product.id}&quantity=1">
					<svg xmlns="http://www.w3.org/2000/svg" width="26" height="26"
						fill="white" class="bi bi-cart4" viewBox="0 0 16 16">
  						<path
							d="M0 2.5A.5.5 0 0 1 .5 2H2a.5.5 0 0 1 .485.379L2.89 4H14.5a.5.5 0 0 1 .485.621l-1.5 6A.5.5 0 0 1 13 11H4a.5.5 0 0 1-.485-.379L1.61 3H.5a.5.5 0 0 1-.5-.5zM3.14 5l.5 2H5V5H3.14zM6 5v2h2V5H6zm3 0v2h2V5H9zm3 0v2h1.36l.5-2H12zm1.11 3H12v2h.61l.5-2zM11 8H9v2h2V8zM8 8H6v2h2V8zM5 8H3.89l.5 2H5V8zm0 5a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0zm9-1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0z" />
					</svg> Thêm vào giỏ hàng
				</a>
			</div>

		</div>
	</div>
	<div class="row p-5 bg-light">
		<h4>Chi tiết sản phẩm</h4>
		<p>Lorem Ipsum is simply dummy text of the printing and
			typesetting industry. Lorem Ipsum has been the industry's standard
			dummy text ever since the 1500s, when an unknown printer took a
			galley of type and scrambled it to make a type specimen book. It has
			survived not only five centuries, but also the leap into electronic
			typesetting, remaining essentially unchanged. It was popularised in
			the 1960s with the release of Letraset sheets containing Lorem Ipsum
			passages, and more recently with desktop publishing software like
			Aldus PageMaker including versions of Lorem Ipsum.</p>
		<div class="row">
			<div class="col-6">
				<h4>Đánh giá</h4>
				<div class="stars">
					<input class="star star-5" id="star-5" type="radio" name="star" />
					<label class="star star-5" for="star-5"></label> <input
						class="star star-4" id="star-4" type="radio" name="star" /> <label
						class="star star-4" for="star-4"></label> <input
						class="star star-3" id="star-3" type="radio" name="star" /> <label
						class="star star-3" for="star-3"></label> <input
						class="star star-2" id="star-2" type="radio" name="star" /> <label
						class="star star-2" for="star-2"></label> <input
						class="star star-1" id="star-1" type="radio" name="star" /> <label
						class="star star-1" for="star-1"></label>
				</div>
			</div>
		</div>

	</div>
</div>
