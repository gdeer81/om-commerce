# om-commerce
Single Page App for Ecommerce using Om.Next

#basic idea
every ecommerce site has these basic concerns: users, products, content management, and checkout.

# architecture
Frontend: Om.next obviously
DB: postgresql
Backend: Any server side stuff will be done in Clojure
I'll probably deploy to Heroku since it has been pretty easy to work with in the past.

# Background
As someone who has worked with Java, Java Servelet Pages, and other enterprisey technologies to create web apps I thought it would be interesting to try a bleeding edge technology and compare the experience between the two.

After working through a few Om tutorials, reading a lot of documentation, and watching all of David Nolen's talks I figured it was time to jump right into it.

# The old way
As I said before, I've built enterprisey webapps using JSPs for the front-end, java servlets of some kind for controllers/business logic, and java objects for a number of various kinds of data models.

The client will send a request to the server which will send it through some middleware and call various objects that are running or instantiate new ones to build the page based on some template and then send it back to the client to render. If ajax isn't implemented on the page you'll see a lot of full page refreshes which will hit the server again and sometimes be a bad user experience.  

Oddly enough I've bulit webapps in Clojure that almost followed the same pattern. I had Clojure functions on the backend that would do some logic and add data to the request for some frontend template to render. I wasn't writing JSPs but I was writing html pages with unreadable curly braces, percent signs, and other nonsense that was kind of a pain to maintain.

Obviously Om is only the V in the MVC pattern so I thought about being silly and using a java mvc framework and swapping out the V part with Om. However, most frameworks are very prescriptive in how you build your application so unless I get really masochist I'm going to stick with an isomorphic Clojure stack.

#The New Way
As I said, when I wrote webapps in Clojure it still felt like the same workflow I had before except now I could evaluate things at the repl, but I would still end up just printing the response objects on the page or using some other typical debugging method. 

This time I want a radically new workflow with figwheel and devcards and all the exciting tools that are in the Clojurescript world.  

I'm going to be out of my comfort zone for a large part of this project.

