**Spring容器Bean的生命周期**

通过创建一个Bean并实现Spring常用的生命周期接口通过打印输出日志,确定SpringBean的加载过程

` 

public class TestSpringBean extends TestSpringParentBean implements InitializingBean, DisposableBean,
         ApplicationContextAware, BeanNameAware, ApplicationListener<ContextRefreshedEvent>, BeanPostProcessor,
         BeanFactoryPostProcessor, BeanFactoryAware {
 
     public  TestSpringBean(){
         System.out.println("TestSpringBean的初始化方法被调用");
     }
 
 
     public String sayHello(){
         System.out.println("TestSpringBean------sayHello----的方法被调用");
 
         return "Hello";
     }
 
 
     @Override
     public void afterPropertiesSet() throws Exception {
         System.out.println("TestSpringBean--------InitializingBean接口------afterPropertiesSet实现方法----的方法被调用");
     }
 
     @Override
     public void destroy() throws Exception {
         System.out.println("TestSpringBean--------DisposableBean接口------destroy实现方法----的方法被调用");
     }
 
     @Override
     public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
         applicationContext.publishEvent(this);
         System.out.println("TestSpringBean--------ApplicationContextAware接口------setApplicationContext实现方法----的方法被调用");
     }
 
     @Override
     public void setBeanName(String s) {
         System.out.println("TestSpringBean--------BeanNameAwaree接口------setBeanName实现方法----的方法被调用");
     }
 
     @Override
     public void onApplicationEvent(ContextRefreshedEvent event) {
         System.out.println("TestSpringBean--------ApplicationListener接口------onApplicationEvent实现方法----的方法被调用");
     }
 
     @Override
     public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
         System.out.println("TestSpringBean--------BeanPostProcessor接口------postProcessBeforeInitialization实现方法----的方法被调用");
         return null;
     }
 
     @Override
     public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
         System.out.println("TestSpringBean--------BeanPostProcessor接口------postProcessAfterInitialization实现方法----的方法被调用");
         return null;
     }
 
     @Override
     public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
         System.out.println("TestSpringBean--------BeanFactoryPostProcessor接口------postProcessBeanFactory实现方法----的方法被调用");
 
     }
 
     @Override
     public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
         System.out.println("TestSpringBean--------BeanFactoryAware接口------setBeanFactory实现方法----的方法被调用");
     }
 
     public void initMethod(){
         System.out.println("TestSpringBean--------initMethod----的方法被调用");
     }
 
     public void destoryMethod(){
         System.out.println("TestSpringBean--------destoryMethod----的方法被调用");
     }
 }
`
结果打印日志为：

`TestSpringParentBean的初始化方法结束调用

 TestSpringBean的初始化方法被调用
 
 TestSpringBean--------BeanNameAwaree接口------setBeanName实现方法----的方法被调用
 
 TestSpringBean--------BeanFactoryAware接口------setBeanFactory实现方法----的方法被调用
 
 TestSpringBean--------ApplicationContextAware接口------setApplicationContext实现方法----的方法被调用
 
 TestSpringBean--------InitializingBean接口------afterPropertiesSet实现方法----的方法被调用
 
 TestSpringBean--------initMethod----的方法被调用
 
 TestSpringBean--------BeanFactoryPostProcessor接口------postProcessBeanFactory实现方法----的方法被调用
 
 TestSpringBean--------BeanPostProcessor接口------postProcessBeforeInitialization实现方法----的方法被调用
 
 TestSpringBean--------BeanPostProcessor接口------postProcessBeforeInitialization实现方法----的方法被调用
 
 TestSpringBean--------BeanPostProcessor接口------postProcessBeforeInitialization实现方法----的方法被调用
 
 TestSpringBean--------BeanPostProcessor接口------postProcessBeforeInitialization实现方法----的方法被调用
 
 TestSpringBean--------DisposableBean接口------destroy实现方法----的方法被调用
 
 TestSpringBean--------destoryMethod----的方法被调用`
