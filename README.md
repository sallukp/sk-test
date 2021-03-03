## Thank you for your interest

Your submission will be run in a clean environment
to see if it produces the expected output. Please provide
instructions for installing any dependencies.

### What we look for

We will be looking at the quality and professionalism of your work. In
particular we look for clean, well-designed, maintainable code. Although this is
a rather small task, it should be approached as you would an actual task for a
customer.

We would like to see some architecture, but only as much as reasonable.
Your code shuold be easily extensible without burdensome abstractions.

### When you are finished

Please push your solution to the master branch in the provided github
repository. There is no need to fork this repository to your own github account.

## The Problem

We want you to create a command-line application that will calculate the
ranking table for a sports league.

### Input/output

The input and output will be text. Your solution should parse the provided
sample-input.txt file via stdin (pipe or redirect) or by parsing a file passed
by name on the command line. Your solution should output the correct result via
stdout to the console.

The input contains results of games, one per line. See sample-input.txt for
details. The output should be ordered from most to least points, following the
format specified in expected-output.txt.

You can expect that the input will be well-formed. There is no need to add
special handling for malformed input files.

### The rules

A draw (tie) is worth 1 point and a win is worth 3 points. A
loss is worth 0 points. If two or more teams have the same number of points,
they should have the same rank and be printed in alphabetical order (as in the
tie for 3rd place in the sample data).

### Guidelines

This should be implemented in a language with which you are familiar.
Please choose a language that is both comfortable for you and suited to the task.

Your solution should be able to be run (and if applicable, built) from the
command line. Please include appropriate scripts and instructions for
running your application and your tests.

If you use other libraries installed by a common package manager,
it is not necessary to commit the installed packages.

We write automated tests, and we would like you to do so as well.

We appreciate well factored, object-oriented or functional designs.

Please document any steps necessary to run your solution and your tests.

### Platform support

This will be run in a unix-ish environment (OS X). If you choose to use a
compiled language, please keep this in mind. (Dependency on Xcode is acceptable
for Objective-C solutions) Please use platform-agnostic constructs where
possible (line-endings and file-path-separators are two problematic areas).