# IO 


## Basic IO actions 

- getChar :: IO Char
reads a character from the keyboard, echoes it to the screen, and returns the character as its result value

- putChar :: Char -> IO ()
the action writes the character c to the screen and returns no result value

- return :: a -> IO a
the action return v simply returns the value v without performing any interaction at all

## do sequencing 

combines a sequence of IO actions 

- this following expression reads 3 chars discards the second and returns the first and third as a pair
```haskell
do v1 <- a1
   v2 <- a2
   .
   .
   .
   vn <- an
   return (f v1 v2 ... vn)
```

   - the layout rule applies to do expressions
   -  ai generates values for vi


## defined primitives

-reading a string from the keyboard

```haskell
getLine :: IO String
getLine = do x <- getChar
             if x == '\n' then
                return []
             else
                do xs <- getLine
                   return (x:xs)
```

-writing a string to the screen

```haskell
putStr :: String -> IO ()
putStr [] = return ()
putStr (x:xs) = do putChar x
                   putStr xs
```

- writing a string to the screen followed by a newline character

```haskell
putStrLn :: String -> IO ()
putStrLn xs = do putStr xs
                 putChar '\n'
```

## main on slide 12

a complete haskell program us a single IO action called main

```haskell
main :: IO ()
main = getLine >>= \cs - > putStrLn (reverse cs)
```

- this binds the result of the getLine function to a lambda function that takes the result of 'getLine' and reverses it and then prints it to the screen


EQ10. What does each of the three basic IO actions do? Also, what is each of their
type?
getChar :: IO Char
reads a character from the keyboard, echoes it to the screen, and returns the character as its result value

putChar :: Char -> IO ()
the action writes the character c to the screen and returns no result value

return :: a -> IO a
the action return v simply returns the value v without performing any interaction at all

EQ11. What is the working of act1 (with the definition on slide #9) for example