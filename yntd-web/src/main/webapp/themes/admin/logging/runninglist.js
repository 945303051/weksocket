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

$scope.chosenTime=function(){
	layer.open({
		type: 1,
		title:"数据导出",
		offset : [ '200px', '' ],
		border : [ 10, 0.3, '#000', true ],
		area : [ '550px', '180px' ],
		content: $("#chosenTime")
	})
}

})