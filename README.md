# Issue-Management-Service
## Mysql Setup  &nbsp; [Visit](https://dev.mysql.com/downloads/mysql/)
```
https://dev.mysql.com/downloads/mysql/ 에서 MySQL server 설치. (최신 버전 8.4.0)
```

## 설치 후 cmd 실행. 
```
cd web-server\src\main\java\com\se14\repository\db_impl
```
```
mysql -u root -p < schema.sql
```

## MySQL server 설치 시 설정했던 root 계정 password 입력.
```
Enter password: {your password}
```

## Web-Server
```
cd web-server
```

### Set Database Connection
```
db-connection.bat
```
### Set Database Connection for OSX or Unix
```
./db-connection.sh
```
### Build Executable Jar
```
gradlew build
```
### Build Executable Jar for OSX or Unix
```
chmod +x gradlew
./gradlew build
```

### Run 
```
java -jar "build\libs\Web_Server-0.0.1-SNAPSHOT.jar"
```

### Website Link  &nbsp; [Visit](http://localhost:8080/)
```
http://localhost:8080
```

<br />
<br />


## GUI Application
```
cd application
```

### Build Executable Jar
```
gradlew build
```
### Build Executable Jar for OSX or Unix
```
chmod +x gradlew
./gradlew build
```
### Run 
```
java -jar "build\libs\application-1.0-SNAPSHOT.jar"
```
