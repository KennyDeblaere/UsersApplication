# UsersApplication
Java Semester IV Assignment @Howest
<b>If this text is shown in bold, download the file, and open it with a plain text editor</b>


You are to write an application managing the online status of users, by means of sending command pattern-like objects.

1

Create a class UsersClient, establishing a connection to a local server at port 9000 upon instantiation.
This client should have the following functionality, implemented using request by commands to the server:


- public void logon():			notifies the server of our session

- public void putOnline(String name):	notifies the server a certain user has come online.
					The server should check if the user is already known, and add it if it is 
					not. 

					Store the users in the most efficient type of data structure allowing to 
					set or read their status.


- public void getOnlineUsers():  	requests the server to send a list of online users to the client. The client
					should, upon receipt of the response, then print their names to stdout.
					The server must only answer this request if the session has ben logged on.


Design the appropriate interface(s) for sending objects from the server to the client and from the client to the server.




2

Create a class UsersServer, listening at port 9000 upon instantiation.

The server must allow multiple clients to connect, and keep an object (ClientConnection) in memory for each connection.
The server must keep a collection of connections in memory, and must be able to look up connections by
ip address, as well as by name efficiently. In case multiple connections authenticated the same name,
the last connection authenticating the name will be deciding.

The server must have the following methods:

- public void startServer

- public void putOnline(String name)

- public .... getOnlineUsers();


The class ClientConnection receives commands from the client, sharing an implementation of one common interface.

The following methods are required:

- private .... receiveFromClient(): a blocking method waiting for a command from the client, and invoking the method below
- private void processClientCommand(.... cmd)
- private void sendToClient(... cmd)

Where necessary, methods must be thread safe.
