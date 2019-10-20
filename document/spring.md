 <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-sleuth</artifactId>
   </dependency>
   <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-sleuth-zipkin</artifactId>
   </dependency>


javax.annotation.PostConstruct
javax.annotation.PreDestroy


org.springframework.beans.factory.InitializingBean
org.springframework.beans.factory.DisposableBean

org.springframework.core.env.Environment

org.springframework.beans.factory.FactoryBean
org.springframework.beans.factory.BeanFactory
org.springframework.beans.factory.BeanFactoryAware
org.springframework.beans.factory.BeanClassLoaderAware

org.springframework.beans.factory.annotation.Autowired
org.springframework.beans.factory.annotation.Qualifier
org.springframework.beans.factory.annotation.Value
org.springframework.beans.factory.annotation.Required

org.springframework.context.annotation.Bean

org.springframework.context.annotation.Primary

org.springframework.context.annotation.Scope

org.springframework.context.annotation.Condition

org.springframework.boot.context.properties.EnableConfigurationProperties

org.springframework.boot.context.properties.ConfigurationProperties

org.springframework.boot.context.properties.NestedConfigurationProperty

org.springframework.boot.autoconfigure.condition.ConditionalOnClass

org.springframework.boot.autoconfigure.condition.ConditionalOnBean

org.springframework.boot.autoconfigure.condition.ConditionalOnJava

org.springframework.boot.autoconfigure.condition.ConditionalOnExpression

org.springframework.boot.autoconfigure.condition.ConditionalOnProperty

org.springframework.context.annotation.Conditional

org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean

org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass

org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication

org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication

org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate

org.springframework.context.annotation.PropertySource

org.springframework.context.annotation.PropertySources

org.springframework.web.bind.annotation.RestController
org.springframework.boot.autoconfigure.AutoConfigureAfter

org.springframework.boot.autoconfigure.AutoConfigureBefore

org.springframework.boot.autoconfigure.AutoConfigureOrder

org.springframework.boot.autoconfigure.EnableAutoConfiguration

org.springframework.boot.autoconfigure.SpringBootApplication

org.springframework.boot.SpringBootConfiguration

org.springframework.context.annotation.ComponentScan

org.springframework.web.context.request.RequestContextHolder

org.springframework.web.bind.annotation.RestController

org.springframework.web.bind.annotation.RequestMapping
org.springframework.web.bind.annotation.GetMapping
org.springframework.web.bind.annotation.PostMapping
org.springframework.web.bind.annotation.PutMapping
org.springframework.web.bind.annotation.DeleteMapping




org.springframework.web.bind.annotation.PathVariable
org.springframework.web.bind.annotation.CookieValue
org.springframework.web.bind.annotation.RequestParam
org.springframework.web.bind.annotation.RequestHeader
org.springframework.web.bind.annotation.ResponseStatus



org.springframework.http.converter.HttpMessageConverter

spring.datasource
type
driverClassName
url
username
password


spring.dubbo
application.name
registry.address
protocol.name
protocol.port


mybatis
configLocation
mapperLocations


spring.data.elasticsearch
clusterName
clusterNodes


spring.data.mongodb
host
port
database
gridFsDatabase



spring.redis
host
port
url


spring.rabbitmq
host
port
virtualHost

rocketmq
nameServer


defaultMQProducer
rocketMQTemplate

redisTemplate
stringRedisTemplate


org.springframework.cache.annotation.EnableCaching
org.springframework.cache.CacheManager

spring.cache.type

<dependency>
		    <groupId>org.springframework.boot</groupId>
		    <artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>


        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-cache</artifactId>
        </dependency>


		<dependency>
		    <groupId>org.springframework.boot</groupId>
		    <artifactId>spring-boot-starter-data-redis</artifactId>
		</dependency>


		<dependency>
		    <groupId>org.springframework.boot</groupId>
		    <artifactId>spring-boot-starter-mail</artifactId>
		</dependency>


缓存
日志
权限

FilterRegistrationBean 


org.springframework.security.config.annotation.SecurityConfigurer

60.Spring-Security-Logout/src/main/java/cc/mrbird/security/browser/UserDetailService

org.springframework.security.crypto.password.PasswordEncoder

org.springframework.security.core.userdetails.UserDetails

org.springframework.security.core.userdetails.User

org.springframework.security.authentication.AuthenticationProvider

org.springframework.security.core.Authentication

org.springframework.security.authentication.AbstractAuthenticationToken


org.springframework.security.authentication.AuthenticationManager
\
org.springframework.security.authentication.AuthenticationManager#authenticate

org.springframework.security.authentication.AuthenticationDetailsSource

org.springframework.security.web.authentication.AuthenticationFailureHandler

60.Spring-Security-Logout/src/main/java/cc/mrbird/validate/code/ValidateCodeFilter.java

60.Spring-Security-Logout/src/main/java/cc/mrbird/handler/MyAuthenticationSucessHandler.java:21

org.springframework.security.web.RedirectStrategy

org.springframework.security.web.authentication.logout.LogoutSuccessHandler

org.springframework.security.web.session.SessionInformationExpiredStrategy

org.springframework.security.web.access.AccessDeniedHandler

org.springframework.security.access.prepost.PreAuthorize
"hasAuthority('admin')

org.apache.shiro.authz.annotation.RequiresRoles
org.apache.shiro.authz.annotation.RequiresPermissions


org.springframework.security.web.authentication.WebAuthenticationDetailsSource