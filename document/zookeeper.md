数据发布/订阅、负载均衡、命名服务、分布式协调/通知、集群管理、Master选举、分布式锁和分布式队列等。

（Recipe，如共享锁服务、master选举、分布式计数器等


        <dependency>
            <groupId>org.apache.zookeeper</groupId>
            <artifactId>zookeeper</artifactId>
            <version>3.4.11</version>
        </dependency>
        <dependency>
            <groupId>org.apache.curator</groupId>
            <artifactId>curator-recipes</artifactId>
            <version>4.0.1</version>
        </dependency>



        <dependency>
            <groupId>org.eclipse.jetty</groupId>
            <artifactId>jetty-server</artifactId>
        </dependency>

        <dependency>
            <groupId>org.eclipse.jetty</groupId>
            <artifactId>jetty-servlet</artifactId>
        </dependency>



				<groupId>com.baomidou</groupId>
                <artifactId>mybatis-plus-boot-starter</artifactId>


	<dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>

<dubbo:protocol name="http" id="http" port="${servlet.port:8080}" server="${servlet.container:tomcat}"/>


dubbo.registry.address
dubbo.config-center.address

org.apache.dubbo.config.ApplicationConfig
org.apache.dubbo.config.RegistryConfig
org.apache.dubbo.config.ProtocolConfig
org.apache.dubbo.config.ConfigCenterConfig

org.apache.curator.framework.api.CreateBuilderMain#creatingParentsIfNeeded

org.apache.curator.framework.CuratorFramework#checkExists
org.apache.curator.framework.CuratorFramework#create
org.apache.curator.framework.CuratorFramework#setData