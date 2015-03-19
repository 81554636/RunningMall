# RunningMall
开发/调试/打包：
  在rMall工程上运行mvn clean package -Plocal
  
-P可以是local或者Product

使用jboss as maven plugin
clean时，从jboss undeploy war
package时，往jboss deploy war
