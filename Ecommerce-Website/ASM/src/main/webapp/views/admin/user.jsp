<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<h3>Manage users</h3>
<div class="row mt-3">
	<div class="col-3">
		<button type="button" class="btn btn-success" data-bs-toggle="modal"
			data-bs-target="#modal_add_id">
			<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20"
				fill="currentColor" class="bi bi-plus-circle" viewBox="0 0 16 16">
  		<path
					d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
  		<path
					d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z" />
	</svg>
			Add a new user
		</button>
		<!-- Modal add -->
		<div class="modal fade" id="modal_add_id" tabindex="-1"
			aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Alert</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form action="/ASM/admin/users/create" method="post">
							<label class="form-label">Full name</label> <input type="text"
								name="fullname" class="form-control" required="required"
								oninvalid="this.setCustomValidity('Không được để trống fullname')"
								oninput="this.setCustomValidity('')" /> <label
								class="form-label">Email</label> <input type="email"
								name="email" class="form-control" required="required"
								oninvalid="this.setCustomValidity('Không được để trống email')"
								oninput="this.setCustomValidity('')" />
							<c:if test="${ !empty sessionScope.isEmail }">
								<div class="text-danger">${ sessionScope.isEmail }</div>
								<c:remove var="isEmail" scope="session" />
							</c:if>
							<label class="form-label">Password</label> <input type="password"
								name="password" class="form-control" required="required"
								oninvalid="this.setCustomValidity('Không được để trống password')"
								oninput="this.setCustomValidity('')" /> <label
								class="form-label">Phone number</label> <input type="text"
								name="phonenumber" class="form-control" required="required"
								oninvalid="this.setCustomValidity('Không được để trống phonenumber')"
								oninput="this.setCustomValidity('')" /> <label
								class="form-label">Avatar</label> <input type="text"
								name="avatar" class="form-control" required="required"
								oninvalid="this.setCustomValidity('Không được để trống avatar')"
								oninput="this.setCustomValidity('')" /> <label
								class="form-check-label">Role</label>
							<div>
								<input type="radio" class="form-check-input" name="role"
									value="1"> Admin <input type="radio"
									class="form-check-input" name="role" value="0"> User
							</div>
							<div class="mt-3">
								<button type="button" class="btn btn-secondary"
									data-bs-dismiss="modal">Cancel</button>
								<button class="btn btn-primary">Add</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="col-3">
		<button type="button" class="btn btn-success" data-bs-toggle="modal"
			data-bs-target="#modal_addseller_id">
			<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20"
				fill="currentColor" class="bi bi-plus-circle" viewBox="0 0 16 16">
  		<path
					d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
  		<path
					d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z" />
	</svg>
			Add a new seller
		</button>
		<!-- Modal add -->
		<div class="modal fade" id="modal_addseller_id" tabindex="-1"
			aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Alert</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form action="/ASM/admin/users/createseller" method="post">

							<label class="form-label">Name</label> <input type="text"
								name="nameseller" class="form-control" required="required"
								oninvalid="this.setCustomValidity('Không được để trống name')"
								oninput="this.setCustomValidity('')" /> <label
								class="form-label">Full name</label> <input type="text"
								name="fullname" class="form-control" required="required"
								oninvalid="this.setCustomValidity('Không được để trống fullname')"
								oninput="this.setCustomValidity('')" /> <label
								class="form-label">Email</label> <input type="email"
								name="email" class="form-control" required="required"
								oninvalid="this.setCustomValidity('Không được để trống email')"
								oninput="this.setCustomValidity('')" />
							<c:if test="${ !empty sessionScope.isEmail }">
								<div class="text-danger">${ sessionScope.isEmail }</div>
								<c:remove var="isEmail" scope="session" />
							</c:if>


							<label class="form-label">Password</label> <input type="password"
								name="password" class="form-control" required="required"
								oninvalid="this.setCustomValidity('Không được để trống password')"
								oninput="this.setCustomValidity('')" /> <label
								class="form-label">Phone number</label> <input type="text"
								name="phonenumber" class="form-control" required="required"
								oninvalid="this.setCustomValidity('Không được để trống phonenumber')"
								oninput="this.setCustomValidity('')" /> <label
								class="form-label">Avatar</label> <input type="text"
								name="avatar" class="form-control" required="required"
								oninvalid="this.setCustomValidity('Không được để trống avatar')"
								oninput="this.setCustomValidity('')" />

							<div class="mt-3">
								<button type="button" class="btn btn-secondary"
									data-bs-dismiss="modal">Cancel</button>
								<button class="btn btn-primary">Add</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="col">

		<form action="/ASM/admin/users/search" method="post">
			<div class="row">
				<div class="col-5">
					<input class="form-control" type="text" name="username"
						placeholder="Type here to search..." />
				</div>
				<div class="col-1">
					<button type="submit" class="btn btn-primary">Search</button>
				</div>
			</div>

		</form>
	</div>
</div>

