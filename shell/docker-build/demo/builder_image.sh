#!/usr/bin/env bash

group=demo
app_name=demo
tag=1.0.0
hub=47.106.243.217:5000

if [ "x$2" != "x" ]; then
  group=$2
fi
if [ "x$3" != "x" ]; then
  app_name=$3
fi
if [ "x$4" != "x" ]; then
  tag=$4
fi

image_name="$hub/${group}/${app_name}:$tag"
echo image name:$image_name

function build_image(){
echo -e "\n exec build image ......"
WORKSPACE=/data/docker-build/${app_name}
echo WORKSPACE $WORKSPACE
cd ${WORKSPACE}
echo $image_name && \
docker build -t ${image_name} ${WORKSPACE}
}

function push_registry(){
echo -e "\n exec push images into registry ......"
docker login -u "root" -p "123456789" "$hub"
docker push ${image_name}
}

function clean(){
echo -e "\n exec clean ......"
docker rm -f ${app_name}
docker rmi -f ${image_name}
}

function rebuild(){
echo -e "\n rebuild ......"
clean
build_image
#push_registry
}

if [ "$1" == "build_image" ]; then
  echo "exec build_image"
  build_image
elif [ "$1" == "push_registry" ]; then
  echo "exec push_registry"
  push_registry
elif [ "$1" == "clean" ]; then
  echo "exec clean"
  clean
elif [ "$1" == "rebuild" ]; then
  echo "exec rebuild"
  rebuild
fi




