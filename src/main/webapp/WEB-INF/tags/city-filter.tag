<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="cities" required="true" type="java.util.Collection"%>
<%@ attribute name="searchForm" required="true" type="com.stepanova.travelWithUs.form.SearchForm"%>


<div class="panel-heading">City filters</div>
<div class="panel-body cities">
	<label><input type="checkbox" id="allCities"> All</label>
	<c:forEach var="city" items="${cities }">
		<div class="form-group">
			<div class="checkbox">
				<label><input type="checkbox" name="city" value="${city.id }" ${searchForm.cities.contains(city.id) ? 'checked' : '' } class="search-option"> 
					${city.name }(${city.tourCount })
				</label>
			</div>
		</div>
	</c:forEach>
</div>
