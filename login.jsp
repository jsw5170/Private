<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% if(session.getAttribute("ValidMem") != null) { %>
	<jsp:forward page="main.jsp"></jsp:forward>
<% } %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JPS/Servlet 21-1</title>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width"/>
  	<meta name="google-signin-client_id" content="916545226917-l3srqp161aul7ud1cqm09ddh5nq7plsn.apps.googleusercontent.com">
  
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
	<!-- Bootstrap -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	 crossorigin="anonymous">
	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	 crossorigin="anonymous">

	<style type="text/css">
	.header,body{padding-bottom:20px}.header,.jumbotron{border-bottom:1px solid #e5e5e5}body{padding-top:20px}.footer,.header,.marketing{padding-right:15px;padding-left:15px}.header h3{margin-top:0;margin-bottom:0;line-height:40px}.footer{padding-top:19px;color:#777;border-top:1px solid #e5e5e5}@media (min-width:768px){.container{max-width:730px}}.container-narrow>hr{margin:30px 0}.jumbotron{text-align:center}.jumbotron .btn{padding:14px 24px;font-size:21px}.marketing{margin:40px 0}.marketing p+h4{margin-top:28px}@media screen and (min-width:768px){.footer,.header,.marketing{padding-right:0;padding-left:0}.header{margin-bottom:30px}.jumbotron{border-bottom:0}}
	</style>
<script src="http://code.jquery.com/jquery.js"></script>
	<script>
	
	function formcheck() {
		submit();
	}
	function submit() {
		$.ajax({
			data: $('#loginProcess').serialize(),
			url: 'loginProcess',
			type: 'post',
			dataType: 'text',
			success: function (json) {
				var result = JSON.parse(json);
				if(result.code == "success"){
					alert(result.desc);
					window.location.replace("login.jsp");
				}else{
					alert(result.desc);
				}
			}
		});
	}
	   function onSignIn(googleUser) {
	    	var profile = googleUser.getBasicProfile();
	    	 console.log(profile);
	    	 console.log(profile.getId());
	    	var pname = profile.getName();
	    	var pid = profile.getId();
	    	$.ajax({
				data: {id:pid,name:pname},
				url: 'loginProcess',
				type: 'post',
				dataType: 'text',
				success: function (json) {
					var result = JSON.parse(json);
					if(result.code == "success"){
						alert(result.desc);
						signOut1();
						window.location.replace("login.jsp");
					}else{
						alert(result.desc);
					}
				}
			});
	    	$('#my-signin2').css('display', 'none');
	    	$('#logout1').css('display', 'block');
	    	$('#upic1').attr('src', profile.getImageUrl());
	    	$('#uname').html('[ ' +profile.getName() + ' ]');
	    }
	    function onFailure(error) {
	    }
		function signOut1() {
		    var auth2 = gapi.auth2.getAuthInstance();
		    auth2.signOut().then(function () {
		    	$('#my-signin2').css('display', 'block');
		    	$('#logout1').css('display', 'none');
		    	$('#upic1').attr('src', '');
		    	$('#uname').html('');
		    });
		}
	    function renderButton() {
	        gapi.signin2.render('my-signin2', {
		        'scope': 'profile email',
		        'width': 240,
		        'height': 50,
		        'longtitle': true,
		        'theme': 'dark',
		        'onsuccess': onSignIn,
		        'onfailure': onFailure
	        });

	    }
	    $(document).ready(function() {
	    	
	    });
	    
	    window.fbAsyncInit = function() {
	        FB.init({
	          appId      : '2348859555328498',
	          cookie     : true,
	          xfbml      : true,
	          version    : 'v4.0'
	        });

	        FB.getLoginStatus(function(response) {
	        	console.log(response);
	          statusChangeCallback(response);
	        });
	      };

	      // Load the SDK asynchronously
	      (function(d, s, id) {
	        var js, fjs = d.getElementsByTagName(s)[0];
	        if (d.getElementById(id)) return;
	        js = d.createElement(s); js.id = id;
	        js.src = "https://connect.facebook.net/en_US/sdk.js";
	        fjs.parentNode.insertBefore(js, fjs);
	      }(document, 'script', 'facebook-jssdk'));

	      function statusChangeCallback(response) {
	        if (response.status === 'connected') {
	          getINFO();
	        } else {
	          $('#login2').css('display', 'block');
	          $('#logout2').css('display', 'none');
	          $('#upic2').attr('src', '');
	          $('#uname').html('');
	        }
	      }
	    	  
	      function fbLogin () {
	        FB.login(function(response){
	          statusChangeCallback(response);
	        }, {scope: 'public_profile, email'});
	      }

	      function fbLogout () {
	        FB.logout(function(response) {
	          statusChangeCallback(response);
	        });
	      }

	      function getINFO() {
	        FB.api('/me?fields=id,name,picture.width(100).height(100).as(picture_small)', function(response) {
	        console.log(response);
	        console.log(response.id);
	    	var pname = response.name;
	    	var pid = response.id;
	    	$.ajax({
				data: {id:pid,name:pname},
				url: 'loginProcess',
				type: 'post',
				dataType: 'text',
				success: function (json) {
					var result = JSON.parse(json);
					if(result.code == "success"){
						alert(result.desc);
						fbLogout();
						window.location.replace("login.jsp");
					}else{
						alert(result.desc);
					}
				}
			});
	          $('#login2').css('display', 'none');
	          $('#logout2').css('display', 'block');
	          $('#upic2').attr('src', response.picture_small.data.url );
	          $('#uname').html('[ ' + response.name + ' ]');
	        });
	      }
	      
	      Kakao.init('7837425de0e9c3ddfd265563d8ff3447');
	      function loginWithKakao() {
	        // 로그인 창을 띄웁니다.
	        Kakao.Auth.login({
	          success: function(authObj) {
	            //alert(JSON.stringify(authObj));
	            signIn(authObj);
	          },
	          fail: function(err) {
	            alert(JSON.stringify(err));
	          }
	        });
	      };

	      function signIn(authObj) {
	          //console.log(authObj);
	          Kakao.API.request({
	              url: '/v2/user/me',
	              success: function(res) {
	                  console.log(res);
	                  console.log(res.id);
	                  var pname = res.properties.nickname;
	                  var pid = res.id;
	      	    	$.ajax({
	      				data: {id:pid,name:pname},
	      				url: 'loginProcess',
	      				type: 'post',
	      				dataType: 'text',
	      				success: function (json) {
	      					var result = JSON.parse(json);
	      					if(result.code == "success"){
	      						alert(result.desc);
	      						signOut3();
	      						window.location.replace("login.jsp");
	      					}else{
	      						alert(result.desc);
	      					}
	      				}
	      			});
	                  $('#login3').css('display', 'none');
	                 	$('#logout3').css('display', 'block');
	                  $('#upic3').attr('src', res.properties.thumbnail_image );
	                 	$('#uname').html('[ ' + res.properties.nickname + ' ]');
	               }
	           })
	  	}

	      function signOut3() {
	  	    Kakao.Auth.logout(function () {
	  	    	$('#login3').css('display', 'block');
	  	    	$('#logout3').css('display', 'none');
	  	    	$('#upic3').attr('src', '');
	  	    	$('#uname').html('');
	  	    });
	  	}
	</script>
</head>
<body>
	<form id="loginProcess">
		아이디 : <input type="text" id="id" name="id"
						value ="<% if(session.getAttribute("id") != null)
										out.println(session.getAttribute("id"));
										%>"><br/>
		비밀번호 : <input type="password" id="pw" name="pw"><br/>
		<input type="button" value="로그인" onclick="formcheck()">&nbsp; &nbsp; 
		<input type="button" value="회원가입" onclick="javascript:window.location='join.jsp'">
	</form>
	 <div id="my-signin2"></div>
    <div id="logout1" style="display: none;">
    <input type="button" class="btn btn-success" onclick="signOut1();" value="로그아웃" /><br>

    <img id="upic1" src=""><br>
    <span id="uname"></span>
    </div>  
    <script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>

<div id="login2" style="display: block;">
    <input type="button" onclick="fbLogin();" value="페이스북 로그인" /><br>
</div>

<div id="logout2" style="display: none;">
    <input type="button" class="btn btn-success" onclick="fbLogout();" value="로그아웃" /><br>

    <img id="upic2" src=""><br>
    <span id="uname"></span>
</div>

<div id="login3" style="display: block">
    <a id="custom-login-btn" href="javascript:loginWithKakao()">
    <img src="//mud-kage.kakao.com/14/dn/btqbjxsO6vP/KPiGpdnsubSq3a0PHEGUK1/o.jpg" width="300"/>
    </a>
</div>

<div id="logout3" style="display: none;">
    <input type="button" class="btn btn-success" onclick="signOut3();" value="로그아웃" /><br>

    <img id="upic3" src=""><br>
   	<span id="uname"></span>
</div>

	<div id="naverIdLogin">
		<a id="naverIdLogin_loginButton" href="#" role="button"><img src="https://static.nid.naver.com/oauth/big_g.PNG" width=320></a>
	</div>

	<!-- /container -->
	<script src="https://code.jquery.com/jquery-1.12.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<!-- (2) LoginWithNaverId Javscript SDK -->
	<script src="naveridlogin_js_sdk_2.0.0.js"></script>

	<!-- (3) LoginWithNaverId Javscript 설정 정보 및 초기화 -->
	<script>
		var naverLogin = new naver.LoginWithNaverId(
			{
				clientId: "7WkrHNCp62R4MhIx1TKq",
				callbackUrl: "http://localhost:8081/Project2/login.jsp",
				isPopup: false,
				loginButton: {color: "green", type: 3, height: 60}
			}
		);
		/* (4) 네아로 로그인 정보를 초기화하기 위하여 init을 호출 */
		naverLogin.init();
		
		/* (4-1) 임의의 링크를 설정해줄 필요가 있는 경우 */
		$("#gnbLogin").attr("href", naverLogin.generateAuthorizeUrl());

		/* (5) 현재 로그인 상태를 확인 */
		window.addEventListener('load', function () {
			naverLogin.getLoginStatus(function (status) {
				if (status) {
					/* (6) 로그인 상태가 "true" 인 경우 로그인 버튼을 없애고
					   사용자 정보를 출력합니다. */
					setLoginStatus();
				}
			});
		});

		/* (6) 로그인 상태가 "true" 인 경우 로그인 버튼을 없애고
		   사용자 정보를 출력합니다. */
		function setLoginStatus() {
			console.log(naverLogin.user);
			console.log(naverLogin.user.getId());
			var pname = naverLogin.user.getName();
			var pid = naverLogin.user.getId();
	    	$.ajax({
				data: {id:pid,name:pname},
				url: 'loginProcess',
				type: 'post',
				dataType: 'text',
				success: function (json) {
					var result = JSON.parse(json);
					if(result.code == "success"){
						alert(result.desc);
						naverLogin.logout();
						window.location.replace("login.jsp");
					}else{
						alert(result.desc);
					}
				}
			});
			var uid = naverLogin.user.getId();
			var profileImage = naverLogin.user.getProfileImage();
			var uName = naverLogin.user.getName();
			var nickName = naverLogin.user.getNickName();
			var eMail = naverLogin.user.getEmail();
			$("#naverIdLogin_loginButton").html(
					'<br><br><img src="' + profileImage + 
					'" height=50 /> <p>' + uid + "-" + uName + '님 반갑습니다.</p>');
			$("#gnbLogin").html("Logout");
			$("#gnbLogin").attr("href", "#");
			/* (7) 로그아웃 버튼을 설정하고 동작을 정의합니다. */
			$("#gnbLogin").click(function () {
				naverLogin.logout();
				//location.reload();
				location.href="http://localhost:8081/Project2/login.jsp";
			});
		}
	</script>
</body>
</html>