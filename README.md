**BiLogic** (BivalentLogic) is programming language (esoteric) for JVM. All variables are boolean.

## Methods

```
def name
    body
end def
```

```
<VarList> invoke name <Arguments>

VarList := varname, <VarList> | _, <VarList> | varname | _
Arguments := true <Arguments> | false <Arguments> |
    varname <Arguments> | true | false | varname 
```

Main method:
```
def main
    ...
end def
```

Example
```
def main
    res1, res2 invoke method1
    res3, _, res4, res5 invoke method2 false res1
end def

def method1
    in var1, var2
    ret var2
    var3 is var1 -> var2
    ret var3
end def

def method2 arg1 arg2
    ret arg1 and arg2
    ret arg1 or arg2
    ret arg1 xor arg2
    ret arg1 -> arg2
end def
```

## Assignment

Operations:
- `not`
- `or`
- `and`
- `xor`
- `->`
```
Assignment := <VarList> is <Expression>
Expression := true | false | varname | not <Expression>
        <Expression> <BOperation> <Expression>
BOperation := and | or | xor | ->
```
Examples:
```
var is true
```
```
var is false
```
```
result is var1 and var2 or not var3
```
```
var1, var2 is expression
```

## Input

```
Input := in <VarList>
```
Examples:
```
in var
```
```
in var1, var2, var3
```

## Output

Start with `out`. Examples:
```
out {var}
```
```
out text
```
```
out Value = {var}
```

And you can use expressions inside braces:
```
out Result: {var1 or not var2 -> var3}. I hope.
```

## If

```
IfStatement :=
if <Expression> then
    <Code>
else
    <Code>
end if
```

Example:
```
if var1 and not var2 then
    var3, var4 is expression
    out Hmmm {var3}
else
    out {var3}
end if
```

## While

```
While :=
while <Expression> do
    <Code>
end while
```

Example:
```
while var1 or var2
    out {var3}
    var4 is var5 or var6
    var2 is var6 -> var4
end while
```

## Exception

```
panic
```
```
panic Message
```

## Comment

```
-- Text
```

## License (MIT)

MIT License

Copyright (c) 2017 Vseslav Sekorin (vssekorin.com)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
