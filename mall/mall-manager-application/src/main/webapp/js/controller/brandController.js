 //控制层 
app.controller('brandController' ,function($scope,$controller   ,brandService){	
	
	$controller('baseController',{$scope:$scope});//继承
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		brandService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//分页
	$scope.findPage=function(pageNum,pageSize){
		brandService.findPage(pageNum,pageSize).success(
			function(response){
				$scope.list=response.data;
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//查询实体 
	$scope.findOne=function(id){				
		brandService.findOne(id).success(
			function(response){
				$scope.entity= response;					
			}
		);				
	}
	
	//保存 
	$scope.save=function(){				
		var serviceObject;//服务层对象  				
		if($scope.entity.id!=null){//如果有ID
			serviceObject=brandService.update( $scope.entity ); //修改  
		}else{
			serviceObject=brandService.add( $scope.entity  );//增加 
		}				
		serviceObject.success(
			function(response){
				if("0"===response.code){
					//重新查询 
		        	$scope.reloadList();//重新加载
				}else{
					alert(response.msg);
				}
			}		
		);				
	}
	
	 
	//批量删除 
	$scope.delete=function(){
		//获取选中的复选框			
		brandService.delete( $scope.selectIds ).success(
			function(response){
				if("0"===response.success){
					$scope.reloadList();//刷新列表
					$scope.selectIds=[];
				}						
			}		
		);				
	}
	
	$scope.searchEntity={};//定义搜索对象 
	
	//搜索
	$scope.search=function(pageNum,pageSize){
		brandService.search(pageNum,pageSize,$scope.searchEntity).success(
			function(response){
				$scope.list=response.data;
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
    
});	
