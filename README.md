# Zoo Application
This is a backend implementation for a zoo application.   
It is based on Spring boot and Spring Data JPA. It uses an in memory H2 database for the application database.    
The zoo contains rooms and animals. Animals are located in rooms and can also have favourite rooms.


The database can be accessed on: /h2-console
There is no password required.

The following reports can be viewed as JSON:

- List of animals in the zoo but not in the room: /animals/no-rooms  

    - Eg http://localhost:8080/animals/no-rooms

- List of animals in a specific room : /animals/rooms/{roomId} where roomId is an id of a room   
    - Eg http://localhost:8080/animals/rooms/3

- List of favourite rooms of a specific animal: /animals/{animalId}/fav-room where animalId is an id of an animal
    - Eg http://localhost:8080/animals/2/fav-room

