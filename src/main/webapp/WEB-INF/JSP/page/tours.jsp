<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="travelWithUs" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="tourList">
<jsp:include page="../fragment/tour-list.jsp" />
	<div class="text-center hidden-print">
		<img id="loadMoreIndicator" src="/static/img/loading.gif"
			class="hidden" alt="Loading..."> <a id="loadMore"
			class="btn btn-primary">Load more tours</a>
	</div>
</div>
<travelWithUs:add-tour-popup />