################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/views/IdleView.cpp \
../src/views/StartupView.cpp \
../src/views/ZapfView.cpp 

OBJS += \
./src/views/IdleView.o \
./src/views/StartupView.o \
./src/views/ZapfView.o 

CPP_DEPS += \
./src/views/IdleView.d \
./src/views/StartupView.d \
./src/views/ZapfView.d 


# Each subdirectory must supply rules for building sources it contributes
src/views/%.o: ../src/views/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


