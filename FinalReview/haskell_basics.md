# Haskell basics

What does lazy pure functional langauge mean

    * lazy: evaluation is deferred until the value is needed
    * pure: no side effects
    * functional: programming with functions

## standard prelude functions

```haskell
-- select the first element of a list
head :: [a] -> a
head [1,2,3]
1

-- remove the first element of a list
tail :: [a] -> [a]
tail [1,2,3]
[2,3]

-- select the nth element if a list
(!!) :: [a] -> Int -> a
[1,2,3] !! 1

-- select the first n elements of a list
take :: Int -> [a] -> [a]
take 2 [1,2,3]
[1,2]

-- remove the first n elements of a list
drop :: Int -> [a] -> [a]
drop 2 [1,2,3]
[3]

-- append two lists
(++) :: [a] -> [a] -> [a]
[1,2] ++ [3,4]
[1,2,3,4]

-- reverse a list
reverse :: [a] -> [a]
reverse [1,2,3]
[3,2,1]

-- calculate the length of a list
length :: [a] -> Int
length [1,2,3]
3

-- calculate the sum of a list of numbers
sum :: Num a => [a] -> a
sum [1,2,3]
6

-- calculate the product of a list of numbers
product :: Num a => [a] -> a
product [1,2,3]
6


EQ1: Haskell's ________ is what helps guarantee referential transparency.
    * lazy evaluation
    * type system
    * purity
    * immutability
ans: purity