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

## 考虑添加

* 从教务处拉取选课列表
* 从特定服务器拉取选课列表缓存
* 可视化选课列表
* 排课功能

## requests

* Gson
* HttpClient

## API
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
|-|-|
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


## 格式及一些约定
### 项目目录
```
WHUT_Course
|
│  README.md
│
└─src
    ├─cache
    │  │  timetable.json
    │  │
    │  └─course
    │      ├─专业选课
    │      │      4210004110.json
    │      │      4220003110.json
    │      │      map_file.json
    │      │
    │      ├─个性课程选课
    │      │      5160003990.json
    │      │      5160006990.json
    │      │      map_file.json
    │      │
    │      ├─英语体育课选课
    │      │      4210112160.json
    │      │      4210114160.json
    │      │      map_file.json
    │      │
    │      └─补修课选课
    │              4120261140.json
    │              4210003110.json
    │              map_file.json
    │
    ├─client
    │      alipay.png
    │      AlipayLogin.java
    │      SelectedCourseList.java
    │      SelectTable.java
    │      SelectTress.java
    │      Timetable.java
    │
    ├─requests
    │      AddCourseClass.java
    │      Course.java
    │      CourseTypeList.java
    │      ParseMapFile.java
    │      ParseTimetable.java
    │      Requests.java
    │      SelectedCourse.java
    │      SSOResponse.java
    │      TimetableCourse.java
    │      User.java
    │
    └─start
            Main.java
            staticValue.java
```
### config
所有的路径设置默认是在src/start/staticValue.java中设置
### 选课缓存
对于选课列表的缓存，存放于src/cache下，命名为course。course目录下是各大类别的选课列表目录，对于某一类别的目录，应该提供此格式的map_file.json文件。
```json
{
	"大学物理B-大类必修已开课": "4050463130",
	"电路原理B-大类必修未开课": "4110019110", 
	"计算机基础与编程综合实验-大类必修已开课": "4120261140", 
	"离散结构-大类必修已开课": "4120045110", 
	"面向对象程序设计C-大类必修已开课": "4120048110", 
	"线性代数-大类必修已开课": "4050229110", 
	"体育3-体育必修未开课": "4210003110", 
	"高级语言程序设计A-通识必修未开课": "4120020110", 
	"计算机科学导论-通识必修未开课": "4120027110"
}
```
即一个课程名称和课程代码的映射  

对于map_file.json的同级目录，存放某一课程的所有可选课程信息，命名为课程代码.json。对于每个可选课程信息，格式为:
```json
{
	"addclass": "bxkxkAdd.do?xnxq=2017-2018-2&kcdm=4120048110&jxjhh=20164123&addid={suid_obj}&keyinfo=10A8DE488D1D20FCE140EB22ED30AE1F", 
	"classes": 
	[
		{
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
}
```
其中addclass为添加课程的url,addid参数需替换为对应的addid。  

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