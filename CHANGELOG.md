# Change Log
All notable changes to this project will be documented in this file. This project
adheres to [Semantic Versioning](http://semver.org/). The change log file consists
of sections listing each version and the date they were released along with what
was added, changed, deprecated, removed, fix and security fixes.

- Added - Lists new features
- Changed - Lists changes in existing functionality
- Deprecated -  Lists once-stable features that will be removed in upcoming releases
- Removed - Lists deprecated features removed in this release
- Fixed - Lists any bug fixes
- Security - Lists security fixes to security vulnerabilities

## [Unreleased]
## [1.0.2] - 2018-01-03
### Changed
- Updated Testify Version

## [1.0.1] - 2017-12-19
### Fixed
- Insured that `local-resources-bom` dependencies are test scoped.

## [1.0.0] - 2017-12-18
### Changed
- Changed groupId from `org.testifyproject.resources` to `org.testifyproject.local-resources`
- Updated Testify Dependency Version to 1.0.0
- Updated local resources versions to the latest released version

## [0.9.5] - 2017-09-15
### Changed
Updated versions of Testify and Build-Tools dependencies

## [0.9.4] - 2017-07-16
### Changed
- Updated to the latest Testify API version 0.9.6
- Updated and added LocalResourceInstance paramter to LocalResourceProvider#stop method

## [0.9.3] - 2017-06-06
### Changed 
- Upgraded Testify API version to 0.9.4 and adopted Semantic Testing
 - Updated ResourceProvider implementations to use LocalResourceProvider updated method signature
 - Updated LocalResourceInstance getServer() reference to getResource()
 - Updated LocalResourceInstanceBuilder server() reference to resource()

## [0.9.2] - 2017-03-20
### Added
- Added sonatype plugin repos

### Changed
- Centralized the generation of a jar with resource version classifier
- Contributing documentation

### Fixed
- Issue with artifacts not being deployed due to [missing javadoc artifacts](https://travis-ci.org/testify-project/resources/builds/212759300)

## [0.9.1] - 2017-03-19
### Fixed
- Issue with artifacts not being deployed due to [missing javadoc artifacts](https://travis-ci.org/testify-project/resources/builds/212755133)

## [0.9.0] - 2017-03-19
### Added
- ResourceProvider implementations for:
 - HSQL Database
 - ElasticSearch
 - Apache Storm
 - Apache Kafka
 - Apache YARN
 - Apache HDFS
 - Apache ZooKeeper
 - Titan Graph Database
