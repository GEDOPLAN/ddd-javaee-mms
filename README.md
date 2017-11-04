# ddd-javaee-mms

This is a demo project for our conference / user group talk "DDD with Java EE".

The project uses various maven profiles by which you can switch to your preferred platform configuration:

The tests in src/test use a standalone CDI container. Please activate exacly one of these two profiles:
* cdi_weld: JBoss Weld (a)
* cdi-owb: Open Web Beans

Bean validation is done by 
* bv-hibernate: Hibernate Validator (a)

JPA providers are selected by
* jpa-eclipselink: EclipseLink (a)
* jpa-hibernate: Hibernate ORM

Finally the database is selected by
* db-h2: H2 (a)

Profiles marked with (a) are activated by default, but if you choose a different setting, you must activate explicitely exacly one profile per group.

The project may also be deployed onto some Java EE 8 server, but due to the lack of a reasonable ui the benefits of a server deployment are limited.