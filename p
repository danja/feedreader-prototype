<?xml version="1.0"?>
  <!--
    Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the
    NOTICE file distributed with this work for additional information regarding copyright ownership. The ASF
    licenses this file to You under the Apache License, Version 2.0 (the "License"); you may not use this file
    except in compliance with the License. You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
    CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing
    permissions and limitations under the License.
  -->
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">

  <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>eu.fusepool</groupId>
        <artifactId>parent</artifactId>
        <version>0.1-SNAPSHOT</version>
        <relativePath>../fusepool-platform/parent</relativePath>
    </parent>

  <groupId>it.danja</groupId>
  <version>1.0.0-SNAPSHOT</version>
  <packaging>bundle</packaging>
  <artifactId>NewsMonitor</artifactId>

  <name>NewsMonitor</name>
  <description>Feed aggregator/reader with topic-focussed discovery</description>
  <url>https://github.com/danja/NewsMonitor</url>
  <licenses>
    <license>
      <name>Apache Software License, Version 2.0</name>
      <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
      <distribution>repo</distribution>
      <comments>A business-friendly OSS license</comments>
    </license>
  </licenses>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  </properties>

  <build>
    <!-- make it an OSGi bundle -->
    <plugins>
      <plugin>
        <groupId>org.apache.felix</groupId>
        <artifactId>maven-bundle-plugin</artifactId>
        <extensions>true</extensions>
        <configuration>
          <instructions>
            <Import-Package>
              *
            </Import-Package>
          </instructions>
        </configuration>
      </plugin>
     <plugin>
        <groupId>org.apache.felix</groupId>
        <artifactId>maven-scr-plugin</artifactId>
        <executions>
          <execution>
            <id>generate-scr-scrdescriptor</id>
            <goals>
              <goal>scr</goal>
            </goals>
          </execution>
        </executions>
        <version>1.7.4</version>
      </plugin>

      <plugin>
        <groupId>org.apache.felix</groupId>
        <artifactId>maven-bundle-plugin</artifactId>
        <extensions>true</extensions>
      </plugin>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.0</version>
        <configuration>
          <source>1.6</source>
          <target>1.6</target>
        </configuration>
      </plugin>

            <plugin>
                <groupId>org.apache.felix</groupId>
                <artifactId>maven-bundle-plugin</artifactId>
                <extensions>true</extensions>
                <configuration>
                    <instructions>
                        <Import-Package>
                            org.apache.stanbol.commons.web.base; provide:=true,
                            org.apache.felix.webconsole; provide:=true,
                            *
                        </Import-Package>
                        <Export-Package>
                            it.danja.newsmonitor; version=${project.version}
                        </Export-Package>
                        <Private-Package>
                            it.danja.newsmonitor.resource; version=${project.version},
                            it.danja.newsmonitor.webfragment; version=${project.version}
                        </Private-Package>
                    </instructions>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.rat</groupId>
                <artifactId>apache-rat-plugin</artifactId>
                <configuration>
                    <excludeSubProjects>false</excludeSubProjects>
                    <excludes>
                        <!-- exclude>src/main/resources/org/apache/stanbol/commons/usermanagement/test.txt</exclude -->
                    </excludes>
                </configuration>
            </plugin>
    </plugins>
  </build>


  <dependencies>
    <dependency>
      <groupId>commons-codec</groupId>
      <artifactId>commons-codec</artifactId>
      <version>1.7</version>
    </dependency>
    <dependency>
      <groupId>commons-lang</groupId>
      <artifactId>commons-lang</artifactId>
      <version>2.6</version>
    </dependency>
    <dependency>
      <groupId>commons-logging</groupId>
      <artifactId>commons-logging</artifactId>
      <version>1.1.1</version>
    </dependency>
    <dependency>
      <groupId>org.apache.httpcomponents</groupId> 
      <artifactId>httpclient-osgi</artifactId>
      <version>4.3.1</version>
    </dependency>
      <dependency>
        <groupId>org.apache.httpcomponents</groupId>
        <artifactId>httpcore-osgi</artifactId>
        <version>4.3</version>
      </dependency>
    <dependency>
      <groupId>org.apache.httpcomponents</groupId>
      <artifactId>httpclient</artifactId>
      <version>4.0.1</version>
    </dependency>
    <dependency>
	<groupId>org.apache.httpcomponents</groupId>
	<artifactId>httpclient-cache</artifactId>
	<version>4.3.5</version>
    </dependency>
    <dependency>
		<groupId>org.apache.httpcomponents</groupId>
		<artifactId>fluent-hc</artifactId>
		<version>4.3.5</version>
    </dependency>
    <dependency>
      <groupId>org.freemarker</groupId> 
      <artifactId>freemarker</artifactId>
	  <version>2.3.20</version>
    </dependency>
    <dependency>
 		<groupId>org.apache.httpcomponents</groupId>
		<artifactId>httpcore-nio</artifactId>
		<version>4.3.2</version>
    </dependency>
      <dependency>
        <groupId>org.apache.httpcomponents</groupId>
        <artifactId>httpmime</artifactId>
        <version>4.3.1</version>
      </dependency>

