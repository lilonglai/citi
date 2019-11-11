minum linux
ip a
k8s 16
https://www.cnblogs.com/xingyys/p/11594189.html

F:\btm\vm

centos7.master  etcd kubeadm kube-apiserver kube-scheduler kube-controller-manager kubelet flanneld docker
centos7.node1   kubeadm kubelet flanneld docker
centos7.node2   kubeadm kubelet flanneld docker

echo "192.168.11.150 master.k8s master" >> /etc/hosts
echo "1192.168.11.151 node1.k8s node1" >> /etc/hosts
echo "192.168.11.152 node2.k8s node2" >> /etc/hosts

安装网络
yum install -y wget vim net-tools epel-release

关闭selinux
setenforce 0
sed -i 's/SELINUX=permissive/SELINUX=disabled/' /etc/sysconfig/selinux
sed -i "s/SELINUX=enforcing/SELINUX=disabled/g" /etc/selinux/config

禁用交换区
swapoff -a
sed -i 's/.*swap.*/#&/' /etc/fstab

防火墙
systemctl stop firewalld
systemctl disable firewalld


cat <<EOF >  /etc/sysctl.d/k8s.conf
net.bridge.bridge-nf-call-ip6tables = 1
net.bridge.bridge-nf-call-iptables = 1
EOF
sysctl --system

下载阿里源
mv /etc/yum.repos.d/CentOS-Base.repo /etc/yum.repos.d/CentOS-Base.repo.backup
wget -O /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo
yum makecache fast


cat <<EOF > /etc/yum.repos.d/kubernetes.repo
[kubernetes]
name=Kubernetes
baseurl=https://mirrors.aliyun.com/kubernetes/yum/repos/kubernetes-el7-x86_64
enabled=1
gpgcheck=0
EOF

## 重建yum缓存
yum clean all
yum makecache fast
yum -y update

yum -y install yum-utils device-mapper-persistent-data lvm2
yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo


cat > /etc/docker/daemon.json <<EOF
{
  "registry-mirrors": ["https://xxx.mirror.aliyuncs.com"],
  "exec-opts": ["native.cgroupdriver=systemd"],
  "log-driver": "json-file",
  "log-opts": {
    "max-size": "100m"
  },
  "storage-driver": "overlay2",
  "storage-opts": [
    "overlay2.override_kernel_check=true"
  ]
}
EOF

systemctl restart docker

yum install -y kubeadm kubelet

cat > kubeadm.sh <<EOF
#!/bin/bash

## 使用如下脚本下载国内镜像，并修改tag为google的tag
set -e

KUBE_VERSION=v1.16.0
KUBE_PAUSE_VERSION=3.1
ETCD_VERSION=3.3.15-0
CORE_DNS_VERSION=1.6.2

GCR_URL=k8s.gcr.io
ALIYUN_URL=registry.cn-hangzhou.aliyuncs.com/google_containers

images=(kube-proxy:${KUBE_VERSION}
kube-scheduler:${KUBE_VERSION}
kube-controller-manager:${KUBE_VERSION}
kube-apiserver:${KUBE_VERSION}
pause:${KUBE_PAUSE_VERSION}
etcd:${ETCD_VERSION}
coredns:${CORE_DNS_VERSION})

for imageName in ${images[@]} ; do
  docker pull $ALIYUN_URL/$imageName
  docker tag  $ALIYUN_URL/$imageName $GCR_URL/$imageName
  docker rmi $ALIYUN_URL/$imageName
done
EOF

sh ./kubeadm.sh


kubeadm init \
 --apiserver-advertise-address 192.168.11.150 \
 --kubernetes-version=v1.16.2 \
 --pod-network-cidr=10.244.0.0/16


mkdir -p $HOME/.kube
sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
sudo chown $(id -u):$(id -g) $HOME/.kube/config

kubeadm join 192.168.11.150:6443 --token enj897.7nu2a6uwfnkwyn55 \
    --discovery-token-ca-cert-hash sha256:044849850320f8f8c1d65a46fa0652a209d274c9f338b1633f3c32fa596533cc

kubectl run -it curl --image=radial/busyboxplus:curl


wget https://raw.githubusercontent.com/coreos/flannel/master/Documentation/kube-flannel.yml


cat <<EOF flanneld.sh
#!/bin/bash

set -e

FLANNEL_VERSION=v0.11.0

# 在这里修改源
QUAY_URL=quay.io/coreos
QINIU_URL=quay-mirror.qiniu.com/coreos

images=(flannel:${FLANNEL_VERSION}-amd64
flannel:${FLANNEL_VERSION}-arm64
flannel:${FLANNEL_VERSION}-arm
flannel:${FLANNEL_VERSION}-ppc64le
flannel:${FLANNEL_VERSION}-s390x)

for imageName in ${images[@]} ; do
  docker pull $QINIU_URL/$imageName
  docker tag  $QINIU_URL/$imageName $QUAY_URL/$imageName
  docker rmi $QINIU_URL/$imageName
done
EOF

sh flanneld.sh
kubectl apply -f kube-flanneld.yaml

cat << EOF > /var/lib/kubelet/kubeadm-flags.env
KUBELET_KUBEADM_ARGS="--cgroup-driver=systemd --pod-infra-container-image=k8s.gcr.io/pause:3.1"
EOF

systemctl restart kubelet

cat <<EOF >/run/flannel/docker
DOCKER_OPT_BIP="--bip=10.244.2.1/24"
DOCKER_OPT_IPMASQ="--ip-masq=false"
DOCKER_OPT_MTU="--mtu=1450"
DOCKER_NETWORK_OPTIONS=" --bip=10.244.2.1/24 --ip-masq=false --mtu=1450"
EOF

cat <<EOF >/run/flannel/docker
DOCKER_OPT_BIP="--bip=10.244.2.1/24"
DOCKER_OPT_IPMASQ="--ip-masq=false"
DOCKER_OPT_MTU="--mtu=1450"
DOCKER_NETWORK_OPTIONS=" --bip=10.244.2.1/24 --ip-masq=false --mtu=1450"
EOF


yum install docker-ce kubelet kubeadm kubectl 