################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/Files.cpp \
../src/ZapfController.cpp \
../src/ZapfDisplay.cpp \
../src/ZapfkitClient.cpp 

OBJS += \
./src/Files.o \
./src/ZapfController.o \
./src/ZapfDisplay.o \
./src/ZapfkitClient.o 

CPP_DEPS += \
./src/Files.d \
./src/ZapfController.d \
./src/ZapfDisplay.d \
./src/ZapfkitClient.d 


# Each subdirectory must supply rules for building sources it contributes
src/%.o: ../src/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


