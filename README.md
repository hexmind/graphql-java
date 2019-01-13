# graphql-java-weather 

## Sun, clouds and movies

```
+-graphql-java-weather---------+ HTTP
|                              +----------------------------> metaweather.com/api
| graphiql-spring-boot-starter |
|                              | GQL              SQL
| graphql-java-tools           +--------> Hasura +----------> Postgres
|                              |               | |            films DB
|                              | remote schema | |
|                              +<- - - - - - - + |
+------------------------------+                 |
                                                 |
  WebSockets                                     |
<------------------------------------------------+

```                                              

## GraphIQL

Tool for writing, validating, and testing GraphQL queries:

```http://localhost:8081/graphiql```

Example query:
```
{
  searchPlace(query:"NEW YORK") {
    title
    films {
      title
      releaseYear
    }
    forecast {
      consolidatedWeather {
        applicableDate
        theTemp
      }
    }    
  }
}
```