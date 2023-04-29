# Haskell types, function types, type classes, currying

## haskell types

```haskell
-- a type is a collection of relatede values
-- e has type t
e :: t

--applying function tot he wrong type of argument is a type error
1 + False
-- ERROR: Couldn't match expected type ‘Bool’ with actual type ‘Integer’
```
Bool - logical values
Char - single characters
String - strings of characters, type String = [Char]
Int - fixed-precision integers
Integer - arbitrary-precision integers
Float - single-precision floating-point numbers
Double - double-precision floating-point numbers

A list is a sequence of values of the same type. The type of the values in the list is the list's element type. The type of a list of values of type t is [t].

Tuple types are like list types, but instead of being homogeneous, they can contain values of different types. The type of a tuple of values of types t1, t2, ..., tn is (t1, t2, ..., tn).

```haskell
-- a list of integers
[1,2,3] :: [Int]

-- a list of booleans

[True, False] :: [Bool]

-- tuples of integers and booleans

[(1,True), (2,False)] :: [(Int, Bool)]

```


## function types

function mapping from values of one type (T1) to values of another type (T2) is written as
 T1 -> T2.

```haskell
 not :: Bool -> Bool
 isDigit :: Char -> Bool
 toUpper :: Char -> Char
 (&&) :: Bool -> Bool -> Bool


```

## type classes

### class constraints , type variables must be bound to certain types in order to satisfy the constraints

```haskell
min:: Ord a => a -> a -> a
min x y = if x <= y then x else y
 
-- Ord a is a class constraint

elem :: Eq a => a -> [a] -> Bool
elem _ [] = False
elem x (y:ys) = x == y || elem x ys
-- Eq a is a class constraint
```

constraints arise because values of the generic types are subjected to operaions that are not supported by all types

```haskell

min :: Ord a => a -> a -> a
min x y = if x < y then x else y
elem :: Eq a => a -> [a] -> Bool
elem x (y:ys) | x == y = True
elem x (y:ys) = elem x ys
elem x [] = False
```

Eq - equality types
Ord - ordered types
Show - showable types
Read - readable types
Num - numeric types
Integral - integral types
Fractional - fractional types


## conditional expressions

functions can be defined using conditional expressions
if condition then expression1 else expression2
    - e1 and e2 must be of the same type
    - else is mandatory in haskell

```haskell
abs :: Int -> Int
abs n = if n >= 0 then n else -n


```


## guards
used to make defintions involving multiple conditions easier to read

``` haskell

abs n | n >= 0 = n
      | otherwise = -n

signum n | n < 0 = -1
         | n == 0 = 0
         | otherwise = 1

```

guards and conditional expressions can be used together

```haskell
take :: Int -> [a] -> [a]
take n _ | n <= 0 = []
take _ [] = []
take n (x:xs) = x : take (n-1) xs
```


## lambda expressions

used to define functions without naming them

``` haskell
add x y = x + y
square x = x * x

-- can be written as

add = \x -> (\y -> x + y)
square = \x -> x * x

-- can be used to avoid naming functions that are only referenced once

odds n = map f [0..n-1]
         where
            f x = x*2 + 1

-- can be written as

odds n = map (\x -> x*2 + 1) [0..n-1]
```

## case expressions
patterns can be used to in defining case expressions

```haskell
take m ys = case (m,ys) of
                (0,_) -> []
                (_,[]) -> []
                (n,x:xs) -> x : take (n-1) xs



```

## let expressions

```haskell
f x = let y = x*x
      in y + y

f 3 = let y = 3*3
      in y + y
    = let y = 9
      in y + y
    = 9 + 9
    = 18
```

## where clauses

```haskell
f x -- using where block
| cond1 x = a
| cond2 x = g a
| otherwise = f (h x a)
where a = w x

```

EQ2. Consider a function safetail :: [a] -> [a] that behaves in the same way
as the library function tail, except that safetail maps the empty list to the empty
list, whereas tail gives an error in this case. Define safetail using
    (a) a conditional expression
    ``` haskell
    safetail :: [a] -> [a]
    safetail xs = if null xs then [] else tail xs
    ```
    (b) guarded equations
    ``` haskell
    safetail :: [a] -> [a]
    safetail xs | null xs = []
                | otherwise = tail xs
    ```
    (c) pattern matching
    ``` haskell
    safetail :: [a] -> [a]
    safetail [] = []
    safetail (_:xs) = xs
    ```
EQ3. What is the type of the following Haskell expression? ("Howdy,":"all":[])
    ``` haskell
    ("Howdy,":"all":[]) :: [[Char]]
    ```
EQ4. What is the type of f? f x = x ‘div‘ 2
    ``` haskell
    f :: Integral a => a -> a
    ```
EQ5. What is the type of the following lambda expression? \x y -> x <= y

    ``` haskell
    \x y -> x <= y :: Ord a => a -> a -> Bool
    ```


