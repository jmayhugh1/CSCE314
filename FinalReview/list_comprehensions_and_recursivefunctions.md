# List comprehensions and recursive functions:

## List comprehensions

the expression x <- [1..10] is called a gernerator as it shows how to generate values for x

```haskell
[x | x <- [1..10]]
[1,2,3,4,5,6,7,8,9,10]
```

comprehensions can have multiple generators

```haskell
[(x,y) | x <- [1..3], y <- [4..6]]

[(1,4),(1,5),(1,6),(2,4),(2,5),(2,6),(3,4),(3,5),(3,6)]

```
multiple generatros are like nested loops with later generatros as more deeply nested loop[s whose variable change value more frequently

```haskell
[(x,y) | y <- [4,5], x <- [1,2,3]]

[(1,4),(2,4),(3,4),(1,5),(2,5),(3,5)]

-- x<- [1,2,3] is the ;ast generator so it changes value more frequently

```
later generators can depend on the variables that are introduced by earlier generators

```haskell
[(x,y) | x <- [1..3], y <- [x..3]]

[(1,1),(1,2),(1,3),(2,2),(2,3),(3,3)]


list comprehensions can also use guards to restrict the values produced by earlier generators

```haskell
[(x,y) | x <- [1..3], y <- [x..3], x /= y]

[(1,2),(1,3),(2,3)]
```

## Recursive functions

functions can be defined in terms of themselves

```haskell
fac n = if n == 0 then 1 else n * fac (n-1)

-- can be written as

fac 0 = 1
fac n = n * fac (n-1)
```

EQ6. Let xs = [3,2,1]. What is the result of the following expression?
zip xs (tail xs)

tail xs = [2,1]

zip [3,2,1] [2,1] = [(3,2),(2,1)]