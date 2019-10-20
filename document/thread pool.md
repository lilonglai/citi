RejectedExecutionHandler
DiscardPolicy
DiscardOldestPolicy
AbortPolicy 抛异常
CallerRunsPolicy   




BlockQueue
add 抛异常
offer 不等待
put 等待（无返回值）

take 等待（无返回值）
poll 等待
remove 抛异常


jmap
jstack


thread state
New 

Runnable
blocked
waiting
terminated
timed_waiting


线程池



mybatis


悲观锁
pessimistic

乐观锁
CAS
提交时检查

脏读
不可重复读
丢失更新

事务
隔离性 atomicity
一致性  consistentcy
隔离性 isolation
持久性 durability

Configuration
ComponentScan
EnableAutoConfiguration

XA

Basically Available
Soft state（软状态）
Eventually consistent（最终一致性）


补偿事务（TCC）

：针对每个操作，都要注册一个与其对应的确认和补偿（撤销）操作。它分为三个阶段：

Try 阶段主要是对业务系统做检测及资源预留
Confirm 阶段主要是对业务系统做确认提交，Try阶段执行成功并开始执行 Confirm阶段时，默认 Confirm阶段是不会出错的。即：只要Try成功，Confirm一定成功。
Cancel 阶段主要是在业务执行错误，需要回滚的状态下执行的业务取消，预留资源释放

应用程序补偿
本地消息表

AP


虚拟节点


java 8接口默认实现
::引用方法