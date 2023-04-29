# Modules 

defining and using modules and reasons of them

- A haskell prgroam consists of a collection of modules, the purposes are
    1. to control namespaces
    2. to create abstract data types

- modules contain various decelrations
    1. import declarations
    2. data and type declarations
    3. type signatures
    4. function definitions
-one module per fle and must begoin with an uppercase letter
```haskell
module Tree ( Tree(Leaf,Branch), fringe ) where --this is the export list
data Tree a = Leaf a | Branch (Tree a) (Tree a)
fringe :: Tree a -> [a]
fringe (Leaf x) = [x]
fringe (Branch left right) = fringe left ++ fringe right
```
- module declerations begins with the keyword module
- the module name may be the same as that of the type
- Same indentation rules apply
```



-- qualified imports
```haskell
module Main (main) where
import Tree ( Tree(Leaf,Branch), fringe )
import qualified Fringe ( fringe )
main = do print (fringe (Branch (Leaf 1) (Leaf 2)))
print (Fringe.fringe (Branch (Leaf 1) (Leaf 2)))
```
-- qualifiers are used to resolve conflicts between diff entities with the same name

EQ9: Example ADT (Abstract Data Type) Stack with two different ways of implementing it and example uses of those stacks as in Exercise 6.

```haskell
module Stack ( StkType, push, pop, top, empty ) where
data StkType a = EmptyStk | Stk a (StkType a)
push x s = Stk x s
pop (Stk _ s) = s
top (Stk x _) = x
empty = EmptyStk

module Stack ( StkType, push, pop, top, empty ) where
newtype StkType a = Stk [a]
push x (Stk xs) = Stk (x:xs)
pop (Stk (_:xs)) = Stk xs
top (Stk (x:_)) = x
empty = Stk []
```