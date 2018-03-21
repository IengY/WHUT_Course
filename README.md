# WHUT_Course
## 说明
此工具为了解决在武汉理工大学选课期间教务处爆炸导致选课体验极差的问题而解决。具有GUI界面，操作友好，支持抢课及挂机捡漏功能。
## 原理
利用Java httpClient库构造post和get请求，达到选课功能。  为了解决教务处爆炸无法拉取选课列表的问题，选课列表本地解析，后期会加入拉取选课列表功能。选课列表的解析主要依赖于Gson对json字符串的解析。
## 如何使用
clone code & run Main.java
## feature

* 利用本地缓存解析选课列表
* 利用选课列表自动抢课及捡漏

## 正在实现的功能

* 排课及同时间课程替换功能

## 考虑添加

* 从教务处拉取选课列表
* 从特定服务器拉取选课列表缓存
* 可视化选课列表
* 排课功能

## requests

* Gson
* HttpClient
* SQLite-JDBC

## API
API部分依赖于[xkxx](https://github.com/Maymomo/xkxt)项目,将该项目部署到服务器上提供API接口，相应的配置在src/start/staticValue.java中

### 选课列表
URL:http：//119.23.234.110:80/get_classes
method:POST  
```json
data: {
  "userName":,
  "password":,
  "class_type": "",
}
```

|参数|含义|类型|
|-|-|-|
|userName|学号|String|
|password|教务处密码|String|
|class_type|选课类型|String|

|class_type|mean|
|-|-|
|zykxk|专业课选课信息|
|gxkxk|公选课选课信息|
|cxkxk|重修课选课信息|
|gxxk|个性课选课信息|
|bxkxk|补修课选课信息|
|tqxk|提前选课|
|yytykxk|英语体育课选课信息|

此API返回一个JSON数组串，其内容为对应class_type的所有课程,addAction为添加该课程的URL
```json
[
	{
		"classes": 
		[
			{
			"addAction":"",
			"add_id": "5E23835DA9654EA8E053FD02A8C05B48", 
			"课程名称": "面向对象程序设计C", 
			"上课老师": "王云华",
			"上课时间": "周三第5-6节{第01-14周}", 
			"上课地点": "新1-401", 
			"容量": "130", 
			"选上": "118", 
			"本轮已选": "0", 
			"选课方式": " 跨专业听课", 
			"学分": "2.5", 
			"备注": "NULL", 
			"双语等级": "0 "
			}, 
			{
			"addAction":"",
			"add_id": "5E23835DA9654EA8E053FD02A8C05B48", 
			"课程名称": "面向对象程序设计C", 
			"上课老师": "王云华",
			"上课时间": "周三第5-6节{第01-14周}", 
			"上课地点": "新1-401", 
			"容量": "130", 
			"选上": "118", 
			"本轮已选": "0", 
			"选课方式": " 跨专业听课", 
			"学分": "2.5", 
			"备注": "NULL", 
			"双语等级": "0 "
			}
		]
	},
	{
		"classes": 
		[
			{
			"addAction":"",
			"add_id": "5E23835DA9654EA8E053FD02A8C05B48", 
			"课程名称": "概论", 
			"上课老师": "王云华",
			"上课时间": "周三第5-6节{第01-14周}", 
			"上课地点": "新1-401", 
			"容量": "130", 
			"选上": "118", 
			"本轮已选": "0", 
			"选课方式": " 跨专业听课", 
			"学分": "2.5", 
			"备注": "NULL", 
			"双语等级": "0 "
			}, 
			{
			"addAction":"",
			"add_id": "5E23835DA9654EA8E053FD02A8C05B48", 
			"课程名称": "概论", 
			"上课老师": "王云华",
			"上课时间": "周三第5-6节{第01-14周}", 
			"上课地点": "新1-401", 
			"容量": "130", 
			"选上": "118", 
			"本轮已选": "0", 
			"选课方式": " 跨专业听课", 
			"学分": "2.5", 
			"备注": "NULL", 
			"双语等级": "0 "
			}
		]
	}
]
```

## 格式及一些约定
### 项目目录
```
WHUT_Course
|
│ README.md
│
└─src
    │
    ├─client
    │      alipay.png
    │      AlipayLogin.java
    │      SelectedCourseList.java
    │      SelectTable.java
    │      SelectTress.java
    │      Timetable.java
    │
    ├─database
    │      Course.db
    │
    ├─requests
    │      Course.java
    │      CourseList.java
    │      ParseCourseJson.java
    │      ParseTimetable.java
    │      Requests.java
    │      SelectedCourse.java
    │      ServerRequests.java
    │      SSOResponse.java
    │      TimetableCourse.java
    │      User.java
    │
    ├─sqliteDatabase
    │      CourseSQLiteJDBC.java
    │
    └─start
            Main.java
            staticValue.java

```
### config
所有的路径设置默认是在src/start/staticValue.java中设置

### database
数据库采用SQLite，数据库文件为Course.db
|名称|含义|
|-|-|
|Course|选课列表数据库|
|Timetable|个人课表数据库|
#### Course
|表名称|含义|
|-|-|
|bxkxk|必修课选课信息|
|cxkxk|重修课选课信息|
|gxkxk|公选课选课信息|
|gxxk|个性课选课信息|
|tqxk|提前选课选课信息|
|yytykxk|英语体育课选课信息|
|zykxk|专业课选课信息|

对于这些表均含有相同的表结构  
```sql
DROP TABLE IF EXISTS "main"."zykxk";
CREATE TABLE "zykxk" (
"课程名称"  TEXT NOT NULL,
"上课老师"  TEXT NOT NULL,
"上课时间"  TEXT NOT NULL,
"上课地点"  TEXT NOT NULL,
"容量"  INTEGER,
"选上"  INTEGER,
"本轮已选"  INTEGER,
"选课方式"  TEXT,
"备注"  TEXT,
"学分"  INTEGER NOT NULL,
"双语等级"  TEXT,
"add_id"  TEXT NOT NULL,
"addAction"  TEXT NOT NULL,
PRIMARY KEY ("add_id" ASC)
);
```

## 废弃待重构的内容
### 课表缓存
对于每一门课程 采用以下格式
```json
{
	"name":"课程名称",
	"id":{
		"row":"第几行",
		"col":"第几列",
		},
	"start_time":"开始时间",
	"end_time":"结束时间",
	"location":"上课地点",
	"teacher":"老师",
	"isDivide":"0不分单双周，1单周，2双周",
}
```
对于整个json字符串，应该以下格式,命名为timetable.json保存于src/cache目录下
```json
[
		{
			"name":"课程1名称",
			"id":{
					"row":"第几行",
					"col":"第几列",
				},
			"start_time":"开始时间",
			"end_time":"结束时间",
			"location":"上课地点",
			"teacher":"老师",
			"isDivide":"0不分单双周，1单周，2双周",
		},
		{
			"name":"课程1名称",
			"id":{
					"row":"第几行",
					"col":"第几列",
				},
			"start_time":"开始时间",
			"end_time":"结束时间",
			"location":"上课地点",
			"teacher":"老师",
			"isDivide":"0不分单双周，1单周，2双周",
		}
]
```
