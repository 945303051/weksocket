/**
 * 装配页面
 */
var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope,$http,$interval){
	$http({
		method: 'GET',
		url:"/member/assemblyProgress/data.json",
		cache:false,
		async:false
	}).then(function(res){
		$scope.items=res.data.projects;
		/*$scope.items=res.data.projects.content;
		var totalElements=res.data.projects.totalElements;
		var totalPages=res.data.projects.totalPages;
		var number=res.data.number;
		pagination(totalElements,totalPages,number);*/
	})
	
	function pagination(totalElements,totalPages,number){
		$(".tcdPageCode").createPage({
			elementCount :totalElements,
			pageCount :totalPages,
			current :number,
			backFn : function(to){
				Fw.updateFilter($("#uuid").val(), 'page', to);
			}
		})
	};
	
	$scope.gain=function(id){
		window.location.href="/member/assemblyProgress/gain?id="+id;
	}
})