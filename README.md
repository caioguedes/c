# C
Lazy cli tool (even on the name) for personal ordinary tasks. It may not fit your personal tasks.

## Requirements
 * Clojure 1.10+
 * Leiningen 2+
 * Java 8+
 
## Install
At moment you need generate the uberjar your self, for that use:
```shell script
lein uberjar
```
then copy the standalone jar to a folder and create a alias to following command:

```shell script
alias c="java -jar c-0.0.1-SNAPSHOT-standalone.jar"
```

## Tasks

### Replace variables on bootstrap.yml (yet main command)
Usually I need to see all config in a Spring Project at work, usually they all have a bootstrap file located on:
`src/main/resources/bootstrap.yml`, this command
replaces variables in bootstrap.yml and prints out urls to Cloud Config server and Vault.

```shell script
c
```

Example of bootstrap.yml file:
```shell script
spring:
  application:
    name: my-project
  cloud:
    config:
      uri: http://config-server-${ENV_TYPE:dev}.example.com
    vault:
      host: vault-${ENV_TYPE:dev}.example.com
      token: ${VAULT_TOKEN:fixed-dev-token}
      generic:
        default-context: projects/application
        application-name: projects/my-project
```
Output:
```shell script
"Spring Cloud Config:"
"Url:" "http://config-server-dev.example.com/my-project/default"
"Spring Cloud Vault:"
"Token:" "fixed-dev-token"
"Url:" "null://vault-dev..example.com:null/ui/vault/secrets/secret/show/projects/my-project"
```

## Testing
```shell script
lein test
```
