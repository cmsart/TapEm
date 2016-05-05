var net = require('net');
 
var HOST = '104.131.40.244';
var PORT = 1234;
 
var player1 = '';
var player2 = '';
var scores = '';
 
var score1 = 0;
var score2 = 0;
 
// Create a server instance, and chain the listen function to it
// The function passed to net.createServer() becomes the event handler for the 'connection' event
// The sock object the callback function receives UNIQUE for each connection
const server = net.createServer(function(sock) {
   
    // We have a connection - a socket object is assigned to the connection automatically
    //console.log('CONNECTED: ' + sock.remoteAddress +':'+ sock.remotePort);
   
    server.getConnections(function(err, count) {
 
        //set address of player 1 and player 2
        if(count == 1){
            player1 = sock.remoteAddress;
            console.log(player1 + " is connected as player 1.");
        } else if (count == 2){
            player2 = sock.remoteAddress;
            console.log(player2 + " is connected as player 2.");
        }
    });
 
    // Add a 'data' event handler to this instance of socket
    sock.on('data', function(data) {
 
        if(sock.remoteAddress == player1){
            score1 = data;
        } else if (sock.remoteAddress == player2){
            score2 = data;
        }
 
        score1 = String(score1).trim();
        score2 = String(score2).trim();
 
        scores = score1 + " " + score2 + "\r\n";
 
        // Write the data back to the socket, the client will receive it as data
        sock.write(scores);
    });
   
    // Add a 'close' event handler to this instance of socket
    sock.on('close', function(data) {
        console.log('CLOSED: ' + sock.remoteAddress +' '+ sock.remotePort);
        server.close();
    });
   
}).listen(PORT, HOST);
 
console.log('Server listening on ' + HOST +':'+ PORT);