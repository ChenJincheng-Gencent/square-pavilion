# square-pavilion（四方阁）

## 前言

&emsp;&emsp;Square pavilion is a cube project for e-commerce.（四方阁是一个为电商而生的中台项目）。在玄幻小说中，总有一些比较牛逼且左右逢源于各个势力之间的中立组织，这些组织通常带有“阁”字。因此本项目取名“四方阁”，取包容并蓄，吸取百家之长之意。欢迎有兴趣的小伙伴们一起努力，把这个项目完善、推广。


## 技术选型

技术|说明|版本
:--:|:--:|:--:
JDK|Java开发工具包|11
Maven|构建工具|3.6.3
IDEA|IDE软件|2020.1.2
Spring Boot|容器+MVC框架|2.3.1.RELEASE
MyBatis|ORM框架|2.1.0
Elasticsearch|搜索引擎|7.3.1
Redis|分布式缓存|5.0.5
MongoDb|NoSql数据库|4.2
Mysql|关系型数据库|5.7.27
Lombok|简化对象封装工具|1.18.8
Dubbo|RPC框架|2.7.0
Zookeeper|分布式注册中心|3.4.14
Swagger2|API文档工具|2.9.2


## 项目架构

&emsp;&emsp;Square pavilion采用微服务思想，实现分布式、高并发且易于拓展的中台框架。结合我所在公司的中台实践，将该项目分为应用层、中心层、能力服务层及基本组件层四大层级。项目为电商项目，包含前台商城系统和后台管理系统。  
&emsp;&emsp;从业务维度拆分，四大层级具体内容，如下所述：  
&emsp;&emsp;应用层有mall-manager-application（后台管理应用层）、mall-member-application（会员应用层）、mall-item-application（商品应用层）、mall-trade-application（交易应用层）、mall-share-application（开放应用层）；  
&emsp;&emsp;中心层有mall-member-center（会员中心）、mall-item-center（商品中心）、mall-trade-center（交易中心）、mall-promotion-center（营销中心）、mall-inventory-center（库存中心）；  
&emsp;&emsp;能力服务层有mall-search-service（搜索服务）、mall-mq-service（MQ服务）、mall-message-service（消息服务）、mall-payment-service（支付服务）、mall-identity-service（认证服务）；  
&emsp;&emsp;基本组件层有mall-common（公共组件）、mall-job（定时任务组件）、mall-cache（缓存组件）。  
&emsp;&emsp;另外，还包含mall-modules(工程父模块)以及mall-doc（文档模块）。  