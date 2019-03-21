
function getnewOrders(){
	$(".neworder").click(function(){
		$.ajax({
			url:"../../ownerLogin/getNewOrders",
			success:function(data)
			{
				var data_ = JSON.parse(data);
				var mes = data_.info.orderid;
				var str = "";
				for (var i = 0; i < data_.info.length; i++)
				{

					str = str + '<tr><td>'+data_.info[i].orderid+'</td><td>黄焖鸡x1<br>粉丝汤<br></td><td>加辣</td><td>'+timestampToTime(data_.info[i].orderstarttime)+'</td><td>'+data_.info[i].detailaddress+'</td><td><div class="btn-group" role="group" aria-label="..."><button type="button" class="btn btn-default">接单</button><button type="button" class="btn btn-default">拒单</button></div></td></tr>';
				}
				$(".neworder_table").append(str);
				//添加接单拒单的未来事件
			},
			complete:function(xhr)
					{
						console.log("complete");
						var sessionstatus = xhr.getResponseHeader("SESSIONSTATUS");
						if (sessionstatus == "TIMEOUT")
						{
							console.log(xhr.getResponseHeader("CONTEXTPATH"));
							window.location.href= xhr.getResponseHeader("CONTEXTPATH");
						}
					},
				error:function(){
					console.log("error");
					alert("网络问题，稍后再试！！");
				}
		})
	})
}


$(".newevalute").click(function(){
	$.ajax({
		url:"../../ownerLogin/getNewOrders",
		success:function(data)
		{
			var data_ = JSON.parse(data);
			var mes = data_.info.orderid;
			var str = "";
			for (var i = 0; i < data_.info.length; i++)
			{

				str = str + '<tr><td>'+data_.info[i].orderid+'</td><td>黄焖鸡x1<br>粉丝汤<br></td><td>加辣</td><td>'+timestampToTime(data_.info[i].orderstarttime)+'</td><td>'+data_.info[i].detailaddress+'</td><td><div class="btn-group" role="group" aria-label="..."><button type="button" class="btn btn-default">接单</button><button type="button" class="btn btn-default">拒单</button></div></td></tr>';
			}
			$(".neworder_table").append(str);
			//添加接单拒单的未来事件
		},
		complete:function(xhr)
				{
					console.log("complete");
					var sessionstatus = xhr.getResponseHeader("SESSIONSTATUS");
					if (sessionstatus == "TIMEOUT")
					{
						console.log(xhr.getResponseHeader("CONTEXTPATH"));
						window.location.href= xhr.getResponseHeader("CONTEXTPATH");
					}
				},
			error:function(){
				console.log("error");
				alert("网络问题，稍后再试！！");
			}
	})
})


$(".today_order").click(function(){
	$.ajax({
		url:"../../ownerLogin/getNewOrders",
		success:function(data)
		{
			var data_ = JSON.parse(data);
			var mes = data_.info.orderid;
			var str = "";
			for (var i = 0; i < data_.info.length; i++)
			{

				str = str + '<tr><td>'+data_.info[i].orderid+'</td><td>黄焖鸡x1<br>粉丝汤<br></td><td>加辣</td><td>'+timestampToTime(data_.info[i].orderstarttime)+'</td><td>'+data_.info[i].detailaddress+'</td><td><div class="btn-group" role="group" aria-label="..."><button type="button" class="btn btn-default">接单</button><button type="button" class="btn btn-default">拒单</button></div></td></tr>';
			}
			$(".neworder_table").append(str);
			//添加接单拒单的未来事件
		},
		complete:function(xhr)
				{
					console.log("complete");
					var sessionstatus = xhr.getResponseHeader("SESSIONSTATUS");
					if (sessionstatus == "TIMEOUT")
					{
						console.log(xhr.getResponseHeader("CONTEXTPATH"));
						window.location.href= xhr.getResponseHeader("CONTEXTPATH");
					}
				},
			error:function(){
				console.log("error");
				alert("网络问题，稍后再试！！");
			}
	})
})




function timestampToTime(timestamp) {
    var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    Y = date.getFullYear() + '-';
    M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    D = date.getDate() + ' ';
    h = date.getHours() + ':';
    m = date.getMinutes() + ':';
    s = date.getSeconds();
    return Y+M+D+h+m+s;
	}