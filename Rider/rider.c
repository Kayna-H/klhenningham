//main file
//gcc -Wall -std=gnu99 -g rider.c sleeper.c coordinato.c coordinator.h rider.h 
//sleeper.h -o rider -lpthread
//./rider <int> <int> <int>
#include <pthread.h>
#include <stdio.h>
#include <sys/types.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>
#include <string.h>
#include "sleeper.h"
#include "coordinator.h"
#include "rider.h"
#define  _GNU_SOURCE

void* riderThread(void* params);

int numAvailCars = 0;
int numCars;
//how do u initialize an array
int bumperCars[100];

int main(int argc, char* argv[])
{
  pthread_t tid[100]; 
  pthread_attr_t attr; 
  
  /* get the default attributes */ 
  pthread_attr_init(&attr);
   
  if (argc <= 3) {
    printf("Usage: ./a.out <integer value> <integer value> <integer value>\n");
    return -1;
  }
  //printf("num args: %d\n", argc);
  
  int numThreads = 0;
  numThreads = atoi(argv[2]);
  
  numCars = atoi(argv[1]); 
  
  int runningTime = atoi(argv[3]);
  time_t secs = runningTime;
  time_t startTime = time(NULL);
  
  numAvailCars = numCars;

  for (int i = 0; i < numCars; i++) {
    //fill array with values
    bumperCars[i] = i + 1;
  }
  
  for (int i = 0; i < numThreads; i++) { 
       //create ride thread
       //what to pass here?
    pthread_create(&tid[i], &attr, riderThread, (void*)(intptr_t)(i + 1));
  }
  
  time_t endTime = time(NULL);
  time_t elapsedTime = endTime - startTime;
  int timeToWait = secs - elapsedTime;
  //printf("Time to wait: %d\n", timeToWait);
  sleep(timeToWait);
}

void * riderThread(void * param)
{
  
  //int rider = (int) param;
  int rider = (int)param;
  
  walkAroundTime();
  printf("Rider %d is walking around the park.\n", rider);
  
  int lineNumber = getInLine();
  printf("Rider %d is now riding in car %d\n", rider, lineNumber);
  rideTime();
  
  returnCar(lineNumber); 
  printf("Rider %d returned car %d.\n", rider, lineNumber);
  
  pthread_exit(NULL);
}