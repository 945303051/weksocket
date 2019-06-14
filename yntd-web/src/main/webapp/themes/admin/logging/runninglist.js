/**
 * 
 */
var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope,$http,$interval) {
		 $(function(){
				$.ajax({
					type :"GET",
					url :"/admin/logging/running/data.json",
					async:true,
					cache : false,
					ifModified:true,
					success : function(data) {
						/*optionPie.series[0].data=data.resault;
						optionPie.title.text=name;
						pie.setOption(optionPie);*/
					}
				})
		 })
})