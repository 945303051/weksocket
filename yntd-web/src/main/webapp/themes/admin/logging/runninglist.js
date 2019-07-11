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
		var para="RUNNING";
		showLineChart(para);
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

$scope.keys=[{key:"name",value:"名称"}]

$scope.search=function(){
	var val= $scope.value;
	var key= $scope.selected;
	if(typeof val=="undefined"){
		layer.msg("请输入需要查询的内容");
		$scope.removeFilter(key);
		return;
	}else{
		$scope.updateFilter(key,val);
	}
}

$scope.createRecordTime=function(){
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	console.info(startTime);
	if(startTime==""){
		layer.msg("请选择开始时间！");
		return;
	}else if(endTime==""){
		layer.msg("请选择结束时间！");
		return;
	}
	var recordTime=startTime+"&"+endTime;
	$scope.updateFilter("recordTime",recordTime);
	
}

$scope.updateFilter=function(key,val){
	Fw.updateFilter($("#uuid").val(),key,val);
}

$scope.removeFilter=function(key){
	Fw.removeFilter($("#uuid").val(),key);
}

$scope.exportData=function(){
	window.location.href="/admin/logging/running/exportdata";
}

function showLineChart(para){
	var myChart=echarts.init(document.getElementById('line'));
	$.ajax({
		type :"GET",
		url :"/admin/logging/line2?para="+para,  
		async:true,
		cache : false,
		ifModified:true,
		success : function(data){
			 var type = data.type;
			 var optionLine = {
						color: ['#00FF00','#696969', '#DC143C','#FFFF00'],
					    tooltip: {
					        trigger: 'axis'
					    },
					    grid:{
					        left: '5%',
					        right: '10%',
					        bottom: '3%',
					        containLabel: true
					    },
					    dataZoom: [
				               {   // 这个dataZoom组件，默认控制x轴。
				                   type: 'slider', // 这个 dataZoom 组件是 slider 型 dataZoom 组件
				                   xAxisIndex: 0,
				                   start: 0,      // 左边在 10% 的位置。
				                   end: 100         // 右边在 60% 的位置。
				               },
					    ],
					    xAxis: {
					        type: 'category',
				            axisLine: {onZero: true},
				            name:"日期",
				            nameTextStyle:{
				        		color:"red",
				        		fontSize:12
				    		},
					        boundaryGap: false,
					        data: [],
					        axisLabel: {
				                textStyle: {
				                    color: '#33cc66',//坐标值得具体的颜色
				                }
				            }
					    },
					    yAxis: {
					        type: 'value',
					        axisLabel: {
				                textStyle: {
				                    color: '#33cc66',//坐标值得具体的颜色
				                }
				            },
				            name:'运行时间单位：分钟',
				            nameTextStyle:{
				        		color:"red",
				        		fontSize:12
				    		},	
					    },
						series: [{
				            name:'运行时间',
				            type:'line',
						    data:[]
						}]
				};
			 optionLine.xAxis.data=data.xAxis;
			 optionLine.series=data.series;
			 myChart.setOption(optionLine);
		} 
	})
	
}

})