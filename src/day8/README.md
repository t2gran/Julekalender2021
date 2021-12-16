```
0:  a  b  c     e  f  g   | 6
1:        c        f      | 2 *
2:  a     c  d  e     g   | 5
3:  a     c  d     f  g   | 5
4:     b  c  d     f      | 4 *
5:  a  b     d     f  g   | 5
6:  a  b     d  e  f  g   | 6
7:  a     c        f      | 3 *
8:  a  b  c  d  e  f  g   | 7 *
9:  a  b  c  d     f  g   | 6
```

## Solution 1
Group numbers by # of lit segments and 
resolve #5 and #6 by finding signal c, e, f.
Other signals are not needed

### 5 Segments
```
2:     c  e     
3:     c     f  
5:  b        f  
```

### 6 Segments
```
0:   c     e    
6:      d  e    
9:   c  d       
```

## Solution 2
Solve the problem using a Matrix. This can be solved by implementing different strategies for identifying rows (number) or columns(signal). Iterate over the strategies until the problem is solved or all strategies are used without any progress. 

Another more abstract solution would be to swap rows and columns until the expected pattern is found. One way to do it is to add the Signal header to the matrix, then sort the rows and columns. Add the headings to the input and sort it the same way. Now the to headers should give you the mapping from signal to segment.

### This is the target matrix 
```
    a  b  c  d  e  f  g
0:  +  +  +     +  +  +  
1:        +        +    
2:  +     +  +  +     + 
3:  +     +  +     +  + 
4:     +  +  +     +    
5:  +  +     +     +  + 
6:  +  +     +  +  +  + 
7:  +     +        +    
8:  +  +  +  +  +  +  + 
9:  +  +  +  +     +  + 
```

#### Target matrix sorted
Sorted by column size and value, then break ties with row length and value.
```
    f  a  c  g  d  b  e
8:  +  +  +  +  +  +  + 
9:  +  +  +  +  +  +    
3:  +  +  +  +  +       
0:  +  +  +  +     +  +  
7:  +  +  +             
6:  +  +     +  +  +  + 
5:  +  +     +  +  +    
4:  +     +     +  +    
1:  +     +            
2:     +  +  +  +     + 
    9  8  8  7  7  6  4
```

#### Input matrix sorted
Now, the same sort algorithm can be applied to any input and should result in the same pattern.
We show only the solution matrix here:
```
    g  d  e  c  f  b  a
    +  +  +  +  +  +  + 
    +  +  +  +  +  +    
    +  +  +  +  +       
    +  +  +  +     +  +  
    +  +  +             
    +  +     +  +  +  + 
    +  +     +  +  +    
    +     +     +  +    
    +     +            
       +  +  +  +     + 
```
This will produce a mapping between segments (Target matrix) and signals (input matrix).
```
    f  a  c  g  d  b  e  (Segments)
    g  d  e  c  f  b  a  (Signals) 
```

