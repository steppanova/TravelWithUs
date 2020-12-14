<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="travelWithUs" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="tourList" data-page-count="${pageCount}" data-page-number="1">
<div class="row">
<jsp:include page="../fragment/tour-list.jsp" />
</div>
    <c:if test="${pageCount > 1 }">
	<div class="text-center hidden-print"> 
	<a id="loadMore" class="btn btn-primary">Load more tours</a>
	</div>
	</c:if>
</div>
<travelWithUs:add-tour-popup />
