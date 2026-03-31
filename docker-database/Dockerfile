FROM mysql:8.0
ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=renfe
COPY import.sql /docker-entrypoint-initdb.d/
COPY estaciones.csv /var/lib/mysql-files/
