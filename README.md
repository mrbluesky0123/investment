## Specification
- JDK: 1.8
- Framework: Spring Boot
- DB: H2(Embedded)
- Language: Kotlin
- Persistence Framework: MyBatis
- Test: JUnit5   
# 
## Sequence Diagram
![seq1](https://github.com/mrbluesky0123/todolist-kotlin/blob/main/seq_diagram1.png?raw=true)
![seq2](https://github.com/mrbluesky0123/todolist-kotlin/blob/main/seq_diagram2.png?raw=true)
![seq3](https://github.com/mrbluesky0123/todolist-kotlin/blob/main/seq_diagram3.png?raw=true)

# 
## ERD
![ERD](https://github.com/mrbluesky0123/todolist-kotlin/blob/main/erd.png?raw=true)

#
## Execution
(JDK는 설치되어있다고 가정)   
git clone 수행 후
```
$ ./gradlew clean build
$ java -jar build/libs/exam-0.0.1-SNAPSHOT.jar 
```
# 
## API Specification
- Swagger: [http://127.0.0.1:8091/swagger-ui.html](http://127.0.0.1:8091/swagger-ui.html)

#
## Initial data
* src > main > resources 위치에 schema.sql, data.sql 존재
* 프로그램 구동 시 수행되어 db에 테이블이 생성되고 초기 데이터가 적재 됨