# WHUT_Course
## 说明
此工具为了解决在武汉理工大学选课期间教务处爆炸导致选课体验极差的问题而解决。具有GUI界面，操作友好，支持抢课及挂机捡漏功能。
## 原理
利用Java httpClient库构造post和get请求，达到选课功能。  为了解决教务处爆炸无法拉取选课列表的问题，选课列表本地解析，后期会加入拉取选课列表功能。选课列表的解析主要依赖于Gson对json字符串的解析。
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

## 格式及一些约定
对于选课列表的缓存，存放于src下，命名为course。course目录下是各大类别的选课列表目录，对于某一类别的目录，应该提供此格式的map_file.json文件。
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
