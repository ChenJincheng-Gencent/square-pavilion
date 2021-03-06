//服务层
app.service('specificationService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get('../manager/v1/specification/all');
	}
	//分页 
	this.findPage=function(pageNum,pageSize){
		return $http.get('../manager/v1/specification/list/page?pageNum='+pageNum+'&pageSize='+pageSize);
	}
	//查询实体
	this.findOne=function(id){
		return $http.get('../manager/v1/specification/group?specId='+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post('../manager/v1/specification/group',entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.put('../manager/v1/specification/group',entity );
	}
	//删除
	this.delete=function(ids){
		return $http.delete('../manager/v1/specification/group/batch?ids='+ids);
	}
	//搜索
	this.search=function(pageNum,pageSize,searchEntity){
		return $http.post('../manager/v1/specification/list/page/condition?pageNum='+pageNum+"&pageSize="+pageSize, searchEntity);
	}    	
	//下拉列表
	this.selectOptionList=function(){
		return $http.get('../manager/v1/specification/all');
	}
	
});
