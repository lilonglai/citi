org.springframework.context.annotation.PropertySource


org.apache.dubbo.config.ReferenceConfig
org.apache.dubbo.config.ApplicationConfig
org.apache.dubbo.config.RegistryConfig
org.apache.dubbo.config.ServiceConfig
org.apache.dubbo.config.ConfigCenterConfig

org.apache.dubbo.config.ConfigCenterConfig#setAddress


        <dependency>
            <groupId>org.apache.dubbo</groupId>
            <artifactId>dubbo-registry-zookeeper</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.dubbo</groupId>
            <artifactId>dubbo-configcenter-zookeeper</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.dubbo</groupId>
            <artifactId>dubbo-rpc-dubbo</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.dubbo</groupId>
            <artifactId>dubbo-remoting-netty4</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.dubbo</groupId>
            <artifactId>dubbo-serialization-hessian2</artifactId>
        </dependency>


org.springframework.context.support.ClassPathXmlApplicationContext
org.springframework.context.annotation.AnnotationConfigApplicationContext


config
/dubbo/config/service/configurators


org.jboss.netty.bootstrap.ClientBootstrap
org.jboss.netty.bootstrap.ServerBootstrap