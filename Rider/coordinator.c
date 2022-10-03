#include <sys/types.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>
#include <pthread.h>
#include <sys/types.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "coordinator.h"
#include "rider.h"

extern int numCars;
extern int bumperCars[100];
extern int numAvailCars;

pthread_mutex_t mutex   = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t noCarsAvail = PTHREAD_COND_INITIALIZER;

int getInLine() 
{
  //pthread_mutex_lock(&mutex);

  //if no car available, block
  //return carId
 // if (all array values are zero, if bounded buffer is empty, idk how
  //to check that condition)
  int carId = 0;
  
  //printf("1numAvailCars: %d: \n", numAvailCars);
    
  if (numAvailCars > 0) {
    numAvailCars--;
    //printf("2numAvailCars: %d: \n", numAvailCars);
    for (int i = 0; i < numCars; i++) {
      if (bumperCars[i] != 0) {
        //assign car ID
        carId = i + 1;
        //printf("carId: %d\n", carId);
        //empty out spot in buffer
        bumperCars[i] = 0;
        return carId;
      }
    }
  }
  
  else if (numAvailCars == 0) {
    pthread_cond_wait(&noCarsAvail, &mutex);
    for (int i = 0; i < numCars; i++) {
      if (bumperCars[i] != 0) {
        //assign car ID
        carId = i + 1;
        //empty out spot in buffer
        bumperCars[i] = 0;
        return carId;
      }
    }
    //return carId;
  }
  
  return EXIT_SUCCESS;
  
  //pthread_mutex_unlock(&mutex);

}

void returnCar(int carId) 
{
  //place carId in buffer
  bumperCars[carId - 1] = carId;
  
  //unblock a waiting thread
  //does this unblock just one thread?
  //does there need to be a condition for this?
  pthread_cond_signal(&noCarsAvail);
  numAvailCars++;
}