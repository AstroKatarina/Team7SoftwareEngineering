import socket

# Define the host and port to listen on
host = 'localhost'  # You can change this to your desired host or use an empty string for all available interfaces
port = 7500  # Change this to the desired UDP port number

# Create a UDP socket
server_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

# Bind the socket to the host and port
server_socket.bind((host, port))

print(f"Listening on {host}:{port} for UDP datagrams...")

while True:
    # Receive data from the client
    data, client_address = server_socket.recvfrom(1024)  # Receive data in 1024-byte chunks
    print(f"Received from {client_address}: {data.decode('utf-8')}")  # Assuming the data is in UTF-8 encoding
