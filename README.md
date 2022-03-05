## paper-system

### 1.简要介绍

paper-system是一个论文检索系统，基于Spring Boot+MyBatis+Bootstrap+Thymeleaf。本系统实现了用户权限控制：分为**普通用户**和**系统管理员**两部分；普通用户具有**综合检索**，**复合检索**，**网络检索**，**上传论文**，**个人信息管理**的功能；系统管理员具有**论文信息管理**，**论文检索**，**上传论文**，**普通用户管理**，**系统管理员管理**的功能。本系统能有效减少用户查找论文的时间和开销。

<br>



### 2.总体模块设计

<div align=center><img src="https://gitee.com/mountisome/images/raw/master/img/科研训练.jpg" width="70%"></div>

<br>



### 3.技术选型

- 核心框架：Spring Boot
- 持久层框架：MyBatis
- 页面：Thymeleaf
- CSS样式：Bootstrap
- 数据库连接池：Druid
- 数据库：MySQL

<br>



### 4.功能介绍

普通用户：

- 综合检索：以标题或作者或摘要或关键字进行模糊检索论文
- 复合检索：以分类号、论文名称、作者、关键字和论文类别进行模糊检索论文
- 网络检索：登录中国知网进行论文检索
- 上传论文：上传本地论文
- 个人信息管理：可以修改本用户的用户名、密码、电话和邮箱

系统管理员：

- 论文信息管理：对论文进行修改、删除和查看
- 论文检索：以标题或作者或摘要或关键字进行模糊检索论文
- 上传论文：上传本地论文
- 普通用户管理：管理所有普通用户的信息
- 系统管理员管理：管理所有系统管理员的信息

<br>



### 5.页面展示

#### 登录界面：

<div align=center><img src="https://gitee.com/mountisome/images/raw/master/img/Snipaste_2022-02-17_12-07-30.jpg"></div>

<br>

#### 注册界面：

<div align=center><img src="https://gitee.com/mountisome/images/raw/master/img/Snipaste_2022-02-17_12-07-44.jpg"></div>

<br>

#### 综合检索界面：

<div align=center><img src="https://gitee.com/mountisome/images/raw/master/img/Snipaste_2022-02-17_12-08-44.jpg"></div>

<br>

#### 复合检索界面：

<div align=center><img src="https://gitee.com/mountisome/images/raw/master/img/Snipaste_2022-02-17_12-08-54.jpg"></div>

<br>

#### 上传论文界面：

<div align=center><img src="https://gitee.com/mountisome/images/raw/master/img/Snipaste_2022-02-17_12-08-59.jpg"></div>

<br>

#### 个人信息管理界面：

<div align=center><img src="https://gitee.com/mountisome/images/raw/master/img/Snipaste_2022-02-17_12-09-05.jpg"></div>

<br>

#### 系统管理员的论文检索界面：

<div align=center><img src="https://gitee.com/mountisome/images/raw/master/img/Snipaste_2022-02-17_12-09-20.jpg"></div>

<br>

#### 普通用户管理界面：

<div align=center><img src="https://gitee.com/mountisome/images/raw/master/img/Snipaste_2022-02-17_12-09-26.jpg"></div>

<br>

#### 系统管理员管理界面：

<div align=center><img src="https://gitee.com/mountisome/images/raw/master/img/Snipaste_2022-02-17_12-09-31.jpg"></div>

<br>



### 6.使用教程

- jdk推荐1.8
- 根据sql文件夹中的`paper_system.sql`文件建立好数据库
- 根据`pom.xml`安装需要的包
- 部署Spring Boot启动配置
- 启动项目 
- 在浏览器中输入`http://localhost:8080/`跳转到登录界面
- 用户名为`name1`，密码为`123`登录论文检索系统
- **注意：我的项目放在`E:\projects\论文检索系统`文件夹下，上传的论文也会存放在该文件夹下的upload文件夹，如果想要更改存放路径，需要修改`PaperInfoServlet`的`uploadPaper`方法**
- 网络检索功能使用了爬虫，需要先在python安装的路径下添加一个`chromedriver.exe`文件，详见[链接](https://blog.csdn.net/weixin_44318830/article/details/103339273)
