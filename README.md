# hibernate-mappings
Common ORM mappings implemented using Hibernate. This is a sandbox project that exposes hibernate's abilities and allows to perform tests.

## Setting up database

This project runs with 4 different databases: Oracle, Ibm DB2, Postgres and MySQL.
To run it, simply install postgres and create database using

    createdb hm
    
Or use already configured docker images (*src/resources/setup.sh*).
Every database is set up to use *jb* as login and *pass* as password by default.
This can be configuret directly in *setup.sh*.

*hibernate-mappings* makes use of spring's dll generation, so there's no need to run sql scripts to create tables.

## Setting up Oracle database docker container

To set up container with Oracle db, simply download Oracle database 19.3.0 installer (*LINUX.X64_193000_db_home.zip*) and copy it to */src/resources/oracle/19.3.0*. 
Then run commands from *setup.sh* under *Oracle* section.
