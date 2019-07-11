/**
 * 装配页面
 */
var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope,$http,$interval){
	var id=$("input#id").val();
	$http({
		method: 'GET',
		url:"/member/assemblyProgress/details.json?id="+id,
		cache:false,
		async:false
	}).then(function(res){
		$scope.item=res.data.project;
		console.info($scope.item);
	})

})