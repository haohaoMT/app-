<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="common/header.jsp"%>
<div class="clearfix"></div>
<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">
		<div class="x_panel">
			<div class="x_title">
				<h2>
					新增用户 <i class="fa fa-user"></i><small>${devUserSession.devName}</small>
				</h2>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">

				<form class="form-horizontal form-label-left" action="addDevUser" method="post" id="form1" >

					<input name="id" type="hidden" value="${devUserSession.id}">
					<div class="item form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12"
							for="name">开发者姓名<span class="required">*</span>
						</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input id="devName" class="form-control col-md-7 col-xs-12"
								data-validate-length-range="6" data-validate-words="2"
								name="devName" placeholder="请输入你的姓名"
								required="required" type="text">
						</div>
					</div>
					
					<div class="item form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12"
							for="email">邮箱<span class="required">*</span>
						</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="email" id="devEmail" name="devEmail" required="required"
								class="form-control col-md-7 col-xs-12">
						</div>
					</div>
					<div class="item form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12"
							for="name">开发者账号<span class="required">*</span>
						</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input id="devCode" class="form-control col-md-7 col-xs-12"
								data-validate-length-range="6" data-validate-words="2"
								name="devCode" placeholder="请输入你的账号名"
								required="required" type="text" >
						</div>
					</div>
					<div class="item form-group">
						<label for="password" class="control-label col-md-3">密码</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input id="devPassword" type="password" name="devPassword"
								data-validate-length="6,8"
								class="form-control col-md-7 col-xs-12" required="required">
						</div>
					</div>
					<div class="item form-group">
						<label for="password2"
							class="control-label col-md-3 col-sm-3 col-xs-12">重复密码</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input id="password2" type="password" name="password2"
								data-validate-linked="password"
								class="form-control col-md-7 col-xs-12" required="required">
						</div>
					</div>
					
					<div class="item form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12"
							for="textarea">开发者简介<span class="required">*</span>
						</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<textarea id="devInfo" required="required" name="devInfo"
								class="form-control col-md-7 col-xs-12"></textarea>
						</div>
					</div>
					<div class="ln_solid"></div>
					<div class="form-group">
						<div class="col-md-6 col-md-offset-3">
							<button type="submit" id="sub" class="btn btn-primary">提交</button>
							<button  type="reset" class="btn btn-success">重置</button>
						</div>
						<span>${error }</span>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<%@include file="common/footer.jsp"%>
 <script src="${pageContext.request.contextPath }/statics/localjs/devuseradd.js"></script>  

	