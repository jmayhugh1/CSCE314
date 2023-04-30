# Monads

## how to make a data structure an instance of functor
- functors support mapping of a function over a strcture such as lists or trees

```haskell
class Functor f where
   fmap :: (a -> b) -> f a -> f b
   -- fmap takes a functoin of type (a->b) and a structure of type (f a) and returns a structure of type (f b)

   --to make something an instance of functor
instance Functor [] where
   --fmap :: (a -> b) -> [a] -> [b]
fmap = map
      
    -- functor with maybe 
instance Functor Maybe where
    --fmap :: (a -> b) -> Maybe a -> Maybe b
    fmap f Nothing = Nothing
    fmap f (Just x) = Just (f x)
    ```
    > fmap(+1) Nothing
    Nothing
    > fmap not (Just False)
    Just True

    ```
    -- functor with trees
    data Tree a = Leaf a | Node (Tree a) (Tree a)

    instance Functor Tree where
        --fmap :: (a -> b) -> Tree a -> Tree b
        fmap g (Leaf x) = Leaf (g x)
        fmap g (Node l r) = Node (fmap g l) (fmap g r)
    ```
```

- functor laws fmap id = id
- fmap (g . h) = fmap g . fmap h

1. fmap preserves identity function



## how to make a data structure an instance of applicative 

the funciton pure takes a value of any type and returns a structure of that type with that value as its only element

```haskell
class Functor f => Applicative f where
    pure :: a -> f a
    (<*>) :: f (a -> b) -> f a -> f b
```

- the operator <*> is a generalization of function application to the case where the value being applied is itself in a structure

- applicative functor instance example 1: maybe
```haskell
data Maybe a = Nothing | Just a

instance Applicative Maybe where
    --pure :: a -> Maybe a
    pure = Just
    --(<*>) :: Maybe (a -> b) -> Maybe a -> Maybe b
    Nothing <*> _ = Nothing
    (Just g) <*> mx = fmap g mx
    > pure (+1) <*> Nothing
Nothing
> pure (+) <*> Just 2 <*> Just 3
Just 5
> mult3 x y z = x*y*z
> pure mult3 <*> Just 1 <*> Just 2 <*> Just 4
Just 8
```
-- appliactive functor instance example 2: list type []
```haskell

instance Applicative [] where
    --pure :: a -> [a]
    pure x = [x]
    --(<*>) :: [a -> b] -> [a] -> [b]
    gs <*> xs = [g x | g <- gs, x <- xs]
    > pure (+1) <*> [1,2,3]
[2,3,4]

> pure (+1) <*> [1,2,3]
[2,3,4]
> pure (+) <*> [1,3] <*> [2,5]
[3,6,5,8]
> pure (:) <*> "ab" <*> ["cd","ef"]
["acd","aef","bcd","bef"]

> [(*2), (+3)] <*> [1,2,3]
[2,4,6,4,5,6]

```

--applicative functor instance example 3: the tree type
```haskell
data Tree a = Leaf a | Node (Tree a) (Tree a)
instace Applicative Tree where
    --pure :: a -> Tree a
    pure x = Leaf x
    --(<*>) :: Tree (a -> b) -> Tree a -> Tree b
    Leaf g <*> tx = fmap g tx
    Node l r <*> tx = Node (l <*> tx) (r <*> tx)
> pure (+1) <*> tree1
Node (Leaf 2) (Leaf 3)
> pure (+) <*> tree1 <*> tree2
Node (Node (Leaf 11) (Leaf 21)) (Node (Leaf 12) (Leaf 22))

```
- applicative functor laws
1. pure id <*> v = v
2. pure (.) <*> u <*> v <*> w = u <*> (v <*> w)
3. pure f <*> pure x = pure (f x)
4. u <*> pure y = pure ($ y) <*> u

- pure preserves identity function
- pure preserves function composition
- when an effectful function is applied to a pure argument, the order in wich the two components are evaluated does not matter
- the operator <*> is associative



## how to make a data structure an instance of monad

```haskell
data Maybe a = Nothing | Just a
instance Monad Maybe where
    -- (>>=) :: Maybe a -> (a -> Maybe b) -> Maybe b
    Nothing >>= _ = Nothing
    (Just x) >>= f = f x

dic2 x = if even c then Just (x `div` 2) else Nothing

(Just 10) >>= div2
Just 5

(Just 10) >>= div2 >>= div2

Just 2

(Just 10) >>= div2 >>= div2 >>= div2

Nothing

```

Monad instance example 2: list type []
```haskell
intance Monad [] where 
    -- (>>=) :: [a] -> (a -> [b]) -> [b]
    xs >>= f = [y | x <- xs, y <- f x]

pairs xs ys = xs >>= \x -> ys >>= \y -> return (x,y)

> pairs [1,2] [3,4]
[(1,3),(1,4),(2,3),(2,4)]
```

Monad instance example 3: the tree type
```haskell
instance Monad Tree where 
    -- (>>=) :: Tree a -> (a -> Tree b) -> Tree b
    Leaf x >>= f = f x
    Node l r >>= f = Node (l >>= f) (r >>= f)

tree1 = Node (Leaf 1) (Leaf 2)
tree2 = Node (Leaf 10) (Leaf 20)
feven = \x -> Leaf (even x)
fnot = \x -> Leaf (not x)

tree1 >>= feven >>= fnot
Node (Leaf False) (Leaf True)

tree1 >>= (\x -> feven x >>= fnot)
Node (Leaf False) (Leaf True)

Monad laws
1. return x >>= f = f x
2. m >>= return = m
3. (m >>= f) >>= g = m >>= (\x -> f x >>= g)
>>= is associative
```