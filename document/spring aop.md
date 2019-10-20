org.aopalliance.intercept.Invocation#getArguments

org.aopalliance.intercept.Joinpoint


org.aopalliance.aop.Advice
org.springframework.aop.AfterAdvice
org.springframework.aop.AfterReturningAdvice
org.springframework.aop.ThrowsAdvice
org.springframework.aop.BeforeAdvice

org.springframework.aop.Advisor

org.aopalliance.intercept.ConstructorInterceptor
org.aopalliance.intercept.ConstructorInvocation
org.aopalliance.intercept.ConstructorInvocation#getConstructor
org.aopalliance.intercept.MethodInterceptor
org.aopalliance.intercept.MethodInvocation
org.aopalliance.intercept.MethodInvocation#getMethod


org.springframework.aop.Pointcut
org.springframework.aop.ClassFilter
org.springframework.aop.MethodMatcher



java.lang.reflect.Proxy#isProxyClass
org.springframework.util.ClassUtils#isCglibProxy

org.springframework.aop.support.AopUtils
org.springframework.aop.TargetClassAware


org.aspectj.lang.annotation.Aspect
org.aspectj.lang.annotation.Before
org.aspectj.lang.annotation.After
org.aspectj.lang.annotation.AfterReturning
org.aspectj.lang.annotation.AfterThrowing
org.aspectj.lang.annotation.Around

org.aspectj.lang.ProceedingJoinPoint

execution(modifiers-pattern? return-type-pattern declaring-type-pattern? name-pattern(param-pattern) throws-pattern)

org.springframework.context.annotation.EnableAspectJAutoProxy

org.springframework.context.annotation.ImportBeanDefinitionRegistrar
org.springframework.context.annotation.ImportBeanDefinitionRegistrar#registerBeanDefinitions

org.springframework.beans.factory.support.BeanDefinitionRegistry

org.springframework.beans.factory.config.BeanDefinition


org.springframework.context.MessageSource
org.springframework.context.ApplicationEventPublisher


org.springframework.context.ApplicationEvent

org.springframework.context.support.AbstractApplicationContext#applicationEventMulticaster

org.springframework.context.ApplicationListener

org.springframework.cache.annotation.EnableCaching

org.springframework.context.ConfigurableApplicationContext

org.springframework.core.env.ConfigurableEnvironment

org.springframework.beans.factory.config.BeanFactoryPostProcessor

org.springframework.beans.factory.config.BeanFactoryPostProcessor#postProcessBeanFactory

org.springframework.beans.factory.config.BeanPostProcessor

org.springframework.beans.factory.config.BeanPostProcessor#postProcessBeforeInitialization
org.springframework.beans.factory.config.BeanPostProcessor#postProcessAfterInitialization
org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor

org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor#postProcessProperties

org.springframework.beans.BeanMetadataAttribute
org.springframework.beans.BeanMetadataElement

org.springframework.core.AttributeAccessor

org.springframework.util.Assert

org.springframework.util.ObjectUtils

org.springframework.beans.PropertyValue


org.springframework.web.context.request.RequestContextHolder#getRequestAttributes
org.springframework.web.context.request.ServletRequestAttributes


set names utf8mb4

SHOW VARIABLES LIKE 'lower_case_%'

Statement: SHOW VARIABLES LIKE 'sql_mode'


<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
    <version>2.1.6.RELEASE</version>
</dependency>


<dependency>
    <groupId>javax.validation</groupId>
    <artifactId>validation-api</artifactId>
    <version>2.0.1.Final</version>
</dependency>