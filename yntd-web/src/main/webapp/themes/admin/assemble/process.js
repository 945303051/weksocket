/**
 * 
 */
var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope,$http,$interval) {
	var uuid=$("#uuid").val();
	$(function(){
		$http({
			method: 'GET',
			url:"/admin/assemble/data.json",
			cache:false,
			async:false
		}).then(function(res){
//			$scope.items=res.data.projects;
			$scope.items=res.data.projects.content;
			var totalElements=res.data.projects.totalElements;
			var totalPages=res.data.projects.totalPages;
			var number=res.data.number;
			pagination(totalElements,totalPages,number)
		})
	});
		
	
	function pagination(totalElements,totalPages,number){
		$(".tcdPageCode").createPage({
			elementCount :totalElements,
			pageCount :totalPages,
			current :number,
			backFn : function(to){
				Fw.updateFilter(uuid, 'page', to);
			}
		})
	};
	
		$scope.process=function(){
			window.location.href="/admin/assemble/process";
		}
		
		$scope.update=function(id){
			layer.open({
				type: 1,
				title:"项目详情",
				offset : 'auto',
				border : [ 10, 0.3, '#000', true ],
				area : ['800px','689px'],
				content:$("#details"),
				success:function(){
					getDetails(id);
				}
			})
		}
		function getDetails(id){
			$http({
				method: 'GET',
				url:"/admin/assemble/details.json?id="+id,
				cache:false,
				async:false
			}).then(function(res){
				$scope.item=res.data.project;
			})
		}
		
		$scope.search=function(){
			var para = $scope.value;
			console.info(para);  
			if(typeof para=="undefined"){
				layer.msg("请输入要查询的内容");
			}else{
				$scope.query(para);
			}
		}
		$scope.query=function(para){
			window.location.href="/admin/assemble/data.json?para="+para;
		}
		
		/*$scope.search=function(){
			var name = $scope.value;
			console.info(name);  
			if(typeof name=="undefined"){
				layer.msg("请输入要查询的内容");
			}else{
				$scope.query();
			}
		}*/
		/*$scope.query=function(){
			var val= $scope.value;
			Fw.updateFilter(uuid,"name",val);
		}*/
		
		$scope.updateFilter=function(key,val){
			Fw.updateFilter(uuid,key,val);
		}
		$scope.removeFilter=function(key){
			Fw.removeFilter(uuid,key);
		}
		
		$scope.add=function(){
			window.location.href="/admin/assemble/add";
		}
		
//		非空验证
		$().ready(function() {
		    $("#add").validate();
		});
		
		$scope.remove=function(id){
			window.location.href="/admin/assemble/delete?id="+id;
		}
})