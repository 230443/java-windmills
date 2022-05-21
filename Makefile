install:
	cd windmills-backend;\
	mvn clean package;

run:
	docker-compose up -d

	export FLASK_APP=$(realpath hardware/turbine_api.py);\
	flask run &

	cd windmills-backend;\
	java -jar target/windmills-backend-0.0.1-SNAPSHOT.jar
