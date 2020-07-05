alter
session
set "_ORACLE_SCRIPT" = true;
create user jb identified by pass;
grant connect, resource, dba to jb;
alter system disable restricted session;
exit;
