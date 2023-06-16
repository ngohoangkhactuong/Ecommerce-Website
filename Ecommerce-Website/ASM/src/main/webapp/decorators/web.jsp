<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<fmt:setLocale value="${param.lang}" />
<fmt:setBundle basename="vn.iotstar.convert.global" />

<!DOCTYPE html>
<html lang="fr">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Tuong Vi 's Shop</title>
	<%-- Library bootstrap --%>
	<%@include file="/common/bootstrap.jsp"%>
	
	<link rel="shortcut icon" href="https://f7-zpc.zdn.vn/3653115574917346255/94b82f22fc9c25c27c8d.jpg" type="image/x-icon">

	<link href="<c:url value="/templates/css/home.css"/>" rel="stylesheet" type="text/css">
		<link href="<c:url value="/templates/css/banner.css"/>" rel="stylesheet" type="text/css">
	
</head>

<body class="container-fluid font-monospace">


	<%@include file="/common/web/header.jsp"%>

	<dec:body />

	<%@include file="/common/web/footer.jsp"%>

	<script type="text/javascript">
		var mybutton = document.querySelector(".backTop");

		window.onscroll = function() {
			scrollFunction();
		};

		function scrollFunction() {
			if (document.body.scrollTop > 300
					|| document.documentElement.scrollTop > 300) {
				mybutton.style.display = "block";
			} else {
				mybutton.style.display = "none";
			}
		}

		function topFunction() {
			document.body.scrollTop = 0;
			document.documentElement.scrollTop = 0;
		}
	</script>

</body>
</html>