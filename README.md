# square-pavilion（四方阁）

## 一、前言

&emsp;&emsp;Square pavilion is a cube project for e-commerce.（四方阁是一个为电商而生的中台项目）。在玄幻小说中，总有一些比较牛逼且左右逢源于各个势力之间的中立组织，这些组织通常带有“阁”字。因此本项目取名“四方阁”，取包容并蓄，吸取百家之长之意。欢迎有兴趣的小伙伴们一起努力，把这个项目完善、推广。


## 二、技术选型

技术|说明|版本
:--:|:--:|:--:
JDK|Java开发工具包|11
Maven|构建工具|3.6.3
IDEA|IDE软件|2020.1.2
Spring Boot|容器+MVC框架|2.3.2.RELEASE
Spring Cloud|微服务框架|Hoxton.SR8
Spring Cloud Alibaba|微服务框架|2.2.3.RELEASE
MyBatis|ORM框架|2.1.0
Elasticsearch|搜索引擎|7.3.1
Redis|分布式缓存|5.0.5
MongoDb|NoSql数据库|4.2
Mysql|关系型数据库|5.7.27
Lombok|简化对象封装工具|1.18.8
Nacos|分布式注册配置中心|1.3.2
Swagger2|API文档工具|2.9.2


## 三、项目架构

&emsp;&emsp;Square pavilion采用微服务思想，实现分布式、高并发且易于拓展的中台框架。结合我所积累的中台实践，将该项目分为应用层、业务中台层、能力服务层及基本组件层四大层级。项目为电商项目，包含前台商城系统和后台管理系统。  
&emsp;&emsp;从业务维度拆分，四大层级具体内容，如下所述：  
&emsp;&emsp;应用层有mall-manager-application（管理应用）、mall-member-application（会员应用）、mall-item-application（商品应用）、mall-trade-application（交易应用）、mall-share-application（开放应用）以及mall-job-application（任务应用）；  
&emsp;&emsp;业务中台层再分为三层：业务核心层、业务协作层和业务实体层。其中业务核心层有mall-trade-center（交易中心）、mall-inventory-center（库存中心）、mall-logistics-center（物流中心）；业务协作层有mall-promotion-center（营销中心）、mall-evaluation-center（评价中心）、mall-ticket-center（工单中心）；  
&emsp;&emsp;能力服务层有mall-search-service（搜索服务）、mall-mq-service（MQ服务）、mall-message-service（消息服务）、mall-payment-service（支付服务）、mall-identity-service（认证服务）；  
&emsp;&emsp;基本组件层有mall-common（公共组件）、mall-cache（缓存组件）、mall-modules(模块组件)以及mall-doc（文档组件）。  
![avatar](https://raw.githubusercontent.com/ChenJincheng-Gencent/square-pavilion/master/mall/mall-doc/%E6%9E%B6%E6%9E%84/Square%20Pavilion%E6%9E%B6%E6%9E%84%E5%9B%BE.png "Square Pavilion架构图")

## 四、架构设计思想和分层业务模型
&emsp;&emsp;基本组件层为技术收敛的通用能力，与具体业务无关，形式为jar包。目前设计的主要有定时任务和缓存组件。每个平台都可直接引入使用，而不需要重复建设，可扩展其他组件；  
&emsp;&emsp;能力服务层，是电商通用基本的服务能力，各个电商平台复用后，可以在上面增加个性化的能力，也可扩展其他服务能力。本层一般不会频繁改动，与业务相关，但与快速变化的业务需求关联不大。比如支付服务，支付服务完成多种支付方式接入后，基本不会改动，除非再接入新的支付方式。因此抽出收敛为jar包，嵌入到业务中台层中，供具体业务能力调用；  
&emsp;&emsp;业务中台层是电商基本的业务能力集合，比如商品管理、会员管理、库存管理，下单结算，物流发货，营销活动管理等，是实现电商业务流程的基石。可直接复用和扩展。业务中台层分为三层的目的在于业务解耦，解决循依赖和引用混乱问题。将业务分为核心、协作和实体三种形式，其中核心是电商核心业务流程涉及到的相关能力集合；协作是为了更好地实现核心业务而起到的辅助作用能力集合，去掉该层也可正常实现电商基本业务；实体为一些静态数据的管理，比如商品、会员等。通过将业务解耦为核心业务流程能力、协助辅助能力和静态数据管理能力后，可以实现业务的高内聚低耦合，方便业务需求快速迭代开发、维护和扩展；  
&emsp;&emsp;应用层是根据具体业务逻辑对业务中台层的能力进行聚合、编排，与前台商城和后台管理系统的表现形式紧密耦合。商品应用主要提供搜索，列表、详情、评价等功能；会员应用主要提供会员的登录、收货地址、积分和订单等功能；交易应用主要提供加入购物车，下单、支付等功能；管理应用主要提供后台管理功能；开放应用则主要提供跟外部进行接口交互的功能。不同电商平台往往在应用层的业务逻辑区别会较大，因而本层的可复用性相对较差，但业务拆分解耦清晰，框架可直接复用。  

## 五、端口号分配
应用名|代码模块|端口号
:--:|:--:|:--:
交易应用|mall-trade-application|9100-9199
会员应用|mall-member-application|9200-9299
商品应用|mall-item-application|9300-9399
管理应用|mall-manager-application|9400-9499
分享应用|mall-share-application|9500-9599
任务应用|mall-job-application|9600-9699
交易中心|mall-trade-center|8100-8199
会员中心|mall-member-center|8200-8299
商品中心|mall-item-center|8300-8399
库存中心|mall-inventory-center|8400-8499
营销中心|mall-promotion-center|8500-8599
工单中心|mall-ticket-center|8600-8699
物流中心|mall-logistics-center|8700-8799
评价中心|mall-evaluation-center|8800-8899    

## 六、Redis库分配
0号库留做公共使用  
应用名|代码模块|库号
:--:|:--:|:--:
交易应用|mall-trade-application|1
会员应用|mall-member-application|2
商品应用|mall-item-application|3
管理应用|mall-manager-application|4
分享应用|mall-share-application|5
任务应用|mall-job-application|6
交易中心|mall-trade-center|11
会员中心|mall-member-center|12
商品中心|mall-item-center|13
库存中心|mall-inventory-center|14
营销中心|mall-promotion-center|15
工单中心|mall-ticket-center|16
物流中心|mall-logistics-center|17    
评价中心|mall-evaluation-center|18

## 七、Mysql库分配
应用名|代码模块|库名
:--:|:--:|:--:
交易中心|mall-trade-center|sp_trade
会员中心|mall-member-center|sp_member
商品中心|mall-item-center|sp_item
库存中心|mall-inventory-center|sp_inventory
营销中心|mall-promotion-center|sp_promotion
工单中心|mall-ticket-center|sp_ticket
物流中心|mall-logistics-center|sp_logistics
评价中心|mall-evaluation-center|sp_evaluation
任务应用|mall-job-application|sp_job