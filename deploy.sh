#!/bin/bash
env=$1
build=$2
IP="116.62.111.191"
ENV="online"
if [ "${env}"x = "test" ]; then
	IP="101.37.30.26"
	ENV="test"
fi
if [ "${build}"x = "true"x ]; then
	cd /f/work/workspace/eclipse/rapid
	echo "rapid git sync start..."
	git add ./*
	git commit -m "ahab"
	git pull
	git push origin master
	echo "rapid sync finish, start build..."
	mvn clean install -Dmaven.test.skip

	cd /f/work/workspace/eclipse/btkj
	echo "btkj git sync start..."
	git add ./*
	git commit -m "ahab"
	git pull
	git push origin sj1.0
	echo "btkj sync finish, start build..."
	mvn clean install -Dmaven.test.skip -P${ENV}
fi

echo "start upload to server..."
scp F:/work/workspace/eclipse/btkj/btkj-config/btkj-config-deploy/target/config-service.tar.gz root@${IP}:/root/dubbo
scp F:/work/workspace/eclipse/btkj/btkj-courier/btkj-courier-deploy/target/courier-service.tar.gz root@${IP}:/root/dubbo
scp F:/work/workspace/eclipse/btkj/btkj-user/btkj-user-deploy/target/user-service.tar.gz root@${IP}:/root/dubbo
scp F:/work/workspace/eclipse/btkj/btkj-master/btkj-master-deploy/target/master-service.tar.gz root@${IP}:/root/dubbo
scp F:/work/workspace/eclipse/btkj/btkj-vehicle/btkj-vehicle-deploy/target/vehicle-service.tar.gz root@${IP}:/root/dubbo
scp F:/work/workspace/eclipse/btkj/btkj-nonauto/btkj-nonauto-deploy/target/nonauto-service.tar.gz root@${IP}:/root/dubbo
scp F:/work/workspace/eclipse/btkj/btkj-community/btkj-community-deploy/target/community-service.tar.gz root@${IP}:/root/dubbo
scp F:/work/workspace/eclipse/btkj/btkj-baotu-vehicle/btkj-baotu-vehicle-deploy/target/baotu-vehicle-service.tar.gz root@${IP}:/root/dubbo
scp F:/work/workspace/eclipse/btkj/btkj-bihu-vehicle/btkj-bihu-vehicle-deploy/target/bihu-vehicle-service.tar.gz root@${IP}:/root/dubbo
scp F:/work/workspace/eclipse/btkj/btkj-lebaoba-vehicle/btkj-lebaoba-vehicle-deploy/target/lebaoba-vehicle-service.tar.gz root@${IP}:/root/dubbo
scp F:/work/workspace/eclipse/btkj/btkj-statistics/btkj-statistics-deploy/target/statistics-service.tar.gz root@${IP}:/root/dubbo
scp F:/work/workspace/eclipse/btkj/btkj-payment/btkj-payment-deploy/target/payment-service.tar.gz root@${IP}:/root/dubbo