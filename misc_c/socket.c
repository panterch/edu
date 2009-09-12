/* sends a string via udp.
 * a valid listener is netcat:
 * netcat -lup 4000
 */

#include <sys/socket.h>
#include <sys/types.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <netinet/ip.h>


int main(void)
{
  char *msg = "Hello, World!\n";
  int sock = socket(AF_INET, SOCK_DGRAM, 0);
  struct sockaddr_in server;
  server.sin_family = AF_INET;
  server.sin_port   = htons(4000);
  server.sin_addr.s_addr = inet_addr("127.0.0.1");
  sendto(sock, msg, strlen(msg)+1, 0, (struct sockaddr*)&server,
         sizeof(server));
  close(sock);
  exit(0);
}
