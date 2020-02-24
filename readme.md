### Dijkstra Algorithm with Edges

This is an implementation of the dijkstra algorithm with edges.
The differences from the "standard" algorithm are the following:
- Edges are used to hold data of weights instead of having the weight on the nodes
- Priority Queue is used to select next smallest path
- Only reachable edges are considered at every step, instead of adding all nodes with infinite weights
- No need to update the nodes distance at every step and re-order the priority queue
- Only new reachable edges are updated with the shortest distance and then added to the priority queue
