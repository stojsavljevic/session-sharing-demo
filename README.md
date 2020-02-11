# Session Sharing Demo

This sample demonstrates usage of different session sharing implementations:
* Basic Tomcat's HTTPSession (no session sharing)
  ```
  spring.profiles.active=spring-session
  spring.session.store-type=none
  ```
* Spring Session: Hazlecast Repository
  ```
  spring.profiles.active=spring-session
  spring.session.store-type=hazelcast
  ```
* Spring Session: Redis Repository
  ```
  spring.profiles.active=spring-session
  spring.session.store-type=redis
  spring.redis.cluster.nodes=SET_REDIS_CLUSTER_NODES_HERE
  spring.redis.password=
  ```
  *NOTE:* Redis cluster needs to be installed
* Spring Session: HashMap Implementation (no session sharing)
  ```
  spring.profiles.active=spring-session
  spring.session.store-type=hash-map
  ```
* Hazelcast Web Manager
  ```
  spring.profiles.active=hazelcast-wm
  spring.session.store-type=none
  ```

It also provides endpoints for writting and reading session attributes that can be used to test race conditions that can happen with session sharing.

## Security

Security can be enabled/disabled using `security.enabled` property.
When enabled, form login is enabled and credentials are: `user/user`.

CSRF protection is always disabled.

## Clustering demo application

One can run two (or more) instances of this application using different `application.properties`.

This can be achieved with `--spring.config.location=file:PATH_TO_FILE` program argument.

Two `application.properties` need to have different:

* `server.port`
* `hz.port` in case that Hazelcast is used (_Spring Session: Hazlecast Repository_ or _Hazelcast Web Manager_)

## Testing

For testing race conditions of the appllication (with two instances) one can use `session-sharing-test` applicaion.

## test 6
