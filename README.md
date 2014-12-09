Program5
========

Eclipse can import projects form github nicely if you haven't done that.


To push a change to github   "git push"
To pull changes from github "git pull origin"

Messages

Brian: I think we need to add all the vertexes into the graph and then add the edges.
       The GUI is made using the array of vertexes which don't have any connections with
       the way we add them in right now. It would be simple to just loop through the graph
       vertices and put them in the right spot of the array. 
       
Brian: Update on the code. I add all the vertices into the graph and then add the edges in with the depth first method.
       The code runs and our maze appears, it just doesn't have all the edges. I think this is to do with the way
       I put the vertices into the array. For example, the vertex that one vertex is connect with may not be added
       yet so it may not connect them when the GUI is made. Will look more into it later.