/**
 * 
 */
var app = angular.module('dashboradApp', []);
app.controller('myCon', function($scope,$http,$interval) {
	$interval(function(){
		$http({
			method: 'GET',
			url:"/admin/index/data.json",
			cache:false,
			async:false}).then(function(res){
			$scope.runningTimes=res.data.RUNNING;
			$scope.poweroffTimes=res.data.POWEROFF;
			$scope.alarmTimes=res.data.ALARM;
			$scope.waitingTimes=res.data.WAITING;
			$scope.manualTimes=res.data.MANUAL;
		})
	},2000)
})
