 //控制层 
app.controller('itemCatController' ,function($scope,$controller   ,itemCatService){	
	
	$controller('baseController',{$scope:$scope});//继承
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		itemCatService.findAll().success(
			function(response){
				$scope.list=response.data;
			}			
		);
	}    
	
	//分页
	$scope.findPage=function(pageNum,pageSize){
		itemCatService.findPage(pageNum,pageSize).success(
			function(response){
				$scope.list=response.data;
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//查询实体 
	$scope.findOne=function(id){				
		itemCatService.findOne(id).success(
			function(response){
				$scope.entity= response.data;
			}
		);				
	}
	
	//保存 
	$scope.save=function(){				
		var serviceObject;//服务层对象  				
		if($scope.entity.id!=null){//如果有ID
			serviceObject=itemCatService.update( $scope.entity ); //修改  
		}else{
			$scope.entity.parentId=$scope.parentId;//赋予上级ID
			serviceObject=itemCatService.add( $scope.entity  );//增加 
		}				
		serviceObject.success(
			function(response){
				if("0"=== response.code){
					//重新查询
					$scope.findByParentId($scope.parentId);//重新加载
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
		itemCatService.delete( $scope.selectIds ).success(
			function(response){
				if("0"=== response.code){
					$scope.reloadList();//刷新列表
					$scope.selectIds = [];
				}						
			}		
		);				
	}
	
	$scope.searchEntity={};//定义搜索对象 
	
	//搜索
	$scope.search=function(pageNum,pageSize){
		itemCatService.search(pageNum,pageSize,$scope.searchEntity).success(
			function(response){
				$scope.list=response.data;
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	// 根据父ID查询分类
	$scope.findByParentId =function(parentId){
		$scope.parentId=parentId;//记住上级ID
		itemCatService.findByParentId(parentId).success(function(response){
			$scope.list=response.data;
		});
	}
	
	// 定义一个变量记录当前是第几级分类
	$scope.grade = 1;
	
	$scope.setGrade = function(value){
		$scope.grade = value;
	}
	
	$scope.selectList = function(p_entity){
		
		if($scope.grade == 1){
			$scope.entity_1 = null;
			$scope.entity_2 = null;
		}
		if($scope.grade == 2){
			$scope.entity_1 = p_entity;
			$scope.entity_2 = null;
		}
		if($scope.grade == 3){
			$scope.entity_2 = p_entity;
		}
		
		$scope.findByParentId(p_entity.id);
	}
	
	
	
	
	
	
	
	
	
	
    
});	
