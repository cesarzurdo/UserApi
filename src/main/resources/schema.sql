CREATE TABLE IF NOT EXISTS "USERS" (
    ID NUMBER (19) PRIMARY KEY AUTO_INCREMENT,
    UUID VARCHAR2(50) NOT NULL,
    NAME VARCHAR2(50),
    EMAIL VARCHAR2(50) NOT NULL,
    "PASSWORD" VARCHAR2(70) NOT NULL,
    ACTIVE BOOLEAN
);

CREATE TABLE IF NOT EXISTS PHONES (
    ID NUMBER (19) PRIMARY KEY AUTO_INCREMENT,
    NUMBER BIGINT NOT NULL,
    CITYCODE INT NOT NULL,
    COUNTRYCODE VARCHAR2(5) NOT NULL,
    USER_ID NUMBER (19),
    CONSTRAINT PHONE_USER_FK FOREIGN KEY (USER_ID) REFERENCES "USERS" (ID)
);

insert into "USERS" (ID, UUID, NAME, EMAIL, PASSWORD, ACTIVE) values (1, 'bbd279cf-2fe9-488f-853e-43968f642fa2', 'Sandor Ilewicz', 'silewicz0@elpais.com', '$2a$10$oy50bcJ7BjfckJ7I5gcED.zzGJbkOUMmbGCeSbp5FH2FQQREjf9e2', true);
--insert into "USERS" (ID, UUID, NAME, EMAIL, PASSWORD) values (2, 'b1f9e644-faa9-4182-bc58-b7ae28cc02e3', 'Kassia Hollow', 'khollow1@flickr.com', '$2a$04$DyUBAyvyTe3X79r24SfXBOUoJTJB7P1ZEODJLBO7AsV');
--insert into "USERS" (ID, UUID, NAME, EMAIL, PASSWORD) values (3, '93cdbf90-e9a3-4ddc-9f87-83a345bbe193', 'Jeff Row', 'jrow2@constantcontact.com', '$2a$04$hcl5Z.oq2FXHm78tkvx8mesklwaJJdLDjPVtIuvvGG');
--insert into "USERS" (ID, UUID, NAME, EMAIL, PASSWORD) values (4, '424f317e-62dd-4f5c-ad79-73b7f176dc2b', 'Trip Kinane', 'tkinane3@mozilla.org', '$2a$04$HbwssjXN77awL/5XFqFqkuyTwELbrFhrtGGqqdqgz5V');
--insert into "USERS" (ID, UUID, NAME, EMAIL, PASSWORD) values (5, 'aa07279a-01e5-488e-93f1-e3df67d23134', 'Jeana Bonnier', 'jbonnier4@noaa.gov', '$2a$04$GlIC9vBPfuqWInI/x/zrIO9KltuM1t1eD9LV4dEqG67');
--
--insert into PHONES (ID, NUMBER, CITYCODE, COUNTRYCODE, USER_ID) values (1, 1, 1, 'BG', 2);
--insert into PHONES (ID, NUMBER, CITYCODE, COUNTRYCODE, USER_ID) values (2, 2, 2, 'MQ', 3);
--insert into PHONES (ID, NUMBER, CITYCODE, COUNTRYCODE, USER_ID) values (4, 4, 4, 'CN', 5);
insert into PHONES (ID, NUMBER, CITYCODE, COUNTRYCODE, USER_ID) values (5, 5, 5, 'SE', 1);
--insert into PHONES (ID, NUMBER, CITYCODE, COUNTRYCODE, USER_ID) values (7, 7, 7, 'SL', 3);
--insert into PHONES (ID, NUMBER, CITYCODE, COUNTRYCODE, USER_ID) values (8, 8, 8, 'BR', 4);
--insert into PHONES (ID, NUMBER, CITYCODE, COUNTRYCODE, USER_ID) values (9, 9, 9, 'PT', 5);
insert into PHONES (ID, NUMBER, CITYCODE, COUNTRYCODE, USER_ID) values (10, 10, 10, 'ID', 1);