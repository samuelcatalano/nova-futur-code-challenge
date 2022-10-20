## TAKEN DECISIONS

#### About Collatz Conjucture:
To be very honest I only have heard about the Collatz conjecture once in the high school a long time ago. So basically I had to search for more information on Google until finding **(3n + 1)** or **(n/2)** formula. After that, I built an iterative version and a recursive version which print the conjecture according to the number inputted.

#### About Graphics Point - Dot Product:
This is a classic problem in high school about vectors. This one I remembered the formula for solving but was written in the documentation also. **(a1, a2,···, an)** and **(b1, b2,···, bn)** is **a1b1+a2b2+···+anbn**. My approach was trying to use Java 8 resources like a new way to do a range foreach in parallel and also use map and reduce to multiply and sum the values.

#### About Feedback:
In this case, the idea was to create a function to calculate the sum of a number and its successor. How bigger is the number, more time will spend to calculate and more math operations will be necessary to calculate also. The idea was to use a HashMap simulating a local cache and store new values. When the number is not at the cache a message informing that the number is calculating is returned. After that, if you do the same request using the same number, then will return the number calculated. If you make a request for a number which is already in the local cache, the number is returned instantly. Remembering that there are a lot of options of APIs cache like **Redis**, **Infinispan**, **Couchbase**, **Caffeine**, etc.
