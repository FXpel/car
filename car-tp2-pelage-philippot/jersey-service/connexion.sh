mvn clean compile
mvn exec:java


curl -v http://localhost:8080/myapp/application/pwd

curl -v http://localhost:8080/myapp/myresource/home:/mnt/c/Users/fxpel/Desktop/"

curl -v -d "file=filetodelete&copy=/mnt/c/Users/fxpel/Desktop/reptest" "http://localhost:8080/myapp/myresource/download"

curl -v -d 'login=anonymous&psw=anonymous'  http://localhost:8080/myapp/myresource/auth


curl -v -d 'login=anonymous&psw=anonymous'  http://localhost:8080/myapp/myresource/auth
curl -v http://localhost:8080/myapp/myresource/cdup
curl -v http://localhost:8080/myapp/myresource/cwd:reptest
curl -v http://localhost:8080/myapp/myresource/home:/home/m1/pelage/Desktop
curl -v http://localhost:8080/myapp/myresource/upload:/home/m1/pelage/Documents/M1S2/CAR/TP2/car-tp2-pelage-philippot/to_trash
curl -v -d 'login=user&psw=12345'  http://localhost:8080/myapp/myresource/auth
curl -v http://localhost:8080/myapp/myresource/download:to_trash 


curl -v http://localhost:8080/myapp/myresource/upload:/mnt/c/Users/fxpel/Desktop/Test.class