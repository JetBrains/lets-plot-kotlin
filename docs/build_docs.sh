#!/usr/bin/env bash

docker_image="registry.jetbrains.team/p/writerside/builder/writerside-builder:241.16003"
instance_id="lpk"

root_path=$PWD
build_path="docs/build"
writerside_artifacts_tmp_path="docs/writerside-lpk"

sys_user_id=$(id -u)
sys_group_id=$(id -g)

# Clean build dir, run Dokka, run Docker (to build Writerside part)
rm -rf $root_path/$build_path && \
./gradlew dokkaHtml && \
docker run --rm -v $root_path:/opt/sources \
  $docker_image \
  /bin/bash -c "
  export DISPLAY=:99 &&
  Xvfb :99 &
  /opt/builder/bin/idea.sh helpbuilderinspect \
  --source-dir /opt/sources \
  --product Writerside/${instance_id} \
  --runner other \
  --output-dir /opt/sources/${writerside_artifacts_tmp_path}; \
  unzip -O UTF-8 -qq '/opt/sources/${writerside_artifacts_tmp_path}/webHelpLPK*.zip' -d /opt/sources/${build_path}; \
  rm -rf /opt/sources/${writerside_artifacts_tmp_path}; \
  chown -R $sys_user_id:$sys_group_id /opt/sources/${build_path}
  "