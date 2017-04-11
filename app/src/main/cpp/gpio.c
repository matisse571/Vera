#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <poll.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/stat.h>
#include <sys/types.h>

#include "gpio.h"

#define LOG_TAG "GPIO"

#ifndef EXEC
#include <android/log.h>
#define  LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG,__VA_ARGS__)
#else
#define LOGD(...) printf(">==< %s >==< ",LOG_TAG),printf(__VA_ARGS__),printf("\n")
#endif


void on_new_value(int val);
int read_gpio(char *path, void (*callback)(int));
//LOGD(path);
int main(int argc, char **argv){
    LOGD("Hello!\n");
#ifdef HOST
    return 0;
#endif
    if(argc == 2)
        return read_gpio(argv[1], on_new_value);
       // LOGD(argc);
    else
        LOGD("missing argument path");

    return -1;
}

int read_gpio(char *path, void (*callback)(int)){
    int fd = open(path, O_RDONLY);
    LOGD("interrupt received gpio.c, val: %d", fd);
   // LOGD(fd);
    char buf[11];
    int res = 0;

    if(fd == -1){
        perror("error opening file");
        return -1;
    }

    struct pollfd gpio_poll_fd = {
            .fd = fd,
            .events = POLLPRI,
            .revents = 0
    };

  for(;;){
        res = poll(&gpio_poll_fd,1,-1);
        if(res == -1){
            perror("error polling");
            break;
        }

         LOGD("aPOLLPRI");

        if((gpio_poll_fd.revents & POLLPRI)  == POLLPRI){
 LOGD("bPOLLPRI");

            int off = lseek(fd, 0, SEEK_SET);
            if(off == -1) break;
            memset(&buf[0], 0, 11);
            size_t num = read(fd, &buf[0], 10*sizeof(char));

            callback(atoi(buf));
        }

       // if((gpio_poll_fd.revents & POLLERR) == POLLERR){
            //seems always to be true ..
            //LOGD("POLLERR");
       // }
    }
    return 0;
}

void on_new_value(int val){
    LOGD("interrupt received a, val: %d", val);

}