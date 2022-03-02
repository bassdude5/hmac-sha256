# Compile
Run the install.sh script from the project root directory.
# Run
Start the server first using the run_server.sh file, then run the client_server.sh file.

# Output Screenshots

![](2.png)

![3](3.png)

# Key Generation for HMAC-SHA256

![](1.png)

For generating the key we first used the appropiate algorithm (in this case it was SHA-256) and then on the basis of the shared private key which given to both client and server we generated HMAC and then represented a 64 Base encoded hash. This hash which was a string was to be exactly the same since both client and server had the secret key and message and could validate that indeed the message was not tampered. 