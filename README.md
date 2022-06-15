# https---github.com-neelimasahu-cse-ADB_TeamGrey_ParcelService_Neelima

Docker Installation steps:
Created a volume
 
docker run -d -p 2717:27017 -v ~/mongo_Neelima:/data/db --name mongoParcelContainer mongo:latest
 
Entered the container using following command:
docker exec -it mongoParcelContainer bash
 
 
Now To Run mongo inside the container: 
 
root@a6c1a166728a:/# mongo
> show dbs
> use parcelData        //created a database
> db.user.insert({"name":"neelima"})  //created a test data
> db.user.find() // checked the inserted record.
