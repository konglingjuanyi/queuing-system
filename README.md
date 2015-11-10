1.	下载安装Postgresql。
从官网http://www.postgresql.org/ 上下载最新稳定版，我下载的是Window 下的Version 9.4.5，大小为58.42M，执行exe安装即可。安装好以后，默认用户名是postgres，密码为安装过程中自己设定的。pgAdmin是自带的可视化工具。将#{安装目录}/bin添加到环境变量以后，可以在命令提示符下执行sql命令，常用命令见http://blog.chinaunix.net/uid-26642180-id-3485465.html。

2.	Myeclipse中配置jetty run
Run Configurations->Maven Build上右键->new:
1)	选择Name，随便填，例如jettyrun
2)	点击Browse Workspace，选择对应要运行的项目
3)	Goals填jetty:run

3.	myeclipse安装mybatis generator插件
下载http://pan.baidu.com/s/1i3lk4AT 离线插件，解压缩，将解压缩以后的plugins文件夹下的文件放到myeclipse安装目录下的plugins文件夹下。重启myeclipse，在generator.xml上右键，可以看到Generate Mybatis/ibatis artifacts字样，说明安装成功。

4.	Myeclipse安装jrebel插件
下载http://pan.baidu.com/s/1gd9BjDh 破解插件，解压缩，把jrebel.jar和jrebel.lic放到某个文件夹下，然后再myeclipse的vm启动参数中添加：
-noverify -javaagent:D:\Java\jrebel\jrebel.jar     (所放jrebel路径)
-Xbootclasspath/p:D:/Java/jrebel/rebelboot.jar   (设置与jrebel相同路径即可)
-Drebel.generate.show=true
-Drebel.spring_plugin=true
-Drebel.aspectj_plugin=true
-Drebel.cxf_plugin=true
-Drebel.logback_plugin=true
-Drebel.mybatis_plugin=true

