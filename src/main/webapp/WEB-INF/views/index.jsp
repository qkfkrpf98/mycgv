<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CGV</title>
<link rel="stylesheet"  href="http://localhost:9000/mycgv/resources/css/mycgv.css">
<script src="http://localhost:9000/mycgv/resources/js/jquery-3.6.0.min.js"></script>
<script src="http://localhost:9000/mycgv/resources/js/mycgv_index.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<!--   <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script> -->
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<script>
	let login_result = '${login_result}';
	let logout_result = '${logout_result}';
	
	if(logout_result == 'ok'){
		alert("로그아웃에 성공하셨습니다.");
	}
	
	if(login_result == 'ok'){
		alert("로그인에 성공하셨습니다.");
	}
	
	function movieList(){
		//Ajax를 이용한 데이터 가져오기
		//alert("성공");
		$.ajax({
			url : "movie_list_json.do",
			success : function(result){
			
			let movie = JSON.parse(result);
			
			let output ="<div class='s1_article'>";
			for(data of movie.list){
				output += "<article>";
				output += "<img src='http://localhost:9000/mycgv/resources/upload/"+data.msfile+"'>";
				output += "<div>"+data.mname+"</div>";
				output += "<div>99%  | 예매율 37.1%</div>";
				output += "</article>";
			}
			output += "</div>";
			
			$(".mchart").after(output);
			}
		});
	}	
	
</script>
<style>
	.notice_list li span{
		display:inline-block;
		padding:0 8px; 
	}
</style>
</head>
<body>
	<!-- Header Include -->
	<iframe src="header.do" width="100%" height="160px" scrolling="no" frameborder=0 ></iframe>
	
	
	<!---------------------------------------------->
	<!--------------- Content ----------------------->
	<!---------------------------------------------->
	<div class="carousel">
	   <!--bootstrap4 carousel  -->
		<div id="demo" class="carousel slide" data-ride="carousel">

			<!-- Indicators -->
			<ul class="carousel-indicators">
				<li data-target="#demo" data-slide-to="0" class="active"></li>
				<li data-target="#demo" data-slide-to="1"></li>
				<li data-target="#demo" data-slide-to="2"></li>
				<li data-target="#demo" data-slide-to="3"></li>
			</ul>

			<!-- The slideshow -->
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="http://localhost:9000/mycgv/resources/images/carousel_01.jpg">
				</div>
				<div class="carousel-item">
					<img src="http://localhost:9000/mycgv/resources/images/carousel_02.jpg">
				</div>
				<div class="carousel-item">
					<img src="http://localhost:9000/mycgv/resources/images/carousel_03.jpg">
				</div>
				<div class="carousel-item">
					<img src="http://localhost:9000/mycgv/resources/images/carousel_04.jpg">
				</div>
			</div>

			<!-- Left and right controls -->
			<a class="carousel-control-prev" href="#demo" data-slide="prev">
				<span class="carousel-control-prev-icon"></span>
			</a> <a class="carousel-control-next" href="#demo" data-slide="next">
				<span class="carousel-control-next-icon"></span>
			</a>
		</div>
	<div class="content">
		<section>
			<h3 class="mchart">무비차트 | 상영예정작</h3>
			<%-- <div class="s1_article">
				<c:forEach var="vo" items="${movielist}">
					<article>
						<img src="http://localhost:9000/mycgv/resources/upload/${vo.msfile1 }">
						<div>${vo.mname}</div>
						<div>99%  | 예매율 37.1%</div>
					</article>
				</c:forEach>
				<!-- <article>
					<img src="http://localhost:9000/mycgv/resources/images/82120_320.jpg">
					<div>탑건-매버릭</div>
					<div>99%  | 예매율 37.1%</div>
				</article>
				<article>
					<img src="http://localhost:9000/mycgv/resources/images/82120_320.jpg">
					<div>탑건-매버릭</div>
					<div>99%  | 예매율 37.1%</div>
				</article>
				<article>
					<img src="http://localhost:9000/mycgv/resources/images/82120_320.jpg">
					<div>탑건-매버릭</div>
					<div>99%  | 예매율 37.1%</div>
				</article>
				<article>
					<img src="http://localhost:9000/mycgv/resources/images/82120_320.jpg">
					<div>탑건-매버릭</div>
					<div>99%  | 예매율 37.1%</div>
				</article>
				<article>
					<img src="http://localhost:9000/mycgv/resources/images/82120_320.jpg">
					<div>탑건-매버릭</div>
					<div>99%  | 예매율 37.1%</div>
				</article>
			</div> --%>
		</section>
		
		<section>
			<h3>특별관</h3>
			<div class="s2_article">
				<div>
					<img src="http://localhost:9000/mycgv/resources/images/16390080561620.png">
				</div>
				<!-- Ajax + Dynamic HTML 로 생성된 공지사항 출력위치 -->
			</div>
		</section>
		
		<section>
			<ul>
				<li>
					<h3>영화관람권</h3>
					<button type="button">더보기</button>
				</li>
				<li>
					<img src="http://localhost:9000/mycgv/resources/images/16094706927780.jpg">
					<div>
						<label>CGV 영화관람권</label>
						<label>12,000원</label>
					</div>
				</li>
				<li>
					<img src="http://localhost:9000/mycgv/resources/images/16094706927780.jpg">
					<div>
						<label>CGV 영화관람권</label>
						<label>12,000원</label>
					</div>
				</li>
				<li>
					<img src="http://localhost:9000/mycgv/resources/images/16094706927780.jpg">
					<div>
						<label>CGV 영화관람권</label>
						<label>12,000원</label>
					</div>
				</li>
			</ul>
			<ul>
				<li>
					<h3>영화관람권</h3>
					<button type="button">더보기</button>
				</li>
				<li >
					<img src="http://localhost:9000/mycgv/resources/images/16094706927780.jpg">
					<div>
						<label>CGV 영화관람권</label>
						<label>12,000원</label>
					</div>
				</li>
				<li>
					<img src="http://localhost:9000/mycgv/resources/images/16094706927780.jpg">
					<div>
						<label>CGV 영화관람권</label>
						<label>12,000원</label>
					</div>
				</li>
				<li>
					<img src="http://localhost:9000/mycgv/resources/images/16094706927780.jpg">
					<div>
						<label>CGV 영화관람권</label>
						<label>12,000원</label>
					</div>
				</li>
			</ul>
			<ul>
				<li>
					<h3>영화관람권</h3>
					<button type="button">더보기</button>
				</li>
				<li >
					<img src="http://localhost:9000/mycgv/resources/images/16094706927780.jpg">
					<div>
						<label>CGV 영화관람권</label>
						<label>12,000원</label>
					</div>
				</li>
				<li>
					<img src="http://localhost:9000/mycgv/resources/images/16094706927780.jpg">
					<div>
						<label>CGV 영화관람권</label>
						<label>12,000원</label>
					</div>
				</li>
				<li>
					<img src="http://localhost:9000/mycgv/resources/images/16094706927780.jpg">
					<div>
						<label>CGV 영화관람권</label>
						<label>12,000원</label>
					</div>
				</li>
			</ul>
		</section>		
	</div>
	
	<!-- footer Include -->
	<iframe src="footer.do" width="100%" height="530px" scrolling="no" frameborder=0></iframe>
	
</body>
</html>







