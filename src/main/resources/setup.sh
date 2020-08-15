# DB2
docker run -itd --name hm-db2 --privileged=true -p 50000:50000 -e LICENSE=accept -e DB2INSTANCE=jb -e DB2INST1_PASSWORD=pass -e DBNAME=hm ibmcom/db2

# MySQL
docker run -itd --name hm-mysql -e MYSQL_DATABASE=hm -e MYSQL_USER=jb -e MYSQL_PASSWORD=pass -e MYSQL_ALLOW_EMPTY_PASSWORD=true -p 3306:3306 mysql
docker exec sakila-mysql bash -c "mysql -u root -e 'set global log_bin_trust_function_creators=true;'"

# Oracle
bash oracle/buildDockerImage.sh -v 19.3.0 -e
docker run -itd --name hm-oracle -p 1521:1521 -p 5500:5500 -e ORACLE_SID=hm -e ORACLE_PDB=nhm -v /home/jb/Workspace/JVM/hibernate-mappings/src/main/resources/oracle/19.3.0/custom_scripts:/opt/oracle/scripts/setup oracle/database:19.3.0-ee

# Postgres
docker run -itd --name book-db -p 5432:5432 -e POSTGRES_PASSWORD=pass -e POSTGRES_USER=jb -e POSTGRES_DB=hm postgres:latest
