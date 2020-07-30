 //控制层 
app.controller('specificationController' ,function($scope,$controller   ,specificationService){	
	
	$controller('baseController',{$scope:$scope});//继承
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		specificationService.findAll().success(
			function(response){
				$scope.list=response.data;
			}			
		);
	}    
	
	//分页
	$scope.findPage=function(pageNum,pageSize){
		specificationService.findPage(pageNum,pageSize).success(
			function(response){
				$scope.list=response.data;
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//查询实体 
	$scope.findOne=function(id){				
		specificationService.findOne(id).success(
			function(response){
				$scope.entity= response.data;
			}
		);				
	}
	
	//保存 
	$scope.save=function(){				
		var serviceObject;//服务层对象  				
		if($scope.entity.specification.id!=null){//如果有ID
			serviceObject=specificationService.update( $scope.entity ); //修改  
		}else{
			serviceObject=specificationService.add( $scope.entity  );//增加 
		}				
		serviceObject.success(
			function(response){
				if("0" === response.code){
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
		specificationService.delete( $scope.selectIds ).success(
			function(response){
				if("0"===response.data){
					$scope.reloadList();//刷新列表
					$scope.selectIds=[];
				}						
			}		
		);				
	}
	
	$scope.searchEntity={};//定义搜索对象 
	
	//搜索
	$scope.search=function(pageNum,pageSize){
		specificationService.search(pageNum,pageSize,$scope.searchEntity).success(
			function(response){
				$scope.list=response.data;
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//$scope.entity={specificationOptionList:[]};
	
	//增加规格选项行
	$scope.addTableRow=function(){
		$scope.entity.specificationOptionList.push({});			
	}
	
	//删除规格选项行
	$scope.deleteTableRow=function(index){
		$scope.entity.specificationOptionList.splice(index,1);
	}
    
});	
