#!/bin/sh

echo "The application will start in ${BOOT_SLEEP}s..." && sleep ${BOOT_SLEEP}
exec java ${JAVA_OPTS} -noverify -XX:+AlwaysPreTouch -Djava.security.egd=file:/dev/./urandom -cp /app/resources/:/app/classes/:/app/libs/* "com.bk.bk.BkApplication"  "$@"
