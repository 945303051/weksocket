;(function($,e){
var color =['#33CC66','#909090', '#880000','#CCFF66']
var timeLineOption = {
		color:color,
	    baseOption: {
	        title: {},
	        timeline: {
	            axisType: 'category',
	            orient: 'vertical',
	            autoPlay: true,
	            inverse: true,
	            playInterval: 3000,
	            left: null,
	            right: 0,
	            top: 20,
	            bottom: 20,
	            width: 55,
	            height: null,
	            symbol: 'none',
	            lineStyle: {
	                color: '#555'
	            },
	            checkpointStyle: {
	                color: '#bbb',
	                borderColor: '#777',
	                borderWidth: 2
	            },
	            controlStyle: {
	                showNextBtn: false,
	                showPrevBtn: false,
	                normal: {
	                    color: '#666',
	                    borderColor: '#666'
	                },
	                emphasis: {
	                    color: '#aaa',
	                    borderColor: '#aaa'
	                }
	            },
	            data: ['8:00','9:00','10:00','11:00','12:00','13:00','14:00','15:00','16:00','17:00','18:00','19:00','20:00','21:00','22:00','23:00','24:00'],
	            /*label: {
	                formatter: function(s) {
	                    return (new Date(s)).getFullYear();
	                }
	            }*/
	        },
	        tooltip: {
	            trigger: 'axis',
	            axisPointer: {
	                type: 'shadow'
	            },
	        },
	        legend: {
	            data: ['停机','报警','等待']
	        },
	        grid: {
	            left: '3%',
	            right: '15%',
	            bottom: '3%',
	            containLabel: true
	        },
	        xAxis: {
	            type: 'value',
	        },
	        yAxis: {
	            type: 'category',
	            data:['m1','m2','m3','m4','m5']
	        },
	        series: [],
	        animationEasing: 'elasticOut',
	        animationDelayUpdate: function(idx) {
	            return idx * 5;
	        }
	    },
	    options: []
	};
  
	var MyTimeLine=function(){
		var _self =this;
		this.init();
	}
	MyTimeLine.prototype={
		init:function(){
			this.initCharts();
			this.initWebsocket();
		},extend : function(obj, obj2) {
			for ( var k in obj2) {
				obj[k] = obj2[k];
			}
			return obj;
		},initCharts:function(){
			var pannel= $("[time-line='true']").get(0);
			this.timeLine=e.init(pannel);
		},initWebsocket:function(){
			var _this = this;
			var time;
			_this.timeLine.on('timelinechanged',function(index){
				time=timeLineOption.baseOption.timeline.data[index.currentIndex]; 
			})
			var mywebsocket=new Mywebsocket("ws://localhost:8080/timeLine/timeLine");
		},getOptionData:function(time){
			var _this = this;
			console.info(time);
		}
	}
	window.MyTimeLine = MyTimeLine;
})(jQuery, echarts)
