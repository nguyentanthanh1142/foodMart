<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<h2>Reset Your Password</h2>
		<p>Please enter your login email, we'll send a new random password
			to your inbox:</p>

		<form id="resetForm" action="<c:url value="/dang-nhap"/>" method="post">
			<table>
				<tr>
					<td>Email:</td>
					<td><input type="text" name="email" id="email" size="20"></td>
					<td><input type="hidden" value="resetpassword" name ="action"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button type="submit">Send me new password</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript">
 
    $(document).ready(function() {
        $("#resetForm").validate({
            rules: {
                email: {
                    required: true,
                    email: true
                }      
            },
             
            messages: {
                email: {
                    required: "Please enter email",
                    email: "Please enter a valid email address"
                }
            }
        });
 
    });
</script>
</body>
</html>