<!-- End add seller -->
<div class="row table-responsive border border-2 mt-5">
	<table class="table table-hover">
		<thead class="text-start">
			<tr>
				<th>Name</th>
				<th>Email</th>
				<th>Phone</th>
				<th>Avarta</th>
				<th>Role</th>
				<th>Seller</th>
				<th>Status</th>

				<th colspan="2">Handle</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${ listPagination }">
				<tr>
					<td>${ user.fullname }</td>
					<td>${ user.email }</td>
					<td>${ user.phonenumber }</td>
					<td><img src="${ user.avatar }" width="50px" height="50px"></td>
					<td>${ user.role==1?"Admin":"User" }</td>
					<td>${ user.seller==null?"X":"Seller" }</td>
					<td><span class="badge bg-${ user.status==1?"success":"secondary" }">${ user.status==1?"Active":"Block" }</span>
					
					<td class="text-center">
						<button class="btn btn-success" data-bs-toggle="modal"
							data-bs-target="#modal_verify_${ user.id }">
							<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20"
								fill="currentColor" class="bi bi-check2" viewBox="0 0 16 16">
						  <path
									d="M13.854 3.646a.5.5 0 0 1 0 .708l-7 7a.5.5 0 0 1-.708 0l-3.5-3.5a.5.5 0 1 1 .708-.708L6.5 10.293l6.646-6.647a.5.5 0 0 1 .708 0z" />
						</svg>
						</button>
						<div class="modal fade" id="modal_verify_${ user.id }"
							tabindex="-1" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title">Alert</h5>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<div class="modal-body">
										<h4>Active this account ?</h4>
									</div>
									<div class="modal-footer">
										<form
											action="/ASM/admin/users/status?act=unblock&id=${ user.id }"
											method="post">
											<button type="button" class="btn btn-secondary"
												data-bs-dismiss="modal">Cancel</button>
											<button class="btn btn-primary" type="submit">Active</button>
										</form>
									</div>
								</div>
							</div>
						</div>
					</td>
					<td class="text-center">
						<button class="btn btn-danger" data-bs-toggle="modal"
							data-bs-target="#modal_unverify_${ user.id }">
							<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20"
								fill="currentColor" class="bi bi-x-square" viewBox="0 0 16 16">
						  <path
									d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z" />
						  <path
									d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z" />
						</svg>
						</button>
						<div class="modal fade" id="modal_unverify_${ user.id }"
							tabindex="-1" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title">Alert</h5>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<div class="modal-body">
										<h4>Block this account ?</h4>
									</div>
									<div class="modal-footer">
										<form
											action="/ASM/admin/users/status?act=block&id=${ user.id }"
											method="post">
											<button type="button" class="btn btn-secondary"
												data-bs-dismiss="modal">Cancel</button>
											<button type="submit" class="btn btn-danger">Block</button>
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
	<div class="row">
		<div
			class="col-6 d-flex justify-content-start align-items-center text-secondary">Showing
			5 out of ${ total } entries</div>
		<div class="col-6 d-flex justify-content-end">
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center">
					<c:if test="${index>1}">
						<li class="page-item px-1 "><a class="page-link"
							href="/ASM/admin/users/index?index=${index-1}"> <svg
									xmlns="http://www.w3.org/2000/svg" width="16" height="16"
									fill="black" class="bi bi-chevron-left" viewBox="0 0 16 16">
  						<path fill-rule="evenodd"
										d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0z" />
					</svg>
						</a></li>
					</c:if>
					<c:forEach begin="1" end="${endPage}" var="i">
						<li class="page-item px-1"><a class="page-link ${index==i?"
							active":"unactive"}" href="/ASM/admin/users/index?index=${i}">${i}</a></li>
					</c:forEach>
					<c:if test="${index<endPage}">
						<li class="page-item px-1"><a class="page-link"
							href="/ASM/admin/users/index?index=${index+1}"> <svg
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
<div
	class="alert alert-success ${sessionScope.display=='show'?'':'d-none'}"
	role="alert"
	style="right: 30px; bottom: 30px; width: auto; position: fixed;">
	<span> <svg xmlns="http://www.w3.org/2000/svg" width="16"
			height="16" fill="currentColor" class="bi bi-bell"
			viewBox="0 0 16 16">
 				<path
				d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2zM8 1.918l-.797.161A4.002 4.002 0 0 0 4 6c0 .628-.134 2.197-.459 3.742-.16.767-.376 1.566-.663 2.258h10.244c-.287-.692-.502-1.49-.663-2.258C12.134 8.197 12 6.628 12 6a4.002 4.002 0 0 0-3.203-3.92L8 1.917zM14.22 12c.223.447.481.801.78 1H1c.299-.199.557-.553.78-1C2.68 10.2 3 6.88 3 6c0-2.42 1.72-4.44 4.005-4.901a1 1 0 1 1 1.99 0A5.002 5.002 0 0 1 13 6c0 .88.32 4.2 1.22 6z" />
		</svg>
	</span> ${sessionScope.messageupdateSuccess}
</div>
<c:remove var="display" scope="session" />


