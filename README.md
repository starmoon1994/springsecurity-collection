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

增加在安全验证的情况下的单元测试 模拟用户的登录

### test-ss-13
在test-ss-12 的基础上进一步扩展

增加 图片验证码模块和手机验证码模块

    form登录
    图片验证码
    登录时 1需先访问/code/image接口  获取图片验证码 2 带验证码参数 进行登录
    手机验证码
    登录时 1需先访问/code/sms接口 获取手机验证码 2 带验证码参数 进行登录（并未发送真实验证码，只是打印在控制台，后续直接扩展调用sdk发送即可）
    
##### *实现原理*

**关于的验证码 模块**

通过ValidateCodeController暴露接口，根据参数type的不同 调用不同的生成器

通过validateCodeProcessorHolder获取生成器image sms  返回ValidateCodeProcessor的实现类

例如获取的是smsValidateCodeProcessor   调用其create方法 做3个操作 1生成验证码generate 2保存验证码save 3发送验证码send  （send方法需要实现类自己重写实现，sms和image是不同的）
（所有的ValidateCodeProcessor实现类都继承了 抽象类AbstractValidateCodeProcessor 提供create generate save validate等方法 其中send方法是抽象方法 继承类需要自行实现  ）

ValidateCodeProcessor 的send方法 sms是模拟向手机发送验证码 image是通过response写出一个验证码图片

校验阶段

    自定义ValidateCodeFilter  用于对校验验证码
    在 ValidateCodeFilter.doFilterInternal方法进行操作  
        获取对应的ValidateCodeProcessor（例如smsXXXX）
        调用对应处理器的validate方法，进行具体的校验 1从请求取出 code输入值 2从session取出 code保存值 3 验证非空 过期 匹配等情况

配置

    将获取code的接口配置可访问
    在filter中配置需要校验验证码的url
    配置验证码的长度 获取url
    将validateCodeFilter置入HttpSecurity的前置过滤器


