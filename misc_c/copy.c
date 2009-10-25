/* 
 * File:   copy_file.c
 *
 * Created on 11. Juni 2009, 15:44
 */

#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <sys/stat.h>

int buffer[1024];
int nread;
int srcfd, dstfd;

/*
 * 
 */
int main(int argc, char** argv) {

    if (argc < 3) {
        perror("Need two filenames");
        exit(1);
    }

    srcfd = open(argv[1], O_RDONLY);
    dstfd = creat(argv[2], S_IRWXU);
    
    do {
        nread = read(srcfd, buffer, sizeof(buffer));
        if(nread > 0){
            write (dstfd, buffer, nread);
        }
    } while (nread > 0);
    close(srcfd);
    close(dstfd);
    exit(0);
}

