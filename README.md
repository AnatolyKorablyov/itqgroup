# itqgroup

POST /document создание документа параметры body: id (String), author (String), title (String)  
GET /documentById получение документа с историей по id (String)  
GET /documentsByIds получение списка документов по списку id (List<String>) или при помощи пагинации (limit (int), offset (int), sort (String)  
GET /documents поиск документов по author (String), status (String), start_date (long), end_date (long). Поиск периода осуществляется по полю create_date   
POST /submitted перевод документа из статуса draft в submitted. Body: список ids (List<String>), author (String), comment (String)  
POST /approved перевод документа из статуса submitted в approved. Body: список ids (List<String>), author (String), comment (String)  
POST /concurrency запуск параллельного перевода одного документа в статус approved. Body: threads (int), attempts (int), id (String)  


**application.properties** свойства для запуска  
application.limit.pageSize=10 дефолтное значение для limit для запроса с пагинацией  
application.generation.document.value=100 количество гененируемых документов через API   
application.worker.batch.size=10 размер пачки для воркеров  
application.worker.cron.expression=0 * * * * * выражение для запуска воркеров по времени   
application.worker.enabled=false включение/выключение воркеров  


для запуска тестов есть файл itqgroup\app\src\test\java\com\itqgroup\service\app\RestIT.java
Для тестов, поднимаются внутренние testContainers с базой данных, БД отдельно можно не поднимать

перед запуском приложения требуется поднять базу данных, для этого есть docker-compose.yaml в корне
для запуска приложения используется itqgroup\app\src\main\java\com\itqgroup\service\app\ITQGroupApplication.java