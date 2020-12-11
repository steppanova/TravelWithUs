<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="countries" required="true" type="java.util.Collection"%>
<%@ attribute name="searchForm" required="true" type="com.stepanova.travelWithUs.form.SearchForm"%>


<div class="panel-heading">Country filters</div>
<div class="panel-body countries">
	<label><input type="checkbox" id="allCountries"> All</label>
	<c:forEach var="country" items="${countries }">
		<div class="form-group">
			<div class="checkbox">
				<label><input type="checkbox" name="country" value="${country.id }" ${searchForm.countries.contains(country.id) ? 'checked' : '' } class="search-option">
					${country.name } (${country.tourCount })
				</label>
			</div>
		</div>
	</c:forEach>
</div>