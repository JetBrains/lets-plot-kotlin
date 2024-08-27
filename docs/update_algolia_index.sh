#!/usr/bin/env bash

# Stop script execution if a command has an error
set -e

# Check if Docker is available
if ! command -v docker &> /dev/null
then
    printf "ERROR: docker could not be found.\n"
    exit 1
fi

algolia_app_name="7961PKYRXV"
algolia_index_name="lets-plot-kotlin"
config_json_product="kotlin"

builder_docker_image="registry.jetbrains.team/p/writerside/builder/writerside-builder:241.16003"
algolia_publisher_docker_image="registry.jetbrains.team/p/writerside/builder/algolia-publisher:2.0.32-3"
instance_id="lpk"

root_path=$PWD
writerside_artifacts_tmp_path="docs/writerside-lpk"
algolia_index_tmp_path="docs/algolia-index"

# Read version of the library from the v.list
config_json_version=""
while read line; do
  key="$( echo $line | (grep -oP 'name="(\w+)"' || echo "") )"
  if [ "$key" = 'name="version"' ]; then
    config_json_version="$( echo $line | grep -oP "\d\.\d\.\d" )"
    break
  fi
done < $root_path/Writerside/v.list
if [ "$config_json_version" = "" ]; then
  printf "ERROR: Cannot read library version.\n"
  exit 1
fi

# Read Aligola secret key from local.properties
aligola_key=""
while read line; do
  key="$( cut -d '=' -f 1 <<< $line)"
  value="$( cut -d '=' -f 2- <<< "$line" )"
  if [ "$key" = "aligola.key" ]; then
    aligola_key=$value
    break
  fi
done < $root_path/local.properties
if [ "$aligola_key" = "" ]; then
  printf "ERROR: The Aligola key cannot be empty.\n"
  exit 1
fi

# Clean run Docker (to build Writerside part)
docker run --rm -v $root_path:/opt/sources \
  $builder_docker_image \
  /bin/bash -c "
  export DISPLAY=:99 &&
  Xvfb :99 &
  /opt/builder/bin/idea.sh helpbuilderinspect \
  --source-dir /opt/sources \
  --product Writerside/${instance_id} \
  --runner other \
  --output-dir /opt/sources/${writerside_artifacts_tmp_path}; \
  unzip -O UTF-8 -qq '/opt/sources/${writerside_artifacts_tmp_path}/algolia-indexes*.zip' -d /opt/sources/${algolia_index_tmp_path}/; \
  rm -rf /opt/sources/${writerside_artifacts_tmp_path}
  "; \

# Run Docker (to update Aligola index)
docker run --rm -v $root_path:/opt/sources \
  $algolia_publisher_docker_image \
  /bin/bash -c "
  env algolia-key='$aligola_key' java -jar /opt/builder/help-publication-agent.jar \
  update-index \
  --application-name '${algolia_app_name}' \
  --index-name '${algolia_index_name}' \
  --product '${config_json_product}' \
  --version '${config_json_version}' \
  --index-directory /opt/sources/${algolia_index_tmp_path}/ \
  2>&1 | tee algolia-update-index-log.txt; \
  rm -rf /opt/sources/${algolia_index_tmp_path}
  "