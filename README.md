# Session Sharing Demo

This sample demonstrates usage of different session sharing implementations:
* Basic Tomcat's HTTPSession (no session sharing)
* Spring Session: Hazlecast Repository
* Spring Session: Redis Repository
* Spring Session: HashMap Implementation
* Hazelcast Web Manager

It also test certain race conditions that causes losing session attributes with Spring Session for Hazlecast and HashMap as repositories.