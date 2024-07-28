
mvn clean
mvn install
rc=$?
# if maven failed, then we will not deploy new version.
if [ $rc -ne 0 ] ; then
  echo Could not perform mvn clean install, exit code [$rc]; exit $rc
fi

# Add env vars to .env config file
echo "$1" >> ./target/.env
echo "$2" >> ./target/.env
echo "$3" >> ./target/.env
echo "$4" >> ./target/.env
echo "$5" >> ./target/.env

# Ensure, that docker-compose stopped
docker-compose --env-file ./target/.env stop

# Start new deployment with provided env vars in ./target/.env file
docker-compose --env-file ./target/.env up --build -d