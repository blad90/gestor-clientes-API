# Gestor Clientes API

Este proyecto utiliza `Quarkus`, un framework de Java.

## Ejecutar la aplicacion en modo dev

Se puede ejecutar el proyecto en modo dev mediante el comando:

```shell script
./mvnw quarkus:dev
```

## Empaquetado y ejecucion de la aplicación

Se puede usar el comando:

```shell script
./mvnw package
```

Genera el archivo `quarkus-run.jar` en el directorio `target/quarkus-app/`.
Tomar en cuenta que este no es un _über-jar_ ya que las dependencias son copiadas en el directorio `target/quarkus-app/lib/`.

La aplicacion es ejecutable usando `java -jar target/quarkus-app/quarkus-run.jar`.

En caso de generar un _über-jar_, ejecutar el comando siguiente:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

La app, empaquetada como _über-jar_, es ejecutable mediante `java -jar target/*-runner.jar`.

## Creando un ejecutable nativo

Se puede lograr mediante el comando:

```shell script
./mvnw package -Dnative
```

O, en caso de no tener instalado GraalVM, se puede ejecutar el build del nativo en un contenedor mediante:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

Se puede correr el ejecutable nativo con: `./target/gestor-clientes-API-1.0.0-runner`