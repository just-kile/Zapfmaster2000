
## Install for frontend build processing

First of all: Install nodejs and npm 
Type in command 

* `sudo add-apt-repository ppa:chris-lea/node.js`
* `sudo apt-get install nodejs`

Try npm -v to see if it has been installed, otherwise sudo apt-get install npm

Now 

It is recommended (but optional) to install karma and gulp globally with

* `sudo npm install -g karma gulp`

so you can cd to buildutils and type:

* `gulp lint` js verification
* `gulp sass` sass compilation
* `gulp watch` recompile sass on change
* `gulp bower` installs bower dependencies
* `gulp rjs-nouglify` concat js and template files to one file (uncompressed)
* `gulp rjs` concat js and template files to one file (compressed = uglified)


 
 To run karma tests just type
 
 *`karma start src/test/resources/javascript/karma.conf.js`

The jasmine tests will be executed everytime a file has been changed.
