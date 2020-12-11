<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="travelWithUs" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach var="t" items="${tours }">
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 col-xlg-2">
			<div id="tour${t.id }" class="panel panel-default tour">
				<div class="panel-body">
					<div class="thumbnail">
						<img src="/media/${t.imageLink }" alt="${t.name }">
						<div class="desc">
							<div class="cell">
								<p>
									<span class="title">Details</span>${t.description }
								</p>
							</div>
						</div>
					</div>
		<h4 class="name">${t.name }</h4>
		<h4 class="name">${t.hotelType }</h4>
		<div class="price">$ ${t.price } <c:if test="${t.hotTour eq 0}"><img class="small" src="/media/fire.png" alt="Fire"></c:if></div>
		<a class="btn btn-primary pull-right buy-btn" data-id-tour="${t.id }">Buy</a><div class="list-group">
	<span class="list-group-item"> <small>Person:</small> <span class="person">${t.person }</span></span>
	<span class="list-group-item"> <small>Duration:</small><span class="country">${t.duration }</span></span>
	<span class="list-group-item"> <small>Country:</small><span class="country">${t.country }</span></span>
    <span class="list-group-item"> <small>City:</small> <span class="city">${t.city }</span></span>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>