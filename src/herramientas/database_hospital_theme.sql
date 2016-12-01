/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Eudy
 * Created: 25-nov-2016
 */

DROP database java_hospital_theme_db;
create database java_hospital_theme_db;
use java_hospital_theme_db;
create table users (
    id int not null auto_increment primary key,
    name_user varchar(100),
    password_user varchar(150),
    when_it datetime,
    id_user int,
    type_of_user enum('admin','medico','cajero')
);

create table type_of_sonography(
    id int not null auto_increment primary key,
    name_of_type_sonography varchar(100),
    when_it datetime,
    id_user int,
    foreign key (id_user) references users(id)
);

create table hospital(
    id int not null auto_increment primary key,
    name_hospital varchar(100),
    telephone varchar(25),
    email varchar(50),
    address text,
    web_page varchar(100),
    when_it datetime,
    rnc varchar(50),
    id_user int,
    foreign key (id_user) references users(id)
);

create table type_of_blood(
    id int not null auto_increment primary key,
    name_of_blood varchar(10),
    when_it datetime,
    id_user int,
    foreign key (id_user) references users(id)
);

create table patient(
    id int not null auto_increment primary key,
    name_patient varchar(100),
    last_patient varchar(100),
    document_id varchar(30) unique,
    date_of_birth date,
    sex enum('m','f'),
    type_of_blood varchar(5),
    when_it datetime,
    id_user int,
    foreign key (id_user) references users(id)
);

create table telephone(
    id int not null auto_increment primary key,
    telephone varchar(20),
    when_it datetime,
    id_patient int,
    id_user int,
    foreign key (id_user) references users(id),
    foreign key (id_patient) references patient(id)
);

create table insurance_patient(
    id int not null auto_increment primary key,
    name_insurance_patient varchar(50),
    insurance_number varchar(30),
    when_it datetime,
    id_patient int,
    id_user int,
    foreign key (id_user) references users(id),
    foreign key (id_patient) references patient(id)
);

create table email(
    id int not null auto_increment primary key,
    email varchar(20),
    when_it datetime,
    id_patient int,
    id_user int,
    foreign key (id_user) references users(id),
    foreign key (id_patient) references patient(id)
);

create table address(
    id int not null auto_increment primary key,
    sector varchar(50),
    province varchar(50),
    address text,
    when_it datetime,
    id_patient int,
    id_user int,
    foreign key (id_user) references users(id),
    foreign key (id_patient) references patient(id)
);

drop table sonography;
create table sonography(
    id int not null auto_increment primary key,
    referred_for varchar(100),
    body text,
    when_it datetime,
    date_delivered datetime,
    condition_sonography enum('normal','cuidado','grave'), 
    status enum('Pendiente_de_entrega','Entregado'),
    id_type_of_sonography int,
    id_patient int,
    id_user int,
    id_hospital int,
    foreign key (id_user) references users(id),
    foreign key (id_type_of_sonography) references type_of_sonography(id),
    foreign key (id_hospital) references hospital(id),
    foreign key (id_patient) references patient(id)
);

drop table title_report_delivered;
create table title_report_delivered(
    id int not null auto_increment primary key,
    title varchar(150),
    id_user int,
    id_hospital int,
    foreign key (id_user) references users(id),
    foreign key (id_hospital) references hospital(id)
);

drop table report_delivered;
create table report_delivered(
    id int not null auto_increment primary key,
    id_hospital int,
    id_user int,
    id_sonography int,
    id_title_report_delivered int,
    foreign key (id_sonography) references sonography(id),
    foreign key (id_user) references users(id),
    foreign key (id_hospital) references hospital(id),
    foreign key (id_title_report_delivered) references title_report_delivered(id)
);

drop table price_of_hospital;
create table price_of_hospital(
    id int not null auto_increment primary key,
    price decimal(20,2),
    id_hospital int,
    id_type_of_sonography int,
    id_user int,
    foreign key (id_user) references users(id),
    foreign key (id_type_of_sonography) references type_of_sonography(id),
    foreign key (id_hospital) references hospital(id)
);
