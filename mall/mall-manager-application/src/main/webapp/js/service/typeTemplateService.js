//服务层
app.service('typeTemplateService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get('../manager/v1/template/all');
	}
	//分页 
	this.findPage=function(pageNum,pageSize){
		return $http.get('../manager/v1/template/list/page?pageNum='+pageNum+'&pageSize='+pageSize);
	}
	//查询实体
	this.findOne=function(id){
		return $http.get('../manager/v1/template/group?id='+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post('../manager/v1/template/group',entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.put('../manager/v1/template/group',entity );
	}
	//删除
	this.delete=function(ids){
		return $http.delete('../manager/v1/template/batch?ids='+ids);
	}
	//搜索
	this.search=function(pageNum,pageSize,searchEntity){
		return $http.post('../manager/v1/template/group/list/page/condition?pageNum='+pageNum+"&pageSize="+pageSize, searchEntity);
	}    	
});
