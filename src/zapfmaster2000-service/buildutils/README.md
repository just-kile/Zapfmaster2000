
## Install for frontend build processing
This package installs karma test runner and its dependencies globally (because maven-karma-plugin expect this :-/ )

Step 1.: Install nodejs and npm 
Add 

* `sudo add-apt-repository ppa:chris-lea/node.js`
* `sudo apt-get install nodejs npm`


Next, type the following command and execute

* `sudo npm i -g  karma karma-chrome-launcher karma-jasmine karma-requirejs karma-junit-reporter karma-firefox-launcher requirejs`

That's it!