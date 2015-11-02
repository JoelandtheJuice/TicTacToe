Development manual, what is needed and how to get the project to build on a fresh machine

###Source control###

First, you need [Git](http://git-scm.com/), if you don't already have it.
The project is hosted on GitHub. Use the clone command `git clone https://github.com/JoelandtheJuice/TicTacToe.git` to download the latest version or you can download the zip [here](https://github.com/JoelandtheJuice/TicTacToe/archive/master.zip).

###Build environment###


Necessities:

- [Java JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](http://maven.apache.org/download.cgi)

Good to have:

- [Travis CI Client](https://github.com/travis-ci/travis.rb) (requires [Ruby](https://www.ruby-lang.org/en/downloads/))
- [Heroku toolbelt](https://toolbelt.heroku.com/) 

###Compiling###

* `mvn -q clean`
* `mvn -q package`

Or you can run our script (while in TicTacToe/ directory)

* `chmod +x bin/runMe`
* `./bin/runMe`
