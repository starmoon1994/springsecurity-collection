安全框架SpringSecurity的基本应用

### test-ss-11 

集成spring security 
自定义认证成功和认证失败的handler
自定义访问拒绝的handler（权限不足）
自定义退出登录成功的handler
MockUserList是模拟用户列表
authorizeRequests对url进行统一的权限要求配置


### test-ss-12

在test-ss-11 的基础上进一步扩展

增加了对session的管理 

    可使用redis或者mongodb进行session缓存
    对session失效或者过期的处理
    

增加RBAC模型的实体类  user role permission（用户-角色-权限）再加上两个关系实体，对应数据库的5张表（用户表，角色表，权限表，用户角色关系表，角色权限关系表）

增加对api的访问控制 可通过注解 可通过congig类配置 

