### vertx-diamond-config

[![Build Status](https://travis-ci.org/CharLemAznable/vertx-diamond-config.svg?branch=master)](https://travis-ci.org/CharLemAznable/vertx-diamond-config)
[![codecov](https://codecov.io/gh/CharLemAznable/vertx-diamond-config/branch/master/graph/badge.svg)](https://codecov.io/gh/CharLemAznable/vertx-diamond-config)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.charlemaznable/vertx-diamond-config/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.charlemaznable/vertx-diamond-config/)
[![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://opensource.org/licenses/mit-license.php)
![GitHub code size](https://img.shields.io/github/languages/code-size/CharLemAznable/vertx-diamond-config)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=CharLemAznable_vertx-diamond-config&metric=alert_status)](https://sonarcloud.io/dashboard?id=CharLemAznable_vertx-diamond-config)

[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=CharLemAznable_vertx-diamond-config&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=CharLemAznable_vertx-diamond-config)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=CharLemAznable_vertx-diamond-config&metric=bugs)](https://sonarcloud.io/dashboard?id=CharLemAznable_vertx-diamond-config)

[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=CharLemAznable_vertx-diamond-config&metric=security_rating)](https://sonarcloud.io/dashboard?id=CharLemAznable_vertx-diamond-config)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=CharLemAznable_vertx-diamond-config&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=CharLemAznable_vertx-diamond-config)

[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=CharLemAznable_vertx-diamond-config&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=CharLemAznable_vertx-diamond-config)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=CharLemAznable_vertx-diamond-config&metric=sqale_index)](https://sonarcloud.io/dashboard?id=CharLemAznable_vertx-diamond-config)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=CharLemAznable_vertx-diamond-config&metric=code_smells)](https://sonarcloud.io/dashboard?id=CharLemAznable_vertx-diamond-config)

[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=CharLemAznable_vertx-diamond-config&metric=ncloc)](https://sonarcloud.io/dashboard?id=CharLemAznable_vertx-diamond-config)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=CharLemAznable_vertx-diamond-config&metric=coverage)](https://sonarcloud.io/dashboard?id=CharLemAznable_vertx-diamond-config)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=CharLemAznable_vertx-diamond-config&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=CharLemAznable_vertx-diamond-config)

使用Diamond配置Vert.x.

##### Maven Dependency

```xml
<dependency>
  <groupId>com.github.charlemaznable</groupId>
  <artifactId>vertx-diamond-config</artifactId>
  <version>0.1.1</version>
</dependency>
```

##### Maven Dependency SNAPSHOT

```xml
<dependency>
  <groupId>com.github.charlemaznable</groupId>
  <artifactId>vertx-diamond-config</artifactId>
  <version>0.1.2-SNAPSHOT</version>
</dependency>
```

##### DiamondHazelcastClusterManager

使用Diamond配置```HazelcastClusterManager```.

添加Diamond配置```{group: "group, 默认为VertxClusterConfig", dataId: "dataId"}```, 内容为```cluster.xml```或```cluster.yaml```文件的内容.

配置VertxOptions添加```clusterManager=@com.github.charlemaznable.vertx.diamond.DiamondHazelcastClusterManager(group, dataId)```, 其中group不提供则使用默认```VertxClusterConfig```.

##### DiamondIgniteClusterManager

使用Diamond配置```IgniteClusterManager```.

添加Diamond配置```{group: "group, 默认为VertxClusterConfig", dataId: "dataId"}```, 内容为```ignite.xml```文件的内容.

配置VertxOptions添加```clusterManager=@com.github.charlemaznable.vertx.diamond.DiamondIgniteClusterManager(group, dataId)```, 其中group不提供则使用默认```VertxClusterConfig```.

##### DiamondZookeeperClusterManager

使用Diamond配置```ZookeeperClusterManager```.

添加Diamond配置```{group: "group, 默认为VertxClusterConfig", dataId: "dataId"}```, 内容为```zookeeper.json```文件的内容.

配置VertxOptions添加```clusterManager=@com.github.charlemaznable.vertx.diamond.DiamondZookeeperClusterManager(group, dataId)```, 其中group不提供则使用默认```VertxClusterConfig```.
