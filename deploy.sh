#!/bin/bash
env=$1
echo "build ${env} project..."
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
mvn clean install -Dmaven.test.skip -P${env}
scpconfig${test}
scpcourier${test}
scpuser${test}
scpmaster${test}
scpvehicle${test}
scpnonauto${test}
scpcommunity${test}
scpbaotuvehicle${test}
scpbihuvehicle${test}
scplebaobavehicle${test}
scpstatistics${test}
scppayment${test}
