
-- Define privileges
insert into ACL_PRIVILEGE (NAME, DESCRIPTION) values('PRV_HOMEPAGE', 'Privilege to see homepage');
insert into ACL_PRIVILEGE (NAME, DESCRIPTION) values('PRV_ADMIN_HOME', 'Privilege to see admin home page');

-- Define urls
insert into ACL_URL (ID, URL, IS_REG_EXP) values(1, '/', 0);
insert into ACL_URL (ID, URL, IS_REG_EXP) values(2, '/admin', 0);

-- Assign privileges to urls
insert into ACL_URL_PRIVILEGE (URL_ID, PRIVILEGE_NAME) values(1, 'PRV_HOMEPAGE');
insert into ACL_URL_PRIVILEGE (URL_ID, PRIVILEGE_NAME) values(2, 'PRV_ADMIN_HOME');

-- Define roles
insert into ACL_ROLE (ID, NAME, DESCRIPTION) values(1, 'ADMIN', 'The top level role. This role can do everything.');

-- Assign privileges to roles
insert into ACL_ROLE_PRIVILEGE (ROLE_ID, PRIVILEGE_NAME) values(1, 'PRV_HOMEPAGE');
insert into ACL_ROLE_PRIVILEGE (ROLE_ID, PRIVILEGE_NAME) values(1, 'PRV_ADMIN_HOME');

-- Define users (use this password: 123456, hash is given below) 
insert into ACL_USER (ID, CREATE_DATE, EMAIL, NAME, SURNAME, PASSWORD, USERNAME) VALUES(1, sysdate(), 'admin@j2ee.sk', 'Serdar', 'Kuzucu', '$2a$10$XgzrY474wCS2DOqZetU4UeFN4q5OoOt56Lt/YHmKbcb6FLDPt0awC', 'admin');

-- Assign role to users
insert into ACL_USER_ROLE (USER_ID, ROLE_ID) values(1, 1);