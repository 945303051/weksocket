/**
 * 作为看板的首页，负责跳转到两个作业区
 */
var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope,$http,$interval){
	$scope.toIndex=function(){
		window.location.href="/member/assemblyProgress/toIndex";
	}
	
	$scope.list=function(){
		window.location.href="/member/assemblyProgress/list";
	}
})

setInterval(function(){
	var now = new Date();
	var weekArray  =  new Array("日", "一", "二", "三", "四", "五", "六");
	var date = now.getFullYear()+"-"+(now.getMonth()+1)+"-"+now.getDate()+"星期"+weekArray[now.getDay()];
	var time = now.getHours()+":"+now.getMinutes()+":"+now.getSeconds();
	$("#date").text(date);
	$("#time").text(time);
},1000)