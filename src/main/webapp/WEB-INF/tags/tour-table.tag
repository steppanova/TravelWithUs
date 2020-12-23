<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="items" required="true" type="java.util.Collection"%>
<%@ attribute name="totalCost" required="true" type="java.lang.Number"%>
<%@ attribute name="showActionColumn" required="true" type="java.lang.Boolean"%>

<table class="table table-bordered">
	<thead>
		<tr>
			<th>Tour</th>
			<th>Price</th>
			<th>Count</th>
			<c:if test="${showActionColumn }">
			<th class="hidden-print">Action</th>
			</c:if>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="item" items="${items }">
			<tr id="tour${item.tour.id }" class="item">
				<td class="text-center"><img class="smal" src="/media/${item.tour.imageLink }" alt="${item.tour.name }"><br>${item.tour.name }</td>
				<td class="price">$ ${item.tour.price }</td>
				<td class="count">${item.counts }</td>
				<c:if test="${showActionColumn }">
				<td class="hidden-print">
				<c:choose>
				<c:when test="${item.counts > 1 }">
					<a class="btn btn-danger remove-tour" data-id-tour="${item.tour.id }" data-count="1">Remove one</a><br><br>
					<a class="btn btn-danger remove-tour remove-all" data-id-tour="${item.tour.id }" data-count="${item.counts }">Remove all</a>
				</c:when>
				<c:otherwise>
					<a class="btn btn-danger remove-tour" data-id-tour="${item.tour.id }" data-count="1">Remove one</a>
				</c:otherwise>
				</c:choose>
				</td>
				</c:if>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="2" class="text-right"><strong>Total:</strong></td>
			<td colspan="${showActionColumn ? 2 : 1}" class="total">$ ${totalCost}</td>
		</tr>
	</tbody>
</table>