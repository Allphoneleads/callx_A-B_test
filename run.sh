gradle -x test clean build -PenvironmentName=prod
java  -Xmx8000M -Xms3000M -XX:MaxPermSize=1000M -XX:+CMSClassUnloadingEnabled -XX:+CMSPermGenSweepingEnabled -jar build/libs/allPhoneLeads.jar &
java  -Xmx512M -Xms128M -XX:MaxPermSize=512M -XX:+CMSClassUnloadingEnabled -XX:+CMSPermGenSweepingEnabled -jar build/libs/allPhoneLeads.jar &
java -jar build/libs/callxABTest.jar &
/usr/bin/memcached -m 1024 -p 11211 -u memcache -l 127.0.0.1

sudo echo 3 > /proc/sys/vm/drop_caches