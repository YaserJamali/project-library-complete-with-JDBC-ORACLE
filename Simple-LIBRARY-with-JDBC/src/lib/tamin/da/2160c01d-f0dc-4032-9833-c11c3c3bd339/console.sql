alter table DEAL
drop
constraint SYS_C007004
/

alter table DEAL
    add foreign key (BUYER_ID) references PERSON ()
    /

alter table DEAL
drop
constraint SYS_C007005
/

alter table DEAL
    add foreign key (SELLER_ID) references PERSON ()
    /

