master node

#!/bin/bash
# install software
yum install -y wget vim net-tools epel-release

#close selinux
setenforce 0
sed -i 's/SELINUX=enforcing/SELINUX=disabled/' /etc/sysconfig/selinux
sed -i "s/SELINUX=enforcing/SELINUX=disabled/g" /etc/selinux/config

#close swap
swapoff -a
sed -i 's/.*swap.*/#&/' /etc/fstab

#close firewalld
systemctl stop firewalld
systemctl disable firewalld

cat <<EOF >  /etc/sysctl.d/k8s.conf
net.bridge.bridge-nf-call-ip6tables = 1
net.bridge.bridge-nf-call-iptables = 1
EOF
sysctl --system


#aliyun
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

yum install -y docker-ce-18.09.9-3.el7
systemctl restart docker

yum install -y kubeadm kubelet

cat > kubeadm.sh <<EOF
#!/bin/bash

## 使用如下脚本下载国内镜像，并修改tag为google的tag
set -e

KUBE_VERSION=v1.16.2
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

systemctl enable kubelet.service

systemctl enable kubelet.service



kubeadm init  --kubernetes-version=v1.16.2 --apiserver-advertise-address=192.168.11.150 --pod-network-cidr=10.244.0.0/16 --service-cidr=10.1.0.0/16

mkdir -p $HOME/.kube
cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
chown $(id -u):$(id -g) $HOME/.kube/config

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
KUBELET_KUBEADM_ARGS="--cgroup-driver=systemd --network-plugin=cni --pod-infra-container-image=k8s.gcr.io/pause:3.1"
EOF

systemctl restart kubelet

kubectl apply -f https://raw.githubusercontent.com/coreos/flannel/v0.9.1/Documentation/kube-flannel.yml





work node
# install software
yum install -y wget vim net-tools epel-release

#close selinux
setenforce 0
sed -i 's/SELINUX=enforcing/SELINUX=disabled/' /etc/sysconfig/selinux
sed -i "s/SELINUX=enforcing/SELINUX=disabled/g" /etc/selinux/config

#close swap
swapoff -a
sed -i 's/.*swap.*/#&/' /etc/fstab

#close firewalld
systemctl stop firewalld
systemctl disable firewalld

cat <<EOF >  /etc/sysctl.d/k8s.conf
net.bridge.bridge-nf-call-ip6tables = 1
net.bridge.bridge-nf-call-iptables = 1
EOF
sysctl --system