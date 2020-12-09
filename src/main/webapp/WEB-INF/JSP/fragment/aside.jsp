<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>

<div class="visible-xs-block xs-option-container">
	<a class="pull-right" data-toggle="collapse" href="#tourCatalog">Tour
		catalog <span class="caret"></span>
	</a> <a data-toggle="collapse" href="#findTours">Find tours <span
		class="caret"></span></a>
</div>
<!-- Search form -->
<form class="search" action="/search">
	<div id="findTours" class="panel panel-primary collapse">
		<div class="panel-heading">Find tours</div>
		<div class="panel-body">
			<div class="input-group">
				<input type="text" name="query" class="form-control"
					placeholder="Search query"> <span class="input-group-btn">
					<a id="goSearch" class="btn btn-default">Go!</a>
				</span>
			</div>
			<div class="more-options">
				<a data-toggle="collapse" href="#searchOptions">More filters <span
					class="caret"></span></a>
			</div>
		</div>
		<div id="searchOptions" class="collapse">
			<div class="panel-heading">Country filters</div>
			<div class="panel-body countries">
				<label> <input type="checkbox" id="allCountries">
					All
				</label>
				<div class="form-group">
					<div class="checkbox">
						<label><input type="checkbox" name="country" value="1"
							class="search-option">E-book (78)</label>
					</div>
				</div>
				<div class="form-group">
					<div class="checkbox">
						<label><input type="checkbox" name="country" value="2"
							class="search-option">Mp3-player (75)</label>
					</div>
				</div>
			</div>
			<div class="panel-heading">Cities filters</div>
			<div class="panel-body cities">
				<label> <input type="checkbox" id="allCities"> All
				</label>
				<div class="form-group">
					<div class="checkbox">
						<label><input type="checkbox" name="city" value="1"
							class="search-option">Dell (56) </label>
					</div>
				</div>
				<div class="form-group">
					<div class="checkbox">
						<label><input type="checkbox" name="city" value="2"
							class="search-option">Apple (22) </label>
					</div>
				</div>
			</div>
		</div>
	</div>
</form>
<!-- /Search form -->
<!-- Countries -->
<div id="tourCatalog" class="panel panel-primary collapse">
	<div class="panel-heading">Tour catalog</div>
	<div class="list-group">
		<a href="/products" class="list-group-item"> <span class="badge">78</span>
			E-book
		</a> <a href="/products" class="list-group-item"> <span class="badge">75</span>
			Mp3-player
		</a> <a href="/products" class="list-group-item"> <span class="badge">110</span>
			Notebook
		</a> <a href="/products" class="list-group-item"> <span class="badge">113</span>
			Phone
		</a> <a href="/products" class="list-group-item"> <span class="badge">216</span>
			Smartphone
		</a> <a href="/products" class="list-group-item"> <span class="badge">95</span>
			Smartwatch
		</a> <a href="/products" class="list-group-item"> <span class="badge">211</span>
			Tablet
		</a>
	</div>
</div>
<!-- /Countries -->