<!-- Stanbol -->
        <dependency>
            <groupId>org.apache.stanbol</groupId>
            <artifactId>org.apache.stanbol.commons.web.base</artifactId>
            <version>1.0.0-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>org.apache.felix</groupId>
            <artifactId>org.apache.felix.webconsole</artifactId>
            <version>3.1.8</version>
        </dependency>
        <dependency>
            <groupId>org.apache.clerezza</groupId>
            <artifactId>platform.config</artifactId>
            <version>0.4</version>
        </dependency>
        <dependency>
            <groupId>org.apache.stanbol</groupId>
            <artifactId>org.apache.stanbol.commons.web.viewable</artifactId>
            <version>1.0.0-SNAPSHOT</version>
        </dependency> 

    
    <!-- OSGi tax -->
    <dependency>
      <groupId>org.osgi</groupId>
      <artifactId>org.osgi.core</artifactId>
      <version>4.3.1</version>
      <scope>provided</scope>
    </dependency>
    <dependency>
      <groupId>org.osgi</groupId>
      <artifactId>org.osgi.compendium</artifactId>
      <version>4.3.1</version>
      <scope>provided</scope>
    </dependency>
    <dependency>
      <groupId>org.apache.felix</groupId>
      <artifactId>org.apache.felix.scr.annotations</artifactId>
    </dependency>

        <dependency>
            <groupId>org.apache.felix</groupId>
            <artifactId>org.osgi.core</artifactId>
            <version>1.4.0</version>
        </dependency>

      <dependency>
        <groupId>org.apache.felix</groupId>
        <artifactId>org.apache.felix.scr</artifactId>
        <version>1.6.2</version>
      </dependency>
      <dependency>
        <groupId>org.apache.felix</groupId>
        <artifactId>org.apache.felix.scr.annotations</artifactId>
        <version>1.9.8</version>
      </dependency>
      <dependency>
        <groupId>org.apache.felix</groupId>
        <artifactId>org.apache.felix.configadmin</artifactId>
        <version>1.2.8</version>
      </dependency>      
      <dependency>
        <groupId>org.apache.felix</groupId>
        <artifactId>org.osgi.compendium</artifactId>
        <version>1.4.0</version>
      </dependency>
      <dependency>
        <groupId>org.apache.felix</groupId>
        <artifactId>org.apache.felix.http.jetty</artifactId>
        <version>2.3.0</version>
      </dependency>
      <dependency>
        <groupId>org.apache.felix</groupId>
        <artifactId>org.apache.felix.http.api</artifactId>
        <version>2.3.0</version>
      </dependency>
      <dependency>
        <groupId>org.apache.felix</groupId>
        <artifactId>org.apache.felix.http.servlet-api</artifactId>
        <version>1.0.0</version>
      </dependency>

    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <scope>test</scope>
    </dependency>
  </dependencies>
</project>