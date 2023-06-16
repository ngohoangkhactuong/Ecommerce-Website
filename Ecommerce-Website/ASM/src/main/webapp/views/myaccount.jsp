<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="container my-5 py-5  border rounded">


		<div class="row">
			<div class="row" style="width: 200px;">
				<img width="150px" height="150px" src="${ user.avatar }">
			</div>
			<div class="row mt-3">
				<div class="col">

					<label class="label-control mt-1">Full name</label> <input
						class="form-control" type="text" name="fullname"
						value="${user.fullname}" readonly /> <label
						class="label-control mt-1">Email</label> <input
						class="form-control" type="email" name="email"
						value="${user.email}" readonly /> <label
						class="label-control mt-1">Phone number </label> <input
						class="form-control" type="text" name="phonenumber"
						value="${user.phonenumber}" readonly />
				</div>

				<div class="col">
					<label class="label-control mt-1">Avatar</label> <input
						class="form-control" type="text" name="avatar"
						value="${user.avatar}" readonly />

					<c:if test="${user.seller == null }">
						<label class="form-check-label">Role</label>
						<div>

							<input type="radio" class="form-check-input" name="role"
								value="1" ${user.role==1?"checked":""}> Admin <input
								type="radio" class="form-check-input" name="role" value="0"
								${user.role==0?"checked":""}> User
						</div>
					</c:if>
					<c:if test="${ !empty sessionScope.isEmail }">
						<div class="text-danger">${ sessionScope.isEmail }</div>
						<c:remove var="isEmail" scope="session" />
					</c:if>
					<c:if test="${user.seller != null }">
						<input class="form-control" type="text" name="sellerid"
							hidden="hidden" value="${user.seller.sellId}" />
						<label class="label-control mt-1">Name</label>
						<input class="form-control" type="text" name="nameseller"
							value="${user.seller.name}" />
						<label class="label-control mt-1">Status</label>
						<div class="form-check">

							<input type="radio" class="form-check-input" name="status"
								value="1" ${user.seller.status==1?"checked":""} disabled> 
								Active
							<input type="radio" class="form-check-input" name="status"
								value="0" ${user.seller.status==0?"checked":""} disabled>
							Unactive
						</div>
					</c:if>
					<br>
					<button class="btn btn-primary" data-bs-toggle="modal"
						data-bs-target="#modal_update_${ user.id }">
						<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20"
							fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
  							<path
								d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z" />
						</svg>
					</button>
					<div class="modal fade" id="modal_update_${ user.id }"
						tabindex="-1" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title">Alert</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<form action="/ASM/my-account/update?id=${user.id}" method="post">
									<div class="modal-body" style="text-align: left;">
										<label class="label-control mt-1">Full name</label> <input
											class="form-control" type="text" name="fullname"
											value="${user.fullname}" /> <label
											class="label-control mt-1">Email</label> <input
											class="form-control" type="email" name="email"
											value="${user.email}" /> <label class="label-control mt-1">Phone
											number</label> <input class="form-control" type="text"
											name="phonenumber" value="${user.phonenumber}" /> <label
											class="label-control mt-1">Avatar</label> <input
											class="form-control" type="text" name="avatar"
											value="${user.avatar}" />
										<c:if test="${ !empty sessionScope.isEmail }">
											<div class="text-danger">${ sessionScope.isEmail }</div>
											<c:remove var="isEmail" scope="session" />
										</c:if>
										<c:if test="${user.seller != null }">
											<input class="form-control" type="text" name="sellerid"
												hidden="hidden" value="${user.seller.sellId}" />
											<label class="label-control mt-1">Name</label>
											<input class="form-control" type="text" name="nameseller"
												value="${user.seller.name}" />
										</c:if>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">Cancel</button>
										<button class="btn btn-primary">Update</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
</div>
