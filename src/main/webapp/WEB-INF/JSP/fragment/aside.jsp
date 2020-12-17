<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="travelWithUs" tagdir="/WEB-INF/tags"%>


<div class="visible-xs-block xs-option-container">
	<a class="pull-right" data-toggle="collapse" href="#tourCatalog">Tour catalog <span class="caret"></span></a> 
	<a data-toggle="collapse" href="#findTours">Find tours <span class="caret"></span></a>
</div>
<%-- Search form --%>
<form class="search" action="/search">
	<div id="findTours" class="panel panel-success collapse">
		<div class="panel-heading">Find tours</div>
		<div class="panel-body">
			<div class="input-group">
				<input type="text" name="query" class="form-control" placeholder="Search query" value="${searchForm.query }"> 
				<span class="input-group-btn"> 
					<a id="goSearch" class="btn btn-default">Go!</a>
				</span>
			</div>
			<div class="more-options">
				<a data-toggle="collapse" href="#searchOptions">More filters <span class="caret"></span></a>
			</div>
		</div>
			<div id="searchOptions" class="collapse ${searchForm.countriesNotEmpty or searchForm.citiesNotEmpty ? 'in' : '' }">
			<travelWithUs:country-filter countries="${COUNTRY_LIST }" searchForm="${searchForm}" />
			<travelWithUs:city-filter cities="${CITY_LIST }"  searchForm="${searchForm}" />
		</div>
	</div>
</form>
<%-- /Search form --%>
<%-- Country --%>
<div id="tourCatalog" class="panel panel-success collapse">
	<div class="panel-heading">Tour catalog</div>
	<div class="list-group">
		<c:forEach var="country" items="${COUNTRY_LIST }">
			<a href="/tours${country.url }"
			 class="list-group-item ${selectedCountryUrl == country.url ? 'active' : '' }"> 
				<span class="badge">${country.tourCount}</span> ${country.name}
			</a>
		</c:forEach>
	</div>
</div>
<%-- /Country --%>