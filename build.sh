#!/bin/sh
##############################################################################
# File:			build.sh
# Descption:		a template of qunar build script
# Version:		1.0
# Modified times:	0
# Date:			2011/7/22
# Author:		zuohua.liang
##############################################################################
#使用方法:		./build.sh -T=ant|mvn -P=beta|product|dev -AB=a|b
#参数说明:		-T:编译方法
#			-P:编译策略	
#			-AB:服务器环境
#参数默认值：		
#			-T:mvn
#			-P:beta	
#			-AB:a
#例:			使用maven编译beta版发布到A类服务器
#			./build.sh -T=mvn -P=beta -AB=a
##############################################################################


##############################################################################
#常量定义
##############################################################################


##############################################################################
#变量及默认值定义
##############################################################################
antbin=`which ant`
mvnbin=`which mvn`
#type="mvn"		#编译方法,从-T参数获取
#profile="beta"		#编译策略,从-P参数获取
#ab="a"			#服务器环境,从-AB参数获取


##############################################################################
#函数定义
##############################################################################
usage() 
{
        echo "
		使用方法:		./build.sh -T=ant|mvn -P=beta|product|dev -AB=a|b
		参数说明:		
					-T:编译方法
					-P:编译策略	
					-AB:服务器环境
		参数默认值：		
					-T:mvn
					-P:beta	
					-AB:a
		例:			使用maven编译beta版发布到A类服务器
					./build.sh -T=mvn -P=beta -AB=a
        "
}


##############################################################################
#参数获取及判断
##############################################################################
for ((i=1;i<=$#;i++))
do
        eval str=\${$i}

        case $str in
                -T*)
                        type=`echo $str |cut -d'='  -f 2`
                        ;;
                -P*)
                        profile=`echo $str |cut -d'='  -f 2`
                        ;;
                -AB*)
                        ab=`echo $str |cut -d'='  -f 2`
                        ;;
                -D=nexus)
                        deploy="nexus"
                        ;;
                *)
                        usage
                        exit 1
                        ;;
        esac
done

##############################################################################
#以下为实际执行的程序，请作相应替换
##############################################################################
echo type=$type, profile=$profile, ab=$ab

#调用方法 sh build.sh -T=mvn -P=product 
#无参数时什么都不做，就是下载代码
#-T=mvn，才需-P参数，如果有AB不同环境参数才需-AB
#如果mvn不需要单元测试，可以在mvn编译里加编译参数
#如果有其他编译需求，直接改下面的内容
if [  -n "$type" ];  then 
	if [ "ant" = $type ] ; then
		$antbin
	elif [ "mvn" = $type ] ; then
		if [ "nexus" = $deploy ] ; then

		 $mvnbin -P$profile$ab clean package -Dmaven.test.skip=true deploy

		 $mvnbin -P$profile$ab clean deploy -Dmaven.test.skip=true

		else
		 $mvnbin -P$profile$ab clean package -Dmaven.test.skip=true
		fi
	fi
fi






  