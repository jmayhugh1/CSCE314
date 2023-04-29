# types and meanings of higher order functions

Given the following definition for g, g = (filter even) . (map (+1))

EQ1. What is the type of g?

Num a => [a] -> [a]

what is the result of g [1..5]
    
    [2,4,6]


