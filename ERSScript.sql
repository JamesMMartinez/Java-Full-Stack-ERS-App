create schema ers_schema;

--Reimbursement Table
create table reimbursement(
reimb_id int primary key,
reimb_amount double precision,
reimb_submitted timestamp,
reimb_resolved timestamp,
reimb_desc varchar(250),
reimb_receipt bytea,
reimb_auth_id int,
reimb_reso_id int,
reimb_status_id int,
reimb_type_id int
);

--Join Example
select r.reimb_id, r.reimb_amount, r.reimb_submitted, r.reimb_resolved, r.reimb_desc,
r.reimb_receipt,r.reimb_auth_id, rs.reimb_status, u.first_name, u.last_name, rt.reimb_type
from ers_schema.reimbursement r 
full join ers_schema.ers_users u on r.reimb_reso_id = u.user_id 
join ers_schema.reimbursement_status rs on r.reimb_status_id = rs.reimb_status_id 
join ers_schema.reimbursement_types rt on r.reimb_type_id = rt.reimb_type_id 
where rs.reimb_status = 'approved' or rs.reimb_status = 'declined'
order by r.reimb_id ;

insert into ers_schema.reimbursement(reimb_amount, reimb_submitted, reimb_desc, reimb_auth_id, reimb_type_id) values();

create sequence ers_seq_1
increment 1
minvalue 1000
maxvalue 2147483648 start 1000
cache 1;
alter table reimbursement alter column reimb_id
set default nextval('ers_seq_1');

ALTER TABLE ers_schema.reimbursement ALTER COLUMN reimb_submitted SET DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE reimbursement ADD CONSTRAINT status_fk FOREIGN KEY (reimb_status_id) REFERENCES reimbursement_status(reimb_status_id);
ALTER TABLE reimbursement ADD CONSTRAINT type_fk FOREIGN KEY (reimb_type_id) REFERENCES reimbursement_types(reimb_type_id);
ALTER TABLE reimbursement ADD CONSTRAINT author_fk FOREIGN KEY (reimb_auth_id) REFERENCES ers_users(user_id);
ALTER TABLE reimbursement ADD CONSTRAINT resolver_fk FOREIGN KEY (reimb_reso_id) REFERENCES ers_users(user_id);

ALTER TABLE reimbursement ALTER COLUMN reimb_status_id SET DEFAULT 1;

--User Table
create table ers_users(
user_id int primary key,
username varchar(30) unique,
password varchar(30) unique,
first_name varchar(20),
last_name varchar(35),
email varchar(40),
role_id int
);

insert into ers_users(user_id, username,password, first_name, last_name, email, role_id)
values(100, 'jmike2198', 'Password1', 'James', 'Martinez', 'jmike2198@gmail.com', 2),
(101, 'thepirateking1', 'Rubberman1', 'Luffy', 'Monkey D.', 'luffymd@gmail.com', 1);

ALTER TABLE ers_users ADD CONSTRAINT role_fk FOREIGN KEY (role_id) REFERENCES user_roles(role_id);

--User Role Table
create table user_roles(
role_id int primary key,
user_role varchar(15)
);

insert into user_roles(role_id, user_role) values(1, 'employee');
insert into user_roles(role_id, user_role) values(2, 'manager');

--Reimbursement Type Table
create table reimbursement_types(
reimb_type_id int primary key,
reimb_type varchar(10)
);

insert into reimbursement_types(reimb_type_id, reimb_type) values(1, 'lodging');
insert into reimbursement_types(reimb_type_id, reimb_type) values(2, 'travel');
insert into reimbursement_types(reimb_type_id, reimb_type) values(3, 'food');
insert into reimbursement_types(reimb_type_id, reimb_type) values(4, 'other');

--Reimbursement Status Table
create table reimbursement_status(
reimb_status_id int primary key,
reimb_status varchar(10)
);

insert into reimbursement_status(reimb_status_id, reimb_status) values(1, 'pending');
insert into reimbursement_status(reimb_status_id, reimb_status) values(2, 'approved');
insert into reimbursement_status(reimb_status_id, reimb_status) values(3, 'declined');




