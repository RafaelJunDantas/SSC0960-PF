main = do
  s <- getLine
  --print $ converteStringToIntegerList s
  putStr $ (printPontos $ converteStringToIntegerList s) ++ "| "
  print $ calculaPontos $ converteStringToIntegerList s

converteStringToIntegerList :: String -> [Integer]
converteStringToIntegerList s = map read $ words s :: [Integer]

calculaPontos :: [Integer] -> Integer
calculaPontos [] = 0
calculaPontos (h:[]) = h
calculaPontos (h:x:[]) = h + x
calculaPontos (h:x:y:[]) = h + x + y
calculaPontos (h:x:y:t) = if h == 10
                            then h + x + y + calculaPontos(x:y:t)
                          else if (h+x) == 10
                            then h + x + y + calculaPontos(y:t)
                          else h + x + calculaPontos (y:t)

printPontos :: [Integer] -> String
printPontos [] = " "
printPontos (h:[]) = if h == 10
                        then "X "
                     else show h 
printPontos (h:x:[]) = if h == 10
                            then "X " ++ printPontos (x:[])
                       else if h + x == 10
                            then show h ++ " / "
                       else show h ++ " " ++ show x 
printPontos (h:x:y:[]) = if h == 10
                            then "X " ++ printPontos (x:y:[])
                         else if h + x == 10
                            then show h ++ " / " ++ printPontos (y:[])
                         else show h ++ " " ++ show x ++ " "
printPontos (h:x:y:t) = if h == 10
                            then "X _ | " ++ printPontos (x:y:t)
                        else if h + x == 10
                            then show h ++ " / | " ++ printPontos (y:t)
                        else show h ++ " " ++ show x ++ " | " ++ printPontos (y:t)

                                

