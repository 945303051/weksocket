/**
 * 
 */
var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope,$http,$interval) {
	$http({
		method: 'GET',
		url:"/admin/logging/running/data.json",
		cache:false,
		async:false
	}).then(function(res){
		$scope.items=res.data.runningRecords.content;
		var totalElements=res.data.runningRecords.totalElements;
		var totalPages=res.data.runningRecords.totalPages;
		var number=res.data.number;
		pagination(totalElements,totalPages,number)
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
})