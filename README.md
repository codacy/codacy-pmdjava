[![Codacy Badge](https://api.codacy.com/project/badge/grade/d0f04e048bba4a01a7ef0166bf5b8d32)](https://www.codacy.com/app/Codacy/codacy-pmdjava)
[![Build Status](https://circleci.com/gh/codacy/codacy-pmdjava.svg?style=shield&circle-token=:circle-token)](https://circleci.com/gh/codacy/codacy-pmdjava)

# Codacy PMDJava

This is the docker engine we use at Codacy to have [PMDJava](https://pmd.github.io/) support.
You can also create a docker to integrate the tool and language of your choice!
Check the **Docs** section for more information.

## Usage

#### Publish the docker

```
sbt 'set version in Docker := "dev"' 'set name := "pmd"' docker:publishLocal
```

##### Docker Image Versioning

 Repository Branch | Docker Image
--- | ---
master | codacy-pmd
pmd-legacy | codacy-pmdjava

#### Run the docker

```
docker run --user=docker --rm=true -v <PATH-TO-CODE>:/src -v <PATH-TO>/ruleset.xml:/src/ruleset.xml codacy/pmd:dev
```
> Make sure all the volumes mounted have the right permissions for user `docker`

#### Generate Docs

1. Update the version in `src/main/resources/docs/patterns.json`

2. Run the DocGenerator
```sh
sbt "run-main codacy.pmd.DocGenerator [<VERSION>]"
```

## Docs

[Tool Developer Guide](https://support.codacy.com/hc/en-us/articles/207994725-Tool-Developer-Guide)

[Tool Developer Guide - Using Scala](https://support.codacy.com/hc/en-us/articles/207280379-Tool-Developer-Guide-Using-Scala)

## Test

We use the [codacy-plugins-test](https://github.com/codacy/codacy-plugins-test) to test our external tools integration.
You can follow the instructions there to make sure your tool is working as expected.

## What is Codacy?

[Codacy](https://www.codacy.com/) is an Automated Code Review Tool that monitors your technical debt, helps you improve your code quality, teaches best practices to your developers, and helps you save time in Code Reviews.

### Among Codacy’s features:

 - Identify new Static Analysis issues
 - Commit and Pull Request Analysis with GitHub, BitBucket/Stash, GitLab (and also direct git repositories)
 - Auto-comments on Commits and Pull Requests
 - Integrations with Slack, HipChat, Jira, YouTrack
 - Track issues in Code Style, Security, Error Proneness, Performance, Unused Code and other categories

Codacy also helps keep track of Code Coverage, Code Duplication, and Code Complexity.

Codacy supports PHP, Python, Ruby, Java, JavaScript, and Scala, among others.

### Free for Open Source

Codacy is free for Open Source projects.
