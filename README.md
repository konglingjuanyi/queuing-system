1.	���ذ�װPostgresql��
�ӹ���http://www.postgresql.org/ �����������ȶ��棬�����ص���Window �µ�Version 9.4.5����СΪ58.42M��ִ��exe��װ���ɡ���װ���Ժ�Ĭ���û�����postgres������Ϊ��װ�������Լ��趨�ġ�pgAdmin���Դ��Ŀ��ӻ����ߡ���#{��װĿ¼}/bin��ӵ����������Ժ󣬿�����������ʾ����ִ��sql������������http://blog.chinaunix.net/uid-26642180-id-3485465.html��

2.	Myeclipse������jetty run
Run Configurations->Maven Build���Ҽ�->new:
1)	ѡ��Name����������jettyrun
2)	���Browse Workspace��ѡ���ӦҪ���е���Ŀ
3)	Goals��jetty:run

3.	myeclipse��װmybatis generator���
����http://pan.baidu.com/s/1i3lk4AT ���߲������ѹ��������ѹ���Ժ��plugins�ļ����µ��ļ��ŵ�myeclipse��װĿ¼�µ�plugins�ļ����¡�����myeclipse����generator.xml���Ҽ������Կ���Generate Mybatis/ibatis artifacts������˵����װ�ɹ���

4.	Myeclipse��װjrebel���
����http://pan.baidu.com/s/1gd9BjDh �ƽ�������ѹ������jrebel.jar��jrebel.lic�ŵ�ĳ���ļ����£�Ȼ����myeclipse��vm������������ӣ�
-noverify -javaagent:D:\Java\jrebel\jrebel.jar     (����jrebel·��)
-Xbootclasspath/p:D:/Java/jrebel/rebelboot.jar   (������jrebel��ͬ·������)
-Drebel.generate.show=true
-Drebel.spring_plugin=true
-Drebel.aspectj_plugin=true
-Drebel.cxf_plugin=true
-Drebel.logback_plugin=true
-Drebel.mybatis_plugin=true

