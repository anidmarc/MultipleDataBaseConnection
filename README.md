# MultipleDataBaseConnection
The objective of the script it will read the list of database hostname from a .xlsx file and will process some sql query and will generate the out of the query in a csv file

If you create the maven or gradle project then you need to add the following dependency
1. apache poi - to read and write the data in excel file
https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml/4.1.0

2. csv api- to write the data in a csv file
https://mvnrepository.com/artifact/org.apache.commons/commons-csv/1.5
    
3. db connector - to connect the postgresql 
https://mvnrepository.com/artifact/postgresql/postgresql/9.1-901-1.jdbc4
