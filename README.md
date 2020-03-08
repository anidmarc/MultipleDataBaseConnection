# MultipleDataBaseConnection
The objective of the script it will read the list of database hostname from a .xlsx file and will process some sql query and will generate the out of the query in a csv file

If you create the maven or gradle project then you need to add the following dependency
1. apache poi - to read and write the data in excel file
<dependency>
     <groupId>org.apache.poi</groupId>
     <artifactId>poi-ooxml</artifactId>
     <version>4.1.0</version>
 </dependency>

2. csv api- to write the data in a csv file
<dependency>
     <groupId>org.apache.poi</groupId>
     <artifactId>poi-ooxml</artifactId>
     <version>4.1.0</version>
</dependency>
    
3. db connector - to connect the postgresql 
<dependency>
     <groupId>postgresql</groupId>
     <artifactId>postgresql</artifactId>
     <version>9.1-901.jdbc4</version>
</dependency>
