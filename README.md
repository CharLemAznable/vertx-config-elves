### vertx-config-elves

[![Build](https://github.com/CharLemAznable/vertx-config-elves/actions/workflows/build.yml/badge.svg)](https://github.com/CharLemAznable/vertx-config-elves/actions/workflows/build.yml)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.charlemaznable/vertx-config-elves/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.charlemaznable/vertx-config-elves/)
[![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://opensource.org/licenses/mit-license.php)
![GitHub code size](https://img.shields.io/github/languages/code-size/CharLemAznable/vertx-config-elves)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=CharLemAznable_vertx-config-elves&metric=alert_status)](https://sonarcloud.io/dashboard?id=CharLemAznable_vertx-config-elves)

[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=CharLemAznable_vertx-config-elves&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=CharLemAznable_vertx-config-elves)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=CharLemAznable_vertx-config-elves&metric=bugs)](https://sonarcloud.io/dashboard?id=CharLemAznable_vertx-config-elves)

[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=CharLemAznable_vertx-config-elves&metric=security_rating)](https://sonarcloud.io/dashboard?id=CharLemAznable_vertx-config-elves)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=CharLemAznable_vertx-config-elves&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=CharLemAznable_vertx-config-elves)

[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=CharLemAznable_vertx-config-elves&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=CharLemAznable_vertx-config-elves)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=CharLemAznable_vertx-config-elves&metric=sqale_index)](https://sonarcloud.io/dashboard?id=CharLemAznable_vertx-config-elves)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=CharLemAznable_vertx-config-elves&metric=code_smells)](https://sonarcloud.io/dashboard?id=CharLemAznable_vertx-config-elves)

[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=CharLemAznable_vertx-config-elves&metric=ncloc)](https://sonarcloud.io/dashboard?id=CharLemAznable_vertx-config-elves)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=CharLemAznable_vertx-config-elves&metric=coverage)](https://sonarcloud.io/dashboard?id=CharLemAznable_vertx-config-elves)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=CharLemAznable_vertx-config-elves&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=CharLemAznable_vertx-config-elves)

使用Apollo/Diamond配置Vert.x.

##### Maven Dependency

```xml
<dependency>
  <groupId>com.github.charlemaznable</groupId>
  <artifactId>vertx-config-elves</artifactId>
  <version>0.1.7</version>
</dependency>
```

##### Maven Dependency SNAPSHOT

```xml
<dependency>
  <groupId>com.github.charlemaznable</groupId>
  <artifactId>vertx-config-elves</artifactId>
  <version>0.1.8-SNAPSHOT</version>
</dependency>
```

##### DiamondHazelcastClusterManager

使用Diamond配置```HazelcastClusterManager```.

添加Diamond配置```{group: "group, 默认为VertxClusterConfig", dataId: "dataId"}```, 内容为```cluster.xml```或```cluster.yaml```文件的内容.

配置VertxOptions添加```clusterManager=@com.github.charlemaznable.vertx.config.DiamondHazelcastClusterManager(group, dataId)```, 其中group不提供则使用默认```VertxClusterConfig```.


##### DiamondIgniteClusterManager

使用Diamond配置```IgniteClusterManager```.

添加Diamond配置```{group: "group, 默认为VertxClusterConfig", dataId: "dataId"}```, 内容为```ignite.json```文件的内容.

配置VertxOptions添加```clusterManager=@com.github.charlemaznable.vertx.config.DiamondIgniteClusterManager(group, dataId)```, 其中group不提供则使用默认```VertxClusterConfig```.


##### DiamondZookeeperClusterManager

使用Diamond配置```ZookeeperClusterManager```.

添加Diamond配置```{group: "group, 默认为VertxClusterConfig", dataId: "dataId"}```, 内容为```zookeeper.json```文件的内容.

配置VertxOptions添加```clusterManager=@com.github.charlemaznable.vertx.config.DiamondZookeeperClusterManager(group, dataId)```, 其中group不提供则使用默认```VertxClusterConfig```.


##### ApolloHazelcastClusterManager

使用Apollo配置```HazelcastClusterManager```.

添加Apollo配置```{namespace: "namespace, 默认为VertxClusterConfig", propertyName: "propertyName"}```, 内容为```cluster.xml```或```cluster.yaml```文件的内容.

配置VertxOptions添加```clusterManager=@com.github.charlemaznable.vertx.config.ApolloHazelcastClusterManager(namespace, propertyName)```, 其中namespace不提供则使用默认```VertxClusterConfig```.


##### ApolloIgniteClusterManager

使用Apollo配置```IgniteClusterManager```.

添加Apollo配置```{namespace: "namespace, 默认为VertxClusterConfig", propertyName: "propertyName"}```, 内容为```ignite.json```文件的内容.

配置VertxOptions添加```clusterManager=@com.github.charlemaznable.vertx.config.ApolloIgniteClusterManager(namespace, propertyName)```, 其中namespace不提供则使用默认```VertxClusterConfig```.


##### ApolloZookeeperClusterManager

使用Apollo配置```ZookeeperClusterManager```.

添加Apollo配置```{namespace: "namespace, 默认为VertxClusterConfig", propertyName: "propertyName"}```, 内容为```zookeeper.json```文件的内容.

配置VertxOptions添加```clusterManager=@com.github.charlemaznable.vertx.config.ApolloZookeeperClusterManager(namespace, propertyName)```, 其中namespace不提供则使用默认```VertxClusterConfig```.
