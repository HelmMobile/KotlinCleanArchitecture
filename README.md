# KotlinCleanArchitecture

![Build Status](https://travis-ci.org/HelmMobile/KotlinCleanArchitecture.svg?branch=master)

## Background

We're a small team of curious developers who are always trying to evolve and improve. 
Even before creating our company we were committed to the quality of our software.
Being a consultancy company has allowed us to improve our quality adding something better in each project we built. 
As a result of this evolution we've built an architecture to start each project from the previous iteration.

## Motivation

We've been working and learning with kotlin since the last year using [this great book](https://leanpub.com/kotlin-for-android-developers) by [@lime_cl](https://twitter.com/lime_cl).
Since the first moment we've been greatly impressed with the language and its gradual learning curve.
With our first attempts we've seen that Koltin is a huge improvement over Java so we decided to start developing our new projects in Kotlin.

So with the Google announcement of Kotlin as a first party language in the SO, we decided to migrate our codebase to kotlin improving it with the Kotlin Syntax Sugar.
 
 
## This Project

This project is the evolution of our way to understand code quality based on [Clean Architecture]((https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html)).

In this repo you'll find an example app to show our way to architect android projects. 


![Screencast](./art/example.gif)

+ To build and install you must get an api from: https://www.themoviedb.org/
+ In your local.properties add API_KEY=your-api-key
+ Uncomment data build.gradle commented line
+ Build and run

## Special Thanks
We know we've not been very active in the android community but we've been aware of it.
Proof of it is the following list of projects and posts that have inspired this project:

+ https://github.com/android10/Android-CleanArchitecture
+ https://github.com/Karumi/Rosie
+ https://github.com/txusballesteros/Android-Clean-Testing
+ https://es.slideshare.net/jmpereirag/limpiando-espero-la-arquitectura-que-yo-quiero
+ https://www.47deg.com/presentations/2017/02/18/Functional-error-handling/

As a gesture of appreciation, we are proud to share our clean architecture approach with a comunity that has shared so much with us.
Thank you all.

## Android Studio plugin

We are aware that working with this architecture can lead into an overwhelming amount of boilerplate. So we have create a [plugin](https://github.com/HelmMobile/Kotlin-Clean-Architecture-plugin-for-Android-Studio) to deal with it.

License
-------

    Copyright 2017 Helm

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
