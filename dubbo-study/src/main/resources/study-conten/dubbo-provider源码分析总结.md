**一.spring使用自定义标签装配Bean的实现步骤**

1.创建需要扩展的组件（dubbo定义了很多需要扩展的组件例如ServiceBean,ProtocolConfig等详见:dubbo-config-spring项目包）

2.定义XSD文件描述组件内容  （定义自定义标签的约束以及属性例如：dubbo-config-spring下面META-INF/dubbo.xsd）

3.创建一个java类，实现BeanDefinitionParser接口，用来解析XSD文件中的定义和组件定义（例如：dubbo-config-spring/schema/DubboBeanDefinitionParser.java）

4.创建Handlerc处理类，继承NamespaceHandlerSupport抽象类实现初始化方法,目的是将自定标签和解析类绑定并将解析的对象注入到spring的容器里面（例如：dubbo-config-spring/schema/DubboNamespaceHandler.java）

5.在META-INF下编写Spring.handlers和Spring.schemas文件（例如：resource/META-INF/spring.handlers和spring.schemas）




**二.DUBBO服务暴露源码解析**

1.通过spring的自定标签解析xml配置文件就会将xml配置的各种标签解析为对应的配置类。例如：<dubbo:service/>标签就会被解析为serviceBean对象

而ServiceBean对象通过一系列的变化最终回转换成dubbo的 服务暴露对象Export对象

转换的过程如下：
        -->  ServiceBean(初始化配置类 通过spring加载)   
        -->  URL(通过ServiceBean的一些参数通过new URL() 对象的方式生成 改对象用于dubbo各个层自建的交互传承dubbo各个协议交换的对象详见：ServiceConfig类下面的doExportUrlsFor1Protocol()方法)  
        -->  Invoker(proxyFactory.getInvoker方法得到接口实现类的代理对象详见：ServiceConfig类下面的doExportUrlsFor1Protocol()方法)
        -->  Exporter( protocol.export(wrapperInvoker) 通过协议对象生成能够提供服务的Export的对象  而exporter对象就是暴露接口接收到请求之后通过请求参数获取到对应的
                        expoter的对象通过exporter对象的.getInvoker方法获取持有接口实现类的代理对象Invoker 通过Invoker对象的invok方法调用对象执行对应的方法进而返回处理结果)
                        
                        




