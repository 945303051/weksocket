/**
 * 
 */
var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope,$http,$interval) {
	$http({
		method: 'GET',
		url:"/admin/logging//data.json",
		cache:false,
		async:false
	}).then(function(res){
		$scope.items=res.data.runningRecords.content;
		 
})
})