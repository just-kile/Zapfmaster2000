################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/serial/InputService.cpp \
../src/serial/WebserviceConnector.cpp 

OBJS += \
./src/serial/InputService.o \
./src/serial/WebserviceConnector.o 

CPP_DEPS += \
./src/serial/InputService.d \
./src/serial/WebserviceConnector.d 


# Each subdirectory must supply rules for building sources it contributes
src/serial/%.o: ../src/serial/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


