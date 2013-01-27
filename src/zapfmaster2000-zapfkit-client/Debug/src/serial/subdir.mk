################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/serial/InputService.cpp \
../src/serial/MessageProcessor.cpp \
../src/serial/SerialConnector.cpp 

OBJS += \
./src/serial/InputService.o \
./src/serial/MessageProcessor.o \
./src/serial/SerialConnector.o 

CPP_DEPS += \
./src/serial/InputService.d \
./src/serial/MessageProcessor.d \
./src/serial/SerialConnector.d 


# Each subdirectory must supply rules for building sources it contributes
src/serial/%.o: ../src/serial/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -I"/home/thomas/git/Zapfmaster2000/src/zapfmaster2000-zapfkit-client/include" -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


