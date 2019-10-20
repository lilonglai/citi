k8s
https://www.cnblogs.com/sfnz/p/6566584.html
https://www.jianshu.com/p/872736e1edd8

F:\btm\vm

centos7.master
centos7.node1
centos7.node2

vi /etc/hosts
192.168.11.147 master.k8s master
192.168.11.148 centos7.node1 node1
192.168.11.149 centos7.node2 node2

防火墙
systemctl stop firewalld
systemctl disable firewalld
vi /etc/selinux/config
SELINUX=disabled


sudo mv /etc/yum.repos.d/CentOS-Base.repo /etc/yum.repos.d/CentOS-Base.repo_bak 
sudo curl -o /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo
sudo yum makecache
sudo yum -y update


ntp 时间
sudo systemctl start ntpd
sudo systemctl enable ntpd
sudo ntpdate ntp1.aliyun.com
sudo hwclock -w


master
yum install -y etcd kubernetes-master ntp flannel

vi /etc/etcd/etcd.conf
ETCD_DATA_DIR="/var/lib/etcd/default.etcd"
ETCD_LISTEN_CLIENT_URLS="http://0.0.0.0:2379"
ETCD_NAME="default"
ETCD_ADVERTISE_CLIENT_URLS="http://master:2379"

systemctl restart etcd
etcdctl cluster-health
etcdctl  member  list

/etc/kubernetes/apiserver
KUBE_API_ADDRESS="--insecure-bind-address=0.0.0.0"
KUBE_ETCD_SERVERS="--etcd-servers=http://master:2379"
KUBE_SERVICE_ADDRESSES="--service-cluster-ip-range=10.254.0.0/16"
KUBE_ADMISSION_CONTROL="--admission-control=AlwaysAdmit"
KUBE_API_ARGS=""

vi /etc/kubernetes/controller-manager
KUBE_CONTROLLER_MANAGER_ARGS=""

vi vi /etc/kubernetes/scheduler
KUBE_SCHEDULER_ARGS="--address=0.0.0.0"



node
yum install -y kubernetes-node ntp flannel docker
vi /etc/kubernetes/config
KUBE_LOGTOSTDERR="--logtostderr=true"
KUBE_LOG_LEVEL="--v=0"
KUBE_ALLOW_PRIV="--allow-privileged=false"
KUBE_MASTER="--master=http://master:8080"


vi /etc/kubernetes/kubelet
KUBELET_ADDRESS="--address=0.0.0.0"
KUBELET_HOSTNAME="--hostname-override=slave2"
KUBELET_API_SERVER="--api-servers=http://master:8080"
KUBELET_POD_INFRA_CONTAINER="--pod-infra-container-image=registry.access.redhat.com/rhel7/pod-infrastructure:latest"
KUBELET_ARGS=""


vi /etc/sysconfig/flanneld
FLANNEL_ETCD_ENDPOINTS="http://master:2379"
FLANNEL_ETCD_PREFIX="/coreos.com/network"

etcdctl set /atomic.io/network/config '{"Network": "172.16.0.0/16"}'
etcdctl get /atomic.io/network/config


for i in flanneld kube-proxy kubelet docker;do sudo systemctl restart $i;done


for i in flanneld kube-proxy kubelet docker;do systemctl status $i;done



master
sudo systemctl start etcd
sudo systemctl enable etcd
etcdctl member list

for i in  kube-apiserver kube-controller-manager kube-scheduler;do systemctl restart $i; sudo systemctl enable $i;done



for i in  kube-apiserver kube-controller-manager kube-scheduler;do systemctl restart $i;done


systemctl restart kube-apiserver
systemctl restart kube-controller-manager
systemctl restart kube-scheduler


node
for i in flanneld kube-proxy kubelet docker;do sudo systemctl restart $i;done


for i in flanneld kube-proxy kubelet docker;do sudo systemctl restart $i;sudo systemctl enable $i;systemctl status $i ;done


vi /etc/docker/daemon.json
{
  "registry-mirrors": ["https://6cr8af3y.mirror.aliyuncs.com"]
}

systemctl restart docker


vi nginx-rc.yaml
apiVersion: v1
kind: ReplicationController
metadata:
  name: nginx-controller
spec:
  replicas: 2
  selector:
    name: nginx
  template:
    metadata:
      labels:
        name: nginx
    spec:
      containers:
        - name: nginx
          image: nginx
          ports:
           - containerPort: 80


vi nginx-service.yaml
apiVersion: v1
kind: Service
metadata:
  name: nginx-service
spec:
  ports:
    - port: 8000
      targetPort: 80
      protocol: TCP
  type: NodePort
  selector:
    name: nginx


kubectl create -f nginx-rc.yaml
kubectl delete -f nginx-rc.yaml

kubectl create -f nginx-service.yaml

kubectl get pod
kubectl describe pod

kubectl describe service


kubectl get service


slave1   192.168.11.148   172.16.82.2
slave2   192.168.11.149   172.16.47.2

etcdctl set /atomic.io/network/config '{"Network": "172.16.0.0/16"}'
etcdctl get /atomic.io/network/config

etcdctl ls /atomic.io/network/subnets


kubectl exec -it nginx-controller-6jcqf /bin/bash



yum install -y etcd docker kubernetes flannel

systemctl enable etcd
systemctl restart etcd

systemctl enable flanneld
systemctl restart flanneld

systemctl enable kube-apiserver kube-controller-manager kube-scheduler
systemctl restart kube-apiserver kube-controller-manager kube-scheduler

yum install -y docker kubernetes flannel
systemctl enable kubelet kube-proxy
systemctl restart kubelet kube-proxy
systemctl restart docker


iptables
yum install iptables-services
systemctl status iptables
systemctl start iptables.service

iptables -I FORWARD -s 0.0.0.0/0 -d 0.0.0.0/0 -j ACCEPT
service iptables save

systemctl restart iptables.service


iptables -P INPUT ACCEPT
iptables -P FORWARD ACCEPT
iptables -F