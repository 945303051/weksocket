var myTimeLine = new MyTimeLine();
var ranking = new Ranking();
var myLine = new MyLine();

var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope,$http,$interval) {
	$interval(function(){
		$http({
			method: 'GET',
			url:"/member/datalist.json",
			cache:false,
			async:false}).then(function(res){
			$scope.items=res.data.resault;
		})
	},2000)
})

var myPies = new MyPies();

setInterval(function(){
	var now = new Date();
	var date = now.getFullYear()+"-"+(now.getMonth()+1)+"-"+now.getDate()+"星期"+now.getDay();
	var time = now.getHours()+":"+now.getMinutes()+":"+now.getSeconds();
	$("#date").text(date);
	$("#time").text(time);
},1000)

setInterval(function(){
listRoll();
},6000)

function listRoll(){
	var $fristTr =  $("tbody tr:first").clone(false);
	$("tbody tr:first").remove();
	$("tbody").append($fristTr);
}

$(function(){
    new ZouMa().Start();
});

function ZouMa() {
	this.maxLength = 3;  
	this.Timer = 6000; 
	this.Ul = $("ul#pies");
	var handId; 
	var self = this;
	this.Start = function () {
	    if (self.Ul.children().length < this.maxLength) {
	        self.Ul.append(self.Ul.children().clone());
	    }
	    handId = setInterval(self.Play, self.Timer);
	}
		this.Play = function () {
	    var li = self.Ul.children("li").eq(0);
	    var left = li.eq(0).width();
	    li.animate({ "marginLeft": (-1 * left) + "px" }, 1000, function () {
			$(this).css("margin-left", "auto").appendTo(self.Ul);
        });
    }
}