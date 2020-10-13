#!/usr/bin/env bash

set -e

MYSQL_HOST=${MYSQL_PORT_3306_TCP_ADDR-127.0.0.1}
MYSQL_USER=root
MYSQL_PASS=root
MYSQL_DB_CLIENTE=cliente
MYSQL_DB_CONTA=conta
MYSQL_DB_PROPOSRTA=proposta

echo "host = $MYSQL_HOST"
echo "user = $MYSQL_USER"
echo "password = $MYSQL_PASS"
echo "cliente = $MYSQL_DB_CLIENTE"
echo "conta = $MYSQL_DB_CONTA"
echo "proposta = $MYSQL_DB_PROPOSRTA"

while !(mysqladmin -h $MYSQL_HOST ping > /dev/null 2>&1)
do
  sleep 1
done
echo "mysql ready..."

function create_db() {
  echo "creating database $1"
  mysql -h $MYSQL_HOST -u $MYSQL_USER -p$MYSQL_PASS -e "CREATE DATABASE IF NOT EXISTS $1"
  echo "creating tables for database $1"
  mysql -h $MYSQL_HOST -u $MYSQL_USER -p$MYSQL_PASS $1 < $2
}

create_db $MYSQL_DB_CLIENTE "src/db/cliente.sql"
create_db $MYSQL_DB_CONTA "src/db/conta.sql"
create_db $MYSQL_DB_PROPOSRTA "src/db/proposta.sql"

echo "prepare database finished."
