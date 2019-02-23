md target
javac -d target -sourcepath src/main/java src/main/java/com/epam/BatRunApp.java
java -cp target com.epam.BatRunApp graph.txt A C
pause