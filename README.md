# A Compiler from Scratch
This is a project to begin trying to understand how compilers work and are constructed, and how to create a programming
language.

This compiler is for a Ruby-like language which is compiled/transpiled into JavaScript.

It has three components:

1. Tokenizer
1. Parser
1. Code generator

Each component can be thought of as a step in the compilation process, and each step takes in some input, transforms it 
into some data structure, and then passes that to the next step in the process as output.

The goal of each step is to get closer and closer to the target language from the source language, whilst also performing 
optimisations along the way, with the final step in the process outputting the target language, which can then be executed
using the appropriate means.

##
Destroy All Software "A Compiler From Scratch"

Crafting Interpreters
