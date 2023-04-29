# defining new types 
data - defines a new type from scratch describing its constructors
type - defines a new name for an existing type
newtype - defines a new type from an existing type with a single constructor with a single field, used for wrapper types

a new type can be defined by specifying its values, or by specifying its operations
    - specifying values: data Bool = False | True
    - specifying operations: data Shape = Circle Float | Rect Float Float

## constructor with args 

```haskell

data Shape = Circle Float | Rect Float Float

Circle :: Float -> Shape
Rect :: Float -> Float -> Shape


-- another example

data Person = Person Name Gender Age

type Name = String

data Gender = Female | Male | Unknown

type Age = Int

Male :: Gender

Person :: Name -> Gender -> Age -> Person

```

## paramterized data decs

```haskell
data Pair a b = Pair a b

Pair :: a -> b -> Pair a b

data Maybe a = Nothing | Just a

Nothing :: Maybe a

Just :: a -> Maybe a

Just 3 :: Maybe Int

```

## type decleration

```haskell
type pos = (Int, Int)
type trans = pos -> pos

--cannot be recursive
type Tree = (Int, [Tree])

```
EQ8. Given the following definition, data Person = Person String Int, what is
the type of constructor Person?

Person :: String -> Int -> Person

