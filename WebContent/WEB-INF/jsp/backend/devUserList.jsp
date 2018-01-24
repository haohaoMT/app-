<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="common/header.jsp"%>
<div class="clearfix"></div>
<div class="row">
	</div>
	<div class="col-md-12 col-sm-12 col-xs-12">
		<div class="x_panel">
			<div class="x_content">
				<p class="text-muted font-13 m-b-30"></p>
				<div id="datatable-responsive_wrapper"
					class="dataTables_wrapper form-inline dt-bootstrap no-footer">
					<div class="row">
						<div class="col-sm-12">
							<table id="datatable-responsive"
								class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
								cellspacing="0" width="100%" role="grid"
								aria-describedby="datatable-responsive_info"
								style="width: 100%;">
								<thead>
									<tr role="row">
										<th class="sorting_asc" tabindex="0"
											aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="First name: activate to sort column descending"
											aria-sort="ascending">序号</th>
										<th class="sorting" tabindex="0"
											aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">
											开发者帐号</th>
										<th class="sorting" tabindex="0"
											aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">
											开发者名称</th>
										<th class="sorting" tabindex="0"
											aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">
											开发者邮箱</th>
										<th class="sorting" tabindex="0"
											aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">
											开发者简介</th>
										<th class="sorting" tabindex="0"
											aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">
											创建时间</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="devUserList" items="${devUserList }"
										varStatus="status">
										<tr role="row" class="odd">
											<td>${devUserList.id}</td>
											<td>${devUserList.devCode }</td>
											<td>${devUserList.devName }</td>
											<td>${devUserList.devEmail }</td>
											<td>${devUserList.devInfo }</td>
											<td>
											<fmt:formatDate value="${devUserList.creationDate }" pattern="yyyy-MM-dd"/></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<div class="box" id="box"></div>
				</div>

			</div>
		</div>
	</div>
</div>

<form method="post" action="devUserList" id="select">
					<input type="hidden" name="pageIndex"  id="currentPage" value="1" />
					<button type="submit" ></button>
					</form>
<%@include file="common/footer.jsp"%>
<script
	src="${pageContext.request.contextPath }/statics/localjs/rollpage.js"></script>
<script
	src="${pageContext.request.contextPath }/statics/localjs/appinfolist.js"></script>
<script
	src="${pageContext.request.contextPath }/statics/localjs/paging.js"></script>
<script>
    
        var totalCount = ${requestScope.pages.totalCount }<!--总条数  -->
        var currentPage=${requestScope.pages.currentPageNo };<!--总页数  -->
		var pageCount=${requestScope.pages.totalPageCount };<!--总页数  -->
        $('#box').paging({
            initPageNo: currentPage, // 初始页码
            totalPages: pageCount, //总页数
            totalCount: '合计' + totalCount + '条数据', // 条目总数
            slideSpeed: 600, // 缓动速度。单位毫秒
            jump: true, //是否支持跳转
            callback: function(page) { // 回调函数
            	$("#currentPage").val(page); 
            if (page!=currentPage) {
					$("#select").submit();
			}
						}
					})
				</script>


