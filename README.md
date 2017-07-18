**BiLogic** is programming language for JVM. All variables are boolean.

## Assignment

Operations:
- `not`
- `or`
- `and`
- `->` (implication)
```
Assignment := <VarList> is <Expression>
VarList := varname, <VarList> | varname
Expression := true | false | varname | not <Expression>
        <Expression> <Operation> <Expression>
Operation := and | or | ->
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
