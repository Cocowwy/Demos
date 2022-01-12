# springboot-demo
基于springboot的相关框架，中间件的Demo演示  
:zap:**sharding-sphere**:zap:  
**springdatajdbc**分支使用**spring-data-jdbc**作为持久层，**mybatisplus**分支使用**mybatis-plus**作为持久层，主要**demo**使用**master分支（即springdatajdbc）**
 - 目前已经整合，完成分表，按照年份+月份的方式进行
 - springdatajdbc在使用方法名betwen来直接进行调用查询操作时，和sharding不兼容好像，改成@Query形式可以，有空的时候debug一下原因
