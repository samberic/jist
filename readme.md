#Jist

##What is Jist?

Jist is a simple [pastebin](http://pastebin.com/)/[Gist](https://gist.github.com) clone.

##Why? Aren't there other clones out there?

Yes, absolutely.  However Jist is aimed very specifically at the corporate world, places like banks and regulated environments that tend to be stuck on old versions of IE, and will only use Java 
(trying to get python or ruby working in-house isn't going to happen).  The aim with Jist was simple:

1) Pure Java!
2) Simple to run; it's one command. Other than the Jar, nothing needs to be downloaded, nothing needs to be configured. It just runs.

##But you used Java 8 and I only have Java 4/5/6/7

Pull requests are welcome if you want it dropping down. The aim is that you've probably got a box you can sneak Java 8 onto. If not then drop me an [email](mailto:sam@samatkinson.com) and I'm happy to look into it.

##How do I run it?

java -jar jist.jar

Jist will be running on port :4567.

##How do I use it?

Go to http://yourhost:4567.  Here you can create your new Jist.

All Jists are available from http://yourhost:4567/jists/<jistid>

##I'd love feature X Y Z

Pull requests are welcomed and encouraged, or just [email me](mailto:sam@samatkinson.com) and I'll see what I can do.

##Release notes

###1.0.0
- Basic first release, single file anonymous gists.

##Roadmap
- Multiple files per jist
- Commenting
- Login
- Anonymous jists