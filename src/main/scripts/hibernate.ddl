
    create table accounts (
        account_type varchar(31) not null,
        id int4 not null,
        balance float8,
        created_on timestamp,
        term int4,
        owner_id int4,
        primary key (id)
    );

    create table customer_phones (
        customer_id int4 not null,
        phone varchar(255),
        phone_order int4 not null,
        primary key (customer_id, phone_order)
    );

    create table customers (
        id int4 not null,
        address varchar(255),
        name varchar(255),
        primary key (id)
    );

    create table employees (
        id int4 not null,
        name varchar(255),
        supervisor_id int4,
        primary key (id)
    );

    alter table accounts 
        add constraint FK809DBBE61C55FCED 
        foreign key (owner_id) 
        references customers;

    alter table customer_phones 
        add constraint FK55C2F8464F94DBE2 
        foreign key (customer_id) 
        references customers;

    alter table employees 
        add constraint FK9D39EF85954B9419 
        foreign key (supervisor_id) 
        references employees;

    create sequence hibernate_sequence;
