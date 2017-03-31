rd /s /q  src\test\resources\inbox
rd /s /q  src\test\resources\outbox
rd /s /q  src\test\resources\processed
xcopy src\assembly\inbox_test src\test\resources\inbox\
mvn clean install
@pause