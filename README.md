###Instruction:

A springboot demo application with simple API (CRUD operations) connecting to postgresSQL database.

1. Use Spring Initializr to start a springboot project (choose packaging type: jar or war)

2. Open project with Eclipse and implement simple API (CRUD operations: GET + POST + PUT + DELETE) as below chart
   ![image](https://github.com/hbtoan2910/demo/assets/59778636/20f5c4dc-3e41-4835-b0e9-e1f56f505f47)

3. Connect to PostgresSQL database ( can use client app "pdAdmin 4" to easily view database > tables )

4. Notes:
   - Well built API should includes 3 layers:
     > API layer (class StudentController)
     > Service layer (class StudentService)
     > Data Access layer (interface StudentRepository)
     
   - Create 1 more table 'teacher' to test together with table 'student' to understand clearer about GENERATEDTYPE.SEQUENCE (allocationSize attribute)

   - If using @Transactional for method, then no need to use JPARepository methods, just use entity 's set method to update data in database (save, saveAll, findbyId, findAll, ...)
