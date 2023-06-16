<h3>Dashboard</h3>
<hr />
<div class="row row-cols-2 row-cols-lg-4 g-2 g-lg-3 text-uppercase">

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
	<div class="col">
		<div class="p-3 border bg-white rounded-3">
			<div class="row">
				<div class="col-8">
					<p>Total sale</p>
					<span class="text-primary">${totalSale}</span>
				</div>
				<div class="col-4 d-flex justify-content-center align-items-center">
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
<h3 class="mt-3">Top sales</h3>
<hr />
<!-- Top sale -->
<div id="carouselExampleDark" class="carousel carousel-dark slide"
	data-bs-ride="carousel">
	<div class="carousel-indicators">
		<button type="button" data-bs-target="#carouselExampleDark"
			data-bs-slide-to="0" class="active" aria-current="true"
			aria-label="Slide 1"></button>
		<button type="button" data-bs-target="#carouselExampleDark"
			data-bs-slide-to="1" aria-label="Slide 2"></button>
		<button type="button" data-bs-target="#carouselExampleDark"
			data-bs-slide-to="2" aria-label="Slide 3"></button>
	</div>
	<div class="carousel-inner">
		<div class="carousel-item active" data-bs-interval="10000">
			<img src="${pro1.image }" alt="...">
			<div class="carousel-caption d-none d-md-block">
				<h5>${pro1.name }</h5>
				<p>${pro1.price }</p>
			</div>
		</div>
		<div class="carousel-item" data-bs-interval="2000">
			<img src="${pro2.image }" alt="...">
			<div class="carousel-caption d-none d-md-block">
				<h5>${pro2.name }</h5>
				<p>${pro2.price }</p>
			</div>
		</div>
		<div class="carousel-item">
			<img src="${pro3.image }" alt="...">
			<div class="carousel-caption d-none d-md-block">
				<h5>${pro3.name }</h5>
				<p>${pro3.price }</p>
			</div>
		</div>
	</div>
	<button class="carousel-control-prev" type="button"
		data-bs-target="#carouselExampleDark" data-bs-slide="prev">
		<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span
			class="visually-hidden">Previous</span>
	</button>
	<button class="carousel-control-next" type="button"
		data-bs-target="#carouselExampleDark" data-bs-slide="next">
		<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
			class="visually-hidden">Next</span>
	</button>
</div>


















