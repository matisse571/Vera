#ifndef _GPIO_H_
#define _GPIO_H_
int read_gpio(char *path, void (*callback)(int));
#endif