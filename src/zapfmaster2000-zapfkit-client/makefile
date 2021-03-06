CC        := g++
LD        := g++

MODULES   := views serial
SRC_DIR   := $(addprefix src/,$(MODULES))
BUILD_DIR := $(addprefix build/,$(MODULES))

SRC       := $(foreach sdir,$(SRC_DIR),$(wildcard $(sdir)/*.cpp)) $(wildcard src/*.cpp)
OBJ       := $(patsubst src/%.cpp,build/%.o,$(SRC))
INCLUDES  := $(addprefix -I,$(SRC_DIR))
ALL_LIBS  := curl SDL SDL_ttf SDL_image boost_thread wiringPi pthread log4cpp
LIBS	  := $(addprefix -l,$(ALL_LIBS))

vpath %.cpp $(SRC_DIR)

define make-goal
$1/%.o: %.cpp
	$(CC) $(INCLUDES) -c $$< -o $$@
endef

build/%.o: src/%.cpp
	$(CC) $(INCLUDES) -c $< -o $@

.PHONY: all checkdirs clean

all: checkdirs zapfmaster2000-zapfkit-client

zapfmaster2000-zapfkit-client: $(OBJ)
	$(LD) $^ $(LIBS) -o $@


checkdirs: $(BUILD_DIR)

$(BUILD_DIR):
	@mkdir -p $@

clean:
	@rm -rf $(BUILD_DIR)

$(foreach bdir,$(BUILD_DIR),$(eval $(call make-goal,$(bdir))))

# install

prefix = /usr
bindir = $(prefix)/bin
sharedir = $(prefix)/share/zapfmaster2000-zapfkit-client

install: zapfmaster2000-zapfkit-client
	install -m 0755 zapfmaster2000-zapfkit-client $(DESTDIR)$(bindir)
	install -m 0666 -d $(DESTDIR)$(sharedir)/resources
	install -m 0666 resources/* $(DESTDIR)$(sharedir)/resources
    
.PHONY: install
